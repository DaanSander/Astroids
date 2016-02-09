package com.daansander.astroids.graphics;

public class Screen {

	public int[] pixels;
	private int width, height;

	int current, xtime, ytime;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;

		pixels = new int[width * height];
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void drawCircle(int x, int y, int size, int color) {
		for (int i = 0; i < 360; i++) {
			int xp = (int) Math.round(x + size * Math.cos(i));
			int yp = (int) Math.round(y + size * Math.sin(i));

			if (xp < 0 || xp >= width)
				x = 0;
			if (yp < 0 || yp >= height)
				yp = 0;

			pixels[xp + yp * width] = color;
		}
	}

	public void fillRectangle(int x, int y, int xSize, int ySize, int color) {
		for (int yp = y; yp < (y + ySize); yp++) {
			if (yp < 0 || yp >= height)
				continue;
			for (int xp = x; xp < (x + xSize); xp++) {
				if (xp < 0 || xp >= width)
					continue;
				pixels[xp + yp * width] = color;
			}
		}
	}

	public void drawRectangle(int x, int y, int xx, int yy, int color) {
		for (int yp = y; yp < yy - y + y; yp++) {
			for (int xp = x; xp < xx - x + x; xp++) {
				if (yp == y || yp == yy || xp == x || xp == xx) {
					pixels[xp + yp * width] = color;
				}
			}
		}
	}

	public void render(int xc, int yc, int tileX, int tileY, int size, SpriteSheet sheet) {
		for (int y = 0; y < size; y++) {
			int yp = yc + y;

			if (yp >= height || yp < 0)
				continue;
			for (int x = 0; x < size; x++) {
				int xp = xc + x;

				if (xp >= width || xp < 0)
					continue;

				if (sheet.pixels[((tileX * size) + x) + ((tileY * size) + y) * sheet.SIZE] != -16777216) {
					this.pixels[xp + yp * width] = sheet.pixels[((tileX * size) + x) + ((tileY * size) + y) * sheet.SIZE];
				}
			}
		}
	}

	public void render(int xc, int yc, int tileX, int tileY, int size, int color, SpriteSheet sheet) {
		for (int y = 0; y < size; y++) {
			int yp = yc + y;

			if (yp >= height || yp < 0)
				continue;
			for (int x = 0; x < size; x++) {
				int xp = xc + x;

				if (xp >= width || xp < 0)
					continue;

				if (sheet.pixels[((tileX * size) + x) + ((tileY * size) + y) * sheet.SIZE] != -16777216) {
					this.pixels[xp + yp * width] = color;
				}
			}
		}
	}

	public void renderSprite(int xc, int yc, Sprite sprite) {
		for (int y = 0; y < sprite.SIZE; y++) {
			int yp = y + yc;
			if (yp >= height || yp < 0)
				continue;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xp = x + xc;
				if (xp >= width || xp < 0)
					continue;

				if (sprite.pixels[x + y * sprite.SIZE] != -16777216) {
					pixels[xp + yp * width] = sprite.pixels[x + y * sprite.SIZE];
				}
			}
		}
	}
	// pixels[xtime + ytime * width] = 0xff000f;
}