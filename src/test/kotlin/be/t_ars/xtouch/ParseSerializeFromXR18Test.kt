package be.t_ars.xtouch

import be.t_ars.xtouch.xctl.FromXR18
import be.t_ars.xtouch.xctl.ToXTouch
import org.junit.jupiter.api.Test

class ParseSerializeFromXR18Test : AbstractParseSerializeTest() {
	private val toXTouch = ToXTouch(this::receivePayload)
	private val fromXR18 = FromXR18 { event ->
		event(toXTouch)
	}

	@Test
	fun sniffRegularly() {
		verify(
			byteArrayOf(
				0xD0.toByte(),
				0x00.toByte(), 0x10.toByte(), 0x20.toByte(), 0x30.toByte(),
				0x40.toByte(), 0x50.toByte(), 0x60.toByte(), 0x70.toByte()
			)
		)
	}

	@Test
	fun sniffStartup() {
		verify(
			byteArrayOf(
				0xF0.toByte(),
				0x00.toByte(),
				0x20.toByte(),
				0x32.toByte(),
				0x58.toByte(),
				0x54.toByte(),
				0x01.toByte(),
				0xF7.toByte()
			)
		)
		verify(
			byteArrayOf(
				0xB0.toByte(),
				0x30.toByte(), 0x00.toByte(),
				0x31.toByte(), 0x00.toByte(),
				0x32.toByte(), 0x00.toByte(),
				0x33.toByte(), 0x00.toByte(),
				0x34.toByte(), 0x00.toByte(),
				0x35.toByte(), 0x00.toByte(),
				0x36.toByte(), 0x00.toByte(),
				0x37.toByte(), 0x00.toByte()
			)
		)
		verify(
			byteArrayOf(
				0xE0.toByte(), 0x7F.toByte(), 0x21.toByte(),
				0xE1.toByte(), 0x5F.toByte(), 0x3B.toByte(),
				0xE2.toByte(), 0x1F.toByte(), 0x1B.toByte(),
				0xE3.toByte(), 0x1F.toByte(), 0x30.toByte(),
				0xE4.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xE5.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xE6.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xE7.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xE8.toByte(), 0x00.toByte(), 0x00.toByte()
			)
		)
		verify(
			byteArrayOf(
				0x90.toByte(),
				0x00.toByte(), 0x00.toByte(), 0x01.toByte(), 0x00.toByte(), 0x02.toByte(), 0x00.toByte(),
				0x03.toByte(), 0x00.toByte(), 0x04.toByte(), 0x00.toByte(), 0x05.toByte(), 0x00.toByte(),
				0x06.toByte(), 0x00.toByte(), 0x07.toByte(), 0x00.toByte(), 0x08.toByte(), 0x00.toByte(),
				0x09.toByte(), 0x00.toByte(), 0x0A.toByte(), 0x00.toByte(), 0x0B.toByte(), 0x00.toByte(),
				0x0C.toByte(), 0x00.toByte(), 0x0D.toByte(), 0x00.toByte(), 0x0E.toByte(), 0x00.toByte(),
				0x0F.toByte(), 0x00.toByte(), 0x10.toByte(), 0x00.toByte(), 0x11.toByte(), 0x00.toByte(),
				0x12.toByte(), 0x00.toByte(), 0x13.toByte(), 0x00.toByte(), 0x14.toByte(), 0x00.toByte(),
				0x15.toByte(), 0x00.toByte(), 0x16.toByte(), 0x00.toByte(), 0x17.toByte(), 0x00.toByte(),
				0x18.toByte(), 0x02.toByte(), 0x19.toByte(), 0x00.toByte(), 0x1A.toByte(), 0x00.toByte(),
				0x1B.toByte(), 0x00.toByte(), 0x1C.toByte(), 0x00.toByte(), 0x1D.toByte(), 0x00.toByte(),
				0x1E.toByte(), 0x00.toByte(), 0x1F.toByte(), 0x00.toByte(), 0x20.toByte(), 0x00.toByte(),
				0x21.toByte(), 0x00.toByte(), 0x22.toByte(), 0x00.toByte(), 0x23.toByte(), 0x00.toByte(),
				0x24.toByte(), 0x00.toByte(), 0x25.toByte(), 0x00.toByte(), 0x26.toByte(), 0x00.toByte(),
				0x27.toByte(), 0x00.toByte(), 0x28.toByte(), 0x00.toByte(), 0x29.toByte(), 0x00.toByte(),
				0x2A.toByte(), 0x00.toByte(), 0x2B.toByte(), 0x00.toByte(), 0x2C.toByte(), 0x00.toByte(),
				0x2D.toByte(), 0x00.toByte(), 0x2E.toByte(), 0x00.toByte(), 0x2F.toByte(), 0x00.toByte(),
				0x30.toByte(), 0x00.toByte(), 0x31.toByte(), 0x00.toByte(), 0x32.toByte(), 0x00.toByte(),
				0x33.toByte(), 0x02.toByte(), 0x34.toByte(), 0x00.toByte(), 0x35.toByte(), 0x00.toByte(),
				0x36.toByte(), 0x00.toByte(), 0x37.toByte(), 0x00.toByte(), 0x38.toByte(), 0x00.toByte(),
				0x39.toByte(), 0x00.toByte(), 0x3A.toByte(), 0x00.toByte(), 0x3B.toByte(), 0x00.toByte(),
				0x3C.toByte(), 0x00.toByte(), 0x3D.toByte(), 0x00.toByte(), 0x3E.toByte(), 0x00.toByte(),
				0x3F.toByte(), 0x00.toByte(), 0x40.toByte(), 0x00.toByte(), 0x41.toByte(), 0x00.toByte(),
				0x42.toByte(), 0x00.toByte(), 0x43.toByte(), 0x00.toByte(), 0x44.toByte(), 0x00.toByte(),
				0x45.toByte(), 0x00.toByte(), 0x46.toByte(), 0x00.toByte(), 0x47.toByte(), 0x00.toByte(),
				0x48.toByte(), 0x00.toByte(), 0x49.toByte(), 0x00.toByte(), 0x4A.toByte(), 0x00.toByte(),
				0x4B.toByte(), 0x00.toByte(), 0x4C.toByte(), 0x00.toByte(), 0x4D.toByte(), 0x00.toByte(),
				0x4E.toByte(), 0x00.toByte(), 0x4F.toByte(), 0x00.toByte(), 0x50.toByte(), 0x00.toByte(),
				0x51.toByte(), 0x00.toByte(), 0x52.toByte(), 0x00.toByte(), 0x53.toByte(), 0x00.toByte(),
				0x54.toByte(), 0x00.toByte(), 0x55.toByte(), 0x00.toByte(), 0x56.toByte(), 0x00.toByte(),
				0x57.toByte(), 0x00.toByte(), 0x58.toByte(), 0x00.toByte(), 0x59.toByte(), 0x00.toByte(),
				0x5A.toByte(), 0x00.toByte(), 0x5B.toByte(), 0x00.toByte(), 0x5C.toByte(), 0x00.toByte(),
				0x5D.toByte(), 0x00.toByte(), 0x5E.toByte(), 0x00.toByte(), 0x5F.toByte(), 0x00.toByte(),
				0x60.toByte(), 0x00.toByte(), 0x61.toByte(), 0x00.toByte(), 0x62.toByte(), 0x00.toByte(),
				0x63.toByte(), 0x00.toByte(), 0x64.toByte(), 0x00.toByte(), 0x65.toByte(), 0x00.toByte(),
				0x66.toByte(), 0x00.toByte(), 0x67.toByte(), 0x00.toByte(), 0x68.toByte(), 0x00.toByte(),
				0x69.toByte(), 0x00.toByte(), 0x6A.toByte(), 0x00.toByte(), 0x6B.toByte(), 0x00.toByte(),
				0x6C.toByte(), 0x00.toByte(), 0x6D.toByte(), 0x00.toByte(), 0x6E.toByte(), 0x00.toByte(),
				0x6F.toByte(), 0x00.toByte(), 0x70.toByte(), 0x00.toByte(), 0x71.toByte(), 0x00.toByte(),
				0x72.toByte(), 0x00.toByte(), 0x73.toByte(), 0x00.toByte()
			)
		)
		verify(
			byteArrayOf(
				0xB0.toByte(),
				0x30.toByte(), 0x40.toByte(), 0x38.toByte(), 0x00.toByte(),
				0x31.toByte(), 0x40.toByte(), 0x39.toByte(), 0x00.toByte(),
				0x32.toByte(), 0x40.toByte(), 0x3A.toByte(), 0x00.toByte(),
				0x33.toByte(), 0x40.toByte(), 0x3B.toByte(), 0x01.toByte(),
				0x34.toByte(), 0x40.toByte(), 0x3C.toByte(), 0x00.toByte(),
				0x35.toByte(), 0x40.toByte(), 0x3D.toByte(), 0x00.toByte(),
				0x36.toByte(), 0x40.toByte(), 0x3E.toByte(), 0x00.toByte(),
				0x37.toByte(), 0x40.toByte(), 0x3F.toByte(), 0x00.toByte()
			)
		)
		verify(
			byteArrayOf(
				0xB0.toByte(),
				0x60.toByte(), 0x00.toByte(), 0x61.toByte(), 0x06.toByte(),
				0x62.toByte(), 0x00.toByte(), 0x63.toByte(), 0x00.toByte(),
				0x64.toByte(), 0x00.toByte(), 0x65.toByte(), 0x00.toByte(),
				0x66.toByte(), 0x00.toByte(), 0x67.toByte(), 0x00.toByte(),
				0x68.toByte(), 0x00.toByte(), 0x69.toByte(), 0x00.toByte(),
				0x6A.toByte(), 0x00.toByte(), 0x6B.toByte(), 0x00.toByte()
			)
		)
		verify(
			byteArrayOf(
				0xF0.toByte(), 0x00.toByte(), 0x00.toByte(), 0x66.toByte(), 0x58.toByte(),
				0x20.toByte(), 0x47.toByte(),
				0x43.toByte(), 0x68.toByte(), 0x20.toByte(), 0x31.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0x43.toByte(), 0x61.toByte(), 0x6A.toByte(), 0x6F.toByte(), 0x6E.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xF7.toByte(),
				0xF0.toByte(), 0x00.toByte(), 0x00.toByte(), 0x66.toByte(), 0x58.toByte(),
				0x21.toByte(), 0x47.toByte(),
				0x43.toByte(), 0x68.toByte(), 0x20.toByte(), 0x32.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0x53.toByte(), 0x6E.toByte(), 0x61.toByte(), 0x72.toByte(), 0x65.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xF7.toByte(),
				0xF0.toByte(), 0x00.toByte(), 0x00.toByte(), 0x66.toByte(), 0x58.toByte(),
				0x22.toByte(), 0x47.toByte(),
				0x43.toByte(), 0x68.toByte(), 0x20.toByte(), 0x33.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0x4F.toByte(), 0x48.toByte(), 0x20.toByte(), 0x4C.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xF7.toByte(),
				0xF0.toByte(), 0x00.toByte(), 0x00.toByte(), 0x66.toByte(), 0x58.toByte(),
				0x23.toByte(), 0x47.toByte(),
				0x43.toByte(), 0x68.toByte(), 0x20.toByte(), 0x34.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0x4F.toByte(), 0x48.toByte(), 0x20.toByte(), 0x52.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xF7.toByte(),
				0xF0.toByte(), 0x00.toByte(), 0x00.toByte(), 0x66.toByte(), 0x58.toByte(),
				0x24.toByte(), 0x07.toByte(),
				0x43.toByte(), 0x68.toByte(), 0x20.toByte(), 0x35.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xF7.toByte(),
				0xF0.toByte(), 0x00.toByte(), 0x00.toByte(), 0x66.toByte(), 0x58.toByte(),
				0x25.toByte(), 0x07.toByte(),
				0x43.toByte(), 0x68.toByte(), 0x20.toByte(), 0x36.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xF7.toByte(),
				0xF0.toByte(), 0x00.toByte(), 0x00.toByte(), 0x66.toByte(), 0x58.toByte(),
				0x26.toByte(), 0x07.toByte(),
				0x43.toByte(), 0x68.toByte(), 0x20.toByte(), 0x37.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xF7.toByte(),
				0xF0.toByte(), 0x00.toByte(), 0x00.toByte(), 0x66.toByte(), 0x58.toByte(),
				0x27.toByte(), 0x07.toByte(),
				0x43.toByte(), 0x68.toByte(), 0x20.toByte(), 0x38.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xF7.toByte()
			)
		)
	}

