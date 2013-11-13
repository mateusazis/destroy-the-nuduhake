package br.uff.pse.destroythenuduhake.game.level;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.assets.SoundAsset;

import com.badlogic.gdx.math.Vector2;


public class Player extends ControlableEntity {

	private int score = 0;
	private Sword s;
	private GraphicAsset swordAsset;
	private Vector2 swordRelativePos, currentSwordPos = new Vector2();
	private Vector2 swordPos = new Vector2(0,0);
	private SoundAsset swordSound;
	
	public Player(float x, float y, GraphicAsset asset, GraphicAsset swordAsset, SoundAsset swordSound) {
		super(x, y, asset);
		setLife(6);
		setMaxMoveVelocity(3f);
		swordRelativePos = new Vector2(getWidth() -10, getHeight() / 2f - 5);;
		currentSwordPos.set(swordRelativePos); 
		
		this.swordAsset = swordAsset;
		this.swordSound = swordSound;
		turnedLeft = false;
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
//		swordRelativePos.x = -s.getWidth();
		float originX = getOriginX();
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
	
}
