package com.daansander.astroids.powerup;

import com.daansander.astroids.Game;
import com.daansander.astroids.entity.EntityShield;
import com.daansander.astroids.graphics.Sprite;

public class PowerupShield extends Powerup {

	public PowerupShield(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void activate() {
		int x  = Game.game.player.x;
		int y = Game.game.player.y;
		
		Game.game.level.addEntity(new EntityShield(x + 16 /2 , y + 16 /2, 20, 10));
	}

}