	@Test
	fun sniffRotateKnob() {
		verify(byteArrayOf(0xB0.toByte(), 0x30.toByte(), 0x40.toByte(), 0x38.toByte(), 0x01.toByte()))
		verify(byteArrayOf(0xB0.toByte(), 0x31.toByte(), 0x40.toByte(), 0x39.toByte(), 0x01.toByte()))
	}

	@Test
	fun sniffKnobPress() {
		verify(byteArrayOf(0xB0.toByte(), 0x30.toByte(), 0x40.toByte(), 0x38.toByte(), 0x00.toByte()))
	}

	@Test
	fun sniffFaderMove() {
		verify(byteArrayOf(0xE0.toByte(), 0x2F.toByte(), 0x1E.toByte()))
		verify(byteArrayOf(0xE0.toByte(), 0x6F.toByte(), 0x26.toByte()))
	}

	@Test
	fun sniffMainFaderMove() {
		verify(byteArrayOf(0xE8.toByte(), 0x3C.toByte(), 0x02.toByte()))
	}

	@Test
	fun sniffButtonPress() {
		verify(byteArrayOf(0x90.toByte(), 0x18.toByte(), 0x00.toByte(), 0x19.toByte(), 0x02.toByte()))
	}

	@Test
	fun sniffDigits() {
		verify(byteArrayOf(0xB0.toByte(), 0x61.toByte(), 0x6F.toByte()))
		verify(byteArrayOf(0x90.toByte(), 0x18.toByte(), 0x02.toByte()))
		verify(
			byteArrayOf(
				0xB0.toByte(),
				0x62.toByte(), 0x71.toByte(),
				0x73.toByte(), 0x76.toByte(),
				0x64.toByte(), 0x06.toByte()
			)
		)
	}

