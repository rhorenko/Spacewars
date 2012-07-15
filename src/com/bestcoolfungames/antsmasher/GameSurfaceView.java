package com.bestcoolfungames.antsmasher;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView
  implements SurfaceHolder.Callback
{
  private GameSurface gameSurface;

  public GameSurfaceView(Context paramContext)
  {
    super(paramContext);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return this.gameSurface.thread.onTouchEvent(paramMotionEvent);
  }

  public void setGameSurface(GameSurface paramGameSurface)
  {
    this.gameSurface = paramGameSurface;
  }

  public void setSurfaceSize(int paramInt1, int paramInt2)
  {
    this.gameSurface.getThread().setSurfaceSize(paramInt1, paramInt2);
  }

  public void stop()
  {
    try
    {
      if (this.gameSurface != null)
        this.gameSurface.thread.join();
      label17: return;
    }
    catch (InterruptedException localInterruptedException)
    {
      break label17:
    }
  }

  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.gameSurface != null)
      this.gameSurface.thread.setSurfaceSize(paramInt2, paramInt3);
  }

  public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    try
    {
      if (!(this.gameSurface.thread.isAlive()))
        this.gameSurface.thread.start();
      label23: return;
    }
    catch (IllegalThreadStateException localIllegalThreadStateException)
    {
      break label23:
    }
  }

  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
  }
}