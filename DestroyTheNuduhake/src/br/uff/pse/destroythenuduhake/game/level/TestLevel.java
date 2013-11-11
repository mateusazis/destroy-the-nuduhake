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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class TestLevel extends Level {

	private GraphicAsset playerTex;
	private Player player;
	private DefaultController defaultController;
	private OrthographicCamera camera;
	private IAManager manager;
	private Map map;
	OrthogonalTiledMapRenderer renderer;
	
	// physics
	private World world;
	private Box2DDebugRenderer r;
	
	private Physics.LevelContactListener pListener;
	
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
		// setup objects

		Vector2 playerPos = map.getPlayerPosition();
		player = new Player(playerPos.x, playerPos.y, playerTex, b.<GraphicAsset>getAsset(AssetDatabase.SPRITE_SWORD));
		addActor(player);
		player.setupPhysics(world);
		
		
		//IA---------------------------------------------------------------------------------------
		manager = new IAManager(player);
		
		//Enemies----------------------------------------------------------------------------------
		for(Rectangle r : map.findObjects("shooter")){
			ShooterEnemy e = new ShooterEnemy(r.x, r.y, b);
			e.setupPhysics(world);
			addActor(e);
			
			manager.addEnemies(e);
		}
		
		for(Rectangle r : map.findObjects("ball_shooter")){
			BallShooter ball = new BallShooter(r.x, r.y, b);
			ball.setupPhysics(world);
			addActor(ball);
			
			manager.addEnemies(ball);
		}
		
		addActor(manager);

		// setup input
		defaultController = new DefaultController(player, b, this);

		world.setContactListener(pListener = new Physics.LevelContactListener());
		camera.zoom = CAMERA_SIZE;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		map.dispose();
	}
	
	@Override
	public void render() {
		super.render();
		defaultController.update();
		camera.position.set(player.getX(), camera.position.y, 0);
//		camera.position.set(player.getX(), player.getY(), 0);
		world.step(1/60f, 6, 2);
		pListener.processContacts();
//		r.render(world, camera.combined.scale(Physics.BOX_TO_WORLD, Physics.BOX_TO_WORLD, Physics.BOX_TO_WORLD));
	}
}
