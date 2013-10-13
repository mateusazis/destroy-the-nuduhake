package br.uff.pse.destroythenuduhake.game.level;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.LevelObject;
import br.uff.pse.destroythenuduhake.game.Physics;

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

	private int life = 3;
	private int atackPower;	
	private Vector2 velocity;
	private Fixture fixture;
	
	Rectangle 	bounds = new Rectangle();
	private State		state = State.IDLE;
	private boolean		facingLeft = true;
	
	public enum State {
		IDLE, WALKING, JUMPING, DYING
	}
	
	protected static final float SPEED = 1f;	// unit per second
	protected static final float JUMP_VELOCITY = 5f;
	protected static final float SIZE = 0.5f; // half a unit
	

	public ControlableEntity(float x, float y, GraphicAsset asset) {
		super(x, y, asset);
		velocity = new Vector2();
		velocity.x = 0f;
		velocity.y = 0f;
	}
	
	@Override
	public void createBodyFixture(Body b, PolygonShape boxShape) {
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = boxShape;
		fixtureDef.density = 0.0f;
		fixtureDef.friction = 8.0f;
		fixtureDef.restitution = 0;
		b.createFixture(fixtureDef);
		b.setType(BodyType.DynamicBody);
	}

	public void moveLeft() {
		if(getState() != State.JUMPING){
			this.setFacingLeft(true);
			this.setState(State.WALKING);
			getBody().applyLinearImpulse(-SPEED, 0, getX(), getY());
	//		this.getVelocity().x = SPEED;
		}
	}

	public void moveRight() {
		if(getState() != State.JUMPING){
			this.setFacingLeft(false);
			this.setState(State.WALKING);
			getBody().applyLinearImpulse(SPEED, 0, getX(), getY());
	//		this.getVelocity().x = SPEED;
		}
	}
	
	public void touchGround(){
		setState(State.IDLE);
	}

	public void jump() {
		if(getState() != State.JUMPING){
			setState(State.JUMPING);
			getBody().applyLinearImpulse(0.0f, JUMP_VELOCITY, getX(), getY());
		}
	}

	public void atack() {

	}

	public void onAtacked(int atackPower) {
		this.life = Math.max(0, this.life - atackPower);
		if(isDead())
			die();
	}
	
	public boolean isDead(){
		return this.life <= 0;
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
