package br.uff.pse.files;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.app.LauncherActivity.ListItem;
import android.content.Context;
import br.uff.pse.destroythenuduhake.dtn.BundleReceiver;
import br.uff.pse.destroythenuduhake.game.control.Asset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;
import br.uff.pse.destroythenuduhake.interfacepk.Header;
import br.uff.pse.destroythenuduhake.interfacepk.Item;


public class FileManager extends Activity implements BundleReceiver
{
	// Lista que conter� o nome dos assets
	private static ArrayList<String> filesPaths = new ArrayList<String>();
	private static ArrayList<Boolean> checkedAssets = new ArrayList<Boolean>();
	//private static String assetFilePath ="/data/data/br.uff.pse.dest/assets/";

	public static void writeAsset(Asset asset,  Context ctx) 
	{
		loadListFile(ctx);
		loadCheckListFile(ctx);
		//String fileName = writeValidation(asset.getFilePath(),ctx,1);	
		String fileName = asset.getFilePath();
		try
		{
			//FileOutputStream fOut = ctx.openFileOutput(fileName, Context.MODE_PRIVATE);
			FileOutputStream fOut = new FileOutputStream(fileName);
			BufferedOutputStream buffer = new BufferedOutputStream (fOut);
			ObjectOutput output = new ObjectOutputStream ( buffer);
			try
			{													
						output.writeObject(asset);	
						filesPaths.add(fileName);
					//	if(asset.type.equals("Default"))
					//		checkedAssets.add(true);
					//	else
					//		checkedAssets.add(false);
						saveListFile(ctx);
						saveCheckListFile(ctx);
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
	public static void deleteAsset(String fileName,Context ctx)
	{
		loadListFile(ctx);
		loadCheckListFile(ctx);
		int pos = getAssetListPosition(fileName);
		if(pos != -1)
		{
			checkedAssets.remove(pos);
			ctx.deleteFile(fileName);
		//	filesPaths.remove(fileName);
			filesPaths.remove(pos);
		}
		saveListFile(ctx);
		saveCheckListFile(ctx);
	}
	public static int getAssetListPosition(String filename)
	{
		for(int i = 0 ; i < filesPaths.size();i++)
		{
			if(filesPaths.get(i).equals(filename))
				return i;
		}
		return -1;
	}
	public static void deleteAllFiles(Context ctx)
	{
		loadListFile(ctx);
		for(int i = 0; i < filesPaths.size() ; i++)
		{
			ctx.deleteFile(filesPaths.get(i));
		}
		filesPaths.clear();
		checkedAssets.clear();
		saveListFile(ctx);
		saveCheckListFile(ctx);
	}
	public static String writeValidation(String filename,Context ctx, int num)
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
	public static ArrayList<Item> readAllFilesNames(Context ctx)
	{		
		loadListFile(ctx);
		loadCheckListFile(ctx);
		ArrayList<Item> items = new ArrayList<Item>();
		ArrayList<Item> capacetes = new ArrayList<Item>();
		ArrayList<Item> ombreiras = new ArrayList<Item>();
		ArrayList<Item> terrenos = new ArrayList<Item>();
		capacetes.add(new Header("Capacetes"));
		ombreiras.add(new Header("Ombreiras"));
		terrenos.add(new Header("Terrenos"));
		for(int i = 0; i< filesPaths.size();i++)
		{
	//		Asset a = readAsset(filesPaths.get(i),ctx); 
	//		if(a.type.equals("Capacete"))
	//			capacetes.add(new ListItem(a.author,filesPaths.get(i),ctx));
	//		if(a.type.equals("Ombreira"))
	//			ombreiras.add(new ListItem(a.author,filesPaths.get(i),ctx));
	//		if(a.type.equals("Terreno"))
	//			terrenos.add(new ListItem(a.author,filesPaths.get(i),ctx));
		}
	/*	items.add(new Header("Terrain Asset"));
        items.add(new ListItem("Default", "Terrain"));
        items.add(new ListItem("Z�zinho", "Terreno de fogo"));
        items.add(new ListItem("Jurema", "Terreno de gelo"));

		items.add(new Header("Enemy Asset"));
        items.add(new ListItem("Default", "Enemy"));
        items.add(new ListItem("Z�zinho", "Inimigo de fogo"));
        items.add(new ListItem("Jurema", "Inimigo de gelo"));

		items.add(new Header("Hero Asset"));
        items.add(new ListItem("Default", "Hero"));
        items.add(new ListItem("Cl�udio", "Her�i bolad�o"));
        items.add(new ListItem("Jeremias", "Obi Juan"));
        items.add(new ListItem("Francis", "Megaman"));

		items.add(new Header("Background Asset"));
        items.add(new ListItem("Default", "Background"));
        items.add(new ListItem("Emanuelle", "Chaos"));
        items.add(new ListItem("Maximillian", "Armageddon"));
    */    

		ArrayList<Item> combined = new ArrayList<Item>();
		combined.addAll(capacetes);
		combined.addAll(ombreiras);
		combined.addAll(terrenos);
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
		ArrayList<String> list = new ArrayList<String>();
		try
		{
		      //use buffering
		      FileInputStream file = ctx.openFileInput("SpecialListArchive");
		      BufferedInputStream buffer = new BufferedInputStream( file );
		      ObjectInput input = new ObjectInputStream ( buffer );
		      try
		      {
		        list = (ArrayList<String>) input.readObject();
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
	@Override
	public void onReceive(AssetBundle[] bundles) {
		// TODO Auto-generated method stub
		
	}
	

	


}
