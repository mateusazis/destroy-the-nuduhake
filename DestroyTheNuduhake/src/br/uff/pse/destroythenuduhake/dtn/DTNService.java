package br.uff.pse.destroythenuduhake.dtn;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




import br.uff.pse.destroythenuduhake.DisplayAssetsActivity;
import br.uff.pse.destroythenuduhake.game.control.Asset;
import br.uff.pse.destroythenuduhake.game.control.AssetBundle;



import br.uff.pse.files.FileManager;
import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import de.tubs.ibr.dtn.api.Block;
import de.tubs.ibr.dtn.api.Bundle;
import de.tubs.ibr.dtn.api.Bundle.ProcFlags;
import de.tubs.ibr.dtn.api.BundleID;
import de.tubs.ibr.dtn.api.DTNClient;
import de.tubs.ibr.dtn.api.DTNClient.Session;
import de.tubs.ibr.dtn.api.DataHandler;
import de.tubs.ibr.dtn.api.GroupEndpoint;
import de.tubs.ibr.dtn.api.Node;
import de.tubs.ibr.dtn.api.Registration;
import de.tubs.ibr.dtn.api.ServiceNotAvailableException;
import de.tubs.ibr.dtn.api.SessionConnection;
import de.tubs.ibr.dtn.api.SessionDestroyedException;
import de.tubs.ibr.dtn.api.SingletonEndpoint;
import de.tubs.ibr.dtn.api.TransferMode;

public class DTNService extends IntentService implements ShareService
{

	// This TAG is used to identify this class (e.g. for debugging)
	private static final String TAG = "InfoService";

	// mark a specific bundle as delivered
	public static final String MARK_DELIVERED_INTENT = "uff.br.infouffdtn.MARK_DELIVERED";

	// process a status report
	public static final String REPORT_DELIVERED_INTENT = "uff.br.infouffdtn.REPORT_DELIVERED";

	// this intent send out a PING message
	public static final String SEND_BUNDLE_INTENT = "uff.br.infouffdtn.PING";

	// indicates updated data to other components
	public static final String DATA_UPDATED = "uff.br.infouffdtn.DATA_UPDATED";
	public static final String PAYLOAD_UPDATED = "uff.br.infouffdtn.PAYLOAD_UPDATED";
	public static final String REFRESH = "uff.br.infouffdtn.REFRESH";
	// group EID of this app
	public static final GroupEndpoint PING_GROUP_EID = new GroupEndpoint("dtn://broadcast.dtn/ping");

	// This is the object that receives interactions from clients. See
	// RemoteService for a more complete example.
	private final IBinder mBinder = new LocalBinder();

	// The communication with the DTN service is done using the DTNClient
	private DTNClient mClient = null;


	public DTNService()
	{
		super(TAG);
	}

	/**
	 * Class for clients to access. Because we know this service always runs in
	 * the same process as its clients, we don't need to deal with IPC.
	 */
	public class LocalBinder extends Binder
	{
		public DTNService getService()
		{
			return DTNService.this;
		}
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return mBinder;
	}

	public GroupEndpoint getGroupEndpoint()
	{
		return PING_GROUP_EID;
	}

