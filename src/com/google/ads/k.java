package com.google.ads;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.webkit.WebView;
import com.google.ads.util.a;
import java.util.HashMap;

public final class k
  implements j
{
  public final void a(d paramd, HashMap<String, String> paramHashMap, WebView paramWebView)
  {
    String str1 = (String)paramHashMap.get("urls");
    if (str1 == null)
      com.google.ads.util.a.e("Could not get the urls param from canOpenURLs gmsg.");
    while (true)
    {
      return;
      String[] arrayOfString1 = str1.split(",");
      HashMap localHashMap = new HashMap();
      PackageManager localPackageManager = paramWebView.getContext().getPackageManager();
      int i = arrayOfString1.length;
      int j = 0;
      if (j < i)
      {
        String str4;
        int k;
        String str2 = arrayOfString1[j];
        String[] arrayOfString2 = str2.split(";", 2);
        String str3 = arrayOfString2[0];
        if (arrayOfString2.length >= 2)
        {
          str4 = arrayOfString2[1];
          if (localPackageManager.resolveActivity(new Intent(str4, Uri.parse(str3)), 65536) == null)
            break label153;
          k = 1;
        }
        while (true)
        {
          while (true)
          {
            while (true)
            {
              localHashMap.put(str2, Boolean.valueOf(k));
              ++j;
            }
            str4 = "android.intent.action.VIEW";
          }
          label153: byte b = 0;
        }
      }
      a.a(paramWebView, localHashMap);
    }
  }
}