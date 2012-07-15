package com.revmob.ads;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Ad
{
  protected Activity activity;
  protected String adURL;

  public Ad(Activity paramActivity, String paramString)
  {
    this.activity = paramActivity;
    this.adURL = paramString;
  }

  public void click()
  {
    if (this.adURL == null);
    while (true)
    {
      return;
      String str = getMarketURL(new HTTPHelper());
      Log.i("RevMob", str);
      this.activity.startActivity(createIntentThatOpensURL(str));
    }
  }

  public Intent createIntentThatOpensURL(String paramString)
  {
    return new Intent("android.intent.action.VIEW", Uri.parse(paramString));
  }

  public String getMarketURL(HTTPHelper paramHTTPHelper)
  {
    String str1;
    Object localObject = this.adURL;
    if (!(this.adURL.startsWith("market")))
    {
      paramHTTPHelper.post(this.adURL, "");
      str1 = paramHTTPHelper.getLastRedirectHandler().getLastRedirectedUrl(this.adURL);
      Matcher localMatcher = Pattern.compile("details\\?id=[a-zA-Z0-9\\.]+").matcher(str1);
      if (!(localMatcher.find()))
        break label91;
      String str2 = localMatcher.group();
      localObject = "market://" + str2;
    }
    while (true)
    {
      return localObject;
      label91: localObject = str1;
    }
  }

  public String getTargetURL()
  {
    return this.adURL;
  }
}