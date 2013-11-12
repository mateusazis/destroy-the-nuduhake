package br.uff.pse.destroythenuduhake.game.level;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CoinManager {
	private int coinCount;
	private FixedObject coin;
	private Player player;
	GraphicAsset coinAsset;
	BitmapFont font;
	SpriteBatch spriteBatch;
	
	public CoinManager(GraphicAsset coinAsset, Player player) {
		this.player = player;
		this.coinAsset = coinAsset;
		this.font = new BitmapFont();
		font.setColor(new Color(0f, 0f, 1f, 1f));
		
		spriteBatch = new SpriteBatch();
		
		coin = new FixedObject(300, 200, coinAsset);
		player.getParent().addActor(coin);
	}
	
	public void update(){
		spriteBatch.begin();
		coinCount = player.getScore();
		font.draw(spriteBatch, coinCount + " (Ainda não soube colocar isso lá em cima u.u)", 400, 400);
		spriteBatch.end();
	}
}
