package com.daansander.astroids.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.daansander.astroids.Game;
import com.daansander.astroids.entity.EntityAstroid;
import com.daansander.astroids.graphics.Sprite;
import com.daansander.astroids.graphics.SpriteSheet;

public class Astroid {

	public Sprite[] sprites;
	private List<EntityAstroid> astroids = new ArrayList<>();
	private Game game;
	private Random random;
	private int ticks;
	
	public Astroid(int beginX, int beginY, int max, SpriteSheet sheet, Game game) {
		this.game = game;
		this.random = new Random();

		sprites = new Sprite[max];

		for (int i = 0; i < max; i++) {
			sprites[i] = new Sprite(beginX + i, beginY, 16, sheet);
		}
	}

	public void tick() {
		if(ticks / 2 == 10) {
			ticks = 0;
			spawnAstroid();
		}
		
		ticks++;
	}
	
	public void spawnAstroid() {
		int xc = random.nextInt(Game.WIDTH);
		int yc = random.nextInt(Game.HEIGHT);

		int xDir = (game.player.x - xc) % 5;
		int yDir = (game.player.y - yc) % 5;

		EntityAstroid mob = new EntityAstroid(xc, yc, xDir, yDir, randomSprite());
		astroids.add(mob);
		game.level.addEntity(mob);
	}

	public Sprite randomSprite() {
		return sprites[random.nextInt(sprites.length)];
	}
}