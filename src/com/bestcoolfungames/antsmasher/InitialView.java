package com.bestcoolfungames.antsmasher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.bestcoolfungames.util.Util;
import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;
import com.revmob.RevMobAdvertiser;
import com.revmob.ads.banner.Banner;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class InitialView extends Activity
  implements AdListener, MetaTagListener
{
  static final int ADS_MAGIC_CONSTANT = 150;
  public static final String MM_APID = "58057";
  public static final String MM_INTER_APID = "58057";
  public static final String MM_TEST_APID = "28911";
  public static final String MM_TEST_INTERID = "28911";
  public static final int NUMBER_OF_GOOGLE_ADS = 5;
  private static final int NUM_META_TAGS = 9;
  public static AdView[] ad;
  public static int adCounter;
  public static boolean[] adVisibility;
  public static MediaPlayer backgroundMp;
  public static Boolean backgroundMpErrored;
  private static Banner banner;
  public static int curAd;
  public static int curGoogleAd;
  public static Boolean initialViewCreated;
  public static RelativeLayout layoutView;
  public static int levelCount;
  public static InitialView mainInstance;
  public static boolean menuHasBeenJustLoaded = false;
  public static boolean[] metas;
  public static boolean mmAdShowing;
  public static final Random rand = new Random();
  public static Activity shownActivity;
  private static AdView shownAd;
  final int antSizeX;
  final int antSizeY;
  TextView bg;
  int counter;
  final int delay;
  public boolean gamePaused = false;
  ImageView initialAnt;
  int initialAntAngle;
  boolean initialAntControl;
  int initialAntDir;
  Bitmap[] initialAntFrames;
  public boolean isRunning;
  private Context mContext;

  static
  {
    mmAdShowing = false;
    backgroundMpErrored = Boolean.valueOf(false);
    initialViewCreated = Boolean.valueOf(false);
  }

  public InitialView()
  {
    this.delay = 30;
    this.antSizeX = 50;
    this.antSizeY = 70;
  }

  public static boolean canPlayMoreGames(Context paramContext)
  {
    SharedPreferences localSharedPreferences1 = paramContext.getSharedPreferences("AppData", 0);
    localSharedPreferences1.edit();
    SharedPreferences localSharedPreferences2 = paramContext.getSharedPreferences("AntSmasherShop", 0);
    int i = localSharedPreferences1.getInt("NumberOfPlayedGamesSinceV29", 0);
    int j = 0;
    if (localSharedPreferences2.getBoolean("KidsMode", false))
      j = 0 + 1;
    if (localSharedPreferences2.getBoolean("FunMode", false))
      ++j;
    if (localSharedPreferences2.getBoolean("BabyMode", false))
      ++j;
    if (localSharedPreferences2.getBoolean("NoAds", false))
      ++j;
    if (localSharedPreferences2.getBoolean("Protection", false))
      ++j;
    if (localSharedPreferences2.getInt("MaxLifes", 3) > 3)
      ++j;
    Log.i("AS", "Plays: " + i + ", Products: " + j);
    if ((i >= 15) && (j != 0));
    return true;
  }

  public static void createBackgroundMusic(Context paramContext)
  {
    boolean bool = paramContext.getSharedPreferences("AppData", 0).getBoolean("Sound", true);
    if (backgroundMp == null)
      if (bool)
      {
        backgroundMpErrored = Boolean.valueOf(false);
        backgroundMp = MediaPlayer.create(paramContext, 2131034122);
        if (backgroundMp != null)
        {
          backgroundMp.setAudioStreamType(3);
          backgroundMp.setLooping(true);
          backgroundMp.start();
          backgroundMp.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
          {
            public void onCompletion()
            {
              if (paramMediaPlayer != null)
              {
                if ((!(InitialView.backgroundMpErrored.booleanValue())) && (paramMediaPlayer.isPlaying()))
                  paramMediaPlayer.stop();
                paramMediaPlayer.release();
              }
            }
          });
          backgroundMp.setOnErrorListener(new MediaPlayer.OnErrorListener()
          {
            public boolean onError(, int paramInt1, int paramInt2)
            {
              InitialView.backgroundMpErrored = Boolean.valueOf(true);
              return false;
            }
          });
        }
      }
    while (true)
    {
      do
        return;
      while ((backgroundMpErrored.booleanValue()) || (backgroundMp.isPlaying()));
      backgroundMp.release();
      backgroundMp = null;
      createBackgroundMusic(paramContext);
    }
  }

  static void deliverTapJoyProduct(Activity paramActivity)
  {
    SharedPreferences localSharedPreferences = paramActivity.getSharedPreferences("AntSmasherShop", 0);
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    short s = 1;
    if (Util.isDebug)
      s = 100;
    int i = localSharedPreferences.getInt("TapJoyProduct", 0);
    int j = 0;
    switch (i)
    {
    default:
      if (paramActivity instanceof StoreActivity)
      {
        int k = Integer.decode(((StoreActivity)paramActivity).coinText.getText().toString()).intValue() - j;
        ((StoreActivity)paramActivity).coinText.setText(k);
      }
      localEditor.putInt("TapJoySpentPoints", j + localSharedPreferences.getInt("TapJoySpentPoints", 0));
      localEditor.commit();
      playButtonSound("Out", paramActivity);
      return;
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    }
    try
    {
      do
      {
        HashMap localHashMap9 = new HashMap();
        localHashMap9.put("FromTapJoy", "Kids");
        localHashMap9.put("All", "Kids_Purchased");
        GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_Kids_FromTapJoy", -1);
        j = 1000 / s;
        localEditor.putBoolean("KidsMode", true);
        localEditor.commit();
      }
      while (!(paramActivity instanceof StoreActivity));
      label522: paramActivity.findViewById(2131230807).setVisibility(4);
    }
    catch (Exception localException9)
    {
      while (true)
        localException9.printStackTrace();
      try
      {
        do
        {
          HashMap localHashMap8 = new HashMap();
          localHashMap8.put("FromTapJoy", "Fun");
          localHashMap8.put("All", "Fun_Purchased");
          GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_Fun_FromTapJoy", -1);
          j = 500 / s;
          localEditor.putBoolean("FunMode", true);
          localEditor.commit();
        }
        while (!(paramActivity instanceof StoreActivity));
        paramActivity.findViewById(2131230811).setVisibility(4);
      }
      catch (Exception localException8)
      {
        byte b2;
        while (true)
          localException8.printStackTrace();
        try
        {
          do
          {
            HashMap localHashMap7 = new HashMap();
            localHashMap7.put("FromTapJoy", "NoAds");
            localHashMap7.put("All", "NoAds_Purchased");
            GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_NoAds_FromTapJoy", -1);
            localEditor.putBoolean("NoAds", true);
            localEditor.commit();
            if (ad != null)
            {
              b2 = 0;
              if (b2 < 5)
                break label522;
            }
          }
          while (!(paramActivity instanceof StoreActivity));
          paramActivity.findViewById(2131230814).setVisibility(4);
        }
        catch (Exception localException7)
        {
          while (true)
          {
            while (true)
              localException7.printStackTrace();
            ad[b2].setVisibility(4);
            ++b2;
          }
          try
          {
            do
            {
              HashMap localHashMap6 = new HashMap();
              localHashMap6.put("FromTapJoy", "Lives3To4");
              localHashMap6.put("All", "Lives3To4_Purchased");
              GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_Lives3To4_FromTapJoy", -1);
              j = 300 / s;
              localEditor.putInt("MaxLifes", 4);
              localEditor.commit();
            }
            while (!(paramActivity instanceof StoreActivity));
            paramActivity.findViewById(2131230816).setVisibility(4);
            paramActivity.findViewById(2131230817).setVisibility(4);
            paramActivity.findViewById(2131230818).setVisibility(0);
          }
          catch (Exception localException6)
          {
            while (true)
              localException6.printStackTrace();
            try
            {
              do
              {
                HashMap localHashMap5 = new HashMap();
                localHashMap5.put("FromTapJoy", "Lives3To5");
                localHashMap5.put("All", "Lives3To5_Purchased");
                GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_Lives3To5_FromTapJoy", -1);
                j = 600 / s;
                localEditor.putInt("MaxLifes", 5);
                localEditor.commit();
              }
              while (!(paramActivity instanceof StoreActivity));
              paramActivity.findViewById(2131230816).setVisibility(4);
              paramActivity.findViewById(2131230817).setVisibility(4);
              paramActivity.findViewById(2131230818).setVisibility(4);
            }
            catch (Exception localException5)
            {
              while (true)
                localException5.printStackTrace();
              try
              {
                do
                {
                  HashMap localHashMap4 = new HashMap();
                  localHashMap4.put("FromTapJoy", "GameOverProtection");
                  localHashMap4.put("All", "GameOverProtection_Purchased");
                  GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_GameOverProtection_FromTapJoy", -1);
                  j = 500 / s;
                  localEditor.putBoolean("Protection", true);
                  localEditor.putBoolean("ProtectionEnabled", true);
                  localEditor.commit();
                }
                while (!(paramActivity instanceof StoreActivity));
                paramActivity.findViewById(2131230819).setVisibility(4);
              }
              catch (Exception localException4)
              {
                while (true)
                  localException4.printStackTrace();
                try
                {
                  do
                  {
                    HashMap localHashMap3 = new HashMap();
                    localHashMap3.put("FromTapJoy", "Lives4To5");
                    localHashMap3.put("All", "Lives4To5_Purchased");
                    GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_Lives4To5_FromTapJoy", -1);
                    j = 300 / s;
                    localEditor.putInt("MaxLifes", 5);
                    localEditor.commit();
                  }
                  while (!(paramActivity instanceof StoreActivity));
                  paramActivity.findViewById(2131230816).setVisibility(4);
                  paramActivity.findViewById(2131230817).setVisibility(4);
                  paramActivity.findViewById(2131230818).setVisibility(4);
                }
                catch (Exception localException3)
                {
                  while (true)
                    localException3.printStackTrace();
                  try
                  {
                    do
                    {
                      HashMap localHashMap2 = new HashMap();
                      localHashMap2.put("FromTapJoy", "Baby");
                      localHashMap2.put("All", "Baby_Purchased");
                      GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_Baby_FromTapJoy", -1);
                      j = 1000 / s;
                      localEditor.putBoolean("BabyMode", true);
                      localEditor.commit();
                    }
                    while (!(paramActivity instanceof StoreActivity));
                    paramActivity.findViewById(2131230809).setVisibility(4);
                  }
                  catch (Exception localException2)
                  {
                    while (true)
                      localException2.printStackTrace();
                    try
                    {
                      byte b1;
                      do
                      {
                        do
                        {
                          do
                          {
                            HashMap localHashMap1 = new HashMap();
                            localHashMap1.put("FromTapJoy", "DeluxePack");
                            localHashMap1.put("All", "DeluxePack_Purchased");
                            GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_DeluxePack_FromTapJoy", -1);
                            j = 2500 / s;
                            localEditor.putInt("MaxLifes", 5);
                            localEditor.putBoolean("BabyMode", true);
                            localEditor.putBoolean("FunMode", true);
                            localEditor.putBoolean("KidsMode", true);
                            localEditor.putBoolean("Protection", true);
                            localEditor.putBoolean("ProtectionEnabled", true);
                            localEditor.putBoolean("NoAds", true);
                            localEditor.putBoolean("DeluxePack", true);
                            localEditor.commit();
                          }
                          while (!(paramActivity instanceof StoreActivity));
                          paramActivity.findViewById(2131230816).setVisibility(4);
                          paramActivity.findViewById(2131230817).setVisibility(4);
                          paramActivity.findViewById(2131230818).setVisibility(4);
                          paramActivity.findViewById(2131230809).setVisibility(4);
                          paramActivity.findViewById(2131230807).setVisibility(4);
                          paramActivity.findViewById(2131230811).setVisibility(4);
                          paramActivity.findViewById(2131230819).setVisibility(4);
                          paramActivity.findViewById(2131230814).setVisibility(4);
                          paramActivity.findViewById(2131230803).setVisibility(4);
                        }
                        while (ad == null);
                        b1 = 0;
                      }
                      while (b1 >= 5);
                      ad[b1].setVisibility(4);
                      ++b1;
                    }
                    catch (Exception localException1)
                    {
                      while (true)
                        localException1.printStackTrace();
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  public static View getBannerView()
  {
    Object localObject;
    if (banner != null)
      localObject = banner;
    while (true)
    {
      return localObject;
      localObject = shownAd;
    }
  }

  public static void playButtonSound(String paramString, Context paramContext)
  {
    int i;
    if (paramString.equals("In"))
      i = 2131034124;
    while (true)
    {
      if (paramContext.getSharedPreferences("AppData", 0).getBoolean("Sound", true))
      {
        MediaPlayer localMediaPlayer = MediaPlayer.create(paramContext, i);
        if (localMediaPlayer != null)
        {
          localMediaPlayer.setAudioStreamType(3);
          localMediaPlayer.start();
          localMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
          {
            public void onCompletion()
            {
              if ((paramMediaPlayer != null) && (paramMediaPlayer.isPlaying()))
                paramMediaPlayer.stop();
            }
          });
        }
      }
      return;
      i = 2131034125;
    }
  }

  private void setupAdMobAds(Activity paramActivity, AdListener paramAdListener)
  {
    AdSize localAdSize;
    byte b;
    if (getResources().getDisplayMetrics().heightPixels > 1000)
    {
      localAdSize = AdSize.IAB_LEADERBOARD;
      ad[0] = new AdView(paramActivity, localAdSize, "a14e2db2ef9c88c");
      ad[1] = new AdView(paramActivity, localAdSize, "a14e2db2d15abe6");
      ad[2] = new AdView(paramActivity, localAdSize, "a14e2db2b3adc23");
      ad[3] = new AdView(paramActivity, localAdSize, "a14e2db2984c283");
      ad[4] = new AdView(paramActivity, localAdSize, "a14e2db280c6d07");
      b = 0;
    }
    while (true)
    {
      while (b >= 5)
      {
        shownAd = ad[0];
        return;
        localAdSize = AdSize.BANNER;
      }
      ad[b].setAdListener(paramAdListener);
      ad[b].loadAd(new AdRequest());
      layoutView.addView(ad[b]);
      ++b;
    }
  }

  public static void setupMillennialAds()
  {
    Log.i("AS", "Call MM Interstitial");
    new Hashtable().put("gender", "female");
  }

  private void setupRevMobAds(Activity paramActivity)
  {
    banner = new Banner("4fc6792ca5062c00080000cc", this);
    layoutView.removeAllViews();
    layoutView.addView(banner);
  }

  public boolean appInstalledOrNot(String paramString)
  {
    byte b;
    PackageManager localPackageManager = getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 1);
      b = 1;
      return b;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
        b = 0;
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

  public void onCreate(Bundle paramBundle)
  {
    SharedPreferences.Editor localEditor1;
    Util.setContext(getApplicationContext());
    initialViewCreated = Boolean.valueOf(true);
    mainInstance = this;
    adCounter = 0;
    RevMobAdvertiser.registerInstall(this, Constants.BCFAdsID);
    metas = new boolean[9];
    byte b = 1;
    if (b > 9)
    {
      super.onCreate(paramBundle);
      setContentView(2130903041);
      setRequestedOrientation(1);
      setVolumeControlStream(3);
      overridePendingTransition(0, 0);
      SharedPreferences localSharedPreferences = getSharedPreferences("AppData", 0);
      localEditor1 = localSharedPreferences.edit();
      localEditor1.putInt("State", 0);
      localEditor1.putBoolean("ShouldContinue", false);
      localEditor1.putBoolean("GamePaused", false);
      localEditor1.commit();
      SharedPreferences.Editor localEditor2 = getSharedPreferences("AntSmasherShop", 0).edit();
      localEditor2.putBoolean("InitialProductAlreadyOffered", false);
      localEditor2.commit();
      this.mContext = getApplicationContext();
      createBackgroundMusic(this.mContext);
      if (!(localSharedPreferences.contains("Vibration")))
      {
        localEditor1.putBoolean("Vibration", true);
        localEditor1.commit();
      }
      levelCount = 1;
      layoutView = (RelativeLayout)findViewById(2131230724);
      ad = new AdView[5];
      adVisibility = new boolean[5];
      if (Constants.isPaidVersion())
      {
        SharedPreferences.Editor localEditor3 = getSharedPreferences("AntSmasherShop", 0).edit();
        localEditor3.putBoolean("NoAds", true);
        localEditor3.commit();
      }
      if (!(getSharedPreferences("AntSmasherShop", 0).getBoolean("NoAds", false)))
      {
        if (Math.random() > 0.5D)
          break label433;
        setupRevMobAds(this);
      }
    }
    while (true)
    {
      while (true)
      {
        layoutView.removeAllViews();
        CookieSyncManager.createInstance(this.mContext);
        CookieManager.getInstance().removeAllCookie();
        localEditor1.putInt("ShowMoreApps", 2);
        localEditor1.commit();
        Intent localIntent = new Intent(this.mContext, Menu.class);
        overridePendingTransition(2130968576, 2130968577);
        startActivityForResult(localIntent, 1);
        prepareABTesting();
        update();
        return;
        MetaTags localMetaTags = new MetaTags(b);
        MetaTagListener[] arrayOfMetaTagListener = new MetaTagListener[1];
        arrayOfMetaTagListener[0] = this;
        localMetaTags.execute(arrayOfMetaTagListener);
        ++b;
      }
      label433: setupAdMobAds(this, this);
    }
  }

  public void onDestroy()
  {
    Log.w("", "ONDESTROY");
    super.onDestroy();
    Process.killProcess(Process.myPid());
  }

  public void onDismissScreen(Ad paramAd)
  {
  }

  public void onFailedToReceiveAd(Ad paramAd, AdRequest.ErrorCode paramErrorCode)
  {
    byte b = 0;
    while (true)
    {
      if (b >= 5);
      while (true)
      {
        return;
        if (paramAd != ad[b])
          break;
        adVisibility[b] = false;
      }
      ++b;
    }
  }

  public void onLeaveApplication(Ad paramAd)
  {
  }

  public void onPresentScreen(Ad paramAd)
  {
  }

  public void onReceiveAd(Ad paramAd)
  {
    byte b = 0;
    while (true)
    {
      if (b >= 5);
      while (true)
      {
        return;
        if (paramAd != ad[b])
          break;
        adVisibility[b] = true;
      }
      ++b;
    }
  }

  public void onResume()
  {
    super.onResume();
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      SharedPreferences.Editor localEditor = getSharedPreferences("AppData", 0).edit();
      localEditor.putInt("State", 0);
      localEditor.putBoolean("ShouldContinue", false);
      localEditor.commit();
      overridePendingTransition(2130968576, 2130968577);
      finishActivity(1);
    }
    try
    {
      finalize();
      label65: finish();
      return;
    }
    catch (Throwable localThrowable)
    {
      break label65:
    }
  }

  public void prepareABTesting()
  {
    SharedPreferences localSharedPreferences = getSharedPreferences("AppData", 0);
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    if (!(localSharedPreferences.getFloat(Constants.key.getABDifficultyLabel(this), 0F) < 0F))
    {
      localEditor.putFloat(Constants.key.getABDifficultyLabel(this), 3.5F);
      localEditor.commit();
    }
  }

  public void receivedYesOnMeta(int paramInt)
  {
    Log.i("AS", "Received Meta" + paramInt);
    metas[(paramInt - 1)] = true;
  }

  public void update()
  {
    Handler localHandler = new Handler();
    localHandler.postDelayed(new Runnable(this, localHandler)
    {
      public void finalize()
      {
        try
        {
          super.finalize();
          return;
        }
        catch (Throwable localThrowable)
        {
          while (true)
            localThrowable.printStackTrace();
        }
      }

      public void run()
      {
        ViewParent localViewParent;
        RelativeLayout.LayoutParams localLayoutParams;
        SharedPreferences localSharedPreferences = this.this$0.getSharedPreferences("AppData", 0);
        SharedPreferences.Editor localEditor = localSharedPreferences.edit();
        boolean bool1 = localSharedPreferences.getBoolean("GamePaused", false);
        boolean bool2 = this.this$0.getSharedPreferences("AntSmasherShop", 0).getBoolean("NoAds", false);
        if ((!(bool1)) && (!(bool2)))
        {
          int i = InitialView.adCounter;
          InitialView.adCounter = i + 1;
          if ((i % 150 == 0) && (localSharedPreferences.getInt("GameMode", 0) != 3))
          {
            localViewParent = null;
            localLayoutParams = null;
          }
        }
        try
        {
          byte b;
          if ((InitialView.shownActivity != null) && (InitialView.access$0().getParent() != null) && (InitialView.access$0().getParent() instanceof RelativeLayout))
          {
            localViewParent = InitialView.access$0().getParent();
            localLayoutParams = (RelativeLayout.LayoutParams)InitialView.access$0().getLayoutParams();
            b = 0;
            if ((!(InitialView.ad[(InitialView.adCounter / 150 % 5)].isReady())) && (b < 5))
              break label353;
            if (!(InitialView.access$0().isReady()))
              InitialView.access$0().loadAd(new AdRequest());
            ((RelativeLayout)localViewParent).removeView(InitialView.access$0());
          }
          label204: InitialView.access$1(InitialView.ad[(InitialView.adCounter / 150 % 5)]);
          if (localViewParent != null)
          {
            InitialView.access$0().setLayoutParams(localLayoutParams);
            ((RelativeLayout)localViewParent).addView(InitialView.access$0());
            if (localSharedPreferences.getInt("GameMode", 0) != 3)
              break label392;
            InitialView.access$0().setClickable(false);
          }
          switch (localSharedPreferences.getInt("State", 0))
          {
          case 13:
          default:
          case 3:
          case 4:
          case 15:
          case 5:
          case 12:
          case 6:
          case 7:
          case 8:
          case 9:
          case 10:
          case 11:
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
                                {
                                  while (true)
                                  {
                                    while (true)
                                    {
                                      this.val$handler.postDelayed(this, 30);
                                      return;
                                      label353: InitialView.ad[(InitialView.adCounter / 150 % 5)].loadAd(new AdRequest());
                                      InitialView.adCounter = 150 + InitialView.adCounter;
                                      ++b;
                                    }
                                    label392: InitialView.access$0().setClickable(true);
                                  }
                                  this.this$0.finishActivity(1);
                                  InitialView.levelCount = 0;
                                  localEditor.putInt("State", 0);
                                  localEditor.putBoolean("ShouldContinue", false);
                                  localEditor.commit();
                                  Intent localIntent12 = new Intent(InitialView.access$2(this.this$0), GameOver.class);
                                  this.this$0.overridePendingTransition(2130968576, 2130968577);
                                  localIntent12.addFlags(536870912);
                                  this.this$0.startActivityForResult(localIntent12, 1);
                                  System.gc();
                                  localEditor.putBoolean("GamePaused", false);
                                  localEditor.commit();
                                }
                                this.this$0.finishActivity(1);
                                localEditor.putLong("StartGameDate", new Date().getTime());
                                localEditor.putLong("PlayTimeDiscount", 0L);
                                localEditor.putInt("Points", 0);
                                localEditor.putInt("Lifes", 2);
                                localEditor.putInt("State", 1);
                                localEditor.putBoolean("ShouldContinue", true);
                                localEditor.putBoolean("Bonus", false);
                                localEditor.putBoolean("Bonus-Life", false);
                                localEditor.putBoolean("Paused", false);
                                localEditor.putBoolean("FirstLevel", true);
                                localEditor.putFloat("Acceleration", 0F);
                                localEditor.putInt("SurfaceAction", 0);
                                localEditor.putBoolean("Prot", this.this$0.getSharedPreferences("AntSmasherShop", 0).getBoolean("ProtectionEnabled", false));
                                localEditor.commit();
                                InitialView.levelCount = 1;
                                Intent localIntent11 = new Intent(InitialView.access$2(this.this$0), GameActivity.class);
                                this.this$0.overridePendingTransition(2130968576, 2130968577);
                                this.this$0.startActivityForResult(localIntent11, 1);
                                System.gc();
                                localEditor.putBoolean("GamePaused", false);
                                localEditor.commit();
                              }
                              this.this$0.finishActivity(1);
                              Intent localIntent10 = new Intent(InitialView.access$2(this.this$0), TutorialActivity.class);
                              this.this$0.overridePendingTransition(2130968576, 2130968577);
                              this.this$0.startActivityForResult(localIntent10, 1);
                              localEditor.putInt("State", 0);
                              System.gc();
                              localEditor.commit();
                            }
                            this.this$0.overridePendingTransition(2130968576, 2130968577);
                            this.this$0.finishActivity(1);
                            InitialView.levelCount = 0;
                            localEditor.putInt("State", 0);
                            localEditor.putBoolean("ShouldContinue", false);
                            localEditor.commit();
                            Intent localIntent9 = new Intent(InitialView.access$2(this.this$0), Menu.class);
                            this.this$0.startActivityForResult(localIntent9, 1);
                            System.gc();
                            localEditor.putBoolean("GamePaused", false);
                            localEditor.putInt("State", 0);
                            localEditor.commit();
                          }
                          this.this$0.overridePendingTransition(2130968576, 2130968577);
                          this.this$0.finishActivity(1);
                          localEditor.putInt("State", 0);
                          localEditor.putBoolean("ShouldContinue", false);
                          localEditor.commit();
                          Intent localIntent8 = new Intent(InitialView.access$2(this.this$0), PlayMenu.class);
                          this.this$0.startActivityForResult(localIntent8, 1);
                          System.gc();
                          localEditor.putBoolean("GamePaused", false);
                          localEditor.commit();
                        }
                        this.this$0.overridePendingTransition(2130968576, 2130968577);
                        this.this$0.finishActivity(1);
                        localEditor.putInt("State", 0);
                        localEditor.putBoolean("ShouldContinue", false);
                        localEditor.commit();
                        Intent localIntent7 = new Intent(InitialView.access$2(this.this$0), Twitter.class);
                        this.this$0.overridePendingTransition(2130968576, 2130968577);
                        this.this$0.startActivityForResult(localIntent7, 1);
                        System.gc();
                        localEditor.putBoolean("GamePaused", false);
                        localEditor.commit();
                      }
                      this.this$0.overridePendingTransition(2130968576, 2130968577);
                      this.this$0.finishActivity(1);
                      localEditor.putInt("State", 0);
                      localEditor.putBoolean("ShouldContinue", false);
                      localEditor.commit();
                      Intent localIntent6 = new Intent(InitialView.access$2(this.this$0), GameOver.class);
                      this.this$0.startActivityForResult(localIntent6, 1);
                      System.gc();
                      localEditor.putBoolean("GamePaused", false);
                      localEditor.commit();
                    }
                    this.this$0.overridePendingTransition(2130968576, 2130968577);
                    this.this$0.finishActivity(1);
                    localEditor.putInt("State", 0);
                    localEditor.putBoolean("ShouldContinue", false);
                    localEditor.commit();
                    Intent localIntent5 = new Intent(InitialView.access$2(this.this$0), HighScores.class);
                    this.this$0.startActivityForResult(localIntent5, 1);
                    System.gc();
                    localEditor.putBoolean("GamePaused", false);
                    localEditor.commit();
                  }
                  this.this$0.overridePendingTransition(2130968576, 2130968577);
                  this.this$0.finishActivity(1);
                  localEditor.putInt("State", 0);
                  localEditor.putBoolean("ShouldContinue", false);
                  localEditor.commit();
                  Intent localIntent4 = new Intent(InitialView.access$2(this.this$0), Options.class);
                  this.this$0.startActivityForResult(localIntent4, 1);
                  System.gc();
                  localEditor.putBoolean("GamePaused", false);
                  localEditor.commit();
                }
                this.this$0.overridePendingTransition(2130968576, 2130968577);
                this.this$0.finishActivity(1);
                localEditor.putInt("State", 0);
                localEditor.putBoolean("ShouldContinue", false);
                localEditor.commit();
                Intent localIntent3 = new Intent(InitialView.access$2(this.this$0), FBCustom.class);
                this.this$0.startActivityForResult(localIntent3, 1);
                System.gc();
                localEditor.putBoolean("GamePaused", false);
                localEditor.commit();
              }
              this.this$0.overridePendingTransition(2130968576, 2130968577);
              this.this$0.finishActivity(1);
              localEditor.putInt("State", 0);
              localEditor.putBoolean("ShouldContinue", false);
              localEditor.commit();
              Intent localIntent2 = new Intent(InitialView.access$2(this.this$0), StoreActivity.class);
              this.this$0.startActivityForResult(localIntent2, 1);
              System.gc();
              localEditor.putBoolean("GamePaused", false);
              localEditor.commit();
            }
          case 14:
          }
          this.this$0.overridePendingTransition(2130968576, 2130968577);
          this.this$0.finishActivity(1);
          localEditor.putInt("State", 0);
          localEditor.putBoolean("ShouldContinue", false);
          localEditor.commit();
          Intent localIntent1 = new Intent(InitialView.access$2(this.this$0), MoreGames.class);
          this.this$0.startActivityForResult(localIntent1, 1);
          System.gc();
          localEditor.putBoolean("GamePaused", false);
          localEditor.commit();
        }
        catch (Exception localException)
        {
          break label204:
        }
      }
    }
    , 30);
  }
}