package com.bestcoolfungames.antsmasher;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.widget.TextView;

public class TextViewResizer
{
  private static int canvasHeight;
  private static int canvasWidth;
  private static Typeface typeFace;

  public static void resizeTextView(TextView paramTextView, int paramInt1, int paramInt2)
  {
    typeFace = paramTextView.getTypeface();
    canvasWidth = paramInt1;
    canvasHeight = paramInt2;
    paramTextView.setTextSize(textSizeFromBounds(paramTextView.getText().toString(), canvasWidth, canvasHeight));
  }

  public static int textSizeFromBounds(String paramString, int paramInt1, int paramInt2)
  {
    Paint localPaint = new Paint();
    Rect localRect = new Rect();
    localPaint.setTypeface(typeFace);
    int i = 1;
    byte b = 0;
    while (true)
    {
      int j;
      int k;
      do
      {
        if (b != 0)
          return i;
        localPaint.setTextSize(i);
        localPaint.getTextBounds(paramString, 0, paramString.length(), localRect);
        j = localRect.height();
        k = localRect.width();
        ++i;
      }
      while ((paramInt2 - paramInt2 / 20 > j) && (paramInt1 - paramInt1 / 20 > k));
      b = 1;
    }
  }
}