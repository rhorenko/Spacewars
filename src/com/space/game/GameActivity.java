package com.space.game;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.space.levels.LevelInterface;
import com.space.levels.LevelsList;

public class GameActivity extends Activity
{	
	public final static String TAG = "StempWars->GameActivity";
	
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
    scoreBar = null;
    pauseButton = null;
    pauseWarning = null;
    bringItOnHome = false;
    gameMode = 0;
    analyticsAlreadyDone = Boolean.valueOf(false);
  }

  private void createLevel(Class<?> paramClass)
  {
    Constructor localConstructor = paramClass.getConstructors()[0];
    try
    {
      if (gameSurface != null)
      {
        surfaceView.surfaceDestroyed(null);
        surfaceView.stop();
      }
      gameSurface = ((LevelInterface)localConstructor.newInstance(new Object[0]));
      Context localContext = getApplicationContext();
      if (surfaceView == null)
        surfaceView = new GameSurfaceView(localContext);
      ((GameSurface)gameSurface).setup(localContext, surfaceView);
      surfaceView.setSurfaceSize(localContext.getResources().getDisplayMetrics().widthPixels, localContext.getResources().getDisplayMetrics().heightPixels);
      GameSurface localGameSurface1 = (GameSurface)gameSurface;
      localGameSurface1.tempX = (int)(0.65000000000000002D * localGameSurface1.tempX);
      GameSurface localGameSurface2 = (GameSurface)gameSurface;
      localGameSurface2.tempY = (int)(0.75D * localGameSurface2.tempY);
      return;
    }
    catch (Exception localException)
    {
      throw new IllegalStateException("Oh, No!", localException);
    }
  }

  private void startBackgroundMusic(GameActivity paramGameActivity)
  {
    backgroundMpErrored = Boolean.valueOf(false);
    backgroundMp = MediaPlayer.create(paramGameActivity, 2131034121);
    
    backgroundMp.setAudioStreamType(3);
    backgroundMp.setLooping(true);
    backgroundMp.setVolume(0.40000000596046448F, 0.40000000596046448F);
    if (getSharedPreferences("AppData", 0).getBoolean("Sound", true))
      backgroundMp.start();
    backgroundMp.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
    {
      public void onCompletion(MediaPlayer paramMediaPlayer)
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
    if (levelChosen[i] != false)
    {
      i = InitialView.rand.nextInt(LevelsList.numberOfLevels);
      if (levelChosen[i] != false)
      {
        i = InitialView.rand.nextInt(LevelsList.numberOfLevels);
        if (levelChosen[i] != false)
        {
          i = InitialView.rand.nextInt(LevelsList.numberOfLevels);
          if (levelChosen[i] != false)
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
              if (levelChosen[k] != false)
                break label149;
              i = k;
              levelChosen[k] = true;
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
                levelChosen[i] = true;
                b = 1;
              }
              levelChosen[l] = false;
              ++l;
            }
            levelChosen[i] = true;
          }
          levelChosen[i] = true;
        }
        levelChosen[i] = true;
      }
      levelChosen[i] = true;
    }
  }

  public void onBackPressed()
  {
    isChangingScreen = true;
    if (gameMode == 3)
    {
      staticToast = Toast.makeText(this, getString(2131165215), 0);
      staticToast.show();
    }
    while (true)
    {
      
      ((GameSurface)gameSurface).doStop();
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
    int k = 0;
    running = true;
    levelChosen = new boolean[LevelsList.numberOfLevels];
    transitionFlag = 0;
    SharedPreferences localSharedPreferences = getSharedPreferences("AppData", 0);
    if (localSharedPreferences.getInt("Points", 0) == 0)
    {
      overridePendingTransition(2130968576, 2130968577);
      gameMode = localSharedPreferences.getInt("GameMode", 0);
      super.onCreate(paramBundle);
      setVolumeControlStream(3);
      setRequestedOrientation(1);
      setContentView(2130903043);
      layoutView = ((RelativeLayout)findViewById(2131230728));
      if (!(localSharedPreferences.getBoolean("FirstLevel", false))) return;
      localClass = LevelsList.getLevel(0);
      createLevel(localClass);
      RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -1);
      ((GameSurface)gameSurface).setLayoutParams(localLayoutParams1);
      GameSurface localGameSurface1 = (GameSurface)gameSurface;
      localGameSurface1.tempX = (int)(0.69999998807907104F * localGameSurface1.tempX);
      GameSurface localGameSurface2 = (GameSurface)gameSurface;
      localGameSurface2.tempY = (int)(0.69999998807907104F * localGameSurface2.tempY);
      layoutView.addView(((GameSurface)gameSurface).getSurfaceView());
      scoreBar = ((ImageView)findViewById(2131230729));
      scoreBar.bringToFront();
      pauseButton = ((ImageView)findViewById(2131230736));
      pauseButton.bringToFront();
      pauseWarning = ((ImageView)findViewById(2131230737));
      pauseWarning.bringToFront();
      pauseWarning.setVisibility(4);
      int i = getResources().getDisplayMetrics().widthPixels;
      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(i, -2);
      localLayoutParams2.leftMargin = (-i);
      localLayoutParams2.topMargin = 200;
      pauseWarning.setLayoutParams(localLayoutParams2);
      pauseButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View v) {
			// TODO Auto-generated method stub
			GameActivity.this.pause(Boolean.valueOf(false));
		}
      });
      f = getApplicationContext().getResources().getDisplayMetrics().density;
      numberFrames = new Drawable[10];
      numbers = new ImageView[5];
      lifes = new ImageView[5];
      livesShow = new Boolean[5];
      j = 0;
      if (j < 10) return;
      b = 0;
      if (b < 5) return;
      numbers[0].setBackgroundDrawable(numberFrames[0]);
      numbers[0].setAlpha(255);
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
                return;
              updateScores();
              updateLifes();
              update();
              pauseWarning.bringToFront();
              return;
              overridePendingTransition(0, 2130968577);
            }
            label486: localClass = LevelsList.getLevel(InitialView.rand.nextInt(LevelsList.numberOfLevels));
          }
          label502: numberFrames[j] = getApplicationContext().getResources().getDrawable(2130837627 + j);
          ++j;
        }
        label531: numbers[b] = new ImageView(getApplicationContext(), null);
        numbers[b].bringToFront();
        RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams((int)(35.0F * f), (int)(35.0F * f));
        localLayoutParams3.setMargins(1 + b * 20, 0, 0, 0);
        numbers[b].setLayoutParams(localLayoutParams3);
        layoutView.addView(numbers[b]);
        numbers[b].setAlpha(0);
        ++b;
      }
      label641: lifes[k] = ((ImageView)findViewById(2131230731 + k));
      lifes[k].setBackgroundResource(2130837664);
      lifes[k].getBackground().mutate().setAlpha(255);
      livesShow[k] = Boolean.valueOf(true);
      lifes[k].bringToFront();
      ++k;
    }
  }

  public boolean onKeyLongPress(int paramInt, KeyEvent paramKeyEvent)
  {
    byte b;
    if (gameMode == 3)
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
    if ((backgroundMp != null) && (!(backgroundMpErrored.booleanValue())) && (backgroundMp.isPlaying()))
      backgroundMp.stop();
    if (!(isChangingScreen))
    {
      Log.i("AS", "Going to quit app");
      SharedPreferences.Editor localEditor2 = getSharedPreferences("AntSmasherShop", 0).edit();
      localEditor2.putBoolean("InitialProductAlreadyOffered", false);
      localEditor2.commit();
      SharedPreferences.Editor localEditor1 = getSharedPreferences("AppData", 0).edit();
      localEditor1.putBoolean("GamePaused", true);
      localEditor1.commit();
      if (gameMode == 3)
      {
        ((GameSurface)gameSurface).doStop();
        localEditor1.putInt("State", 5);
        localEditor1.commit();
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
    AdsUtils.configureBanner(this, layoutView, Integer.valueOf(gameMode));
    startAnalytics();
    ((GameSurface)gameSurface).start();
    thisActivity = this;
    startBackgroundMusic((GameActivity)thisActivity);
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
      ((GameSurface)gameSurface).refreshScreen();
    }
  }

  public void pause(Boolean paramBoolean)
  {
    if (gameMode == 3)
      return;
    int i = getResources().getDisplayMetrics().widthPixels;
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(i, -2);
    localLayoutParams.leftMargin = 0;
    localLayoutParams.topMargin = 200;
    pauseWarning.setLayoutParams(localLayoutParams);
    if ((pauseWarning.getVisibility() == 0) && (!(paramBoolean.booleanValue())))
    {
      TranslateAnimation localTranslateAnimation2 = new TranslateAnimation(0F, -i, 0F, 0F);
      localTranslateAnimation2.setDuration(200);
      localTranslateAnimation2.setFillEnabled(true);
      localTranslateAnimation2.setFillBefore(true);
      pauseWarning.startAnimation(localTranslateAnimation2);
      localTranslateAnimation2.setAnimationListener(new Animation.AnimationListener(this, i)
      {
        public void onAnimationEnd()
        {
          RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(val$deviceWidth, -2);
          localLayoutParams.leftMargin = (-val$deviceWidth);
          localLayoutParams.topMargin = 200;
          this$0.pauseWarning.setLayoutParams(localLayoutParams);
          this$0.pauseWarning.setVisibility(4);
          ((GameSurface)this$0.gameSurface).doResume();
          GameActivity.access$1(this$0, (GameActivity)GameActivity.access$0(this$0));
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
        pauseWarning.bringToFront();
      ((GameSurface)gameSurface).doPause();
      if ((backgroundMp != null) && (!(backgroundMpErrored.booleanValue())) && (backgroundMp.isPlaying()))
        backgroundMp.stop();
      pauseWarning.setVisibility(0);
      TranslateAnimation localTranslateAnimation1 = new TranslateAnimation(-i, 0F, 0F, 0F);
      localTranslateAnimation1.setDuration(200);
      localTranslateAnimation1.setFillEnabled(true);
      localTranslateAnimation1.setFillBefore(true);
      pauseWarning.startAnimation(localTranslateAnimation1);
      localTranslateAnimation1.setAnimationListener(new Animation.AnimationListener(this, i)
      {
        public void onAnimationEnd()
        {
          RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(val$deviceWidth, -2);
          localLayoutParams.leftMargin = 0;
          localLayoutParams.topMargin = 200;
          this$0.pauseWarning.setLayoutParams(localLayoutParams);
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
        if (livesShow[b1].booleanValue())
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
          lifes[i1].getBackground().mutate().setAlpha(255);
          livesShow[i1] = Boolean.valueOf(true);
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
                if ((val$index >= 0) && (val$index <= 4))
                {
                  this$0.lifes[val$index].getBackground().mutate().setAlpha((int)(255.0F * val$to));
                  arrayOfBoolean = GameActivity.access$4(this$0);
                  i = val$index;
                  if (val$to < 1F)
                    break label180;
                  j = 1;
                }
                while (true)
                {
                  arrayOfBoolean[i] = Boolean.valueOf(j);
                  SharedPreferences localSharedPreferences = this$0.getSharedPreferences("AppData", 0);
                  if ((localSharedPreferences.getInt("Lifes", 0) < 1) && (this$0.transitionFlag != 1))
                  {
                    this$0.transitionFlag = 1;
                    ((GameSurface)this$0.gameSurface).doStop();
                    this$0.isChangingScreen = true;
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
          lifes[i1].startAnimation(localAnimationSet);
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
      lifes[localInteger.intValue()].getBackground().mutate().setAlpha((int)(255.0F * f1));
      lifes[localInteger.intValue()].invalidate();
      arrayOfBoolean = livesShow;
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
          while ((i >= 1) || (transitionFlag == 1));
          transitionFlag = 1;
          ((GameSurface)gameSurface).doStop();
          isChangingScreen = true;
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
        SharedPreferences localSharedPreferences = this$0.getSharedPreferences("AppData", 0);
        this$0.updateScores();
        if (GameActivity.lifeFlag != 0)
        {
          this$0.setLifes(GameActivity.lifeFlag);
          if (GameActivity.lifeFlag == -2)
            this$0.setLifes(GameActivity.lifeFlag);
          GameActivity.lifeFlag = 0;
        }
        if (localSharedPreferences.getBoolean("ShouldContinue", false))
          val$handler.postDelayed(this, Math.max(150 - System.currentTimeMillis() - l, 1L));
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
              localAlphaAnimation.setAnimationListener(new Animation.AnimationListener(this, localSharedPreferences, val$mContext)
              {
                public void onAnimationEnd()
                {
                  float f1;
                  float f2;
                  Class localClass;
                  byte b1;
                  byte b2;
                  ((GameSurface)GameActivity.4.access$0(this$1).gameSurface).doStop();
                  SharedPreferences.Editor localEditor = val$settings.edit();
                  GameActivity.surfaceAction = 0;
                  if (val$settings.getInt("Lifes", -1) > 0)
                  {
                    GameActivity.4.access$0(this$1).isChangingScreen = true;
                    localEditor.putInt("State", 1);
                    localEditor.putBoolean("ShouldContinue", true);
                    int i = val$settings.getInt("GameMode", 0);
                    f1 = val$settings.getFloat("Acceleration", 0F);
                    f2 = val$settings.getFloat(Constants.key.getABDifficultyLabel(val$mContext), 0F);
                    if (!(f2 < 0F))
                      f2 = 3.5F;
                    Util.DebugLog("levelAcc - " + String.valueOf(f1 + f2));
                    if (GameActivity.4.access$0(this$1).getResources().getDisplayMetrics().widthPixels > 600)
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
                      if (!(val$settings.getBoolean("FirstLevel", false)))
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
                                ((GameSurface)GameActivity.4.access$0(this$1).gameSurface).getThread().join();
                                GameSurface localGameSurface1 = (GameSurface)GameActivity.4.access$0(this$1).gameSurface;
                                localGameSurface1.tempX = (int)(0.80000001192092896F * localGameSurface1.tempX);
                                GameSurface localGameSurface2 = (GameSurface)GameActivity.4.access$0(this$1).gameSurface;
                                localGameSurface2.tempY = (int)(0.80000001192092896F * localGameSurface2.tempY);
                                GameActivity.access$2(GameActivity.4.access$0(this$1), localClass);
                                GameActivity.4.access$0(this$1).scoreBar.bringToFront();
                                GameActivity.4.access$0(this$1).pauseButton.bringToFront();
                                if (GameActivity.4.access$0(this$1).bringItOnHome)
                                  GameActivity.4.access$0(this$1).pauseWarning.bringToFront();
                                b1 = 0;
                                if (b1 < 5)
                                  break label636;
                                b2 = 0;
                                if (b2 < 5)
                                  break label658;
                                ((GameSurface)GameActivity.4.access$0(this$1).gameSurface).getThread().start();
                                GameActivity.4.access$0(this$1).update();
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
                    label658: label608: label636: localClass = LevelsList.getLevel(GameActivity.4.access$0(this$1).nextLevel());
                  }
                  catch (InterruptedException localInterruptedException)
                  {
                    while (true)
                    {
                      while (true)
                      {
                        while (true)
                          localInterruptedException.printStackTrace();
                        GameActivity.4.access$0(this$1).numbers[b1].bringToFront();
                        ++b1;
                      }
                      GameActivity.4.access$0(this$1).lifes[b2].bringToFront();
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
              GameActivity.access$3(this$0).startAnimation(localAlphaAnimation);
            }
          while ((GameActivity.surfaceAction != 3) || (this$0.transitionFlag == 1));
          this$0.transitionFlag = 1;
          ((GameSurface)this$0.gameSurface).doStop();
          this$0.isChangingScreen = true;
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
      lifes[j].getBackground().mutate().setAlpha(255);
      livesShow[j] = Boolean.valueOf(true);
    }
    while (true)
    {
      while (true)
        ++j;
      lifes[j].getBackground().mutate().setAlpha(0);
      livesShow[j] = Boolean.valueOf(false);
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
          numbers[0].setBackgroundDrawable(numberFrames[i]);
          numbers[0].setAlpha(255);
        }
        return;
      }
      arrayOfInt[j] = ((int)(i / Math.pow(10.0D, -1 + (int)Math.ceil(Math.log10(0.10000000000000001D + i)) - j)) % 10);
      ++j;
    }
    if (arrayOfInt[b] != -1)
    {
      numbers[b].setAlpha(255);
      numbers[b].setBackgroundDrawable(numberFrames[arrayOfInt[b]]);
    }
    while (true)
    {
      do
        while (true)
          ++b;
      while (b == 0);
      numbers[b].setAlpha(0);
    }
  }
}