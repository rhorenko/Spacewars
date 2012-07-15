package com.bestcoolfungames.antsmasher;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Twitter extends Activity
{
  private static TwitterWebView twitterWebView;
  private WebView webview;

  private TwitterWebView getTwitterWebView()
  {
    if (twitterWebView == null)
      twitterWebView = new TwitterWebView();
    return twitterWebView;
  }

  protected void finalize()
  {
    try
    {
      finalize();
      label4: return;
    }
    catch (Throwable localThrowable)
    {
      break label4:
    }
  }

  public void onBackPressed()
  {
    SharedPreferences localSharedPreferences = getSharedPreferences("AppData", 0);
    localSharedPreferences.edit();
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    localEditor.putInt("State", 7);
    localEditor.putBoolean("ShouldContinue", false);
    localEditor.commit();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903055);
    setRequestedOrientation(1);
    setVolumeControlStream(3);
    overridePendingTransition(2130968576, 2130968577);
    this.webview = ((WebView)findViewById(2131230777));
    this.webview.getSettings().setJavaScriptEnabled(true);
    SharedPreferences localSharedPreferences = getSharedPreferences("AppData", 0);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(localSharedPreferences.getInt("TwitterAux", 0));
    String str1 = getString(2131165274, arrayOfObject);
    String str2 = "http://bestcoolfungames.com/api/twitter/send_twitt.php?code=4199&message=" + Uri.encode(str1) + "&consumer=" + "zAmKkpgz4K7loMMnQNIFA" + "&secret=" + "rWOQo3R5DW1Y1qkusgEWd5hP4Cwvc9tVT5eUGulGc";
    getTwitterWebView().run(this);
    this.webview.setWebViewClient(getTwitterWebView());
    this.webview.loadUrl(str2);
  }

  protected void onPause()
  {
    super.onPause();
    SharedPreferences.Editor localEditor = getSharedPreferences("AppData", 0).edit();
    localEditor.putBoolean("GamePaused", true);
    localEditor.commit();
  }

  protected void onResume()
  {
    super.onResume();
    SharedPreferences.Editor localEditor = getSharedPreferences("AppData", 0).edit();
    localEditor.putBoolean("GamePaused", false);
    localEditor.commit();
    if (getTwitterWebView().context == null)
      finish();
  }
}