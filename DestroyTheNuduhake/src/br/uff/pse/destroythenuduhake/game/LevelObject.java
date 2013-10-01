package br.uff.pse.destroythenuduhake.game;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LevelObject extends Actor{

	private GraphicAsset graphic;
	protected BodyDef bodyDef = new BodyDef();
	protected Body body;
	
	public LevelObject(float x, float y, GraphicAsset asset){
		super();
		setPosition(x, y);
		getBodyDef().position.set(x, y);
		this.setGraphic(asset);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		getGraphic().render(batch, getX(), getY());
	}

	public BodyDef getBodyDef() {
		return bodyDef;
	}

	public void setBodyDef(BodyDef bodyDef) {
		this.bodyDef = bodyDef;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public GraphicAsset getGraphic() {
		return graphic;
	}

	public void setGraphic(GraphicAsset graphic) {
		this.graphic = graphic;
	}
	
//	@Override
//	public void act(float delta) {
//		super.act(delta);
//		
//		if(Gdx.input.isTouched()){
//			float dX = Gdx.input.getX() - getX();
//			float dY = Gdx.graphics.getHeight() - Gdx.input.getY() - getY();
//			
//			float mag = (float)Math.sqrt(dX * dX + dY * dY);
//			float SPEED = 100;
//			
//			super.translate(SPEED * dX * delta / mag , SPEED * dY * delta / mag);
//		}
//	}
}
