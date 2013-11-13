package br.uff.pse.destroythenuduhake.game.level;

import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;

public class LifeManager {
	private int lifeCount;
	private FixedObject heartBg;
	private FixedObject heart1;
	private FixedObject heart2;
	private FixedObject heart3;
	private Player player;
	GraphicAsset fullHeartAsset, halfHeartAsset, emptyHeartAsset, heartBgAsset;
	public LifeManager(GraphicAsset fullHeartAsset, GraphicAsset halfHeartAsset, GraphicAsset emptyHeartAsset, GraphicAsset heartBgAsset, Player player) {
		this.player = player;
		this.fullHeartAsset = fullHeartAsset;
		this.halfHeartAsset = halfHeartAsset;
		this.emptyHeartAsset = emptyHeartAsset;
		this.heartBgAsset = heartBgAsset;
		
		heart1 = new FixedObject(-380, 190,fullHeartAsset);
		heart2 = new FixedObject(-330, 190,fullHeartAsset);
		heart3 = new FixedObject(-280, 190,fullHeartAsset);
		heartBg = new FixedObject(-384,  187, heartBgAsset);
		
		player.getParent().addActor(heart1);
		player.getParent().addActor(heart2);
		player.getParent().addActor(heart3);
		player.getParent().addActor(heartBg);
		
		heartBg.setZIndex(heart1.getZIndex()-1);
		
	}
	
	public void update(){
		lifeCount = player.getLife();
		
		if(lifeCount >= 2)
			heart1.setGraphic(fullHeartAsset);
		else
			if(lifeCount == 1)
				heart1.setGraphic(halfHeartAsset);
			else
				heart1.setGraphic(emptyHeartAsset);
					
			
		if(lifeCount >= 4)
			heart2.setGraphic(fullHeartAsset);
		else
			if(lifeCount == 3)
				heart2.setGraphic(halfHeartAsset);
			else
				heart2.setGraphic(emptyHeartAsset);
		
		if(lifeCount == 6)
			heart3.setGraphic(fullHeartAsset);
		else
			if(lifeCount == 5)
				heart3.setGraphic(halfHeartAsset);
			else
				heart3.setGraphic(emptyHeartAsset);
	}
}
