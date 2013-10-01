package br.uff.pse.destroythenuduhake.game;

import java.util.Iterator;

import org.w3c.dom.Entity;

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
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TestLevel extends Level {

	private GraphicAsset raiTex;
	private GraphicAsset playerTex;
	private LevelObject raiObject;
	private SoundAsset choiceSound;
	private MusicAsset music;
	private Player player;
	private Block block;
	private DefaultController controllerPadrao;
	OrthographicCamera camera;

	static final float WORLD_TO_BOX = 0.01f;
	static final float BOX_TO_WORLD = 100f;
	World world = new World(new Vector2(0.0f, -1000.0f), true);
	Box2DDebugRenderer debugRenderer;

	@Override
	public void createWithAssetBundle(AssetBundle b) {
		super.createWithAssetBundle(b);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		raiTex = b.getAsset(AssetIDs.SPRITE_MARIO);
		raiObject = new LevelObject(800/8 - 64/2, 80, raiTex);

		playerTex = b.getAsset(AssetIDs.SPRITE_SHELL);
		player = new Player(300, 20, playerTex);
		controllerPadrao = new DefaultController(player);
		Gdx.input.setInputProcessor(controllerPadrao);
		addObject(player);

		// choiceSound = b.getAsset(AssetIDs.SOUND_CHOICE);
		// music = b.getAsset(AssetIDs.MUSIC_JUNGLE);
		// music.play();

		addObject(raiObject);

		// addObject(new LevelObject(0, 0,
		// b.<GraphicAsset>getAsset(AssetIDs.SPRITE_SHELL)));

		camera = new OrthographicCamera();
		camera.viewportHeight = 320;
		camera.viewportWidth = 480;
		camera.position.set(camera.viewportWidth * .5f,
				camera.viewportHeight * .5f, 0f);
		camera.update();
		
		
		// Ground body
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(0, 10));
		Body groundBody = world.createBody(groundBodyDef);
		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox((camera.viewportWidth) * 2, 10.0f);
		groundBody.createFixture(groundBox, 0.0f);
		
		
		// Dynamic Body
		
		player.setBody(world.createBody(player.getBodyDef()));
		player.getBody().setUserData(player);
		CircleShape dynamicCircle = new CircleShape();
		dynamicCircle.setRadius(1f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicCircle;
		fixtureDef.density = 0.1f;
		fixtureDef.friction = 0.0f;
		fixtureDef.restitution = 0;
		player.getBody().createFixture(fixtureDef);
		
		
		raiObject.setBody(world.createBody(raiObject.getBodyDef()));
		raiObject.getBody().setUserData(raiObject);
		raiObject.getBody().createFixture(fixtureDef);
		
		//debugRenderer = new Box2DDebugRenderer();

	}

	@Override
	public void render() {
		super.render();
		controllerPadrao.update();
		world.step(1 / 300f, 6, 2);

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

	@Override
	public void dispose() {
		super.dispose();
		raiTex.dispose();
	}
}
