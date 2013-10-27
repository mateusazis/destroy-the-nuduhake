package br.uff.pse.destroythenuduhake.dtn;

import java.io.Serializable;

public class Author implements Serializable{
	private static final long serialVersionUID = -1876735404383325492L;
	private static Author builtin;
	
	private String name;
	private String deviceID;
	
	private Author(){
		name = "Built-in";
		deviceID = null;
	}
	
	public Author(String name, String deviceID){
		this.name = name;
		this.deviceID = deviceID;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Author))
			return false;
		Author other = (Author)o;
		return this.deviceID.equals(other.deviceID);
	}
	
	public static Author getBuiltin(){
		if(builtin == null)
			builtin = new Author();
		return builtin;
	}
}
