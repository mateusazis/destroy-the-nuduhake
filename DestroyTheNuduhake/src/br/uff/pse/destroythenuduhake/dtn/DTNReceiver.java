package br.uff.pse.destroythenuduhake.dtn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DTNReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent)
	{
		String action = intent.getAction();

		if (action.equals(de.tubs.ibr.dtn.Intent.RECEIVE))
		{
			// We received a notification about a new bundle and
			// wake-up the local PingService to received the bundle.
			Intent i = new Intent(context, DTNService.class);
			i.setAction(de.tubs.ibr.dtn.Intent.RECEIVE);
			context.startService(i);
		}
	}
}
