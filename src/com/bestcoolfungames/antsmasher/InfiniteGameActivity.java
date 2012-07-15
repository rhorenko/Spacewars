package com.bestcoolfungames.antsmasher;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.os.Bundle;

public class InfiniteGameActivity extends Activity
{
  public static Bitmap[][] ants;
  public static Bitmap bg;
  private InfiniteGameActivityView.GameThread gameThread;
  private InfiniteGameActivityView gameView;

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setRequestedOrientation(1);
    setContentView(2130903047);
    this.gameView = ((InfiniteGameActivityView)findViewById(2131230759));
    this.gameThread = this.gameView.getThread();
  }

  public void onPause()
  {
    super.onPause();
    SharedPreferences.Editor localEditor = getSharedPreferences("AppData", 0).edit();
    localEditor.putBoolean("GamePaused", true);
    localEditor.commit();
    try
    {
      this.gameThread.join();
      finish();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
        localInterruptedException.printStackTrace();
    }
  }

  protected void onResume()
  {
    super.onResume();
    SharedPreferences.Editor localEditor = getSharedPreferences("AppData", 0).edit();
    localEditor.putBoolean("GamePaused", false);
    localEditor.commit();
  }
}