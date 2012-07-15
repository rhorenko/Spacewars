package com.bestcoolfungames.imagecropper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.bestcoolfungames.util.Util;

public class CropImageView extends View
{
  static final int AREA_CENTER = 5;
  static final int AREA_LOWER_LEFT = 3;
  static final int AREA_LOWER_RIGHT = 4;
  static final int AREA_UPPER_LEFT = 1;
  static final int AREA_UPPER_RIGHT = 2;
  private Bitmap bitmap;
  private int centerX;
  private int centerY;
  boolean cropped;
  private final int diffBetweenSquares;
  private Rect imageRealRect;
  private Rect imageScreenRect;
  private Rect imageSelectionRect;
  boolean moveBottom;
  boolean moveLeft;
  boolean moveRight;
  boolean moveTop;
  private Paint paint;
  private int selectionSize;
  public int touchedArea = 0;

  public CropImageView(Context paramContext)
  {
    super(paramContext);
    this.diffBetweenSquares = 70;
    this.cropped = false;
    LoadMe();
  }

  public CropImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.diffBetweenSquares = 70;
    this.cropped = false;
    LoadMe();
  }

  public CropImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.diffBetweenSquares = 70;
    this.cropped = false;
    LoadMe();
  }

  private void LoadMe()
  {
    this.paint = new Paint();
    this.imageScreenRect = new Rect();
    this.imageRealRect = new Rect();
    this.imageSelectionRect = new Rect();
    this.selectionSize = 20;
  }

  private void onTouchEventActionDown(int paramInt1, int paramInt2)
  {
    if ((paramInt1 >= this.imageSelectionRect.left) && (paramInt1 <= this.imageSelectionRect.left + this.selectionSize) && (paramInt2 >= this.imageSelectionRect.top) && (paramInt2 <= this.imageSelectionRect.top + this.selectionSize))
      this.diffBetweenSquares = 1;
    while (true)
    {
      do
        while (true)
        {
          while (true)
          {
            while (true)
            {
              return;
              if ((paramInt1 < this.imageSelectionRect.right - this.selectionSize) || (paramInt1 > this.imageSelectionRect.right) || (paramInt2 < this.imageSelectionRect.top) || (paramInt2 > this.imageSelectionRect.top + this.selectionSize))
                break;
              this.diffBetweenSquares = 2;
            }
            if ((paramInt1 < this.imageSelectionRect.left) || (paramInt1 > this.imageSelectionRect.left + this.selectionSize) || (paramInt2 < this.imageSelectionRect.bottom - this.selectionSize) || (paramInt2 > this.imageSelectionRect.bottom))
              break;
            this.diffBetweenSquares = 3;
          }
          if ((paramInt1 < this.imageSelectionRect.right - this.selectionSize) || (paramInt1 > this.imageSelectionRect.right) || (paramInt2 < this.imageSelectionRect.bottom - this.selectionSize) || (paramInt2 > this.imageSelectionRect.bottom))
            break;
          this.diffBetweenSquares = 4;
        }
      while ((paramInt1 < this.imageSelectionRect.left + this.selectionSize) || (paramInt1 > this.imageSelectionRect.right - this.selectionSize) || (paramInt2 < this.imageSelectionRect.top + this.selectionSize) || (paramInt2 > this.imageSelectionRect.bottom - this.selectionSize));
      this.centerX = paramInt1;
      this.centerY = paramInt2;
      this.diffBetweenSquares = 5;
    }
  }

  private void onTouchEventActionUp(int paramInt1, int paramInt2)
  {
    switch (this.diffBetweenSquares)
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
      while (true)
      {
        while (true)
        {
          while (true)
          {
            while (true)
            {
              if (this.imageSelectionRect.top < this.imageScreenRect.top)
                this.imageSelectionRect.top = this.imageScreenRect.top;
              if (this.imageSelectionRect.bottom > this.imageScreenRect.bottom)
                this.imageSelectionRect.bottom = this.imageScreenRect.bottom;
              if (this.imageSelectionRect.left < this.imageScreenRect.left)
                this.imageSelectionRect.left = this.imageScreenRect.left;
              if (this.imageSelectionRect.right > this.imageScreenRect.right)
                this.imageSelectionRect.right = this.imageScreenRect.right;
              if ((this.imageSelectionRect.right < 70 + this.imageSelectionRect.left) && (((this.diffBetweenSquares == 2) || (this.diffBetweenSquares == 4))))
                this.imageSelectionRect.right = (70 + this.imageSelectionRect.left);
              if ((this.imageSelectionRect.bottom < 70 + this.imageSelectionRect.top) && (((this.diffBetweenSquares == 3) || (this.diffBetweenSquares == 4))))
                this.imageSelectionRect.bottom = (70 + this.imageSelectionRect.top);
              if ((this.imageSelectionRect.top > -70 + this.imageSelectionRect.bottom) && (((this.diffBetweenSquares == 1) || (this.diffBetweenSquares == 2))))
                this.imageSelectionRect.top = (-70 + this.imageSelectionRect.bottom);
              if ((this.imageSelectionRect.left > -70 + this.imageSelectionRect.right) && (((this.diffBetweenSquares == 1) || (this.diffBetweenSquares == 3))))
                this.imageSelectionRect.left = (-70 + this.imageSelectionRect.right);
              return;
              this.imageSelectionRect.top = paramInt2;
              this.imageSelectionRect.left = paramInt1;
            }
            this.imageSelectionRect.top = paramInt2;
            this.imageSelectionRect.right = paramInt1;
          }
          this.imageSelectionRect.bottom = paramInt2;
          this.imageSelectionRect.left = paramInt1;
        }
        this.imageSelectionRect.bottom = paramInt2;
        this.imageSelectionRect.right = paramInt1;
      }
    case 5:
    }
    int i = paramInt1 - this.centerX;
    int j = paramInt2 - this.centerY;
    if ((this.imageSelectionRect.bottom < this.imageScreenRect.bottom - j) && (this.imageSelectionRect.top > this.imageScreenRect.top - j))
    {
      Rect localRect7 = this.imageSelectionRect;
      localRect7.top = (j + localRect7.top);
      Rect localRect8 = this.imageSelectionRect;
      localRect8.bottom = (j + localRect8.bottom);
      if ((this.imageSelectionRect.right >= this.imageScreenRect.right - i) || (this.imageSelectionRect.left <= this.imageScreenRect.left - i))
        break label771;
      Rect localRect4 = this.imageSelectionRect;
      localRect4.left = (i + localRect4.left);
      Rect localRect5 = this.imageSelectionRect;
      localRect5.right = (i + localRect5.right);
    }
    while (true)
    {
      do
        while (true)
        {
          while (true)
          {
            do
              while (true)
              {
                while (true)
                {
                  this.centerX = paramInt1;
                  this.centerY = paramInt2;
                }
                if (this.imageSelectionRect.bottom < this.imageScreenRect.bottom - j)
                  break;
                Rect localRect6 = this.imageSelectionRect;
                localRect6.top = (localRect6.top + this.imageScreenRect.bottom - this.imageSelectionRect.bottom);
                this.imageSelectionRect.bottom = this.imageScreenRect.bottom;
              }
            while (this.imageSelectionRect.top > this.imageScreenRect.top - j);
            Rect localRect1 = this.imageSelectionRect;
            localRect1.bottom = (localRect1.bottom + this.imageScreenRect.top - this.imageSelectionRect.top);
            this.imageSelectionRect.top = this.imageScreenRect.top;
          }
          label771: if (this.imageSelectionRect.right < this.imageScreenRect.right - i)
            break;
          Rect localRect3 = this.imageSelectionRect;
          localRect3.left = (localRect3.left + this.imageScreenRect.right - this.imageSelectionRect.right);
          this.imageSelectionRect.right = this.imageScreenRect.right;
        }
      while (this.imageSelectionRect.left > this.imageScreenRect.left - i);
      Rect localRect2 = this.imageSelectionRect;
      localRect2.right = (localRect2.right + this.imageScreenRect.left - this.imageSelectionRect.left);
      this.imageSelectionRect.left = this.imageScreenRect.left;
    }
  }

  private void updateBitmap(int paramInt1, int paramInt2)
  {
    this.selectionSize = ((paramInt1 + paramInt2) / 30);
    if (this.bitmap != null)
    {
      this.imageScreenRect.right = paramInt1;
      this.imageScreenRect.bottom = (paramInt1 * this.bitmap.getHeight() / this.bitmap.getWidth());
      if (this.imageScreenRect.bottom > paramInt2)
      {
        this.imageScreenRect.bottom = paramInt2;
        this.imageScreenRect.right = (paramInt2 * this.bitmap.getWidth() / this.bitmap.getHeight());
      }
      int i = (paramInt1 - this.imageScreenRect.right) / 2;
      int j = (paramInt2 - this.imageScreenRect.bottom) / 2;
      this.imageScreenRect.left = i;
      this.imageScreenRect.top = j;
      Rect localRect1 = this.imageScreenRect;
      localRect1.right = (i + localRect1.right);
      Rect localRect2 = this.imageScreenRect;
      localRect2.bottom = (j + localRect2.bottom);
      this.imageRealRect.left = 0;
      this.imageRealRect.top = 0;
      this.imageRealRect.right = this.bitmap.getWidth();
      this.imageRealRect.bottom = this.bitmap.getHeight();
      this.imageSelectionRect.left = (this.imageScreenRect.left + this.imageScreenRect.width() / 4);
      this.imageSelectionRect.top = (this.imageScreenRect.top + this.imageScreenRect.height() / 4);
      this.imageSelectionRect.right = (this.imageScreenRect.right - this.imageScreenRect.width() / 4);
      this.imageSelectionRect.bottom = (this.imageScreenRect.bottom - this.imageScreenRect.height() / 4);
      invalidate();
    }
  }

  public void dealloc()
  {
    this.bitmap = null;
    this.paint = null;
    System.gc();
  }

  public Bitmap getCroppedBitmap()
  {
    int i = this.bitmap.getWidth();
    int j = this.bitmap.getHeight();
    float f1 = i / this.imageScreenRect.width();
    float f2 = j / this.imageScreenRect.height();
    int k = this.imageSelectionRect.right - this.imageSelectionRect.left;
    int l = this.imageSelectionRect.bottom - this.imageSelectionRect.top;
    int i1 = (int)(f1 * (this.imageSelectionRect.left - this.imageScreenRect.left));
    int i2 = (int)(f2 * (this.imageSelectionRect.top - this.imageScreenRect.top));
    int i3 = (int)(f1 * k);
    int i4 = (int)(f2 * l);
    if (i1 <= 0)
      i1 = 1;
    if (i2 <= 0)
      i2 = 1;
    if (i3 <= 0)
      i3 = 1;
    if (i4 <= 0)
      i4 = 1;
    this.bitmap = Util.createBitmap(this.bitmap, i1, i2, i3, i4);
    return this.bitmap;
  }

  public Rect getImageSelectionRect()
  {
    return this.imageSelectionRect;
  }

  public int max(int paramInt1, int paramInt2)
  {
    if (paramInt1 > paramInt2);
    while (true)
    {
      return paramInt1;
      paramInt1 = paramInt2;
    }
  }

  public int max(int paramInt1, int paramInt2, int paramInt3)
  {
    return max(paramInt1, max(paramInt2, paramInt3));
  }

  public int min(int paramInt1, int paramInt2)
  {
    if (paramInt1 < paramInt2);
    while (true)
    {
      return paramInt1;
      paramInt1 = paramInt2;
    }
  }

  public int min(int paramInt1, int paramInt2, int paramInt3)
  {
    return min(paramInt1, min(paramInt2, paramInt3));
  }

  public void onDraw(Canvas paramCanvas)
  {
    this.paint.setColor(-16777216);
    this.paint.setStyle(Paint.Style.FILL);
    paramCanvas.drawRect(0F, 0F, getWidth(), getHeight(), this.paint);
    if (this.bitmap != null)
    {
      paramCanvas.clipRect(0F, 0F, getWidth(), getHeight(), Region.Op.REPLACE);
      this.paint.setAlpha(128);
      paramCanvas.drawBitmap(this.bitmap, this.imageRealRect, this.imageScreenRect, this.paint);
      paramCanvas.clipRect(this.imageSelectionRect, Region.Op.REPLACE);
      this.paint.setAlpha(255);
      paramCanvas.drawBitmap(this.bitmap, this.imageRealRect, this.imageScreenRect, this.paint);
      this.paint.setColor(-1996488705);
      this.paint.setStyle(Paint.Style.FILL);
      float f1 = 1 + this.imageSelectionRect.left + this.selectionSize;
      float f2 = 1 + this.imageSelectionRect.top + this.selectionSize;
      paramCanvas.drawRect(this.imageSelectionRect.left, this.imageSelectionRect.top, f1, f2, this.paint);
      this.paint.setStyle(Paint.Style.STROKE);
      this.paint.setColor(-1442840576);
      float f3 = 1 + this.imageSelectionRect.left + this.selectionSize;
      float f4 = 1 + this.imageSelectionRect.top + this.selectionSize;
      paramCanvas.drawRect(this.imageSelectionRect.left, this.imageSelectionRect.top, f3, f4, this.paint);
      this.paint.setColor(-1996488705);
      this.paint.setStyle(Paint.Style.FILL);
      float f5 = 1 + this.imageSelectionRect.right - this.selectionSize;
      float f6 = 1 + this.imageSelectionRect.top + this.selectionSize;
      paramCanvas.drawRect(f5, this.imageSelectionRect.top, this.imageSelectionRect.right, f6, this.paint);
      this.paint.setStyle(Paint.Style.STROKE);
      this.paint.setColor(-1442840576);
      float f7 = 1 + this.imageSelectionRect.right - this.selectionSize;
      float f8 = 1 + this.imageSelectionRect.top + this.selectionSize;
      paramCanvas.drawRect(f7, this.imageSelectionRect.top, this.imageSelectionRect.right, f8, this.paint);
      this.paint.setColor(-1996488705);
      this.paint.setStyle(Paint.Style.FILL);
      float f9 = 1 + this.imageSelectionRect.bottom - this.selectionSize;
      float f10 = 1 + this.imageSelectionRect.left + this.selectionSize;
      paramCanvas.drawRect(this.imageSelectionRect.left, f9, f10, this.imageSelectionRect.bottom, this.paint);
      this.paint.setStyle(Paint.Style.STROKE);
      this.paint.setColor(-1442840576);
      float f11 = 1 + this.imageSelectionRect.bottom - this.selectionSize;
      float f12 = 1 + this.imageSelectionRect.left + this.selectionSize;
      paramCanvas.drawRect(this.imageSelectionRect.left, f11, f12, this.imageSelectionRect.bottom, this.paint);
      this.paint.setColor(-1996488705);
      this.paint.setStyle(Paint.Style.FILL);
      paramCanvas.drawRect(1 + this.imageSelectionRect.right - this.selectionSize, 1 + this.imageSelectionRect.bottom - this.selectionSize, this.imageSelectionRect.right, this.imageSelectionRect.bottom, this.paint);
      this.paint.setStyle(Paint.Style.STROKE);
      this.paint.setColor(-1442840576);
      paramCanvas.drawRect(1 + this.imageSelectionRect.right - this.selectionSize, 1 + this.imageSelectionRect.bottom - this.selectionSize, this.imageSelectionRect.right, this.imageSelectionRect.bottom, this.paint);
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    setBitmap(this.bitmap);
  }

  public void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt1 > 0)
      updateBitmap(paramInt1, paramInt2);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = (int)paramMotionEvent.getX();
    int j = (int)paramMotionEvent.getY();
    switch (paramMotionEvent.getAction())
    {
    default:
    case 1:
    case 2:
    case 0:
    }
    while (true)
    {
      while (true)
      {
        while (true)
        {
          return true;
          this.diffBetweenSquares = 0;
        }
        onTouchEventActionUp(i, j);
        invalidate();
      }
      onTouchEventActionDown(i, j);
    }
  }

  public void rotate()
  {
    Matrix localMatrix = new Matrix();
    localMatrix.postRotate(90.0F);
    this.bitmap = Util.createBitmap(this.bitmap, 0, 0, this.bitmap.getWidth(), this.bitmap.getHeight(), localMatrix, true);
    updateBitmap(getWidth(), getHeight());
  }

  public void setBitmap(Bitmap paramBitmap)
  {
    this.bitmap = paramBitmap;
    int i = getWidth();
    int j = getHeight();
    if ((i > 0) && (j > 0) && (!(this.cropped)))
    {
      this.cropped = true;
      int k = i;
      int l = j;
      if (k < l)
      {
        int i1 = k;
        k = l;
        l = i1;
      }
      if (paramBitmap != null)
      {
        this.bitmap = Bitmap.createScaledBitmap(paramBitmap, k, l, true);
        paramBitmap.recycle();
      }
      updateBitmap(i, j);
    }
  }
}