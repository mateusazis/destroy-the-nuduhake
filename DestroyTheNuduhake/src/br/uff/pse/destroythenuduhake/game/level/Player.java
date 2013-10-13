package br.uff.pse.destroythenuduhake.game.level;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;


public class Player extends ControlableEntity {

	public Player(float x, float y, GraphicAsset asset) {
		super(x, y, asset);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(getVelocity().x > 0){
			if(isFacingLeft()){
				getBody().setLinearVelocity(Player.SPEED, 0);
			}
			else{
				getBody().setLinearVelocity(-Player.SPEED, 0);
			}
		}
	}

}
