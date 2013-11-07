package br.uff.pse.destroythenuduhake.game;

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

		@Override
		public void preSolve(Contact contact, Manifold oldManifold) {
		}

		@Override
		public void postSolve(Contact contact, ContactImpulse impulse) {
		}

		@Override
		public void endContact(Contact contact) {
		}

		@Override
		public void beginContact(Contact contact) {
			Body bodyA = contact.getFixtureA().getBody();
			Body bodyB = contact.getFixtureB().getBody();

			colide(bodyA, bodyB);
			// colide(bodyB, bodyA);

			// if (bodyA.getLinearVelocity().y < 0
			// || bodyB.getLinearVelocity().y < 0) {
			//
			// if (a instanceof ControlableEntity) {
			// // ((ControlableEntity) a).touchGround();
			// }
			// if (b instanceof ControlableEntity) {
			// // ((ControlableEntity) b).touchGround();
			// }
			// }
			//
			// if (a != null)
			// a.onContactStart(b);
			// if (b != null)
			// b.onContactStart(a);
		}
	}

	public static void colide(Body bodyA, Body bodyB) {
		LevelObject a = (LevelObject) bodyA.getUserData();
		LevelObject b = (LevelObject) bodyB.getUserData();

		float globalYA = bodyA.getPosition().y;
		float globalYB = bodyB.getPosition().y;

		// float globalXA = bodyA.getPosition().x;
		// float globalXB = bodyB.getPosition().x;

		PolygonShape shapeA = (PolygonShape) bodyA.getFixtureList().get(0)
				.getShape();
		int verticesCount = shapeA.getVertexCount();
		float xMinA = Float.MAX_VALUE, xMaxA = Float.MIN_VALUE, yMinA = Float.MAX_VALUE, yMaxA = Float.MIN_VALUE;
		Vector2 vector = new Vector2();
		// for (int i = 0; i < verticesCount; i++) {
		// shapeA.getVertex(i, vector);
		// if (vector.x < xMinA)
		// xMinA = vector.x;
		// if (vector.x > xMaxA)
		// xMaxA = vector.x;
		// }
		// xMinA += globalXA;
		// xMaxA += globalXA;

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
		float xB, yMinB = Float.MAX_VALUE, yMaxB = Float.MIN_VALUE;
		for (int i = 0; i < verticesCount; i++) {
			shapeB.getVertex(i, vector);
			if (vector.y < yMinB)
				yMinB = vector.y;
			if (vector.y > yMaxB)
				yMaxB = vector.y;
		}
		// xB = vector.x;
		// xB += globalXB;

		yMinB += globalYB;
		yMaxB += globalYB;

		// System.out.println("yMinA:" + yMinA +
		// "\nyMaxA: " + yMaxA);
		//
		// System.out.println("yMinB:" + yMinB +
		// "\nyMaxB: " + yMaxB);
		//
		// System.out.println("xMinA:" + xMinA +
		// "\nxMaxA: " + xMaxA);
		// System.out.println("xMinB:" + xB +
		// "\nxB: " + xB);

		// if (xB >= xMinA && xB <= xMaxA) {
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
		// }
	}
}
