package be.t_ars.xtouch.demo

import be.t_ars.xtouch.osc.XR18OSCAPI
import java.net.Inet4Address

fun main() {
	println("Starting ..")
	val xr18API = XR18OSCAPI(Inet4Address.getByName("192.168.0.2"))
	xr18API.run()
}