package com.daansander.astroids.entity;

public abstract class MovingEntity extends Entity {

	public int movementX, movementY;

	public MovingEntity(int x, int y, int movementX, int movementY, int collisionSize) {
		super(x, y, collisionSize);
		this.movementX = movementX;
		this.movementY = movementY;
	}

	@Override
	public void tick() {
		if (!hasCollided()) {
			super.x += movementX;
			super.y += movementY;
		}
	}

}
