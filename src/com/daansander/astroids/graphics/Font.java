package com.daansander.astroids.graphics;

public class Font {

	private static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";

	public static void display(int x, int y, String message, int color, Screen screen) {
		message = message.toUpperCase();

		int tileY = 13;
		int tileX = 0;
		int size = (int) Math.sqrt(SpriteSheet.sprites.SIZE);

		for (int i = 0; i < message.length(); i++) {

			if (message.charAt(i) == '§') {
				y += 16;
				x -= 16 * message.indexOf(message.charAt(i));
				continue;
			}

			int index = chars.indexOf(message.charAt(i));

			tileX = index;

			if (tileX > (size - 1)) {
			}

			while (tileX > (size - 1)) {
				tileX -= 16;
				tileY = (tileY + 1) % size;
			}

//			screen.render(x, y, tileX, tileY, 16, SpriteSheet.sprites);
			screen.render(x, y, tileX, tileY, 16, color, SpriteSheet.sprites);

			x += 16;
			tileY = 13;
			tileX = 0;
		}
	}
}