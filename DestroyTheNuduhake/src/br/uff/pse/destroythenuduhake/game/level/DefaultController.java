package br.uff.pse.destroythenuduhake.game.level;

import java.util.HashMap;
import java.util.Map;

import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.game.control.Level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class DefaultController {

	private Player player;
	private InputSlider slider;
	
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
		slider = new InputSlider(100, 100, b, this);
		l.addActor(slider);
	}

	// ** Key presses and touches **************** //

		public void leftPressed() {
			keys.get(keys.put(Keys.LEFT, true));
		}

		public void rightPressed() {
			keys.get(keys.put(Keys.RIGHT, true));
		}

		public void jumpPressed() {
			keys.get(keys.put(Keys.JUMP, true));
		}

		public void firePressed() {
			keys.get(keys.put(Keys.FIRE, false));
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
		}

		/** Change Bob's state and parameters based on input controls **/
		private void processInput() {
			if (keys.get(Keys.LEFT)) 
				player.moveLeft();
			if (keys.get(Keys.RIGHT)) 
				player.moveRight();
			if (keys.get(Keys.JUMP)) 
				player.jump();
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isKeyPressed(Input.Keys.MENU))
				player.atack();
		}
		
	public boolean touchDown(int x, int y, int pointer, int button) {
//		float xPlayer = player.getStage().stageToScreenCoordinates(player.localToStageCoordinates(new Vector2(player.getX(), player.getY()))).x;
//		if (x < xPlayer) 
//			leftPressed();
//		if(x > xPlayer)
//			rightPressed();
//		if(y < 200)
//			jumpPressed();
		return true;
	}

	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		leftReleased();
		rightReleased();
		jumpReleased();
		return false;
	}

}
