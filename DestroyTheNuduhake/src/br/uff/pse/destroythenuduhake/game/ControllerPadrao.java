package br.uff.pse.destroythenuduhake.game;

import java.util.HashMap;
import java.util.Map;

import br.uff.pse.destroythenuduhake.game.ControlableEntity.State;

import com.badlogic.gdx.InputProcessor;

public class ControllerPadrao implements InputProcessor {

Player player;
	
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

	public ControllerPadrao(Player player) {
		this.player = player;
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
//			player.update(delta);
		}

		/** Change Bob's state and parameters based on input controls **/
		private void processInput() {
			if (keys.get(Keys.LEFT)) {
				player.setFacingLeft(true);
				player.setState(State.WALKING);
				player.getVelocity().x = Player.SPEED;
			}
			if (keys.get(Keys.RIGHT)) {
				// left is pressed
				player.setFacingLeft(false);
				player.setState(State.WALKING);
				player.getVelocity().x = Player.SPEED;
			}
			
			if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) ||
					(!keys.get(Keys.LEFT) && !(keys.get(Keys.RIGHT)))) {
				player.setState(State.IDLE);
				player.getVelocity().x = 0;
			}
			if (keys.get(Keys.JUMP)) {
				player.setState(State.JUMPING);
				//player.getBody().applyLinearImpulse(0, 40, player.getX(), player.getY());
				player.getBody().applyLinearImpulse(0f, 20f, player.getX(), player.getY());
			}
			if (!keys.get(Keys.JUMP)){
				//player.getBody().setLinearVelocity(player.getVelocity().x, 0);
			}
		}

	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (x < player.getX()) {
			rightPressed();
		}
		if(x > player.getX()){
			leftPressed();
		}
		if(y > player.getY()){
			jumpPressed();
		}
		return true;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		leftReleased();
		rightReleased();
		return false;
	}

}