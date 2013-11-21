package br.uff.pse.destroythenuduhake.game.level;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class ItemManager extends Actor{

	
	private static final float SINE_DURATION = 3f, SINE_MAGNITUDE = 20f;
	
	private float elapsed = 0;
	private List<StageItem> items;
	
	
	public ItemManager() {
		super();
		items = new LinkedList<StageItem>();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		elapsed += delta;
		if(elapsed >= SINE_DURATION)
			elapsed -= SINE_DURATION;
		float offset = SINE_MAGNITUDE * (float)Math.sin(Math.PI * 2 * elapsed / SINE_DURATION);
		for(StageItem item : items)
			item.setYOffset(offset);
	}
	
	public StageItem add(StageItem item){
		items.add(item);
		item.setManager(this);
		return item;
	}
	
	public void removeItem(StageItem item){
		items.remove(item);
	}
}
