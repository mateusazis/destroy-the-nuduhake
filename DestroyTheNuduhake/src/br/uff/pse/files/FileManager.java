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
import java.util.HashMap;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import br.uff.pse.destroythenuduhake.R;
import br.uff.pse.destroythenuduhake.game.assets.AssetDatabase;
import br.uff.pse.destroythenuduhake.game.assets.GraphicAsset;
import br.uff.pse.destroythenuduhake.game.control.Asset;

import br.uff.pse.destroythenuduhake.game.control.AssetID;
import br.uff.pse.destroythenuduhake.interfacepk.Header;
import br.uff.pse.destroythenuduhake.interfacepk.Item;
import br.uff.pse.destroythenuduhake.interfacepk.ListItem;


public class FileManager extends Activity 
{

	public static final String REFRESH = "br.uff.pse.destroythenudhuhake.REFRESH";

	public static void addAsset(Asset a, Context ctx){
		ArrayList<Asset> list = loadListFile(ctx);

		Asset asset = FileManager.checkAndReturnIfAssetExists(a, ctx);
		if(asset == null)
		{
			list.add(a);
		}
		else
		{
		int pos = list.indexOf(asset);
		list.set(pos,a);

		}
		saveListFile(list,ctx);
		Intent i = new Intent(REFRESH);
		ctx.sendBroadcast(i);

		  
	}
	

	public static Asset checkAndReturnIfAssetExists(Asset a,Context ctx)
	{
		ArrayList<Asset> list = loadListFile(ctx);
	
		int pos = list.indexOf(a);
		if(pos != -1)
			return list.get(pos);
		return null;
		
	}

	public static void deleteAsset(Asset a,Context ctx)
	{
		ArrayList<Asset> list = loadListFile(ctx);

		int pos = list.indexOf(a);
		if(pos != -1)
		{
			File f = new File(a.getFilePath());
			f.delete();
			list.remove(pos);
		}
		saveListFile(list,ctx);
	 
	}

	public static void deleteAllFiles(Context ctx)
	{
		ArrayList<Asset> list = loadListFile(ctx);
		for(int i = 0; i < list.size() ; i++)
		{
		//	ctx.deleteFile(filesPaths.get(i).getFilepath());
			File f = new File(list.get(i).getFilePath());
			f.delete();
		}
		list.clear();
		//checkedAssets.clear();
		saveListFile(list,ctx);
		//saveCheckListFile(ctx);
	}

	public static ArrayList<Item> readAllFilesNames(Context ctx)
	{		
		ArrayList<Asset> list = loadListFile(ctx);
//		loadCheckListFile(ctx);
		Asset[] builtins = AssetDatabase.getEditableBuiltinAssets();
		//fazemos um mapa associando cada AssetID a uma lista de assets com esse ID
		HashMap<AssetID, ArrayList<Item>> itemListMap = new HashMap<AssetID, ArrayList<Item>>();

		for(int i = 0; i< list.size();i++)
		{
			Asset current = list.get(i);
			AssetID id = current.getId();
			Bitmap b = null;
			if(current instanceof GraphicAsset)
				b = ((GraphicAsset)current).getBitmap(ctx);
			else
				b = BitmapFactory.decodeResource(ctx.getResources(),R.drawable.speakericon);
			
			ArrayList<Item> myList;
			if(!itemListMap.containsKey(id)){
				//se for um tipo de asset inédito, crie uma lista nova pra ele
				myList = new ArrayList<Item>();
				itemListMap.put(id, myList);
				//e adiciona a ela um header igual ao nome do ID
				myList.add(new Header(id.getName()));
			}
			else{
				//senão, já existem assets desse tipo. Apenas pega a lista existente
				myList = itemListMap.get(id);
			}
			
			//adiciona o asset na lista (AINDA TEM QUE VER O CASO DE NÃO SER GRAPHIC ASSET!)
			myList.add(new ListItem(current,current.isOriginal(),b));
			
	//		if(a.type.equals("Ombreira"))
	//			ombreiras.add(new ListItem(a.author,filesPaths.get(i),ctx));
	//		if(a.type.equals("Terreno"))
	//			terrenos.add(new ListItem(a.author,filesPaths.get(i),ctx));
		}
		for(int i = 0;i<builtins.length;i++)
		{
			Asset current = builtins[i];
			AssetID id = current.getId();
			Bitmap b = null;
			if(current instanceof GraphicAsset)
				b = ((GraphicAsset)current).getBitmap(ctx);
			else
				b = BitmapFactory.decodeResource(ctx.getResources(),R.drawable.speakericon);
			ArrayList<Item> myList;
			if(!itemListMap.containsKey(id)){
				//se for um tipo de asset inédito, crie uma lista nova pra ele
				myList = new ArrayList<Item>();
				itemListMap.put(id, myList);
				//e adiciona a ela um header igual ao nome do ID
				myList.add(new Header(id.getName()));
			}
			else{
				//senão, já existem assets desse tipo. Apenas pega a lista existente
				myList = itemListMap.get(id);
			}
			
			//adiciona o asset na lista (AINDA TEM QUE VER O CASO DE NÃO SER GRAPHIC ASSET!)
			myList.add(new ListItem(current,current.isOriginal(),b));
			

		}


		ArrayList<Item> combined = new ArrayList<Item>();
		
		//percorre cada lista criada e junta na listona
		for(ArrayList<Item> itemList : itemListMap.values())
			combined.addAll(itemList);
		
		return combined;
	}

