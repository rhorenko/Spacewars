package com.bestcoolfungames.antsmasher;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import com.bestcoolfungames.util.Util;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.Random;

public abstract class GameSurface
  implements LevelInterface
{
  public static final int NUMBER_OF_INSECT_FRAMES = 5;
  public static final int TYPES_OF_ANTS = 9;
  private static Bitmap[][] antFrames;
  private static Bitmap[][] antFramesFriendSmasher;
  private static Bitmap[][] antFramesOriginal;
  public static int antHeight;
  public static int antWidth;
  private static Bitmap[] beeFrames;
  private static Bitmap bloodFrame;
  static Bitmap[] bonusFrames;
  static Bitmap dyingbee;
  private static Bitmap mBackgroundImage;
  static SurfaceBitmap ouchBmp;
  public static final Random rand = new Random();
  public static float ratio;
  public static float ratioX;
  public static float ratioY;
  Bitmap.Config CONF;
  float accel;
  int alphaDegree;
  public int[][] antAngle;
  int antCounter;
  public int[][] antDirection;
  public int[][] antLife;
  public int[][] antOrder;
  int[][] antOrdinator;
  final int antSizeX;
  final int antSizeY;
  int antSmashedCounter;
  SurfaceBitmap[][] ants;
  public int[] beeAngle;
  public int[] beeDirection;
  boolean[] beeInScreen;
  public int[] beeOrder;
  int[] beeOrdinator;
  final int beeSizeX;
  final int beeSizeY;
  SurfaceBitmap[] bees;
  int bigAntAlphaControl;
  SurfaceBitmap bonus;
  int bonusAngle;
  int bonusDir;
  int bonusType;
  int counter;
  SharedPreferences.Editor editor;
  int gameMode;
  boolean[][] inScreen;
  boolean isAlphaing;
  public boolean isBonus;
  boolean isCircleing;
  boolean isKillingBee;
  public boolean isSound;
  int killingBeeCounter;
  int killingBeeIndex;
  int mCanvasHeight;
  int mCanvasWidth;
  Context mContext;
  int maxLifes;
  public MediaPlayer mp;
  public int[] numberOfAntsWithType;
  public int numberOfBees;
  public int numberOfObjects;
  SurfaceBitmap[] numbers;
  public int objectPadding;
  int ordinatorCounter;
  public int paceX;
  public int paceY;
  boolean passAheadTouchEvents;
  boolean passed;
  boolean paused;
  public int proceed;
  boolean protection;
  public float scale;
  SharedPreferences settings;
  boolean[][] smashed;
  private long startDiscount;
  private GameSurfaceView surfaceView;
  GameSurfaceThread thread;
  int totalOfAnts;
  public final float touchMargin = 0.23999999463558197F;
  boolean youch;

  static
  {
    ouchBmp = null;
    bloodFrame = null;
  }

  public GameSurface()
  {
    this.antSizeX = 50;
    this.antSizeY = 70;
    this.beeSizeX = 60;
    this.beeSizeY = 80;
    this.CONF = Bitmap.Config.ARGB_4444;
    this.startDiscount = 0L;
    this.counter = 0;
  }

  private Resources getResources()
  {
    return this.surfaceView.getResources();
  }

  public static Bitmap mergeBitmaps(Bitmap paramBitmap1, Bitmap paramBitmap2)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap1);
    new Canvas(localBitmap).drawBitmap(paramBitmap2, 0F, 0F, null);
    return localBitmap;
  }

  public static void restoreOriginalAnts()
  {
    antFrames = antFramesOriginal;
  }

  public static void setFixedBitmap(Context paramContext, Bitmap paramBitmap)
  {
    Log.d("AntSmasher", "we not set the new theBmps");
    int i = (int)(1.4624999761581421F * 50.0F * 1.1000000238418579F);
    int j = (int)(1.25F * 70.0F * 1.1000000238418579F);
    Bitmap localBitmap1 = Bitmap.createScaledBitmap(paramBitmap, i, j, true);
    System.gc();
    Matrix localMatrix = new Matrix();
    localMatrix.postRotate(180.0F);
    Bitmap localBitmap2 = Bitmap.createBitmap(localBitmap1, 0, 0, i, j, localMatrix, true);
    System.gc();
    if (bloodFrame == null)
    {
      bloodFrame = BitmapFactory.decodeResource(paramContext.getResources(), 2130837563);
      if (bloodFrame == null)
        Util.Log("Error: bloodFrame is null");
    }
    Bitmap localBitmap3 = mergeBitmaps(localBitmap2, bloodFrame);
    System.gc();
    if (antFramesFriendSmasher == null)
    {
      int[] arrayOfInt = new int[2];
      arrayOfInt[0] = 9;
      arrayOfInt[1] = 5;
      antFramesFriendSmasher = (Bitmap[][])Array.newInstance(Bitmap.class, arrayOfInt);
    }
    byte b1 = 0;
    if (b1 >= 9)
    {
      antFrames = antFramesFriendSmasher;
      return;
    }
    byte b2 = 0;
    while (true)
    {
      while (b2 >= 4)
      {
        antFramesFriendSmasher[b1][4] = localBitmap3;
        ++b1;
      }
      antFramesFriendSmasher[b1][b2] = localBitmap2;
      ++b2;
    }
  }

  public float acceleration()
  {
    return this.accel;
  }

  public void acceleration(float paramFloat)
  {
    this.editor.putFloat("Acceleration", paramFloat);
    this.editor.commit();
  }

  public void beeWasSmashed(int paramInt)
  {
    if (this.isCircleing);
    while (true)
    {
      while (true)
      {
        label238: 
        do
          while (true)
          {
            while (true)
            {
              return;
              int i = 2131034127 + rand.nextInt(1);
              this.mp = MediaPlayer.create(this.mContext, i);
              this.mp.setAudioStreamType(3);
              if (this.settings.getBoolean("Sound", true))
                this.mp.start();
              this.mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(this)
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
              this.killingBeeCounter = 0;
              if (ouchBmp == null)
                ouchBmp = new SurfaceBitmap(BitmapFactory.decodeResource(getResources(), 2130837577));
              ouchBmp.setPosition(this.bees[paramInt].getLeft(), -35 + this.bees[paramInt].getTop());
              if (this.gameMode != 0)
                break label238;
              if (!(this.protection))
                break;
              this.isCircleing = true;
              this.protection = false;
              this.editor.putBoolean("Prot", false);
              this.editor.commit();
            }
            this.paused = true;
            this.killingBeeIndex = paramInt;
            this.bees[paramInt].setBitmap(BitmapFactory.decodeResource(getResources(), 2130837556));
            this.beeAngle[paramInt] = 180;
            killingBee(paramInt);
            this.isKillingBee = true;
          }
        while (this.gameMode == 3);
        int j = this.settings.getInt("Lifes", 0);
        if (j > 1)
        {
          this.isCircleing = true;
          if (GameActivity.lifeFlag == -1)
            setLifes(-2);
          while (true)
          {
            while (true)
            {
              this.editor.putInt("Lifes", j + -1);
              this.editor.commit();
            }
            setLifes(-1);
          }
        }
        if (!(this.protection))
          break;
        this.isCircleing = true;
        this.protection = false;
        this.editor.putBoolean("Prot", false);
        this.editor.commit();
      }
      this.paused = true;
      this.killingBeeIndex = paramInt;
      this.bees[paramInt].setBitmap(BitmapFactory.decodeResource(getResources(), 2130837556));
      this.beeAngle[paramInt] = 180;
      killingBee(paramInt);
      this.isKillingBee = true;
    }
  }

  void bonusWasSmashed()
  {
    this.isBonus = false;
    switch (this.bonusType)
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
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
              while (true)
              {
                this.editor.putBoolean("Bonus", false);
                this.editor.commit();
                this.antSmashedCounter = (1 + this.antSmashedCounter);
                int i = 2131034119;
                if (this.bonusType == 0)
                  i = 2131034123;
                this.mp = MediaPlayer.create(this.mContext, i);
                this.mp.setAudioStreamType(3);
                if (this.isSound)
                  this.mp.start();
                this.mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(this)
                {
                  public void onCompletion()
                  {
                    if ((paramMediaPlayer != null) && (paramMediaPlayer.isPlaying()))
                      paramMediaPlayer.stop();
                  }
                });
                return;
                this.editor.putInt("Lifes", 1 + this.settings.getInt("Lifes", 0));
                this.editor.commit();
                setLifes(1);
              }
              this.editor.putInt("Points", 5 + this.settings.getInt("Points", 0));
              this.editor.commit();
            }
            this.editor.putInt("Points", 10 + this.settings.getInt("Points", 0));
            this.editor.commit();
          }
          this.editor.putInt("Points", 25 + this.settings.getInt("Points", 0));
          this.editor.commit();
        }
        this.editor.putInt("Points", 50 + this.settings.getInt("Points", 0));
        this.editor.commit();
      }
      this.editor.putInt("Points", 100 + this.settings.getInt("Points", 0));
      this.editor.commit();
    }
  }

  void doEndGame()
  {
    if (this.settings.getBoolean("Vibration", true))
      ((Vibrator)this.mContext.getSystemService("vibrator")).vibrate(200);
    GameActivity.surfaceAction = 3;
    SharedPreferences.Editor localEditor = this.mContext.getSharedPreferences("AppData", 0).edit();
    localEditor.putBoolean("ShouldContinue", false);
    localEditor.commit();
    this.passed = true;
  }

  public void doPause()
  {
    this.thread.doPause();
  }

  public void doResume()
  {
    this.thread.doResume();
  }

  public void doStop()
  {
    this.passAheadTouchEvents = false;
    this.thread.doStop();
  }

  public View getSurfaceView()
  {
    return this.surfaceView;
  }

  public GameSurfaceThread getThread()
  {
    return this.thread;
  }

  void killingBee(int paramInt)
  {
    this.killingBeeCounter = (1 + this.killingBeeCounter);
    if (this.beeAngle[paramInt] % 360 > 0)
    {
      int[] arrayOfInt = this.beeAngle;
      arrayOfInt[paramInt] = (4 + arrayOfInt[paramInt]);
    }
    if (this.killingBeeCounter > 40)
    {
      this.isKillingBee = false;
      doEndGame();
    }
  }

  void killingBeeDraw(Canvas paramCanvas)
  {
    ouchBmp.draw(paramCanvas, 0);
  }

  public void refreshScreen()
  {
    this.thread.refreshScreen();
  }

  public void resetVars()
  {
    this.thread.resetVars();
  }

  public void setLayoutParams(RelativeLayout.LayoutParams paramLayoutParams)
  {
    this.surfaceView.setLayoutParams(paramLayoutParams);
  }

  public void setLifes(int paramInt)
  {
    GameActivity.lifeFlag = paramInt;
  }

  public void setup(Context paramContext, GameSurfaceView paramGameSurfaceView)
  {
    this.surfaceView = paramGameSurfaceView;
    this.surfaceView.setGameSurface(this);
    this.mContext = paramContext;
    SurfaceHolder localSurfaceHolder = paramGameSurfaceView.getHolder();
    localSurfaceHolder.addCallback(paramGameSurfaceView);
    this.passAheadTouchEvents = true;
    this.thread = new GameSurfaceThread(this, localSurfaceHolder, this.mContext, new Handler(this)
    {
      public void handleMessage()
      {
      }
    });
    paramGameSurfaceView.setFocusable(true);
  }

  public void sheWasSmashed(int paramInt1, int paramInt2)
  {
    int i;
    if (this.antLife[paramInt1][paramInt2] <= 1)
    {
      this.smashed[paramInt1][paramInt2] = 1;
      if (paramInt1 != 7)
      {
        this.ants[paramInt1][paramInt2].setBitmap(antFrames[paramInt1][4]);
        this.antSmashedCounter = (1 + this.antSmashedCounter);
        if (paramInt1 != 3)
          break label219;
        this.editor.putInt("Points", 10 + this.settings.getInt("Points", 0));
        this.editor.commit();
        i = 2131034123;
        if (paramInt1 != 0)
          break label330;
        i = 2131034112 + rand.nextInt(1);
        if (paramInt1 >= 4)
          i = 2131034116;
        this.mp = MediaPlayer.create(this.mContext, i);
        if (this.mp != null)
        {
          this.mp.setAudioStreamType(3);
          if ((this.isSound) && (this.mp != null))
          {
            this.mp.start();
            this.mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(this)
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
      }
    }
    while (true)
    {
      do
      {
        do
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
                        {
                          return;
                          this.ants[paramInt1][paramInt2].setBitmap(antFrames[(paramInt1 + 1)][4]);
                        }
                        label219: if ((paramInt1 != 4) && (paramInt1 != 5))
                          break;
                        this.editor.putInt("Points", 50 + this.settings.getInt("Points", 0));
                      }
                      if (paramInt1 != 6)
                        break;
                      this.editor.putInt("Points", 100 + this.settings.getInt("Points", 0));
                    }
                    this.editor.putInt("Points", 1 + this.settings.getInt("Points", 0));
                  }
                  label330: if (paramInt1 != 1)
                    break;
                  i = 2131034114;
                }
                if (paramInt1 != 2)
                  break;
                i = 2131034115;
              }
            while (paramInt1 != 3);
            i = 2131034120;
          }
          int[] arrayOfInt = this.antLife[paramInt1];
          arrayOfInt[paramInt2] = (-1 + arrayOfInt[paramInt2]);
          this.mp = MediaPlayer.create(this.mContext, 2131034117);
        }
        while (this.mp == null);
        this.mp.setAudioStreamType(3);
        if (this.isSound)
          this.mp.start();
        this.mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(this)
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
      while (paramInt1 < 4);
      this.bigAntAlphaControl = 1;
    }
  }

  public void simpleDraw()
  {
    this.thread.simpleDraw();
  }

  public void start()
  {
  }

  class GameSurfaceThread extends Thread
  {
    Bitmap.Config CONF;
    private boolean mRun = true;
    private SurfaceHolder mSurfaceHolder;
    Paint p;
    private Boolean refreshScreenFlag = Boolean.valueOf(false);

    public GameSurfaceThread(, SurfaceHolder paramSurfaceHolder, Context paramContext, Handler paramHandler)
    {
      this.CONF = Bitmap.Config.ARGB_4444;
      paramGameSurface.maxLifes = paramContext.getSharedPreferences("AntSmasherShop", 0).getInt("MaxLifes", 3);
      GameSurface.ratioX = 1.4624999761581421F;
      GameSurface.ratioY = 1.25F;
      GameSurface.ratio = 1.1000000238418579F;
      GameSurface.antWidth = (int)(50.0F * GameSurface.ratio * GameSurface.ratioX);
      GameSurface.antHeight = (int)(70.0F * GameSurface.ratio * GameSurface.ratioY);
      paramGameSurface.isCircleing = false;
      paramGameSurface.isAlphaing = false;
      paramGameSurface.isKillingBee = false;
      paramGameSurface.bigAntAlphaControl = 0;
      paramGameSurface.killingBeeCounter = 0;
      paramGameSurface.alphaDegree = 255;
      paramGameSurface.scale = paramGameSurface.mContext.getResources().getDisplayMetrics().density;
      paramGameSurface.paused = false;
      paramGameSurface.settings = paramGameSurface.mContext.getSharedPreferences("AppData", 0);
      paramGameSurface.editor = paramGameSurface.settings.edit();
      GameActivity.lifeFlag = 0;
      paramGameSurface.isSound = paramGameSurface.settings.getBoolean("Sound", true);
      paramGameSurface.accel = paramGameSurface.settings.getFloat("Acceleration", 0F);
      paramGameSurface.protection = paramGameSurface.settings.getBoolean("Prot", false);
      paramGameSurface.gameMode = paramGameSurface.settings.getInt("GameMode", 0);
      this.mSurfaceHolder = paramSurfaceHolder;
      paramGameSurface.mContext = paramContext;
      Resources localResources = paramContext.getResources();
      if (GameSurface.access$0() == null)
        GameSurface.access$1(BitmapFactory.decodeResource(localResources, 2130837558));
      paramGameSurface.numberOfAntsWithType = new int[9];
      int[] arrayOfInt1 = new int[2];
      arrayOfInt1[0] = 9;
      arrayOfInt1[1] = 10;
      paramGameSurface.antAngle = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt1));
      int[] arrayOfInt2 = new int[2];
      arrayOfInt2[0] = 9;
      arrayOfInt2[1] = 10;
      paramGameSurface.antOrder = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt2));
      int[] arrayOfInt3 = new int[2];
      arrayOfInt3[0] = 9;
      arrayOfInt3[1] = 10;
      paramGameSurface.antDirection = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt3));
      int[] arrayOfInt4 = new int[2];
      arrayOfInt4[0] = 9;
      arrayOfInt4[1] = 10;
      paramGameSurface.inScreen = ((boolean[][])Array.newInstance(Boolean.TYPE, arrayOfInt4));
      int[] arrayOfInt5 = new int[2];
      arrayOfInt5[0] = 9;
      arrayOfInt5[1] = 10;
      paramGameSurface.ants = ((SurfaceBitmap[][])Array.newInstance(SurfaceBitmap.class, arrayOfInt5));
      paramGameSurface.bees = new SurfaceBitmap[5];
      paramGameSurface.beeInScreen = new boolean[5];
      paramGameSurface.ordinatorCounter = -1;
      int[] arrayOfInt6 = new int[2];
      arrayOfInt6[0] = 9;
      arrayOfInt6[1] = 10;
      paramGameSurface.antOrdinator = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt6));
      paramGameSurface.beeOrdinator = new int[5];
      int[] arrayOfInt7 = new int[2];
      arrayOfInt7[0] = 9;
      arrayOfInt7[1] = 10;
      paramGameSurface.smashed = ((boolean[][])Array.newInstance(Boolean.TYPE, arrayOfInt7));
      byte b1 = 0;
      if (b1 >= 5)
      {
        b2 = 0;
        if (b2 < 9)
          break label1050;
        int[] arrayOfInt8 = new int[2];
        arrayOfInt8[0] = 9;
        arrayOfInt8[1] = 10;
        paramGameSurface.antLife = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt8));
        if (GameSurface.access$2() == null)
        {
          int[] arrayOfInt9 = new int[2];
          arrayOfInt9[0] = 9;
          arrayOfInt9[1] = 5;
          GameSurface.access$3((Bitmap[][])Array.newInstance(Bitmap.class, arrayOfInt9));
          if (GameSurface.access$4() == null)
            GameSurface.access$5(GameSurface.access$2());
          Log.i("BMP LEAK", "BMP LEAK");
          b7 = 0;
          if (b7 < 9)
            break label1094;
        }
        if (GameSurface.access$6() == null)
        {
          GameSurface.access$7(new Bitmap[5]);
          Log.i("BMP LEAK", "BMP BEE LEAK");
          j = 0;
          if (j < 5)
            break label1262;
        }
        if (GameSurface.dyingbee == null)
          GameSurface.dyingbee = BitmapFactory.decodeResource(paramGameSurface.mContext.getResources(), 2130837577);
        paramGameSurface.antSmashedCounter = 0;
        paramGameSurface.antCounter = 0;
        b4 = 0;
        if (b4 < 9)
          break label1330;
        paramGameSurface.isBonus = false;
        if (GameSurface.bonusFrames == null)
        {
          GameSurface.bonusFrames = new Bitmap[6];
          GameSurface.bonusFrames[0] = BitmapFactory.decodeResource(paramGameSurface.mContext.getResources(), 2130837663);
          GameSurface.bonusFrames[1] = BitmapFactory.decodeResource(paramGameSurface.mContext.getResources(), 2130837567);
          GameSurface.bonusFrames[2] = BitmapFactory.decodeResource(paramGameSurface.mContext.getResources(), 2130837564);
          GameSurface.bonusFrames[3] = BitmapFactory.decodeResource(paramGameSurface.mContext.getResources(), 2130837566);
          GameSurface.bonusFrames[4] = BitmapFactory.decodeResource(paramGameSurface.mContext.getResources(), 2130837568);
          GameSurface.bonusFrames[5] = BitmapFactory.decodeResource(paramGameSurface.mContext.getResources(), 2130837565);
        }
        if (paramGameSurface.settings.getBoolean("Bonus", false))
        {
          paramGameSurface.isBonus = true;
          paramGameSurface.bonus = new SurfaceBitmap();
          if (GameSurface.rand.nextInt(2) != 1)
            break label1418;
          b6 = 1;
          paramGameSurface.bonusDir = b6;
          paramGameSurface.bonusAngle = 180;
          paramGameSurface.antCounter = 1;
          if ((paramGameSurface.settings.getInt("Lifes", 0) >= paramGameSurface.maxLifes) || (!(paramGameSurface.settings.getBoolean("Bonus-Life", false))))
            break label1425;
          paramGameSurface.bonusType = 0;
        }
      }
      while (true)
      {
        while (true)
        {
          label1050: label1094: label1262: int i;
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
                      paramGameSurface.bonus.setPosition(100, -300);
                      paramGameSurface.bonus.setBitmap(GameSurface.bonusFrames[paramGameSurface.bonusType]);
                      paramGameSurface.bonus.setBitmap(Bitmap.createScaledBitmap(paramGameSurface.bonus.bitmap(), (int)(1.1000000238418579F * paramGameSurface.bonus.bitmap().getWidth()), (int)(1.1000000238418579F * paramGameSurface.bonus.bitmap().getHeight()), true));
                      paramGameSurface.startPositions();
                      return;
                      paramGameSurface.beeInScreen[b1] = true;
                      ++b1;
                    }
                    byte b3 = 0;
                    while (true)
                    {
                      while (b3 >= 10)
                        ++b2;
                      paramGameSurface.inScreen[b2][b3] = 1;
                      paramGameSurface.smashed[b2][b3] = 0;
                      ++b3;
                    }
                    System.gc();
                    int k = 0;
                    while (true)
                    {
                      while (k >= 5)
                        ++b7;
                      GameSurface.access$2()[b7][k] = BitmapFactory.decodeResource(paramGameSurface.mContext.getResources(), k + 2130837505 + b7 * 5);
                      GameSurface.access$2()[b7][k] = GameSurface.access$2()[b7][k].copy(this.CONF, false);
                      if ((b7 != 4) && (b7 != 6))
                        GameSurface.access$2()[b7][k] = Bitmap.createScaledBitmap(GameSurface.access$2()[b7][k], GameSurface.antWidth, GameSurface.antHeight, true);
                      if (b7 == 6)
                        GameSurface.access$2()[b7][k] = Bitmap.createScaledBitmap(GameSurface.access$2()[b7][k], (int)(2.5F * GameSurface.antWidth), (int)(2.5F * GameSurface.antHeight), true);
                      ++k;
                    }
                    GameSurface.access$6()[j] = BitmapFactory.decodeResource(paramGameSurface.mContext.getResources(), 2130837552 + j);
                    GameSurface.access$6()[j] = GameSurface.access$6()[j].copy(this.CONF, false);
                    GameSurface.access$6()[j] = Bitmap.createScaledBitmap(GameSurface.access$6()[j], 84, 112, true);
                    ++j;
                  }
                  label1330: System.gc();
                  byte b5 = 0;
                  while (true)
                  {
                    while (b5 >= 5)
                      ++b4;
                    paramGameSurface.antLife[b4][b5] = 1;
                    if (b4 == 4)
                      paramGameSurface.antLife[b4][b5] = 3;
                    if (b4 == 6)
                      paramGameSurface.antLife[b4][b5] = 5;
                    if (b4 == 7)
                      paramGameSurface.antLife[b4][b5] = 2;
                    ++b5;
                  }
                  label1418: b6 = -1;
                }
                label1425: i = GameSurface.rand.nextInt(100);
                if (i >= 21)
                  break;
                paramGameSurface.bonusType = 1;
              }
              if (i >= 49)
                break;
              paramGameSurface.bonusType = 2;
            }
            if (i >= 72)
              break;
            paramGameSurface.bonusType = 3;
          }
          if (i >= 87)
            break;
          paramGameSurface.bonusType = 4;
        }
        paramGameSurface.bonusType = 5;
      }
    }

    public void doDraw()
    {
      byte b2;
      int j;
      int l;
      if (paramCanvas == null)
        return;
      paramCanvas.drawBitmap(GameSurface.access$0(), 0F, 0F, null);
      byte b1 = 0;
      if (b1 >= 9)
        b2 = 0;
      while (true)
      {
        while (true)
        {
          do
          {
            if (b2 < 9)
              break label643;
            l = 0;
            if (l < this.this$0.numberOfBees)
              break label1484;
            if (!(this.this$0.isBonus))
              break label457;
            if (GameSurface.rand.nextInt(10) < 2)
            {
              GameSurface localGameSurface8 = this.this$0;
              localGameSurface8.bonusDir = (-1 * localGameSurface8.bonusDir);
            }
            if (this.this$0.bonus.getLeft() > this.this$0.mCanvasWidth - this.this$0.bonus.getWidth())
              this.this$0.bonusAngle = 216;
            if (this.this$0.bonus.getLeft() < 0)
              this.this$0.bonusAngle = 144;
            if (GameSurface.rand.nextInt(10) < 2)
            {
              GameSurface localGameSurface7 = this.this$0;
              localGameSurface7.bonusDir = (-1 * localGameSurface7.bonusDir);
            }
            if (this.this$0.bonusAngle > 300)
              this.this$0.bonusAngle = 144;
            if (this.this$0.bonusAngle < 60)
              this.this$0.bonusAngle = 216;
            GameSurface localGameSurface5 = this.this$0;
            localGameSurface5.bonusAngle = (localGameSurface5.bonusAngle + 5 * this.this$0.bonusDir);
            if (!(this.this$0.paused))
              this.this$0.bonus.setPosition((int)Math.round(this.this$0.bonus.getLeft() - 5.0D * Math.sin(Math.toRadians(180 + this.this$0.bonusAngle))), this.this$0.bonus.getTop() + (int)(3.0F * this.this$0.scale * (1F + this.this$0.acceleration() / 100.0F)));
            this.this$0.bonus.draw(paramCanvas, 0);
            if (this.this$0.bonus.getTop() <= this.this$0.mCanvasHeight)
              break label457;
          }
          while (!(this.this$0.settings.getBoolean("Bonus", false)));
          GameSurface localGameSurface6 = this.this$0;
          localGameSurface6.antSmashedCounter = (1 + localGameSurface6.antSmashedCounter);
          this.this$0.editor.putBoolean("Bonus", false);
          this.this$0.editor.commit();
          this.this$0.isBonus = false;
          if ((this.this$0.antSmashedCounter >= this.this$0.antCounter) && (!(this.this$0.isAlphaing)))
          {
            label457: this.this$0.isAlphaing = true;
            Log.i("", "First step");
            GameActivity.surfaceAction = 2;
            SharedPreferences.Editor localEditor = this.this$0.mContext.getSharedPreferences("AppData", 0).edit();
            localEditor.putBoolean("ShouldContinue", false);
            localEditor.commit();
          }
          if (this.this$0.isAlphaing)
            break label1617;
          this.this$0.updatePositions();
        }
        int i = 0;
        while (true)
        {
          while (i >= this.this$0.numberOfAntsWithType[b1])
            ++b1;
          if ((this.this$0.smashed[b1][i] != 0) && (!(this.this$0.isAlphaing)))
            this.this$0.ants[b1][i].draw(paramCanvas, this.this$0.antAngle[b1][i]);
          ++i;
        }
        label643: j = 0;
        if (j < this.this$0.numberOfAntsWithType[b2])
          break;
        ++b2;
      }
      if ((this.this$0.smashed[b2][j] == 0) && (this.this$0.inScreen[b2][j] != 0))
      {
        if (!(this.this$0.paused))
        {
          if (b2 == 7)
            break label1085;
          this.this$0.ants[b2][j].setBitmap(GameSurface.access$4()[b2][(this.this$0.counter / 4 % 4)]);
        }
        if ((b2 == 4) || (b2 == 6))
          break label1178;
        this.this$0.ants[b2][j].draw(paramCanvas, this.this$0.antAngle[b2][j]);
        if ((this.this$0.ants[b2][j].getTop() > this.this$0.mCanvasHeight) && (this.this$0.mCanvasHeight != 0))
        {
          int k = this.this$0.settings.getInt("Lifes", 0);
          if ((this.this$0.protection) && (((!(this.this$0.protection)) || (k < 2))))
            break label1329;
          if ((this.this$0.gameMode != 3) || ((this.this$0.gameMode == 3) && (k > 2)))
          {
            if (GameActivity.lifeFlag != -1)
              break label1317;
            this.this$0.setLifes(-2);
            this.this$0.editor.putInt("Lifes", k - 1);
            this.this$0.editor.commit();
          }
          GameSurface localGameSurface2 = this.this$0;
          localGameSurface2.antSmashedCounter = (1 + localGameSurface2.antSmashedCounter);
          this.this$0.smashed[b2][j] = 1;
          this.this$0.inScreen[b2][j] = 0;
          this.this$0.mp = MediaPlayer.create(this.this$0.mContext, 2131034126);
          this.this$0.mp.setAudioStreamType(3);
          if (this.this$0.isSound)
            this.this$0.mp.start();
          this.this$0.mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(this)
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
          if (k <= 1)
            this.this$0.doEndGame();
        }
      }
      while (true)
      {
        while (true)
        {
          while (true)
          {
            do
            {
              while (true)
              {
                while (true)
                {
                  while (true)
                  {
                    while (true)
                      ++j;
                    label1085: if (this.this$0.antLife[b2][j] != 2)
                      break;
                    this.this$0.ants[b2][j].setBitmap(GameSurface.access$4()[b2][(this.this$0.counter / 4 % 4)]);
                  }
                  this.this$0.ants[b2][j].setBitmap(GameSurface.access$4()[(b2 + 1)][(this.this$0.counter / 4 % 4)]);
                }
                label1178: if (this.this$0.bigAntAlphaControl != 0)
                  break;
                this.this$0.ants[b2][j].draw(paramCanvas, this.this$0.antAngle[b2][j]);
              }
              this.p = new Paint();
              this.p.setAlpha(128);
              this.this$0.ants[b2][j].draw(paramCanvas, this.this$0.antAngle[b2][j], this.p);
              GameSurface localGameSurface1 = this.this$0;
              localGameSurface1.bigAntAlphaControl = (1 + localGameSurface1.bigAntAlphaControl);
            }
            while (this.this$0.bigAntAlphaControl <= 6);
            this.this$0.bigAntAlphaControl = 0;
          }
          label1317: this.this$0.setLifes(-1);
        }
        label1329: this.this$0.protection = false;
        this.this$0.editor.putBoolean("Prot", false);
        this.this$0.editor.commit();
        GameSurface localGameSurface3 = this.this$0;
        localGameSurface3.antSmashedCounter = (1 + localGameSurface3.antSmashedCounter);
        this.this$0.smashed[b2][j] = 1;
        this.this$0.inScreen[b2][j] = 0;
        this.this$0.mp = MediaPlayer.create(this.this$0.mContext, 2131034126);
        this.this$0.mp.setAudioStreamType(3);
        if (this.this$0.isSound)
          this.this$0.mp.start();
        this.this$0.mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(this)
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
      if ((this.this$0.beeInScreen[l] != 0) && (!(this.this$0.isAlphaing)))
      {
        if (!(this.this$0.paused))
          label1484: this.this$0.bees[l].setBitmap(GameSurface.access$6()[(this.this$0.counter / 4 % 4)]);
        this.this$0.bees[l].draw(paramCanvas, this.this$0.beeAngle[l]);
      }
      while (true)
      {
        do
          while (true)
            ++l;
        while (this.this$0.bees[l].bitmap() == GameSurface.access$6()[4]);
        this.this$0.bees[l].setBitmap(GameSurface.access$6()[0]);
      }
      label1617: this.p = new Paint();
      Paint localPaint = this.p;
      GameSurface localGameSurface4 = this.this$0;
      int i1 = -17 + localGameSurface4.alphaDegree;
      localGameSurface4.alphaDegree = i1;
      localPaint.setAlpha(Math.max(i1, 0));
      byte b3 = 0;
      if (b3 >= 9)
      {
        int i3 = 0;
        while (true)
        {
          if (i3 >= this.this$0.numberOfBees);
          this.this$0.bees[i3].draw(paramCanvas, this.this$0.beeAngle[i3], this.p);
          ++i3;
        }
      }
      int i2 = 0;
      while (true)
      {
        while (i2 >= this.this$0.numberOfAntsWithType[b3])
          ++b3;
        if (this.this$0.smashed[b3][i2] != 0)
          this.this$0.ants[b3][i2].draw(paramCanvas, this.this$0.antAngle[b3][i2], this.p);
        ++i2;
      }
    }

    public void doPause()
    {
      if (!(this.this$0.paused))
        GameSurface.access$8(this.this$0, new Date().getTime());
      this.this$0.paused = true;
    }

    public void doResume()
    {
      if ((this.this$0.paused) && (GameSurface.access$9(this.this$0) > 0L))
      {
        long l1 = this.this$0.settings.getLong("PlayTimeDiscount", 0L);
        SharedPreferences.Editor localEditor = this.this$0.settings.edit();
        long l2 = new Date().getTime() - GameSurface.access$9(this.this$0);
        if (l2 > 0L)
          l1 = l1 + l2;
        localEditor.putLong("PlayTimeDiscount", l1);
        localEditor.commit();
      }
      this.this$0.paused = false;
    }

    public void doStop()
    {
      this.CONF = false;
    }

    boolean onTouchEvent()
    {
      float f;
      byte b1;
      int j;
      int k;
      byte b2;
      if (this.this$0.paused)
      {
        b2 = 0;
        return b2;
      }
      if (this.this$0.acceleration() < 26.0D)
      {
        f = 26.0F;
        b1 = 0;
        if (b1 < 9)
          break label320;
        j = (int)(13.0F * this.this$0.scale);
        k = 0;
      }
      while (true)
      {
        while (true)
        {
          while (true)
          {
            if (k < this.this$0.numberOfBees)
              break label648;
            if ((this.this$0.isBonus) && (this.this$0.bonus != null) && (!(this.this$0.isKillingBee)) && ((int)paramMotionEvent.getX() > this.this$0.bonus.getLeft() - (int)(0.23999999463558197F * this.this$0.bonus.getWidth() * this.this$0.scale)) && ((int)paramMotionEvent.getX() < this.this$0.bonus.getLeft() + this.this$0.bonus.getWidth() + (int)(0.23999999463558197F * this.this$0.bonus.getWidth() * this.this$0.scale)) && ((int)paramMotionEvent.getY() > this.this$0.bonus.getTop() - (int)(0.23999999463558197F * this.this$0.bonus.getHeight() * this.this$0.scale)) && ((int)paramMotionEvent.getY() < this.this$0.bonus.getTop() + this.this$0.bonus.getHeight() + (int)(0.23999999463558197F * this.this$0.bonus.getHeight() * this.this$0.scale)))
              this.this$0.bonusWasSmashed();
            b2 = 1;
          }
          f = this.this$0.acceleration();
        }
        label320: int i = 0;
        while (true)
        {
          while (i >= this.this$0.numberOfAntsWithType[b1])
            ++b1;
          if ((!(this.this$0.isKillingBee)) && (this.this$0.smashed[b1][i] == 0) && ((int)paramMotionEvent.getX() > this.this$0.ants[b1][i].getLeft() - (int)(0.23999999463558197F * this.this$0.ants[b1][i].getWidth()) - (int)(0.69999998807907104F * f)) && ((int)paramMotionEvent.getX() < this.this$0.ants[b1][i].getLeft() + this.this$0.ants[b1][i].getWidth() + (int)(0.23999999463558197F * this.this$0.ants[b1][i].getWidth()) + (int)(0.69999998807907104F * f)) && ((int)paramMotionEvent.getY() > this.this$0.ants[b1][i].getTop() - (int)(0.23999999463558197F * this.this$0.ants[b1][i].getHeight()) - (int)(0.69999998807907104F * f)) && ((int)paramMotionEvent.getY() < this.this$0.ants[b1][i].getTop() + this.this$0.ants[b1][i].getHeight() + (int)(0.23999999463558197F * this.this$0.ants[b1][i].getHeight()) + (int)(0.69999998807907104F * f)) && ((((b1 != 4) && (b1 != 6) && (b1 != 7)) || (paramMotionEvent.getAction() == 0))))
            this.this$0.sheWasSmashed(b1, i);
          ++i;
        }
        if ((!(this.this$0.isKillingBee)) && (!(this.this$0.isAlphaing)) && ((int)paramMotionEvent.getX() > j + this.this$0.bees[k].getLeft()) && ((int)paramMotionEvent.getX() < this.this$0.bees[k].getLeft() + this.this$0.bees[k].getWidth() - j) && ((int)paramMotionEvent.getY() > j + this.this$0.bees[k].getTop()) && ((int)paramMotionEvent.getY() < this.this$0.bees[k].getTop() + this.this$0.bees[k].getHeight() - j))
          label648: this.this$0.beeWasSmashed(k);
        ++k;
      }
    }

    public void refreshScreen()
    {
      this.refreshScreenFlag = Boolean.valueOf(true);
    }

    public void resetVars()
    {
      this.this$0.numberOfAntsWithType = new int[7];
      this.this$0.numberOfBees = 0;
      this.this$0.numberOfObjects = 0;
    }

    public void run()
    {
      Canvas localCanvas2;
      SurfaceHolder localSurfaceHolder2;
      if (this.CONF == 0)
        return;
      if ((this.this$0.paused) && (this.this$0.isKillingBee));
      try
      {
        localCanvas2 = this.mSurfaceHolder.lockCanvas();
        localSurfaceHolder2 = this.mSurfaceHolder;
      }
      finally
      {
        long l4;
        try
        {
          GameSurface localGameSurface3 = this.this$0;
          localGameSurface3.counter = (1 + localGameSurface3.counter);
          long l2 = SystemClock.uptimeMillis();
          doDraw(localCanvas2);
          this.this$0.killingBee(this.this$0.killingBeeIndex);
          this.this$0.killingBeeDraw(localCanvas2);
          long l3 = SystemClock.uptimeMillis();
          l4 = l3 - l2;
          if (l4 > 16);
        }
        finally
        {
          long l5;
          try
          {
            do
            {
              Thread.sleep(l5);
              monitorexit;
              if (localCanvas2 != null)
                this.mSurfaceHolder.unlockCanvasAndPost(localCanvas2);
            }
            while ((this.this$0.paused) && (!(this.refreshScreenFlag.booleanValue())));
            this.refreshScreenFlag = Boolean.valueOf(false);
          }
          catch (InterruptedException localInterruptedException)
          {
            Canvas localCanvas1;
            SurfaceHolder localSurfaceHolder1;
            try
            {
              localCanvas1 = this.mSurfaceHolder.lockCanvas();
              localSurfaceHolder1 = this.mSurfaceHolder;
            }
            finally
            {
              try
              {
                do
                {
                  GameSurface localGameSurface1 = this.this$0;
                  localGameSurface1.counter = (1 + localGameSurface1.counter);
                  long l1 = SystemClock.uptimeMillis();
                  doDraw(localCanvas1);
                  if (this.this$0.isCircleing)
                  {
                    this.this$0.killingBeeDraw(localCanvas1);
                    GameSurface localGameSurface2 = this.this$0;
                    int i = localGameSurface2.killingBeeCounter;
                    localGameSurface2.killingBeeCounter = (i + 1);
                    if (i > 40)
                      this.this$0.isCircleing = false;
                  }
                  if (SystemClock.uptimeMillis() - l1 > 20);
                  monitorexit;
                }
                while (localCanvas1 == null);
                this.mSurfaceHolder.unlockCanvasAndPost(localCanvas1);
              }
              finally
              {
                while (true)
                {
                  monitorexit;
                  throw localObject2;
                  localObject1 = finally;
                  if (0 != 0)
                    this.mSurfaceHolder.unlockCanvasAndPost(null);
                  throw localObject1;
                  localInterruptedException = localInterruptedException;
                }
              }
            }
          }
        }
      }
    }

    // ERROR //
    public void setSurfaceSize(, int paramInt2)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 180	com/bestcoolfungames/antsmasher/GameSurface$GameSurfaceThread:mSurfaceHolder	Landroid/view/SurfaceHolder;
      //   4: astore_3
      //   5: aload_3
      //   6: monitorenter
      //   7: iload_1
      //   8: iload_2
      //   9: if_icmple +18 -> 27
      //   12: iload_1
      //   13: iload_2
      //   14: ixor
      //   15: istore 5
      //   17: iload_2
      //   18: iload 5
      //   20: ixor
      //   21: istore_2
      //   22: iload 5
      //   24: iload_2
      //   25: ixor
      //   26: istore_1
      //   27: aload_0
      //   28: getfield 27	com/bestcoolfungames/antsmasher/GameSurface$GameSurfaceThread:this$0	Lcom/bestcoolfungames/antsmasher/GameSurface;
      //   31: iload_1
      //   32: putfield 401	com/bestcoolfungames/antsmasher/GameSurface:mCanvasWidth	I
      //   35: aload_0
      //   36: getfield 27	com/bestcoolfungames/antsmasher/GameSurface$GameSurfaceThread:this$0	Lcom/bestcoolfungames/antsmasher/GameSurface;
      //   39: iload_2
      //   40: putfield 433	com/bestcoolfungames/antsmasher/GameSurface:mCanvasHeight	I
      //   43: invokestatic 184	com/bestcoolfungames/antsmasher/GameSurface:access$0	()Landroid/graphics/Bitmap;
      //   46: iload_1
      //   47: iload_2
      //   48: iconst_1
      //   49: invokestatic 369	android/graphics/Bitmap:createScaledBitmap	(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;
      //   52: invokestatic 195	com/bestcoolfungames/antsmasher/GameSurface:access$1	(Landroid/graphics/Bitmap;)V
      //   55: aload_0
      //   56: getfield 180	com/bestcoolfungames/antsmasher/GameSurface$GameSurfaceThread:mSurfaceHolder	Landroid/view/SurfaceHolder;
      //   59: invokeinterface 617 1 0
      //   64: aload_3
      //   65: monitorexit
      //   66: return
      //   67: astore 4
      //   69: aload_3
      //   70: monitorexit
      //   71: aload 4
      //   73: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   27	71	67	finally
    }

    public void simpleDraw()
    {
      Canvas localCanvas;
      SurfaceHolder localSurfaceHolder;
      try
      {
        localCanvas = this.mSurfaceHolder.lockCanvas();
        localSurfaceHolder = this.mSurfaceHolder;
      }
      finally
      {
        try
        {
        }
        finally
        {
          monitorexit;
          throw localObject2;
          localObject1 = finally;
          if (0 != 0)
            this.mSurfaceHolder.unlockCanvasAndPost(null);
        }
      }
    }
  }
}