package com.daansander.astroids.entity;

import com.daansander.astroids.Game;
import com.daansander.astroids.graphics.Screen;

public abstract class Entity {

	public boolean destroyed = false;
	public int x, y;
	protected int collisionSize;

	protected Entity(int x, int y, int collisionSize) {
		this.x = x;
		this.y = y;
		this.collisionSize = collisionSize;
	}

	public abstract void tick();

	public abstract void render(Screen screen);

	public boolean hasCollided() {
		for (Entity entity : Game.game.level.getEntities()) {
			if (entity == this)
				continue;
			if (!entity.destroyed) {
				int xMin = this.x;
				int yMin = this.y;

				int xMax = this.x + collisionSize;
				int yMax = this.y + collisionSize;

				if (entity.x < xMax && entity.x > xMin && entity.y < yMax && entity.y > yMin) {
					if (entity instanceof EntityAstroid && this instanceof EntityAstroid) {
						((MovingEntity) entity).movementX *= -1;
						((MovingEntity) entity).movementY *= -1;
						return false;
					} else if (this instanceof EntityPlayer && entity instanceof EntityAstroid) {
						// if (!((EntityPlayer) entity).invincible) {
						Game.game.level.clear();
						System.err.println("You have been hit by an astroid!");
						Game.game.started = false;
						((EntityPlayer) this).dead = true;
						return false;
						// } else {
						// destroy();
						// return true;
						// }
					}
				}
			}
		}
		return false;
	}

	public synchronized void destroy() {
		if (!destroyed)
			destroyed = true;
	}

}