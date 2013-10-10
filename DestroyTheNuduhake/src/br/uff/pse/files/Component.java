package br.uff.pse.files;

import java.io.Serializable;

public class Component implements Serializable
{
	private String filepath;
	private int versionNumber;
	private String author;
	private boolean checked;
	private int id;
	
	public Component(String f, int i, String a, boolean c, int id)
	{
		this.setFilepath(f);
		this.setVersionNumber(i);
		this.setAuthor(a);
		this.setChecked(c);
		this.setId(id);
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public int getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(int id) {
		this.versionNumber = id;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