	private String getLocalEndpoint()
	{
		try
		{
			if (mClient != null)
			{
				if (mClient.getDTNService() == null)
					return null;
				return mClient.getDTNService().getEndpoint();
			}
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	private synchronized void sendContent()
	{

		
			ArrayList<Asset> files = FileManager.getFilesToSend(this);
		
		
			try
			{
				List<Node> neighbours = mClient.getDTNService().getNeighbors();
				for (int i = 0; i < neighbours.size(); i++)
				{
					String destAddress = neighbours.get(i).endpoint.toString() + "/example-app";
					SingletonEndpoint destination = new SingletonEndpoint(destAddress);
	
					// create a new bundle
					Bundle b = new Bundle();
	
					// set the destination of the bundle
					b.setDestination(destination);
	
					// limit the lifetime of the bundle to 60 seconds
					b.setLifetime(120L);
					
	
					// set status report requests for bundle reception
					 b.set(ProcFlags.REQUEST_REPORT_OF_BUNDLE_RECEPTION, true);
	
					// set destination for status reports
					 b.setReportto(SingletonEndpoint.ME);
	
					// generate some payload
					

					
	
					try
					{
						
						// get the DTN session
						Session s = mClient.getSession();
	

	
						// send the bundle
						//BundleID ret = s.send(b, payload.getBytes());
						
						for(int j = 0 ; j < files.size(); j++)
						{
							
							try 
							{
							  byte[] contentBytes = FileManager.prepareAssetToSend(files.get(j),this);
							  BundleID ret = s.send(b, contentBytes);
							  
							  	if (ret == null)
								{
									Log.e(TAG, "could not send the message");

								}
								else
								{
									Log.d(TAG, "Bundle sent, BundleID: " + ret.toString());

									
								}
							} 
							catch(Exception e)
							{
								
							}
							finally
							{

							}													
						}
						
					}
					catch (SessionDestroyedException e)
					{
						Log.e(TAG, "could not send the message", e);
						
					}
					catch (InterruptedException e)
					{
						Log.e(TAG, "could not send the message", e);
						
					}
				}
			}
			catch (Exception e)
			{
	
			}
		

	}

	@Override
	protected void onHandleIntent(Intent intent)
	{
		String action = intent.getAction();

		if (de.tubs.ibr.dtn.Intent.RECEIVE.equals(action))
		{
			// Received bundles from the DTN service here
			try
			{
				// We loop here until no more bundles are available
				// (queryNext() returns false)
				while (mClient.getSession().queryNext())
				{
				}
			}
			catch (SessionDestroyedException e)
			{
				Log.e(TAG, "Can not query for bundle", e);
			}
			catch (InterruptedException e)
			{
				Log.e(TAG, "Can not query for bundle", e);
			}
		}
		else
			if (MARK_DELIVERED_INTENT.equals(action))
			{
				// retrieve the bundle ID of the intent
				BundleID bundleid = intent.getParcelableExtra("bundleid");

				try
				{
					// mark the bundle ID as delivered
					mClient.getSession().delivered(bundleid);
				}
				catch (Exception e)
				{
					Log.e(TAG, "Can not mark bundle as delivered.", e);
				}
			}
			else
				if (REPORT_DELIVERED_INTENT.equals(action))
				{
					// retrieve the source of the status report
					SingletonEndpoint source = intent.getParcelableExtra("source");

					// retrieve the bundle ID of the intent
					BundleID bundleid = intent.getParcelableExtra("bundleid");

					Log.d(TAG, "Status report received for " + bundleid.toString() + " from " + source.toString());
				}
				else
					if (SEND_BUNDLE_INTENT.equals(action))
					{
						// retrieve the ping destination
						// SingletonEndpoint destination = new
						// SingletonEndpoint(intent.getStringExtra("destination"));

						// send out the ping
						sendContent();
					}
	}

	SessionConnection mSession = new SessionConnection()
	{

		@Override
		public void onSessionConnected(Session session)
		{
			Log.d(TAG, "Session connected");

			String localeid = getLocalEndpoint();
			if (localeid != null)
			{
				// notify other components of the updated EID
				Intent i = new Intent(DATA_UPDATED);
				i.putExtra("localeid", localeid);
				sendBroadcast(i);
			}
		}

		@Override
		public void onSessionDisconnected()
		{
			Log.d(TAG, "Session disconnected");
		}

	};

	public void preparePayload(String payload)
	{
		// notify other components of the updated EID
		Intent i = new Intent(PAYLOAD_UPDATED);
		i.putExtra("payload", payload);
		sendBroadcast(i);
	}

	@Override
	public void onCreate()
	{
		super.onCreate();

		// create a new DTN client
		mClient = new DTNClient(mSession);

		// create registration with "example-app" as endpoint
		// if the EID of this device is "dtn://device" then the
		// address of this app will be "dtn://device/example-app"
		Registration registration = new Registration("example-app");

		// additionally join a group
		registration.add(PING_GROUP_EID);

		// register own data handler for incoming bundles
		mClient.setDataHandler(mDataHandler);

		try
		{
			// initialize the connection to the DTN service
			mClient.initialize(this, registration);
			Log.d(TAG, "Connection to DTN service established.");
		}
		catch (ServiceNotAvailableException e)
		{
			// The DTN service has not been found
			Log.e(TAG, "DTN service unavailable. Is IBR-DTN installed?", e);
		}
		catch (SecurityException e)
		{
			// The service has not been found
			Log.e(TAG, "The app has no permission to access the DTN service. It is important to install the DTN service first and then the app.", e);
		}
	}

	@Override
	public void onDestroy()
	{
		// terminate the DTN service
		mClient.terminate();
		mClient = null;

		super.onDestroy();
	}

	/**
	 * This data handler is used to process incoming bundles
	 */
	private DataHandler mDataHandler = new DataHandler()
	{

		ByteArrayOutputStream stream = null;
		private Bundle mBundle = null;

		@Override
		public void startBundle(Bundle bundle)
		{
			// store the bundle header locally
			mBundle = bundle;
		}

		@Override
		public void endBundle()
		{
			// complete bundle received
			BundleID received = new BundleID(mBundle);


			
			// mark the bundle as delivered
			Intent i = new Intent(DTNService.this, DTNService.class);
			i.setAction(MARK_DELIVERED_INTENT);
			i.putExtra("bundleid", received);
			startService(i);

			// free the bundle header
			mBundle = null;

			// notify other components of the updated value
			Intent updatedIntent = new Intent(DATA_UPDATED);
			updatedIntent.putExtra("bundleid", received);
			sendBroadcast(updatedIntent);
		}

		@Override
		public TransferMode startBlock(Block block)
		{
			// we are only interested in payload blocks (type = 1)
			if (block.type == 1)
			{
				// return SIMPLE mode to received the payload as "payload()"
				// calls
				stream = new ByteArrayOutputStream();
				return TransferMode.SIMPLE;
			}
			else
			{
				// return NULL to discard the payload of this block
				return TransferMode.NULL;
			}
		}

		@Override
		public void endBlock()
		{
			if (stream != null) {

				try
				{

					
					byte[] streamBytes = stream.toByteArray();

					try 
					{

					  Asset c = FileManager.getAssetFromBytes(streamBytes);
					 
					  FileManager.writeAsset(c, DTNService.this);
					  


					} 
					catch(Exception e)
					{
						
					}
					finally 
					{

					}
				}
				catch(Exception e)
				{
				}
           
		    
                stream = null;
		    }
		}

		@Override
		public ParcelFileDescriptor fd()
		{
			// This method is used to hand-over a file descriptor to the
			// DTN service. We do not need the method here and always return
			// null.
			return null;
		}

		@Override
		public void payload(byte[] data)
		{
			if (stream == null) return;
		    // write data to the stream
		    try {
                stream.write(data);
            } catch (IOException e) {
                Log.e(TAG, "error on writing payload", e);
            }

			


		}

		@Override
		public void progress(long offset, long length)
		{
			// if payload is written to a file descriptor, the progress
			// will be announced here
			Log.d(TAG, offset + " of " + length + " bytes received");
		}
	};


	@Override
	public void start(BundleReceiver receiver, AssetBundle[] bundles) {
		// TODO Auto-generated method stub
		
	}
}
