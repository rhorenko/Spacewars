package com.bestcoolfungames.antsmasher;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MoreGamesClient extends WebViewClient
{
  private String currentUrl;
  private SharedPreferences.Editor editor;
  private SharedPreferences settings;
  private Context theContext;

  public MoreGamesClient(Context paramContext)
  {
    if (paramContext == null)
      throw new NullPointerException();
    this.theContext = paramContext;
    this.settings = this.theContext.getSharedPreferences("AppData", 0);
  }

  protected void backToMainMenu()
  {
    this.editor = this.settings.edit();
    this.editor.putInt("State", 5);
    this.editor.commit();
    this.theContext = null;
    this.editor = null;
    this.settings = null;
  }

  public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    Log.i("AS", "Received error Current URL: " + this.currentUrl);
    backToMainMenu();
  }

  public boolean shouldOverrideKeyEvent(WebView paramWebView, KeyEvent paramKeyEvent)
  {
    byte b;
    if ((paramKeyEvent.getAction() == 0) && (paramKeyEvent.getKeyCode() == 4))
    {
      Log.i("AS", "Should override key Current URL: " + this.currentUrl);
      b = 1;
    }
    while (true)
    {
      return b;
      b = 0;
    }
  }

  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    this.currentUrl = paramString;
    Log.i("AS", "Should override url Current URL: " + this.currentUrl);
    return false;
  }
}