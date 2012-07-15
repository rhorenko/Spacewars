package com.bestcoolfungames.antsmasher;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import java.util.Date;

public class TutorialActivity extends Activity
  implements View.OnClickListener
{
  public static final int BAIL_OUT = -1;
  public static final int EXPLAIN_LIFES = 0;
  public static final int EXPLAIN_POINTS = 1;
  public static final int SMASH_ANTS = 2;
  private ImageView ant0;
  private Handler ant0AnimHandler;
  private TranslateAnimation ant0Animation;
  private int[] ant0Anims;
  private int ant0currentAnim = 0;
  private Boolean ant0stopAnim = Boolean.valueOf(false);
  private ImageView ant1;
  private Handler ant1AnimHandler;
  private TranslateAnimation ant1Animation;
  private int[] ant1Anims;
  private int ant1currentAnim = 0;
  private Boolean ant1stopAnim = Boolean.valueOf(false);
  private int deviceHeight;
  private int deviceWidth;
  ImageView finger;
  private Boolean isSound;
  public RelativeLayout layoutView;
  ImageView[] lifes;
  private Context mContext;
  private long miliCounter;
  ImageView number;
  Drawable numberFrame;
  int state;
  ImageView text;

  private void smashAntsTututorial()
  {
    this.miliCounter = new Date().getTime();
    this.ant0 = new ImageView(this);
    Bitmap localBitmap1 = BitmapFactory.decodeResource(getResources(), this.ant0Anims[0]);
    Matrix localMatrix1 = new Matrix();
    localMatrix1.postRotate(180.0F);
    Bitmap localBitmap2 = Bitmap.createBitmap(localBitmap1, 0, 0, localBitmap1.getWidth(), localBitmap1.getHeight(), localMatrix1, true);
    this.ant0.setImageBitmap(localBitmap2);
    this.layoutView.addView(this.ant0);
    RelativeLayout.LayoutParams localLayoutParams1 = (RelativeLayout.LayoutParams)this.ant0.getLayoutParams();
    localLayoutParams1.setMargins(-80 + this.deviceWidth / 2 - localBitmap1.getWidth() / 2, this.deviceHeight / 2 - localBitmap1.getWidth(), 0, 0);
    this.ant0.setLayoutParams(localLayoutParams1);
    this.ant0Animation = new TranslateAnimation(0F, 0F, -this.deviceHeight / 2, 0F);
    this.ant0Animation.setDuration(3000);
    this.ant0Animation.setInterpolator(new LinearInterpolator());
    this.ant0Animation.setFillAfter(true);
    this.ant0.setAnimation(this.ant0Animation);
    this.ant0AnimHandler = new Handler();
    1 local1 = new Runnable(this)
    {
      public void run()
      {
        if (TutorialActivity.access$0(this.this$0).booleanValue());
        while (true)
        {
          return;
          Resources localResources = this.this$0.getResources();
          int[] arrayOfInt = TutorialActivity.access$1(this.this$0);
          TutorialActivity localTutorialActivity = this.this$0;
          int i = 1 + TutorialActivity.access$2(localTutorialActivity);
          TutorialActivity.access$3(localTutorialActivity, i);
          Bitmap localBitmap1 = BitmapFactory.decodeResource(localResources, arrayOfInt[(i % TutorialActivity.access$1(this.this$0).length)]);
          Matrix localMatrix = new Matrix();
          localMatrix.postRotate(180.0F);
          Bitmap localBitmap2 = Bitmap.createBitmap(localBitmap1, 0, 0, localBitmap1.getWidth(), localBitmap1.getHeight(), localMatrix, true);
          TutorialActivity.access$4(this.this$0).setImageBitmap(localBitmap2);
          TutorialActivity.access$5(this.this$0).postDelayed(this, 50);
        }
      }
    };
    this.ant0AnimHandler.postDelayed(local1, 50);
    this.ant0Animation.setAnimationListener(new Animation.AnimationListener(this)
    {
      public void onAnimationEnd()
      {
        TutorialActivity.access$6(this.this$0, Boolean.valueOf(true));
        Bitmap localBitmap1 = BitmapFactory.decodeResource(this.this$0.getResources(), 2130837514);
        Matrix localMatrix = new Matrix();
        localMatrix.postRotate(180.0F);
        Bitmap localBitmap2 = Bitmap.createBitmap(localBitmap1, 0, 0, localBitmap1.getWidth(), localBitmap1.getHeight(), localMatrix, true);
        TutorialActivity.access$4(this.this$0).setImageBitmap(localBitmap2);
        MediaPlayer localMediaPlayer = MediaPlayer.create(TutorialActivity.access$7(this.this$0), 2131034114);
        if (localMediaPlayer != null)
        {
          localMediaPlayer.setAudioStreamType(3);
          if ((TutorialActivity.access$8(this.this$0).booleanValue()) && (localMediaPlayer != null))
          {
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
          }
        }
        this.this$0.numberFrame = this.this$0.getApplicationContext().getResources().getDrawable(2130837628);
        this.this$0.number.setBackgroundDrawable(this.this$0.numberFrame);
      }

      public void onAnimationRepeat()
      {
      }

      public void onAnimationStart()
      {
      }
    });
    this.ant0Animation.start();
    this.ant1 = new ImageView(this);
    Bitmap localBitmap3 = BitmapFactory.decodeResource(getResources(), this.ant1Anims[0]);
    Matrix localMatrix2 = new Matrix();
    localMatrix2.postRotate(180.0F);
    Bitmap localBitmap4 = Bitmap.createBitmap(localBitmap3, 0, 0, localBitmap3.getWidth(), localBitmap3.getHeight(), localMatrix2, true);
    this.ant1.setImageBitmap(localBitmap4);
    this.layoutView.addView(this.ant1);
    RelativeLayout.LayoutParams localLayoutParams2 = (RelativeLayout.LayoutParams)this.ant1.getLayoutParams();
    localLayoutParams2.setMargins(80 + this.deviceWidth / 2 - localBitmap3.getWidth() / 2, this.deviceHeight / 2 - localBitmap3.getWidth(), 0, 0);
    this.ant1.setLayoutParams(localLayoutParams2);
    this.ant1Animation = new TranslateAnimation(0F, 0F, -this.deviceHeight / 2, 0F);
    this.ant1Animation.setDuration(3000);
    this.ant1Animation.setStartOffset(3000);
    this.ant1Animation.setFillAfter(true);
    this.ant1Animation.setInterpolator(new LinearInterpolator());
    this.ant1.setAnimation(this.ant1Animation);
    this.ant1AnimHandler = new Handler();
    3 local3 = new Runnable(this)
    {
      public void run()
      {
        if (TutorialActivity.access$9(this.this$0).booleanValue());
        while (true)
        {
          return;
          Resources localResources = this.this$0.getResources();
          int[] arrayOfInt = TutorialActivity.access$10(this.this$0);
          TutorialActivity localTutorialActivity = this.this$0;
          int i = 1 + TutorialActivity.access$11(localTutorialActivity);
          TutorialActivity.access$12(localTutorialActivity, i);
          Bitmap localBitmap1 = BitmapFactory.decodeResource(localResources, arrayOfInt[(i % TutorialActivity.access$10(this.this$0).length)]);
          Matrix localMatrix = new Matrix();
          localMatrix.postRotate(180.0F);
          Bitmap localBitmap2 = Bitmap.createBitmap(localBitmap1, 0, 0, localBitmap1.getWidth(), localBitmap1.getHeight(), localMatrix, true);
          TutorialActivity.access$13(this.this$0).setImageBitmap(localBitmap2);
          TutorialActivity.access$14(this.this$0).postDelayed(this, 50);
        }
      }
    };
    this.ant1AnimHandler.postDelayed(local3, 50);
    this.ant1Animation.setAnimationListener(new Animation.AnimationListener(this)
    {
      public void onAnimationEnd()
      {
        TutorialActivity.access$15(this.this$0, Boolean.valueOf(true));
        Bitmap localBitmap1 = BitmapFactory.decodeResource(this.this$0.getResources(), 2130837519);
        Matrix localMatrix = new Matrix();
        localMatrix.postRotate(180.0F);
        Bitmap localBitmap2 = Bitmap.createBitmap(localBitmap1, 0, 0, localBitmap1.getWidth(), localBitmap1.getHeight(), localMatrix, true);
        TutorialActivity.access$13(this.this$0).setImageBitmap(localBitmap2);
        MediaPlayer localMediaPlayer = MediaPlayer.create(TutorialActivity.access$7(this.this$0), 2131034115);
        if (localMediaPlayer != null)
        {
          localMediaPlayer.setAudioStreamType(3);
          if ((TutorialActivity.access$8(this.this$0).booleanValue()) && (localMediaPlayer != null))
          {
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
          }
        }
        this.this$0.numberFrame = this.this$0.getApplicationContext().getResources().getDrawable(2130837629);
        this.this$0.number.setBackgroundDrawable(this.this$0.numberFrame);
      }

      public void onAnimationRepeat()
      {
      }

      public void onAnimationStart()
      {
      }
    });
    this.ant1Animation.start();
    Bitmap localBitmap5 = BitmapFactory.decodeResource(getResources(), 2130837588);
    Matrix localMatrix3 = new Matrix();
    localMatrix3.postRotate(-30.0F);
    Bitmap localBitmap6 = Bitmap.createBitmap(localBitmap5, 0, 0, localBitmap5.getWidth(), localBitmap5.getHeight(), localMatrix3, true);
    this.finger.setImageBitmap(localBitmap6);
    RelativeLayout.LayoutParams localLayoutParams3 = (RelativeLayout.LayoutParams)this.finger.getLayoutParams();
    localLayoutParams3.setMargins(-40 + this.deviceWidth / 2, -40 + this.deviceHeight / 2, 0, 0);
    this.finger.setLayoutParams(localLayoutParams3);
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(-(int)(this.deviceWidth / 5 * Math.sin(Math.toRadians(-30.0D))), -80.0F, (int)(this.deviceHeight / 5 * Math.cos(Math.toRadians(-30.0D))), 0F);
    localTranslateAnimation.setDuration(700);
    localTranslateAnimation.setStartOffset(2300);
    localTranslateAnimation.setInterpolator(new AccelerateInterpolator());
    this.finger.setAnimation(localTranslateAnimation);
    localTranslateAnimation.setAnimationListener(new Animation.AnimationListener(this)
    {
      public void onAnimationEnd()
      {
        TranslateAnimation localTranslateAnimation = new TranslateAnimation(-80.0F, -(int)(TutorialActivity.access$16(this.this$0) / 5 * Math.sin(Math.toRadians(-30.0D))), 0F, (int)(TutorialActivity.access$17(this.this$0) / 5 * Math.cos(Math.toRadians(-30.0D))));
        localTranslateAnimation.setDuration(1000);
        localTranslateAnimation.setInterpolator(new LinearInterpolator());
        this.this$0.finger.setAnimation(localTranslateAnimation);
        localTranslateAnimation.setAnimationListener(new Animation.AnimationListener(this)
        {
          public void onAnimationEnd()
          {
            TranslateAnimation localTranslateAnimation = new TranslateAnimation(-(int)(TutorialActivity.access$16(TutorialActivity.5.access$0(this.this$1)) / 5 * Math.sin(Math.toRadians(-30.0D))), 80.0F, (int)(TutorialActivity.access$17(TutorialActivity.5.access$0(this.this$1)) / 5 * Math.cos(Math.toRadians(-30.0D))), 0F);
            localTranslateAnimation.setDuration(700);
            localTranslateAnimation.setStartOffset(1300);
            localTranslateAnimation.setFillAfter(true);
            localTranslateAnimation.setInterpolator(new AccelerateInterpolator());
            TutorialActivity.5.access$0(this.this$1).finger.setAnimation(localTranslateAnimation);
            localTranslateAnimation.setAnimationListener(new Animation.AnimationListener(this)
            {
              public void onAnimationEnd()
              {
                TranslateAnimation localTranslateAnimation = new TranslateAnimation(80.0F, -(int)(TutorialActivity.access$16(TutorialActivity.5.access$0(TutorialActivity.5.1.access$0(this.this$2))) / 5 * Math.sin(Math.toRadians(-30.0D))), 0F, (int)(TutorialActivity.access$17(TutorialActivity.5.access$0(TutorialActivity.5.1.access$0(this.this$2))) / 5 * Math.cos(Math.toRadians(-30.0D))));
                localTranslateAnimation.setDuration(1000);
                localTranslateAnimation.setInterpolator(new LinearInterpolator());
                TutorialActivity.5.access$0(TutorialActivity.5.1.access$0(this.this$2)).finger.setAnimation(localTranslateAnimation);
                localTranslateAnimation.setAnimationListener(new Animation.AnimationListener(this)
                {
                  public void onAnimationEnd()
                  {
                    SharedPreferences.Editor localEditor = TutorialActivity.5.access$0(TutorialActivity.5.1.access$0(TutorialActivity.5.1.1.access$0(this.this$3))).getSharedPreferences("AppData", 0).edit();
                    localEditor.putInt("State", 4);
                    localEditor.commit();
                  }

                  public void onAnimationRepeat()
                  {
                  }

                  public void onAnimationStart()
                  {
                  }
                });
                localTranslateAnimation.start();
              }

              public void onAnimationRepeat()
              {
              }

              public void onAnimationStart()
              {
              }
            });
            localTranslateAnimation.start();
          }

          public void onAnimationRepeat()
          {
          }

          public void onAnimationStart()
          {
          }
        });
      }

      public void onAnimationRepeat()
      {
      }

      public void onAnimationStart()
      {
      }
    });
    localTranslateAnimation.start();
    this.finger.bringToFront();
    findViewById(2131230729).bringToFront();
    findViewById(2131230736).bringToFront();
    this.number.bringToFront();
    byte b = 0;
    while (true)
    {
      if (b >= 2)
        return;
      this.lifes[b].bringToFront();
      ++b;
    }
  }

  public void onBackPressed()
  {
    onClick(null);
  }

  public void onClick(View paramView)
  {
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mContext = this;
    this.isSound = Boolean.valueOf(this.mContext.getSharedPreferences("AppData", 0).getBoolean("Sound", true));
    int[] arrayOfInt1 = new int[4];
    arrayOfInt1[0] = 2130837510;
    arrayOfInt1[1] = 2130837511;
    arrayOfInt1[2] = 2130837512;
    arrayOfInt1[3] = 2130837513;
    this.ant0Anims = arrayOfInt1;
    int[] arrayOfInt2 = new int[4];
    arrayOfInt2[0] = 2130837515;
    arrayOfInt2[1] = 2130837516;
    arrayOfInt2[2] = 2130837517;
    arrayOfInt2[3] = 2130837518;
    this.ant1Anims = arrayOfInt2;
    this.state = 2;
    setVolumeControlStream(3);
    setRequestedOrientation(1);
    setContentView(2130903054);
    this.layoutView = ((RelativeLayout)findViewById(2131230822));
    this.layoutView.setOnClickListener(this);
    float f = getApplicationContext().getResources().getDisplayMetrics().density;
    this.lifes = new ImageView[2];
    this.numberFrame = getApplicationContext().getResources().getDrawable(2130837627);
    this.number = new ImageView(getApplicationContext(), null);
    this.number.bringToFront();
    this.finger = new ImageView(this);
    this.layoutView.addView(this.finger);
    this.deviceHeight = getResources().getDisplayMetrics().heightPixels;
    this.deviceWidth = getResources().getDisplayMetrics().widthPixels;
    this.text = new ImageView(this);
    this.layoutView.addView(this.text);
    RelativeLayout.LayoutParams localLayoutParams1 = (RelativeLayout.LayoutParams)this.text.getLayoutParams();
    localLayoutParams1.setMargins(-45 + getResources().getDisplayMetrics().widthPixels / 4, getResources().getDisplayMetrics().heightPixels / 2, 0, 0);
    this.text.setLayoutParams(localLayoutParams1);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams((int)(35.0F * f), (int)(35.0F * f));
    localLayoutParams2.setMargins(1, 0, 0, 0);
    this.number.setLayoutParams(localLayoutParams2);
    this.layoutView.addView(this.number);
    this.number.setBackgroundDrawable(this.numberFrame);
    int i = 0;
    while (true)
    {
      if (i >= 2)
      {
        smashAntsTututorial();
        return;
      }
      this.lifes[i] = ((ImageView)findViewById(3 + 2131230731 + i));
      this.lifes[i].setBackgroundResource(2130837664);
      this.lifes[i].getBackground().mutate().setAlpha(255);
      this.lifes[i].bringToFront();
      ++i;
    }
  }
}