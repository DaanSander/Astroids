package com.daansander.astroids.powerup;

import com.daansander.astroids.Game;
import com.daansander.astroids.graphics.Sprite;

public class PowerupClear extends Powerup {

	public PowerupClear(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void activate() {
		Game.game.level.clearEntities();
	}
}