package br.uff.pse.destroythenuduhake.game;

import java.util.Iterator;

import org.w3c.dom.Entity;

import br.uff.pse.destroythenuduhake.game.ControlableEntity.State;
import br.uff.pse.destroythenuduhake.game.assets.AssetIDs;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.assets.MusicAsset;
import br.uff.pse.destroythenuduhake.game.assets.SoundAsset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
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

	//physics
	private static final float WORLD_TO_BOX = 0.01f;
	private static final float BOX_TO_WORLD = 100f;
	private World world;
	private Box2DDebugRenderer debugRenderer;

	@Override
	public void createWithAssetBundle(AssetBundle b) {
		super.createWithAssetBundle(b);

		Vector2 gravity = new Vector2(0.0f, -1000.0f);
		world = new World(gravity, true);

		//setup assets
		playerTex = b.getAsset(AssetIDs.SPRITE_MARIO);
		shellTex = b.getAsset(AssetIDs.SPRITE_SHELL);
		
		// setup objects
		ground = new LevelObject(200, 10, b.<GraphicAsset>getAsset(AssetIDs.SPRITE_GROUND));
		ground.setupPhysics(world);
		addActor(ground);
		
		ground2 = new LevelObject(670, 70, b.<GraphicAsset>getAsset(AssetIDs.SPRITE_GROUND));
		ground2.setupPhysics(world);
		addActor(ground2);
		
		shell = new ControlableEntity(800/8 - 64/2, 80, shellTex);
		addActor(shell);
		shell.setupPhysics(world);

		player = new Player(300, 20, playerTex);
		addActor(player);
		player.setupPhysics(world);
		
		//setup input
		controllerPadrao = new DefaultController(player);
		Gdx.input.setInputProcessor(controllerPadrao);
		
		//debugRenderer = new Box2DDebugRenderer();
		
		world.setContactListener(new LevelContactListener());
	}

	@Override
	public void render() {
		super.render();
		controllerPadrao.update();
		world.step(1 / 300f, 6, 2);

		//System.out.print(player.getState());
		//debugRenderer.render(world, camera.combined);
		Iterator<Body> bi = world.getBodies();

		while (bi.hasNext()) {
			Body b = bi.next();

			// Get the bodies user data - in this example, our user
			// data is an instance of the Entity class
			Actor e = (Actor) b.getUserData();

			if (e != null) {
				// Update the entities/sprites position and angle
				((Actor) e).setPosition(b.getPosition().x, b.getPosition().y);
				// We need to convert our angle from radians to degrees
				((Actor) e).setRotation(MathUtils.radiansToDegrees
						* b.getAngle());
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

//	@Override
//	public void dispose() {
//		super.dispose();
//		raiTex.dispose();
//	}
	
	class LevelContactListener implements ContactListener{

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
			if(contact.getFixtureA().getBody().getUserData() == shell && contact.getFixtureB().getBody().getUserData() == player
				|| contact.getFixtureA().getBody().getUserData() == player && contact.getFixtureB().getBody().getUserData() == shell){
				shell.setGraphic(shellTex);
			}
			else{
				player.touchGround();
			}
		}
	}
}

