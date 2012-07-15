package com.google.android.apps.analytics;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class AnalyticsParameterEncoder
{
  public static String encode(String paramString)
  {
    return encode(paramString, "UTF-8");
  }

  static String encode(String paramString1, String paramString2)
  {
    String str;
    try
    {
      str = URLEncoder.encode(paramString1, paramString2).replace("+", "%20");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new AssertionError("URL encoding failed for: " + paramString1);
    }
  }
}