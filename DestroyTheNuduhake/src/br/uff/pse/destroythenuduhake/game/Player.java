package br.uff.pse.destroythenuduhake.game;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;


public class Player extends ControlableEntity {

	public Player(float x, float y, GraphicAsset asset) {
		super(x, y, asset);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void act(float delta) {
		if(getVelocity().x > 0){
			if(isFacingLeft()){
				//setPosition(getX() + getVelocity().x, getY());
				getBody().setLinearVelocity(50, 0);
			}
			else{
				setPosition(getX() - getVelocity().x, getY());
				getBody().setLinearVelocity(-50, 0);
			}
		}
		
	}

}
