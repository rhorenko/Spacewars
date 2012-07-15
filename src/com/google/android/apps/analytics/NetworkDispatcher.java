package com.google.android.apps.analytics;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.ParseException;
import org.apache.http.message.BasicHttpRequest;

class NetworkDispatcher
  implements Dispatcher
{
  private static final HttpHost GOOGLE_ANALYTICS_HOST = new HttpHost("www.google-analytics.com", 80);
  private static final int MAX_EVENTS_PER_PIPELINE = 30;
  private static final int MAX_SEQUENTIAL_REQUESTS = 5;
  private static final long MIN_RETRY_INTERVAL = 2;
  private static final String USER_AGENT_TEMPLATE = "%s/%s (Linux; U; Android %s; %s-%s; %s Build/%s)";
  private DispatcherThread dispatcherThread;
  private boolean dryRun;
  private final String userAgent;

  public NetworkDispatcher()
  {
    this("GoogleAnalytics", "1.2");
  }

  public NetworkDispatcher(String paramString1, String paramString2)
  {
    this.dryRun = false;
    Locale localLocale = Locale.getDefault();
    Object[] arrayOfObject = new Object[7];
    arrayOfObject[0] = paramString1;
    arrayOfObject[1] = paramString2;
    arrayOfObject[2] = Build.VERSION.RELEASE;
    if (localLocale.getLanguage() != null)
    {
      str1 = localLocale.getLanguage().toLowerCase();
      arrayOfObject[3] = str1;
      if (localLocale.getCountry() == null)
        break label115;
      str2 = localLocale.getCountry().toLowerCase();
    }
    while (true)
    {
      while (true)
      {
        arrayOfObject[4] = str2;
        arrayOfObject[5] = Build.MODEL;
        arrayOfObject[6] = Build.ID;
        this.userAgent = String.format("%s/%s (Linux; U; Android %s; %s-%s; %s Build/%s)", arrayOfObject);
        return;
        str1 = "en";
      }
      label115: str2 = "";
    }
  }

  public void dispatchEvents(Event[] paramArrayOfEvent)
  {
    if (this.dispatcherThread != null)
      this.dispatcherThread.dispatchEvents(paramArrayOfEvent);
  }

  String getUserAgent()
  {
    return this.userAgent;
  }

  public void init(Dispatcher.Callbacks paramCallbacks, PipelinedRequester paramPipelinedRequester, String paramString)
  {
    stop();
    this.dispatcherThread = new DispatcherThread(paramCallbacks, paramPipelinedRequester, paramString, this.userAgent, this, null);
    this.dispatcherThread.start();
  }

  public void init(Dispatcher.Callbacks paramCallbacks, String paramString)
  {
    stop();
    this.dispatcherThread = new DispatcherThread(paramCallbacks, paramString, this.userAgent, this, null);
    this.dispatcherThread.start();
  }

  public boolean isDryRun()
  {
    return this.dryRun;
  }

  public void setDryRun(boolean paramBoolean)
  {
    this.dryRun = paramBoolean;
  }

  public void stop()
  {
    if ((this.dispatcherThread != null) && (this.dispatcherThread.getLooper() != null))
    {
      this.dispatcherThread.getLooper().quit();
      this.dispatcherThread = null;
    }
  }

  public void waitForThreadLooper()
  {
    this.dispatcherThread.getLooper();
  }

  private static class DispatcherThread extends HandlerThread
  {
    private final Dispatcher.Callbacks callbacks;
    private AsyncDispatchTask currentTask;
    private Handler handlerExecuteOnDispatcherThread;
    private int lastStatusCode;
    private int maxEventsPerRequest;
    private final NetworkDispatcher parent;
    private final PipelinedRequester pipelinedRequester;
    private final String referrer;
    private final RequesterCallbacks requesterCallBacks;
    private long retryInterval;
    private final String userAgent;

    private DispatcherThread(Dispatcher.Callbacks paramCallbacks, PipelinedRequester paramPipelinedRequester, String paramString1, String paramString2, NetworkDispatcher paramNetworkDispatcher)
    {
      super("DispatcherThread");
      this.maxEventsPerRequest = 30;
      this.currentTask = null;
      this.callbacks = paramCallbacks;
      this.referrer = paramString1;
      this.userAgent = paramString2;
      this.pipelinedRequester = paramPipelinedRequester;
      this.requesterCallBacks = new RequesterCallbacks(this, null);
      this.pipelinedRequester.installCallbacks(this.requesterCallBacks);
      this.parent = paramNetworkDispatcher;
    }

    private DispatcherThread(Dispatcher.Callbacks paramCallbacks, String paramString1, String paramString2, NetworkDispatcher paramNetworkDispatcher)
    {
      this(paramCallbacks, new PipelinedRequester(NetworkDispatcher.access$200()), paramString1, paramString2, paramNetworkDispatcher);
    }

    public void dispatchEvents(Event[] paramArrayOfEvent)
    {
      if (this.handlerExecuteOnDispatcherThread != null)
        this.handlerExecuteOnDispatcherThread.post(new AsyncDispatchTask(this, paramArrayOfEvent));
    }

    protected void onLooperPrepared()
    {
      this.handlerExecuteOnDispatcherThread = new Handler();
    }

    private class RequesterCallbacks
  implements PipelinedRequester.Callbacks
    {
      public void pipelineModeChanged()
      {
        if (paramBoolean)
          NetworkDispatcher.DispatcherThread.access$1002(this.this$0, 30);
        while (true)
        {
          return;
          NetworkDispatcher.DispatcherThread.access$1002(this.this$0, 1);
        }
      }

      public void requestSent()
      {
        if (NetworkDispatcher.DispatcherThread.access$400(this.this$0) == null);
        while (true)
        {
          Event localEvent;
          do
          {
            return;
            localEvent = NetworkDispatcher.DispatcherThread.access$400(this.this$0).removeNextEvent();
          }
          while (localEvent == null);
          NetworkDispatcher.DispatcherThread.access$900(this.this$0).eventDispatched(localEvent.eventId);
        }
      }

      public void serverError()
      {
        NetworkDispatcher.DispatcherThread.access$502(this.this$0, paramInt);
      }
    }

    private class AsyncDispatchTask
  implements Runnable
    {
      private final LinkedList<Event> events = new LinkedList();

      public AsyncDispatchTask(, Event[] paramArrayOfEvent)
      {
        Collections.addAll(this.events, paramArrayOfEvent);
      }

      private void dispatchSomePendingEvents()
        throws IOException, ParseException, HttpException
      {
        if ((GoogleAnalyticsTracker.getInstance().getDebug()) && (paramBoolean))
          Log.v("GoogleAnalyticsTracker", "dispatching events in dry run mode");
        int i = 0;
        if ((i < this.events.size()) && (i < NetworkDispatcher.DispatcherThread.access$1000(this.this$0)))
        {
          String str;
          BasicHttpRequest localBasicHttpRequest;
          Event localEvent = (Event)this.events.get(i);
          if ("__##GOOGLEPAGEVIEW##__".equals(localEvent.category))
          {
            str = NetworkRequestUtil.constructPageviewRequestPath(localEvent, NetworkDispatcher.DispatcherThread.access$1100(this.this$0));
            localBasicHttpRequest = new BasicHttpRequest("GET", str);
            localBasicHttpRequest.addHeader("Host", NetworkDispatcher.access$200().getHostName());
            localBasicHttpRequest.addHeader("User-Agent", NetworkDispatcher.DispatcherThread.access$1200(this.this$0));
            if (GoogleAnalyticsTracker.getInstance().getDebug())
              Log.i("GoogleAnalyticsTracker", localBasicHttpRequest.getRequestLine().toString());
            if (!(paramBoolean))
              break label243;
            NetworkDispatcher.DispatcherThread.access$1300(this.this$0).requestSent();
          }
          while (true)
          {
            while (true)
            {
              while (true)
              {
                while (true)
                {
                  while (true)
                    ++i;
                  if (!("__##GOOGLETRANSACTION##__".equals(localEvent.category)))
                    break;
                  str = NetworkRequestUtil.constructTransactionRequestPath(localEvent, NetworkDispatcher.DispatcherThread.access$1100(this.this$0));
                }
                if (!("__##GOOGLEITEM##__".equals(localEvent.category)))
                  break;
                str = NetworkRequestUtil.constructItemRequestPath(localEvent, NetworkDispatcher.DispatcherThread.access$1100(this.this$0));
              }
              str = NetworkRequestUtil.constructEventRequestPath(localEvent, NetworkDispatcher.DispatcherThread.access$1100(this.this$0));
            }
            label243: NetworkDispatcher.DispatcherThread.access$800(this.this$0).addRequest(localBasicHttpRequest);
          }
        }
        if (!(paramBoolean))
          NetworkDispatcher.DispatcherThread.access$800(this.this$0).sendRequests();
      }

      public Event removeNextEvent()
      {
        return ((Event)this.events.poll());
      }

      public void run()
      {
        long l;
        NetworkDispatcher.DispatcherThread.access$402(this.this$0, this);
        byte b = 0;
        if ((b < 5) && (this.events.size() > 0))
          l = 0L;
        try
        {
          while ((NetworkDispatcher.DispatcherThread.access$500(this.this$0) == 500) || (NetworkDispatcher.DispatcherThread.access$500(this.this$0) == 503))
          {
            l = ()(Math.random() * NetworkDispatcher.DispatcherThread.access$600(this.this$0));
            if (NetworkDispatcher.DispatcherThread.access$600(this.this$0) > 256)
              NetworkDispatcher.DispatcherThread.access$630(this.this$0, 2);
            Thread.sleep(l * 1000);
            dispatchSomePendingEvents(NetworkDispatcher.DispatcherThread.access$700(this.this$0).isDryRun());
            ++b;
          }
          NetworkDispatcher.DispatcherThread.access$602(this.this$0, 2);
        }
        catch (InterruptedException localInterruptedException)
        {
          Log.w("GoogleAnalyticsTracker", "Couldn't sleep.", localInterruptedException);
          NetworkDispatcher.DispatcherThread.access$800(this.this$0).finishedCurrentRequests();
          NetworkDispatcher.DispatcherThread.access$900(this.this$0).dispatchFinished();
          NetworkDispatcher.DispatcherThread.access$402(this.this$0, null);
          return;
        }
        catch (IOException localIOException)
        {
          Log.w("GoogleAnalyticsTracker", "Problem with socket or streams.", localIOException);
        }
        catch (HttpException localHttpException)
        {
          while (true)
            Log.w("GoogleAnalyticsTracker", "Problem with http streams.", localHttpException);
        }
      }
    }
  }
}