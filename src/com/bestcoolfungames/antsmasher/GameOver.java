package com.bestcoolfungames.antsmasher;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.bestcoolfungames.util.Util;
import com.google.ads.AdView;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;
import com.revmob.RevMobAds;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class GameOver extends Activity
{
  public static AdView ad;
  public static int sessionRun = 0;
  private DialogInterface.OnClickListener _neutralPopupOnClickListener;
  private DialogInterface.OnClickListener _noPopupOnClickListener;
  private DialogInterface.OnClickListener _yesPopupOnClickListener;
  private Boolean alreadyDoneScreenFix;
  private Boolean analyticsAlreadyDone;
  private ImageView facebookButton;
  private int gameOverProduct;
  boolean isChangingScreen = false;
  public RelativeLayout layoutView;
  private ImageView logo;
  private Context mContext;
  private ImageView menuButton;
  private ImageView playButton;
  private TextView playerCurrentScore;
  private TextView playerHighScore;
  private EditText playerNameScore;
  private RelativeLayout scoreBox;
  private ImageView twitterButton;

  public GameOver()
  {
    this.alreadyDoneScreenFix = Boolean.valueOf(false);
    this.analyticsAlreadyDone = Boolean.valueOf(false);
  }

  private void adjustLayout()
  {
    this.logo.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(this, this)
    {
      public boolean onPreDraw()
      {
        int k;
        int l;
        int i2;
        int i3;
        if ((GameOver.access$1(this.this$0).booleanValue()) && (GameOver.access$2(this.this$0).getVisibility() == 4))
        {
          GameOver.access$2(this.this$0).setVisibility(0);
          GameOver.access$3(this.this$0).setVisibility(0);
          GameOver.access$4(this.this$0).setVisibility(0);
          GameOver.access$5(this.this$0).setVisibility(0);
          GameOver.access$6(this.this$0).setVisibility(0);
          GameOver.access$7(this.this$0).setVisibility(0);
        }
        if ((!(GameOver.access$1(this.this$0).booleanValue())) && (this.val$activity.getResources().getConfiguration().orientation == 1))
        {
          int i = GameOver.access$8(this.this$0).getResources().getDisplayMetrics().heightPixels - GameOver.access$2(this.this$0).getHeight() + GameOver.access$3(this.this$0).getHeight() + GameOver.access$4(this.this$0).getHeight() + GameOver.access$5(this.this$0).getHeight() + Util.dipToPx(50);
          if (i >= 0)
            break label560;
          int j = 3;
          k = i / j;
          if (k >= 0)
            break label565;
          l = 0;
          RelativeLayout.LayoutParams localLayoutParams1 = (RelativeLayout.LayoutParams)GameOver.access$2(this.this$0).getLayoutParams();
          localLayoutParams1.topMargin = l;
          GameOver.access$2(this.this$0).setLayoutParams(localLayoutParams1);
          int i1 = k + l + GameOver.access$2(this.this$0).getHeight();
          RelativeLayout.LayoutParams localLayoutParams2 = (RelativeLayout.LayoutParams)GameOver.access$3(this.this$0).getLayoutParams();
          localLayoutParams2.topMargin = i1;
          GameOver.access$3(this.this$0).setLayoutParams(localLayoutParams2);
          i2 = i1 + GameOver.access$3(this.this$0).getHeight();
          if (k >= 0)
            break label571;
          i3 = (int)(-GameOver.access$4(this.this$0).getHeight() / 3.7999999999999998D);
        }
        while (true)
        {
          while (true)
          {
            while (true)
            {
              int i5 = i2 + i3;
              RelativeLayout.LayoutParams localLayoutParams3 = (RelativeLayout.LayoutParams)GameOver.access$4(this.this$0).getLayoutParams();
              localLayoutParams3.topMargin = i5;
              GameOver.access$4(this.this$0).setLayoutParams(localLayoutParams3);
              int i6 = i5 + GameOver.access$4(this.this$0).getHeight();
              if (k < 0)
                k = (int)(-GameOver.access$5(this.this$0).getHeight() / 3.6000000000000001D);
              int i7 = i6 + k;
              RelativeLayout.LayoutParams localLayoutParams4 = (RelativeLayout.LayoutParams)GameOver.access$5(this.this$0).getLayoutParams();
              localLayoutParams4.topMargin = i7;
              GameOver.access$5(this.this$0).setLayoutParams(localLayoutParams4);
              int i8 = 3 + (i5 + i7) / 2;
              RelativeLayout.LayoutParams localLayoutParams5 = (RelativeLayout.LayoutParams)GameOver.access$7(this.this$0).getLayoutParams();
              localLayoutParams5.topMargin = i8;
              GameOver.access$7(this.this$0).setLayoutParams(localLayoutParams5);
              int i9 = -2 + i8 + GameOver.access$6(this.this$0).getHeight() / 2;
              RelativeLayout.LayoutParams localLayoutParams6 = (RelativeLayout.LayoutParams)GameOver.access$6(this.this$0).getLayoutParams();
              localLayoutParams6.topMargin = i9;
              GameOver.access$6(this.this$0).setLayoutParams(localLayoutParams6);
              GameOver.access$9(this.this$0, Boolean.valueOf(true));
              return true;
              label560: byte b = 5;
            }
            label565: l = k;
          }
          label571: int i4 = k;
        }
      }
    });
  }

  private DialogInterface.OnClickListener getNeutralPopupOnClickListener()
  {
    if (this._neutralPopupOnClickListener == null)
      this._neutralPopupOnClickListener = new DialogInterface.OnClickListener(this)
      {
        public void onClick(, int paramInt)
        {
          paramDialogInterface.dismiss();
          Log.i("AS", "Bought product with TapJoyPoints");
          InitialView.deliverTapJoyProduct(InitialView.shownActivity);
        }
      };
    return this._neutralPopupOnClickListener;
  }

  private DialogInterface.OnClickListener getNoPopupOnClickListener()
  {
    if (this._noPopupOnClickListener == null)
      this._noPopupOnClickListener = new DialogInterface.OnClickListener(this)
      {
        public void onClick(, int paramInt)
        {
          paramDialogInterface.dismiss();
        }
      };
    return this._noPopupOnClickListener;
  }

  private DialogInterface.OnClickListener getYesPopupOnClickListener()
  {
    if (this._yesPopupOnClickListener == null)
      this._yesPopupOnClickListener = new DialogInterface.OnClickListener(this)
      {
        public void onClick(, int paramInt)
        {
          SharedPreferences.Editor localEditor = this.this$0.getSharedPreferences("AppData", 0).edit();
          this.this$0.updateScores();
          localEditor.putInt("State", 11);
          localEditor.putInt("gameOverProduct", GameOver.access$10(this.this$0));
          localEditor.commit();
        }
      };
    return this._yesPopupOnClickListener;
  }

  private boolean isOnline()
  {
    byte b;
    NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting()))
      b = 1;
    while (true)
    {
      return b;
      b = 0;
    }
  }

  private void savePlayerName(SharedPreferences.Editor paramEditor)
  {
    paramEditor.putString("MEM-LastName", this.playerNameScore.getText().toString());
    paramEditor.commit();
  }

  private void startAnalytics()
  {
    try
    {
      GoogleAnalyticsTracker.getInstance().start("UA-21142514-5", 10, this);
      if (!(this.analyticsAlreadyDone.booleanValue()))
      {
        GoogleAnalyticsTracker.getInstance().trackEvent("Activity", "Activity_GameOver_Enter", "", -1);
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

  public boolean isCNLocale(Context paramContext)
  {
    boolean bool;
    if ((paramContext != null) && (paramContext.getResources() != null) && (paramContext.getResources().getConfiguration() != null) && (paramContext.getResources().getConfiguration().locale != null))
      bool = paramContext.getResources().getConfiguration().locale.getISO3Country().equals("CANADA");
    while (true)
    {
      return bool;
      byte b = 0;
    }
  }

  public boolean isUSLocale(Context paramContext)
  {
    boolean bool;
    if ((paramContext != null) && (paramContext.getResources() != null) && (paramContext.getResources().getConfiguration() != null) && (paramContext.getResources().getConfiguration().locale != null))
      bool = paramContext.getResources().getConfiguration().locale.getISO3Country().equals("USA");
    while (true)
    {
      return bool;
      byte b = 0;
    }
  }

  public void onBackPressed()
  {
    InitialView.playButtonSound("Out", this.mContext);
    this.alreadyDoneScreenFix = true;
    updateScores();
    SharedPreferences.Editor localEditor = getSharedPreferences("AppData", 0).edit();
    localEditor.putInt("State", 5);
    localEditor.commit();
  }

  public void onCreate(Bundle paramBundle)
  {
    long l3;
    long l4;
    SharedPreferences localSharedPreferences = getSharedPreferences("AppData", 0);
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    long l1 = localSharedPreferences.getLong("StartGameDate", 0L);
    if (l1 > 0L)
    {
      long l2 = new Date().getTime();
      l3 = localSharedPreferences.getLong("PlayTimeDiscount", 0L);
      if (!(l3 > 0L))
        break label1216;
      l4 = (int)((l2 - l1 - l3) / 1000);
      localEditor.putLong("PlayTimeDiscount", 0L);
      int j = localSharedPreferences.getInt("GameMode", 0);
      if ((l4 > 1200) && (j == 0));
    }
    try
    {
      String str3 = Constants.key.getABDifficultyLabel(this) + "_AlreadyDone";
      if (!(Boolean.valueOf(localSharedPreferences.getBoolean(str3, false)).booleanValue()))
      {
        float f = localSharedPreferences.getFloat(Constants.key.getABDifficultyLabel(this), 0F);
        if (!(f < 0F))
          f = 3.5F;
        new HashMap().put("PlayTime[difficulty=" + String.valueOf(f) + "]", String.valueOf(l4));
        GoogleAnalyticsTracker.getInstance().trackEvent(Constants.key.getABDifficultyLabel(this), "Scores_PlayTime[difficulty=" + String.valueOf(f) + "]", String.valueOf(l4), (int)l4);
        localEditor.putBoolean(str3, true);
      }
      localEditor.putLong("StartGameDate", 0L);
      localEditor.commit();
      super.onCreate(paramBundle);
      setContentView(2130903044);
      setRequestedOrientation(1);
      this.mContext = getApplicationContext();
      InitialView.createBackgroundMusic(this.mContext);
      setVolumeControlStream(3);
      overridePendingTransition(2130968576, 2130968577);
      this.layoutView = ((RelativeLayout)findViewById(2131230728));
      this.logo = ((ImageView)findViewById(2131230742));
      this.scoreBox = ((RelativeLayout)findViewById(2131230743));
      this.playButton = ((ImageView)findViewById(2131230738));
      this.menuButton = ((ImageView)findViewById(2131230739));
      this.twitterButton = ((ImageView)findViewById(2131230740));
      this.facebookButton = ((ImageView)findViewById(2131230741));
      this.logo.setVisibility(4);
      this.scoreBox.setVisibility(4);
      this.playButton.setVisibility(4);
      this.menuButton.setVisibility(4);
      this.twitterButton.setVisibility(4);
      this.facebookButton.setVisibility(4);
      new AlphaAnimation(0F, 1F).setDuration(500);
      ImageView localImageView1 = this.playButton;
      1 local1 = new View.OnClickListener(this, this)
      {
        public void onClick()
        {
          if ((InitialView.backgroundMp != null) && (!(InitialView.backgroundMpErrored.booleanValue())) && (InitialView.backgroundMp.isPlaying()))
            InitialView.backgroundMp.stop();
          this.this$0.updateScores();
          SharedPreferences localSharedPreferences = this.this$0.getSharedPreferences("AppData", 0);
          SharedPreferences.Editor localEditor = localSharedPreferences.edit();
          boolean bool = InitialView.canPlayMoreGames(this.val$mContext);
          InitialView.playButtonSound("In", this.val$mContext);
          this.this$0.isChangingScreen = true;
          if (bool);
          try
          {
            int i = 1 + localSharedPreferences.getInt("numberOfRetries", 0);
            float f = localSharedPreferences.getFloat(Constants.key.getABDifficultyLabel(this.val$mContext), 0F);
            GoogleAnalyticsTracker.getInstance().trackEvent(Constants.key.getABDifficultyLabel(this.val$mContext), "Retries[difficulty=" + String.valueOf(f) + "]", String.valueOf(i), -1);
            localEditor.putInt("numberOfRetries", i);
            localEditor.putInt("State", 4);
            localEditor.commit();
            return;
          }
          catch (Exception localException)
          {
            while (true)
              localException.printStackTrace();
          }
        }
      };
      localImageView1.setOnClickListener(local1);
      ImageView localImageView2 = this.menuButton;
      2 local2 = new View.OnClickListener(this, this)
      {
        public void onClick()
        {
          InitialView.playButtonSound("Out", this.val$mContext);
          this.this$0.isChangingScreen = true;
          this.this$0.updateScores();
          SharedPreferences.Editor localEditor = this.this$0.getSharedPreferences("AppData", 0).edit();
          localEditor.putInt("State", 5);
          localEditor.commit();
        }
      };
      localImageView2.setOnClickListener(local2);
      ImageView localImageView3 = this.twitterButton;
      3 local3 = new View.OnClickListener(this, this)
      {
        public void onClick()
        {
          InitialView.playButtonSound("In", this.val$mContext);
          this.this$0.isChangingScreen = true;
          SharedPreferences localSharedPreferences = this.this$0.getSharedPreferences("AppData", 0);
          SharedPreferences.Editor localEditor = localSharedPreferences.edit();
          GameOver.access$0(this.this$0, localEditor);
          localEditor.putInt("State", 6);
          localEditor.putInt("TwitterAux", localSharedPreferences.getInt("Points", 0));
          localEditor.putBoolean("GoingTweet", true);
          localEditor.commit();
        }
      };
      localImageView3.setOnClickListener(local3);
      ImageView localImageView4 = this.facebookButton;
      4 local4 = new View.OnClickListener(this, this)
      {
        public void onClick()
        {
          InitialView.playButtonSound("In", this.val$mContext);
          this.this$0.isChangingScreen = true;
          SharedPreferences localSharedPreferences = this.this$0.getSharedPreferences("AppData", 0);
          SharedPreferences.Editor localEditor = localSharedPreferences.edit();
          GameOver.access$0(this.this$0, localEditor);
          localEditor.putInt("State", 10);
          localEditor.putInt("TwitterAux", localSharedPreferences.getInt("Points", 0));
          localEditor.putBoolean("GoingFB", true);
          localEditor.commit();
        }
      };
      label1321: label1331: label1341: label1216: label1242: label1248: localImageView4.setOnClickListener(local4);
    }
    catch (Exception localException2)
    {
      byte b;
      try
      {
        while (true)
        {
          new HashMap().put("Discriminated", Integer.toString(10 * localSharedPreferences.getInt("Points", 0) / 10));
          GoogleAnalyticsTracker.getInstance().trackEvent("Scores", "Scores_Average", "", localSharedPreferences.getInt("Points", 0));
          GoogleAnalyticsTracker.getInstance().trackEvent("Scores", "Scores_Discriminated", Integer.toString(10 * localSharedPreferences.getInt("Points", 0) / 10), -1);
          this.playerCurrentScore = ((TextView)findViewById(2131230745));
          this.playerCurrentScore.setText(localSharedPreferences.getInt("Points", 0));
          this.playerHighScore = ((TextView)findViewById(2131230746));
          this.playerHighScore.setText(Math.max(localSharedPreferences.getInt("MEM-Points0", 0), localSharedPreferences.getInt("Points", 0)));
          this.playerNameScore = ((EditText)findViewById(2131230747));
          this.playerNameScore.setText(localSharedPreferences.getString("MEM-LastName", "Smasher"));
          if (localSharedPreferences.getBoolean("TwitterToast", false))
          {
            localEditor.putBoolean("TwitterToast", false);
            localEditor.commit();
            Toast.makeText(this, "Twitted", 0).show();
          }
          EditText localEditText = this.playerNameScore;
          5 local5 = new View.OnKeyListener(this)
          {
            public boolean onKey(, int paramInt, KeyEvent paramKeyEvent)
            {
              byte b;
              if (paramInt == 66)
                b = 1;
              while (true)
              {
                return b;
                b = 0;
              }
            }
          };
          localEditText.setOnKeyListener(local5);
          int i = localSharedPreferences.getInt("NumberOfPlayedGames", 0);
          if ((!(localSharedPreferences.getBoolean(Constants.key.getRatingLabel(this), true))) || (i != 3) || (!(isOnline())))
            break label1242;
          b = 1;
          if (Boolean.valueOf(localSharedPreferences.getBoolean("GoingTweet", false)).booleanValue())
          {
            localEditor.putBoolean("GoingTweet", false);
            localEditor.commit();
          }
          if (Boolean.valueOf(localSharedPreferences.getBoolean("GoingFB", false)).booleanValue())
          {
            localEditor.putBoolean("GoingFB", false);
            localEditor.commit();
          }
          if (b != 0)
          {
            AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(this);
            AlertDialog.Builder localBuilder2 = localBuilder1.setTitle(getString(2131165206)).setMessage(getString(2131165207));
            String str1 = getString(2131165210);
            6 local6 = new DialogInterface.OnClickListener(this, localSharedPreferences, this)
            {
              public void onClick(, int paramInt)
              {
                SharedPreferences.Editor localEditor = this.val$settings.edit();
                localEditor.putBoolean(Constants.key.getRatingLabel(this.val$mContext), false);
                localEditor.commit();
                paramDialogInterface.dismiss();
              }
            };
            AlertDialog.Builder localBuilder3 = localBuilder2.setPositiveButton(str1, local6);
            String str2 = getString(2131165209);
            7 local7 = new DialogInterface.OnClickListener(this, localEditor, this)
            {
              public void onClick(, int paramInt)
              {
                this.val$editor.putBoolean(Constants.key.getRatingLabel(this.val$mContext), false);
                this.val$editor.commit();
                try
                {
                  this.this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.bestcoolfungames.antsmasher")));
                  label49: paramDialogInterface.dismiss();
                  return;
                }
                catch (ActivityNotFoundException localActivityNotFoundException)
                {
                  break label49:
                }
              }
            };
            localBuilder3.setNegativeButton(str2, local7).show();
          }
          if ((InitialView.metas != null) && (isOnline()))
          {
            if ((InitialView.metas[1] == 0) || (sessionRun != 1))
              break label1248;
            showStoreItem();
          }
          localEditor.putInt("NumberOfPlayedGames", 1 + localSharedPreferences.getInt("NumberOfPlayedGames", 0));
          localEditor.putInt("NumberOfPlayedGamesSinceV29", 1 + localSharedPreferences.getInt("NumberOfPlayedGamesSinceV29", 0));
          localEditor.commit();
          adjustLayout();
          sessionRun = 1 + sessionRun;
          return;
          l3 = 0L;
        }
        localException2 = localException2;
        localException2.printStackTrace();
      }
      catch (Exception localException1)
      {
        while (true)
        {
          do
            while (true)
            {
              while (true)
              {
                while (true)
                {
                  while (true)
                  {
                    while (true)
                    {
                      while (true)
                        localException1.printStackTrace();
                      b = 0;
                    }
                    if ((InitialView.metas[8] == 0) || (sessionRun != 0))
                      break label1341;
                    if (InitialView.metas[6] == 0)
                      break label1331;
                    if (InitialView.metas[7] == 0)
                      break label1321;
                    Random localRandom = new Random(System.currentTimeMillis());
                    if (!(localRandom.nextBoolean()))
                      break;
                    RevMobAds.showFullscreenAd(this, Constants.BCFADS_NEW_FULLSCREEN_AD_ID);
                  }
                  RevMobAds.showFullscreenAd(this, Constants.BCFADS_NEW_FULLSCREEN_AD_ID);
                }
                RevMobAds.showFullscreenAd(this, Constants.BCFADS_NEW_FULLSCREEN_AD_ID);
              }
              RevMobAds.showFullscreenAd(this, Constants.BCFADS_NEW_FULLSCREEN_AD_ID);
            }
          while ((InitialView.metas[5] == 0) || (sessionRun != 2));
          RevMobAds.showFullscreenAd(this, Constants.BCFADS_NEW_FULLSCREEN_AD_ID);
        }
      }
    }
  }

  protected void onPause()
  {
    if (this.alreadyDoneScreenFix == 0)
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
          label128: super.onPause();
          return;
          Log.i("AS", "Going to change screen");
          this.alreadyDoneScreenFix = false;
        }
        localException1 = localException1;
        localException1.printStackTrace();
      }
      catch (Exception localException2)
      {
        break label128:
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
    startAnalytics();
    InitialView.shownActivity = this;
    InitialView.createBackgroundMusic(this);
    AdsUtils.configureBanner(this, this.layoutView, null);
    super.onStart();
  }

  public void showStoreItem()
  {
    SharedPreferences localSharedPreferences = getSharedPreferences("AntSmasherShop", 0);
    if (!(Constants.isPaidVersion()))
    {
      Boolean localBoolean = Boolean.valueOf(false);
      Log.i("AS", "Already offered? " + localBoolean);
      if (!(Boolean.valueOf(localSharedPreferences.getBoolean("DeluxePack", false)).booleanValue()))
      {
        this.gameOverProduct = 8;
        StorePopups.showDetails(this, localSharedPreferences, this.gameOverProduct, getYesPopupOnClickListener(), getNoPopupOnClickListener(), getNeutralPopupOnClickListener());
      }
    }
  }

  /**
   * @deprecated
   */
  public void updateScores()
  {
    SharedPreferences localSharedPreferences;
    SharedPreferences.Editor localEditor;
    byte b1;
    monitorenter;
    try
    {
      localSharedPreferences = getSharedPreferences("AppData", 0);
      localEditor = localSharedPreferences.edit();
      savePlayerName(localEditor);
      b1 = 0;
      label323: break label323:
    }
    finally
    {
      while (true)
        do
        {
          while (true)
          {
            byte b2;
            monitorexit;
            throw localObject;
            localEditor.putInt("MEM-Points" + b2, localSharedPreferences.getInt("MEM-Points" + (b2 - 1), 0));
            localEditor.putString("MEM-Names" + b2, localSharedPreferences.getString("MEM-Names" + (b2 - 1), ""));
            localEditor.commit();
            --b2;
          }
          ++b1;
        }
        while (b1 < 7);
    }
  }
}