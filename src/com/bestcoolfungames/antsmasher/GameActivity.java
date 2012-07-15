package com.bestcoolfungames.antsmasher;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.bestcoolfungames.util.Util;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GameActivity extends Activity
{
  public static int lifeFlag;
  static Toast staticToast;
  public static int surfaceAction;
  private Boolean analyticsAlreadyDone;
  private MediaPlayer backgroundMp;
  private Boolean backgroundMpErrored;
  boolean bringItOnHome;
  public final long delay = 150;
  int gameMode;
  public LevelInterface gameSurface;
  boolean isChangingScreen = false;
  public RelativeLayout layoutView;
  boolean[] levelChosen;
  ImageView[] lifes;
  private Boolean[] livesShow;
  Drawable[] numberFrames;
  ImageView[] numbers;
  ImageView pauseButton;
  ImageView pauseWarning;
  boolean running;
  ImageView scoreBar;
  private GameSurfaceView surfaceView;
  private Activity thisActivity;
  int transitionFlag = 0;

  public GameActivity()
  {
    this.scoreBar = null;
    this.pauseButton = null;
    this.pauseWarning = null;
    this.bringItOnHome = false;
    this.gameMode = 0;
    this.analyticsAlreadyDone = Boolean.valueOf(false);
  }

  private void createLevel(Class<?> paramClass)
  {
    Constructor localConstructor = paramClass.getConstructors()[0];
    try
    {
      if (this.gameSurface != null)
      {
        this.surfaceView.surfaceDestroyed(null);
        this.surfaceView.stop();
      }
      this.gameSurface = ((LevelInterface)localConstructor.newInstance(new Object[0]));
      Context localContext = getApplicationContext();
      if (this.surfaceView == null)
        this.surfaceView = new GameSurfaceView(localContext);
      ((GameSurface)this.gameSurface).setup(localContext, this.surfaceView);
      this.surfaceView.setSurfaceSize(localContext.getResources().getDisplayMetrics().widthPixels, localContext.getResources().getDisplayMetrics().heightPixels);
      GameSurface localGameSurface1 = (GameSurface)this.gameSurface;
      localGameSurface1.paceX = (int)(0.65000000000000002D * localGameSurface1.paceX);
      GameSurface localGameSurface2 = (GameSurface)this.gameSurface;
      localGameSurface2.paceY = (int)(0.75D * localGameSurface2.paceY);
      return;
    }
    catch (Exception localException)
    {
      throw new IllegalStateException("Oh, No!", localException);
    }
  }

  private void startAnalytics()
  {
    try
    {
      GoogleAnalyticsTracker.getInstance().start("UA-21142514-5", 10, this);
      if (!(this.analyticsAlreadyDone.booleanValue()))
      {
        GoogleAnalyticsTracker.getInstance().trackEvent("Activity", "Activity_Game_Enter", "", -1);
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

  private void startBackgroundMusic(GameActivity paramGameActivity)
  {
    this.backgroundMpErrored = Boolean.valueOf(false);
    this.backgroundMp = MediaPlayer.create(paramGameActivity, 2131034121);
    this.backgroundMp.setOnErrorListener(new MediaPlayer.OnErrorListener(this)
    {
      public boolean onError(, int paramInt1, int paramInt2)
      {
        GameActivity.access$5(this.this$0, Boolean.valueOf(true));
        return false;
      }
    });
    this.backgroundMp.setAudioStreamType(3);
    this.backgroundMp.setLooping(true);
    this.backgroundMp.setVolume(0.40000000596046448F, 0.40000000596046448F);
    if (getSharedPreferences("AppData", 0).getBoolean("Sound", true))
      this.backgroundMp.start();
    this.backgroundMp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(this)
    {
      public void onCompletion()
      {
        if (paramMediaPlayer != null)
        {
          if (paramMediaPlayer.isPlaying())
            paramMediaPlayer.stop();
          paramMediaPlayer.release();
        }
      }
    });
  }

  protected void finalize()
  {
    Log.i("TAG", "Level dealloc");
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

  protected int nextLevel()
  {
    byte b;
    int k;
    int i = InitialView.rand.nextInt(LevelsList.numberOfLevels);
    if (this.levelChosen[i] != 0)
    {
      i = InitialView.rand.nextInt(LevelsList.numberOfLevels);
      if (this.levelChosen[i] != 0)
      {
        i = InitialView.rand.nextInt(LevelsList.numberOfLevels);
        if (this.levelChosen[i] != 0)
        {
          i = InitialView.rand.nextInt(LevelsList.numberOfLevels);
          if (this.levelChosen[i] != 0)
          {
            b = 0;
            k = 0;
            if (k >= LevelsList.numberOfLevels);
          }
        }
      }
    }
    while (true)
    {
      while (true)
      {
        while (true)
        {
          while (true)
          {
            int j = i;
            Util.DebugLog("level - " + String.valueOf(j));
            return j;
            if (b == 0)
            {
              if (this.levelChosen[k] != 0)
                break label149;
              i = k;
              this.levelChosen[k] = true;
              b = 1;
            }
            label149: 
            do
              while (true)
                ++k;
            while (k != -1 + LevelsList.numberOfLevels);
            int l = 0;
            while (true)
            {
              while (l >= LevelsList.numberOfLevels)
              {
                i = 0;
                this.levelChosen[i] = true;
                b = 1;
              }
              this.levelChosen[l] = false;
              ++l;
            }
            this.levelChosen[i] = true;
          }
          this.levelChosen[i] = true;
        }
        this.levelChosen[i] = true;
      }
      this.levelChosen[i] = true;
    }
  }

  public void onBackPressed()
  {
    this.isChangingScreen = true;
    if (this.gameMode == 3)
    {
      staticToast = Toast.makeText(this, getString(2131165215), 0);
      staticToast.show();
    }
    while (true)
    {
      return;
      ((GameSurface)this.gameSurface).doStop();
      SharedPreferences.Editor localEditor = getSharedPreferences("AppData", 0).edit();
      localEditor.putInt("State", 3);
      localEditor.commit();
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    Class localClass;
    float f;
    int j;
    byte b;
    int k;
    this.running = true;
    this.levelChosen = new boolean[LevelsList.numberOfLevels];
    this.transitionFlag = 0;
    SharedPreferences localSharedPreferences = getSharedPreferences("AppData", 0);
    if (localSharedPreferences.getInt("Points", 0) == 0)
    {
      overridePendingTransition(2130968576, 2130968577);
      this.gameMode = localSharedPreferences.getInt("GameMode", 0);
      super.onCreate(paramBundle);
      setVolumeControlStream(3);
      setRequestedOrientation(1);
      setContentView(2130903043);
      this.layoutView = ((RelativeLayout)findViewById(2131230728));
      if (!(localSharedPreferences.getBoolean("FirstLevel", false)))
        break label486;
      localClass = LevelsList.getLevel(0);
      createLevel(localClass);
      RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -1);
      ((GameSurface)this.gameSurface).setLayoutParams(localLayoutParams1);
      GameSurface localGameSurface1 = (GameSurface)this.gameSurface;
      localGameSurface1.paceX = (int)(0.69999998807907104F * localGameSurface1.paceX);
      GameSurface localGameSurface2 = (GameSurface)this.gameSurface;
      localGameSurface2.paceY = (int)(0.69999998807907104F * localGameSurface2.paceY);
      this.layoutView.addView(((GameSurface)this.gameSurface).getSurfaceView());
      this.scoreBar = ((ImageView)findViewById(2131230729));
      this.scoreBar.bringToFront();
      this.pauseButton = ((ImageView)findViewById(2131230736));
      this.pauseButton.bringToFront();
      this.pauseWarning = ((ImageView)findViewById(2131230737));
      this.pauseWarning.bringToFront();
      this.pauseWarning.setVisibility(4);
      int i = getResources().getDisplayMetrics().widthPixels;
      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(i, -2);
      localLayoutParams2.leftMargin = (-i);
      localLayoutParams2.topMargin = 200;
      this.pauseWarning.setLayoutParams(localLayoutParams2);
      this.pauseButton.setOnClickListener(new View.OnClickListener(this)
      {
        public void onClick()
        {
          this.this$0.pause(Boolean.valueOf(false));
        }
      });
      f = getApplicationContext().getResources().getDisplayMetrics().density;
      this.numberFrames = new Drawable[10];
      this.numbers = new ImageView[5];
      this.lifes = new ImageView[5];
      this.livesShow = new Boolean[5];
      j = 0;
      if (j < 10)
        break label502;
      b = 0;
      if (b < 5)
        break label531;
      this.numbers[0].setBackgroundDrawable(this.numberFrames[0]);
      this.numbers[0].setAlpha(255);
      k = 0;
    }
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
              if (k < 5)
                break label641;
              updateScores();
              updateLifes();
              update();
              this.pauseWarning.bringToFront();
              return;
              overridePendingTransition(0, 2130968577);
            }
            label486: localClass = LevelsList.getLevel(InitialView.rand.nextInt(LevelsList.numberOfLevels));
          }
          label502: this.numberFrames[j] = getApplicationContext().getResources().getDrawable(2130837627 + j);
          ++j;
        }
        label531: this.numbers[b] = new ImageView(getApplicationContext(), null);
        this.numbers[b].bringToFront();
        RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams((int)(35.0F * f), (int)(35.0F * f));
        localLayoutParams3.setMargins(1 + b * 20, 0, 0, 0);
        this.numbers[b].setLayoutParams(localLayoutParams3);
        this.layoutView.addView(this.numbers[b]);
        this.numbers[b].setAlpha(0);
        ++b;
      }
      label641: this.lifes[k] = ((ImageView)findViewById(2131230731 + k));
      this.lifes[k].setBackgroundResource(2130837664);
      this.lifes[k].getBackground().mutate().setAlpha(255);
      this.livesShow[k] = Boolean.valueOf(true);
      this.lifes[k].bringToFront();
      ++k;
    }
  }

  public boolean onKeyLongPress(int paramInt, KeyEvent paramKeyEvent)
  {
    byte b;
    if (this.gameMode == 3)
    {
      staticToast = Toast.makeText(this, getString(2131165215), 0);
      staticToast.show();
      b = 1;
    }
    while (true)
    {
      return b;
      boolean bool = super.onKeyLongPress(paramInt, paramKeyEvent);
    }
  }

  protected void onPause()
  {
    if ((this.backgroundMp != null) && (!(this.backgroundMpErrored.booleanValue())) && (this.backgroundMp.isPlaying()))
      this.backgroundMp.stop();
    if (!(this.isChangingScreen))
    {
      Log.i("AS", "Going to quit app");
      SharedPreferences.Editor localEditor2 = getSharedPreferences("AntSmasherShop", 0).edit();
      localEditor2.putBoolean("InitialProductAlreadyOffered", false);
      localEditor2.commit();
      SharedPreferences.Editor localEditor1 = getSharedPreferences("AppData", 0).edit();
      localEditor1.putBoolean("GamePaused", true);
      localEditor1.commit();
      if (this.gameMode == 3)
      {
        ((GameSurface)this.gameSurface).doStop();
        localEditor1.putInt("State", 5);
        localEditor1.commit();
      }
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
          label168: super.onPause();
          return;
          Log.i("AS", "Going to change screen");
          this.isChangingScreen = false;
        }
        localException1 = localException1;
        localException1.printStackTrace();
      }
      catch (Exception localException2)
      {
        break label168:
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
  }

  protected void onStart()
  {
    InitialView.shownActivity = this;
    AdsUtils.configureBanner(this, this.layoutView, Integer.valueOf(this.gameMode));
    startAnalytics();
    ((GameSurface)this.gameSurface).start();
    this.thisActivity = this;
    startBackgroundMusic((GameActivity)this.thisActivity);
    super.onStart();
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    int i = getSharedPreferences("AppData", 0).getInt("Lifes", 0);
    if ((!(paramBoolean)) && (i > 0))
      pause(Boolean.valueOf(true));
    while (true)
    {
      return;
      ((GameSurface)this.gameSurface).refreshScreen();
    }
  }

  public void pause(Boolean paramBoolean)
  {
    if (this.gameMode == 3)
      return;
    int i = getResources().getDisplayMetrics().widthPixels;
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(i, -2);
    localLayoutParams.leftMargin = 0;
    localLayoutParams.topMargin = 200;
    this.pauseWarning.setLayoutParams(localLayoutParams);
    if ((this.pauseWarning.getVisibility() == 0) && (!(paramBoolean.booleanValue())))
    {
      TranslateAnimation localTranslateAnimation2 = new TranslateAnimation(0F, -i, 0F, 0F);
      localTranslateAnimation2.setDuration(200);
      localTranslateAnimation2.setFillEnabled(true);
      localTranslateAnimation2.setFillBefore(true);
      this.pauseWarning.startAnimation(localTranslateAnimation2);
      localTranslateAnimation2.setAnimationListener(new Animation.AnimationListener(this, i)
      {
        public void onAnimationEnd()
        {
          RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.val$deviceWidth, -2);
          localLayoutParams.leftMargin = (-this.val$deviceWidth);
          localLayoutParams.topMargin = 200;
          this.this$0.pauseWarning.setLayoutParams(localLayoutParams);
          this.this$0.pauseWarning.setVisibility(4);
          ((GameSurface)this.this$0.gameSurface).doResume();
          GameActivity.access$1(this.this$0, (GameActivity)GameActivity.access$0(this.this$0));
        }

        public void onAnimationRepeat()
        {
        }

        public void onAnimationStart()
        {
        }
      });
    }
    while (true)
    {
      while (true)
        this.pauseWarning.bringToFront();
      ((GameSurface)this.gameSurface).doPause();
      if ((this.backgroundMp != null) && (!(this.backgroundMpErrored.booleanValue())) && (this.backgroundMp.isPlaying()))
        this.backgroundMp.stop();
      this.pauseWarning.setVisibility(0);
      TranslateAnimation localTranslateAnimation1 = new TranslateAnimation(-i, 0F, 0F, 0F);
      localTranslateAnimation1.setDuration(200);
      localTranslateAnimation1.setFillEnabled(true);
      localTranslateAnimation1.setFillBefore(true);
      this.pauseWarning.startAnimation(localTranslateAnimation1);
      localTranslateAnimation1.setAnimationListener(new Animation.AnimationListener(this, i)
      {
        public void onAnimationEnd()
        {
          RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.val$deviceWidth, -2);
          localLayoutParams.leftMargin = 0;
          localLayoutParams.topMargin = 200;
          this.this$0.pauseWarning.setLayoutParams(localLayoutParams);
        }

        public void onAnimationRepeat()
        {
        }

        public void onAnimationStart()
        {
        }
      });
    }
  }

  public void setLifes(int paramInt)
  {
    label132: label457: float f1;
    Iterator localIterator1;
    Boolean[] arrayOfBoolean;
    int k;
    int l;
    Iterator localIterator2;
    SharedPreferences localSharedPreferences = getSharedPreferences("AppData", 0);
    int i = localSharedPreferences.getInt("Lifes", 0);
    ArrayList localArrayList = new ArrayList();
    if (paramInt > 0)
    {
      localArrayList.add(Integer.valueOf(5 - i));
      if (!(Boolean.valueOf(false).booleanValue()))
        break label457;
      localIterator2 = localArrayList.iterator();
      if (localIterator2.hasNext())
        break label132;
    }
    do
    {
      float f2;
      float f3;
      ScaleAnimation localScaleAnimation1;
      ScaleAnimation localScaleAnimation2;
      return;
      int j = Math.max(i, 0);
      byte b1 = 0;
      while (true)
      {
        byte b2;
        do
          b2 = 4 - j;
        while (b1 > b2);
        if (this.livesShow[b1].booleanValue())
          localArrayList.add(Integer.valueOf(b1));
        ++b1;
      }
      int i1 = ((Integer)localIterator2.next()).intValue();
      if (paramInt > 0)
      {
        f2 = 0.40000000596046448F;
        f3 = 1F;
        if ((i1 >= 0) && (i1 <= 4))
        {
          this.lifes[i1].getBackground().mutate().setAlpha(255);
          this.livesShow[i1] = Boolean.valueOf(true);
        }
        localScaleAnimation1 = new ScaleAnimation(0.10000000149011612F, 1.2000000476837158F, 0.10000000149011612F, 1.2000000476837158F, 12.0F, 12.0F);
        localScaleAnimation1.setDuration(100);
        localScaleAnimation2 = new ScaleAnimation(1.2000000476837158F, 1F, 1.2000000476837158F, 1F, 12.0F, 12.0F);
        localScaleAnimation2.setStartOffset(101);
        localScaleAnimation2.setDuration(20);
      }
      while (true)
      {
        while (true)
        {
          AnimationSet localAnimationSet;
          do
          {
            AlphaAnimation localAlphaAnimation = new AlphaAnimation(f2, f3);
            localAlphaAnimation.setDuration(300);
            localAlphaAnimation.setAnimationListener(new Animation.AnimationListener(this, i1, f3)
            {
              public void onAnimationEnd()
              {
                Boolean[] arrayOfBoolean;
                int i;
                int j;
                if ((this.val$index >= 0) && (this.val$index <= 4))
                {
                  this.this$0.lifes[this.val$index].getBackground().mutate().setAlpha((int)(255.0F * this.val$to));
                  arrayOfBoolean = GameActivity.access$4(this.this$0);
                  i = this.val$index;
                  if (this.val$to < 1F)
                    break label180;
                  j = 1;
                }
                while (true)
                {
                  arrayOfBoolean[i] = Boolean.valueOf(j);
                  SharedPreferences localSharedPreferences = this.this$0.getSharedPreferences("AppData", 0);
                  if ((localSharedPreferences.getInt("Lifes", 0) < 1) && (this.this$0.transitionFlag != 1))
                  {
                    this.this$0.transitionFlag = 1;
                    ((GameSurface)this.this$0.gameSurface).doStop();
                    this.this$0.isChangingScreen = true;
                    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
                    localEditor.putInt("State", 3);
                    localEditor.putBoolean("ShouldContinue", false);
                    localEditor.commit();
                  }
                  return;
                  label180: byte b = 0;
                }
              }

              public void onAnimationRepeat()
              {
              }

              public void onAnimationStart()
              {
              }
            });
            localAnimationSet = new AnimationSet(true);
            localAnimationSet.setFillBefore(true);
            localAnimationSet.addAnimation(localScaleAnimation1);
            localAnimationSet.addAnimation(localScaleAnimation2);
            localAnimationSet.addAnimation(localAlphaAnimation);
          }
          while ((i1 < 0) || (i1 > 4));
          this.lifes[i1].startAnimation(localAnimationSet);
        }
        f2 = 1F;
        f3 = 0F;
        localScaleAnimation1 = new ScaleAnimation(1F, 1.2000000476837158F, 1F, 1.2000000476837158F, 12.0F, 12.0F);
        localScaleAnimation1.setDuration(30);
        localScaleAnimation2 = new ScaleAnimation(1.2000000476837158F, 0.10000000149011612F, 1.2000000476837158F, 0.10000000149011612F, 12.0F, 12.0F);
        localScaleAnimation2.setStartOffset(31);
        localScaleAnimation2.setDuration(150);
      }
      if (paramInt <= 0)
        break label660;
      f1 = 1F;
      localIterator1 = localArrayList.iterator();
    }
    while (!(localIterator1.hasNext()));
    Integer localInteger = (Integer)localIterator1.next();
    if ((localInteger.intValue() >= 0) && (localInteger.intValue() <= 4))
    {
      this.lifes[localInteger.intValue()].getBackground().mutate().setAlpha((int)(255.0F * f1));
      this.lifes[localInteger.intValue()].invalidate();
      arrayOfBoolean = this.livesShow;
      k = localInteger.intValue();
      if (f1 < 1F)
        break label666;
      l = 1;
    }
    while (true)
    {
      while (true)
      {
        while (true)
        {
          do
            arrayOfBoolean[k] = Boolean.valueOf(l);
          while ((i >= 1) || (this.transitionFlag == 1));
          this.transitionFlag = 1;
          ((GameSurface)this.gameSurface).doStop();
          this.isChangingScreen = true;
          SharedPreferences.Editor localEditor = localSharedPreferences.edit();
          localEditor.putInt("State", 3);
          localEditor.putBoolean("ShouldContinue", false);
          localEditor.commit();
        }
        label660: f1 = 0F;
      }
      label666: byte b3 = 0;
    }
  }

  public void update()
  {
    Handler localHandler = new Handler();
    localHandler.postDelayed(new Runnable(this, localHandler, this)
    {
      public void run()
      {
        long l = System.currentTimeMillis();
        SharedPreferences localSharedPreferences = this.this$0.getSharedPreferences("AppData", 0);
        this.this$0.updateScores();
        if (GameActivity.lifeFlag != 0)
        {
          this.this$0.setLifes(GameActivity.lifeFlag);
          if (GameActivity.lifeFlag == -2)
            this.this$0.setLifes(GameActivity.lifeFlag);
          GameActivity.lifeFlag = 0;
        }
        if (localSharedPreferences.getBoolean("ShouldContinue", false))
          this.val$handler.postDelayed(this, Math.max(150 - System.currentTimeMillis() - l, 1L));
        while (true)
        {
          do
            while (true)
            {
              return;
              if (GameActivity.surfaceAction != 2)
                break;
              GameActivity.surfaceAction = 0;
              AlphaAnimation localAlphaAnimation = new AlphaAnimation(1F, 0F);
              localAlphaAnimation.setDuration(300);
              localAlphaAnimation.setAnimationListener(new Animation.AnimationListener(this, localSharedPreferences, this.val$mContext)
              {
                public void onAnimationEnd()
                {
                  float f1;
                  float f2;
                  Class localClass;
                  byte b1;
                  byte b2;
                  ((GameSurface)GameActivity.4.access$0(this.this$1).gameSurface).doStop();
                  SharedPreferences.Editor localEditor = this.val$settings.edit();
                  GameActivity.surfaceAction = 0;
                  if (this.val$settings.getInt("Lifes", -1) > 0)
                  {
                    GameActivity.4.access$0(this.this$1).isChangingScreen = true;
                    localEditor.putInt("State", 1);
                    localEditor.putBoolean("ShouldContinue", true);
                    int i = this.val$settings.getInt("GameMode", 0);
                    f1 = this.val$settings.getFloat("Acceleration", 0F);
                    f2 = this.val$settings.getFloat(Constants.key.getABDifficultyLabel(this.val$mContext), 0F);
                    if (!(f2 < 0F))
                      f2 = 3.5F;
                    Util.DebugLog("levelAcc - " + String.valueOf(f1 + f2));
                    if (GameActivity.4.access$0(this.this$1).getResources().getDisplayMetrics().widthPixels > 600)
                      f1 = Math.max(20.0F, f1);
                    switch (i)
                    {
                    default:
                      GameActivity.lifeFlag = 0;
                      localEditor.putBoolean("FirstLevel", false);
                      localEditor.putInt("LevelCount", InitialView.levelCount);
                      InitialView.levelCount = 1 + InitialView.levelCount;
                      if (InitialView.levelCount % 3 != 0)
                        break label582;
                      localEditor.putBoolean("Bonus", true);
                      if ((6 + InitialView.levelCount) % 9 != 0)
                        break label595;
                      localEditor.putBoolean("Bonus-Life", true);
                      localEditor.commit();
                      if (!(this.val$settings.getBoolean("FirstLevel", false)))
                        break label608;
                      localClass = LevelsList.getLevel(0);
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    }
                  }
                  try
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
                                ((GameSurface)GameActivity.4.access$0(this.this$1).gameSurface).getThread().join();
                                GameSurface localGameSurface1 = (GameSurface)GameActivity.4.access$0(this.this$1).gameSurface;
                                localGameSurface1.paceX = (int)(0.80000001192092896F * localGameSurface1.paceX);
                                GameSurface localGameSurface2 = (GameSurface)GameActivity.4.access$0(this.this$1).gameSurface;
                                localGameSurface2.paceY = (int)(0.80000001192092896F * localGameSurface2.paceY);
                                GameActivity.access$2(GameActivity.4.access$0(this.this$1), localClass);
                                GameActivity.4.access$0(this.this$1).scoreBar.bringToFront();
                                GameActivity.4.access$0(this.this$1).pauseButton.bringToFront();
                                if (GameActivity.4.access$0(this.this$1).bringItOnHome)
                                  GameActivity.4.access$0(this.this$1).pauseWarning.bringToFront();
                                b1 = 0;
                                if (b1 < 5)
                                  break label636;
                                b2 = 0;
                                if (b2 < 5)
                                  break label658;
                                ((GameSurface)GameActivity.4.access$0(this.this$1).gameSurface).getThread().start();
                                GameActivity.4.access$0(this.this$1).update();
                                return;
                                localEditor.putFloat("Acceleration", f1 + f2);
                              }
                              localEditor.putFloat("Acceleration", f1 + f2);
                            }
                            localEditor.putFloat("Acceleration", 0.5F + f1);
                          }
                          localEditor.putFloat("Acceleration", 0.05000000074505806F);
                        }
                        label582: localEditor.putBoolean("Bonus", false);
                      }
                      label595: localEditor.putBoolean("Bonus-Life", false);
                    }
                    label658: label608: label636: localClass = LevelsList.getLevel(GameActivity.4.access$0(this.this$1).nextLevel());
                  }
                  catch (InterruptedException localInterruptedException)
                  {
                    while (true)
                    {
                      while (true)
                      {
                        while (true)
                          localInterruptedException.printStackTrace();
                        GameActivity.4.access$0(this.this$1).numbers[b1].bringToFront();
                        ++b1;
                      }
                      GameActivity.4.access$0(this.this$1).lifes[b2].bringToFront();
                      ++b2;
                    }
                  }
                }

                public void onAnimationRepeat()
                {
                }

                public void onAnimationStart()
                {
                }
              });
              GameActivity.access$3(this.this$0).startAnimation(localAlphaAnimation);
            }
          while ((GameActivity.surfaceAction != 3) || (this.this$0.transitionFlag == 1));
          this.this$0.transitionFlag = 1;
          ((GameSurface)this.this$0.gameSurface).doStop();
          this.this$0.isChangingScreen = true;
          SharedPreferences.Editor localEditor = localSharedPreferences.edit();
          localEditor.putInt("State", 3);
          localEditor.putBoolean("ShouldContinue", false);
          localEditor.commit();
        }
      }
    }
    , 150);
  }

  public void updateLifes()
  {
    SharedPreferences localSharedPreferences = getSharedPreferences("AppData", 0);
    int i = localSharedPreferences.getInt("Lifes", 0);
    localSharedPreferences.edit().commit();
    int j = 0;
    if (j >= 5)
      return;
    if (j > 4 - i)
    {
      this.lifes[j].getBackground().mutate().setAlpha(255);
      this.livesShow[j] = Boolean.valueOf(true);
    }
    while (true)
    {
      while (true)
        ++j;
      this.lifes[j].getBackground().mutate().setAlpha(0);
      this.livesShow[j] = Boolean.valueOf(false);
    }
  }

  public void updateScores()
  {
    byte b;
    int j;
    SharedPreferences localSharedPreferences = getSharedPreferences("AppData", 0);
    int[] arrayOfInt = new int[5];
    arrayOfInt[4] = -1;
    arrayOfInt[3] = -1;
    arrayOfInt[2] = -1;
    arrayOfInt[1] = -1;
    arrayOfInt[0] = -1;
    int i = localSharedPreferences.getInt("Points", 0);
    if (i > 0)
      j = 0;
    while (true)
    {
      if (j >= Math.min((int)Math.ceil(Math.log10(0.10000000000000001D + i)), 5))
      {
        b = 0;
        if (b < 5)
          break;
        if (i < 10)
        {
          this.numbers[0].setBackgroundDrawable(this.numberFrames[i]);
          this.numbers[0].setAlpha(255);
        }
        return;
      }
      arrayOfInt[j] = ((int)(i / Math.pow(10.0D, -1 + (int)Math.ceil(Math.log10(0.10000000000000001D + i)) - j)) % 10);
      ++j;
    }
    if (arrayOfInt[b] != -1)
    {
      this.numbers[b].setAlpha(255);
      this.numbers[b].setBackgroundDrawable(this.numberFrames[arrayOfInt[b]]);
    }
    while (true)
    {
      do
        while (true)
          ++b;
      while (b == 0);
      this.numbers[b].setAlpha(0);
    }
  }
}