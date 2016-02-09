package com.daansander.astroids.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.daansander.astroids.Game;

public class InputHandler implements KeyListener {

	private boolean[] keys = new boolean[256];
	private int[] polled = new int[256];

	public InputHandler(Game game) {
		game.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public boolean pressedOnce(int key) {
		return polled[key] == 1;
	}

	public boolean isDown(int key) {
		return keys[key];
	}

	public void tick() {
		for (int i = 0; i < keys.length; i++) {
			if (keys[i]) {
				polled[i]++;
			} else {
				polled[i] = 0;
			}
		}
	}

}