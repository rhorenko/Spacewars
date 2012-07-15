package com.bestcoolfungames.antsmasher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.bestcoolfungames.antsmasher.inapp.BillingService;
import com.bestcoolfungames.antsmasher.inapp.Consts.ResponseCode;

public class BillingReceiver extends BroadcastReceiver
{
  private static final String TAG = "BillingReceiver";

  private void checkResponseCode(Context paramContext, long paramLong, int paramInt)
  {
    Intent localIntent = new Intent("com.android.vending.billing.RESPONSE_CODE");
    localIntent.setClass(paramContext, BillingService.class);
    localIntent.putExtra("request_id", paramLong);
    localIntent.putExtra("response_code", paramInt);
    paramContext.startService(localIntent);
  }

  private void notify(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("com.bestcoolfungames.antsmasher.inapp.GET_PURCHASE_INFORMATION");
    localIntent.setClass(paramContext, BillingService.class);
    localIntent.putExtra("notification_id", paramString);
    paramContext.startService(localIntent);
  }

  private void purchaseStateChanged(Context paramContext, String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("com.android.vending.billing.PURCHASE_STATE_CHANGED");
    localIntent.setClass(paramContext, BillingService.class);
    localIntent.putExtra("inapp_signed_data", paramString1);
    localIntent.putExtra("inapp_signature", paramString2);
    paramContext.startService(localIntent);
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    if ("com.android.vending.billing.PURCHASE_STATE_CHANGED".equals(str))
      purchaseStateChanged(paramContext, paramIntent.getStringExtra("inapp_signed_data"), paramIntent.getStringExtra("inapp_signature"));
    while (true)
    {
      while (true)
      {
        while (true)
        {
          return;
          if (!("com.android.vending.billing.IN_APP_NOTIFY".equals(str)))
            break;
          notify(paramContext, paramIntent.getStringExtra("notification_id"));
        }
        if (!("com.android.vending.billing.RESPONSE_CODE".equals(str)))
          break;
        checkResponseCode(paramContext, paramIntent.getLongExtra("request_id", -1), paramIntent.getIntExtra("response_code", Consts.ResponseCode.RESULT_ERROR.ordinal()));
      }
      Log.w("BillingReceiver", "unexpected action: " + str);
    }
  }
}