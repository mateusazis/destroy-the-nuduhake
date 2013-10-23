package br.uff.pse.destroythenuduhake.game.level.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.game.level.Enemy;
import br.uff.pse.destroythenuduhake.game.level.Player;

public class BallShooter extends Enemy {

	private static float SHOOT_INTERVAL = 2f;
	private float elapsed = 0;
	private GraphicAsset ballAsset, smokeAsset;
	
	public BallShooter(float x, float y, AssetBundle bundle) {
		super(x, y, bundle.<GraphicAsset>getAsset(AssetDatabase.SPRITE_BALL_SHOOTER), 800);
		this.ballAsset = bundle.getAsset(AssetDatabase.SPRITE_BALL);
		this.smokeAsset = bundle.getAsset(AssetDatabase.SPRITE_SMOKE);
	}
	
	@Override
	public void setupPhysics(World world) {
		super.setupPhysics(world);
		
		getBody().setType(BodyType.StaticBody);
	}
	
	@Override
	public void awake(Player p) {
		super.awake(p);
	}
	
	@Override
	public void updateIA(float delta) {
		super.updateIA(delta);
		elapsed += delta;
		if(elapsed >= SHOOT_INTERVAL){
			shoot();
			elapsed -= SHOOT_INTERVAL;
		}
	}
	
	private void shoot(){
		Vector2 shootPos = new Vector2(-ballAsset.getWidth() - 30,getHeight() - ballAsset.getHeight());
		shootPos = localToStageCoordinates(shootPos);
		Ball b = new Ball(shootPos.x, shootPos.y, ballAsset, smokeAsset);
		b.setupPhysics(getBody().getWorld());
		getParent().addActor(b);
		getManager().addEnemies(b);
	}
}
