package br.uff.pse.destroythenuduhake.game.level;

import java.util.Iterator;

import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.game.control.Level;
import br.uff.pse.destroythenuduhake.game.control.LevelObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TestLevel extends Level {

	private GraphicAsset playerTex;
	private GraphicAsset shellTex;
	private LevelObject ground, ground2;
	private ControlableEntity shell;
	private Player player;
	private Block block;
	private DefaultController controllerPadrao;
	private OrthographicCamera camera;

	// physics
	private static final float WORLD_TO_BOX = 0.01f;
	private static final float BOX_TO_WORLD = 10f;
	private World world;

	@Override
	public void createWithAssetBundle(AssetBundle b) {
		super.createWithAssetBundle(b);

		Vector2 gravity = new Vector2(0.0f, -10.0f);
		world = new World(gravity, true);

		// setup assets
		playerTex = b.getAsset(AssetDatabase.SPRITE_MARIO);

		shellTex = b.getAsset(AssetDatabase.SPRITE_SHELL);

		// setup objects
		ground = new LevelObject(100, 10,
				b.<GraphicAsset> getAsset(AssetDatabase.SPRITE_GROUND));
		ground.setupPhysics(world);
		// ground.set
		addActor(ground);

		ground2 = new LevelObject(700, 120,
				b.<GraphicAsset> getAsset(AssetDatabase.SPRITE_GROUND));
		ground2.setupPhysics(world);
		addActor(ground2);

		shell = new ControlableEntity(70, 20, shellTex);
		addActor(shell);
		shell.setupPhysics(world);

		player = new Player(100, 40, playerTex);
		addActor(player);
		player.setupPhysics(world);

		// setup input
		controllerPadrao = new DefaultController(player);
		Gdx.input.setInputProcessor(controllerPadrao);

		// debugRenderer = new Box2DDebugRenderer();

		world.setContactListener(new LevelContactListener());

		camera = (OrthographicCamera) this.getCamera();
		camera.zoom = 3f;

	}

	@Override
	public void render() {
		super.render();
		controllerPadrao.update();
		camera.position.set(player.getX(), camera.position.y, 0);
		world.step(1 / 10f, 6, 2);

		Iterator<Body> bi = world.getBodies();

		while (bi.hasNext()) {
			Body b = bi.next();

			// Get the bodies user data - in this example, our user
			// data is an instance of the Entity class
			Actor e = (Actor) b.getUserData();

			if (e != null) {
				// Update the entities/sprites position and angle
				((Actor) e).setPosition(b.getPosition().x * BOX_TO_WORLD - e.getWidth()/2,
						b.getPosition().y * BOX_TO_WORLD - e.getHeight()/2);
				// We need to convert our angle from radians to degrees
				((Actor) e).setRotation(MathUtils.radiansToDegrees
						* b.getAngle());
//				((Actor) e).setPosition(b.getPosition().x,
//						b.getPosition().y);
			}
		}
	}

	// @Override
	// public void act(float delta) {
	// // TODO Auto-generated method stub
	// super.act(delta);
	// if(Gdx.input.justTouched()){
	// choiceSound.play();
	// }
	// }

	// @Override
	// public void dispose() {
	// super.dispose();
	// raiTex.dispose();
	// }

	class LevelContactListener implements ContactListener {

		@Override
		public void preSolve(Contact contact, Manifold oldManifold) {
			// TODO Auto-generated method stub

		}

		@Override
		public void postSolve(Contact contact, ContactImpulse impulse) {
			// TODO Auto-generated method stub

		}

		@Override
		public void endContact(Contact contact) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beginContact(Contact contact) {
			if (contact.getFixtureA().getBody().getUserData() == shell
					&& contact.getFixtureB().getBody().getUserData() == player
					|| contact.getFixtureA().getBody().getUserData() == player
					&& contact.getFixtureB().getBody().getUserData() == shell) {
				shell.setGraphic(shellTex);
			} else {
				player.touchGround();
			}
		}
	}
}
