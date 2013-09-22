package br.uff.pse.destroythenuduhake.game;

public class TestLevel extends Level{
	
	private GraphicAsset raiTex;
	private LevelObject raiObject;
	
	@Override
	public void create() {
		super.create();
		
		raiTex = new GraphicAsset("images/mario.png");
		raiObject = new LevelObject(800/2 - 64/2, 20, raiTex);
		
		addObject(raiObject);
	}

	@Override
	public void dispose() {
		super.dispose();
		raiTex.dispose();
	}
}
