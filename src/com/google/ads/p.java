package com.google.ads;

import android.webkit.WebView;
import com.google.ads.util.a;
import java.util.HashMap;

public final class p
  implements j
{
  public final void a(d paramd, HashMap<String, String> paramHashMap, WebView paramWebView)
  {
    String str1 = (String)paramHashMap.get("type");
    String str2 = (String)paramHashMap.get("errors");
    a.e("Invalid " + str1 + " request error: " + str2);
    c localc = paramd.g();
    if (localc != null)
      localc.a(AdRequest.ErrorCode.INVALID_REQUEST);
  }
}