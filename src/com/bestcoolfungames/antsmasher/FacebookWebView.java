package com.bestcoolfungames.antsmasher;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class FacebookWebView extends WebViewClient
{
  private Toast ad;
  public Context context;

  public void onPageFinished(WebView paramWebView, String paramString)
  {
    if ((!(paramString.contains("http://touch.facebook.com/"))) && (paramString.contains("http://m.facebook.com/")));
    paramString.contains("http://bestcoolfungames.com/api/fb/callback.php?post_id");
    if ((paramString.contains("http://bestcoolfungames.com/api/fb/callback.php?post_id")) && (this.context != null))
    {
      SharedPreferences.Editor localEditor = this.context.getSharedPreferences("AppData", 0).edit();
      localEditor.putInt("State", 7);
      localEditor.commit();
      this.context = null;
    }
  }

  public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = this.context.getSharedPreferences("AppData", 0).edit();
    localEditor.putInt("State", 7);
    localEditor.commit();
  }

  public void run(Context paramContext)
  {
    this.context = paramContext;
    try
    {
      this.ad = Toast.makeText(paramContext, paramContext.getString(2131165204), 1);
      this.ad.show();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    paramWebView.loadUrl(paramString);
    return true;
  }
}