package br.uff.pse.destroythenuduhake.game.level.enemies;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class Bullet extends Shot {
	private static final float SPEED = 10f;
	private boolean isShooterTurnedLeft;

	public Bullet(float x, float y, GraphicAsset asset, GraphicAsset smokeAsset, boolean isShooterTurnedLeft) {
		super(x, y, asset, smokeAsset);
	    this.isShooterTurnedLeft = isShooterTurnedLeft;
	}

	@Override
	public void setupPhysics(World world) {
		super.setupPhysics(world);
		getBody().setType(BodyType.KinematicBody);
		getBody().setLinearVelocity(SPEED * (isShooterTurnedLeft ? -1 : 1), 0);
	}
}
