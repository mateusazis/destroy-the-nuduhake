package br.uff.pse.destroythenuduhake.game.level.enemies;

import com.badlogic.gdx.scenes.scene2d.Group;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.LevelObject;
import br.uff.pse.destroythenuduhake.game.level.Enemy;

public class Shot extends Enemy {
	private static final float LIFETIME = 5f;
	private static final int ATACK_POWER = 1;
	private float elapsedLifetime = 0;
	
//	protected boolean dead = false;
	
	protected GraphicAsset smokeAsset;
	
	public Shot(float x, float y, GraphicAsset asset, GraphicAsset smokeAsset) {
		super(x, y, asset, 100000);
		this.smokeAsset = smokeAsset;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		elapsedLifetime += delta;
		if(elapsedLifetime >= LIFETIME){
			this.die();
		}
	}
	
	@Override
	public void die(){
		Group parent = getParent();
		//ao chamar o die, é removido do stage!
		super.die();
		Smoke smoke = new Smoke(getX(), getY(), smokeAsset);
		parent.addActor(smoke);
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
