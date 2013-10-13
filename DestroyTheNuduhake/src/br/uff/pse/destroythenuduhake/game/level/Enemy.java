package br.uff.pse.destroythenuduhake.game.level;

import com.badlogic.gdx.Gdx;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;

public class Enemy extends ControlableEntity {

	private Player target;
	
	public Enemy(float x, float y, GraphicAsset asset) {
		super(x, y, asset);
	}
	
	public void awake(Player p){
		this.target = p;
	}
	
	public void sleep(){
		this.target = null;
	}
	
	public void updateIA(){
		jump();
	}

	public boolean isSleeping(){
		return target == null;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(!isSleeping()){
			updateIA();
		}
	}
}
