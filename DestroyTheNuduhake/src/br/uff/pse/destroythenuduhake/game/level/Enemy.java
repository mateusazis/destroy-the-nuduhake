package br.uff.pse.destroythenuduhake.game.level;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;

public class Enemy extends ControlableEntity {

	private Player target;
	private float minXPlayerDistance = 300f;
	
	public Enemy(float x, float y, GraphicAsset asset) {
		this(x, y, asset, 300);
	}
	
	public Enemy(float x, float y, GraphicAsset asset, float minXPlayerDistance) {
		super(x, y, asset);
		this.minXPlayerDistance = minXPlayerDistance;
	}
	
	public float getMinXPlayerDistance(){
		return minXPlayerDistance;
	}
	
	public void awake(Player p){
		this.target = p;
	}
	
	public void sleep(){
		this.target = null;
	}
	
	public void updateIA(){	}

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
	
	public Player getTarget(){
		return target;
	}
}
