package br.uff.pse.destroythenuduhake.game.level;

import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class IAManager extends Actor{

	private static float MIN_X_DISTANCE = 300;
	private List<Enemy> enemies;
	private Player player;
	
	public IAManager(Enemy [] enemies, Player p){
		this.enemies = Arrays.asList(enemies);
		this.player = p;
	}
	
	public void addEnemies(Enemy...e){
		for(int i = 0; i < e.length; i++)
			enemies.add(e[i]);
	}
	
	
	@Override
	public void act(float delta) {
		super.act(delta);
		for(int i = 0; i < enemies.size(); i++){
			Enemy e = enemies.get(i);
			if(e.isDead()){
				enemies.remove(i);
				i--;
			} else {
				float xDistance = Math.abs(e.getX() - player.getX());
				if(xDistance <= MIN_X_DISTANCE)
					e.awake(player);
				else
					e.sleep();
			}
			
		}
	}
}
