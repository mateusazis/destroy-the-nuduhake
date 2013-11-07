package br.uff.pse.destroythenuduhake.game.control;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.Physics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class LevelObject extends Actor{

	private GraphicAsset graphic;
	private BodyDef bodyDef;
	private Body body;
	private Rectangle r;
	private TextureRegion region;
	private Fixture fixture;
	
	public LevelObject(float x, float y, GraphicAsset asset){
		super();
		r = new Rectangle();
		region = new TextureRegion();
		setPosition(x, y);
		setGraphic(asset);
	}
	
	public void setFlipped(boolean flipped){
		if(flipped != region.isFlipX())
			region.flip(true, false);
	}
	
	public boolean isFlipped(){
		return region.isFlipX();
	}
	
	public TextureRegion getTextureRegion(){
		return region;
	}
	
	public Rectangle getRect(){
		r.x = getX(); r.y = getY();
		return r;
	}
	
	public void onContactStart(LevelObject other){	}
	
	
	public void dispose(){
		if(body != null){
			World w = body.getWorld();
			w.destroyBody(body);
		}
	}

	
	public void setupPhysics(World world){
		bodyDef = new BodyDef();
		bodyDef.position.set(getX() * Physics.WORLD_TO_BOX, getY() * Physics.WORLD_TO_BOX);
		bodyDef.type = BodyDef.BodyType.StaticBody;
		
		body = world.createBody(bodyDef);
		body.setUserData(this);
		
		rebuildShape();
//		PolygonShape groundBox = new PolygonShape();
//		float w = getWidth() * Physics.WORLD_TO_BOX, h = getHeight() * Physics.WORLD_TO_BOX;
//		float hW = w / 2f, hH = h / 2f;
//		groundBox.setAsBox(hW, hH, new Vector2(hW, hH), 0);
//		this.fixture = createBodyFixture(body, groundBox);
	}
	
	protected void rebuildShape(){
		Array<Fixture> fixtures = body.getFixtureList();
		for(Fixture f : fixtures)
			body.destroyFixture(f);
		
		PolygonShape groundBox = new PolygonShape();
		float w = getWidth() * Physics.WORLD_TO_BOX, h = getHeight() * Physics.WORLD_TO_BOX;
		float hW = w / 2f, hH = h / 2f;
		groundBox.setAsBox(hW, hH, new Vector2(hW, hH), 0);
		this.fixture = createBodyFixture(body, groundBox);
	}
	
	public Fixture getFixture(){
		return fixture;
	}
	
	public Fixture createBodyFixture(Body b, PolygonShape boxShape){
		return body.createFixture(boxShape, 0.0f);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(body != null){
			Vector2 physicsPosition = body.getPosition();
			setPosition(physicsPosition.x * Physics.BOX_TO_WORLD, physicsPosition.y * Physics.BOX_TO_WORLD);
			setRotation(MathUtils.radiansToDegrees * body.getAngle());
		}
	}
	
	public void superRemove(){
		super.remove();
	}
	
	@Override
	public boolean remove() {
		Level l = (Level)getStage();
		boolean resp = super.remove();
		if(resp)
			l.addToDisposeList(this);
		return resp;
//			return false;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
//		float x = getX(), y = getY();
//		getGraphic().render(batch, x, y);
		getGraphic().render(batch, this);
	}

	public BodyDef getBodyDef() {
		return bodyDef;
	}

	public void setBodyDef(BodyDef bodyDef) {
		this.bodyDef = bodyDef;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public GraphicAsset getGraphic() {
		return graphic;
	}

	public void setGraphic(GraphicAsset graphic) {
		this.graphic = graphic;
		region.setRegion(graphic.getTexture());
		r.setSize(graphic.getWidth(), graphic.getHeight());
		setSize(graphic.getWidth(), graphic.getHeight());
	}
	
//	@Override
//	public void act(float delta) {
//		super.act(delta);
//		
//		if(Gdx.input.isTouched()){
//			float dX = Gdx.input.getX() - getX();
//			float dY = Gdx.graphics.getHeight() - Gdx.input.getY() - getY();
//			
//			float mag = (float)Math.sqrt(dX * dX + dY * dY);
//			float SPEED = 100;
//			
//			super.translate(SPEED * dX * delta / mag , SPEED * dY * delta / mag);
//		}
//	}
}
