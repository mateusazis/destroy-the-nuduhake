package br.uff.pse.files;

import java.io.Serializable;

public class Asset implements Serializable
{

	/**
	 * @param args
	 */
	public String name;
	public String type;
	public Asset(String name, String type)
	{
		this.name = name;
		this.type = type;
	}

}
