package br.uff.pse.destroythenuduhake.game.level;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.assets.SoundAsset;
import br.uff.pse.destroythenuduhake.game.control.LevelObject;

public abstract class StageItem extends LevelObject{
	
	private float y0;
	private SoundAsset sound;
	private ItemManager manager;
	private OrthographicCamera c;
	
	public StageItem(float x, float y, GraphicAsset graphic, SoundAsset collectSound, OrthographicCamera c) {
		super(x, y, graphic);
		setOverlapable(true);
		this.y0 = y;
		this.c = c;
		
		sound = collectSound;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if(Math.abs(getX() - c.position.x) < 800 && Math.abs(getY() - c.position.y) < 480)
			super.draw(batch, parentAlpha);
	}
	
	public final void setManager(ItemManager manager){
		this.manager = manager;
	}
	
	public final void setYOffset(float offset){
		setY(y0 + offset);
	}
	
	@Override
	public void onOverlap(LevelObject other){
		if(other instanceof Player){
			sound.play();
			onCollected((Player)other);
			manager.removeItem(this);
			manager = null; sound = null;
			remove();
		}
	}
	
	protected abstract void onCollected(Player p);
}
