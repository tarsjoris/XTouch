package be.t_ars.xtouch

import be.t_ars.xtouch.xairedit.IXAirEditInteractor
import be.t_ars.xtouch.xctl.IXTouchEvents
import org.junit.jupiter.api.Test

class EncoderChannelSwitchingTest {
	private fun performTest(
		expectedChannel: Int,
		expectedOutput: Int,
		whenPart: (IXTouchEvents) -> Unit
	) {
		performTest(expectedChannel, expectedOutput, IXAirEditInteractor.ETab.EQ, whenPart)
	}

	@Test
	fun testEncoderNextChannelBank1() {
		for (channel in 1..8) {
			performTest(
				channel + 1,
				XAirEditInteractorMock.OUTPUT_MAINLR
			) {
				it.channelSelectPressed(channel, true)
				it.encoderEqPressed(true)
				it.knobRotated(1, true)
			}
		}
	}

	@Test
	fun testEncoderNextChannelBank2() {
		for (channel in 1..7) {
			performTest(
				channel + 8 + 1,
				XAirEditInteractorMock.OUTPUT_MAINLR
			) {
				it.nextBankPressed(true)
				it.channelSelectPressed(channel, true)
				it.encoderEqPressed(true)
				it.knobRotated(1, true)
			}
		}
		// from channel16 to aux
		performTest(
			XAirEditInteractorMock.CHANNEL_AUX,
			XAirEditInteractorMock.OUTPUT_MAINLR
		) {
			it.nextBankPressed(true)
			it.channelSelectPressed(8, true)
			it.encoderEqPressed(true)
			it.knobRotated(1, true)
		}
	}

	@Test
	fun testEncoderNextChannelAux() {
		// from aux to rtn1
		performTest(
			XAirEditInteractorMock.CHANNEL_RTN1,
			XAirEditInteractorMock.OUTPUT_MAINLR
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(1, true)
			it.encoderEqPressed(true)
			it.knobRotated(1, true)
		}
	}

	@Test
	fun testEncoderNextChannelRtn() {
		for (rtn in 1..3) {
			performTest(
				XAirEditInteractorMock.CHANNEL_RTN1 + rtn - 1 + 1,
				XAirEditInteractorMock.OUTPUT_MAINLR
			) {
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.channelSelectPressed(4 + rtn, true)
				it.encoderEqPressed(true)
				it.knobRotated(1, true)
			}
		}
		// from rtn4 to bus1
		performTest(
			XAirEditInteractorMock.CHANNEL_MAIN,
			XAirEditInteractorMock.OUTPUT_BUS1
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(8, true)
			it.encoderEqPressed(true)
			it.knobRotated(1, true)
		}
	}

	@Test
	fun testEncoderNextChannelBus() {
		for (bus in 1..5) {
			performTest(
				XAirEditInteractorMock.CHANNEL_MAIN,
				XAirEditInteractorMock.OUTPUT_BUS1 + bus - 1 + 1
			) {
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.channelSelectPressed(bus, true)
				it.encoderEqPressed(true)
				it.knobRotated(1, true)
			}
		}
		// from bus6 to fx1
		performTest(
			XAirEditInteractorMock.CHANNEL_MAIN,
			XAirEditInteractorMock.OUTPUT_FX1
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(6, true)
			it.encoderEqPressed(true)
			it.knobRotated(1, true)
		}
	}

	@Test
	fun testEncoderNextChannelFx() {
		for (fx in 1..3) {
			performTest(
				XAirEditInteractorMock.CHANNEL_MAIN,
				XAirEditInteractorMock.OUTPUT_FX1 + fx - 1 + 1
			) {
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.channelSelectPressed(fx, true)
				it.encoderEqPressed(true)
				it.knobRotated(1, true)
			}
		}
		// from fx4 to main
		performTest(
			XAirEditInteractorMock.CHANNEL_MAIN,
			XAirEditInteractorMock.OUTPUT_MAINLR
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(4, true)
			it.encoderEqPressed(true)
			it.knobRotated(1, true)
		}
	}

	@Test
	fun testEncoderNextChannelMain() {
		// no next channel
		performTest(
			XAirEditInteractorMock.CHANNEL_MAIN,
			XAirEditInteractorMock.OUTPUT_MAINLR
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(8, true)
			it.encoderEqPressed(true)
			it.knobRotated(1, true)
		}
	}

	@Test
	fun testEncoderPreviousChannelBank1() {
		// no previous channel
		performTest(
			1,
			XAirEditInteractorMock.OUTPUT_MAINLR
		) {
			it.channelSelectPressed(1, true)
			it.encoderEqPressed(true)
			it.knobRotated(1, false)
		}
		for (channel in 2..8) {
			performTest(
				channel - 1,
				XAirEditInteractorMock.OUTPUT_MAINLR
			) {
				it.channelSelectPressed(channel, true)
				it.encoderEqPressed(true)
				it.knobRotated(1, false)
			}
		}
	}

