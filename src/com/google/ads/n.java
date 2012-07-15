package com.google.ads;

import android.webkit.WebView;
import com.google.ads.util.a;
import java.util.HashMap;

public final class n
  implements j
{
  public final void a(d paramd, HashMap<String, String> paramHashMap, WebView paramWebView)
  {
    AdActivity localAdActivity;
    String str = (String)paramHashMap.get("js");
    if (str == null)
      com.google.ads.util.a.b("Could not get the JS to evaluate.");
    if (paramWebView instanceof h)
    {
      localAdActivity = ((h)paramWebView).b();
      if (localAdActivity != null)
        break label56;
      com.google.ads.util.a.b("Could not get the AdActivity from the AdWebView.");
    }
    while (true)
    {
      label56: h localh;
      while (true)
      {
        while (true)
        {
          return;
          com.google.ads.util.a.b("Trying to evaluate JS in a WebView that isn't an AdWebView");
        }
        localh = localAdActivity.getOpeningAdWebView();
        if (localh != null)
          break;
        com.google.ads.util.a.b("Could not get the opening WebView.");
      }
      a.a(localh, str);
    }
  }
}