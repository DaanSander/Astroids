package com.daansander.astroids.entity;

import com.daansander.astroids.Game;
import com.daansander.astroids.graphics.Screen;
import com.daansander.astroids.graphics.Sprite;

public class EntityAstroid extends MovingEntity {

	private Sprite sprite;

	public EntityAstroid(int x, int y, int xDir, int yDir, Sprite sprite) {
		super(x, y, xDir, yDir, sprite.SIZE);
		this.sprite = sprite;
	}
	
	@Override
	public void tick() {
		super.tick();

		if (x < -10 || x > Game.WIDTH || y < -10 || y > Game.HEIGHT)
			this.destroy();

		movementX = (movementX == 0) ? (Game.game.player.x - x + 1) % 5 : movementX;
		movementY = (movementY == 0) ? (Game.game.player.y - y + 1) % 5 : movementY;
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(x, y, sprite);
	}

}