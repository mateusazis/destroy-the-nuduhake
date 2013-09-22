package br.uff.pse.destroythenuduhake.game;


public class DefaultBundle extends AssetBundle{

	private static DefaultBundle instance;
	
	private DefaultBundle(){
		super("");
	}
	
	public static DefaultBundle getInstance(){
		if(instance == null)
			instance = new DefaultBundle();
		return instance;
	}
	
}