	@Test
	fun testEncoderPreviousChannelBank2() {
		for (channel in 1..8) {
			performTest(
				channel + 8 - 1,
				XAirEditInteractorMock.OUTPUT_MAINLR
			) {
				it.nextBankPressed(true)
				it.channelSelectPressed(channel, true)
				it.encoderEqPressed(true)
				it.knobRotated(1, false)
			}
		}
	}

	@Test
	fun testEncoderPreviousChannelAux() {
		// from aux to channel16
		performTest(
			16,
			XAirEditInteractorMock.OUTPUT_MAINLR
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(1, true)
			it.encoderEqPressed(true)
			it.knobRotated(1, false)
		}
	}

	@Test
	fun testEncoderPreviousChannelRtn() {
		// from rtn1 to aux
		performTest(
			XAirEditInteractorMock.CHANNEL_AUX,
			XAirEditInteractorMock.OUTPUT_MAINLR
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(5, true)
			it.encoderEqPressed(true)
			it.knobRotated(1, false)
		}
		for (rtn in 2..4) {
			performTest(
				XAirEditInteractorMock.CHANNEL_RTN1 + rtn - 1 - 1,
				XAirEditInteractorMock.OUTPUT_MAINLR
			) {
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.channelSelectPressed(4 + rtn, true)
				it.encoderEqPressed(true)
				it.knobRotated(1, false)
			}
		}
	}

	@Test
	fun testEncoderPreviousChannelBus() {
		// from bus1 to rtn4
		performTest(
			XAirEditInteractorMock.CHANNEL_RTN4,
			XAirEditInteractorMock.OUTPUT_MAINLR
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(1, true)
			it.encoderEqPressed(true)
			it.knobRotated(1, false)
		}
		for (bus in 2..6) {
			performTest(
				XAirEditInteractorMock.CHANNEL_MAIN,
				XAirEditInteractorMock.OUTPUT_BUS1 + bus - 1 - 1
			) {
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.channelSelectPressed(bus, true)
				it.encoderEqPressed(true)
				it.knobRotated(1, false)
			}
		}
	}

	@Test
	fun testEncoderPreviousChannelFx() {
		// from fx1 to bus6
		performTest(
			XAirEditInteractorMock.CHANNEL_MAIN,
			XAirEditInteractorMock.OUTPUT_BUS6
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(1, true)
			it.encoderEqPressed(true)
			it.knobRotated(1, false)
		}
		for (fx in 2..4) {
			performTest(
				XAirEditInteractorMock.CHANNEL_MAIN,
				XAirEditInteractorMock.OUTPUT_FX1 + fx - 1 - 1
			) {
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.channelSelectPressed(fx, true)
				it.encoderEqPressed(true)
				it.knobRotated(1, false)
			}
		}
	}

	@Test
	fun testEncoderPreviousChannelMain() {
		// from main to fx4
		performTest(
			XAirEditInteractorMock.CHANNEL_MAIN,
			XAirEditInteractorMock.OUTPUT_FX4
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(8, true)
			it.encoderEqPressed(true)
			it.knobRotated(1, false)
		}
	}

	@Test
	fun testEncoderNextBank1() {
		for (channel in 1..8) {
			performTest(
				channel + 8,
				XAirEditInteractorMock.OUTPUT_MAINLR
			) {
				it.channelSelectPressed(channel, true)
				it.encoderEqPressed(true)
				it.nextBankPressed(true)
			}
		}
	}

	@Test
	fun testEncoderNextBank2() {
		// from channel9 to aux
		performTest(
			XAirEditInteractorMock.CHANNEL_AUX,
			XAirEditInteractorMock.OUTPUT_MAINLR
		) {
			it.nextBankPressed(true)
			it.channelSelectPressed(1, true)
			it.encoderEqPressed(true)
			it.nextBankPressed(true)
		}
		// stay on channel10-12
		for (channel in 2..4) {
			performTest(
				channel + 8,
				XAirEditInteractorMock.OUTPUT_MAINLR
			) {
				it.nextBankPressed(true)
				it.channelSelectPressed(channel, true)
				it.encoderEqPressed(true)
				it.nextBankPressed(true)
			}
		}
		// from channel13-16 to rtn1-4
		for (channel in 1..4) {
			performTest(
				XAirEditInteractorMock.CHANNEL_RTN1 + channel - 1,
				XAirEditInteractorMock.OUTPUT_MAINLR
			) {
				it.nextBankPressed(true)
				it.channelSelectPressed(channel + 4, true)
				it.encoderEqPressed(true)
				it.nextBankPressed(true)
			}
		}
	}

