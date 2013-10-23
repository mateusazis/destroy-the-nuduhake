package br.uff.pse.destroythenuduhake.game.level;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.Level;
import br.uff.pse.destroythenuduhake.game.control.LevelObject;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class ShooterEnemy extends Enemy {

	GraphicAsset bulletAsset;
	World world;
	boolean podeAtirar;
	Level level;

	final int RECHARGE_TIME = 3;

	public ShooterEnemy(float x, float y, GraphicAsset asset,
			GraphicAsset bulletAsset, Level level) {
		super(x, y, asset);
		this.bulletAsset = bulletAsset;
		this.level = level;
		this.podeAtirar = true;
	}

	@Override
	public void atack() {
		Bullet b1 = new Bullet(900, 120, bulletAsset);
		level.addActor(b1);
		b1.setupPhysics(this.getBody().getWorld());
		b1.getBody().setBullet(true);
		b1.getBody().setType(BodyType.KinematicBody);
		b1.getBody().setLinearVelocity(-10, 0);
		// Bullet b2 = new Bullet(getX(), getY(), bulletAsset);
		// Bullet b3 = new Bullet(getX(), getY(), bulletAsset);
		//
	}

	@Override
	public void updateIA(float delta) {
		if (podeAtirar){
			atack();
			podeAtirar = false;
		}
	}

	class Bullet extends LevelObject {

		public Bullet(float x, float y, GraphicAsset asset) {
			super(x, y, asset);
		}

	}
}
