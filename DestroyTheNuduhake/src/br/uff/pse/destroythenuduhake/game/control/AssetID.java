package br.uff.pse.destroythenuduhake.game.control;

import java.io.Serializable;

import android.util.Log;

public class AssetID implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idNumber;
	private String name, description;
	
	public AssetID(int idNumber, String name, String description){
		this.idNumber = idNumber;
		this.name = name;
		this.description = description;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description;
	}
	
	@Override
	public boolean equals(Object other){
		if(other == null || !(other instanceof AssetID))
			return false;
		AssetID idOther = (AssetID)other;
		return this.idNumber == idOther.idNumber;
	}
	@Override
	public int hashCode()
	{
		return idNumber;
	}
}
