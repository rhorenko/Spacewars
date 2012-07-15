package com.bestcoolfungames.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class Helper
{
  private static Context context = null;

  public static int dipToPx(int paramInt)
  {
    return (int)(paramInt * getContext().getResources().getDisplayMetrics().density);
  }

  public static void fixViewScale(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2)
  {
    RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)paramView.getLayoutParams();
    if (paramInt3 > 0)
    {
      localLayoutParams.leftMargin = (int)(paramFloat1 * paramInt3);
      if (paramInt4 <= 0)
        break label99;
      localLayoutParams.topMargin = (int)(paramFloat2 * paramInt4);
    }
    while (true)
    {
      label99: 
      do
        while (true)
        {
          do
          {
            if (paramInt1 != 0)
              localLayoutParams.width = (int)(paramFloat2 * paramInt1);
            if (paramInt2 != 0)
              localLayoutParams.height = (int)(paramFloat2 * paramInt2);
            paramView.setLayoutParams(localLayoutParams);
            return;
          }
          while (paramInt3 >= 0);
          localLayoutParams.rightMargin = (int)(-1.0F * paramFloat1 * paramInt3);
        }
      while (paramInt4 >= 0);
      localLayoutParams.bottomMargin = (int)(-1.0F * paramFloat2 * paramInt4);
    }
  }

  public static String getAppVersion()
  {
    String str;
    try
    {
      str = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
      {
        localNameNotFoundException.printStackTrace();
        ??? = null;
      }
    }
  }

  public static Context getContext()
  {
    if (context == null)
      throw new NullPointerException();
    return context;
  }

  public static double getScaleToFitWidthAndHeight(View paramView, int paramInt1, int paramInt2)
  {
    double d = 1D;
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    int i = localLayoutParams.width;
    int l = localLayoutParams.height;
    byte b = 0;
    if ((localLayoutParams.width <= paramInt1) && (localLayoutParams.height <= paramInt2))
      b = 1;
    if (b != 0)
      if (paramInt2 == 0)
        if (i >= paramInt1);
    while (true)
    {
      int j;
      int k;
      int i1;
      int i2;
      while (true)
      {
        while (true)
        {
          return d;
          d = d + 0.01D;
          j = (int)(d * localLayoutParams.width);
          (int)(d * localLayoutParams.height);
        }
        if (paramInt1 == 0)
          while (true)
          {
            if (l >= paramInt2);
            d = d + 0.01D;
            (int)(d * localLayoutParams.width);
            i1 = (int)(d * localLayoutParams.height);
          }
        do
        {
          d = d + 0.01D;
          j = (int)(d * localLayoutParams.width);
          i1 = (int)(d * localLayoutParams.height);
        }
        while ((j < paramInt1) || (i1 < paramInt2));
      }
      if (paramInt2 == 0)
        while (true)
        {
          if (j <= paramInt1);
          d = d - 0.01D;
          k = (int)(d * localLayoutParams.width);
          (int)(d * localLayoutParams.height);
        }
      if (paramInt1 == 0)
        while (true)
        {
          if (i1 <= paramInt2);
          d = d - 0.01D;
          (int)(d * localLayoutParams.width);
          i2 = (int)(d * localLayoutParams.height);
        }
      do
        do
        {
          d = d - 0.01D;
          k = (int)(d * localLayoutParams.width);
          i2 = (int)(d * localLayoutParams.height);
        }
        while (k <= paramInt1);
      while (i2 > paramInt2);
    }
  }

  public static int pxToDip(int paramInt)
  {
    return (int)(paramInt / getContext().getResources().getDisplayMetrics().density);
  }

  public static float rescaleToFit(View paramView, int paramInt1, int paramInt2)
  {
    float f = (float)getScaleToFitWidthAndHeight(paramView, paramInt1, paramInt2);
    rescaleView(paramView, f);
    return f;
  }

  public static void rescaleView(View paramView, float paramFloat)
  {
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    localLayoutParams.width = (int)(paramFloat * localLayoutParams.width);
    localLayoutParams.height = (int)(paramFloat * localLayoutParams.height);
    paramView.setLayoutParams(localLayoutParams);
    paramView.requestLayout();
  }

  public static void setContext(Context paramContext)
  {
    context = paramContext;
  }

  public class TextViewResizer
  {
    private int canvasHeight;
    private int canvasWidth;
    private Typeface typeFace;

    public void resizeTextView(, int paramInt1, int paramInt2)
    {
      this.typeFace = paramTextView.getTypeface();
      this.canvasWidth = paramInt1;
      this.canvasHeight = paramInt2;
      paramTextView.setTextSize(textSizeFromBounds(paramTextView.getText().toString(), this.canvasWidth, this.canvasHeight));
    }

    public int textSizeFromBounds(, int paramInt1, int paramInt2)
    {
      Paint localPaint = new Paint();
      Rect localRect = new Rect();
      localPaint.setTypeface(this.typeFace);
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

  public static final class UpdateCheck
  {
    public static final String from_to_format = "About_Updated_from_v%s_to_v%s";
    public static final String param1 = "About";
    public static final String param2 = "About_Updated";
    public static String param3 = null;

    public static boolean didUpdateApp()
    {
      byte b = 1;
      SharedPreferences localSharedPreferences = Helper.getContext().getSharedPreferences("PushNotification", 0);
      SharedPreferences.Editor localEditor = localSharedPreferences.edit();
      String str1 = localSharedPreferences.getString("AppVersion", null);
      if (str1 == null)
        str1 = "0.0.00";
      String str2 = Helper.getAppVersion();
      Log.i("AppVersion", "Old: " + str1 + " New: " + str2);
      if (!(str1.equals(str2)))
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = str1;
        arrayOfObject[b] = str2;
        param3 = String.format("About_Updated_from_v%s_to_v%s", arrayOfObject);
        localEditor.putString("AppVersion", str2);
        localEditor.commit();
        Log.i("AppVersion", "App updated: " + param3);
      }
      while (true)
      {
        return b;
        Log.i("AppVersion", "App not updated: " + param3);
        b = 0;
      }
    }
  }
}