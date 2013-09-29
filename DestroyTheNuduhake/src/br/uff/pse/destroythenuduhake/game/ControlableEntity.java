package br.uff.pse.destroythenuduhake.game;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;

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
	
	static final float SPEED = 2f;	// unit per second
	static final float JUMP_VELOCITY = 1f;
	static final float SIZE = 0.5f; // half a unit
	

	public ControlableEntity(float x, float y, GraphicAsset asset) {
		super(x, y, asset);
		velocity = new Vector2();
		velocity.x = 0f;
		velocity.y = 0f;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha){
		
		super.draw(batch, parentAlpha);
	}

	public void moveLeft() {
		this.setFacingLeft(true);
		this.setState(State.WALKING);
		this.getVelocity().x = SPEED;
	}

	public void moveRight() {
		this.setFacingLeft(false);
		this.setState(State.WALKING);
		this.getVelocity().x = SPEED;
	}

	public void jump() {
		
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
