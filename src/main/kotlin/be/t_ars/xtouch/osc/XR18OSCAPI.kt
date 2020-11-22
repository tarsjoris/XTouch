package be.t_ars.xtouch.osc

import be.t_ars.xtouch.util.Listeners
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.util.concurrent.atomic.AtomicBoolean

class XR18OSCAPI(private val host: InetAddress) {
	companion object {
		private const val PORT = 10024

		const val CHANNEL_COUNT = 17
		const val AUX_CHANNEL = 17
		const val BUS_COUNT = 6
		private const val DEBUG = false
	}

	private val socket = DatagramSocket()
	private val running = AtomicBoolean(true)
	private val listeners = Listeners<IOSCListener>()

	fun addListener(listener: IOSCListener) {
		listeners.add(listener)
	}

	fun run() {
		startRequestChanges()

		val buffer = ByteArray(256)
		val udpPacket = DatagramPacket(buffer, buffer.size)
		while (running.get()) {
			socket.receive(udpPacket)
			printPacket(udpPacket.data)
			try {
				val packet = parsePacket(udpPacket.data, udpPacket.length)
				processPacket(packet)
			} catch (e: Exception) {
				val data = String(udpPacket.data, 0, udpPacket.length)
				println("Could not process packet '$data': ${e.message}")
				e.printStackTrace()
			}
		}
	}

	fun stop() {
		running.set(false)
	}

	private fun processPacket(packet: IOSCPacket) {
		when (packet) {
			is OSCBundle ->
				packet.packets.forEach { processPacket(it) }
			is OSCMessage ->
				processMessage(packet)
		}
	}

	private fun processMessage(message: OSCMessage) {
		val parts = message.address.split('/')
		when (parts[0]) {
			"" -> {
				when (parts[1]) {
					"ch" -> {
						val channel = (parts[2][0] - '0') * 10 + (parts[2][1] - '0')
						if (channel in 1..CHANNEL_COUNT)
							processChannelMessage(channel, parts, message)
					}
					"rtn" -> {
						when (parts[2]) {
							"aux" -> processChannelMessage(AUX_CHANNEL, parts, message)
						}
					}
					"lr" -> processLRMessage(parts, message)
					"bus" -> {
						val bus = parts[2][0] - '0'
						if (bus in 1..BUS_COUNT) {
							processBusMessage(bus, parts, message)
						}
					}
				}
			}
		}
	}

	private fun processChannelMessage(channel: Int, parts: List<String>, message: OSCMessage) {
		when (parts[3]) {
			"config" -> {
				when (parts[4]) {
					"name" -> {
						val name = message.getString(0)
						if (name != null) {
							listeners.broadcast { it.channelName(channel, name) }
						}
					}
					"color" -> {
						val color = message.getInt(0)
						if (color != null) {
							listeners.broadcast { it.channelColor(channel, color) }
						}
					}
				}
			}
		}
	}

	private fun processLRMessage(parts: List<String>, message: OSCMessage) {
		when (parts[2]) {
			"mix" -> {
				when (parts[3]) {
					"on" -> {
						val on = message.getInt(0) == 1
						listeners.broadcast { it.lrMixOn(on) }
					}
				}
			}
		}
	}

	private fun processBusMessage(bus: Int, parts: List<String>, message: OSCMessage) {
		when (parts[3]) {
			"mix" -> {
				when (parts[4]) {
					"on" -> {
						val on = message.getInt(0) == 1
						listeners.broadcast { it.busMixOn(bus, on) }
					}
				}
			}
			"config" -> {
				when (parts[4]) {
					"name" -> {
						val name = message.getString(0)
						if (name != null) {
							listeners.broadcast { it.busName(bus, name) }
						}
					}
					"color" -> {
						val color = message.getInt(0)
						if (color != null) {
							listeners.broadcast { it.busColor(bus, color) }
						}
					}
				}
			}
		}
	}

	private fun startRequestChanges() {
		GlobalScope.launch {
			while (running.get()) {
				send("/xremote")
				delay(7500)
			}
		}
	}

	fun requestBusesMixOn() {
		for (i in 1..BUS_COUNT) {
			requestBusMixOn(i)
		}
	}

	fun requestBusMixOn(bus: Int) {
		validateBus(bus)
		send("/bus/$bus/mix/on")
	}

	fun setBusMixOn(bus: Int, on: Boolean) {
		validateBus(bus)
		send("/bus/$bus/mix/on", OSCArgInt(if (on) 1 else 0))
	}

	fun requestLRMixOn() =
		send("/lr/mix/on")

	fun setLRMixOn(on: Boolean) =
		send("/lr/mix/on", OSCArgInt(if (on) 1 else 0))

	fun fetchConfigs() {
		for (i in 1..BUS_COUNT) {
			fetchBusConfig(i)
		}
		for (i in 1..CHANNEL_COUNT) {
			this.fetchChannelConfig(i)
		}
	}

	private fun fetchBusConfig(bus: Int) {
		validateBus(bus)
		fetchConfig("/bus/$bus")
	}

	private fun fetchChannelConfig(channel: Int) {
		validateChannel(channel)
		fetchConfig(getChannelPrefix(channel))
	}

	private fun fetchConfig(prefix: String) {
		send("$prefix/config/name")
		send("$prefix/config/color")
	}

	private fun getChannelPrefix(channel: Int) =
		when (channel) {
			AUX_CHANNEL -> "/rtn/aux"
			else -> "/ch/" + pad(channel)
		}

	private fun send(address: String, vararg args: IOSCArg) =
		send(OSCMessage(address, args))

	private fun send(packet: IOSCPacket) {
		val payload = packet.serialize()
		printPacket(payload)
		socket.send(DatagramPacket(payload, payload.size, host, PORT))
	}

	private fun validateChannel(channel: Int) =
		assert(channel in 1..CHANNEL_COUNT)

	private fun validateBus(bus: Int) =
		assert(bus in 1..BUS_COUNT)

	private fun pad(number: Int) =
		number.toString().padStart(2, '0')

	private fun printPacket(data: ByteArray) {
		if (DEBUG) {
			println(String(data))
			println(data.joinToString(separator = ",", transform = { it.toString() }))
		}
	}
}