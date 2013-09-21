package com.br.noixgdxgame;

import br.uff.pse.destroythenuduhake.game.MyGdxGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class MainDesktop {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Rai is late again";
		cfg.useGL20 = false;
		cfg.width = 800;
		cfg.height = 800;
		
		new LwjglApplication(new MyGdxGame(), cfg);
	}
}
