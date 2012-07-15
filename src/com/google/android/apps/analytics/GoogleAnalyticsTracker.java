package com.google.android.apps.analytics;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GoogleAnalyticsTracker
{
  private static final GoogleAnalyticsTracker INSTANCE = new GoogleAnalyticsTracker();
  public static final String LOG_TAG = "GoogleAnalyticsTracker";
  public static final String PRODUCT = "GoogleAnalytics";
  public static final String VERSION = "1.2";
  public static final String WIRE_VERSION = "4.6ma";
  private String accountId;
  private ConnectivityManager connetivityManager;
  private CustomVariableBuffer customVariableBuffer;
  private boolean debug = false;
  private int dispatchPeriod;
  private Runnable dispatchRunner;
  private Dispatcher dispatcher;
  private boolean dispatcherIsBusy;
  private boolean dryRun = false;
  private EventStore eventStore;
  private Handler handler;
  private Map<String, Map<String, Item>> itemMap;
  private Context parent;
  private boolean powerSaveMode;
  private Map<String, Transaction> transactionMap;
  private String userAgentProduct = "GoogleAnalytics";
  private String userAgentVersion = "1.2";

  private GoogleAnalyticsTracker()
  {
    this.transactionMap = new HashMap();
    this.itemMap = new HashMap();
    this.dispatchRunner = new Runnable(this)
    {
      public void run()
      {
        this.this$0.dispatch();
      }
    };
  }

  private void cancelPendingDispatches()
  {
    this.handler.removeCallbacks(this.dispatchRunner);
  }

  private void createEvent(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt)
  {
    Event localEvent = new Event(this.eventStore.getStoreId(), paramString1, paramString2, paramString3, paramString4, paramInt, this.parent.getResources().getDisplayMetrics().widthPixels, this.parent.getResources().getDisplayMetrics().heightPixels);
    localEvent.setCustomVariableBuffer(this.customVariableBuffer);
    this.customVariableBuffer = new CustomVariableBuffer();
    this.eventStore.putEvent(localEvent);
    resetPowerSaveMode();
  }

  public static GoogleAnalyticsTracker getInstance()
  {
    return INSTANCE;
  }

  private void maybeScheduleNextDispatch()
  {
    if (this.dispatchPeriod < 0);
    while (true)
    {
      do
        return;
      while ((!(this.handler.postDelayed(this.dispatchRunner, 1000 * this.dispatchPeriod))) || (this.transactionMap == 0));
      Log.v("GoogleAnalyticsTracker", "Scheduled next dispatch");
    }
  }

  private void resetPowerSaveMode()
  {
    if (this.powerSaveMode)
    {
      this.powerSaveMode = false;
      maybeScheduleNextDispatch();
    }
  }

  public void addItem(Item paramItem)
  {
    if ((Transaction)this.transactionMap.get(paramItem.getOrderId()) == null)
    {
      Log.i("GoogleAnalyticsTracker", "No transaction with orderId " + paramItem.getOrderId() + " found, creating one");
      Transaction localTransaction = new Transaction.Builder(paramItem.getOrderId(), 0D).build();
      this.transactionMap.put(paramItem.getOrderId(), localTransaction);
    }
    Object localObject = (Map)this.itemMap.get(paramItem.getOrderId());
    if (localObject == null)
    {
      localObject = new HashMap();
      this.itemMap.put(paramItem.getOrderId(), localObject);
    }
    ((Map)localObject).put(paramItem.getItemSKU(), paramItem);
  }

  public void addTransaction(Transaction paramTransaction)
  {
    this.transactionMap.put(paramTransaction.getOrderId(), paramTransaction);
  }

  public void clearTransactions()
  {
    this.transactionMap.clear();
    this.itemMap.clear();
  }

  public boolean dispatch()
  {
    byte b = 0;
    if (this.transactionMap != 0)
      Log.v("GoogleAnalyticsTracker", "Called dispatch");
    if (this.dispatcherIsBusy)
    {
      if (this.transactionMap != 0)
        Log.v("GoogleAnalyticsTracker", "...but dispatcher was busy");
      maybeScheduleNextDispatch();
    }
    while (true)
    {
      do
      {
        while (true)
        {
          while (true)
          {
            return b;
            NetworkInfo localNetworkInfo = this.connetivityManager.getActiveNetworkInfo();
            if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
              break;
            if (this.transactionMap != 0)
              Log.v("GoogleAnalyticsTracker", "...but there was no network available");
            maybeScheduleNextDispatch();
          }
          if (this.eventStore.getNumStoredEvents() == 0)
            break;
          Event[] arrayOfEvent = this.eventStore.peekEvents();
          this.dispatcher.dispatchEvents(arrayOfEvent);
          this.dispatcherIsBusy = true;
          maybeScheduleNextDispatch();
          if (this.transactionMap != 0)
            Log.v("GoogleAnalyticsTracker", "Sending " + arrayOfEvent.length + " to dispatcher");
          b = 1;
        }
        this.powerSaveMode = true;
      }
      while (this.transactionMap == 0);
      Log.v("GoogleAnalyticsTracker", "...but there was nothing to dispatch");
    }
  }

  void dispatchFinished()
  {
    this.dispatcherIsBusy = false;
  }

  public boolean getDebug()
  {
    return this.transactionMap;
  }

  Dispatcher getDispatcher()
  {
    return this.dispatcher;
  }

  EventStore getEventStore()
  {
    return this.eventStore;
  }

  public String getVisitorCustomVar(int paramInt)
  {
    if ((paramInt < 1) || (paramInt > 5))
      throw new IllegalArgumentException("Index must be between 1 and 5 inclusive.");
    return this.eventStore.getVisitorCustomVar(paramInt);
  }

  public boolean isDryRun()
  {
    return this.dryRun;
  }

  public boolean setCustomVar(int paramInt, String paramString1, String paramString2)
  {
    return setCustomVar(paramInt, paramString1, paramString2, 3);
  }

  public boolean setCustomVar(int paramInt1, String paramString1, String paramString2, int paramInt2)
  {
    CustomVariable localCustomVariable;
    byte b;
    try
    {
      localCustomVariable = new CustomVariable(paramInt1, paramString1, paramString2, paramInt2);
      if (this.customVariableBuffer == null)
        this.customVariableBuffer = new CustomVariableBuffer();
      this.customVariableBuffer.setCustomVariable(localCustomVariable);
      b = 1;
      return b;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      while (true)
        b = 0;
    }
  }

  public void setDebug(boolean paramBoolean)
  {
    this.transactionMap = paramBoolean;
  }

  public void setDispatchPeriod(int paramInt)
  {
    int i = this.dispatchPeriod;
    this.dispatchPeriod = paramInt;
    if (i <= 0)
      maybeScheduleNextDispatch();
    while (true)
    {
      do
        return;
      while (i <= 0);
      cancelPendingDispatches();
      maybeScheduleNextDispatch();
    }
  }

  public void setDryRun(boolean paramBoolean)
  {
    this.dryRun = paramBoolean;
    if (this.dispatcher != null)
      this.dispatcher.setDryRun(paramBoolean);
  }

  public void setProductVersion(String paramString1, String paramString2)
  {
    this.userAgentProduct = paramString1;
    this.userAgentVersion = paramString2;
  }

  public void start(String paramString, int paramInt, Context paramContext)
  {
    Object localObject1;
    Object localObject2;
    if (this.eventStore == null)
    {
      localObject1 = new PersistentEventStore(new PersistentEventStore.DataBaseHelper(paramContext));
      if (this.dispatcher != null)
        break label80;
      localObject2 = new NetworkDispatcher(this.userAgentProduct, this.userAgentVersion);
      ((Dispatcher)localObject2).setDryRun(this.dryRun);
    }
    while (true)
    {
      while (true)
      {
        start(paramString, paramInt, paramContext, (EventStore)localObject1, (Dispatcher)localObject2);
        return;
        localObject1 = this.eventStore;
      }
      label80: localObject2 = this.dispatcher;
    }
  }

  void start(String paramString, int paramInt, Context paramContext, EventStore paramEventStore, Dispatcher paramDispatcher)
  {
    start(paramString, paramInt, paramContext, paramEventStore, paramDispatcher, new DispatcherCallbacks(this));
  }

  void start(String paramString, int paramInt, Context paramContext, EventStore paramEventStore, Dispatcher paramDispatcher, Dispatcher.Callbacks paramCallbacks)
  {
    this.accountId = paramString;
    this.parent = paramContext;
    this.eventStore = paramEventStore;
    this.eventStore.startNewVisit();
    this.dispatcher = paramDispatcher;
    this.dispatcher.init(paramCallbacks, this.eventStore.getReferrer());
    this.dispatcherIsBusy = false;
    if (this.connetivityManager == null)
      this.connetivityManager = ((ConnectivityManager)this.parent.getSystemService("connectivity"));
    if (this.handler == null)
      this.handler = new Handler(paramContext.getMainLooper());
    while (true)
    {
      setDispatchPeriod(paramInt);
      return;
      cancelPendingDispatches();
    }
  }

  public void start(String paramString, Context paramContext)
  {
    start(paramString, -1, paramContext);
  }

  public void stop()
  {
    this.dispatcher.stop();
    cancelPendingDispatches();
  }

  public void trackEvent(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    createEvent(this.accountId, paramString1, paramString2, paramString3, paramInt);
  }

  public void trackPageView(String paramString)
  {
    createEvent(this.accountId, "__##GOOGLEPAGEVIEW##__", paramString, null, -1);
  }

  public void trackTransactions()
  {
    Map localMap;
    Iterator localIterator1 = this.transactionMap.values().iterator();
    do
    {
      if (!(localIterator1.hasNext()))
        break label244;
      Transaction localTransaction = (Transaction)localIterator1.next();
      Event localEvent1 = new Event(this.eventStore.getStoreId(), this.accountId, "__##GOOGLETRANSACTION##__", "", "", 0, this.parent.getResources().getDisplayMetrics().widthPixels, this.parent.getResources().getDisplayMetrics().heightPixels);
      localEvent1.setTransaction(localTransaction);
      this.eventStore.putEvent(localEvent1);
      localMap = (Map)this.itemMap.get(localTransaction.getOrderId());
    }
    while (localMap == null);
    Iterator localIterator2 = localMap.values().iterator();
    while (true)
    {
      if (!(localIterator2.hasNext()));
      Item localItem = (Item)localIterator2.next();
      Event localEvent2 = new Event(this.eventStore.getStoreId(), this.accountId, "__##GOOGLEITEM##__", "", "", 0, this.parent.getResources().getDisplayMetrics().widthPixels, this.parent.getResources().getDisplayMetrics().heightPixels);
      localEvent2.setItem(localItem);
      this.eventStore.putEvent(localEvent2);
    }
    label244: clearTransactions();
    resetPowerSaveMode();
  }

  final class DispatcherCallbacks
  implements Dispatcher.Callbacks
  {
    public void dispatchFinished()
    {
      GoogleAnalyticsTracker.access$000(this.this$0).post(new Runnable(this)
      {
        public void run()
        {
          this.this$1.this$0.dispatchFinished();
        }
      });
    }

    public void eventDispatched()
    {
      GoogleAnalyticsTracker.access$100(this.this$0).deleteEvent(paramLong);
    }
  }
}