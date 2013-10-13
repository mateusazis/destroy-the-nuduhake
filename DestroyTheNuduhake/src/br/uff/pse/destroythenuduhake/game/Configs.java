package br.uff.pse.destroythenuduhake.game;

public class Configs {

	private static boolean _android = true;
	
	public static void setup(boolean isAndroid){
		_android = isAndroid;
	}
	
	public static boolean isAndroid(){
		return _android;
	}
	
}
