package com.google.ads;

import android.content.Context;
import android.net.Uri;
import android.webkit.WebView;
import com.google.ads.util.a;
import java.util.HashMap;
import java.util.Locale;

public final class l
  implements j
{
  public final void a(d paramd, HashMap<String, String> paramHashMap, WebView paramWebView)
  {
    String str1 = (String)paramHashMap.get("u");
    if (str1 == null)
      a.e("Could not get URL from click gmsg.");
    while (true)
    {
      return;
      f localf = paramd.l();
      if (localf != null)
      {
        Uri localUri = Uri.parse(str1);
        String str2 = localUri.getHost();
        if ((str2 != null) && (str2.toLowerCase(Locale.US).endsWith(".admob.com")))
        {
          String str3 = null;
          String str4 = localUri.getPath();
          if (str4 != null)
          {
            String[] arrayOfString = str4.split("/");
            if (arrayOfString.length >= 4)
              str3 = arrayOfString[2] + "/" + arrayOfString[3];
          }
          localf.b(str3);
        }
      }
      new Thread(new w(str1, paramWebView.getContext().getApplicationContext())).start();
    }
  }
}