package com.space.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 
 * @author Roman Gorenko
 * 
 * This class contains bitmap for different game objects  (ship, big ship, mine, up, life)
 *
 */

public class SurfaceBitmap
{
  private int cX;
  private int cY;
  private int left;
  private int top;
  private Bitmap mBitmap;
  
  /**
   * Constructor
   * @param bitmap
   */
  public SurfaceBitmap(Bitmap bitmap)
  {
    setBitmap(bitmap);
  }
  
  public SurfaceBitmap()
  {
    mBitmap=null;
  }

  public void draw(Canvas paramCanvas, int paramInt)
  {
    draw(paramCanvas, paramInt, null);
  }

  public void draw(Canvas canvas, int degrees, Paint paint)
  {
    canvas.save();
    canvas.rotate(degrees, this.cX, this.cY);
    canvas.drawBitmap(this.mBitmap, this.left, this.top, paint);
    canvas.restore();
  }

  public void draw(Canvas canvas, Paint paint)
  {
    draw(canvas, 0, paint);
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

  public Bitmap getBitmap()
  {
    return this.mBitmap;
  }
  
  public void setBitmap(Bitmap bitmap)
  {
    this.mBitmap = bitmap;
  }

  public void setPosition(int paramLeft, int paramTop)
  {
    this.top = paramTop;
    this.left = paramLeft;
    this.cX = centerX();
    this.cY = centerY();
  }
  
}