package com.daansander.astroids.entity;

import com.daansander.astroids.Game;
import com.daansander.astroids.graphics.Screen;
import com.daansander.astroids.powerup.Powerup;

public class EntityPowerup extends Entity {

	private int maxLifeTime = 20 * 5;
	private int lifeTime;
	private Powerup powerup;

	public EntityPowerup(int x, int y, Powerup powerup) {
		super(x, y, 16);
		this.powerup = powerup;
	}

	@Override
	public boolean hasCollided() {
		for (Entity entity : Game.game.level.getEntities()) {
			if (!entity.destroyed) {
				if (entity instanceof EntityProjectile) {
					for (int y = entity.y; y < (entity.y + entity.collisionSize); y++) {
						for (int x = entity.x; x < (entity.x + entity.collisionSize); x++) {
							if (super.x + (super.collisionSize / 2) == x && super.y + (super.collisionSize / 2) == y) {
								entity.destroy();
								this.destroy();
								Game.game.addPowerup(powerup);

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
		hasCollided();

		if (lifeTime > maxLifeTime) {
			destroy();
		}

		lifeTime++;

	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(x, y, powerup.sprite);
	}

}
