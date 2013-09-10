package br.uff.pse.files;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import de.tubs.ibr.dtn.util.Base64.OutputStream;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;


public class FileManager extends Activity
{
	// Lista que conter� o nome dos assets
	private static ArrayList<String> filesPaths = new ArrayList<String>();
	private static String assetFilePath ="/data/data/br.uff.pse.dest/assets/";
	
	public static void writeAsset(Asset asset, String fileName, Context ctx) 
	{
		loadListFile(ctx);
		fileName = writeValidation(fileName,ctx);					
		try
		{
			FileOutputStream fOut = ctx.openFileOutput(fileName, Context.MODE_PRIVATE);
			BufferedOutputStream buffer = new BufferedOutputStream (fOut);
			ObjectOutput output = new ObjectOutputStream ( buffer);
			try
			{													
						output.writeObject(asset);	
						filesPaths.add(fileName);
						saveListFile(ctx);
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
		Asset asset = null;
		try
		{
		      //use buffering
		      FileInputStream file = ctx.openFileInput(fileName);
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
		ctx.deleteFile(fileName);
		filesPaths.remove(fileName);
		saveListFile(ctx);
	}
	public static void deleteAllFiles(Context ctx)
	{
		loadListFile(ctx);
		for(int i = 0; i < filesPaths.size() ; i++)
		{
			ctx.deleteFile(filesPaths.get(i));
		}
		filesPaths.clear();
		saveListFile(ctx);
	}
	public static String writeValidation(String filename,Context ctx)
	{
		loadListFile(ctx);
		for(int i = 0;i<filesPaths.size();i++)
		{
			if(filename.equals(filesPaths.get(i)))		
			{
				return writeValidation(filename+"(Copy)",ctx);
			}
		}
		return filename;
	}
	public static String[] readAllFilesNames(Context ctx)
	{		
		loadListFile(ctx);
		String[] array = filesPaths.toArray(new String[filesPaths.size()]);
		return array;
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
		ArrayList<String> list = null;
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
	

	


}
