package com.google.ads;

import android.webkit.WebView;
import com.google.ads.util.a;
import java.util.HashMap;

public final class q
  implements j
{
  public final void a(d paramd, HashMap<String, String> paramHashMap, WebView paramWebView)
  {
    String str1 = (String)paramHashMap.get("url");
    String str2 = (String)paramHashMap.get("afma_notify_dt");
    boolean bool = "1".equals(paramHashMap.get("drt_include"));
    a.c("Received ad url: <\"url\": \"" + str1 + "\", \"afmaNotifyDt\": \"" + str2 + "\">");
    c localc = paramd.g();
    if (localc != null)
    {
      localc.a(bool);
      localc.b(str1);
    }
  }
}