package com.bestcoolfungames.antsmasher;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class SurfaceBitmap
{
  private int cX;
  private int cY;
  private int left;
  private Bitmap mBitmap;
  private int top;

  public SurfaceBitmap(Bitmap paramBitmap)
  {
    setBitmap(paramBitmap);
  }

  private int centerX()
  {
    int i;
    if (this.mBitmap != null)
      i = getLeft() + getWidth() / 2;
    while (true)
    {
      return i;
      i = -1;
    }
  }

  private int centerY()
  {
    int i;
    if (this.mBitmap != null)
      i = getTop() + getHeight() / 2;
    while (true)
    {
      return i;
      i = -1;
    }
  }

  public Bitmap bitmap()
  {
    return this.mBitmap;
  }

  public void draw(Canvas paramCanvas, int paramInt)
  {
    draw(paramCanvas, paramInt, null);
  }

  public void draw(Canvas paramCanvas, int paramInt, Paint paramPaint)
  {
    paramCanvas.save();
    paramCanvas.rotate(paramInt, this.cX, this.cY);
    paramCanvas.drawBitmap(this.mBitmap, this.left, this.top, paramPaint);
    paramCanvas.restore();
  }

  public void draw(Canvas paramCanvas, Paint paramPaint)
  {
    draw(paramCanvas, 0, paramPaint);
  }

  public int getHeight()
  {
    int i;
    if (this.mBitmap != null)
      i = this.mBitmap.getHeight();
    while (true)
    {
      return i;
      i = -1;
    }
  }

  public int getLeft()
  {
    return this.left;
  }

  public int getTop()
  {
    return this.top;
  }

  public int getWidth()
  {
    int i;
    if (this.mBitmap != null)
      i = this.mBitmap.getWidth();
    while (true)
    {
      return i;
      i = -1;
    }
  }

  public void setBitmap(Bitmap paramBitmap)
  {
    this.mBitmap = paramBitmap;
  }

  public void setPosition(int paramInt1, int paramInt2)
  {
    this.top = paramInt2;
    this.left = paramInt1;
    this.cX = centerX();
    this.cY = centerY();
  }
}