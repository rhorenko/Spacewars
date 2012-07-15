package com.google.ads;

import android.webkit.WebView;
import com.google.ads.util.a;
import java.util.HashMap;

public final class t
  implements j
{
  public final void a(d paramd, HashMap<String, String> paramHashMap, WebView paramWebView)
  {
    String str = (String)paramHashMap.get("a");
    if (str == null)
      a.a("Could not get the action parameter for open GMSG.");
    while (true)
    {
      while (true)
      {
        return;
        if (!(str.equals("webapp")))
          break;
        AdActivity.launchAdActivity(paramd, new e("webapp", paramHashMap));
      }
      AdActivity.launchAdActivity(paramd, new e("intent", paramHashMap));
    }
  }
}