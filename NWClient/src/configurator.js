import * as dat from "dat.gui/build/dat.gui";
import $ from "jquery";
import { update } from "./time"

export function configurator(config, onChange) {
	const gui = new dat.GUI();
	const change = onChange.bind(null, config);
	config.sound = config.sounds[0]

	gui.add(config, "gridDim")
		.step(1)
		.min(0)
		.max(10)
		.onChange(change)

	gui.add(config, "showLength")
		.step(1)
		.min(0)
		.max(1000 * 60 * 5)
		.onChange(v => {
			$(".time").attr("max", v);
		})

	gui.add(config, "sound", config.sounds).onChange(change)
	

	$(".time").attr("max", config.showLength);
	change();
	return config;
}