package com.daansander.astroids.graphics;

public class Sprite {

	public final int SIZE;
	public int[] pixels;
	private int x, y;
	private SpriteSheet spriteSheet;

	public static Sprite astroid = new Sprite(0, 3, 16, SpriteSheet.sprites);
	public static Sprite powerupClear = new Sprite(0, 6, 16, SpriteSheet.sprites);
	public static Sprite powerupShield = new Sprite(1, 6, 16, SpriteSheet.sprites);
	
	public Sprite(int x, int y, int size, SpriteSheet spriteSheet) {
		this.x = x;
		this.y = y;
		this.SIZE = size;
		this.spriteSheet = spriteSheet;

		pixels = new int[SIZE * SIZE];

		load();
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = spriteSheet.pixels[((this.x * SIZE) + x) + ((this.y * SIZE) + y) * spriteSheet.SIZE];
			}
		}
	}

}