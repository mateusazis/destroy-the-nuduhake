package br.uff.pse.destroythenuduhake.game.mainmenu;

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
	
	//blinking info
	private enum State{
		IDLE, BLINKING;
	}
	private State state = State.IDLE;
	private float blinkElapsed = 0;
	private static final float BLINK_DURATION = 2f, ON_DURATION = 0.1f;
	
	
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
	}
	
	
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		bounds = font.draw(batch, content, getX(), getY());
	}
	
	@Override
	public void act(float delta) {
		
		switch(state){
		case IDLE:
			if(bounds != null && Gdx.input.justTouched()){
				int x = Gdx.input.getX(), y = Gdx.input.getY();
				
				Actor hitActor = hit(x - getX(), Gdx.graphics.getHeight() - y - getY() + getHeight(), false);
				if(hitActor == this && listener != null){
					state = State.BLINKING;
					blinkElapsed = 0;
				}
			}
			break;
			
		case BLINKING:
			blinkElapsed += delta;
			boolean visible = Math.floor(blinkElapsed / ON_DURATION) % 2 == 1;
			setVisible(visible);
			
			if(blinkElapsed >= BLINK_DURATION){
				state = State.IDLE;
				listener.onTouched(id);
				setVisible(true);
			}
			break;
		}
	}
	
}
