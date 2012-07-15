package com.bestcoolfungames.antsmasher;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.bestcoolfungames.util.Util;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class PlayMenu extends Activity
{
  static final int SMASH_YOUR_FRIENDS = 10;
  static SharedPreferences.Editor editor;
  static Bitmap[] initialAntFrames = null;
  static SharedPreferences settings;
  private DialogInterface.OnClickListener _neutralPopupOnClickListener;
  private DialogInterface.OnClickListener _noPopupOnClickListener;
  private DialogInterface.OnClickListener _yesPopupOnClickListener;
  private Boolean alreadyDoneScreenFix;
  private Boolean analyticsAlreadyDone;
  final int antSizeX;
  final int antSizeY;
  private Button babyButton;
  private Button backButton;
  TextView bg;
  private Button classicButton;
  int counter;
  final int delay = 30;
  private Button funButton;
  private int gameProduct;
  ImageView initialAnt;
  int initialAntAngle;
  boolean initialAntControl;
  int initialAntDir;
  boolean isChangingScreen;
  private Button kidsButton;
  RelativeLayout layoutView;
  int levelCount;
  private ImageView logo;
  private Context mContext;
  boolean timerControl;
  boolean viewStatus = false;

  public PlayMenu()
  {
    this.antSizeX = 50;
    this.antSizeY = 70;
    this.alreadyDoneScreenFix = Boolean.valueOf(false);
    this.isChangingScreen = false;
    this.analyticsAlreadyDone = Boolean.valueOf(false);
  }

  private void adjustLayout()
  {
    this.logo.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(this, this)
    {
      public boolean onPreDraw()
      {
        byte b1;
        int i1;
        int i2;
        int i3;
        if ((PlayMenu.access$6(this.this$0).booleanValue()) && (PlayMenu.access$7(this.this$0).getVisibility() == 4))
        {
          PlayMenu.access$8(this.this$0).setVisibility(0);
          PlayMenu.access$9(this.this$0).setVisibility(0);
          PlayMenu.access$10(this.this$0).setVisibility(0);
          PlayMenu.access$7(this.this$0).setVisibility(0);
          PlayMenu.access$11(this.this$0).setVisibility(0);
        }
        if (PlayMenu.access$6(this.this$0).booleanValue())
        {
          b1 = 1;
          return b1;
        }
        if ((!(PlayMenu.access$6(this.this$0).booleanValue())) && (this.val$activity.getResources().getConfiguration().orientation == 1))
        {
          int i = PlayMenu.access$0(this.this$0).getResources().getDisplayMetrics().heightPixels;
          int j = i - PlayMenu.access$12(this.this$0).getHeight() + ((RelativeLayout.LayoutParams)PlayMenu.access$12(this.this$0).getLayoutParams()).topMargin;
          int k = j - PlayMenu.access$7(this.this$0).getHeight() + PlayMenu.access$9(this.this$0).getHeight() + PlayMenu.access$11(this.this$0).getHeight() + Util.dipToPx(50);
          if (k >= 0)
            break label538;
          int l = 3;
          i1 = k / l;
          i2 = i - j;
          if (i1 >= 0)
            break label544;
          i3 = 0;
        }
        while (true)
        {
          while (true)
          {
            while (true)
            {
              int i5 = i2 + i3;
              RelativeLayout.LayoutParams localLayoutParams1 = (RelativeLayout.LayoutParams)PlayMenu.access$7(this.this$0).getLayoutParams();
              localLayoutParams1.topMargin = i5;
              PlayMenu.access$7(this.this$0).setLayoutParams(localLayoutParams1);
              int i6 = i5 + PlayMenu.access$7(this.this$0).getHeight() - PlayMenu.access$8(this.this$0).getHeight() / 3;
              RelativeLayout.LayoutParams localLayoutParams2 = (RelativeLayout.LayoutParams)PlayMenu.access$8(this.this$0).getLayoutParams();
              localLayoutParams2.topMargin = i6;
              PlayMenu.access$8(this.this$0).setLayoutParams(localLayoutParams2);
              int i7 = i1 + i5 + PlayMenu.access$7(this.this$0).getHeight();
              RelativeLayout.LayoutParams localLayoutParams3 = (RelativeLayout.LayoutParams)PlayMenu.access$9(this.this$0).getLayoutParams();
              localLayoutParams3.topMargin = i7;
              PlayMenu.access$9(this.this$0).setLayoutParams(localLayoutParams3);
              int i8 = i7 + PlayMenu.access$9(this.this$0).getHeight() - PlayMenu.access$8(this.this$0).getHeight() / 2;
              RelativeLayout.LayoutParams localLayoutParams4 = (RelativeLayout.LayoutParams)PlayMenu.access$10(this.this$0).getLayoutParams();
              localLayoutParams4.topMargin = i8;
              PlayMenu.access$10(this.this$0).setLayoutParams(localLayoutParams4);
              int i9 = i1 + i7 + PlayMenu.access$9(this.this$0).getHeight();
              RelativeLayout.LayoutParams localLayoutParams5 = (RelativeLayout.LayoutParams)PlayMenu.access$11(this.this$0).getLayoutParams();
              localLayoutParams5.topMargin = i9;
              PlayMenu.access$11(this.this$0).setLayoutParams(localLayoutParams5);
              PlayMenu.access$13(this.this$0, Boolean.valueOf(true));
              PlayMenu.access$14(this.this$0);
              b1 = 1;
            }
            label538: byte b2 = 4;
          }
          label544: int i4 = i1;
        }
      }
    });
  }

  private void createFinger()
  {
    if (settings.getInt("NumberOfPlayedGames", 0) > 0);
    while (true)
    {
      return;
      int i = this.mContext.getResources().getDisplayMetrics().heightPixels;
      int j = this.mContext.getResources().getDisplayMetrics().widthPixels;
      RelativeLayout.LayoutParams localLayoutParams1 = (RelativeLayout.LayoutParams)this.classicButton.getLayoutParams();
      ImageView localImageView = new ImageView(this);
      Bitmap localBitmap = BitmapFactory.decodeResource(getResources(), 2130837588);
      Matrix localMatrix = new Matrix();
      float f = -(90.0F - (float)Math.toDegrees(Math.atan((i / 2F - localLayoutParams1.topMargin + this.classicButton.getHeight() / 2F) / (j / 2F - localLayoutParams1.leftMargin + this.classicButton.getWidth() / 2F))));
      localMatrix.postRotate(f);
      localImageView.setImageBitmap(Bitmap.createBitmap(localBitmap, 0, 0, localBitmap.getWidth(), localBitmap.getHeight(), localMatrix, true));
      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams2.leftMargin = (localLayoutParams1.leftMargin + this.classicButton.getWidth() / 2);
      localLayoutParams2.topMargin = (localLayoutParams1.topMargin + this.classicButton.getHeight() / 2);
      localImageView.setLayoutParams(localLayoutParams2);
      ((RelativeLayout)findViewById(2131230791)).addView(localImageView);
      TranslateAnimation localTranslateAnimation = new TranslateAnimation((float)(-20.0D * Math.sin(Math.toRadians(f))), 0F, (float)(20.0D * Math.cos(Math.toRadians(f))), 0F);
      localTranslateAnimation.setRepeatMode(2);
      localTranslateAnimation.setRepeatCount(-1);
      localTranslateAnimation.setDuration(500);
      localImageView.setAnimation(localTranslateAnimation);
      localTranslateAnimation.start();
    }
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
          localEditor.putInt("State", 11);
          localEditor.putInt("gameOverProduct", PlayMenu.access$2(this.this$0));
          localEditor.commit();
        }
      };
    return this._yesPopupOnClickListener;
  }

  private void startAnalytics()
  {
    try
    {
      GoogleAnalyticsTracker.getInstance().start("UA-21142514-5", 10, this);
      if (!(this.analyticsAlreadyDone.booleanValue()))
      {
        GoogleAnalyticsTracker.getInstance().trackEvent("Activity", "Activity_PlayMenu_Enter", "", -1);
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

  private void stopBackgroundMusic()
  {
    if ((InitialView.backgroundMp != null) && (!(InitialView.backgroundMpErrored.booleanValue())) && (InitialView.backgroundMp.isPlaying()))
      InitialView.backgroundMp.stop();
  }

  protected void finalize()
  {
    Log.i("TAG", "Bailing Menu");
    try
    {
      finalize();
      label14: return;
    }
    catch (Throwable localThrowable)
    {
      break label14:
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

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 != -1);
    while (true)
    {
      do
        return;
      while (paramInt1 != 10);
      smashYourFriends(paramIntent.getStringExtra("croppedImageFile"));
      startGame(0);
    }
  }

  public void onBackPressed()
  {
    InitialView.playButtonSound("Out", this.mContext);
    this.isChangingScreen = true;
    editor.putInt("State", 5);
    editor.commit();
  }

  public void onCreate(Bundle paramBundle)
  {
    int i;
    setRequestedOrientation(1);
    super.onCreate(paramBundle);
    setContentView(2130903052);
    settings = getSharedPreferences("AppData", 0);
    editor = settings.edit();
    editor.putInt("State", 0);
    editor.commit();
    this.timerControl = true;
    Typeface.createFromAsset(getAssets(), "fonts/House-A-Rama-League-Night.otf");
    this.mContext = getApplicationContext();
    this.logo = ((ImageView)findViewById(2131230742));
    this.kidsButton = ((Button)findViewById(2131230792));
    this.funButton = ((Button)findViewById(2131230764));
    this.babyButton = ((Button)findViewById(2131230797));
    this.classicButton = ((Button)findViewById(2131230794));
    this.backButton = ((Button)findViewById(2131230799));
    this.kidsButton.setVisibility(4);
    this.funButton.setVisibility(4);
    this.babyButton.setVisibility(4);
    this.classicButton.setVisibility(4);
    this.backButton.setVisibility(4);
    adjustLayout();
    this.backButton.setOnClickListener(new View.OnClickListener(this)
    {
      public void onClick()
      {
        InitialView.playButtonSound("Out", PlayMenu.access$0(this.this$0));
        this.this$0.isChangingScreen = true;
        PlayMenu.editor.putInt("State", 5);
        PlayMenu.editor.commit();
      }
    });
    this.classicButton.setOnClickListener(new View.OnClickListener(this, this)
    {
      public void onClick()
      {
        InitialView.playButtonSound("In", PlayMenu.access$0(this.this$0));
        boolean bool = InitialView.canPlayMoreGames(this.val$activity);
        this.this$0.isChangingScreen = true;
        if (bool);
        try
        {
          new HashMap().put("Game_Mode", "Game_Mode_Classic");
          GoogleAnalyticsTracker.getInstance().trackEvent("Game", "Game_Mode", "Game_Mode_Classic", -1);
          GameSurface.restoreOriginalAnts();
          this.this$0.startGame(0);
          return;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
    });
    this.babyButton.setOnClickListener(new View.OnClickListener(this, this, this)
    {
      public void onClick()
      {
        InitialView.playButtonSound("In", PlayMenu.access$0(this.this$0));
        boolean bool = InitialView.canPlayMoreGames(this.val$activity);
        this.this$0.isChangingScreen = true;
        if ((!(bool)) || (this.this$0.getSharedPreferences("AntSmasherShop", 0).getBoolean("BabyMode", false)));
        try
        {
          new HashMap().put("Game_Mode", "Game_Mode_Baby");
          GoogleAnalyticsTracker.getInstance().trackEvent("Game", "Game_Mode", "Game_Mode_Baby", -1);
          this.this$0.startGame(3);
          return;
        }
        catch (Exception localException2)
        {
          while (true)
            localException2.printStackTrace();
          try
          {
            new HashMap().put("Game_Mode", "Game_Mode_TryBaby");
            GoogleAnalyticsTracker.getInstance().trackEvent("Game", "Game_Mode", "Game_Mode_TryBaby", -1);
            PlayMenu.access$1(this.this$0, 7);
            SharedPreferences localSharedPreferences = this.this$0.getSharedPreferences("AntSmasherShop", 0);
            StorePopups.showDetails(this.val$temp, localSharedPreferences, PlayMenu.access$2(this.this$0), PlayMenu.access$3(this.this$0), PlayMenu.access$4(this.this$0), PlayMenu.access$5(this.this$0));
          }
          catch (Exception localException1)
          {
            while (true)
              localException1.printStackTrace();
          }
        }
      }
    });
    this.funButton.setOnClickListener(new View.OnClickListener(this, this, this)
    {
      public void onClick()
      {
        InitialView.playButtonSound("In", PlayMenu.access$0(this.this$0));
        boolean bool = InitialView.canPlayMoreGames(this.val$activity);
        this.this$0.isChangingScreen = true;
        if ((!(bool)) || (this.this$0.getSharedPreferences("AntSmasherShop", 0).getBoolean("FunMode", false)));
        try
        {
          new HashMap().put("Game_Mode", "Game_Mode_Fun");
          GoogleAnalyticsTracker.getInstance().trackEvent("Game", "Game_Mode", "Game_Mode_Fun", -1);
          this.this$0.startGame(1);
          return;
        }
        catch (Exception localException2)
        {
          while (true)
            localException2.printStackTrace();
          try
          {
            new HashMap().put("Game_Mode", "Game_Mode_TryFun");
            GoogleAnalyticsTracker.getInstance().trackEvent("Game", "Game_Mode", "Game_Mode_TryFun", -1);
            PlayMenu.access$1(this.this$0, 1);
            SharedPreferences localSharedPreferences = this.this$0.getSharedPreferences("AntSmasherShop", 0);
            StorePopups.showDetails(this.val$temp, localSharedPreferences, PlayMenu.access$2(this.this$0), PlayMenu.access$3(this.this$0), PlayMenu.access$4(this.this$0), PlayMenu.access$5(this.this$0));
          }
          catch (Exception localException1)
          {
            while (true)
              localException1.printStackTrace();
          }
        }
      }
    });
    this.kidsButton.setOnClickListener(new View.OnClickListener(this, this, this)
    {
      public void onClick()
      {
        InitialView.playButtonSound("In", PlayMenu.access$0(this.this$0));
        boolean bool = InitialView.canPlayMoreGames(this.val$activity);
        this.this$0.isChangingScreen = true;
        if ((!(bool)) || (this.this$0.getSharedPreferences("AntSmasherShop", 0).getBoolean("KidsMode", false)));
        try
        {
          new HashMap().put("Game_Mode", "Game_Mode_Kids");
          GoogleAnalyticsTracker.getInstance().trackEvent("Game", "Game_Mode", "Game_Mode_Kids", -1);
          this.this$0.startGame(2);
          return;
        }
        catch (Exception localException2)
        {
          while (true)
            localException2.printStackTrace();
          try
          {
            new HashMap().put("Game_Mode", "Game_Mode_TryKids");
            GoogleAnalyticsTracker.getInstance().trackEvent("Game", "Game_Mode", "Game_Mode_TryKids", -1);
            PlayMenu.access$1(this.this$0, 0);
            SharedPreferences localSharedPreferences = this.this$0.getSharedPreferences("AntSmasherShop", 0);
            StorePopups.showDetails(this.val$temp, localSharedPreferences, PlayMenu.access$2(this.this$0), PlayMenu.access$3(this.this$0), PlayMenu.access$4(this.this$0), PlayMenu.access$5(this.this$0));
          }
          catch (Exception localException1)
          {
            while (true)
              localException1.printStackTrace();
          }
        }
      }
    });
    if (initialAntFrames == null)
    {
      initialAntFrames = new Bitmap[5];
      i = 0;
      if (i < 5)
        break label736;
    }
    this.initialAntAngle = (90 * InitialView.rand.nextInt(4));
    float f = getApplication().getApplicationContext().getResources().getDisplayMetrics().density;
    this.layoutView = ((RelativeLayout)findViewById(2131230791));
    this.layoutView.setOnTouchListener(new View.OnTouchListener(this)
    {
      public boolean onTouch(, MotionEvent paramMotionEvent)
      {
        if (((int)paramMotionEvent.getRawX() > this.this$0.initialAnt.getLeft()) && ((int)paramMotionEvent.getRawX() < this.this$0.initialAnt.getRight()) && ((int)paramMotionEvent.getRawY() > this.this$0.initialAnt.getTop()) && ((int)paramMotionEvent.getRawY() < this.this$0.initialAnt.getBottom()) && (this.this$0.initialAntControl))
        {
          Matrix localMatrix = new Matrix();
          localMatrix.postRotate(this.this$0.initialAntAngle);
          this.this$0.initialAnt.setBackgroundDrawable(new BitmapDrawable(Bitmap.createBitmap(PlayMenu.initialAntFrames[4], 0, 0, PlayMenu.initialAntFrames[4].getWidth(), PlayMenu.initialAntFrames[4].getHeight(), localMatrix, true)));
          MediaPlayer localMediaPlayer = MediaPlayer.create(PlayMenu.access$0(this.this$0), 2131034115);
          localMediaPlayer.setAudioStreamType(3);
          if (PlayMenu.settings.getBoolean("Sound", true))
            localMediaPlayer.start();
          localMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(this)
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
          AlphaAnimation localAlphaAnimation = new AlphaAnimation(1F, 0F);
          localAlphaAnimation.setDuration(400);
          localAlphaAnimation.setFillAfter(true);
          localAlphaAnimation.setAnimationListener(new Animation.AnimationListener(this)
          {
            public void onAnimationEnd()
            {
              int i;
              if (InitialView.rand.nextInt(2) == 0)
                i = 1;
              while (true)
              {
                PlayMenu.6.access$0(this.this$1).setViewPlace(PlayMenu.6.access$0(this.this$1).initialAnt, i * InitialView.rand.nextInt(PlayMenu.6.access$0(this.this$1).layoutView.getWidth() / 2), -1 * i * InitialView.rand.nextInt(PlayMenu.6.access$0(this.this$1).layoutView.getHeight() / 2));
                PlayMenu.6.access$0(this.this$1).initialAntControl = true;
                return;
                i = -1;
              }
            }

            public void onAnimationRepeat()
            {
            }

            public void onAnimationStart()
            {
            }
          });
          this.this$0.initialAnt.startAnimation(localAlphaAnimation);
          this.this$0.initialAntControl = false;
        }
        return true;
      }
    });
    this.initialAnt = new ImageView(this, this.mContext, null)
    {
      protected void finalize()
      {
        Log.i("TAG", "Ant Dealloc");
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
    };
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams((int)(50.0F * f), (int)(70.0F * f));
    localLayoutParams.setMargins(getApplication().getApplicationContext().getResources().getDisplayMetrics().widthPixels / 2, getApplication().getApplicationContext().getResources().getDisplayMetrics().heightPixels / 2, 0, 0);
    this.initialAnt.setLayoutParams(localLayoutParams);
    this.layoutView.addView(this.initialAnt);
    Matrix localMatrix = new Matrix();
    localMatrix.postRotate(180.0F);
    try
    {
      ImageView localImageView = this.initialAnt;
      BitmapDrawable localBitmapDrawable = new BitmapDrawable(Bitmap.createBitmap(initialAntFrames[0], 0, 0, initialAntFrames[0].getWidth(), initialAntFrames[0].getHeight(), localMatrix, true));
      localImageView.setBackgroundDrawable(localBitmapDrawable);
      this.initialAntControl = true;
      if (this.mContext.getResources().getDisplayMetrics().heightPixels < 480)
      {
        this.initialAntControl = false;
        this.initialAnt.getBackground().mutate().setAlpha(0);
      }
      this.counter = 0;
      this.initialAntDir = 1;
      findViewById(2131230793).setVisibility(4);
      findViewById(2131230795).setVisibility(4);
      findViewById(2131230796).setVisibility(4);
      findViewById(2131230798).setVisibility(4);
      findViewById(2131230800).setVisibility(4);
      if (this.mContext.getResources().getDisplayMetrics().heightPixels >= 480)
        updateInitAnt();
      this.initialAnt.setOnTouchListener(new View.OnTouchListener(this)
      {
        public boolean onTouch(, MotionEvent paramMotionEvent)
        {
          if (this.this$0.initialAntControl)
          {
            Matrix localMatrix = new Matrix();
            localMatrix.postRotate(this.this$0.initialAntAngle);
            paramView.setBackgroundDrawable(new BitmapDrawable(Bitmap.createBitmap(PlayMenu.initialAntFrames[4], 0, 0, PlayMenu.initialAntFrames[4].getWidth(), PlayMenu.initialAntFrames[4].getHeight(), localMatrix, true)));
            MediaPlayer localMediaPlayer = MediaPlayer.create(PlayMenu.access$0(this.this$0), 2131034115);
            localMediaPlayer.setAudioStreamType(3);
            if (PlayMenu.settings.getBoolean("Sound", true))
              localMediaPlayer.start();
            localMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(this)
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
            AlphaAnimation localAlphaAnimation = new AlphaAnimation(1F, 0F);
            localAlphaAnimation.setDuration(400);
            localAlphaAnimation.setFillAfter(true);
            localAlphaAnimation.setAnimationListener(new Animation.AnimationListener(this)
            {
              public void onAnimationEnd()
              {
                int i;
                if (InitialView.rand.nextInt(2) == 0)
                  i = 1;
                while (true)
                {
                  PlayMenu.8.access$0(this.this$1).setViewPlace(PlayMenu.8.access$0(this.this$1).initialAnt, i * InitialView.rand.nextInt(PlayMenu.8.access$0(this.this$1).layoutView.getWidth() / 2), -1 * i * InitialView.rand.nextInt(PlayMenu.8.access$0(this.this$1).layoutView.getHeight() / 2));
                  PlayMenu.8.access$0(this.this$1).initialAntControl = true;
                  return;
                  i = -1;
                }
              }

              public void onAnimationRepeat()
              {
              }

              public void onAnimationStart()
              {
              }
            });
            this.this$0.initialAnt.startAnimation(localAlphaAnimation);
            this.this$0.initialAntControl = false;
          }
          return false;
        }
      });
      this.classicButton.bringToFront();
      this.kidsButton.bringToFront();
      this.funButton.bringToFront();
      this.babyButton.bringToFront();
      this.backButton.bringToFront();
      update();
      return;
      label736: initialAntFrames[i] = BitmapFactory.decodeResource(this.mContext.getResources(), 2130837515 + i);
      ++i;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      while (true)
        localIllegalArgumentException.printStackTrace();
    }
  }

  protected void onPause()
  {
    if (!(this.isChangingScreen))
    {
      stopBackgroundMusic();
      Log.i("AS", "Going to quit app");
      SharedPreferences.Editor localEditor2 = getSharedPreferences("AntSmasherShop", 0).edit();
      localEditor2.putBoolean("InitialProductAlreadyOffered", false);
      localEditor2.commit();
      this.viewStatus = false;
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
          label107: super.onPause();
          return;
          Log.i("AS", "Going to change screen");
          this.isChangingScreen = false;
        }
        localException1 = localException1;
        localException1.printStackTrace();
      }
      catch (Exception localException2)
      {
        break label107:
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
      this.viewStatus = true;
    }
  }

  protected void onStart()
  {
    this.viewStatus = true;
    InitialView.shownActivity = this;
    InitialView.createBackgroundMusic(this);
    startAnalytics();
    AdsUtils.configureBanner(this, this.layoutView, null);
    super.onStart();
  }

  public void setViewPlace(ImageView paramImageView, int paramInt1, int paramInt2)
  {
    float f = getApplication().getApplicationContext().getResources().getDisplayMetrics().density;
    int i = paramInt1 - paramImageView.getLeft();
    int j = paramInt2 - paramImageView.getTop();
    paramImageView.layout(i + paramImageView.getLeft(), j + paramImageView.getTop(), i + paramImageView.getLeft() + paramImageView.getWidth(), j + paramImageView.getTop() + paramImageView.getHeight());
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(paramImageView.getWidth(), paramImageView.getHeight());
    localLayoutParams1.setMargins(paramImageView.getLeft(), paramImageView.getTop(), 0, 0);
    paramImageView.setLayoutParams(localLayoutParams1);
    paramImageView.setAnimation(null);
    int k = (int)(50.0D * Math.abs(Math.cos(Math.toRadians(this.initialAntAngle))) + 70.0D * Math.abs(Math.sin(Math.toRadians(this.initialAntAngle))));
    int l = (int)(70.0D * Math.abs(Math.cos(Math.toRadians(this.initialAntAngle))) + 50.0D * Math.abs(Math.sin(Math.toRadians(this.initialAntAngle))));
    int i1 = (this.initialAnt.getLeft() + this.initialAnt.getRight()) / 2;
    int i2 = (this.initialAnt.getTop() + this.initialAnt.getBottom()) / 2;
    int i3 = (int)(f * k);
    int i4 = (int)(f * l);
    this.initialAnt.layout(i1 - i3 / 2, i2 - i4 / 2, i1 + i3 / 2, i2 + i4 / 2);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(i3, i4);
    localLayoutParams2.setMargins(i1 - i3 / 2, i2 - i4 / 2, 0, 0);
    this.initialAnt.setLayoutParams(localLayoutParams2);
    Matrix localMatrix = new Matrix();
    localMatrix.postRotate(this.initialAntAngle);
    if ((this.initialAnt.getTop() > -this.initialAnt.getHeight()) && (this.initialAnt.getTop() < this.layoutView.getHeight()))
    {
      ImageView localImageView = this.initialAnt;
      BitmapDrawable localBitmapDrawable = new BitmapDrawable(Bitmap.createBitmap(initialAntFrames[(this.counter / 5 % 4)], 0, 0, initialAntFrames[(this.counter / 5 % 4)].getWidth(), initialAntFrames[(this.counter / 5 % 4)].getHeight(), localMatrix, true));
      localImageView.setBackgroundDrawable(localBitmapDrawable);
    }
  }

  void smashYourFriends(String paramString)
  {
    Util.setContext(this.mContext);
    System.gc();
    Object localObject = null;
    try
    {
      Bitmap localBitmap = BitmapFactory.decodeFile(paramString);
      localObject = localBitmap;
      label21: System.gc();
      if (localObject != null)
        GameSurface.setFixedBitmap(getApplicationContext(), localObject);
      return;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      break label21:
    }
  }

  void startGame(int paramInt)
  {
    stopBackgroundMusic();
    if (settings.getBoolean("Vibration", false))
      ((Vibrator)getSystemService("vibrator")).vibrate(1000);
    if (settings.getInt("NumberOfPlayedGames", 0) == 0)
      editor.putInt("State", 15);
    while (true)
    {
      editor.putInt("GameMode", paramInt);
      editor.commit();
      return;
      editor.putInt("State", 4);
    }
  }

  public void update()
  {
    Handler localHandler = new Handler();
    localHandler.postDelayed(new Runnable(this, localHandler)
    {
      public void run()
      {
        SharedPreferences localSharedPreferences = this.this$0.getSharedPreferences("AppData", 0);
        if ((localSharedPreferences.getInt("State", -1) == 0) && (this.this$0.viewStatus))
          this.this$0.updateInitAnt();
        if ((this.this$0.timerControl) && (!(localSharedPreferences.getBoolean("GamePaused", false))))
          this.val$handler.postDelayed(this, 30);
      }
    }
    , 30);
  }

  void updateInitAnt()
  {
    SharedPreferences localSharedPreferences = getSharedPreferences("AppData", 0);
    if ((this.initialAntControl) && (localSharedPreferences.getInt("State", -1) == 0))
    {
      if (this.initialAnt.getLeft() >= this.layoutView.getWidth() - this.initialAnt.getWidth())
        this.initialAntAngle = 270;
      if (this.initialAnt.getTop() >= this.layoutView.getHeight() - this.initialAnt.getHeight())
        this.initialAntAngle = 0;
      if (this.initialAnt.getLeft() < 0)
        this.initialAntAngle = 90;
      if (this.initialAnt.getTop() < 0)
        this.initialAntAngle = 180;
      if (InitialView.rand.nextInt(10) < 2)
        this.initialAntDir = (-1 * this.initialAntDir);
      this.initialAntAngle = (int)(this.initialAntAngle + 3.6000000000000001D * this.initialAntDir);
      setViewPlace(this.initialAnt, (int)(this.initialAnt.getLeft() + 10.0D * Math.sin(Math.toRadians(this.initialAntAngle))), (int)(this.initialAnt.getTop() - 10.0D * Math.cos(Math.toRadians(this.initialAntAngle))));
    }
    this.counter = (1 + this.counter);
  }
}