package com.bestcoolfungames.antsmasher;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MetaTags extends AsyncTask<MetaTagListener, Void, Boolean>
{
  MetaTagListener l;
  int theMeta;

  public MetaTags(int paramInt)
  {
    this.theMeta = paramInt;
  }

  static URL metaURL(String paramString)
  {
    URL localURL;
    try
    {
      localURL = new URL("https://s3.amazonaws.com/conf.bestcoolfungames.com/android/antsmasher/" + paramString);
      return localURL;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      while (true)
        ??? = null;
    }
  }

  protected Boolean doInBackground(MetaTagListener[] paramArrayOfMetaTagListener)
  {
    Object localObject;
    this.l = paramArrayOfMetaTagListener[0];
    try
    {
      if (new BufferedReader(new InputStreamReader(metaURL("meta" + this.theMeta + ".txt").openStream())).readLine().equals("YES"))
      {
        localObject = Boolean.valueOf(true);
        break label88:
      }
      Boolean localBoolean = Boolean.valueOf(false);
      localObject = localBoolean;
    }
    catch (Exception localException)
    {
      localObject = Boolean.valueOf(false);
    }
    label88: return ((Boolean)localObject);
  }

  protected void onPostExecute(Boolean paramBoolean)
  {
    try
    {
      if (paramBoolean.booleanValue())
        this.l.receivedYesOnMeta(this.theMeta);
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        Log.i("error", localThrowable.toString());
    }
  }
}