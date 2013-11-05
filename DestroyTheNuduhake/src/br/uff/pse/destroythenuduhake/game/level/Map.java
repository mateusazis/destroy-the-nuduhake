package br.uff.pse.destroythenuduhake.game.level;

import java.util.ArrayList;

import br.uff.pse.destroythenuduhake.game.Physics;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Map extends Actor {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private World world;
	private OrthographicCamera camera;
	
	
	public Map(String fileName, float x, float y, World w, OrthographicCamera c){
		map = (new TmxMapLoader()).load("mapa.tmx");
		this.camera = c;
		renderer = new OrthogonalTiledMapRenderer(map, 1);
		this.world = w;
		createTiles(map, x, y);	
	}
	
	public Vector2 getPlayerPosition(){
		for(MapLayer layer : map.getLayers()){
			for(MapObject obj : layer.getObjects()){
				MapProperties props = obj.getProperties();
				String type = props.get("type", String.class);
				if(type != null && type.equalsIgnoreCase("player")){
					RectangleMapObject rectObj = (RectangleMapObject)obj;
					Rectangle r = rectObj.getRectangle();
					return new Vector2(r.x, r.y);
				}
			}
		}
		return null;
	}
	
	public Rectangle[] findObjects(String objectType){
		ArrayList<Rectangle> resp = new ArrayList<Rectangle>();
		
		for(MapLayer layer : map.getLayers()){
			for(MapObject obj : layer.getObjects()){
				MapProperties props = obj.getProperties();
				String type = props.get("type", String.class);
				if(type != null && type.equalsIgnoreCase(objectType)){
					RectangleMapObject rectObj = (RectangleMapObject)obj;
					resp.add(rectObj.getRectangle());
				}
			}
		}
		
		return resp.toArray(new Rectangle[0]);
	}
		
	public void createTiles(TiledMap map, float x0, float y0){
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);
		int rows = layer.getHeight();
		int columns = layer.getWidth();
		CellCoords first = new CellCoords(), last = new CellCoords();
		
		for(int i = 0; i < rows; i++){
			first.clear(); last.clear();
			for(int j = 0; j < columns; j++){
				
				
				Cell cell = layer.getCell(j, i);
				if(cell != null){
					if(!isInnerCell(j, i, layer)){
						if(!first.isSet())
							first.set(i, j);
						last.set(i, j);
					} 
				} else if(first.isSet()){
					createBody(world, first, last, x0, y0);
					first.clear(); last.clear();
				}
			}
			if(first.isSet()){
				createBody(world, first, last, x0, y0);
				first.clear(); last.clear();
			}
		}
	}
	
	private void createBody(World world, CellCoords first, CellCoords last, float x0, float y0){
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set((x0 + first.j * 32) * Physics.WORLD_TO_BOX,  (y0 +  first.i * 32) * Physics.WORLD_TO_BOX);
		bodyDef.type = BodyDef.BodyType.StaticBody;
		
		Body body = world.createBody(bodyDef);
		
		PolygonShape groundBox = new PolygonShape();
		float w = (last.j - first.j + 1) * 32 * Physics.WORLD_TO_BOX, h = (last.i - first.i + 1)* 32 * Physics.WORLD_TO_BOX;
		float hX = w / 2f, hY = h / 2f;
		groundBox.setAsBox(hX, hY, new Vector2(hX, hY), 0);
		body.createFixture(groundBox, 0.0f);
	}
	
	private static boolean isInnerCell(int x, int y, TiledMapTileLayer layer){
		for(int i = -1; i <= 1; i++)
			for(int j = -1; j <= 1; j++){
				int newX = x + i;
				int newY = y + j;
				if((newX != 0 || newY != 0) && layer.getCell(newX, newY) == null)
					return false;
			}
		
		return true;
	}
	
	public void dispose(){
		map.dispose();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		//see: http://stackoverflow.com/questions/15883120/orthogonaltiledmaprenderer-and-normal-spritebatch-renders-a-white-box
		
		batch.end();
		
		renderer.setView(camera);
		renderer.render();
		batch.begin();
	}
	
	private class CellCoords{
		public int i = -1, j = -1;
		
		public boolean isSet(){
			return i != -1 && j != -1;
		}
		
		public void clear(){
			this.i = -1;
			this.j = -1;
		}
		
		public void set(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
}
