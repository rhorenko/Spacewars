package com.revmob.android;

import android.content.Context;

public class PermissionChecker
{
  public static boolean isPermissionEnabled(Context paramContext, String paramString)
  {
    byte b;
    if (paramContext.checkCallingOrSelfPermission("android.permission." + paramString) == 0)
      b = 1;
    while (true)
    {
      return b;
      b = 0;
    }
  }
}