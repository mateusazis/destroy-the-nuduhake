package br.uff.pse.destroythenuduhake.game.level;

import static br.uff.pse.destroythenuduhake.game.assets.AssetDatabase.*;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.assets.SoundAsset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;

import com.badlogic.gdx.math.Vector2;


public class Player extends ControlableEntity {

	private static final int MAX_LIFE = 6;
	private int score = 0;
	private Sword s;
	private GraphicAsset swordAsset;
	private Vector2 swordRelativePos, currentSwordPos = new Vector2();
	private Vector2 swordPos = new Vector2(0,0);
	private SoundAsset swordSound, attackedSound, landSound, jumpSound;
	
	public Player(float x, float y, AssetBundle bundle) {
		super(x, y, bundle.<GraphicAsset>getAsset(SPRITE_MARIO));
		setLife(MAX_LIFE);
		setMaxMoveVelocity(3f);
		swordRelativePos = new Vector2(getWidth() -10, getHeight() / 2f - 5);;
		currentSwordPos.set(swordRelativePos); 
		
		this.swordAsset = bundle.getAsset(SPRITE_SWORD);
		this.swordSound = bundle.getAsset(SOUND_SWORD);
		this.landSound = bundle.getAsset(SOUND_LAND);
		this.jumpSound = bundle.getAsset(SOUND_JUMP);
		this.attackedSound = bundle.getAsset(SOUND_IMPACT);
		
		turnedLeft = false;
	}
	
	@Override
	public void touchGround() {
		super.touchGround();
		landSound.play();
	}
	
	public int getScore(){
		return score;
	}
	
	public Sword getWeapon(){
		return s;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(s == null){
			s = new Sword(this, swordAsset, swordSound);
			getParent().addActor(s);
			s.setZIndex(getZIndex() - 1);
		}
		
		swordPos.set(currentSwordPos);
		localToStageCoordinates(swordPos);
		s.setPosition(swordPos.x, swordPos.y);
	}
	
	@Override
	public void turnLeft() {
		super.turnLeft();
		s.setFlipped(true);
		currentSwordPos.set(swordRelativePos.x -getWidth(), swordRelativePos.y);
	}
	
	@Override
	public void turnRight() {
		super.turnRight();
		s.setFlipped(false);
		currentSwordPos.set(swordRelativePos);
	}
	
	@Override
	public void atack(){
		s.swing();
	}

	public void addScore(int i) {
		this.score += i;
	}
	
	@Override
	public void jump() {
		if(!getState().equals(State.JUMPING))
			jumpSound.play();
		super.jump();
		
	}
	
	@Override
	public void onAtacked(int atackPower){
		super.onAtacked(atackPower);
		attackedSound.play();
	}
	
	public void addLife(int lifeToAdd){
		int life = getLife();
		life += lifeToAdd;
		life = Math.min(MAX_LIFE, life);
		life = Math.max(0, life);
		setLife(life);
	}
}
