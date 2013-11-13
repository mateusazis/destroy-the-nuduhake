package br.uff.pse.destroythenuduhake.game.level;

import android.graphics.Bitmap;
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
		font.setColor(new Color(0f, 0.8f, 0f, 1f));
		font.setScale(2f);
		
		spriteBatch = new SpriteBatch();
		
		coin = new FixedObject(300, 200, coinAsset);
		player.getParent().addActor(coin);
	}
	
	public void update(){
		spriteBatch.begin();
		coinCount = player.getScore();
		font.draw(spriteBatch, Integer.toString(coinCount), 740, 475);
		spriteBatch.end();
	}
}
