package br.uff.pse.destroythenuduhake.game;

import br.uff.pse.destroythenuduhake.game.assets.DTNGameBundle;


public class DefaultBundle extends DTNGameBundle{

	private static DefaultBundle instance;
	
	private DefaultBundle(){
		super("default/");
	}
	
	public static DefaultBundle getInstance(){
		if(instance == null)
			instance = new DefaultBundle();
		return instance;
	}
	
}
