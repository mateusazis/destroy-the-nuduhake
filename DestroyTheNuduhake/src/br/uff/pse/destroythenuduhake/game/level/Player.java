package br.uff.pse.destroythenuduhake.game.level;

import com.badlogic.gdx.math.Vector2;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;


public class Player extends ControlableEntity {

	private Sword s;
	private GraphicAsset swordAsset;
	private Vector2 swordRelativePos;
	private Vector2 swordPos = new Vector2(0,0);
	
	public Player(float x, float y, GraphicAsset asset, GraphicAsset swordAsset) {
		super(x, y, asset);
		swordRelativePos = new Vector2(getWidth(), getHeight() / 2f);
		this.swordAsset = swordAsset;
	}
	
	public Sword getWeapon(){
		return s;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(s == null){
			s = new Sword(this, swordAsset);
			getParent().addActor(s);
		}
		
		swordPos.set(swordRelativePos);
		localToStageCoordinates(swordPos);
		s.setPosition(swordPos.x, swordPos.y);
	}
	
	@Override
	public void atack(){
		s.swing();
	}
}
