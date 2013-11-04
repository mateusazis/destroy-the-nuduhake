package br.uff.pse.destroythenuduhake.game.level;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.LevelObject;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class ControlableEntity extends LevelObject {

	private int life = 3;
	private int atackPower;
	private float velocity;
	private Fixture fixture;

	Rectangle bounds = new Rectangle();
	private State state = State.IDLE;
	protected boolean turnedLeft = true;

	public enum State {
		IDLE, WALKING, JUMPING, DYING
	}

	private float jumpVelocity = 5f;
	private float maxMoveVelocity = 10F;

	public ControlableEntity(float x, float y, GraphicAsset asset) {
		super(x, y, asset);
		velocity = 1f;
//		if(turnedLeft){
//			r.flip(true, false);
//		} Está comentado porque acabei fazendo o inimigo olhando pra direita, ai ia ficar estranho
	}

	public void setJumpVelocity(float value) {
		this.jumpVelocity = value;
	}

	public void setMaxMoveVelocity(float value) {
		this.maxMoveVelocity = value;
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
		if (!turnedLeft) {
			turnLeft();
		}
		if (getBody().getLinearVelocity().x > -maxMoveVelocity)
			getBody().applyLinearImpulse(-velocity, 0, getX(), getY(), true);
	}

	public void moveRight() {
		if(turnedLeft){
			turnRight();
		}
		if (getBody().getLinearVelocity().x < maxMoveVelocity)
			getBody().applyLinearImpulse(velocity, 0, getX(), getY(), true);
	}

	public void touchGround() {
		setState(State.IDLE);
	}

	public void jump() {
		if (getState() != State.JUMPING) {
			setState(State.JUMPING);
			getBody().applyLinearImpulse(0.0f, jumpVelocity, getX(), getY(), true);
		}
	}

	public void atack() {

	}

	public void onAtacked(int atackPower) {
		this.life = Math.max(0, this.life - atackPower);
		if (isDead())
			die();
	}

	public boolean isDead() {
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

	public float getVelocity() {
		return velocity;
	}

	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

	public void turnLeft() {
		turnedLeft = true;
		setFlipped(true);
	}

	public void turnRight() {
		turnedLeft = false;
		setFlipped(false);
	}

	public boolean isTurnedLeft() {
		return turnedLeft;
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
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}

}
