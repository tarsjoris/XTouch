package be.t_ars.xtouch.router

import be.t_ars.xtouch.util.partial
import be.t_ars.xtouch.xctl.AbstractButtonLEDEvent
import be.t_ars.xtouch.xctl.ButtonLEDEvent
import be.t_ars.xtouch.xctl.EButton
import be.t_ars.xtouch.xctl.Event
import be.t_ars.xtouch.xctl.IXR18Events
import be.t_ars.xtouch.xctl.IXR18Listener
import be.t_ars.xtouch.xctl.IXTouchEvents
import be.t_ars.xtouch.xctl.IXTouchListener

class AddonBusOrder : IAddon {
	private val xTouchListener = XTouchListener()
	private val xR18Listener = XR18Listener()

	override fun processEventFromXTouch(event: Event<IXTouchEvents>): Event<IXTouchEvents>? =
		xTouchListener.processEvent(event)

	override fun processEventFromXR18(event: Event<IXR18Events>): Event<IXR18Events>? =
		xR18Listener.processEvent(event)

	inner class XTouchListener : IXTouchListener {
		private var nextEvent: Event<IXTouchEvents>? = null

		fun processEvent(event: Event<IXTouchEvents>): Event<IXTouchEvents>? {
			nextEvent = event
			event(this)
			return nextEvent
		}

		override fun automationPressed(automation: Int, down: Boolean) {
			nextEvent = partial(
				when (automation) {
					2 -> 3
					3 -> 5
					4 -> 2
					5 -> 4
					else -> automation
				},
				down,
				IXTouchEvents::automationPressed
			)
		}
	}

	inner class XR18Listener : IXR18Listener {
		private var nextEvent: Event<IXR18Events>? = null

		fun processEvent(event: Event<IXR18Events>): Event<IXR18Events>? {
			nextEvent = event
			event(this)
			return nextEvent
		}

		override fun setButtonLEDs(buttonLEDEvents: Array<AbstractButtonLEDEvent>) {
			if (buttonLEDEvents.any(this::isRemappadBusEvent)) {
				nextEvent = partial(buttonLEDEvents.map { event ->
					if (event is ButtonLEDEvent) {
						when (event.button) {
							EButton.AUTOMATION_WRITE -> ButtonLEDEvent(EButton.AUTOMATION_TOUCH, event.mode)
							EButton.AUTOMATION_TRIM -> ButtonLEDEvent(EButton.AUTOMATION_WRITE, event.mode)
							EButton.AUTOMATION_TOUCH -> ButtonLEDEvent(EButton.AUTOMATION_LATCH, event.mode)
							EButton.AUTOMATION_LATCH -> ButtonLEDEvent(EButton.AUTOMATION_TRIM, event.mode)
							else -> event
						}
					} else {
						event
					}
				}.toTypedArray(), IXR18Events::setButtonLEDs)
			}
		}

		private fun isRemappadBusEvent(event: AbstractButtonLEDEvent) =
			event is ButtonLEDEvent && event.button in EButton.AUTOMATION_WRITE..EButton.AUTOMATION_LATCH
	}
}