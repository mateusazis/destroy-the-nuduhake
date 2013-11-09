package br.uff.pse.destroythenuduhake.game.level.enemies;

import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.game.level.Enemy;

import com.badlogic.gdx.math.Vector2;

public class ShooterEnemy extends Enemy {

	private GraphicAsset bulletAsset, smokeAsset;
	
	private float elapsed = 0;
	private float PATROL_TIME = 0.5f;

	private static float SHOOT_INTERVAL = 3f;
	private static float AIMING_TIME = 1f;

	public ShooterEnemy(float x, float y, AssetBundle bundle) {
		super(x, y, bundle.<GraphicAsset>getAsset(AssetDatabase.SPRITE_SHOOTER), 200);
		this.bulletAsset = bundle.<GraphicAsset>getAsset(AssetDatabase.SPRITE_BULLET);
		this.smokeAsset = bundle.<GraphicAsset>getAsset(AssetDatabase.SPRITE_SMOKE);
		setMaxMoveVelocity(1f);
	}

	@Override
	public void atack() {
		Vector2 shootPos;
		if(isTurnedLeft()){
			shootPos = new Vector2(-bulletAsset.getWidth() - 20,getHeight() - bulletAsset.getHeight() - 75);
		}
		else{
			shootPos = new Vector2(bulletAsset.getWidth() -20, getHeight() - bulletAsset.getHeight() - 75);
		}
		shootPos = localToStageCoordinates(shootPos);
		
		Bullet bullet = new Bullet(shootPos.x, shootPos.y, bulletAsset, smokeAsset, isTurnedLeft());
		bullet.setupPhysics(this.getBody().getWorld());
		getParent().addActor(bullet);
		
		getManager().addEnemies(bullet);
	}

	@Override
	public void updateIA(float delta) {
		super.updateIA(delta);
		elapsed += delta;
		if(elapsed >= SHOOT_INTERVAL){
			atack();
			elapsed -= SHOOT_INTERVAL;
		}
		if(getTarget().getX() < getX()){
			if(!turnedLeft){
				turnLeft();
				elapsed -= AIMING_TIME;
			}
		}
		else{
			if(turnedLeft){
				turnRight();
				elapsed -= AIMING_TIME;
			}
		}
	}
	
	@Override
	public void patrol(float delta){
		elapsed += delta;
		if(elapsed >= PATROL_TIME){
			if(isTurnedLeft())
				moveRight();
			else
				moveLeft();
			elapsed -= PATROL_TIME;
		}
		else{
			if(isTurnedLeft())
				moveLeft();
			else
				moveRight();
		}
	}
}