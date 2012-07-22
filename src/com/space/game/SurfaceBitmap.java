package com.space.game;

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
  
  public SurfaceBitmap()
  {
    mBitmap=null;
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
  
  private int centerX()
  {
    int i=-1;
    if (mBitmap != null) i = getLeft() + getWidth() / 2;    
     return i;      
  }

  private int centerY()
  {
    int i=-1;
    if (mBitmap != null) i = getTop() + getHeight() / 2;    
     return i;  
  }
  
  public int getHeight()
  {
    int i=-1;
    if (mBitmap != null) i = mBitmap.getHeight();   
     return i;  
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
    int i=-1;
    if (mBitmap != null) i = mBitmap.getWidth();  
    return i;
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