package com.daansander.astroids.entity;

import com.daansander.astroids.Game;
import com.daansander.astroids.graphics.Screen;

public class EntityShield extends Entity {

	private int size = 0;
	private int maxLifeTime = 0;
	private int currentTicks = 0;

	public EntityShield(int x, int y, int size, int maxLifeTime) {
		super(x, y, size);
		this.size = size;
		this.maxLifeTime = maxLifeTime * 20;
		Game.game.player.invincible = true;
	}

	@Override
	public boolean hasCollided() {
		for (Entity entity : Game.game.level.getEntities()) {
			if (!entity.destroyed) {
				if (entity instanceof EntityAstroid) {
					int xMin = super.x - super.collisionSize;
					int yMin = super.y - super.collisionSize;

					int xMax = super.x + super.collisionSize;
					int yMax = super.y + super.collisionSize;

					if (entity.x < xMax && entity.x > xMin && entity.y < yMax && entity.y > yMin) {
						entity.destroy();
						return false;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void tick() {
		hasCollided();
		if (currentTicks > maxLifeTime) {
			destroy();
			Game.game.player.invincible = false;
		}

		currentTicks++;

	}

	@Override
	public void render(Screen screen) {
		screen.drawCircle(x, y, size, 0xffffff);
	}

}
