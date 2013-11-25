package br.uff.pse.destroythenuduhake.game.mainmenu;

import br.uff.pse.destroythenuduhake.game.level.Blinker;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;

public class Text extends Actor implements EventListener{

	private String content;
	private BitmapFont font;
	private TextBounds bounds;
	private TextListener listener;
	private int id;
	
	//blinking info
	private static final float BLINK_DURATION = 2f;
	private static final int BLINK_TIMES = 10;
	
	private Blinker b;
	public boolean touched = false;
	
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
		
		addListener(this);
		
		b = new Blinker(BLINK_DURATION, BLINK_TIMES);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		bounds = font.draw(batch, content, getX(), getY() + bounds.height);
	}
	
	@Override
	public void act(float delta) {
		if(touched){
			if(b.isBlinking()){
				b.update(delta);
				setVisible(b.isVisible());
			} else { //foi clicado e terminou de piscar
				listener.onTouched(id);
				setVisible(true);
			}
		}
	}

	@Override
	public boolean handle(Event event) {
		InputEvent e = (InputEvent)event;
		Type t = e.getType();
		if(t.equals(Type.touchDown)){
			touched = true;
			b.start();
		}
		return false;
	}
}
