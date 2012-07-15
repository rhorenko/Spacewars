package com.bestcoolfungames.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.bestcoolfungames.imagecropper.R.drawable;
import com.bestcoolfungames.imagecropper.R.string;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Util
{
  static final String DEBUGKEY = "get the debug key from logcat after calling the function below once from the emulator";
  private static Context context;
  public static boolean isDebug = false;

  static
  {
    context = null;
  }

  public static void DebugLog(String paramString)
  {
    if (isDebug)
      Log.d(getDebugInfo(), paramString);
  }

  public static void Log(String paramString)
  {
    Log.d(getDebugInfo(), paramString);
  }

  public static void Toast(String paramString)
  {
    Toast.makeText(getContext(), paramString, 0).show();
  }

  public static Bitmap createBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Bitmap localBitmap1;
    System.gc();
    try
    {
      Bitmap localBitmap2 = Bitmap.createBitmap(paramBitmap, paramInt1, paramInt2, paramInt3, paramInt4);
      localBitmap1 = localBitmap2;
      System.gc();
      return localBitmap1;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      Toast(getString(R.string.memory_warning));
      localBitmap1 = getDefaultBitmap();
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localBitmap1 = getWrongFileBitmap();
    }
    catch (Exception localException)
    {
      while (true)
        localBitmap1 = getWrongFileBitmap();
    }
  }

  public static Bitmap createBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Matrix paramMatrix, boolean paramBoolean)
  {
    Bitmap localBitmap1;
    System.gc();
    try
    {
      Bitmap localBitmap2 = Bitmap.createBitmap(paramBitmap, paramInt1, paramInt2, paramInt3, paramInt4, paramMatrix, paramBoolean);
      localBitmap1 = localBitmap2;
      System.gc();
      return localBitmap1;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      Toast(getString(R.string.memory_warning));
      localBitmap1 = getDefaultBitmap();
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localBitmap1 = getWrongFileBitmap();
    }
    catch (Exception localException)
    {
      while (true)
        localBitmap1 = getWrongFileBitmap();
    }
  }

  public static String createRandomString(int paramInt)
  {
    Random localRandom = new Random();
    StringBuilder localStringBuilder = new StringBuilder();
    while (true)
    {
      if (localStringBuilder.length() >= paramInt)
        return localStringBuilder.toString();
      localStringBuilder.append(Integer.toHexString(localRandom.nextInt()));
    }
  }

  public static int dipToPx(int paramInt)
  {
    return (int)(paramInt * getContext().getResources().getDisplayMetrics().density);
  }

  public static boolean firstTime()
  {
    byte b = 0;
    SharedPreferences localSharedPreferences = getContext().getSharedPreferences("PushNotification", 0);
    if (!(localSharedPreferences.getBoolean("firstTime", true)));
    while (true)
    {
      return b;
      SharedPreferences.Editor localEditor = localSharedPreferences.edit();
      localEditor.putBoolean("firstTime", false);
      localEditor.commit();
      b = 1;
    }
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

  public static HashSet<String> getAppHashSet()
  {
    HashSet localHashSet = new HashSet();
    PackageManager localPackageManager = getContext().getPackageManager();
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    Iterator localIterator = localPackageManager.queryIntentActivities(localIntent, 0).iterator();
    while (true)
    {
      String str2;
      do
      {
        String str1;
        int i;
        int j;
        do
        {
          do
          {
            if (!(localIterator.hasNext()))
              return localHashSet;
            str1 = ((ResolveInfo)localIterator.next()).toString();
            i = 1 + str1.indexOf(32);
          }
          while (i < 0);
          j = str1.indexOf(32, i);
        }
        while (j < 0);
        str2 = str1.substring(i, j);
      }
      while (localHashSet.contains(str2));
      localHashSet.add(str2);
    }
  }

  public static String getAppList()
  {
    String str;
    HashSet localHashSet = getAppHashSet();
    int i = localHashSet.size();
    if (i == 0)
    {
      str = "";
      return str;
    }
    StringBuilder localStringBuilder = new StringBuilder(i * 50);
    Iterator localIterator = localHashSet.iterator();
    while (true)
    {
      while (!(localIterator.hasNext()))
        str = localStringBuilder.substring(0, localStringBuilder.length());
      localStringBuilder.append((String)localIterator.next());
      localStringBuilder.append(",");
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

  public static String getDebugInfo()
  {
    StackTraceElement localStackTraceElement = new java.lang.Throwable().getStackTrace()[2];
    return "PushNotification:" + localStackTraceElement.getClassName() + ":" + localStackTraceElement.getMethodName() + ":" + localStackTraceElement.getLineNumber();
  }

  public static Bitmap getDefaultBitmap()
  {
    return BitmapFactory.decodeResource(getContext().getResources(), R.drawable.nomemory);
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

  public static String getScreenDensity()
  {
    String str;
    if (context == null)
      str = "DISPLAY_UNKNOWN";
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
              return str;
              switch (context.getResources().getDisplayMetrics().densityDpi)
              {
              default:
                str = "DISPLAY_UNKNOWN";
              case 120:
              case 160:
              case 240:
              case 320:
              }
            }
            str = "DENSITY_LOW";
          }
          str = "DENSITY_MEDIUM";
        }
        str = "DENSITY_HIGH";
      }
      str = "DENSITY_XHIGH";
    }
  }

  public static String getString(int paramInt)
  {
    return getContext().getString(paramInt);
  }

  public static String getUniqueId()
  {
    SharedPreferences localSharedPreferences;
    String str = Settings.Secure.getString(getContext().getContentResolver(), "android_id");
    if (str == null)
    {
      localSharedPreferences = getContext().getSharedPreferences("PushNotification", 0);
      str = localSharedPreferences.getString("uniqueId", null);
      if (str == null)
        break label44;
    }
    while (true)
    {
      return str;
      label44: str = "rnd" + createRandomString(12);
      SharedPreferences.Editor localEditor = localSharedPreferences.edit();
      localEditor.putString("uniqueId", str);
      localEditor.commit();
    }
  }

  public static Bitmap getWrongFileBitmap()
  {
    return BitmapFactory.decodeResource(getContext().getResources(), R.drawable.wrong_file_type);
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
    byte b;
    context = paramContext;
    ApplicationInfo localApplicationInfo = paramContext.getApplicationInfo();
    int i = 0x2 & localApplicationInfo.flags;
    localApplicationInfo.flags = i;
    if (i != 0)
      b = 1;
    while (true)
    {
      isDebug = b;
      return;
      b = 0;
    }
  }

  public static boolean signedWithDebugKey(Context paramContext, Class<?> paramClass)
  {
    byte b1 = 0;
    byte b2 = 0;
    while (true)
    {
      int i;
      try
      {
        ComponentName localComponentName = new ComponentName(paramContext, paramClass);
        Signature[] arrayOfSignature = getContext().getPackageManager().getPackageInfo(localComponentName.getPackageName(), 64).signatures;
        i = 0;
        if (i < arrayOfSignature.length)
          break label91;
        if ("get the debug key from logcat after calling the function below once from the emulator".equals(arrayOfSignature[0].toCharsString()))
        {
          b2 = 1;
          DebugLog("package has been signed with the debug key");
          break label87:
        }
        DebugLog("package signed with a key other than the debug key");
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        break label89:
      }
      label87: b1 = b2;
      label89: return b1;
      label91: ++i;
    }
  }

  public static boolean wasIinstalledWithAndroidMarket()
  {
    byte b;
    String str = getContext().getPackageManager().getInstallerPackageName(context.getPackageName());
    if (str == null)
      b = 0;
    while (true)
    {
      return b;
      boolean bool = str.equals("com.google.android.feedback");
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
      SharedPreferences localSharedPreferences = Util.getContext().getSharedPreferences("PushNotification", 0);
      SharedPreferences.Editor localEditor = localSharedPreferences.edit();
      String str1 = localSharedPreferences.getString("AppVersion", null);
      if (str1 == null)
        str1 = "0.0.00";
      String str2 = Util.getAppVersion();
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