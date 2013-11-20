package br.uff.pse.destroythenuduhake.game.level;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.assets.SoundAsset;
import br.uff.pse.destroythenuduhake.game.control.LevelObject;

public abstract class StageItem extends LevelObject{

	
	private static final float SINE_DURATION = 3f, SINE_MAGNITUDE = 20f;
	
	private float elapsed = 0, y0;
	private SoundAsset sound;
	
	
	public StageItem(float x, float y, GraphicAsset graphic, SoundAsset collectSound) {
		super(x, y, graphic);
		this.y0 = y;
		
		sound = collectSound;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		elapsed += delta;
		if(elapsed >= SINE_DURATION)
			elapsed -= SINE_DURATION;
		setY(y0 + SINE_MAGNITUDE * (float)Math.sin(Math.PI * 2 * elapsed / SINE_DURATION));
	}
	
	@Override
	public void onOverlap(LevelObject other){
		if(other instanceof Player){
			sound.play();
			onCollected((Player)other);
			remove();
		}
	}
	
	protected abstract void onCollected(Player p);
}
