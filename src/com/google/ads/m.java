package com.google.ads;

import android.webkit.WebView;
import com.google.ads.util.a;
import java.util.HashMap;

public final class m
  implements j
{
  public final void a(d paramd, HashMap<String, String> paramHashMap, WebView paramWebView)
  {
    if (paramWebView instanceof h)
      ((h)paramWebView).a();
    while (true)
    {
      return;
      a.b("Trying to close WebView that isn't an AdWebView");
    }
  }
}