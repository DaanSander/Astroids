package com.daansander.astroids.level;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.daansander.astroids.Game;
import com.daansander.astroids.entity.Entity;
import com.daansander.astroids.entity.EntityPlayer;
import com.daansander.astroids.entity.EntityPowerup;
import com.daansander.astroids.entity.EntityProjectile;
import com.daansander.astroids.graphics.Screen;
import com.daansander.astroids.graphics.Sprite;
import com.daansander.astroids.graphics.SpriteSheet;
import com.daansander.astroids.powerup.Powerup;
import com.daansander.astroids.powerup.PowerupClear;
import com.daansander.astroids.powerup.PowerupShield;

public class Level {

	private List<Entity> entities = new ArrayList<>();
	private Game game;
	private int currentTicks = 0;

	private Sprite[] projectileFrames;
	private List<Powerup> powerups = new ArrayList<>();

	public Level(Game game) {
		this.game = game;

		projectileFrames = new Sprite[4];

		for (int i = 0; i < projectileFrames.length; i++) {
			projectileFrames[i] = new Sprite(0 + i, 4, 16, SpriteSheet.sprites);
		}
	}

	public void spawnProjectile(int direction, int speed) {
		speed = speed % 10;
		direction = direction % projectileFrames.length;

		EntityProjectile projectile = new EntityProjectile(game.player.x, game.player.y, (int) game.player.getDirection().getX() * speed,
				(int) game.player.getDirection().getY() * speed, projectileFrames[direction]);

		entities.add(projectile);
	}

	public void init() {
		powerups.add(new PowerupClear(Sprite.powerupClear));
		powerups.add(new PowerupShield(Sprite.powerupShield));
	}

	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			entity.tick();
		}

		if (entities.size() > 300) {
			System.err.println("To much entities!");
			clearEntities();
		}

		if (currentTicks > 20 * 7) {
			currentTicks = 0;

			spawnRandomPowerUp();
		}
		currentTicks++;

		executeDestroy();
	}

	private void spawnRandomPowerUp() {
		Random random = new Random();
		spawnPowerup(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), powerups.get(random.nextInt(powerups.size())));
	}

	private void spawnPowerup(int x, int y, Powerup powerup) {
		EntityPowerup entityPowerup = new EntityPowerup(x, y, powerup);
		entities.add(entityPowerup);
	}

	public void renderEntities(Screen screen) {
		for (Entity entity : entities) {
			entity.render(screen);
		}
	}

	public void addEntity(Entity entity) {
		this.entities.add(entity);
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void executeDestroy() {
		for (Iterator<Entity> iterator = entities.iterator(); iterator.hasNext();) {
			Entity entity = iterator.next();
			if (entity.destroyed) {
				iterator.remove();
			}
		}
	}

	public void clear() {
		clearEntities();
		game.score = 0;
		currentTicks = 0;
		game.powerupComponent.clear();
}

	public void clearEntities() {
		for (Entity entity : entities) {
			if (!(entity instanceof EntityPlayer))
				entity.destroy();
		}
	}

}