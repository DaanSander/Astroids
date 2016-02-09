package com.daansander.astroids.ui.components;

import java.util.ArrayList;
import java.util.List;

import com.daansander.astroids.graphics.Screen;
import com.daansander.astroids.powerup.Powerup;
import com.daansander.astroids.ui.UIComponent;

public class PowerupComponent extends UIComponent {

	private int selectedSlot = 0;
	private int max;
	private List<Powerup> activePowerups = new ArrayList<>();

	public PowerupComponent(int xOffset, int yOffset, int max) {
		super(xOffset, yOffset);
		this.max = max;
	}

	public void addPowerup(Powerup powerup) {
		if (activePowerups.size() < max) {
			activePowerups.add(powerup);
		}
	}

	@Override
	public void tick() {

	}

	public void clear() {
		activePowerups.clear();
	}

	public void select(int slot) {
		selectedSlot = slot;
	}

	public Powerup getSelectedPowerup() {
		if (selectedSlot < activePowerups.size()) {
			return activePowerups.get(selectedSlot);
		}

		return null;
	}

	public void removeSelected() {
		if (activePowerups.get(selectedSlot) != null && selectedSlot < activePowerups.size()) {
			activePowerups.remove(selectedSlot);
		}
	}

	@Override
	public void render(Screen screen) {
		int offset = 0;
		if (activePowerups.size() > 0) {
			screen.fillRectangle(x + xOffset + selectedSlot * 16, y + yOffset, 17, 17, 0xff000f);
		}
		for (Powerup powerup : activePowerups) {
			screen.renderSprite(x + xOffset + offset, y + yOffset, powerup.sprite);
			offset += 16;
		}

	}

}