	@Test
	fun testEncoderNextBank3() {
		// from aux to bus1
		performTest(
			XAirEditInteractorMock.CHANNEL_MAIN,
			XAirEditInteractorMock.OUTPUT_BUS1
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(1, true)
			it.encoderEqPressed(true)
			it.nextBankPressed(true)
		}
		// stay on channel10-12
		for (channel in 2..4) {
			performTest(
				channel + 8,
				XAirEditInteractorMock.OUTPUT_MAINLR
			) {
				it.nextBankPressed(true)
				it.channelSelectPressed(channel, true)
				it.encoderEqPressed(true)
				it.nextBankPressed(true)
				it.nextBankPressed(true)
			}
		}
		// from rtn1-2 to bus5-6
		for (channel in 1..2) {
			performTest(
				XAirEditInteractorMock.CHANNEL_MAIN,
				XAirEditInteractorMock.OUTPUT_BUS5 + channel - 1
			) {
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.channelSelectPressed(channel + 4, true)
				it.encoderEqPressed(true)
				it.nextBankPressed(true)
			}
		}
		// stay on rtn3
		performTest(
			XAirEditInteractorMock.CHANNEL_RTN3,
			XAirEditInteractorMock.OUTPUT_MAINLR
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(7, true)
			it.encoderEqPressed(true)
			it.nextBankPressed(true)
		}
		// from rtn4 to main
		performTest(
			XAirEditInteractorMock.CHANNEL_MAIN,
			XAirEditInteractorMock.OUTPUT_MAINLR
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(8, true)
			it.encoderEqPressed(true)
			it.nextBankPressed(true)
		}
	}

	@Test
	fun testEncoderNextBank4() {
		// from bus1-4 to fx1-4
		for (channel in 1..4) {
			performTest(
				XAirEditInteractorMock.CHANNEL_MAIN,
				XAirEditInteractorMock.OUTPUT_FX1 + channel - 1
			) {
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.channelSelectPressed(channel, true)
				it.encoderEqPressed(true)
				it.nextBankPressed(true)
			}
		}
		// stay on bus5-6
		for (channel in 1..2) {
			performTest(
				XAirEditInteractorMock.CHANNEL_MAIN,
				XAirEditInteractorMock.OUTPUT_BUS5 + channel - 1
			) {
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.channelSelectPressed(channel + 4, true)
				it.encoderEqPressed(true)
				it.nextBankPressed(true)
			}
		}
		// stay on rtn3
		performTest(
			XAirEditInteractorMock.CHANNEL_RTN3,
			XAirEditInteractorMock.OUTPUT_MAINLR
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(7, true)
			it.encoderEqPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
		}
		// stay on main
		performTest(
			XAirEditInteractorMock.CHANNEL_MAIN,
			XAirEditInteractorMock.OUTPUT_MAINLR
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(8, true)
			it.encoderEqPressed(true)
			it.nextBankPressed(true)
		}
	}

	@Test
	fun testEncoderNextBank5() {
		// stay on fx1-4
		for (channel in 1..4) {
			performTest(
				XAirEditInteractorMock.CHANNEL_MAIN,
				XAirEditInteractorMock.OUTPUT_FX1 + channel - 1
			) {
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.channelSelectPressed(channel, true)
				it.encoderEqPressed(true)
				it.nextBankPressed(true)
			}
		}
		// stay on bus5-6
		for (channel in 1..2) {
			performTest(
				XAirEditInteractorMock.CHANNEL_MAIN,
				XAirEditInteractorMock.OUTPUT_BUS5 + channel - 1
			) {
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.channelSelectPressed(channel + 4, true)
				it.encoderEqPressed(true)
				it.nextBankPressed(true)
				it.nextBankPressed(true)
			}
		}
		// stay on rtn3
		performTest(
			XAirEditInteractorMock.CHANNEL_RTN3,
			XAirEditInteractorMock.OUTPUT_MAINLR
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(7, true)
			it.encoderEqPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
		}
		// stay on main
		performTest(
			XAirEditInteractorMock.CHANNEL_MAIN,
			XAirEditInteractorMock.OUTPUT_MAINLR
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(8, true)
			it.encoderEqPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
		}
	}

	@Test
	fun testEncoderNextBankStayAfterUnassign() {
		// stay on channel, even if current bank assigns a channel to the position
		performTest(
			10,
			XAirEditInteractorMock.OUTPUT_MAINLR
		) {
			it.nextBankPressed(true)
			it.channelSelectPressed(2, true)
			it.encoderEqPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
		}
	}

