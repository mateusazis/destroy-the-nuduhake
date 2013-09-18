package br.uff.pse.files;

import java.io.Serializable;

public class Asset implements Serializable
{

	/**
	 * @param args
	 */
	public String name;
	public String type;
	public String author;
	public Asset(String name, String type, String author)
	{
		this.name = name;
		this.type = type;
		this.author = author;
	}

}
