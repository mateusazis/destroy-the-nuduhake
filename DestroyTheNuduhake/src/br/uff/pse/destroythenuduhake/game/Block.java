package br.uff.pse.destroythenuduhake.game;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Block extends LevelObject {

	public Block(float x, float y, GraphicAsset asset) {
		super(x, y, asset);
		// TODO Auto-generated constructor stub
	}
	
	static final float SIZE = 1f;

	Vector2 	position = new Vector2();
	Rectangle 	bounds = new Rectangle();

}
