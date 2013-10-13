package br.uff.pse.destroythenuduhake.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;

public class Text extends Actor{

	private String content;
	private BitmapFont font;
	private TextBounds bounds;
	private TextListener listener;
	private int id;
	
	public Text(BitmapFont font, String content, float x, float y){
		this(0, font, content, x, y, null);
	}
	
	public Text(int id, BitmapFont font, String content, float x, float y){
		this(id, font, content, x, y, null);
	}
	
	public Text(int id, BitmapFont font, String content, float centerX, float centerY, TextListener listener){
		super();
		this.id = id;
		this.font = font;
		this.content = content;
		this.bounds = font.getBounds(content);
		this.listener = listener;
		
		setBounds(centerX - bounds.width / 2f, centerY - bounds.height / 2f, bounds.width, bounds.height);
		
//		setPosition(x, y);
//		setSize(bounds.width, bounds.height);
		
		addListener(new EventListener() {
			
			@Override
			public boolean handle(Event event) {
				Gdx.app.log("", "event");
				return false;
			}
		});
	}
	
	
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		bounds = font.draw(batch, content, getX(), getY());
	}
	
	@Override
	public void act(float delta) {
		
		if(bounds != null && Gdx.input.justTouched()){
			int x = Gdx.input.getX(), y = Gdx.input.getY();
			
			Actor hitActor = hit(x - getX(), Gdx.graphics.getHeight() - y - getY() + getHeight(), false);
			if(hitActor == this && listener != null)
				listener.onTouched(id);
//			float leftX = x - getX(), upY = Gdx.graphics.getHeight() - y - getY() + bounds.height;
//			if(leftX >= 0 && leftX < bounds.width && upY >= 0 && upY < bounds.height){
//				if(listener != null)
//					listener.onTouched(id);
//			}
		}
		
//		if(Gdx.input.isTouched() && (hit(Gdx.input.getX(), Gdx.input.getY(), true) == this) && listener != null)
//			listener.OnTouched(this.id);
		
//		if(Gdx.input.isTouched())
//			Gdx.app.log("", "is touched");
		
//		Gdx.app.log("", "X: " + getX() + "Y " + getY() + "width " + getWidth() + "height " + getHeight());
	}
	
}
