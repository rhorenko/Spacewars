package com.space.game;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.Random;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.RelativeLayout;

import com.space.levels.LevelInterface;

public abstract class GameSurface
  implements LevelInterface
{
		
  public static final int NUMBER_OF_INSECT_FRAMES = 5;
  public static final int TYPES_OF_shipS = 9;
  private static Bitmap[][] shipFrames;
  private static Bitmap[][] shipFramesFriendSmasher;
  private static Bitmap[][] shipFramesOriginal;
  
  public static int shipHeight;
  public static int shipWidth;
  
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
  public int shipCounter;
  
  public int[][] shipAngle;
  public int[][] shipDirection;
  public int[][] shipLife;
  public int[][] shipOrder;
  public boolean[][] smashed;//indicates that ship is destroyed
  public SurfaceBitmap[][] ships;
  
  int[][] shipOrdinator;
  final int shipSizeX;
  final int shipSizeY;
  int shipSmashedCounter;
 
  public int[] beeAngle;
  public int[] beeDirection;
  boolean[] beeInScreen;
  public int[] beeOrder;
  int[] beeOrdinator;
  final int beeSizeX;
  final int beeSizeY;
  SurfaceBitmap[] bees;
  int bigshipAlphaControl;
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
  public int mCanvasHeight;
  public int mCanvasWidth;
  Context mContext;
  int maxLifes;
  public MediaPlayer mp;
  public int[] numberOfshipsWithType;
  public int numberOfBees;
  public int numberOfObjects;
  SurfaceBitmap[] numbers;
  public int objectPadding;
  int ordinatorCounter;
  public int tempX;
  public int tempY;
  boolean passAheadTouchEvents;
  boolean passed;
  public boolean paused;
  public int proceed;
  boolean protection;
  public float scale;
  SharedPreferences settings;
  
  private long startDiscount;
  private GameSurfaceView surfaceView;
  GameSurfaceThread thread;
  int totalOfships;
  public final float touchMargin = 0.23999999463558197F;
  boolean youch;

  static
  {
    ouchBmp = null;
    bloodFrame = null;
  }

  public GameSurface()
  {
    shipSizeX = 50;
    shipSizeY = 70;
    beeSizeX = 60;
    beeSizeY = 80;
    CONF = Bitmap.Config.ARGB_4444;
    startDiscount = 0L;
    counter = 0;
  }

  private Resources getResources()
  {
    return surfaceView.getResources();
  }

  public static Bitmap mergeBitmaps(Bitmap paramBitmap1, Bitmap paramBitmap2)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap1);
    new Canvas(localBitmap).drawBitmap(paramBitmap2, 0F, 0F, null);
    return localBitmap;
  }

  public static void restoreOriginalships()
  {
    shipFrames = shipFramesOriginal;
  }

  public static void setFixedBitmap(Context paramContext, Bitmap paramBitmap)
  {
    Log.d("shipSmasher", "we not set the new theBmps");
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
        Log.i("TAG","Error: bloodFrame is null");
    }
    Bitmap localBitmap3 = mergeBitmaps(localBitmap2, bloodFrame);
    System.gc();
    if (shipFramesFriendSmasher == null)
    {
      int[] arrayOfInt = new int[2];
      arrayOfInt[0] = 9;
      arrayOfInt[1] = 5;
      shipFramesFriendSmasher = (Bitmap[][])Array.newInstance(Bitmap.class, arrayOfInt);
    }
    byte b1 = 0;
    if (b1 >= 9)
    {
      shipFrames = shipFramesFriendSmasher;
      return;
    }
    byte b2 = 0;
    while (true)
    {
      while (b2 >= 4)
      {
        shipFramesFriendSmasher[b1][4] = localBitmap3;
        ++b1;
      }
      shipFramesFriendSmasher[b1][b2] = localBitmap2;
      ++b2;
    }
  }

  public float acceleration()
  {
    return accel;
  }

  public void acceleration(float paramFloat)
  {
    editor.putFloat("Acceleration", paramFloat);
    editor.commit();
  }

  public void beeWasSmashed(int paramInt)
  {
    if (isCircleing);
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
              mp = MediaPlayer.create(mContext, i);
              mp.setAudioStreamType(3);
              if (settings.getBoolean("Sound", true))
                mp.start();
              mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(this)
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
              killingBeeCounter = 0;
              if (ouchBmp == null)
                ouchBmp = new SurfaceBitmap(BitmapFactory.decodeResource(getResources(), 2130837577));
              ouchBmp.setPosition(bees[paramInt].getLeft(), -35 + bees[paramInt].getTop());
              if (gameMode != 0)
                break label238;
              if (!(protection))
                break;
              isCircleing = true;
              protection = false;
              editor.putBoolean("Prot", false);
              editor.commit();
            }
            paused = true;
            killingBeeIndex = paramInt;
            bees[paramInt].setBitmap(BitmapFactory.decodeResource(getResources(), 2130837556));
            beeAngle[paramInt] = 180;
            killingBee(paramInt);
            isKillingBee = true;
          }
        while (gameMode == 3);
        int j = settings.getInt("Lifes", 0);
        if (j > 1)
        {
          isCircleing = true;
          if (GameActivity.lifeFlag == -1)
            setLifes(-2);
          while (true)
          {
            while (true)
            {
              editor.putInt("Lifes", j + -1);
              editor.commit();
            }
            setLifes(-1);
          }
        }
        if (!(protection))
          break;
        isCircleing = true;
        protection = false;
        editor.putBoolean("Prot", false);
        editor.commit();
      }
      paused = true;
      killingBeeIndex = paramInt;
      bees[paramInt].setBitmap(BitmapFactory.decodeResource(getResources(), 2130837556));
      beeAngle[paramInt] = 180;
      killingBee(paramInt);
      isKillingBee = true;
    }
  }

  void bonusWasSmashed()
  {
    isBonus = false;
    switch (bonusType)
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
                editor.putBoolean("Bonus", false);
                editor.commit();
                shipSmashedCounter = (1 + shipSmashedCounter);
                int i = 2131034119;
                if (bonusType == 0)
                  i = 2131034123;
                mp = MediaPlayer.create(mContext, i);
                mp.setAudioStreamType(3);
                if (isSound)
                  mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(this)
                {
                  public void onCompletion()
                  {
                    if ((paramMediaPlayer != null) && (paramMediaPlayer.isPlaying()))
                      paramMediaPlayer.stop();
                  }
                });
                return;
                editor.putInt("Lifes", 1 + settings.getInt("Lifes", 0));
                editor.commit();
                setLifes(1);
              }
              editor.putInt("Points", 5 + settings.getInt("Points", 0));
              editor.commit();
            }
            editor.putInt("Points", 10 + settings.getInt("Points", 0));
            editor.commit();
          }
          editor.putInt("Points", 25 + settings.getInt("Points", 0));
          editor.commit();
        }
        editor.putInt("Points", 50 + settings.getInt("Points", 0));
        editor.commit();
      }
      editor.putInt("Points", 100 + settings.getInt("Points", 0));
      editor.commit();
    }
  }

  void doEndGame()
  {
    if (settings.getBoolean("Vibration", true))
      ((Vibrator)mContext.getSystemService("vibrator")).vibrate(200);
    GameActivity.surfaceAction = 3;
    SharedPreferences.Editor localEditor = mContext.getSharedPreferences("AppData", 0).edit();
    localEditor.putBoolean("ShouldContinue", false);
    localEditor.commit();
    passed = true;
  }

  public void doPause()
  {
    thread.doPause();
  }

  public void doResume()
  {
    thread.doResume();
  }

  public void doStop()
  {
    passAheadTouchEvents = false;
    thread.doStop();
  }

  public View getSurfaceView()
  {
    return surfaceView;
  }

  public GameSurfaceThread getThread()
  {
    return thread;
  }

  void killingBee(int paramInt)
  {
    killingBeeCounter = (1 + killingBeeCounter);
    if (beeAngle[paramInt] % 360 > 0)
    {
      int[] arrayOfInt = beeAngle;
      arrayOfInt[paramInt] = (4 + arrayOfInt[paramInt]);
    }
    if (killingBeeCounter > 40)
    {
      isKillingBee = false;
      doEndGame();
    }
  }

  void killingBeeDraw(Canvas paramCanvas)
  {
    ouchBmp.draw(paramCanvas, 0);
  }

  public void refreshScreen()
  {
    thread.refreshScreen();
  }

  public void resetVars()
  {
    thread.resetVars();
  }

  public void setLayoutParams(RelativeLayout.LayoutParams paramLayoutParams)
  {
    surfaceView.setLayoutParams(paramLayoutParams);
  }

  public void setLifes(int paramInt)
  {
    GameActivity.lifeFlag = paramInt;
  }

  public void setup(Context paramContext, GameSurfaceView paramGameSurfaceView)
  {
    surfaceView = paramGameSurfaceView;
    surfaceView.setGameSurface(this);
    mContext = paramContext;
    SurfaceHolder localSurfaceHolder = paramGameSurfaceView.getHolder();
    localSurfaceHolder.addCallback(paramGameSurfaceView);
    passAheadTouchEvents = true;
    thread = new GameSurfaceThread(this, localSurfaceHolder, mContext, new Handler(this)
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
    if (shipLife[paramInt1][paramInt2] <= 1)
    {
      smashed[paramInt1][paramInt2] = 1;
      if (paramInt1 != 7)
      {
        ships[paramInt1][paramInt2].setBitmap(shipFrames[paramInt1][4]);
        shipSmashedCounter = (1 + shipSmashedCounter);
        if (paramInt1 != 3)
          break label219;
        editor.putInt("Points", 10 + settings.getInt("Points", 0));
        editor.commit();
        i = 2131034123;
        if (paramInt1 != 0)
          break label330;
        i = 2131034112 + rand.nextInt(1);
        if (paramInt1 >= 4)
          i = 2131034116;
        mp = MediaPlayer.create(mContext, i);
        if (mp != null)
        {
          mp.setAudioStreamType(3);
          if ((isSound) && (mp != null))
          {
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(this)
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
                          ships[paramInt1][paramInt2].setBitmap(shipFrames[(paramInt1 + 1)][4]);
                        }
                        label219: if ((paramInt1 != 4) && (paramInt1 != 5))
                          break;
                        editor.putInt("Points", 50 + settings.getInt("Points", 0));
                      }
                      if (paramInt1 != 6)
                        break;
                      editor.putInt("Points", 100 + settings.getInt("Points", 0));
                    }
                    editor.putInt("Points", 1 + settings.getInt("Points", 0));
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
          int[] arrayOfInt = shipLife[paramInt1];
          arrayOfInt[paramInt2] = (-1 + arrayOfInt[paramInt2]);
          mp = MediaPlayer.create(mContext, 2131034117);
        }
        while (mp == null);
        mp.setAudioStreamType(3);
        if (isSound)
          mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(this)
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
      bigshipAlphaControl = 1;
    }
  }

  public void simpleDraw()
  {
    thread.simpleDraw();
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

    public GameSurfaceThread(GameSurface paramGameSurface, SurfaceHolder paramSurfaceHolder, Context paramContext, Handler paramHandler)
    {
      CONF = Bitmap.Config.ARGB_4444;
      paramGameSurface.maxLifes = paramContext.getSharedPreferences("shipSmasherShop", 0).getInt("MaxLifes", 3);
      GameSurface.ratioX = 1.4624999761581421F;
      GameSurface.ratioY = 1.25F;
      GameSurface.ratio = 1.1000000238418579F;
      GameSurface.shipWidth = (int)(50.0F * GameSurface.ratio * GameSurface.ratioX);
      GameSurface.shipHeight = (int)(70.0F * GameSurface.ratio * GameSurface.ratioY);
      paramGameSurface.isCircleing = false;
      paramGameSurface.isAlphaing = false;
      paramGameSurface.isKillingBee = false;
      paramGameSurface.bigshipAlphaControl = 0;
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
      mSurfaceHolder = paramSurfaceHolder;
      paramGameSurface.mContext = paramContext;
      Resources localResources = paramContext.getResources();
      if (GameSurface.access$0() == null)
        GameSurface.access$1(BitmapFactory.decodeResource(localResources, 2130837558));
      paramGameSurface.numberOfshipsWithType = new int[9];
      int[] arrayOfInt1 = new int[2];
      arrayOfInt1[0] = 9;
      arrayOfInt1[1] = 10;
      paramGameSurface.shipAngle = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt1));
      int[] arrayOfInt2 = new int[2];
      arrayOfInt2[0] = 9;
      arrayOfInt2[1] = 10;
      paramGameSurface.shipOrder = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt2));
      int[] arrayOfInt3 = new int[2];
      arrayOfInt3[0] = 9;
      arrayOfInt3[1] = 10;
      paramGameSurface.shipDirection = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt3));
      int[] arrayOfInt4 = new int[2];
      arrayOfInt4[0] = 9;
      arrayOfInt4[1] = 10;
      paramGameSurface.inScreen = ((boolean[][])Array.newInstance(Boolean.TYPE, arrayOfInt4));
      int[] arrayOfInt5 = new int[2];
      arrayOfInt5[0] = 9;
      arrayOfInt5[1] = 10;
      paramGameSurface.ships = ((SurfaceBitmap[][])Array.newInstance(SurfaceBitmap.class, arrayOfInt5));
      paramGameSurface.bees = new SurfaceBitmap[5];
      paramGameSurface.beeInScreen = new boolean[5];
      paramGameSurface.ordinatorCounter = -1;
      int[] arrayOfInt6 = new int[2];
      arrayOfInt6[0] = 9;
      arrayOfInt6[1] = 10;
      paramGameSurface.shipOrdinator = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt6));
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
        paramGameSurface.shipLife = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt8));
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
        paramGameSurface.shipSmashedCounter = 0;
        paramGameSurface.shipCounter = 0;
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
          paramGameSurface.shipCounter = 1;
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
                      GameSurface.access$2()[b7][k] = GameSurface.access$2()[b7][k].copy(CONF, false);
                      if ((b7 != 4) && (b7 != 6))
                        GameSurface.access$2()[b7][k] = Bitmap.createScaledBitmap(GameSurface.access$2()[b7][k], GameSurface.shipWidth, GameSurface.shipHeight, true);
                      if (b7 == 6)
                        GameSurface.access$2()[b7][k] = Bitmap.createScaledBitmap(GameSurface.access$2()[b7][k], (int)(2.5F * GameSurface.shipWidth), (int)(2.5F * GameSurface.shipHeight), true);
                      ++k;
                    }
                    GameSurface.access$6()[j] = BitmapFactory.decodeResource(paramGameSurface.mContext.getResources(), 2130837552 + j);
                    GameSurface.access$6()[j] = GameSurface.access$6()[j].copy(CONF, false);
                    GameSurface.access$6()[j] = Bitmap.createScaledBitmap(GameSurface.access$6()[j], 84, 112, true);
                    ++j;
                  }
                  label1330: System.gc();
                  byte b5 = 0;
                  while (true)
                  {
                    while (b5 >= 5)
                      ++b4;
                    paramGameSurface.shipLife[b4][b5] = 1;
                    if (b4 == 4)
                      paramGameSurface.shipLife[b4][b5] = 3;
                    if (b4 == 6)
                      paramGameSurface.shipLife[b4][b5] = 5;
                    if (b4 == 7)
                      paramGameSurface.shipLife[b4][b5] = 2;
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
            if (l < this$0.numberOfBees)
              break label1484;
            if (!(this$0.isBonus))
              break label457;
            if (GameSurface.rand.nextInt(10) < 2)
            {
              GameSurface localGameSurface8 = this$0;
              localGameSurface8.bonusDir = (-1 * localGameSurface8.bonusDir);
            }
            if (this$0.bonus.getLeft() > this$0.mCanvasWidth - this$0.bonus.getWidth())
              this$0.bonusAngle = 216;
            if (this$0.bonus.getLeft() < 0)
              this$0.bonusAngle = 144;
            if (GameSurface.rand.nextInt(10) < 2)
            {
              GameSurface localGameSurface7 = this$0;
              localGameSurface7.bonusDir = (-1 * localGameSurface7.bonusDir);
            }
            if (this$0.bonusAngle > 300)
              this$0.bonusAngle = 144;
            if (this$0.bonusAngle < 60)
              this$0.bonusAngle = 216;
            GameSurface localGameSurface5 = this$0;
            localGameSurface5.bonusAngle = (localGameSurface5.bonusAngle + 5 * this$0.bonusDir);
            if (!(this$0.paused))
              this$0.bonus.setPosition((int)Math.round(this$0.bonus.getLeft() - 5.0D * Math.sin(Math.toRadians(180 + this$0.bonusAngle))), this$0.bonus.getTop() + (int)(3.0F * this$0.scale * (1F + this$0.acceleration() / 100.0F)));
            this$0.bonus.draw(paramCanvas, 0);
            if (this$0.bonus.getTop() <= this$0.mCanvasHeight)
              break label457;
          }
          while (!(this$0.settings.getBoolean("Bonus", false)));
          GameSurface localGameSurface6 = this$0;
          localGameSurface6.shipSmashedCounter = (1 + localGameSurface6.shipSmashedCounter);
          this$0.editor.putBoolean("Bonus", false);
          this$0.editor.commit();
          this$0.isBonus = false;
          if ((this$0.shipSmashedCounter >= this$0.shipCounter) && (!(this$0.isAlphaing)))
          {
            label457: this$0.isAlphaing = true;
            Log.i("", "First step");
            GameActivity.surfaceAction = 2;
            SharedPreferences.Editor localEditor = this$0.mContext.getSharedPreferences("AppData", 0).edit();
            localEditor.putBoolean("ShouldContinue", false);
            localEditor.commit();
          }
          if (this$0.isAlphaing)
            break label1617;
          this$0.updatePositions();
        }
        int i = 0;
        while (true)
        {
          while (i >= this$0.numberOfshipsWithType[b1])
            ++b1;
          if ((this$0.smashed[b1][i] != 0) && (!(this$0.isAlphaing)))
            this$0.ships[b1][i].draw(paramCanvas, this$0.shipAngle[b1][i]);
          ++i;
        }
        label643: j = 0;
        if (j < this$0.numberOfshipsWithType[b2])
          break;
        ++b2;
      }
      if ((this$0.smashed[b2][j] == 0) && (this$0.inScreen[b2][j] != 0))
      {
        if (!(this$0.paused))
        {
          if (b2 == 7)
            break label1085;
          this$0.ships[b2][j].setBitmap(GameSurface.access$4()[b2][(this$0.counter / 4 % 4)]);
        }
        if ((b2 == 4) || (b2 == 6))
          break label1178;
        this$0.ships[b2][j].draw(paramCanvas, this$0.shipAngle[b2][j]);
        if ((this$0.ships[b2][j].getTop() > this$0.mCanvasHeight) && (this$0.mCanvasHeight != 0))
        {
          int k = this$0.settings.getInt("Lifes", 0);
          if ((this$0.protection) && (((!(this$0.protection)) || (k < 2))))
            break label1329;
          if ((this$0.gameMode != 3) || ((this$0.gameMode == 3) && (k > 2)))
          {
            if (GameActivity.lifeFlag != -1)
              break label1317;
            this$0.setLifes(-2);
            this$0.editor.putInt("Lifes", k - 1);
            this$0.editor.commit();
          }
          GameSurface localGameSurface2 = this$0;
          localGameSurface2.shipSmashedCounter = (1 + localGameSurface2.shipSmashedCounter);
          this$0.smashed[b2][j] = 1;
          this$0.inScreen[b2][j] = 0;
          this$0.mp = MediaPlayer.create(this$0.mContext, 2131034126);
          this$0.mp.setAudioStreamType(3);
          if (this$0.isSound)
            this$0.mp.start();
          this$0.mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(this)
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
            this$0.doEndGame();
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
                    label1085: if (this$0.shipLife[b2][j] != 2)
                      break;
                    this$0.ships[b2][j].setBitmap(GameSurface.access$4()[b2][(this$0.counter / 4 % 4)]);
                  }
                  this$0.ships[b2][j].setBitmap(GameSurface.access$4()[(b2 + 1)][(this$0.counter / 4 % 4)]);
                }
                label1178: if (this$0.bigshipAlphaControl != 0)
                  break;
                this$0.ships[b2][j].draw(paramCanvas, this$0.shipAngle[b2][j]);
              }
              p = new Paint();
              p.setAlpha(128);
              this$0.ships[b2][j].draw(paramCanvas, this$0.shipAngle[b2][j], p);
              GameSurface localGameSurface1 = this$0;
              localGameSurface1.bigshipAlphaControl = (1 + localGameSurface1.bigshipAlphaControl);
            }
            while (this$0.bigshipAlphaControl <= 6);
            this$0.bigshipAlphaControl = 0;
          }
          label1317: this$0.setLifes(-1);
        }
        label1329: this$0.protection = false;
        this$0.editor.putBoolean("Prot", false);
        this$0.editor.commit();
        GameSurface localGameSurface3 = this$0;
        localGameSurface3.shipSmashedCounter = (1 + localGameSurface3.shipSmashedCounter);
        this$0.smashed[b2][j] = 1;
        this$0.inScreen[b2][j] = 0;
        this$0.mp = MediaPlayer.create(this$0.mContext, 2131034126);
        this$0.mp.setAudioStreamType(3);
        if (this$0.isSound)
          this$0.mp.start();
        this$0.mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(this)
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
      if ((this$0.beeInScreen[l] != 0) && (!(this$0.isAlphaing)))
      {
        if (!(this$0.paused))
          label1484: this$0.bees[l].setBitmap(GameSurface.access$6()[(this$0.counter / 4 % 4)]);
        this$0.bees[l].draw(paramCanvas, this$0.beeAngle[l]);
      }
      while (true)
      {
        do
          while (true)
            ++l;
        while (this$0.bees[l].bitmap() == GameSurface.access$6()[4]);
        this$0.bees[l].setBitmap(GameSurface.access$6()[0]);
      }
      label1617: p = new Paint();
      Paint localPaint = p;
      GameSurface localGameSurface4 = this$0;
      int i1 = -17 + localGameSurface4.alphaDegree;
      localGameSurface4.alphaDegree = i1;
      localPaint.setAlpha(Math.max(i1, 0));
      byte b3 = 0;
      if (b3 >= 9)
      {
        int i3 = 0;
        while (true)
        {
          if (i3 >= this$0.numberOfBees);
          this$0.bees[i3].draw(paramCanvas, this$0.beeAngle[i3], p);
          ++i3;
        }
      }
      int i2 = 0;
      while (true)
      {
        while (i2 >= this$0.numberOfshipsWithType[b3])
          ++b3;
        if (this$0.smashed[b3][i2] != 0)
          this$0.ships[b3][i2].draw(paramCanvas, this$0.shipAngle[b3][i2], p);
        ++i2;
      }
    }

    public void doPause()
    {
      if (!(this$0.paused))
        GameSurface.access$8(this$0, new Date().getTime());
      this$0.paused = true;
    }

    public void doResume()
    {
      if ((this$0.paused) && (GameSurface.access$9(this$0) > 0L))
      {
        long l1 = this$0.settings.getLong("PlayTimeDiscount", 0L);
        SharedPreferences.Editor localEditor = this$0.settings.edit();
        long l2 = new Date().getTime() - GameSurface.access$9(this$0);
        if (l2 > 0L)
          l1 = l1 + l2;
        localEditor.putLong("PlayTimeDiscount", l1);
        localEditor.commit();
      }
      this$0.paused = false;
    }

    public void doStop()
    {
      CONF = false;
    }

    boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
      float f;
      byte b1;
      int j;
      int k;
      byte b2;
      if (this$0.paused)
      {
        b2 = 0;
        return b2;
      }
      if (this$0.acceleration() < 26.0D)
      {
        f = 26.0F;
        b1 = 0;
        if (b1 < 9)
          break label320;
        j = (int)(13.0F * this$0.scale);
        k = 0;
      }
      while (true)
      {
        while (true)
        {
          while (true)
          {
            if (k < this$0.numberOfBees)
              break label648;
            if ((this$0.isBonus) && (this$0.bonus != null) && (!(this$0.isKillingBee)) && ((int)paramMotionEvent.getX() > this$0.bonus.getLeft() - (int)(0.23999999463558197F * this$0.bonus.getWidth() * this$0.scale)) && ((int)paramMotionEvent.getX() < this$0.bonus.getLeft() + this$0.bonus.getWidth() + (int)(0.23999999463558197F * this$0.bonus.getWidth() * this$0.scale)) && ((int)paramMotionEvent.getY() > this$0.bonus.getTop() - (int)(0.23999999463558197F * this$0.bonus.getHeight() * this$0.scale)) && ((int)paramMotionEvent.getY() < this$0.bonus.getTop() + this$0.bonus.getHeight() + (int)(0.23999999463558197F * this$0.bonus.getHeight() * this$0.scale)))
              this$0.bonusWasSmashed();
            b2 = 1;
          }
          f = this$0.acceleration();
        }
        label320: int i = 0;
        while (true)
        {
          while (i >= this$0.numberOfshipsWithType[b1])
            ++b1;
          if ((!(this$0.isKillingBee)) && (this$0.smashed[b1][i] == 0) && ((int)paramMotionEvent.getX() > this$0.ships[b1][i].getLeft() - (int)(0.23999999463558197F * this$0.ships[b1][i].getWidth()) - (int)(0.69999998807907104F * f)) && ((int)paramMotionEvent.getX() < this$0.ships[b1][i].getLeft() + this$0.ships[b1][i].getWidth() + (int)(0.23999999463558197F * this$0.ships[b1][i].getWidth()) + (int)(0.69999998807907104F * f)) && ((int)paramMotionEvent.getY() > this$0.ships[b1][i].getTop() - (int)(0.23999999463558197F * this$0.ships[b1][i].getHeight()) - (int)(0.69999998807907104F * f)) && ((int)paramMotionEvent.getY() < this$0.ships[b1][i].getTop() + this$0.ships[b1][i].getHeight() + (int)(0.23999999463558197F * this$0.ships[b1][i].getHeight()) + (int)(0.69999998807907104F * f)) && ((((b1 != 4) && (b1 != 6) && (b1 != 7)) || (paramMotionEvent.getAction() == 0))))
            this$0.sheWasSmashed(b1, i);
          ++i;
        }
        if ((!(this$0.isKillingBee)) && (!(this$0.isAlphaing)) && ((int)paramMotionEvent.getX() > j + this$0.bees[k].getLeft()) && ((int)paramMotionEvent.getX() < this$0.bees[k].getLeft() + this$0.bees[k].getWidth() - j) && ((int)paramMotionEvent.getY() > j + this$0.bees[k].getTop()) && ((int)paramMotionEvent.getY() < this$0.bees[k].getTop() + this$0.bees[k].getHeight() - j))
          label648: this$0.beeWasSmashed(k);
        ++k;
      }
    }

    public void refreshScreen()
    {
      refreshScreenFlag = Boolean.valueOf(true);
    }

    public void resetVars()
    {
      this$0.numberOfshipsWithType = new int[7];
      this$0.numberOfBees = 0;
      this$0.numberOfObjects = 0;
    }

    public void run()
    {
      Canvas localCanvas2;
      SurfaceHolder localSurfaceHolder2;
      if (CONF == 0)
        return;
      if ((this$0.paused) && (this$0.isKillingBee));
      try
      {
        localCanvas2 = mSurfaceHolder.lockCanvas();
        localSurfaceHolder2 = mSurfaceHolder;
      }
      finally
      {
        long l4;
        try
        {
          GameSurface localGameSurface3 = this$0;
          localGameSurface3.counter = (1 + localGameSurface3.counter);
          long l2 = SystemClock.uptimeMillis();
          doDraw(localCanvas2);
          this$0.killingBee(this$0.killingBeeIndex);
          this$0.killingBeeDraw(localCanvas2);
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
                mSurfaceHolder.unlockCanvasAndPost(localCanvas2);
            }
            while ((this$0.paused) && (!(refreshScreenFlag.booleanValue())));
            refreshScreenFlag = Boolean.valueOf(false);
          }
          catch (InterruptedException localInterruptedException)
          {
            Canvas localCanvas1;
            SurfaceHolder localSurfaceHolder1;
            try
            {
              localCanvas1 = mSurfaceHolder.lockCanvas();
              localSurfaceHolder1 = mSurfaceHolder;
            }
            finally
            {
              try
              {
                do
                {
                  GameSurface localGameSurface1 = this$0;
                  localGameSurface1.counter = (1 + localGameSurface1.counter);
                  long l1 = SystemClock.uptimeMillis();
                  doDraw(localCanvas1);
                  if (this$0.isCircleing)
                  {
                    this$0.killingBeeDraw(localCanvas1);
                    GameSurface localGameSurface2 = this$0;
                    int i = localGameSurface2.killingBeeCounter;
                    localGameSurface2.killingBeeCounter = (i + 1);
                    if (i > 40)
                      this$0.isCircleing = false;
                  }
                  if (SystemClock.uptimeMillis() - l1 > 20);
                  monitorexit;
                }
                while (localCanvas1 == null);
                mSurfaceHolder.unlockCanvasAndPost(localCanvas1);
              }
              finally
              {
                while (true)
                {
                  monitorexit;
                  throw localObject2;
                  localObject1 = finally;
                  if (0 != 0)
                    mSurfaceHolder.unlockCanvasAndPost(null);
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
    //public void setSurfaceSize(, int paramInt2)
    //{
    //}

    public void simpleDraw()
    {
      Canvas localCanvas;
      SurfaceHolder localSurfaceHolder;
      try
      {
        localCanvas = mSurfaceHolder.lockCanvas();
        localSurfaceHolder = mSurfaceHolder;
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
            mSurfaceHolder.unlockCanvasAndPost(null);
        }
      }
    }
    
    public void setSurfaceSize(int paramInt2, int paramInt3){
    	//don't foget set parameters
    };
    
    
  }
}