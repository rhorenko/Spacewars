package com.bestcoolfungames.antsmasher;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InfiniteGameActivityView extends SurfaceView
  implements SurfaceHolder.Callback
{
  public static final int MAX_SPRITES_ON_SCREEN = 10;
  public static final int STATE_DEAD = 2;
  public static final int STATE_DYING = 1;
  public static final int STATE_NORMAL;
  GameThread _mThread;
  List<Enemy> enemies;
  final int logicHeight;
  final int logicWidth = 320;
  public MediaPlayer mp;
  public final Random r;
  float scaleX;
  float scaleY;

  public InfiniteGameActivityView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.logicHeight = 480;
    this.r = new Random();
    SurfaceHolder localSurfaceHolder = getHolder();
    localSurfaceHolder.addCallback(this);
    this._mThread = new GameThread(this, localSurfaceHolder, paramContext, new Handler(this)
    {
      public void handleMessage()
      {
      }
    });
    setFocusable(true);
  }

  boolean collinding(SurfaceBitmap paramSurfaceBitmap1, SurfaceBitmap paramSurfaceBitmap2)
  {
    byte b;
    if ((paramSurfaceBitmap1.getTop() < paramSurfaceBitmap2.getTop() + paramSurfaceBitmap2.getHeight()) && (paramSurfaceBitmap1.getTop() + paramSurfaceBitmap1.getHeight() > paramSurfaceBitmap2.getTop()) && (paramSurfaceBitmap1.getLeft() < paramSurfaceBitmap2.getLeft() + paramSurfaceBitmap2.getWidth()) && (paramSurfaceBitmap1.getLeft() + paramSurfaceBitmap1.getWidth() > paramSurfaceBitmap2.getLeft()))
      b = 1;
    while (true)
    {
      return b;
      b = 0;
    }
  }

  GameThread getThread()
  {
    return this._mThread;
  }

  public void onAccuracyChanged(Sensor paramSensor, int paramInt)
  {
  }

  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    this._mThread.onSensorChanged(paramSensorEvent);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return this._mThread.onTouchEvent(paramMotionEvent);
  }

  public void stop()
  {
    try
    {
      this._mThread.join();
      label7: return;
    }
    catch (InterruptedException localInterruptedException)
    {
      break label7:
    }
  }

  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
    this._mThread.setWorldBounds(paramInt2, paramInt3);
  }

  public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    this._mThread.start();
  }

  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
    byte b = 1;
    if (b == 0)
      label2: return;
    try
    {
      this._mThread.join();
      b = 0;
    }
    catch (InterruptedException localInterruptedException)
    {
      break label2:
    }
  }

  public class Enemy
  {
    public int angle;
    public int antType;
    int direction;
    public int dyingcounter;
    public SurfaceBitmap mBitmap;
    int nVelocity = 4;
    public int state;
    public int velocity;

    public Enemy(, int paramInt1, int paramInt2, int paramInt3)
    {
      this.angle = paramInt1;
      this.velocity = paramInt2;
      this.state = paramInt3;
      if (paramInfiniteGameActivityView.r.nextInt(2) < i);
      while (true)
      {
        this.direction = i;
        this.mBitmap = new SurfaceBitmap();
        return;
        i = -1;
      }
    }

    public void update()
    {
      if (this.mBitmap.getLeft() + this.mBitmap.getWidth() > 320)
        this.angle = -35;
      if (this.mBitmap.getLeft() < 0)
        this.angle = 35;
      if (this.this$0.r.nextInt(10) < 2)
        this.direction = (-1 * this.direction);
      if (this.angle > 45)
      {
        this.angle = 30;
        this.direction = (-1 * this.direction);
      }
      if (this.angle < -45)
      {
        this.angle = -30;
        this.direction = (-1 * this.direction);
      }
      this.angle = (this.angle + 5 * this.direction);
      this.mBitmap.setPosition((int)Math.round(this.mBitmap.getLeft() + Math.sin(Math.toRadians(this.angle)) * this.nVelocity), (int)Math.round(this.mBitmap.getTop() - Math.cos(Math.toRadians(this.angle)) * this.nVelocity));
      if (this.mBitmap.getTop() < this.mBitmap.getHeight())
        this.state = 2;
    }
  }

  class GameThread extends Thread
  {
    long counter;
    int direction;
    private SurfaceHolder mSurfaceHolder;
    boolean paused;
    int velX = 2;
    int velY = 2;
    int worldHeight;
    int worldWidth;

    public GameThread(, SurfaceHolder paramSurfaceHolder, Context paramContext, Handler paramHandler)
    {
      this.mSurfaceHolder = paramSurfaceHolder;
      this.paused = false;
      loadResources(paramContext);
      paramInfiniteGameActivityView.enemies = new ArrayList(10);
      byte b = 0;
      while (true)
      {
        if (b >= 10)
        {
          ((InfiniteGameActivityView.Enemy)paramInfiniteGameActivityView.enemies.get(0)).state = 0;
          ((InfiniteGameActivityView.Enemy)paramInfiniteGameActivityView.enemies.get(0)).antType = paramInfiniteGameActivityView.r.nextInt(3);
          ((InfiniteGameActivityView.Enemy)paramInfiniteGameActivityView.enemies.get(0)).mBitmap.setPosition(160, 500);
          return;
        }
        paramInfiniteGameActivityView.enemies.add(new InfiniteGameActivityView.Enemy(paramInfiniteGameActivityView, 0, 0, 2));
        ++b;
      }
    }

    public void doDraw()
    {
      paramCanvas.drawBitmap(InfiniteGameActivity.bg, 0F, 0F, null);
      paramCanvas.scale(this.this$0.scaleX, this.this$0.scaleY);
      byte b = 0;
      if (b >= 10)
        return;
      InfiniteGameActivityView.Enemy localEnemy = (InfiniteGameActivityView.Enemy)this.this$0.enemies.get(b);
      switch (localEnemy.state)
      {
      default:
      case 0:
      case 1:
      }
      while (true)
      {
        while (true)
        {
          while (true)
            ++b;
          localEnemy.mBitmap.setBitmap(InfiniteGameActivity.ants[localEnemy.antType][(int)(this.counter % 4)]);
          localEnemy.mBitmap.draw(paramCanvas, localEnemy.angle);
        }
        localEnemy.mBitmap.setBitmap(InfiniteGameActivity.ants[localEnemy.antType][4]);
        localEnemy.mBitmap.draw(paramCanvas, 0);
      }
    }

    public boolean doPause()
    {
      byte b;
      if (this.paused)
        b = 0;
      while (true)
      {
        this.paused = b;
        return b;
        b = 1;
      }
    }

    public int getWorldHeight()
    {
      return this.worldHeight;
    }

    public int getWorldWidth()
    {
      return this.worldWidth;
    }

    public void loadResources()
    {
      Resources localResources = paramContext.getResources();
      Bitmap.Config localConfig = Bitmap.Config.ARGB_4444;
      InfiniteGameActivity.bg = BitmapFactory.decodeResource(localResources, 2130837558);
      int[] arrayOfInt = new int[2];
      arrayOfInt[0] = 3;
      arrayOfInt[1] = 5;
      InfiniteGameActivity.ants = (Bitmap[][])Array.newInstance(Bitmap.class, arrayOfInt);
      byte b = 0;
      if (b >= 3)
        return;
      int i = 0;
      while (true)
      {
        while (i >= 5)
          ++b;
        if (InfiniteGameActivity.ants[b][i] == null)
        {
          InfiniteGameActivity.ants[b][i] = BitmapFactory.decodeResource(localResources, i + 2130837505 + b * 5);
          InfiniteGameActivity.ants[b][i] = InfiniteGameActivity.ants[b][i].copy(localConfig, false);
        }
        ++i;
      }
    }

    public void onSensorChanged()
    {
      this.direction = (int)((5.0F + -paramSensorEvent.values[2]) / 2F);
    }

    boolean onTouchEvent()
    {
      return false;
    }

    public void run()
    {
      label53: SurfaceHolder localSurfaceHolder;
      if (this.paused);
      this.counter = (1L + this.counter);
      Object localObject1 = null;
      try
      {
        while (true)
        {
          do
          {
            Canvas localCanvas = this.mSurfaceHolder.lockCanvas(null);
            localObject1 = localCanvas;
            if (localObject1 != null)
              break label53;
          }
          while (localObject1 == null);
          this.mSurfaceHolder.unlockCanvasAndPost(localObject1);
        }
        label110: localSurfaceHolder = this.mSurfaceHolder;
      }
      finally
      {
        long l3;
        try
        {
          long l1 = SystemClock.uptimeMillis();
          doDraw(localObject1);
          updatePhysics();
          long l2 = SystemClock.uptimeMillis();
          l3 = l2 - l1;
          if (l3 > 32);
        }
        finally
        {
          long l4;
          try
          {
            while (true)
            {
              do
              {
                Thread.sleep(l4);
                monitorexit;
              }
              while (localObject1 == null);
              this.mSurfaceHolder.unlockCanvasAndPost(localObject1);
            }
            localObject3 = finally;
            monitorexit;
            throw localObject3;
            localObject2 = finally;
            if (localObject1 != null)
              this.mSurfaceHolder.unlockCanvasAndPost(localObject1);
            throw localObject2;
          }
          catch (InterruptedException localInterruptedException)
          {
            break label110:
          }
        }
      }
    }

    public void setWorldBounds(, int paramInt2)
    {
      this.worldWidth = paramInt1;
      this.worldHeight = paramInt2;
      this.this$0.scaleX = (paramInt1 / 320.0F);
      this.this$0.scaleY = (paramInt2 / 480.0F);
    }

    public void updatePhysics()
    {
      byte b3;
      byte b1 = 0;
      byte b2 = 0;
      if (b2 >= 10)
        if ((b1 < 10) && (!(this.counter % 25 > 0L)))
          b3 = 0;
      while (true)
      {
        if (b3 >= 10);
        while (true)
        {
          return;
          InfiniteGameActivityView.Enemy localEnemy1 = (InfiniteGameActivityView.Enemy)this.this$0.enemies.get(b2);
          switch (localEnemy1.state)
          {
          default:
          case 0:
          case 1:
          }
          while (true)
          {
            do
              while (true)
              {
                while (true)
                  ++b2;
                localEnemy1.update();
                ++b1;
              }
            while (localEnemy1.dyingcounter <= 3);
            localEnemy1.state = 2;
          }
          InfiniteGameActivityView.Enemy localEnemy2 = (InfiniteGameActivityView.Enemy)this.this$0.enemies.get(b3);
          if (localEnemy2.state != 2)
            break;
          localEnemy2.state = 0;
          localEnemy2.mBitmap.setPosition(160, 500);
        }
        ++b3;
      }
    }
  }
}