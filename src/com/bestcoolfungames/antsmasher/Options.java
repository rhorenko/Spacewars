package com.bestcoolfungames.antsmasher;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.bestcoolfungames.util.Util;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;
import java.util.Locale;

public class Options extends Activity
{
  boolean alreadyDoneScreenFix = false;
  private Boolean analyticsAlreadyDone;
  boolean isChangingScreen = false;
  RelativeLayout layoutView;
  Context mContext;
  private RelativeLayout optionsBox;
  boolean timerControl;

  public Options()
  {
    this.analyticsAlreadyDone = Boolean.valueOf(false);
  }

  private void startAnalytics()
  {
    try
    {
      GoogleAnalyticsTracker.getInstance().start("UA-21142514-5", 10, this);
      if (!(this.analyticsAlreadyDone.booleanValue()))
      {
        GoogleAnalyticsTracker.getInstance().trackEvent("Activity", "Activity_Options_Enter", "", -1);
        this.analyticsAlreadyDone = Boolean.valueOf(true);
      }
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  protected void finalize()
  {
    Log.i("TAG", "Bailing Opt");
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
    this.isChangingScreen = true;
    SharedPreferences.Editor localEditor = getSharedPreferences("AppData", 0).edit();
    localEditor.putInt("State", 5);
    localEditor.commit();
  }

  public void onCreate(Bundle paramBundle)
  {
    Util.setContext(getApplicationContext());
    this.mContext = getApplicationContext();
    SharedPreferences localSharedPreferences1 = getSharedPreferences("AppData", 0);
    SharedPreferences.Editor localEditor1 = localSharedPreferences1.edit();
    localEditor1.putBoolean("CanChangeAd3", true);
    localEditor1.commit();
    SharedPreferences localSharedPreferences2 = getSharedPreferences("AntSmasherShop", 0);
    SharedPreferences.Editor localEditor2 = localSharedPreferences2.edit();
    super.onCreate(paramBundle);
    setContentView(2130903051);
    setRequestedOrientation(1);
    setVolumeControlStream(3);
    overridePendingTransition(2130968576, 2130968577);
    this.layoutView = ((RelativeLayout)findViewById(2131230778));
    this.timerControl = true;
    new AlphaAnimation(0F, 1F).setDuration(500);
    this.optionsBox = ((RelativeLayout)findViewById(2131230779));
    ImageView localImageView1 = (ImageView)findViewById(2131230739);
    ImageView localImageView2 = (ImageView)findViewById(2131230788);
    ImageView localImageView3 = (ImageView)findViewById(2131230786);
    ImageView localImageView4 = (ImageView)findViewById(2131230784);
    ImageView localImageView5 = (ImageView)findViewById(2131230783);
    ImageView localImageView6 = (ImageView)findViewById(2131230790);
    this.optionsBox.setVisibility(4);
    Typeface localTypeface1 = Typeface.createFromAsset(getAssets(), "fonts/House-A-Rama-League-Night.otf");
    Typeface localTypeface2 = Typeface.createFromAsset(getAssets(), "fonts/House-A-Rama-Kingpin.otf");
    TextView localTextView1 = (TextView)findViewById(2131230785);
    TextView localTextView2 = (TextView)findViewById(2131230787);
    TextView localTextView3 = (TextView)findViewById(2131230789);
    TextView localTextView4 = (TextView)findViewById(2131230780);
    TextView localTextView5 = (TextView)findViewById(2131230781);
    TextView localTextView6 = (TextView)findViewById(2131230782);
    String str = this.mContext.getResources().getConfiguration().locale.getLanguage();
    Log.i("AS", "language: " + str);
    if ((!(str.equals("iw"))) && (!(str.equals("zh"))) && (!(str.equals("ja"))))
    {
      localTextView1.setTypeface(localTypeface1);
      localTextView2.setTypeface(localTypeface1);
      localTextView3.setTypeface(localTypeface2);
      localTextView4.setTypeface(localTypeface1);
      localTextView5.setTypeface(localTypeface1);
      localTextView6.setTypeface(localTypeface1);
    }
    str.equals("en12341234");
    localTextView1.setVisibility(4);
    localTextView2.setVisibility(4);
    localTextView3.setVisibility(4);
    localTextView4.setVisibility(4);
    localTextView5.setVisibility(4);
    localTextView6.setVisibility(4);
    if (!(localSharedPreferences2.getBoolean("Protection", false)))
      localTextView3.setVisibility(4);
    localImageView1.setOnClickListener(new View.OnClickListener(this)
    {
      public void onClick()
      {
        InitialView.playButtonSound("Out", this.this$0.mContext);
        this.this$0.isChangingScreen = true;
        SharedPreferences.Editor localEditor = this.this$0.getSharedPreferences("AppData", 0).edit();
        localEditor.putInt("State", 5);
        localEditor.commit();
      }
    });
    localImageView3.setOnClickListener(new View.OnClickListener(this)
    {
      public void onClick()
      {
        InitialView.playButtonSound("In", this.this$0.mContext);
        SharedPreferences.Editor localEditor = this.this$0.getSharedPreferences("AppData", 0).edit();
        byte b = 0;
        while (true)
        {
          if (b >= 7)
          {
            localEditor.commit();
            Toast.makeText(this.this$0.getApplication().getApplicationContext(), this.this$0.getResources().getString(2131165226), 2500).show();
            return;
          }
          localEditor.remove("MEM-Points" + b);
          localEditor.remove("MEM-Names" + b);
          ++b;
        }
      }
    });
    if (!(localSharedPreferences2.getBoolean("Protection", false)))
      localImageView2.setVisibility(4);
    localImageView2.setOnClickListener(new View.OnClickListener(this, localSharedPreferences2, localImageView2, localEditor2)
    {
      public void onClick()
      {
        String str;
        InitialView.playButtonSound("In", this.this$0.mContext);
        if (this.val$shopSettings.getBoolean("ProtectionEnabled", false))
        {
          this.val$gopButton.setBackgroundResource(2130837597);
          this.val$shopEditor.putBoolean("ProtectionEnabled", false);
          str = "disabled";
        }
        while (true)
        {
          this.val$shopEditor.commit();
          Log.i("GOP", str);
          return;
          this.val$gopButton.setBackgroundResource(2130837600);
          this.val$shopEditor.putBoolean("ProtectionEnabled", true);
          str = "enabled";
        }
      }
    });
    if (localSharedPreferences1.getBoolean("Vibration", true))
    {
      localImageView4.setBackgroundResource(2130837666);
      if (!(localSharedPreferences1.getBoolean("Sound", true)))
        break label665;
      localImageView5.setBackgroundResource(2130837652);
    }
    while (true)
    {
      while (true)
      {
        localImageView4.setOnClickListener(new View.OnClickListener(this, localImageView4)
        {
          public void onClick()
          {
            SharedPreferences localSharedPreferences = this.this$0.getSharedPreferences("AppData", 0);
            SharedPreferences.Editor localEditor = localSharedPreferences.edit();
            if (localSharedPreferences.getBoolean("Vibration", true))
            {
              this.val$vibrateButton.setBackgroundResource(2130837621);
              localEditor.putBoolean("Vibration", false);
            }
            while (true)
            {
              localEditor.commit();
              return;
              this.val$vibrateButton.setBackgroundResource(2130837666);
              localEditor.putBoolean("Vibration", true);
            }
          }
        });
        localImageView5.setOnClickListener(new View.OnClickListener(this, localImageView5)
        {
          public void onClick()
          {
            SharedPreferences localSharedPreferences = this.this$0.getSharedPreferences("AppData", 0);
            SharedPreferences.Editor localEditor = localSharedPreferences.edit();
            if (localSharedPreferences.getBoolean("Sound", true))
            {
              if ((InitialView.backgroundMp != null) && (!(InitialView.backgroundMpErrored.booleanValue())) && (InitialView.backgroundMp.isPlaying()))
                InitialView.backgroundMp.stop();
              this.val$soundButton.setBackgroundResource(2130837620);
              localEditor.putBoolean("Sound", false);
              localEditor.commit();
            }
            while (true)
            {
              return;
              this.val$soundButton.setBackgroundResource(2130837652);
              localEditor.putBoolean("Sound", true);
              localEditor.commit();
              InitialView.createBackgroundMusic(this.this$0.mContext);
            }
          }
        });
        this.optionsBox.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(this, this, localImageView5, localImageView4, localImageView6, localImageView3, localImageView1, localImageView2, localSharedPreferences2)
        {
          public boolean onPreDraw()
          {
            int j;
            int k;
            RelativeLayout.LayoutParams localLayoutParams1;
            if ((this.this$0.alreadyDoneScreenFix) && (Options.access$0(this.this$0).getVisibility() == 4))
              Options.access$0(this.this$0).setVisibility(0);
            if ((!(this.this$0.alreadyDoneScreenFix)) && (this.val$activity.getResources().getConfiguration().orientation == 1))
            {
              int i = this.this$0.mContext.getResources().getDisplayMetrics().heightPixels;
              j = this.this$0.mContext.getResources().getDisplayMetrics().widthPixels;
              k = i - Util.dipToPx(50);
              localLayoutParams1 = (RelativeLayout.LayoutParams)Options.access$0(this.this$0).getLayoutParams();
              if (localLayoutParams1.height < k)
                break label639;
              localLayoutParams1.height = (k - Util.dipToPx(15));
              if (localLayoutParams1.width < j)
                break label680;
              localLayoutParams1.width = j;
              localLayoutParams1.topMargin = ((k - localLayoutParams1.height) / 2);
              localLayoutParams1.leftMargin = ((j - localLayoutParams1.width) / 2);
              float f1 = localLayoutParams1.width / 314.0F;
              float f2 = localLayoutParams1.height / 408.0F;
              RelativeLayout.LayoutParams localLayoutParams2 = (RelativeLayout.LayoutParams)this.val$soundButton.getLayoutParams();
              localLayoutParams2.rightMargin = (int)(43.0F * f1);
              localLayoutParams2.topMargin = (int)(56.0F * f2);
              localLayoutParams2.width = (int)(f2 * 48.0F);
              localLayoutParams2.height = (int)(f2 * 48.0F);
              this.val$soundButton.setLayoutParams(localLayoutParams2);
              RelativeLayout.LayoutParams localLayoutParams3 = (RelativeLayout.LayoutParams)this.val$vibrateButton.getLayoutParams();
              localLayoutParams3.rightMargin = (int)(120.0F * f1);
              localLayoutParams3.topMargin = (int)(56.0F * f2);
              localLayoutParams3.width = (int)(f2 * 48.0F);
              localLayoutParams3.height = (int)(f2 * 48.0F);
              this.val$vibrateButton.setLayoutParams(localLayoutParams3);
              RelativeLayout.LayoutParams localLayoutParams4 = (RelativeLayout.LayoutParams)this.val$frame01.getLayoutParams();
              localLayoutParams4.leftMargin = (int)(130.0F * f1);
              localLayoutParams4.topMargin = (int)(152.0F * f2);
              localLayoutParams4.width = (int)(f2 * 48.0F);
              localLayoutParams4.height = (int)(72.0F * f2);
              this.val$frame01.setLayoutParams(localLayoutParams4);
              RelativeLayout.LayoutParams localLayoutParams5 = (RelativeLayout.LayoutParams)this.val$resetButton.getLayoutParams();
              localLayoutParams5.rightMargin = (int)(13.0F * f1);
              localLayoutParams5.bottomMargin = (int)(70.0F * f2);
              localLayoutParams5.width = (int)(137.0F * f2);
              localLayoutParams5.height = (int)(84.0F * f2);
              this.val$resetButton.setLayoutParams(localLayoutParams5);
              RelativeLayout.LayoutParams localLayoutParams6 = (RelativeLayout.LayoutParams)this.val$menuButton.getLayoutParams();
              localLayoutParams6.leftMargin = (int)(f1 * 22.0F);
              localLayoutParams6.bottomMargin = (int)(38.0F * f2);
              localLayoutParams6.width = (int)(132.0F * f2);
              localLayoutParams6.height = (int)(69.0F * f2);
              this.val$menuButton.setLayoutParams(localLayoutParams6);
              RelativeLayout.LayoutParams localLayoutParams7 = (RelativeLayout.LayoutParams)this.val$gopButton.getLayoutParams();
              localLayoutParams7.leftMargin = (int)(f1 * 22.0F);
              localLayoutParams7.bottomMargin = (int)(108.0F * f2);
              localLayoutParams7.width = (int)(2F * 180.0F * f2 / 3.0F);
              localLayoutParams7.height = (int)(2F * 100.0F * f2 / 3.0F);
              this.val$gopButton.setLayoutParams(localLayoutParams7);
              if (!(this.val$shopSettings.getBoolean("ProtectionEnabled", false)))
                break label710;
              this.val$gopButton.setBackgroundResource(2130837600);
            }
            while (true)
            {
              while (true)
              {
                label680: 
                do
                  while (true)
                  {
                    do
                    {
                      this.this$0.alreadyDoneScreenFix = true;
                      label639: return true;
                    }
                    while ((localLayoutParams1.height >= k) || ((k - localLayoutParams1.height) / 2 <= Util.dipToPx(50)));
                    localLayoutParams1.height = (k - Util.dipToPx(75));
                  }
                while (localLayoutParams1.width >= j - Util.dipToPx(30));
                localLayoutParams1.width = (j - Util.dipToPx(30));
              }
              label710: this.val$gopButton.setBackgroundResource(2130837597);
            }
          }
        });
        return;
        localImageView4.setBackgroundResource(2130837621);
      }
      label665: localImageView5.setBackgroundResource(2130837620);
    }
  }

  protected void onPause()
  {
    if (!(this.isChangingScreen))
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
          label125: super.onPause();
          return;
          Log.i("AS", "Going to change screen");
          this.isChangingScreen = false;
        }
        localException1 = localException1;
        localException1.printStackTrace();
      }
      catch (Exception localException2)
      {
        break label125:
      }
    }
  }

  protected void onResume()
  {
    SharedPreferences.Editor localEditor = getSharedPreferences("AppData", 0).edit();
    localEditor.putBoolean("GamePaused", false);
    localEditor.commit();
    super.onResume();
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
    startAnalytics();
    InitialView.shownActivity = this;
    InitialView.createBackgroundMusic(this);
    super.onStart();
  }
}