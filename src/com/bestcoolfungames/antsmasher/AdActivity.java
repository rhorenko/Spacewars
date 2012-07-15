package com.bestcoolfungames.antsmasher;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AdActivity extends Activity
{
  public Drawable bg;

  public void onBackPressed()
  {
    SharedPreferences.Editor localEditor = getSharedPreferences("AppData", 0).edit();
    localEditor.putInt("State", 5);
    localEditor.commit();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903040);
    setRequestedOrientation(1);
    findViewById(2131230722).setOnClickListener(new View.OnClickListener(this)
    {
      public void onClick()
      {
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://bestcoolfungames.com/ads/url_android.php"));
        this.this$0.startActivity(localIntent);
      }
    });
    findViewById(2131230723).setOnClickListener(new View.OnClickListener(this)
    {
      public void onClick()
      {
        SharedPreferences.Editor localEditor = this.this$0.getSharedPreferences("AppData", 0).edit();
        localEditor.putInt("State", 5);
        localEditor.commit();
      }
    });
    new Thread(new Runnable(this, new Handler(), new Runnable(this)
    {
      public void run()
      {
        this.this$0.setGlobal();
      }
    })
    {
      public void run()
      {
        Drawable localDrawable;
        try
        {
          localDrawable = Drawable.createFromStream(new URL("https://s3.amazonaws.com/BCFG_Images/Ant+Smasher+Android/bg_android.png").openStream(), "bg_android.png");
          this.this$0.bg = localDrawable;
          this.val$mHandler.post(this.val$mUpdateResults);
          return;
        }
        catch (MalformedURLException localMalformedURLException)
        {
          localMalformedURLException.printStackTrace();
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
        }
        catch (NullPointerException localNullPointerException)
        {
          localNullPointerException.printStackTrace();
        }
        catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
        {
          while (true)
            localArrayIndexOutOfBoundsException.printStackTrace();
        }
      }
    }).start();
  }

  protected void setGlobal()
  {
    findViewById(2131230721).setBackgroundDrawable(this.bg);
    setFeatureDrawable(0, this.bg);
  }
}