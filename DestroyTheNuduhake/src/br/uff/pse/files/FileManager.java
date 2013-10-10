package br.uff.pse.files;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import br.uff.pse.destroythenuduhake.dtn.BundleReceiver;
import br.uff.pse.destroythenuduhake.game.assets.AssetIDs;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.Asset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.interfacepk.Header;
import br.uff.pse.destroythenuduhake.interfacepk.Item;
import br.uff.pse.destroythenuduhake.interfacepk.ListItem;


public class FileManager extends Activity implements BundleReceiver
{
	// Lista que conterï¿½ o nome dos assets
	//private static ArrayList<Component> filesPaths = new ArrayList<Component>();
	private static ArrayList<Asset> filesPaths = new ArrayList<Asset>();
	private static ArrayList<Boolean> checkedAssets = new ArrayList<Boolean>();
	//private static String assetFilePath ="/data/data/br.uff.pse.dest/assets/";

	public static void writeAsset(Asset asset,  Context ctx) 
	{
		loadListFile(ctx);
		//loadCheckListFile(ctx);
		//String fileName = writeValidation(asset.getFilePath(),ctx,1);	
		//String fileName = asset.getFilePath();
	
			//FileOutputStream fOut = ctx.openFileOutput(fileName, Context.MODE_PRIVATE);
		//	FileOutputStream fOut = new FileOutputStream(fileName);
		//	BufferedOutputStream buffer = new BufferedOutputStream (fOut);
		//	ObjectOutput output = new ObjectOutputStream ( buffer);
			try
			{													
		//				output.writeObject(asset);	
		//				Component cmp = new Component(asset.getFilePath(),asset.getVersionNumber(),asset.getAuthor(),asset.isDefault(),asset.getId());
						filesPaths.add(asset);
					//	if(asset.type.equals("Default"))
					//		checkedAssets.add(true);
					//	else
					//		checkedAssets.add(false);
						saveListFile(ctx);
					//	saveCheckListFile(ctx);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				//output.close();
			}

		
		
		
	}
	public static Asset readAsset(String fileName, Context ctx) 
	{
		loadListFile(ctx);
		loadCheckListFile(ctx);
		Asset asset = null;
		try
		{
		      //use buffering
		     // FileInputStream file = ctx.openFileInput(fileName);
			  FileInputStream file = new FileInputStream(fileName);
		      BufferedInputStream buffer = new BufferedInputStream( file );
		      ObjectInput input = new ObjectInputStream ( buffer );
		      try
		      {
		        asset = (Asset)input.readObject();
		        
		      }
		      finally
		      {
		        input.close();
		      }
	    }
	    catch(ClassNotFoundException ex)
	    {
	      ex.printStackTrace();
	    }
	    catch(IOException ex)
	    {
	     ex.printStackTrace();
	    }
		return asset;
		
		
	}
	public static void deleteAsset(Asset a,Context ctx)
	{
		loadListFile(ctx);
	/*	loadCheckListFile(ctx);
		int pos = getAssetListPosition(fileName);
		if(pos != -1)
		{
			//checkedAssets.remove(pos);
			//ctx.deleteFile(fileName);
			File f = new File(fileName);
			f.delete();
		//	filesPaths.remove(fileName);
			filesPaths.remove(pos);
		}
		saveListFile(ctx);
	//	saveCheckListFile(ctx);
 */
		for(int i = 0; i<filesPaths.size() ; i++)
		{
			if(filesPaths.get(i).equals(a))
			{
				File f = new File(a.getDataFilePath());
				f.delete();
				filesPaths.remove(i);
			}
		}
	 
	}
/*	public static int getAssetListPosition(String filename)
	{
		for(int i = 0 ; i < filesPaths.size();i++)
		{
			if(filesPaths.get(i).getDataFilePath().equals(filename))
				return i;
		}
		return -1;
	}
	*/
	public static void deleteAllFiles(Context ctx)
	{
		loadListFile(ctx);
		for(int i = 0; i < filesPaths.size() ; i++)
		{
		//	ctx.deleteFile(filesPaths.get(i).getFilepath());
			File f = new File(filesPaths.get(i).getDataFilePath());
			f.delete();
		}
		filesPaths.clear();
		//checkedAssets.clear();
		saveListFile(ctx);
		//saveCheckListFile(ctx);
	}
	/*public static String writeValidation(String filename,Context ctx, int num)
	{
		loadListFile(ctx);
		for(int i = 0;i<filesPaths.size();i++)
		{
			if(filename.equals(filesPaths.get(i)))		
			{
				if(num ==1)
					return writeValidation(filename+"("+num+")",ctx,++num);
				else
				{
					try
					{
					String file = filename.substring(0, filename.indexOf("("));
					return writeValidation(file+"("+num+")",ctx,++num);
					}
					catch(Exception e)
					{
						System.out.print(e);
					}
					
				}
			}
		}
		return filename;
	}
	*/
	public static ArrayList<Item> readAllFilesNames(Context ctx)
	{		
		loadListFile(ctx);
		loadCheckListFile(ctx);
		ArrayList<Item> items = new ArrayList<Item>();
		ArrayList<Item> hero = new ArrayList<Item>();
		ArrayList<Item> shell = new ArrayList<Item>();
		ArrayList<Item> ground = new ArrayList<Item>();
		ArrayList<Item> dragon = new ArrayList<Item>();
		hero.add(new Header("Heroes"));
		shell.add(new Header("Shells"));
		ground.add(new Header("Grounds"));
		dragon.add(new Header("Dragons"));
		for(int i = 0; i< filesPaths.size();i++)
		{
 
			if(filesPaths.get(i).getId() == AssetIDs.SPRITE_MARIO)
				hero.add(new ListItem(filesPaths.get(i),filesPaths.get(i).isDefault()));
			if(filesPaths.get(i).getId() == AssetIDs.SPRITE_SHELL)
				shell.add(new ListItem(filesPaths.get(i),filesPaths.get(i).isDefault()));
			if(filesPaths.get(i).getId() == AssetIDs.SPRITE_GROUND)
				ground.add(new ListItem(filesPaths.get(i),filesPaths.get(i).isDefault()));
			if(filesPaths.get(i).getId() == AssetIDs.SPRITE_DRAGON)
				dragon.add(new ListItem(filesPaths.get(i),filesPaths.get(i).isDefault()));
	//		if(a.type.equals("Ombreira"))
	//			ombreiras.add(new ListItem(a.author,filesPaths.get(i),ctx));
	//		if(a.type.equals("Terreno"))
	//			terrenos.add(new ListItem(a.author,filesPaths.get(i),ctx));
		}
	/*	items.add(new Header("Terrain Asset"));
        items.add(new ListItem("Default", "Terrain"));
        items.add(new ListItem("Zï¿½zinho", "Terreno de fogo"));
        items.add(new ListItem("Jurema", "Terreno de gelo"));

		items.add(new Header("Enemy Asset"));
        items.add(new ListItem("Default", "Enemy"));
        items.add(new ListItem("Zï¿½zinho", "Inimigo de fogo"));
        items.add(new ListItem("Jurema", "Inimigo de gelo"));

		items.add(new Header("Hero Asset"));
        items.add(new ListItem("Default", "Hero"));
        items.add(new ListItem("Clï¿½udio", "Herï¿½i boladï¿½o"));
        items.add(new ListItem("Jeremias", "Obi Juan"));
        items.add(new ListItem("Francis", "Megaman"));

		items.add(new Header("Background Asset"));
        items.add(new ListItem("Default", "Background"));
        items.add(new ListItem("Emanuelle", "Chaos"));
        items.add(new ListItem("Maximillian", "Armageddon"));
    */    

		ArrayList<Item> combined = new ArrayList<Item>();
		combined.addAll(hero);
		combined.addAll(shell);
		combined.addAll(ground);
		combined.addAll(dragon);
		return combined;
	}
	public static void saveListFile(Context ctx)
	{
		try
		{
			FileOutputStream fOut = ctx.openFileOutput("SpecialListArchive", Context.MODE_PRIVATE);
			BufferedOutputStream buffer = new BufferedOutputStream (fOut);
			ObjectOutput output = new ObjectOutputStream ( buffer);
			try
			{													
						output.writeObject(filesPaths);	
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				output.close();
			}
		}			
		catch(IOException ex)
		{
		    ex.printStackTrace();
		}	
	}
	public static void loadListFile(Context ctx)
	{
		ArrayList<Asset> list = new ArrayList<Asset>();
		try
		{
		      //use buffering
		      FileInputStream file = ctx.openFileInput("SpecialListArchive");
		      BufferedInputStream buffer = new BufferedInputStream( file );
		      ObjectInput input = new ObjectInputStream ( buffer );
		      try
		      {
		        list = (ArrayList<Asset>) input.readObject();
		      }
		      finally
		      {
		        input.close();
		      }
	    }
	    catch(ClassNotFoundException ex)
	    {
	      ex.printStackTrace();
	    }
	    catch(IOException ex)
	    {
	     ex.printStackTrace();
	    }
		filesPaths = list;
		
	}
	public static void saveCheckListFile(Context ctx)
	{
		try
		{
			FileOutputStream fOut = ctx.openFileOutput("CheckListArchive", Context.MODE_PRIVATE);
			BufferedOutputStream buffer = new BufferedOutputStream (fOut);
			ObjectOutput output = new ObjectOutputStream ( buffer);
			try
			{													
						output.writeObject(checkedAssets);	
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				output.close();
			}
		}			
		catch(IOException ex)
		{
		    ex.printStackTrace();
		}	
	}
	public static void loadCheckListFile(Context ctx)
	{
		ArrayList<Boolean> list = new ArrayList<Boolean>();
		try
		{
		      //use buffering
		      FileInputStream file = ctx.openFileInput("CheckListArchive");
		      BufferedInputStream buffer = new BufferedInputStream( file );
		      ObjectInput input = new ObjectInputStream ( buffer );
		      try
		      {
		        list = (ArrayList<Boolean>) input.readObject();
		      }
		      finally
		      {
		        input.close();
		      }
	    }
	    catch(ClassNotFoundException ex)
	    {
	      ex.printStackTrace();
	    }
	    catch(IOException ex)
	    {
	     ex.printStackTrace();
	    }
		checkedAssets = list;
		
	}
	/*
	public static boolean getCheckOptionFromFile(String fileName,Context ctx)
	{
		loadListFile(ctx);
		loadCheckListFile(ctx);
		int pos = getAssetListPosition(fileName);
		if(pos != -1)
		{
			return checkedAssets.get(pos);
		}
		return false;
		
	}
	*/
	@Override
	public void onReceive(AssetBundle[] bundles) {
		// TODO Auto-generated method stub
		
	}
	public static byte[] prepareContentToSend(Asset c)
	{
		//byte[] 0 a 1023 vai ter o Content, o resto será a imagem
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		byte[] cBytes = new byte[1024];
		byte[] bmBytes = null;
		try 
		{
		  out = new ObjectOutputStream(bos);   
		  out.writeObject(c);
		  cBytes = bos.toByteArray();
		  byte[] intBytes = ByteBuffer.allocate(4).putInt(cBytes.length).array();
		  int x = byteArrayToInt(intBytes);
		  if( c instanceof GraphicAsset)
			  bmBytes = ((GraphicAsset) c).getBitmapBytes();
		  else
		  {
			//pegar bytes do som
		  }
			  
		  
		  byte[] retBytes = new byte[cBytes.length + bmBytes.length + intBytes.length];
		  System.arraycopy(intBytes, 0, retBytes, 0, intBytes.length);
		  System.arraycopy(cBytes, 0, retBytes, intBytes.length, cBytes.length);
		  System.arraycopy(bmBytes, 0, retBytes, intBytes.length + cBytes.length, bmBytes.length);
		  return retBytes;
		
		}
		catch(Exception e)
		{
			
		}
		
		return null;
	}
	public static void writeAssetFromBytes(byte[] b, Context ctx)
	{
		byte[] tam = new byte[4];
		tam[0] = b[0];
		tam[1] = b[1];
		tam[2] = b[2];
		tam[3] = b[3];
		int contentSize = byteArrayToInt(tam);
		byte[] contentBytes = new byte[contentSize];
		System.arraycopy(b, 4 , contentBytes, 0, contentBytes.length);
		byte[] bitMapBytes = new byte[b.length - contentSize - 4];
		System.arraycopy(b, contentSize + 4 , bitMapBytes, 0, b.length - contentSize - 4);
		
		try
		{
			ByteArrayInputStream bos = new ByteArrayInputStream(contentBytes);
			ObjectInputStream ois = new ObjectInputStream(bos);
			Asset c = (Asset) ois.readObject();
			
			if(c instanceof GraphicAsset)
			{
				Bitmap bitmap = BitmapFactory.decodeByteArray(bitMapBytes , 0, bitMapBytes.length);
				((GraphicAsset) c).setBitmap(bitmap);
			}
			else //fazer as coisas pro Audio Asset
			{
				
			}
			FileManager.writeAsset(c, ctx);
		
		}
		catch(Exception e)
		{
			
		}
		
		
		
	}
	public static Asset getAssetFromBytes(byte[] b, Context ctx)
	{
		byte[] tam = new byte[4];
		tam[0] = b[0];
		tam[1] = b[1];
		tam[2] = b[2];
		tam[3] = b[3];
		int contentSize = byteArrayToInt(tam);
		byte[] contentBytes = new byte[contentSize];
		System.arraycopy(b, 4 , contentBytes, 0, contentBytes.length);
		byte[] bitMapBytes = new byte[b.length - contentSize - 4];
		System.arraycopy(b, contentSize + 4 , bitMapBytes, 0, b.length - contentSize - 4);
		
		try
		{
			ByteArrayInputStream bos = new ByteArrayInputStream(contentBytes);
			ObjectInputStream ois = new ObjectInputStream(bos);
			Asset c = (Asset) ois.readObject();
			if(c instanceof GraphicAsset)
			{
				Bitmap bitmap = BitmapFactory.decodeByteArray(bitMapBytes , 0, bitMapBytes.length);
				((GraphicAsset) c).setBitmap(bitmap);
			}
			else
			{
				//setar o som
			}
			return c;
		
		}
		catch(Exception e)
		{
			
		}
		return null;
		
		
		
	}
	public static int byteArrayToInt(byte[] b) 
	{
	    return   b[3] & 0xFF |
	            (b[2] & 0xFF) << 8 |
	            (b[1] & 0xFF) << 16 |
	            (b[0] & 0xFF) << 24;
	}
	public static void setAssetChecked(Asset a)
	{

	}
	

	


}
