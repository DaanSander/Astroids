package com.daansander.astroids.entity;

import com.daansander.astroids.Game;
import com.daansander.astroids.graphics.Screen;
import com.daansander.astroids.graphics.Sprite;

public class EntityProjectile extends MovingEntity {

	protected Sprite sprite;

	public EntityProjectile(int x, int y, int movementX, int movementY, Sprite sprite) {
		super(x, y, movementX, movementY, 16);
		this.sprite = sprite;
	}

	@Override
	public boolean hasCollided() {
		for (Entity entity : Game.game.level.getEntities()) {
			if (!entity.destroyed) {
				if (entity instanceof EntityAstroid) {
					for (int y = super.y; y < (super.y + collisionSize); y++) {
						for (int x = super.x; x < (super.x + collisionSize); x++) {
							if (entity.x + (entity.collisionSize / 2) == x && entity.y + (entity.collisionSize / 2) == y) {
								entity.destroy();
								this.destroy();
								Game.game.score++;
								continue;
							}
						}
					}
				}
			}

		}
		return false;
	}

	@Override
	public void tick() {
		super.tick();

		if (x < -10 || x > Game.WIDTH || y < -10 || y > Game.HEIGHT)
			this.destroy();
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(x, y, sprite);
	}

}