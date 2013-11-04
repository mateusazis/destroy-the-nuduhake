package br.uff.pse.destroythenuduhake.game.level;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class IAManager extends Actor{

	private List<Enemy> enemies;
	private Player player;
	
	public IAManager(Player p, Enemy...enemies){
		this.enemies = new ArrayList<Enemy>();
		addEnemies(enemies);
		this.player = p;
	}
	
	public void addEnemies(Enemy...e){
		for(int i = 0; i < e.length; i++){
			enemies.add(e[i]);
			e[i].setManager(this);
		}
	}
	
	
	@Override
	public void act(float delta) {
		super.act(delta);
		Sword s = player.getWeapon();
		Rectangle sRect = s.getRect();
		
		for(int i = 0; i < enemies.size(); i++){
			Enemy e = enemies.get(i);
			if(s.canHitEnemies() && sRect.overlaps(e.getRect()))
				s.onOverlap(e);
			
			if(e.isDead()){
				enemies.remove(i);
				e.setManager(null);
				i--;
			} else {
				float xDistance = Math.abs(e.getX() - player.getX());
				boolean isNear = xDistance <= e.getMinXPlayerDistance(); 
				if(isNear && e.isSleeping())
					e.awake(player);
				if(!isNear && !e.isSleeping())
					e.sleep();
			}
			
		}
	}
}
