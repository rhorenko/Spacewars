package com.google.ads;

import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.webkit.WebView;
import com.google.ads.util.AdUtil;
import com.google.ads.util.a;
import java.util.HashMap;

public final class v
  implements j
{
  private static int a(HashMap<String, String> paramHashMap, String paramString, int paramInt, DisplayMetrics paramDisplayMetrics)
  {
    String str = (String)paramHashMap.get(paramString);
    if (str != null);
    try
    {
      float f = TypedValue.applyDimension(1, Integer.parseInt(str), paramDisplayMetrics);
      paramInt = (int)f;
      return paramInt;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (true)
        com.google.ads.util.a.a("Could not parse \"" + paramString + "\" in a video gmsg: " + str);
    }
  }

  public final void a(d paramd, HashMap<String, String> paramHashMap, WebView paramWebView)
  {
    label63: g localg;
    String str2;
    String str1 = (String)paramHashMap.get("action");
    if (str1 == null)
      com.google.ads.util.a.a("No \"action\" parameter in a video gmsg.");
    while (true)
    {
      while (true)
      {
        while (true)
        {
          label189: String str3;
          while (true)
          {
            while (true)
            {
              AdActivity localAdActivity;
              while (true)
              {
                h localh;
                while (true)
                {
                  int i;
                  int j;
                  int k;
                  int l;
                  while (true)
                  {
                    while (true)
                    {
                      while (true)
                      {
                        return;
                        if (!(paramWebView instanceof h))
                          break;
                        localh = (h)paramWebView;
                        localAdActivity = localh.b();
                        if (localAdActivity != null)
                          break label63;
                        com.google.ads.util.a.a("Could not get adActivity for a video gmsg.");
                      }
                      com.google.ads.util.a.a("Could not get adWebView for a video gmsg.");
                    }
                    boolean bool1 = str1.equals("new");
                    boolean bool2 = str1.equals("position");
                    if ((!(bool1)) && (!(bool2)))
                      break label189;
                    DisplayMetrics localDisplayMetrics1 = AdUtil.a(localAdActivity);
                    i = a(paramHashMap, "x", 0, localDisplayMetrics1);
                    j = a(paramHashMap, "y", 0, localDisplayMetrics1);
                    k = a(paramHashMap, "w", -1, localDisplayMetrics1);
                    l = a(paramHashMap, "h", -1, localDisplayMetrics1);
                    if ((!(bool1)) || (localAdActivity.getAdVideoView() != null))
                      break;
                    localAdActivity.newAdVideoView(i, j, k, l);
                  }
                  localAdActivity.moveAdVideoView(i, j, k, l);
                }
                localg = localAdActivity.getAdVideoView();
                if (localg != null)
                  break;
                a.a(localh, "onVideoEvent", "{'event': 'error', 'what': 'no_video_view'}");
              }
              if (!(str1.equals("click")))
                break;
              DisplayMetrics localDisplayMetrics2 = AdUtil.a(localAdActivity);
              int i1 = a(paramHashMap, "x", 0, localDisplayMetrics2);
              int i2 = a(paramHashMap, "y", 0, localDisplayMetrics2);
              long l1 = SystemClock.uptimeMillis();
              localg.a(MotionEvent.obtain(l1, l1, 0, i1, i2, 0));
            }
            if (!(str1.equals("controls")))
              break label342;
            str3 = (String)paramHashMap.get("enabled");
            if (str3 != null)
              break;
            com.google.ads.util.a.a("No \"enabled\" parameter in a controls video gmsg.");
          }
          if (!(str3.equals("true")))
            break;
          localg.a(true);
        }
        localg.a(false);
      }
      label342: if (!(str1.equals("currentTime")))
        break;
      str2 = (String)paramHashMap.get("time");
      if (str2 != null)
        break;
      com.google.ads.util.a.a("No \"time\" parameter in a currentTime video gmsg.");
    }
    try
    {
      localg.a((int)(1000.0F * Float.parseFloat(str2)));
    }
    catch (NumberFormatException localNumberFormatException)
    {
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
                while (true)
                {
                  while (true)
                  {
                    while (true)
                      com.google.ads.util.a.a("Could not parse \"time\" parameter: " + str2);
                    if (!(str1.equals("hide")))
                      break;
                    localg.setVisibility(4);
                  }
                  if (!(str1.equals("load")))
                    break;
                  localg.a();
                }
                if (!(str1.equals("pause")))
                  break;
                localg.b();
              }
              if (!(str1.equals("play")))
                break;
              localg.c();
            }
            if (!(str1.equals("show")))
              break;
            localg.setVisibility(0);
          }
          if (!(str1.equals("src")))
            break;
          localg.a((String)paramHashMap.get("src"));
        }
        com.google.ads.util.a.a("Unknown video action: " + str1);
      }
    }
  }
}