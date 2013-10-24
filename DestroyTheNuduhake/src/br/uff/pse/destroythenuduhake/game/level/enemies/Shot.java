package br.uff.pse.destroythenuduhake.game.level.enemies;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.LevelObject;
import br.uff.pse.destroythenuduhake.game.level.Enemy;

public class Shot extends Enemy {
	private static final float LIFETIME = 5f;
	private static final int ATACK_POWER = 1;
	private float elapsedLifetime = 0;
	
	protected boolean dead = false;
	
	protected GraphicAsset smokeAsset;
	
	public Shot(float x, float y, GraphicAsset asset, GraphicAsset smokeAsset) {
		super(x, y, asset, 100000);
		this.smokeAsset = smokeAsset;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(dead){
			die2();
			return;
		}
		elapsedLifetime += delta;
		if(elapsedLifetime >= LIFETIME){
			this.die();
		}
	}
	
	@Override
	public void die(){
		super.die();
		dead = true;
	}
	
	public void die2() {
		Smoke smoke = new Smoke(getX(), getY(), smokeAsset);
		getParent().addActor(smoke);
		remove();
		dispose();
//		removeFromLevel();
//		boolean removed = super.remove();
//		Gdx.app.log("", "removed? " + removed);
	}
	
	@Override
	public void onContactStart(LevelObject other) {
		boolean collisionWithPlayer = other == getTarget();
		if(collisionWithPlayer){
			getTarget().onAtacked(ATACK_POWER);
			this.die();
		}
	}
}
