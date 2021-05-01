import React, { useCallback, useEffect, useReducer, useState } from 'react';
import { Slider, Typography, makeStyles, withStyles } from '@material-ui/core';
import './index.css';
import { IChannelConfig, IScribbleStripConfig, reducer, initialState } from './state';
import { createConnection } from './connection';
import SelectBusDialog from './SelectBusDialog';
import { IScribbleStylesProps, scribbleColorProperties } from './scribblecolors';
import { DefaultTheme } from 'styled-components';


const useTopBarStyles = makeStyles<DefaultTheme, IScribbleStylesProps, string>({
	header: {
		top: 0,
		left: 'auto',
		right: 0,
		position: 'sticky',
		width: '100%',
		display: 'flex',
		zIndex: 1100,
		boxSizing: 'border-box',
		flexDirection: 'column',
	},
	headerTitle: {
		minHeight: '64px',
		paddingLeft: '24px',
		paddingRight: '24px',
		display: 'flex',
		position: 'relative',
		alignItems: 'center',
	},
	scribble: scribbleColorProperties,
})


interface ITopBarProps {
	busConfigs: IScribbleStripConfig[],
	selectedBus: number
}

const TopBar = function (props: ITopBarProps) {
	const { busConfigs, selectedBus } = props
	const [dialogOpen, setDialogOpen] = useState(false)
	const busConfig = busConfigs[selectedBus - 1]
	const color = busConfig?.color ?? 0
	const classes = useTopBarStyles({ color })

	const handleOpenDialog = () => {
		setDialogOpen(true)
	}

	const handleCloseDialog = (busIndex: number) => {
		setDialogOpen(false)
		//setSelectedValue(value)
	}
	return (
		<>
			<header className={`${classes.header} ${classes.scribble}`} onClick={handleOpenDialog}>
				<div className={classes.headerTitle}>
					<Typography variant="h6">
						{busConfig?.name ?? "Monitor Mix"}
					</Typography>
				</div>
			</header>
			<SelectBusDialog open={dialogOpen} busConfigs={busConfigs} selectedBus={selectedBus} onClose={handleCloseDialog} />
		</>
	)
}

interface IScribleStripProps {
	color: number,
	label: string
}

const useScribbleStripStyles = makeStyles<DefaultTheme, IScribbleStylesProps, string>({
	scribble: {
		width: '65pt',
		height: '35pt',
		padding: '1pt',
		borderRadius: '2pt',
		textAlign: 'center',
		wordWrap: 'break-word',
		...scribbleColorProperties,
	}
})

const ScribbleStrip = function (props: IScribleStripProps) {
	const classes = useScribbleStripStyles({ color: props.color })
	return <Typography className={classes.scribble}>{props.label}</Typography >
}

const Fader = withStyles({
	thumb: {
		height: 48,
		width: 25,
		backgroundImage: 'url("fader-button.png")',
		marginTop: -24,
		marginLeft: -13,
		borderRadius: 0,
		transform: 'rotate(90deg)'
	},
	mark: {
		height: 30,
		marginTop: -15
	},
	markActive: {
		backgroundColor: 'rgb(132, 134, 136)'
	},
	markLabel: {
		color: 'rgb(132, 134, 136)',
	}
})(Slider)

const marks = [
	{ value: 10, label: '50' },
	{ value: 20, label: '30' },
	{ value: 30, label: '20' },
	{ value: 40, label: '10' },
	{ value: 50, label: '5' },
	{ value: 60, label: '0' },
	{ value: 70, label: '5' },
	{ value: 80, label: '10' },
]

interface IChannelFaderProps {
	level: number,
	onChange: (value: number) => void
}

const ChannelFader = function (props: IChannelFaderProps) {
	const { onChange, level } = props
	const onChangeCallback = useCallback((_, value) => onChange(value), [onChange])
	return (
		<div style={{ marginLeft: 25, marginRight: 25 }}>
			<Fader
				min={0}
				max={80}
				value={level}
				onChange={onChangeCallback}
				marks={marks} />
		</div>
	)
}

interface IChannelProps {
	channelConfig: IChannelConfig,
	onChange: (value: number) => void
}

const Channel = function (props: IChannelProps) {
	const { channelConfig, onChange } = props
	const { color, name, level } = channelConfig
	return (
		<div>
			<div style={{ float: 'left', paddingBottom: '15pt' }}>
				<ScribbleStrip color={color} label={name} />
			</div>
			<div style={{ marginLeft: '75pt', paddingTop: '8pt', paddingBottom: '7pt' }}>
				<ChannelFader level={level} onChange={onChange} />
			</div>
		</div>
	)
}

interface IChannelFadersProps {
	channelConfigs: IChannelConfig[]
	setChannelLevel: (channel: number, level: number) => void
}


const ChannelFaders = function (props: IChannelFadersProps) {
	const { channelConfigs, setChannelLevel } = props
	return (
		<div style={{ margin: '1em' }}>
			{channelConfigs.map((config, index) => (
				<Channel key={index} channelConfig={config} onChange={value => setChannelLevel(index + 1, value)} />
			))}
		</div>
	)
}

const App = function () {
	const [state, dispatch] = useReducer(reducer, initialState);
	useEffect(() => {
		createConnection(dispatch)
	}, [dispatch])
	const setChannelLevel = useCallback((channel: number, level: number) =>
		dispatch({ command: "channellevel", channel, level }),
		[dispatch])
	return (
		<>
			<TopBar busConfigs={state.busConfigs} selectedBus={state.selectedBus} />
			<ChannelFaders channelConfigs={state.channelConfigs} setChannelLevel={setChannelLevel} />
		</>
	)
}

export default App