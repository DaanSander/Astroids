package com.daansander.astroids;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.daansander.astroids.entity.EntityPlayer;
import com.daansander.astroids.graphics.Font;
import com.daansander.astroids.graphics.Screen;
import com.daansander.astroids.graphics.Sprite;
import com.daansander.astroids.graphics.SpriteSheet;
import com.daansander.astroids.input.InputHandler;
import com.daansander.astroids.level.Astroid;
import com.daansander.astroids.level.Level;
import com.daansander.astroids.powerup.Powerup;
import com.daansander.astroids.powerup.PowerupClear;
import com.daansander.astroids.powerup.PowerupShield;
import com.daansander.astroids.ui.UIManager;
import com.daansander.astroids.ui.UIPanel;
import com.daansander.astroids.ui.components.PowerupComponent;

public class Game extends Canvas implements Runnable {

	/**
	 * Ideeen: Powerups: - Schild - Shoot buff
	 *
	 * TODO: rewrite collison!
	 */

	public static Game game;
	public static final int WIDTH = 300;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 4;
	public static final String NAME = "Astroids";

	private static final long serialVersionUID = 1L;

	public int score = 0;
	public EntityPlayer player;
	public Level level;
	public Screen screen;
	private JFrame frame;
	private Thread thread;
	private InputHandler keyBoard;
	private Astroid astroid;

	public PowerupComponent powerupComponent;
	private UIManager uiManager;
	private UIPanel uiPanel;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public boolean started = false;
	private boolean running = false;

	public Game() {
		Dimension dimension = new Dimension(SCALE * WIDTH, SCALE * HEIGHT);

		setMinimumSize(dimension);
		setPreferredSize(dimension);
		setMaximumSize(dimension);

		frame = new JFrame(NAME);
		screen = new Screen(WIDTH, HEIGHT);
		keyBoard = new InputHandler(this);
		game = this;

		frame.setResizable(false);
		frame.setTitle(NAME);
		frame.add(this);

		frame.pack();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void init() {
		player = new EntityPlayer(WIDTH / 2 - 10, HEIGHT / 2, 0, 0, 8, keyBoard, SpriteSheet.sprites);
		astroid = new Astroid(0, 2, 2, SpriteSheet.sprites, this);
		uiManager = new UIManager();
		uiPanel = new UIPanel(WIDTH - 51, HEIGHT - 17, 51, 17, 0x878787);
		uiManager.addPanel(uiPanel);

		powerupComponent = new PowerupComponent(1, 1, 3);
		uiPanel.addComponent(powerupComponent);

		level = new Level(this);

		level.init();

		level.addEntity(player);
		astroid.spawnAstroid();

	}

	public void addPowerup(Powerup powerup) {
		powerupComponent.addPowerup(powerup);
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double ns = 1000000000.0 / 20.0;

		double delta = 0;

		int frames = 0;
		int ticks = 0;

		init();

		while (running) {
			long currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / ns;
			lastTime = currentTime;

			if (delta > 1) {
				ticks++;
				delta--;

				tick();
			}

			frames++;
			render();

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;

				frame.setTitle(NAME + " fps " + frames + ", tps " + ticks);

				ticks = 0;
				frames = 0;
			}
		}
	}

	int xOffset, yOffset;

	public void tick() {
		keyBoard.tick();
		astroid.tick();
		uiManager.tick();

		if(keyBoard.pressedOnce(KeyEvent.VK_SPACE)) {
			if(!started) {
				started = true;
				level.clear();
			}
		}
		
		if (keyBoard.pressedOnce(KeyEvent.VK_3))
			powerupComponent.select(2);
		if (keyBoard.pressedOnce(KeyEvent.VK_2))
			powerupComponent.select(1);
		if (keyBoard.pressedOnce(KeyEvent.VK_1))
			powerupComponent.select(0);

		if (keyBoard.pressedOnce(KeyEvent.VK_S)) {
			if (powerupComponent.getSelectedPowerup() != null) {
				powerupComponent.getSelectedPowerup().activate();
				powerupComponent.removeSelected();
			}
		}

		level.tick();
	}

	public void render() {
		BufferStrategy bf = getBufferStrategy();

		if (bf == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics graphics = bf.getDrawGraphics();

		screen.clear();
		level.renderEntities(screen);

		if (!started) {
			Font.display(40, 40, "Press space§to start", 0xffffff, screen);
		}

		Font.display(0, 0, "Score " + score, 0x00ff00, screen);

		uiManager.renderUI(screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		graphics.dispose();
		bf.show();
	}

	public static void main(String[] args) {
		new Game().start();
	}

}