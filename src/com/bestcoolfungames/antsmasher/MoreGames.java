package com.bestcoolfungames.antsmasher;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MoreGames extends Activity
{
  private static SharedPreferences.Editor editor;
  private static SharedPreferences settings;
  private boolean isChangingScreen = false;

  protected void backToMainMenu()
  {
    InitialView.playButtonSound("Out", getApplicationContext());
    this.isChangingScreen = true;
    editor.putInt("State", 5);
    editor.commit();
  }

  protected void finalize()
  {
    try
    {
      finalize();
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        localThrowable.printStackTrace();
    }
  }

  public void onBackPressed()
  {
    backToMainMenu();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903050);
    setRequestedOrientation(1);
    settings = getSharedPreferences("AppData", 0);
    editor = settings.edit();
    setVolumeControlStream(3);
    overridePendingTransition(2130968576, 2130968577);
    setupWebView();
  }

  protected void onPause()
  {
    if (!(this.isChangingScreen))
    {
      if ((InitialView.backgroundMp != null) && (!(InitialView.backgroundMpErrored.booleanValue())) && (InitialView.backgroundMp.isPlaying()))
        InitialView.backgroundMp.stop();
      Log.i("AS", "Going to quit app");
      SharedPreferences.Editor localEditor = getSharedPreferences("AntSmasherShop", 0).edit();
      localEditor.putBoolean("InitialProductAlreadyOffered", false);
      localEditor.commit();
    }
    while (true)
    {
      super.onPause();
      return;
      Log.i("AS", "Going to change screen");
      this.isChangingScreen = false;
    }
  }

  protected void onResume()
  {
    if (!(this.isChangingScreen))
      backToMainMenu();
    super.onResume();
  }

  protected void onStart()
  {
    if ((InitialView.backgroundMp != null) && (!(InitialView.backgroundMpErrored.booleanValue())) && (InitialView.backgroundMp.isPlaying()))
      InitialView.backgroundMp.stop();
    super.onStart();
  }

  protected void setupWebView()
  {
    WebView localWebView = (WebView)findViewById(2131230777);
    localWebView.getSettings().setJavaScriptEnabled(true);
    this.isChangingScreen = true;
    localWebView.loadUrl("http://bestcoolfungames.com/bcfg/links/market.php");
  }
}