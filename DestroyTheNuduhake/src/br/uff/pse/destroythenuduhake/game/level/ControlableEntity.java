package br.uff.pse.destroythenuduhake.game.level;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.LevelObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class ControlableEntity extends LevelObject {

	private int life;
	private int atackPower;	
	private Vector2 velocity;
	private Fixture fixture;
	
	Rectangle 	bounds = new Rectangle();
	private State		state = State.IDLE;
	private boolean		facingLeft = true;
	
	public enum State {
		IDLE, WALKING, JUMPING, DYING
	}
	
	static final float SPEED = 50f;	// unit per second
	static final float JUMP_VELOCITY = 1f;
	static final float SIZE = 0.5f; // half a unit
	

	public ControlableEntity(float x, float y, GraphicAsset asset) {
		super(x, y, asset);
		velocity = new Vector2();
		velocity.x = 0f;
		velocity.y = 0f;
		this.bodyDef.type = BodyType.DynamicBody;
	}
	
	@Override
	public void setupPhysics(World world){
		Body b = world.createBody(getBodyDef());
		b.setUserData(this);
		setBody(b);
		
		PolygonShape dynamicShape = new PolygonShape();
		float w = getWidth(), h = getHeight();
		float hX = w / 20f, hY = h / 20f;
		dynamicShape.setAsBox(hX, hY, new Vector2(hX, hY), 0);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicShape;
		fixtureDef.density = 0.0f;
		fixtureDef.friction = 8.0f;
		fixtureDef.restitution = 0;
		getBody().createFixture(fixtureDef);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha){
		
		super.draw(batch, parentAlpha);
	}

	public void moveLeft() {
		if(getState() != State.JUMPING){
			this.setFacingLeft(true);
			this.setState(State.WALKING);
			getBody().applyLinearImpulse(SPEED, 0, getX(), getY());
	//		this.getVelocity().x = SPEED;
		}
	}

	public void moveRight() {
		if(getState() != State.JUMPING){
			this.setFacingLeft(false);
			this.setState(State.WALKING);
			getBody().applyLinearImpulse(-SPEED, 0, getX(), getY());
	//		this.getVelocity().x = SPEED;
		}
	}
	
	public void touchGround(){
		setState(State.IDLE);
	}

	public void jump() {
		if(getState() != State.JUMPING){
			setState(State.JUMPING);
			getBody().applyLinearImpulse(0.0f, 1000.0f, getX(), getY());
		}
	}

	public void atack() {

	}

	public void onAtacked(int atackPower) {

	}

	public void die() {

	}

	public int getAtackPower() {
		return atackPower;
	}

	public void setAtackPower(int atackPower) {
		this.atackPower = atackPower;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public boolean isFacingLeft() {
		return facingLeft;
	}

	public void setFacingLeft(boolean facingLeft) {
		this.facingLeft = facingLeft;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Fixture getFixture() {
		return fixture;
	}

	public void setFixture(Fixture fixture) {
		this.fixture = fixture;
	}

}