	@Test
	fun sniffSwitchbank() {
		verify(
			byteArrayOf(
				0xE0.toByte(), 0x0F.toByte(), 0x2C.toByte(),
				0xE1.toByte(), 0x5F.toByte(), 0x3B.toByte(),
				0xE2.toByte(), 0x1F.toByte(), 0x1B.toByte(),
				0xE3.toByte(), 0x1F.toByte(), 0x30.toByte(),
				0xE6.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xE7.toByte(), 0x00.toByte(), 0x00.toByte()
			)
		)
		verify(
			byteArrayOf(
				0xB0.toByte(),
				0x31.toByte(), 0x40.toByte(),
				0x39.toByte(), 0x01.toByte(),
				0x33.toByte(), 0x40.toByte(),
				0x3B.toByte(), 0x01.toByte(),
				0x36.toByte(), 0x40.toByte(),
				0x3E.toByte(), 0x00.toByte(),
				0x37.toByte(), 0x40.toByte(),
				0x3F.toByte(), 0x00.toByte()
			)
		)
		verify(byteArrayOf(0x90.toByte(), 0x18.toByte(), 0x00.toByte()))
		verify(
			byteArrayOf(
				0xF0.toByte(), 0x00.toByte(), 0x00.toByte(), 0x66.toByte(), 0x58.toByte(),
				0x20.toByte(), 0x47.toByte(),
				0x43.toByte(), 0x68.toByte(), 0x20.toByte(), 0x31.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0x43.toByte(), 0x61.toByte(), 0x6A.toByte(), 0x6F.toByte(), 0x6E.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xF7.toByte(),
				0xF0.toByte(), 0x00.toByte(), 0x00.toByte(), 0x66.toByte(), 0x58.toByte(),
				0x21.toByte(), 0x47.toByte(),
				0x43.toByte(), 0x68.toByte(), 0x20.toByte(), 0x32.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0x53.toByte(), 0x6E.toByte(), 0x61.toByte(), 0x72.toByte(), 0x65.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xF7.toByte(),
				0xF0.toByte(), 0x00.toByte(), 0x00.toByte(), 0x66.toByte(), 0x58.toByte(),
				0x22.toByte(), 0x47.toByte(),
				0x43.toByte(), 0x68.toByte(), 0x20.toByte(), 0x33.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0x4F.toByte(), 0x48.toByte(), 0x20.toByte(), 0x4C.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xF7.toByte(),
				0xF0.toByte(), 0x00.toByte(), 0x00.toByte(), 0x66.toByte(), 0x58.toByte(),
				0x23.toByte(), 0x47.toByte(),
				0x43.toByte(), 0x68.toByte(), 0x20.toByte(), 0x34.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0x4F.toByte(), 0x48.toByte(), 0x20.toByte(), 0x52.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xF7.toByte(),
				0xF0.toByte(), 0x00.toByte(), 0x00.toByte(), 0x66.toByte(), 0x58.toByte(),
				0x24.toByte(), 0x07.toByte(),
				0x43.toByte(), 0x68.toByte(), 0x20.toByte(), 0x35.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xF7.toByte(),
				0xF0.toByte(), 0x00.toByte(), 0x00.toByte(), 0x66.toByte(), 0x58.toByte(),
				0x25.toByte(), 0x07.toByte(),
				0x43.toByte(), 0x68.toByte(), 0x20.toByte(), 0x36.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xF7.toByte(),
				0xF0.toByte(), 0x00.toByte(), 0x00.toByte(), 0x66.toByte(), 0x58.toByte(),
				0x26.toByte(), 0x07.toByte(),
				0x43.toByte(), 0x68.toByte(), 0x20.toByte(), 0x37.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xF7.toByte(),
				0xF0.toByte(), 0x00.toByte(), 0x00.toByte(), 0x66.toByte(), 0x58.toByte(),
				0x27.toByte(), 0x07.toByte(),
				0x43.toByte(), 0x68.toByte(), 0x20.toByte(), 0x38.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
				0xF7.toByte()
			)
		)
	}

	override fun doAction(data: ByteArray) {
		fromXR18.processPacket(data)
	}
}