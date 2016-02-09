package com.daansander.astroids.powerup;

import com.daansander.astroids.graphics.Sprite;

public abstract class Powerup {

	public Sprite sprite;

	public Powerup(Sprite sprite) {
		this.sprite = sprite;
	}

	public abstract void activate();

}