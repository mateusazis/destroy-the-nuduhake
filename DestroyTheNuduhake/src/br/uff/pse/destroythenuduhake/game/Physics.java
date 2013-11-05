package br.uff.pse.destroythenuduhake.game;

import br.uff.pse.destroythenuduhake.game.control.LevelObject;
import br.uff.pse.destroythenuduhake.game.level.ControlableEntity;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class Physics {
	public static final float WORLD_TO_BOX = 0.01f;
	public static final float BOX_TO_WORLD = 100f;
	
	public static class LevelContactListener implements ContactListener {

		@Override
		public void preSolve(Contact contact, Manifold oldManifold) {	}

		@Override
		public void postSolve(Contact contact, ContactImpulse impulse) {	}

		@Override
		public void endContact(Contact contact) {	}

		@Override
		public void beginContact(Contact contact) {
			Body bodyA = contact.getFixtureA().getBody();
			Body bodyB = contact.getFixtureB().getBody();
			
			LevelObject a = (LevelObject) bodyA.getUserData();
			LevelObject b = (LevelObject) bodyB.getUserData();
			
			if(bodyA.getLinearVelocity().y < 0 || bodyB.getLinearVelocity().y < 0){
			
				if(a instanceof ControlableEntity){
					((ControlableEntity) a).touchGround();
				}
				if(b instanceof ControlableEntity){
					((ControlableEntity) b).touchGround();
				}
			}
			
			if(a != null)
				a.onContactStart(b);
			if(b != null)
				b.onContactStart(a);
		}
	}
}
