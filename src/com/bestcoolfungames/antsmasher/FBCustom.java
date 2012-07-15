package com.bestcoolfungames.antsmasher;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class FBCustom extends Activity
{
  private static FacebookWebView facebookWebView;
  private WebView webview;

  private FacebookWebView getFacebookWebView()
  {
    if (facebookWebView == null)
      facebookWebView = new FacebookWebView();
    return facebookWebView;
  }

  String fbString(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    return "http://www.bestcoolfungames.com/api/fb/fb.php?a=" + paramString1 + "&l=" + paramString2 + "&t=" + Uri.encode(paramString3) + "&m=" + Uri.encode(paramString4) + "&i=" + paramString5;
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
    arrayOfObject[0] = Integer.valueOf(localSharedPreferences.getInt("Points", 0));
    String str = fbString("115026441882059", "http://bit.ly/antsmasher", getString(2131165275, arrayOfObject), getString(2131165276), "http://files.schvarts.webnode.com.br/200000258-0d3cf0e373/icon_512.png");
    Log.i("TAG", str);
    getFacebookWebView().run(this);
    this.webview.setWebViewClient(getFacebookWebView());
    this.webview.loadUrl(str);
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
    if (InitialView.mainInstance == null)
      finish();
    if (getFacebookWebView().context == null)
      finish();
  }
}