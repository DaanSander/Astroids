package com.daansander.astroids.entity;

import java.awt.Point;
import java.awt.event.KeyEvent;

import com.daansander.astroids.Game;
import com.daansander.astroids.graphics.Font;
import com.daansander.astroids.graphics.Screen;
import com.daansander.astroids.graphics.Sprite;
import com.daansander.astroids.graphics.SpriteSheet;
import com.daansander.astroids.input.InputHandler;
import com.daansander.astroids.powerup.PowerupClear;
import com.daansander.astroids.powerup.PowerupShield;

public class EntityPlayer extends Entity {

	public boolean invincible = false;
	public int currentFrame = 0;
	public int maxFrames;
	protected boolean dead = false;
	private Sprite[] frames;
	private InputHandler input;
	private int timer = 0;

	public EntityPlayer(int x, int y, int beginX, int beginY, int maxFrames, InputHandler input, SpriteSheet sheet) {
		super(x, y, 16);
		this.maxFrames = maxFrames - 1;
		this.input = input;

		frames = new Sprite[maxFrames];

		for (int i = 0; i < frames.length; i++) {
			frames[i] = new Sprite(beginX + i, beginY, 16, sheet);
		}
	}

	public void addFrame(int i) {
		if (i > 0)
			this.currentFrame = (i + currentFrame) % (maxFrames + 1);
		else if (i < 0 && currentFrame == 0)
			this.currentFrame = maxFrames;
		else
			this.currentFrame--;
	}

	public Point getDirection() {
		switch (currentFrame) {
		case 0:
			return new Point(0, -1);
		case 1:
			return new Point(1, -1);
		case 2:
			return new Point(1, 0);
		case 3:
			return new Point(1, 1);
		case 4:
			return new Point(0, 1);
		case 5:
			return new Point(-1, 1);
		case 6:
			return new Point(-1, 0);
		case 7:
			return new Point(-1, -1);
		}
		return null;
	}

	@Override
	public void tick() {
		hasCollided();
		if (dead) {
			if (timer > 20) {
				timer -= 20;
				if (dead)
					dead = false;
			}
			timer++;
		}

		if (input.pressedOnce(KeyEvent.VK_RIGHT))
			addFrame(1);
		if (input.pressedOnce(KeyEvent.VK_LEFT))
			addFrame(-1);
		if (input.pressedOnce(KeyEvent.VK_SPACE)) {
			Game.game.level.spawnProjectile(currentFrame, 9);
		}
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(x, y, frames[currentFrame]);

		if (dead)
			Font.display(50, 180, "You died", 0xff000f, Game.game.screen);
		

		int xMin = this.x;
		int yMin = this.y;
		
		int xMax = this.x + collisionSize;
		int yMax = this.y + collisionSize; 
		
		
//		screen.fillRectangle(xMin, yMin, 2,  2, 0xffffff);
//		screen.fillRectangle(xMax, yMax, 2,  2, 0xffffff);
		
		screen.drawRectangle(xMin, yMin, xMax, yMax, 0xff00ff);
	}

}
