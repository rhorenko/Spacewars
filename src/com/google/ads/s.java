package com.google.ads;

import android.webkit.WebView;
import com.google.ads.util.a;
import java.util.HashMap;

public final class s
  implements j
{
  public final void a(d paramd, HashMap<String, String> paramHashMap, WebView paramWebView)
  {
    String str1 = (String)paramHashMap.get("string");
    String str2 = (String)paramHashMap.get("afma_notify_dt");
    a.c("Received log message: <\"string\": \"" + str1 + "\", \"afmaNotifyDt\": \"" + str2 + "\">");
  }
}