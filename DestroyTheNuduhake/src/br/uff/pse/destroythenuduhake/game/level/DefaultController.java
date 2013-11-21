package br.uff.pse.destroythenuduhake.game.level;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import br.uff.pse.destroythenuduhake.game.Configs;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.game.control.Level;

public class DefaultController {

	private Player player;
	
	enum Keys {
		LEFT, RIGHT, JUMP, FIRE
	}
	
	static Map<Keys, Boolean> keys = new HashMap<Keys, Boolean>();
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.JUMP, false);
		keys.put(Keys.FIRE, false);
	};

	public DefaultController(Player player, AssetBundle b, Level l) {
		this.player = player;
		final float ITEMS_HEIGHT = -240;
		
		InputSlider slider = new InputSlider(-400, ITEMS_HEIGHT, b, this, l.getCamera());
		l.addActor(slider);
		
		JumpButton jmp = new JumpButton(280, ITEMS_HEIGHT, b, this);
		l.addActor(jmp);
		
		AttackButton atk = new AttackButton(160, ITEMS_HEIGHT, b, this);
		l.addActor(atk);
		
		slider.setZIndex(Integer.MAX_VALUE);
		jmp.setZIndex(Integer.MAX_VALUE);
		atk.setZIndex(Integer.MAX_VALUE);
	}

	// ** Key presses and touches **************** //

		public void leftPressed() {
			rightReleased();
			keys.get(keys.put(Keys.LEFT, true));
		}

		public void rightPressed() {
//			Gdx.app.log("", "right pressed");
			leftReleased();
			keys.get(keys.put(Keys.RIGHT, true));
		}

		public void jumpPressed() {
			keys.get(keys.put(Keys.JUMP, true));
		}

		public void firePressed() {
			keys.get(keys.put(Keys.FIRE, true));
		}

		public void leftReleased() {
			keys.get(keys.put(Keys.LEFT, false));
		}

		public void rightReleased() {
			keys.get(keys.put(Keys.RIGHT, false));
		}

		public void jumpReleased() {
			keys.get(keys.put(Keys.JUMP, false));
		}

		public void fireReleased() {
			keys.get(keys.put(Keys.FIRE, false));
		}

		public void update() {
			processInput();
			
			if(!Configs.isAndroid()){
				if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
					rightPressed();
				else
					rightReleased();
				
				if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
					leftPressed();
				else
					leftReleased();
				
				if(Gdx.input.isKeyPressed(Input.Keys.UP))
					jumpPressed();
				else
					jumpReleased();
				
				if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
					firePressed();
				else
					fireReleased();
			}
		}

		/** Change Bob's state and parameters based on input controls **/
		private void processInput() {
			if (keys.get(Keys.LEFT)) 
				player.moveLeft();
			if (keys.get(Keys.RIGHT)) 
				player.moveRight();
			if (keys.get(Keys.JUMP)) 
				player.jump();
			if(keys.get(Keys.FIRE))
				player.atack();
		}
}
