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
import android.graphics.drawable.StateListDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
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
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.bestcoolfungames.imagecropper.ImageCropper;
import com.bestcoolfungames.util.BcfgLogger;
import com.bestcoolfungames.util.Util;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;
import com.revmob.RevMobAds;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class Menu extends Activity
  implements MetaTagListener
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
  int count;
  int counter;
  final int delay = 30;
  private int gameOverProduct;
  Button gamesButton;
  ImageView initialAnt;
  int initialAntAngle;
  boolean initialAntControl;
  int initialAntDir;
  boolean isChangingScreen;
  RelativeLayout layoutView;
  int levelCount;
  ImageView logo;
  private Activity mActivity;
  private Context mContext;
  boolean mustKillSound;
  Button optionsButton;
  Button playButton;
  Button scoresButton;
  Button shopButton;
  Button smashButton;
  ImageView tapJoyExplanation;
  boolean timerControl;
  boolean viewStatus = false;

  public Menu()
  {
    this.antSizeX = 50;
    this.antSizeY = 70;
    this.analyticsAlreadyDone = Boolean.valueOf(false);
    this.isChangingScreen = false;
    this.mustKillSound = false;
    this.alreadyDoneScreenFix = Boolean.valueOf(false);
    this.count = 0;
  }

  private void adjustLayout()
  {
    this.logo.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(this, this)
    {
      public boolean onPreDraw()
      {
        byte b1;
        int i;
        int j;
        int i2;
        int i7;
        int i8;
        if ((Menu.access$3(this.this$0).booleanValue()) && (this.this$0.playButton.getVisibility() == 4))
        {
          this.this$0.playButton.setVisibility(0);
          this.this$0.scoresButton.setVisibility(0);
          this.this$0.optionsButton.setVisibility(0);
          this.this$0.shopButton.setVisibility(0);
          this.this$0.smashButton.setVisibility(0);
          this.this$0.gamesButton.setVisibility(0);
        }
        if (Menu.access$3(this.this$0).booleanValue())
        {
          b1 = 1;
          return b1;
        }
        if ((!(Menu.access$3(this.this$0).booleanValue())) && (this.val$activity.getResources().getConfiguration().orientation == 1))
        {
          i = Menu.access$0(this.this$0).getResources().getDisplayMetrics().heightPixels;
          j = Menu.access$0(this.this$0).getResources().getDisplayMetrics().widthPixels;
          int k = i - Util.dipToPx(50);
          int l = i - this.this$0.logo.getHeight() + ((RelativeLayout.LayoutParams)this.this$0.logo.getLayoutParams()).topMargin;
          int i1 = this.this$0.playButton.getHeight() + this.this$0.scoresButton.getHeight() + this.this$0.shopButton.getHeight() + this.this$0.optionsButton.getHeight() + Util.dipToPx(50);
          RelativeLayout.LayoutParams localLayoutParams1 = (RelativeLayout.LayoutParams)this.this$0.tapJoyExplanation.getLayoutParams();
          localLayoutParams1.width = (j * 90 / 100);
          localLayoutParams1.height = localLayoutParams1.width;
          if (localLayoutParams1.height >= k)
          {
            localLayoutParams1.height = k;
            localLayoutParams1.width = localLayoutParams1.height;
          }
          localLayoutParams1.topMargin = ((k - localLayoutParams1.height) / 2);
          this.this$0.tapJoyExplanation.setLayoutParams(localLayoutParams1);
          i2 = Math.max(-12, (int)((l - 1D * i1) / 3.0D));
          int i3 = i - l;
          if (i2 >= 0)
            break label1828;
          int i4 = 0;
          int i6 = i3 + i4;
          RelativeLayout.LayoutParams localLayoutParams2 = (RelativeLayout.LayoutParams)this.this$0.playButton.getLayoutParams();
          localLayoutParams2.topMargin = i6;
          localLayoutParams2.height = (int)(1D * this.this$0.playButton.getHeight());
          this.this$0.playButton.setLayoutParams(localLayoutParams2);
          TextView localTextView1 = (TextView)this.this$0.findViewById(2131230762);
          RelativeLayout.LayoutParams localLayoutParams3 = (RelativeLayout.LayoutParams)localTextView1.getLayoutParams();
          localLayoutParams3.leftMargin = (45 * this.this$0.playButton.getWidth() / 185);
          localLayoutParams3.topMargin = (3 * this.this$0.playButton.getHeight() / 101);
          localLayoutParams3.width = (110 * this.this$0.playButton.getWidth() / 185);
          localLayoutParams3.height = (80 * this.this$0.playButton.getHeight() / 101);
          localTextView1.setLayoutParams(localLayoutParams3);
          TextViewResizer.resizeTextView(localTextView1, 124 * localLayoutParams3.width / 165, 86 * localLayoutParams3.height / 120);
          i7 = ((RelativeLayout.LayoutParams)this.this$0.playButton.getLayoutParams()).topMargin + (int)(1D * this.this$0.playButton.getHeight());
          if (i2 >= 0)
            break label1835;
          i8 = -(int)(1D * this.this$0.shopButton.getHeight() / 2.0D);
        }
        while (true)
        {
          while (true)
          {
            while (true)
            {
              int i9 = i7 + i8;
              RelativeLayout.LayoutParams localLayoutParams4 = (RelativeLayout.LayoutParams)this.this$0.shopButton.getLayoutParams();
              localLayoutParams4.topMargin = i9;
              localLayoutParams4.height = (int)(1D * this.this$0.shopButton.getHeight());
              localLayoutParams4.width = (int)(1D * this.this$0.shopButton.getWidth());
              this.this$0.shopButton.setLayoutParams(localLayoutParams4);
              TextView localTextView2 = (TextView)this.this$0.findViewById(2131230770);
              RelativeLayout.LayoutParams localLayoutParams5 = (RelativeLayout.LayoutParams)localTextView2.getLayoutParams();
              localLayoutParams5.leftMargin = (33 * this.this$0.shopButton.getWidth() / 172);
              localLayoutParams5.topMargin = (7 * this.this$0.shopButton.getHeight() / 88);
              localLayoutParams5.width = (100 * this.this$0.shopButton.getWidth() / 172);
              localLayoutParams5.height = (63 * this.this$0.shopButton.getHeight() / 88);
              localTextView2.setLayoutParams(localLayoutParams5);
              TextViewResizer.resizeTextView(localTextView2, 124 * localLayoutParams5.width / 148, 75 * localLayoutParams5.height / 95);
              int i10 = i2 + ((RelativeLayout.LayoutParams)this.this$0.shopButton.getLayoutParams()).topMargin + (int)(1D * this.this$0.shopButton.getHeight());
              RelativeLayout.LayoutParams localLayoutParams6 = (RelativeLayout.LayoutParams)this.this$0.scoresButton.getLayoutParams();
              localLayoutParams6.topMargin = i10;
              localLayoutParams6.height = (int)(1D * this.this$0.scoresButton.getHeight());
              this.this$0.scoresButton.setLayoutParams(localLayoutParams6);
              TextView localTextView3 = (TextView)this.this$0.findViewById(2131230768);
              RelativeLayout.LayoutParams localLayoutParams7 = (RelativeLayout.LayoutParams)localTextView3.getLayoutParams();
              localLayoutParams7.leftMargin = (12 * this.this$0.scoresButton.getWidth() / 146);
              localLayoutParams7.topMargin = (11 * this.this$0.scoresButton.getHeight() / 82);
              localLayoutParams7.width = (115 * this.this$0.scoresButton.getWidth() / 146);
              localLayoutParams7.height = (57 * this.this$0.scoresButton.getHeight() / 82);
              localTextView3.setLayoutParams(localLayoutParams7);
              TextViewResizer.resizeTextView(localTextView3, 136 * localLayoutParams7.width / 173, 38 * localLayoutParams7.height / 85);
              int i11 = i2 + ((RelativeLayout.LayoutParams)this.this$0.scoresButton.getLayoutParams()).topMargin + (int)(1D * this.this$0.scoresButton.getHeight());
              RelativeLayout.LayoutParams localLayoutParams8 = (RelativeLayout.LayoutParams)this.this$0.optionsButton.getLayoutParams();
              localLayoutParams8.topMargin = i11;
              localLayoutParams8.height = (int)(1D * this.this$0.optionsButton.getHeight());
              this.this$0.optionsButton.setLayoutParams(localLayoutParams8);
              TextView localTextView4 = (TextView)this.this$0.findViewById(2131230772);
              RelativeLayout.LayoutParams localLayoutParams9 = (RelativeLayout.LayoutParams)localTextView4.getLayoutParams();
              localLayoutParams9.leftMargin = (22 * this.this$0.optionsButton.getWidth() / 148);
              localLayoutParams9.topMargin = (11 * this.this$0.optionsButton.getHeight() / 79);
              localLayoutParams9.width = (105 * this.this$0.optionsButton.getWidth() / 148);
              localLayoutParams9.height = (55 * this.this$0.optionsButton.getHeight() / 79);
              localTextView4.setLayoutParams(localLayoutParams9);
              TextViewResizer.resizeTextView(localTextView4, 142 * localLayoutParams9.width / 158, 55 * localLayoutParams9.height / 83);
              int i12 = ((RelativeLayout.LayoutParams)this.this$0.shopButton.getLayoutParams()).topMargin + (int)(1D * this.this$0.shopButton.getHeight()) - (int)(0.59999999999999998D * 1D * this.this$0.smashButton.getHeight());
              RelativeLayout.LayoutParams localLayoutParams10 = (RelativeLayout.LayoutParams)this.this$0.smashButton.getLayoutParams();
              localLayoutParams10.topMargin = i12;
              localLayoutParams10.height = (int)(1D * this.this$0.smashButton.getHeight());
              this.this$0.smashButton.setLayoutParams(localLayoutParams10);
              TextView localTextView5 = (TextView)this.this$0.findViewById(2131230765);
              RelativeLayout.LayoutParams localLayoutParams11 = (RelativeLayout.LayoutParams)localTextView5.getLayoutParams();
              localLayoutParams11.leftMargin = (26 * this.this$0.smashButton.getWidth() / 178);
              localLayoutParams11.topMargin = (14 * this.this$0.smashButton.getHeight() / 95);
              localLayoutParams11.width = (120 * this.this$0.smashButton.getWidth() / 178);
              localLayoutParams11.height = (40 * this.this$0.smashButton.getHeight() / 95);
              localTextView5.setLayoutParams(localLayoutParams11);
              TextViewResizer.resizeTextView(localTextView5, 174 * localLayoutParams11.width / 180, 47 * localLayoutParams11.height / 60);
              TextView localTextView6 = (TextView)this.this$0.findViewById(2131230766);
              RelativeLayout.LayoutParams localLayoutParams12 = (RelativeLayout.LayoutParams)localTextView6.getLayoutParams();
              localLayoutParams12.rightMargin = (26 * this.this$0.smashButton.getWidth() / 178);
              localLayoutParams12.bottomMargin = (10 * this.this$0.smashButton.getHeight() / 95);
              localLayoutParams12.width = (105 * this.this$0.smashButton.getWidth() / 178);
              localLayoutParams12.height = (55 * this.this$0.smashButton.getHeight() / 95);
              localTextView6.setLayoutParams(localLayoutParams12);
              TextViewResizer.resizeTextView(localTextView6, 142 * localLayoutParams12.width / 158, 58 * localLayoutParams12.height / 83);
              RelativeLayout.LayoutParams localLayoutParams13 = (RelativeLayout.LayoutParams)this.this$0.gamesButton.getLayoutParams();
              localLayoutParams13.width = (i * j * 25 / 100 / j);
              localLayoutParams13.height = (93 * localLayoutParams13.width / 144);
              this.this$0.gamesButton.setLayoutParams(localLayoutParams13);
              Menu.access$4(this.this$0, Boolean.valueOf(true));
              Menu.access$5(this.this$0);
              b1 = 1;
            }
            label1828: int i5 = i2;
          }
          label1835: byte b2 = 0;
        }
      }
    });
  }

  private void createFinger()
  {
    if (getSharedPreferences("AppData", 0).getInt("NumberOfPlayedGames", 0) > 0);
    while (true)
    {
      return;
      int i = this.mContext.getResources().getDisplayMetrics().heightPixels;
      int j = this.mContext.getResources().getDisplayMetrics().widthPixels;
      RelativeLayout.LayoutParams localLayoutParams1 = (RelativeLayout.LayoutParams)this.playButton.getLayoutParams();
      ImageView localImageView = new ImageView(this);
      Bitmap localBitmap = BitmapFactory.decodeResource(getResources(), 2130837588);
      Matrix localMatrix = new Matrix();
      float f = 90.0F - (float)Math.toDegrees(Math.atan((i / 2F - localLayoutParams1.topMargin + this.playButton.getHeight() / 2F) / (j / 2F - localLayoutParams1.rightMargin + this.playButton.getWidth() / 2F)));
      localMatrix.postRotate(f);
      localImageView.setImageBitmap(Bitmap.createBitmap(localBitmap, 0, 0, localBitmap.getWidth(), localBitmap.getHeight(), localMatrix, true));
      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams2.leftMargin = (j - localLayoutParams1.rightMargin + this.playButton.getWidth() / 2 - (int)(1.3999999999999999D * localBitmap.getWidth()));
      localLayoutParams2.topMargin = (localLayoutParams1.topMargin + this.playButton.getHeight() / 2);
      localImageView.setLayoutParams(localLayoutParams2);
      ((RelativeLayout)findViewById(2131230720)).addView(localImageView);
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
          SharedPreferences.Editor localEditor = this.this$0.getSharedPreferences("AntSmasherShop", 0).edit();
          localEditor.putBoolean("InitialProductAlreadyOffered", true);
          localEditor.commit();
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
          SharedPreferences.Editor localEditor = this.this$0.getSharedPreferences("AntSmasherShop", 0).edit();
          localEditor.putBoolean("InitialProductAlreadyOffered", true);
          localEditor.commit();
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
          SharedPreferences.Editor localEditor1 = this.this$0.getSharedPreferences("AntSmasherShop", 0).edit();
          localEditor1.putBoolean("InitialProductAlreadyOffered", true);
          localEditor1.commit();
          SharedPreferences.Editor localEditor2 = this.this$0.getSharedPreferences("AppData", 0).edit();
          localEditor2.putInt("State", 11);
          localEditor2.putInt("gameOverProduct", Menu.access$2(this.this$0));
          localEditor2.commit();
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
        GoogleAnalyticsTracker.getInstance().trackEvent("Activity", "Activity_Menu_Enter", "", -1);
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

  private void startGame(int paramInt)
  {
    if (settings.getBoolean("Vibration", false))
      ((Vibrator)getSystemService("vibrator")).vibrate(1000);
    editor.putInt("State", 4);
    editor.putInt("GameMode", paramInt);
    editor.commit();
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
    finish();
  }

  public void onCreate(Bundle paramBundle)
  {
    byte b;
    Matrix localMatrix;
    Object localObject;
    int i;
    super.onCreate(paramBundle);
    setContentView(2130903049);
    if (!(InitialView.menuHasBeenJustLoaded))
    {
      MetaTags localMetaTags1 = new MetaTags(1);
      MetaTagListener[] arrayOfMetaTagListener1 = new MetaTagListener[1];
      arrayOfMetaTagListener1[0] = this;
      localMetaTags1.execute(arrayOfMetaTagListener1);
      MetaTags localMetaTags2 = new MetaTags(4);
      MetaTagListener[] arrayOfMetaTagListener2 = new MetaTagListener[1];
      arrayOfMetaTagListener2[0] = this;
      localMetaTags2.execute(arrayOfMetaTagListener2);
    }
    settings = getSharedPreferences("AppData", 0);
    editor = settings.edit();
    this.mContext = getApplicationContext();
    this.mActivity = this;
    setRequestedOrientation(1);
    setVolumeControlStream(3);
    overridePendingTransition(2130968576, 2130968577);
    editor.putInt("State", 0);
    editor.commit();
    this.timerControl = true;
    this.logo = ((ImageView)findViewById(2131230742));
    this.tapJoyExplanation = ((ImageView)findViewById(2131230774));
    this.playButton = ((Button)findViewById(2131230738));
    this.scoresButton = ((Button)findViewById(2131230767));
    this.optionsButton = ((Button)findViewById(2131230771));
    this.shopButton = ((Button)findViewById(2131230769));
    this.smashButton = ((Button)findViewById(2131230763));
    this.gamesButton = ((Button)findViewById(2131230773));
    this.playButton.setVisibility(4);
    this.scoresButton.setVisibility(4);
    this.optionsButton.setVisibility(4);
    this.shopButton.setVisibility(4);
    this.smashButton.setVisibility(4);
    this.gamesButton.setVisibility(4);
    adjustLayout();
    SharedPreferences localSharedPreferences = getSharedPreferences("AntSmasherShop", 0);
    if ((localSharedPreferences.getBoolean("NoAds", false)) && (localSharedPreferences.getBoolean("KidsMode", false)) && (localSharedPreferences.getBoolean("FunMode", false)) && (localSharedPreferences.getBoolean("Protection", false)) && (localSharedPreferences.getInt("MaxLifes", 3) == 5))
    {
      b = 1;
      if (b != 0)
        this.shopButton.setVisibility(4);
      this.mContext = getApplicationContext();
      this.tapJoyExplanation.setVisibility(4);
      this.playButton.setOnClickListener(new View.OnClickListener(this)
      {
        public void onClick()
        {
          this.this$0.isChangingScreen = true;
          Menu.editor.putInt("State", 12);
          Menu.editor.commit();
          InitialView.playButtonSound("In", Menu.access$0(this.this$0));
        }
      });
      this.gamesButton.setOnClickListener(new View.OnClickListener(this)
      {
        public void onClick()
        {
          RevMobAds.showPopup(Menu.access$1(this.this$0), Constants.BCFAdsID);
          InitialView.playButtonSound("In", Menu.access$0(this.this$0));
        }
      });
      this.smashButton.setOnClickListener(new View.OnClickListener(this)
      {
        public void onClick()
        {
          this.this$0.isChangingScreen = true;
          try
          {
            GoogleAnalyticsTracker.getInstance().trackEvent("Activity", "Activity_FriendSmasher_Enter", "", -1);
            Util.setContext(Menu.access$0(this.this$0));
            Intent localIntent = new Intent(Menu.access$0(this.this$0), ImageCropper.class);
            this.this$0.startActivityForResult(localIntent, 10);
            InitialView.playButtonSound("In", Menu.access$0(this.this$0));
            return;
          }
          catch (Exception localException)
          {
            while (true)
              localException.printStackTrace();
          }
        }
      });
      this.shopButton.setOnClickListener(new View.OnClickListener(this)
      {
        public void onClick()
        {
          this.this$0.isChangingScreen = true;
          Menu.editor.putInt("State", 11);
          Menu.editor.commit();
          InitialView.playButtonSound("In", Menu.access$0(this.this$0));
        }
      });
      this.scoresButton.setOnClickListener(new View.OnClickListener(this)
      {
        public void onClick()
        {
          this.this$0.isChangingScreen = true;
          Menu.editor.putInt("State", 8);
          Menu.editor.commit();
          InitialView.playButtonSound("In", Menu.access$0(this.this$0));
        }
      });
      this.optionsButton.setOnClickListener(new View.OnClickListener(this)
      {
        public void onClick()
        {
          this.this$0.isChangingScreen = true;
          Menu.editor.putInt("State", 9);
          Menu.editor.commit();
          InitialView.playButtonSound("In", Menu.access$0(this.this$0));
        }
      });
      if (initialAntFrames == null)
      {
        initialAntFrames = new Bitmap[5];
        i = 0;
        if (i < 5)
          break label1233;
      }
      this.initialAntAngle = (90 * InitialView.rand.nextInt(4));
      float f = getApplication().getApplicationContext().getResources().getDisplayMetrics().density;
      this.layoutView = ((RelativeLayout)findViewById(2131230720));
      this.layoutView.setOnTouchListener(new View.OnTouchListener(this)
      {
        public boolean onTouch(, MotionEvent paramMotionEvent)
        {
          if (((int)paramMotionEvent.getRawX() > this.this$0.initialAnt.getLeft()) && ((int)paramMotionEvent.getRawX() < this.this$0.initialAnt.getRight()) && ((int)paramMotionEvent.getRawY() > this.this$0.initialAnt.getTop()) && ((int)paramMotionEvent.getRawY() < this.this$0.initialAnt.getBottom()) && (this.this$0.initialAntControl))
          {
            Matrix localMatrix = new Matrix();
            localMatrix.postRotate(this.this$0.initialAntAngle);
            this.this$0.initialAnt.setBackgroundDrawable(new BitmapDrawable(Bitmap.createBitmap(Menu.initialAntFrames[4], 0, 0, Menu.initialAntFrames[4].getWidth(), Menu.initialAntFrames[4].getHeight(), localMatrix, true)));
            MediaPlayer localMediaPlayer = MediaPlayer.create(Menu.access$0(this.this$0), 2131034115);
            localMediaPlayer.setAudioStreamType(3);
            if (Menu.settings.getBoolean("Sound", true))
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
                  Menu.7.access$0(this.this$1).setViewPlace(Menu.7.access$0(this.this$1).initialAnt, i * InitialView.rand.nextInt(Menu.7.access$0(this.this$1).layoutView.getWidth() / 2), -1 * i * InitialView.rand.nextInt(Menu.7.access$0(this.this$1).layoutView.getHeight() / 2));
                  Menu.7.access$0(this.this$1).initialAntControl = true;
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
      localMatrix = new Matrix();
      localMatrix.postRotate(180.0F);
    }
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
      this.playButton.bringToFront();
      this.shopButton.bringToFront();
      this.smashButton.bringToFront();
      this.scoresButton.bringToFront();
      this.optionsButton.bringToFront();
      this.gamesButton.bringToFront();
      label1233: localObject = Typeface.DEFAULT;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      try
      {
        while (true)
        {
          while (true)
          {
            Typeface localTypeface = Typeface.createFromAsset(getAssets(), "fonts/House-A-Rama-League-Night.otf");
            localObject = localTypeface;
            String str = this.mContext.getResources().getConfiguration().locale.getLanguage();
            Log.i("AS", "language: " + str);
            if ((!(str.equals("iw"))) && (!(str.equals("zh"))) && (!(str.equals("ja"))))
              ((TextView)findViewById(2131230762)).setTypeface((Typeface)localObject);
            if ((str.equals("iw")) || (str.equals("zh")) || (str.equals("ja")) || (str.equals("fr")) || (str.equals("pt")) || (str.equals("ru")))
            {
              StateListDrawable localStateListDrawable = new StateListDrawable();
              localStateListDrawable.addState(new int[0], getResources().getDrawable(2130837625));
              this.playButton.setBackgroundDrawable(localStateListDrawable);
            }
            findViewById(2131230762).bringToFront();
            if (str.equals("en"))
              findViewById(2131230762).setVisibility(4);
            findViewById(2131230768).setVisibility(4);
            findViewById(2131230772).setVisibility(4);
            findViewById(2131230770).setVisibility(4);
            findViewById(2131230765).setVisibility(4);
            findViewById(2131230766).setVisibility(4);
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
                  paramView.setBackgroundDrawable(new BitmapDrawable(Bitmap.createBitmap(Menu.initialAntFrames[4], 0, 0, Menu.initialAntFrames[4].getWidth(), Menu.initialAntFrames[4].getHeight(), localMatrix, true)));
                  MediaPlayer localMediaPlayer = MediaPlayer.create(Menu.access$0(this.this$0), 2131034115);
                  localMediaPlayer.setAudioStreamType(3);
                  if (Menu.settings.getBoolean("Sound", true))
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
                        Menu.9.access$0(this.this$1).setViewPlace(Menu.9.access$0(this.this$1).initialAnt, i * InitialView.rand.nextInt(Menu.9.access$0(this.this$1).layoutView.getWidth() / 2), -1 * i * InitialView.rand.nextInt(Menu.9.access$0(this.this$1).layoutView.getHeight() / 2));
                        Menu.9.access$0(this.this$1).initialAntControl = true;
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
            CookieSyncManager.createInstance(this.mContext);
            CookieManager.getInstance().removeAllCookie();
            update();
            return;
            b = 0;
          }
          initialAntFrames[i] = BitmapFactory.decodeResource(this.mContext.getResources(), 2130837515 + i);
          ++i;
        }
        localIllegalArgumentException = localIllegalArgumentException;
        localIllegalArgumentException.printStackTrace();
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    byte b = 1;
    if (paramInt == 82)
    {
      Calendar localCalendar1 = Calendar.getInstance();
      Calendar localCalendar2 = Calendar.getInstance();
      localCalendar1.set(2012, 6, b);
      if (localCalendar2.after(localCalendar1))
        startActivityForResult(new Intent(this, ContentHandler.class), 0);
    }
    while (true)
    {
      return b;
      boolean bool = super.onKeyDown(paramInt, paramKeyEvent);
    }
  }

  protected void onPause()
  {
    InitialView.menuHasBeenJustLoaded = true;
    if ((this.mustKillSound) && (InitialView.backgroundMp != null) && (!(InitialView.backgroundMpErrored.booleanValue())) && (InitialView.backgroundMp.isPlaying()))
      InitialView.backgroundMp.stop();
    if (!(this.isChangingScreen))
    {
      if ((InitialView.backgroundMp != null) && (!(InitialView.backgroundMpErrored.booleanValue())) && (InitialView.backgroundMp.isPlaying()))
        InitialView.backgroundMp.stop();
      Log.i("AS", "Going to quit app");
      SharedPreferences.Editor localEditor = getSharedPreferences("AntSmasherShop", 0).edit();
      localEditor.putBoolean("InitialProductAlreadyOffered", false);
      localEditor.commit();
      this.viewStatus = false;
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
          label142: super.onPause();
          return;
          Log.i("AS", "Going to change screen");
          this.isChangingScreen = false;
        }
        localException1 = localException1;
        localException1.printStackTrace();
      }
      catch (Exception localException2)
      {
        break label142:
      }
    }
  }

  protected void onResume()
  {
    if (InitialView.mainInstance == null)
      finish();
    while (true)
    {
      super.onResume();
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
    BcfgLogger.SendInfoIfNecessary(this.mContext);
    AdsUtils.configureBanner(this, this.layoutView, null);
    super.onStart();
  }

  public void receivedYesOnMeta(int paramInt)
  {
    if (paramInt == 1);
    try
    {
      showStoreItem();
      do
        label9: return;
      while (paramInt != 4);
      RevMobAds.showPopup(this, Constants.BCFAdsID);
    }
    catch (Throwable localThrowable)
    {
      break label9:
    }
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

  public void update()
  {
    Handler localHandler = new Handler();
    localHandler.postDelayed(new Runnable(this, localHandler)
    {
      public void run()
      {
        if ((this.this$0.getSharedPreferences("AppData", 0).getInt("State", -1) == 0) && (this.this$0.viewStatus))
          this.this$0.updateInitAnt();
        if (this.this$0.timerControl)
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