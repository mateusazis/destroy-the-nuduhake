package br.uff.pse.destroythenuduhake.game;

import java.util.HashMap;

public final class AssetIDs {
	
	public static final int MARIO = 0;
	public static final int SHELL = 1;
	
	private static HashMap<Integer, String> pathTable;
	
	private static void initializePathTable(){
		if(pathTable == null){
			pathTable = new HashMap<Integer, String>();
			pathTable.put(AssetIDs.MARIO, "mario.png");
			pathTable.put(AssetIDs.SHELL, "shell.png");
		}
	}
	
	public static String getFileName(int id){
		initializePathTable();
		return pathTable.get(id);
	}
}
