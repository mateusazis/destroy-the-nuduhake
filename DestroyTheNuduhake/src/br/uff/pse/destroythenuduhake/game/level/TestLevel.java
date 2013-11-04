package br.uff.pse.destroythenuduhake.game.level;

import br.uff.pse.destroythenuduhake.game.Physics;
import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.game.control.Level;
import br.uff.pse.destroythenuduhake.game.control.LevelObject;
import br.uff.pse.destroythenuduhake.game.level.enemies.BallShooter;
import br.uff.pse.destroythenuduhake.game.level.enemies.ShooterEnemy;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class TestLevel extends Level {

	private GraphicAsset playerTex;
	private GraphicAsset shellTex;
	private LevelObject ground, ground2;
	private ControlableEntity shell;
	private Player player;
	private DefaultController controllerPadrao;
	private OrthographicCamera camera;
	private Enemy e;
	private ShooterEnemy se;
	private IAManager manager;
	private Map map;
	OrthogonalTiledMapRenderer renderer;
	// physics
	private World world;
	Box2DDebugRenderer r;
	
	private static final float CAMERA_SIZE = 1f;

	@Override
	public void createWithAssetBundle(AssetBundle b) {
		super.createWithAssetBundle(b);
		
		camera = (OrthographicCamera) this.getCamera();


		
		
		Vector2 gravity = new Vector2(0.0f, -10.0f);
		world = new World(gravity, true);

		r = new Box2DDebugRenderer();
		
		LevelObject bg = new FixedObject(-CAMERA_SIZE*400, -CAMERA_SIZE*240, b.<GraphicAsset>getAsset(AssetDatabase.SPRITE_BACKGROUND));
		addActor(bg);
		bg.setScale(CAMERA_SIZE);
		
		map = new Map("mapa.tmx", 0, 0, world, camera);
		addActor(map);

		// setup assets
		playerTex = b.getAsset(AssetDatabase.SPRITE_MARIO);

		shellTex = b.getAsset(AssetDatabase.SPRITE_SHELL);
		
		// setup objects
		
		
//		ground = new LevelObject(100, 30,
//				b.<GraphicAsset> getAsset(AssetDatabase.SPRITE_GROUND));
//		ground.setupPhysics(world);
//		addActor(ground);
		
//		LevelObject ground3 = new LevelObject(-350, 30,
//				b.<GraphicAsset> getAsset(AssetDatabase.SPRITE_GROUND));
//		ground3.setupPhysics(world);
//		addActor(ground3);

//		ground2 = new LevelObject(700, 120,
//				b.<GraphicAsset> getAsset(AssetDatabase.SPRITE_GROUND));
//		ground2.setupPhysics(world);
//		addActor(ground2);

//		shell = new ControlableEntity(70, 20, shellTex);
//		addActor(shell);
//		shell.setupPhysics(world);

		player = new Player(100, 140, playerTex, b.<GraphicAsset>getAsset(AssetDatabase.SPRITE_SWORD));
		addActor(player);
		player.setupPhysics(world);
		
//		e = new Enemy(200, 40, shellTex);
//		addActor(e);
//		e.setupPhysics(world);
		
//		se = new ShooterEnemy(200, 40, b);
//		addActor(se);
//		se.setupPhysics(world);
		
		manager = new IAManager(player);
		

//		BallShooter ball = new BallShooter(800, 120, b);
//		ball.setupPhysics(world);
//		addActor(ball);
		
//		Ball ball = new Ball(400, 100, b.<GraphicAsset>getAsset(AssetDatabase.SPRITE_BALL));
//		ball.setupPhysics(world);
//		addActor(ball);
		
		//manager = new IAManager(player, ball);
//		manager.addEnemies(se, ball);
		addActor(manager);

		// setup input
		controllerPadrao = new DefaultController(player, b, this);

		// debugRenderer = new Box2DDebugRenderer();

		world.setContactListener(new LevelContactListener());

		
		camera.zoom = CAMERA_SIZE;
		
//		map.setZIndex(1000);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		map.dispose();
	}
	
	@Override
	public void render() {
		super.render();
//		
		controllerPadrao.update();
		camera.position.set(player.getX(), camera.position.y, 0);
		world.step(1/60f, 6, 2);
		
//		map.draw(getSpriteBatch(), 1);
		
//		r.render(world, camera.combined.scale(Physics.BOX_TO_WORLD, Physics.BOX_TO_WORLD, Physics.BOX_TO_WORLD));
//		Iterator<Body> bi = world.getBodies();
//
//		while (bi.hasNext()) {
//			Body b = bi.next();
//
//			// Get the bodies user data - in this example, our user
//			// data is an instance of the Entity class
//			Actor e = (Actor) b.getUserData();
//
//			if (e != null) {
//				// Update the entities/sprites position and angle
//				float posX, posY;
////				posX = b.getPosition().x * BOX_TO_WORLD - e.getWidth()/2;
//				float bodyX = b.getPosition().x;
//				float bodyY = b.getPosition().y;
//				posX = bodyX * Physics.BOX_TO_WORLD; 
//				posY = bodyY * Physics.BOX_TO_WORLD;
//				e.setPosition(posX,posY);
//				// We need to convert our angle from radians to degrees
//				e.setRotation(MathUtils.radiansToDegrees
//						* b.getAngle());
//			}
//		}
	}

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
			LevelObject a = (LevelObject) contact.getFixtureA().getBody().getUserData();
			LevelObject b = (LevelObject) contact.getFixtureB().getBody().getUserData();
			
			
			if(a instanceof ControlableEntity){
				((ControlableEntity) a).touchGround();
			}
			if(b instanceof ControlableEntity){
				((ControlableEntity) b).touchGround();
			}
			
			if(a != null)
				a.onContactStart(b);
			if(b != null)
				b.onContactStart(a);
			
		}
	}
}
