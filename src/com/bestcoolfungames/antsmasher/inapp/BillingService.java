package com.bestcoolfungames.antsmasher.inapp;

import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.android.vending.billing.IMarketBillingService;
import com.android.vending.billing.IMarketBillingService.Stub;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class BillingService extends Service
  implements ServiceConnection
{
  private static final String TAG = "BillingService";
  private static LinkedList<BillingRequest> mPendingRequests = new LinkedList();
  private static HashMap<Long, BillingRequest> mSentRequests = new HashMap();
  private static IMarketBillingService mService;

  private boolean bindToMarketBillingService()
  {
    byte b = 1;
    try
    {
      if (bindService(new Intent("com.android.vending.billing.MarketBillingService.BIND"), this, 1))
        break label62:
      Log.e("BillingService", "Could not bind to service.");
      b = 0;
    }
    catch (SecurityException localSecurityException)
    {
      while (true)
        Log.e("BillingService", "Security exception: " + localSecurityException);
    }
    label62: return b;
  }

  private void checkResponseCode(long paramLong, Consts.ResponseCode paramResponseCode)
  {
    BillingRequest localBillingRequest = (BillingRequest)mSentRequests.get(Long.valueOf(paramLong));
    if (localBillingRequest != null)
      localBillingRequest.responseCodeReceived(paramResponseCode);
    mSentRequests.remove(Long.valueOf(paramLong));
  }

  private boolean confirmNotifications(int paramInt, String[] paramArrayOfString)
  {
    return new ConfirmNotifications(this, paramInt, paramArrayOfString).runRequest();
  }

  private boolean getPurchaseInformation(int paramInt, String[] paramArrayOfString)
  {
    return new GetPurchaseInformation(this, paramInt, paramArrayOfString).runRequest();
  }

  private void purchaseStateChanged(int paramInt, String paramString1, String paramString2)
  {
    ArrayList localArrayList1 = Security.verifyPurchase(paramString1, paramString2);
    if (localArrayList1 == null)
      return;
    ArrayList localArrayList2 = new ArrayList();
    Iterator localIterator = localArrayList1.iterator();
    while (true)
    {
      while (true)
      {
        do
          if (localIterator.hasNext())
            break label72;
        while (localArrayList2.isEmpty());
        confirmNotifications(paramInt, (String[])localArrayList2.toArray(new String[localArrayList2.size()]));
      }
      label72: Security.VerifiedPurchase localVerifiedPurchase = (Security.VerifiedPurchase)localIterator.next();
      if (localVerifiedPurchase.notificationId != null)
        localArrayList2.add(localVerifiedPurchase.notificationId);
      ResponseHandler.purchaseResponse(this, localVerifiedPurchase.purchaseState, localVerifiedPurchase.productId, localVerifiedPurchase.orderId, localVerifiedPurchase.purchaseTime, localVerifiedPurchase.developerPayload);
    }
  }

  private void runPendingRequests()
  {
    int i = -1;
    BillingRequest localBillingRequest = (BillingRequest)mPendingRequests.peek();
    if (localBillingRequest == null)
      if (i >= 0)
        stopSelf(i);
    while (true)
    {
      while (true)
      {
        do
        {
          return;
          if (!(localBillingRequest.runIfConnected()))
            break label57;
          mPendingRequests.remove();
        }
        while (i >= localBillingRequest.getStartId());
        i = localBillingRequest.getStartId();
      }
      label57: bindToMarketBillingService();
    }
  }

  public boolean checkBillingSupported()
  {
    return new CheckBillingSupported(this).runRequest();
  }

  public void handleCommand(Intent paramIntent, int paramInt)
  {
    String str1 = paramIntent.getAction();
    if ("com.bestcoolfungames.antsmasher.inapp.CONFIRM_NOTIFICATION".equals(str1))
      confirmNotifications(paramInt, paramIntent.getStringArrayExtra("notification_id"));
    while (true)
    {
      do
        while (true)
        {
          while (true)
          {
            return;
            if (!("com.bestcoolfungames.antsmasher.inapp.GET_PURCHASE_INFORMATION".equals(str1)))
              break;
            String str2 = paramIntent.getStringExtra("notification_id");
            String[] arrayOfString = new String[1];
            arrayOfString[0] = str2;
            getPurchaseInformation(paramInt, arrayOfString);
          }
          if (!("com.android.vending.billing.PURCHASE_STATE_CHANGED".equals(str1)))
            break;
          purchaseStateChanged(paramInt, paramIntent.getStringExtra("inapp_signed_data"), paramIntent.getStringExtra("inapp_signature"));
        }
      while (!("com.android.vending.billing.RESPONSE_CODE".equals(str1)));
      checkResponseCode(paramIntent.getLongExtra("request_id", -1), Consts.ResponseCode.valueOf(paramIntent.getIntExtra("response_code", Consts.ResponseCode.RESULT_ERROR.ordinal())));
    }
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    mService = IMarketBillingService.Stub.asInterface(paramIBinder);
    runPendingRequests();
  }

  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    Log.w("BillingService", "Billing service disconnected");
    mService = null;
  }

  public void onStart(Intent paramIntent, int paramInt)
  {
    if (paramIntent != null)
      handleCommand(paramIntent, paramInt);
  }

  public boolean requestPurchase(String paramString1, String paramString2)
  {
    return new RequestPurchase(this, paramString1, paramString2).runRequest();
  }

  public boolean restoreTransactions()
  {
    return new RestoreTransactions(this).runRequest();
  }

  public void setContext(Context paramContext)
  {
    attachBaseContext(paramContext);
  }

  public void unbind()
  {
    try
    {
      unbindService(this);
      label5: return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      break label5:
    }
  }

  abstract class BillingRequest
  {
    protected long mRequestId;
    private final int mStartId;

    public BillingRequest(, int paramInt)
    {
      this.mStartId = paramInt;
    }

    public int getStartId()
    {
      return this.mStartId;
    }

    protected void logResponseCode(, Bundle paramBundle)
    {
      Consts.ResponseCode.valueOf(paramBundle.getInt("RESPONSE_CODE"));
    }

    protected Bundle makeRequestBundle()
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("BILLING_REQUEST", paramString);
      localBundle.putInt("API_VERSION", 1);
      localBundle.putString("PACKAGE_NAME", this.this$0.getPackageName());
      return localBundle;
    }

    protected void onRemoteException()
    {
      Log.w("BillingService", "remote billing service crashed");
      BillingService.access$4(null);
    }

    protected void responseCodeReceived()
    {
    }

    protected abstract long run()
      throws RemoteException;

    public boolean runIfConnected()
    {
      byte b;
      if (BillingService.access$2() != null);
      try
      {
        this.mRequestId = run();
        if (!(this.mRequestId > 0L))
          BillingService.access$3().put(Long.valueOf(this.mRequestId), this);
        b = 1;
        return b;
      }
      catch (RemoteException localRemoteException)
      {
        while (true)
        {
          onRemoteException(localRemoteException);
          b = 0;
        }
      }
    }

    public boolean runRequest()
    {
      byte b = 1;
      if (runIfConnected());
      while (true)
      {
        while (true)
        {
          return b;
          if (!(BillingService.access$0(this.this$0)))
            break;
          BillingService.access$1().add(this);
        }
        b = 0;
      }
    }
  }

  class CheckBillingSupported extends BillingService.BillingRequest
  {
    public CheckBillingSupported()
    {
      super(paramBillingService, -1);
    }

    protected long run()
      throws RemoteException
    {
      int j;
      Bundle localBundle = makeRequestBundle("CHECK_BILLING_SUPPORTED");
      int i = BillingService.access$2().sendBillingRequest(localBundle).getInt("RESPONSE_CODE");
      if (i == Consts.ResponseCode.RESULT_OK.ordinal())
        j = 1;
      while (true)
      {
        Log.i("", "Code: " + i);
        ResponseHandler.checkBillingSupportedResponse(j);
        return Consts.BILLING_RESPONSE_INVALID_REQUEST_ID;
        byte b = 0;
      }
    }
  }

  class ConfirmNotifications extends BillingService.BillingRequest
  {
    final String[] mNotifyIds;

    public ConfirmNotifications(, int paramInt, String[] paramArrayOfString)
    {
      super(paramBillingService, paramInt);
      this.mNotifyIds = paramArrayOfString;
    }

    protected long run()
      throws RemoteException
    {
      Bundle localBundle1 = makeRequestBundle("CONFIRM_NOTIFICATIONS");
      localBundle1.putStringArray("NOTIFY_IDS", this.mNotifyIds);
      Bundle localBundle2 = BillingService.access$2().sendBillingRequest(localBundle1);
      logResponseCode("confirmNotifications", localBundle2);
      return localBundle2.getLong("REQUEST_ID", Consts.BILLING_RESPONSE_INVALID_REQUEST_ID);
    }
  }

  class GetPurchaseInformation extends BillingService.BillingRequest
  {
    long mNonce;
    final String[] mNotifyIds;

    public GetPurchaseInformation(, int paramInt, String[] paramArrayOfString)
    {
      super(paramBillingService, paramInt);
      this.mNotifyIds = paramArrayOfString;
    }

    protected void onRemoteException()
    {
      super.onRemoteException(paramRemoteException);
      Security.removeNonce(this.mNonce);
    }

    protected long run()
      throws RemoteException
    {
      this.mNonce = Security.generateNonce();
      Bundle localBundle1 = makeRequestBundle("GET_PURCHASE_INFORMATION");
      localBundle1.putLong("NONCE", this.mNonce);
      localBundle1.putStringArray("NOTIFY_IDS", this.mNotifyIds);
      Bundle localBundle2 = BillingService.access$2().sendBillingRequest(localBundle1);
      logResponseCode("getPurchaseInformation", localBundle2);
      return localBundle2.getLong("REQUEST_ID", Consts.BILLING_RESPONSE_INVALID_REQUEST_ID);
    }
  }

  public class RequestPurchase extends BillingService.BillingRequest
  {
    public final String mDeveloperPayload;
    public final String mProductId;

    public RequestPurchase(, String paramString)
    {
      this(paramBillingService, paramString, null);
    }

    public RequestPurchase(, String paramString1, String paramString2)
    {
      super(paramBillingService, -1);
      this.mProductId = paramString1;
      this.mDeveloperPayload = paramString2;
    }

    protected void responseCodeReceived()
    {
      ResponseHandler.responseCodeReceived(this.this$0, this, paramResponseCode);
    }

    protected long run()
      throws RemoteException
    {
      long l1;
      Bundle localBundle1;
      try
      {
        localBundle1 = makeRequestBundle("REQUEST_PURCHASE");
        localBundle1.putString("ITEM_ID", this.mProductId);
        if (this.mDeveloperPayload != null)
          localBundle1.putString("DEVELOPER_PAYLOAD", this.mDeveloperPayload);
        Bundle localBundle2 = BillingService.access$2().sendBillingRequest(localBundle1);
        PendingIntent localPendingIntent = (PendingIntent)localBundle2.getParcelable("PURCHASE_INTENT");
        if (localPendingIntent == null)
        {
          Log.e("BillingService", "Error with requestPurchase");
          l1 = Consts.BILLING_RESPONSE_INVALID_REQUEST_ID;
          break label120:
        }
        ResponseHandler.buyPageIntentResponse(localPendingIntent, new Intent());
        long l2 = localBundle2.getLong("REQUEST_ID", Consts.BILLING_RESPONSE_INVALID_REQUEST_ID);
        l1 = l2;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        l1 = -1;
      }
      label120: return l1;
    }
  }

  public class RestoreTransactions extends BillingService.BillingRequest
  {
    long mNonce;

    public RestoreTransactions()
    {
      super(paramBillingService, -1);
    }

    protected void onRemoteException()
    {
      super.onRemoteException(paramRemoteException);
      Security.removeNonce(this.mNonce);
    }

    protected void responseCodeReceived()
    {
      ResponseHandler.responseCodeReceived(this.this$0, this, paramResponseCode);
    }

    protected long run()
      throws RemoteException
    {
      this.mNonce = Security.generateNonce();
      Bundle localBundle1 = makeRequestBundle("RESTORE_TRANSACTIONS");
      localBundle1.putLong("NONCE", this.mNonce);
      Bundle localBundle2 = BillingService.access$2().sendBillingRequest(localBundle1);
      logResponseCode("restoreTransactions", localBundle2);
      return localBundle2.getLong("REQUEST_ID", Consts.BILLING_RESPONSE_INVALID_REQUEST_ID);
    }
  }
}