package com.google.ads.util;

import android.util.Log;

public final class a
{
  public static void a(String paramString)
  {
    if (a("Ads", 3))
      Log.d("Ads", paramString);
  }

  public static void a(String paramString, Throwable paramThrowable)
  {
    if (a("Ads", 6))
      Log.e("Ads", paramString, paramThrowable);
  }

  public static void a(Throwable paramThrowable)
  {
    if (a("Ads", 5))
      Log.w("Ads", paramThrowable);
  }

  private static boolean a(String paramString, int paramInt)
  {
    byte b2;
    byte b1 = 0;
    if (paramInt >= 5)
      b2 = 1;
    while (true)
    {
      if ((b2 != 0) || (Log.isLoggable(paramString, paramInt)))
        b1 = 1;
      return b1;
      b2 = 0;
    }
  }

  public static void b(String paramString)
  {
    if (a("Ads", 6))
      Log.e("Ads", paramString);
  }

  public static void b(String paramString, Throwable paramThrowable)
  {
    if (a("Ads", 5))
      Log.w("Ads", paramString, paramThrowable);
  }

  public static void c(String paramString)
  {
    if (a("Ads", 4))
      Log.i("Ads", paramString);
  }

  public static void d(String paramString)
  {
    if (a("Ads", 2))
      Log.v("Ads", paramString);
  }

  public static void e(String paramString)
  {
    if (a("Ads", 5))
      Log.w("Ads", paramString);
  }
}