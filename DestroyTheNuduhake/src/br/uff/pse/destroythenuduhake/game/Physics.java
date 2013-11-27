package br.uff.pse.destroythenuduhake.game;

import java.util.LinkedList;
import java.util.Queue;

import br.uff.pse.destroythenuduhake.game.control.LevelObject;
import br.uff.pse.destroythenuduhake.game.level.ControlableEntity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Physics {
	public static final float WORLD_TO_BOX = 0.01f;
	public static final float BOX_TO_WORLD = 100f;

	public static class LevelContactListener implements ContactListener {

		private Queue<BodyTuple> contacts = new LinkedList<BodyTuple>();
		
		@Override
		public void preSolve(Contact contact, Manifold oldManifold) {
		}

		@Override
		public void postSolve(Contact contact, ContactImpulse impulse) {
		}

		@Override
		public void endContact(Contact contact) {
		}
		
		public void processContacts(){
			while(contacts.size() > 0){
				BodyTuple c = contacts.poll();
				c.o1.onContactStart(c.o2);
				c.o2.onContactStart(c.o1);
			}
		}

		@Override
		public void beginContact(Contact contact) {
			
			Body bodyA = contact.getFixtureA().getBody();
			Body bodyB = contact.getFixtureB().getBody();

			LevelObject a = (LevelObject) bodyA.getUserData();
			LevelObject b = (LevelObject) bodyB.getUserData();
			
			if(a != null && b != null){
				contacts.offer(new BodyTuple(a, b));
			}

			float globalYA = bodyA.getPosition().y;
			float globalYB = bodyB.getPosition().y;

			PolygonShape shapeA = (PolygonShape) bodyA.getFixtureList().get(0)
					.getShape();
			int verticesCount = shapeA.getVertexCount();
			float yMinA = Float.MAX_VALUE, yMaxA = Float.MIN_VALUE;
			Vector2 vector = new Vector2();

			for (int i = 0; i < verticesCount; i++) {
				shapeA.getVertex(i, vector);
				if (vector.y < yMinA)
					yMinA = vector.y;
				if (vector.y > yMaxA)
					yMaxA = vector.y;
			}
			yMinA += globalYA;
			yMaxA += globalYA;

			PolygonShape shapeB = (PolygonShape) bodyB.getFixtureList().get(0)
					.getShape();
			verticesCount = shapeB.getVertexCount();
			float yMinB = Float.MAX_VALUE, yMaxB = Float.MIN_VALUE;
			for (int i = 0; i < verticesCount; i++) {
				shapeB.getVertex(i, vector);
				if (vector.y < yMinB)
					yMinB = vector.y;
				if (vector.y > yMaxB)
					yMaxB = vector.y;
			}
			yMinB += globalYB;
			yMaxB += globalYB;

			if (yMinA >= yMaxB) {
				if (a instanceof ControlableEntity) {
					((ControlableEntity) a).touchGround();
				}
			}
			if (yMinB >= yMaxA) {
				if (b instanceof ControlableEntity) {
					((ControlableEntity) b).touchGround();
				}
			}
		}
	}
	
	private static class BodyTuple{
		public LevelObject o1, o2;
		public BodyTuple(LevelObject a, LevelObject b){
			o1 = a;
			o2 = b;
		}
	}

}
