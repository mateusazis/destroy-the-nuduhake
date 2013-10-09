package com.br.noixgdxgame;

import br.uff.pse.destroythenuduhake.game.TestBundle;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.game.control.Game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class MainDesktop {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Rai is late again";
		cfg.useGL20 = false;
		cfg.width = 800;
		cfg.height = 480;
		
//		new LwjglApplication(new MyGdxGame(), cfg);
		AssetBundle b = new TestBundle();
	    
		new LwjglApplication(new Game(b), cfg);
		
		
	}
}
