package br.uff.pse.destroythenuduhake.game.level.enemies;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.game.level.Enemy;
import br.uff.pse.destroythenuduhake.game.level.Player;

public class BallShooter extends Enemy {

	private static float SHOOT_INTERVAL = 2f;
	private float elapsed = 0;
	private GraphicAsset ballAsset, smokeAsset;
	private GraphicAsset asset3, asset2, asset1;
	private float bodyX;
	
	public BallShooter(float x, float y, AssetBundle bundle) {
		super(x, y, bundle.<GraphicAsset>getAsset(AssetDatabase.SPRITE_BALL_SHOOTER_3), 800);
		
		asset3 = bundle.<GraphicAsset>getAsset(AssetDatabase.SPRITE_BALL_SHOOTER_3);
		asset2 = bundle.<GraphicAsset>getAsset(AssetDatabase.SPRITE_BALL_SHOOTER_2);
		asset1 = bundle.<GraphicAsset>getAsset(AssetDatabase.SPRITE_BALL_SHOOTER_1);
		this.ballAsset = bundle.getAsset(AssetDatabase.SPRITE_BALL);
		this.smokeAsset = bundle.getAsset(AssetDatabase.SPRITE_SMOKE);
	}
	
	@Override
	public void onAtacked(int atackPower) {
		Stage s = super.getStage();
		super.onAtacked(atackPower);
		s.addActor(new Smoke(getX(), getY(), smokeAsset));
		int life = getLife();
		
		if(life == 2){
			setGraphic(asset2);
			rebuildShape();
		}
		if(life == 1){
			setGraphic(asset1);
			rebuildShape();
		}
	}
	
	@Override
	public Rectangle getRect() {
		Rectangle r = super.getRect();
		r.setSize(asset3.getWidth(), asset3.getHeight());
		return r;
	}
	
	@Override
	public void setupPhysics(World world) {
		super.setupPhysics(world);
		bodyX = getBody().getPosition().x;
		getFixture().setFriction(0);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		Body b = getBody();
		
		
		b.setLinearVelocity(0, b.getLinearVelocity().y);
		b.setTransform(bodyX, b.getPosition().y, b.getAngle());
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
		float jumpVelocity = getLife() * 2f;
		Ball b = new Ball(shootPos.x, shootPos.y, jumpVelocity, ballAsset, smokeAsset);
		b.setupPhysics(getBody().getWorld());
		getParent().addActor(b);
		getManager().addEnemies(b);
	}
}
