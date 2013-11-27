package br.uff.pse.destroythenuduhake.game.control;

import java.io.Serializable;

import br.uff.pse.destroythenuduhake.dtn.Author;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;

public abstract class Asset implements Disposable,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -387636279654201957L;
	
	/**
	 * Um número aleatório identificador do asset. Dois assets que têm a mesma origem mantém 
	 * esse serialNumber ao longo da sua vida
	 */
	private int serialNumber;
	private AssetID id;
	private int versionNumber;
	private Author author;
	private String filePath;
	private boolean original;
	
	public Asset(AssetID id, String filePath){
		this.id = id;
		this.versionNumber = 0;
		this.author = Author.getBuiltin();
		this.setFilePath(filePath);
		this.original = true;
	}
	
	protected Asset(AssetID id,String filePath, Author author){
		this.serialNumber = (int)(Math.random() * Integer.MAX_VALUE);
		this.id = id;
		this.versionNumber = 0;
		this.author = author;
		this.setFilePath(filePath);
		this.original = false;
	}
	
	public abstract void load();
	public abstract void dispose();
	public abstract Asset makeCopy(Author author, String newPath);
	
	protected void markModification(){
		versionNumber++;
	}
	
	public AssetID getId(){
		return id;
	}
	
	public int getVersionNumber(){
		return versionNumber;
	}
	
	public Author getAuthor(){
		return author;
	}
	
	protected FileHandle getFileHandle(){
		if(isOriginal())
			return Gdx.files.internal(getFilePath());
		return Gdx.files.absolute(getFilePath());
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public boolean isOriginal() {
		return original;
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Asset))
			return false;
		
		Asset other = (Asset)o;
		
		//se tiverem origens diferentes, não são iguais
		if(this.isOriginal() != other.isOriginal())
			return false;
		
		//se ambos forem originais, é só comparar os IDs
		if(this.isOriginal() && other.isOriginal())
			return this.id.equals(other.id);
		
		//se algum não for original, verifica se os IDs batem
		if(!this.id.equals(other.id))
			return false;
		
		//se ambos forem editados, precisam ter o mesmo autor
		if(!this.isOriginal() && !other.isOriginal() && !this.author.equals(other.author))
			return false;
		
		//pelo menos um editado. IDs iguais. Sem conflito de autor. Verificar se o SerialNumber confere
		return this.serialNumber == other.serialNumber;
	}

}