	@Test
	fun testEncoderNextBankPickupAgainAfterUnassign() {
		// after staying on channel because the position wasn't assigned,
		// pick up switching once you pass the current channel
		performTest(
			XAirEditInteractorMock.CHANNEL_MAIN,
			XAirEditInteractorMock.OUTPUT_FX2
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(2, true)
			it.encoderEqPressed(true)
			it.previousBankPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
		}
	}

	@Test
	fun testEncoderPreviousBank1() {
		// stay on channel1-8
		for (channel in 1..8) {
			performTest(
				channel,
				XAirEditInteractorMock.OUTPUT_MAINLR
			) {
				it.channelSelectPressed(channel, true)
				it.encoderEqPressed(true)
				it.previousBankPressed(true)
			}
		}
	}

	@Test
	fun testEncoderPreviousBank2() {
		for (channel in 1..8) {
			performTest(
				channel,
				XAirEditInteractorMock.OUTPUT_MAINLR
			) {
				it.nextBankPressed(true)
				it.channelSelectPressed(channel, true)
				it.encoderEqPressed(true)
				it.previousBankPressed(true)
			}
		}
	}

	@Test
	fun testEncoderPreviousBank3() {
		// from aux to channel 9
		performTest(
			9,
			XAirEditInteractorMock.OUTPUT_MAINLR
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(1, true)
			it.encoderEqPressed(true)
			it.previousBankPressed(true)
		}
		// stay on bus2-4
		for (channel in 2..4) {
			performTest(
				XAirEditInteractorMock.CHANNEL_MAIN,
				XAirEditInteractorMock.OUTPUT_BUS1 + channel - 1
			) {
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.nextBankPressed(true) // one further because 2-4 is unassigned on bank 3
				it.channelSelectPressed(channel, true)
				it.encoderEqPressed(true)
				it.previousBankPressed(true)
				it.previousBankPressed(true)
			}
		}
		// from rtn1-4 to channel13-18
		for (channel in 1..4) {
			performTest(
				12 + channel,
				XAirEditInteractorMock.OUTPUT_MAINLR
			) {
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.channelSelectPressed(channel + 4, true)
				it.encoderEqPressed(true)
				it.previousBankPressed(true)
			}
		}
	}

	@Test
	fun testEncoderPreviousBank4() {
		// from bus1 to aux
		performTest(
			XAirEditInteractorMock.CHANNEL_AUX,
			XAirEditInteractorMock.OUTPUT_MAINLR
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(1, true)
			it.encoderEqPressed(true)
			it.previousBankPressed(true)
		}
		// stay on bus2-4
		for (channel in 2..4) {
			performTest(
				XAirEditInteractorMock.CHANNEL_MAIN,
				XAirEditInteractorMock.OUTPUT_BUS1 + channel - 1
			) {
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.channelSelectPressed(channel, true)
				it.encoderEqPressed(true)
				it.previousBankPressed(true)
			}
		}
		// from bus5-6 to rtn1-2
		for (channel in 1..2) {
			performTest(
				XAirEditInteractorMock.CHANNEL_RTN1 + channel - 1,
				XAirEditInteractorMock.OUTPUT_MAINLR
			) {
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.nextBankPressed(true)
				it.channelSelectPressed(channel + 4, true)
				it.encoderEqPressed(true)
				it.previousBankPressed(true)
			}
		}
		// can't go back from 7th position on last tab
		// from main to rtn4
		performTest(
			XAirEditInteractorMock.CHANNEL_RTN4,
			XAirEditInteractorMock.OUTPUT_MAINLR
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(8, true)
			it.encoderEqPressed(true)
			it.previousBankPressed(true)
		}
	}

	@Test
	fun testEncoderPreviousBankPickupAgainAfterUnassign() {
		// after staying on channel because the position wasn't assigned,
		// pick up switching once you pass the current channel
		performTest(
			2,
			XAirEditInteractorMock.OUTPUT_MAINLR
		) {
			it.nextBankPressed(true)
			it.channelSelectPressed(2, true)
			it.encoderEqPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.previousBankPressed(true)
			it.previousBankPressed(true)
			it.previousBankPressed(true)
		}
	}

	@Test
	fun testEncoderPreviousBankStayAfterUnassign() {
		// stay on bus2
		performTest(
			XAirEditInteractorMock.CHANNEL_MAIN,
			XAirEditInteractorMock.OUTPUT_BUS2
		) {
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.nextBankPressed(true)
			it.channelSelectPressed(2, true)
			it.encoderEqPressed(true)
			it.previousBankPressed(true)
		}
	}
}