package com.bestcoolfungames.antsmasher;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.bestcoolfungames.util.Util;
import com.google.ads.AdView;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class HighScores extends Activity
{
  public static AdView ad;
  boolean alreadyDoneScreenFix = false;
  private ImageView backButton;
  private ImageView global;
  boolean isGoingBackToMainScreen = false;
  RelativeLayout layoutView;
  private ImageView local;
  Context mContext;
  TextView[] names;
  TextView[] points;
  private RelativeLayout scoresTable;
  private String selectedMode = "local";
  public boolean showLocal;
  boolean timerControl;
  String[] values;

  protected void finalize()
  {
    Log.i("TAG", "Bailing HS");
    try
    {
      finalize();
      label12: return;
    }
    catch (Throwable localThrowable)
    {
      break label12:
    }
  }

  public void onBackPressed()
  {
    InitialView.playButtonSound("Out", this.mContext);
    this.isGoingBackToMainScreen = true;
    SharedPreferences.Editor localEditor = getSharedPreferences("AppData", 0).edit();
    localEditor.putInt("State", 5);
    localEditor.commit();
  }

  public void onCreate(Bundle paramBundle)
  {
    Util.setContext(getApplicationContext());
    this.mContext = getApplicationContext();
    SharedPreferences localSharedPreferences = getSharedPreferences("AppData", 0);
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    localEditor.putBoolean("CanChangeAd1", true);
    localEditor.commit();
    super.onCreate(paramBundle);
    setContentView(2130903045);
    setRequestedOrientation(1);
    this.backButton = ((ImageView)findViewById(2131230751));
    this.scoresTable = ((RelativeLayout)findViewById(2131230749));
    this.local = ((ImageView)findViewById(2131230752));
    this.global = ((ImageView)findViewById(2131230753));
    this.backButton.setVisibility(4);
    this.scoresTable.setVisibility(4);
    this.local.setVisibility(4);
    this.global.setVisibility(4);
    new AlphaAnimation(0F, 1F).setDuration(500);
    setVolumeControlStream(3);
    this.timerControl = true;
    this.showLocal = true;
    overridePendingTransition(2130968576, 2130968577);
    this.layoutView = ((RelativeLayout)findViewById(2131230728));
    this.backButton.setOnTouchListener(new View.OnTouchListener(this)
    {
      public boolean onTouch(, MotionEvent paramMotionEvent)
      {
        InitialView.playButtonSound("Out", this.this$0.mContext);
        this.this$0.isGoingBackToMainScreen = true;
        SharedPreferences.Editor localEditor = this.this$0.getSharedPreferences("AppData", 0).edit();
        localEditor.putInt("State", 5);
        localEditor.commit();
        return false;
      }
    });
    this.local.setOnTouchListener(new View.OnTouchListener(this)
    {
      public boolean onTouch(, MotionEvent paramMotionEvent)
      {
        if (!(HighScores.access$0(this.this$0).equals("local")))
        {
          InitialView.playButtonSound("In", this.this$0.mContext);
          HighScores.access$1(this.this$0, "local");
          this.this$0.viewLocal();
          HighScores.access$2(this.this$0).setBackgroundResource(2130837609);
          HighScores.access$3(this.this$0).setBackgroundResource(2130837598);
          this.this$0.showLocal = true;
        }
        return false;
      }
    });
    this.global.setOnTouchListener(new View.OnTouchListener(this)
    {
      public boolean onTouch(, MotionEvent paramMotionEvent)
      {
        if (!(HighScores.access$0(this.this$0).equals("global")))
        {
          InitialView.playButtonSound("In", this.this$0.mContext);
          HighScores.access$1(this.this$0, "global");
          this.this$0.viewGlobal();
          HighScores.access$2(this.this$0).setBackgroundResource(2130837608);
          HighScores.access$3(this.this$0).setBackgroundResource(2130837599);
          this.this$0.showLocal = false;
        }
        return false;
      }
    });
    ((TextView)findViewById(2131230750)).setVisibility(4);
    findViewById(2131230753).getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(this, this, localSharedPreferences)
    {
      public boolean onPreDraw()
      {
        int j;
        int k;
        RelativeLayout.LayoutParams localLayoutParams1;
        float f;
        byte b;
        if ((this.this$0.alreadyDoneScreenFix) && (HighScores.access$4(this.this$0).getVisibility() == 4))
        {
          HighScores.access$4(this.this$0).setVisibility(0);
          HighScores.access$5(this.this$0).setVisibility(0);
          HighScores.access$2(this.this$0).setVisibility(0);
          HighScores.access$3(this.this$0).setVisibility(0);
        }
        if ((!(this.this$0.alreadyDoneScreenFix)) && (this.val$activity.getResources().getConfiguration().orientation == 1))
        {
          int i = this.this$0.mContext.getResources().getDisplayMetrics().heightPixels;
          j = this.this$0.mContext.getResources().getDisplayMetrics().widthPixels;
          k = i - Util.dipToPx(50);
          localLayoutParams1 = (RelativeLayout.LayoutParams)this.this$0.findViewById(2131230748).getLayoutParams();
          if (localLayoutParams1.height < k)
            break label416;
          localLayoutParams1.height = (k - Util.dipToPx(15));
        }
        while (true)
        {
          do
          {
            localLayoutParams1.width = j;
            Log.i("bug", "BUG CATCHER 2");
            localLayoutParams1.topMargin = ((k - localLayoutParams1.height) / 2);
            (localLayoutParams1.width / 316.0F);
            (localLayoutParams1.height / 485.0F);
            RelativeLayout.LayoutParams localLayoutParams2 = (RelativeLayout.LayoutParams)this.this$0.findViewById(2131230751).getLayoutParams();
            Util.fixViewScale(HighScores.access$2(this.this$0), 0, 0, 0, -localLayoutParams2.height / 2, 1F, 1F);
            Util.fixViewScale(HighScores.access$3(this.this$0), 0, 0, 0, -localLayoutParams2.height / 2, 1F, 1F);
            RelativeLayout.LayoutParams localLayoutParams3 = (RelativeLayout.LayoutParams)this.this$0.findViewById(2131230749).getLayoutParams();
            localLayoutParams3.width = (localLayoutParams1.width - localLayoutParams1.width / 15);
            localLayoutParams3.height = (localLayoutParams1.height - 2 * localLayoutParams2.height);
            f = localLayoutParams3.height / 302.0F;
            this.this$0.names = new TextView[7];
            this.this$0.points = new TextView[7];
            b = 0;
            if (b < 7)
              break label455;
            Log.i("bug", "BUG CATCHER 3");
            this.this$0.findViewById(2131230728).setVisibility(0);
            this.this$0.alreadyDoneScreenFix = true;
            label416: return true;
          }
          while ((localLayoutParams1.height >= k) || (k - localLayoutParams1.height <= Util.dipToPx(100)));
          localLayoutParams1.height = (k - Util.dipToPx(75));
        }
        label455: this.this$0.names[b] = new TextView(this.this$0.getApplication().getApplicationContext());
        this.this$0.names[b].bringToFront();
        RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        localLayoutParams4.topMargin = (int)(f * (46 + b * 35));
        localLayoutParams4.leftMargin = (int)(70.0F * f);
        this.this$0.names[b].setLayoutParams(localLayoutParams4);
        HighScores.access$4(this.this$0).addView(this.this$0.names[b]);
        this.this$0.names[b].setText(this.val$settings.getString("MEM-Names" + b, ""));
        this.this$0.names[b].setTextSize(20.0F);
        this.this$0.names[b].setTextColor(-1);
        this.this$0.points[b] = new TextView(this.this$0.getApplication().getApplicationContext());
        this.this$0.points[b].bringToFront();
        RelativeLayout.LayoutParams localLayoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        localLayoutParams5.addRule(11);
        localLayoutParams5.topMargin = (int)(f * (46 + b * 35));
        localLayoutParams5.rightMargin = (int)(35.0F * f);
        this.this$0.points[b].setLayoutParams(localLayoutParams5);
        HighScores.access$4(this.this$0).addView(this.this$0.points[b]);
        this.this$0.points[b].setTextSize(20.0F);
        this.this$0.points[b].setTextColor(-1);
        if (this.this$0.names[b].getText().toString().trim().compareTo("") != 0)
          this.this$0.points[b].setText(this.val$settings.getInt(new StringBuilder("MEM-Points").append(b).toString(), 0));
        while (true)
        {
          while (this.this$0.points[b].getText().length() >= 4)
            ++b;
          this.this$0.points[b].setText(" " + this.this$0.points[b].getText());
        }
      }
    });
  }

  protected void onPause()
  {
    if (!(this.isGoingBackToMainScreen))
    {
      if ((InitialView.backgroundMp != null) && (!(InitialView.backgroundMpErrored.booleanValue())) && (InitialView.backgroundMp.isPlaying()))
        InitialView.backgroundMp.stop();
      Log.i("AS", "Going to quit app");
      SharedPreferences.Editor localEditor2 = getSharedPreferences("AntSmasherShop", 0).edit();
      localEditor2.putBoolean("InitialProductAlreadyOffered", false);
      localEditor2.commit();
      SharedPreferences.Editor localEditor1 = getSharedPreferences("AppData", 0).edit();
      localEditor1.putBoolean("GamePaused", true);
      localEditor1.commit();
    }
    try
    {
      GoogleAnalyticsTracker.getInstance().stop();
    }
    catch (Exception localException1)
    {
      try
      {
        while (true)
        {
          AdsUtils.removeAllAdViews(this, this.layoutView);
          label127: super.onPause();
          return;
          this.isGoingBackToMainScreen = false;
          Log.i("AS", "Going to change screen");
        }
        localException1 = localException1;
        localException1.printStackTrace();
      }
      catch (Exception localException2)
      {
        break label127:
      }
    }
  }

  protected void onResume()
  {
    super.onResume();
    SharedPreferences.Editor localEditor = getSharedPreferences("AppData", 0).edit();
    localEditor.putBoolean("GamePaused", false);
    localEditor.commit();
    if (InitialView.mainInstance == null)
      finish();
    while (true)
    {
      return;
      InitialView.createBackgroundMusic(this);
    }
  }

  protected void onStart()
  {
    InitialView.shownActivity = this;
    InitialView.createBackgroundMusic(this);
    try
    {
      GoogleAnalyticsTracker.getInstance().start("UA-21142514-5", 10, this);
      GoogleAnalyticsTracker.getInstance().trackEvent("Activity", "Activity_HighScores_Enter", "", -1);
      AdsUtils.configureBanner(this, this.layoutView, null);
      super.onStart();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void setGlobal()
  {
    int i = 0;
    while (true)
    {
      if (i >= this.values.length)
        return;
      this.names[(i / 2)].setText(this.values[i]);
      this.points[(i / 2)].setText(this.values[(i + 1)]);
      i += 2;
    }
  }

  public void viewGlobal()
  {
    byte b = 0;
    while (true)
    {
      if (b >= 7)
      {
        this.names[0].setText(getString(2131165204));
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
            String str;
            try
            {
              str = new BufferedReader(new InputStreamReader(new URL("http://bestcoolfungames.com/api/scores/query.php?code=4199").openStream())).readLine();
              this.this$0.values = str.split("\\#\\!\\?A\\#", 14);
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
              localArrayIndexOutOfBoundsException.printStackTrace();
            }
            catch (Exception localException)
            {
              while (true)
                localException.printStackTrace();
            }
          }
        }).start();
        return;
      }
      this.names[b].setText("");
      this.points[b].setText("");
      ++b;
    }
  }

  public void viewLocal()
  {
    SharedPreferences localSharedPreferences = getSharedPreferences("AppData", 0);
    byte b = 0;
    if (b >= 7)
      return;
    this.names[b].setText(localSharedPreferences.getString("MEM-Names" + b, ""));
    if (this.names[b].getText().toString().trim().compareTo("") != 0)
      this.points[b].setText(localSharedPreferences.getInt(new StringBuilder("MEM-Points").append(b).toString(), 0));
    while (true)
    {
      while (true)
      {
        while (true)
        {
          if (this.points[b].getText().length() < 4)
            break label163;
          ++b;
        }
        this.points[b].setText("");
      }
      label163: this.points[b].setText(" " + this.points[b].getText());
    }
  }
}