package com.bestcoolfungames.antsmasher.inapp;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class ResponseHandler
{
  private static final String TAG = "ResponseHandler";
  private static PurchaseObserver sPurchaseObserver;

  public static void buyPageIntentResponse(PendingIntent paramPendingIntent, Intent paramIntent)
  {
    if (sPurchaseObserver == null);
    while (true)
    {
      return;
      sPurchaseObserver.startBuyPageActivity(paramPendingIntent, paramIntent);
    }
  }

  public static void checkBillingSupportedResponse(boolean paramBoolean)
  {
    if (sPurchaseObserver != null)
      sPurchaseObserver.onBillingSupported(paramBoolean);
  }

  public static void purchaseResponse(Context paramContext, Consts.PurchaseState paramPurchaseState, String paramString1, String paramString2, long paramLong, String paramString3)
  {
    new Thread(new Runnable(paramContext, paramString2, paramString1, paramPurchaseState, paramLong, paramString3)
    {
      // ERROR //
      public void run()
      {
        // Byte code:
        //   0: new 41	com/bestcoolfungames/antsmasher/inapp/PurchaseDatabase
        //   3: dup
        //   4: aload_0
        //   5: getfield 25	com/bestcoolfungames/antsmasher/inapp/ResponseHandler$1:val$context	Landroid/content/Context;
        //   8: invokespecial 44	com/bestcoolfungames/antsmasher/inapp/PurchaseDatabase:<init>	(Landroid/content/Context;)V
        //   11: astore_1
        //   12: aload_1
        //   13: aload_0
        //   14: getfield 27	com/bestcoolfungames/antsmasher/inapp/ResponseHandler$1:val$orderId	Ljava/lang/String;
        //   17: aload_0
        //   18: getfield 29	com/bestcoolfungames/antsmasher/inapp/ResponseHandler$1:val$productId	Ljava/lang/String;
        //   21: aload_0
        //   22: getfield 31	com/bestcoolfungames/antsmasher/inapp/ResponseHandler$1:val$purchaseState	Lcom/bestcoolfungames/antsmasher/inapp/Consts$PurchaseState;
        //   25: aload_0
        //   26: getfield 33	com/bestcoolfungames/antsmasher/inapp/ResponseHandler$1:val$purchaseTime	J
        //   29: aload_0
        //   30: getfield 35	com/bestcoolfungames/antsmasher/inapp/ResponseHandler$1:val$developerPayload	Ljava/lang/String;
        //   33: invokevirtual 48	com/bestcoolfungames/antsmasher/inapp/PurchaseDatabase:updatePurchase	(Ljava/lang/String;Ljava/lang/String;Lcom/bestcoolfungames/antsmasher/inapp/Consts$PurchaseState;JLjava/lang/String;)I
        //   36: istore_2
        //   37: aload_1
        //   38: invokevirtual 51	com/bestcoolfungames/antsmasher/inapp/PurchaseDatabase:close	()V
        //   41: ldc 8
        //   43: monitorenter
        //   44: invokestatic 55	com/bestcoolfungames/antsmasher/inapp/ResponseHandler:access$0	()Lcom/bestcoolfungames/antsmasher/inapp/PurchaseObserver;
        //   47: ifnull +26 -> 73
        //   50: invokestatic 55	com/bestcoolfungames/antsmasher/inapp/ResponseHandler:access$0	()Lcom/bestcoolfungames/antsmasher/inapp/PurchaseObserver;
        //   53: aload_0
        //   54: getfield 31	com/bestcoolfungames/antsmasher/inapp/ResponseHandler$1:val$purchaseState	Lcom/bestcoolfungames/antsmasher/inapp/Consts$PurchaseState;
        //   57: aload_0
        //   58: getfield 29	com/bestcoolfungames/antsmasher/inapp/ResponseHandler$1:val$productId	Ljava/lang/String;
        //   61: iload_2
        //   62: aload_0
        //   63: getfield 33	com/bestcoolfungames/antsmasher/inapp/ResponseHandler$1:val$purchaseTime	J
        //   66: aload_0
        //   67: getfield 35	com/bestcoolfungames/antsmasher/inapp/ResponseHandler$1:val$developerPayload	Ljava/lang/String;
        //   70: invokevirtual 61	com/bestcoolfungames/antsmasher/inapp/PurchaseObserver:postPurchaseStateChange	(Lcom/bestcoolfungames/antsmasher/inapp/Consts$PurchaseState;Ljava/lang/String;IJLjava/lang/String;)V
        //   73: ldc 8
        //   75: monitorexit
        //   76: return
        //   77: astore_3
        //   78: ldc 8
        //   80: monitorexit
        //   81: aload_3
        //   82: athrow
        //
        // Exception table:
        //   from	to	target	type
        //   44	81	77	finally
      }
    }).start();
  }

  /**
   * @deprecated
   */
  // ERROR //
  public static void register(PurchaseObserver paramPurchaseObserver)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: putstatic 20	com/bestcoolfungames/antsmasher/inapp/ResponseHandler:sPurchaseObserver	Lcom/bestcoolfungames/antsmasher/inapp/PurchaseObserver;
    //   7: ldc 2
    //   9: monitorexit
    //   10: return
    //   11: astore_1
    //   12: ldc 2
    //   14: monitorexit
    //   15: aload_1
    //   16: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   3	7	11	finally
  }

  public static void responseCodeReceived(Context paramContext, BillingService.RequestPurchase paramRequestPurchase, Consts.ResponseCode paramResponseCode)
  {
    if (sPurchaseObserver != null)
      sPurchaseObserver.onRequestPurchaseResponse(paramRequestPurchase, paramResponseCode);
  }

  public static void responseCodeReceived(Context paramContext, BillingService.RestoreTransactions paramRestoreTransactions, Consts.ResponseCode paramResponseCode)
  {
    if (sPurchaseObserver != null)
      sPurchaseObserver.onRestoreTransactionsResponse(paramRestoreTransactions, paramResponseCode);
  }

  /**
   * @deprecated
   */
  // ERROR //
  public static void unregister(PurchaseObserver paramPurchaseObserver)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aconst_null
    //   4: putstatic 20	com/bestcoolfungames/antsmasher/inapp/ResponseHandler:sPurchaseObserver	Lcom/bestcoolfungames/antsmasher/inapp/PurchaseObserver;
    //   7: ldc 2
    //   9: monitorexit
    //   10: return
    //   11: astore_1
    //   12: ldc 2
    //   14: monitorexit
    //   15: aload_1
    //   16: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   3	7	11	finally
  }
}