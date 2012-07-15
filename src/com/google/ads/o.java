package com.google.ads;

import android.content.Context;
import android.webkit.WebView;
import com.google.ads.util.a;
import java.util.HashMap;

public final class o
  implements j
{
  public final void a(d paramd, HashMap<String, String> paramHashMap, WebView paramWebView)
  {
    String str = (String)paramHashMap.get("u");
    if (str == null)
      a.e("Could not get URL from click gmsg.");
    while (true)
    {
      return;
      new Thread(new w(str, paramWebView.getContext().getApplicationContext())).start();
    }
  }
}