	public static void saveListFile(ArrayList<Asset> listToSave,Context ctx)
	{
		try
		{
			FileOutputStream fOut = ctx.openFileOutput("SpecialListArchive", Context.MODE_PRIVATE);
			BufferedOutputStream buffer = new BufferedOutputStream (fOut);
			ObjectOutput output = new ObjectOutputStream ( buffer);
			try
			{													
						//output.writeObject(filesPaths);	
						output.writeObject(listToSave);	
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
	public static ArrayList<Asset> loadListFile(Context ctx)
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
		return list;

	}



	public static int byteArrayToInt(byte[] b) 
	{
	    return   b[3] & 0xFF |
	            (b[2] & 0xFF) << 8 |
	            (b[1] & 0xFF) << 16 |
	            (b[0] & 0xFF) << 24;
	}


	public static byte[] convertAssetToBytes(Asset c, Context ctx)
	{
		//byte[] 0 a 1023 vai ter o Content, o resto ser� a imagem
		
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
		  if(c instanceof GraphicAsset)
			  bmBytes = ((GraphicAsset) c).getBitmapBytes(ctx);
		  else
		  {
			  //transformar audio em bytes
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
	public static Asset getAssetFromBytes(byte[] b,Context ctx)
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
			
			
			Asset similarAsset = checkAndReturnIfAssetExists(c,ctx) ;
			//if(similarAsset == null)
			//{

				if(c instanceof GraphicAsset)
				{
					c.setFilePath(getAvaiableFilepath(ctx,ctx.getFilesDir().getAbsolutePath(),true));
					Bitmap bitmap = BitmapFactory.decodeByteArray(bitMapBytes , 0, bitMapBytes.length);
					((GraphicAsset) c).setBitmap(bitmap);
				}
				else
				{
					c.setFilePath(getAvaiableFilepath(ctx,ctx.getFilesDir().getAbsolutePath(),false));
					//RECUPERAR AUDIO
				}

				return c;

		
		}
		catch(Exception e)
		{
		}

		return null;
				
	}
	public static void replaceAssetFromList(Asset a,Context ctx)
	{
		ArrayList<Asset> list = loadListFile(ctx);
		for(int i = 0; i < list.size();i++)
		{
			Asset oldAsset = list.get(i);
			if(oldAsset.equals(a))
			{
				a.setFilePath(oldAsset.getFilePath());				
				list.set(i, a);
			}
		}
		saveListFile(list,ctx);
		
	}
	public static synchronized String writeValidation(String type,int num,Context ctx,String dirPath,boolean isGraphic)
	{
		
	
		try
		{
			ArrayList<Asset> list = loadListFile(ctx);
		String fp = dirPath;
		for(int i = 0; i < list.size();i++)
		{
			String fname = list.get(i).getFilePath().substring(0, list.get(i).getFilePath().lastIndexOf("."));
			if( fname.equals(fp+"/"+type))
			{
				if(num == 0 )
					return writeValidation(type+"("+(num+1)+")",++num,ctx,dirPath,isGraphic);
				else
				{
					String newType = type.substring(0, type.indexOf("("));
					return writeValidation(newType+"("+(num+1)+")",++num,ctx,dirPath,isGraphic);
				}
			}
		}
		
		if(isGraphic)
			return fp+"/"+type+".png";
		else
			return fp+"/"+type+".mp3";
		}
		catch(Exception e)
		{
			
		}
		return null;
	}
	public static synchronized String getAvaiableFilepath(Context ctx,String dirPath,boolean isGraphic)
	{
		
		return writeValidation("NuduhakeFile",0,ctx,dirPath,isGraphic);
	}
//	public static void setContext(Context c) {
//		ctx = c;
//		
//	}
	

	


}
