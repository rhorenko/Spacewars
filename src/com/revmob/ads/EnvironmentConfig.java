package com.revmob.ads;

import android.content.Context;
import android.telephony.TelephonyManager;

public class EnvironmentConfig
{
  private static final String MOBILE_ID_DEBUG_MODE = "355031040373919";
  private static final String PRODUCTION_SERVER_ADDRESS = "android.bcfads.com";
  public static final String SDK_VERSION = "3.1.1";
  private static final String STAGING_SERVER_ADDRESS = "staging.bcfads.com";
  public static boolean debugMode = false;

  public static String getFullServerUrl(String paramString1, String paramString2)
  {
    return paramString1 + "://" + getServerAddress() + "/" + paramString2;
  }

  public static String getMobileId(Context paramContext)
  {
    String str;
    if (debugMode)
      str = "355031040373919";
    while (true)
    {
      return str;
      str = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
    }
  }

  public static String getServerAddress()
  {
    String str;
    if (debugMode)
      str = "staging.bcfads.com";
    while (true)
    {
      return str;
      str = "android.bcfads.com";
    }
  }

  public static boolean isDebugMode()
  {
    return debugMode;
  }

  public static void setDebugMode(boolean paramBoolean)
  {
    debugMode = paramBoolean;
  }
}