package com.google.ads;

import android.net.Uri;
import android.webkit.WebView;
import com.google.ads.util.AdUtil;
import com.google.ads.util.a;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public final class a
{
  public static final Map<String, j> a;
  public static final Map<String, j> b;

  static
  {
    HashMap localHashMap1 = new HashMap();
    localHashMap1.put("/invalidRequest", new p());
    localHashMap1.put("/loadAdURL", new q());
    a = Collections.unmodifiableMap(localHashMap1);
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("/open", new t());
    localHashMap2.put("/canOpenURLs", new k());
    localHashMap2.put("/close", new m());
    localHashMap2.put("/evalInOpener", new n());
    localHashMap2.put("/log", new s());
    localHashMap2.put("/click", new l());
    localHashMap2.put("/httpTrack", new o());
    localHashMap2.put("/touch", new u());
    localHashMap2.put("/video", new v());
    localHashMap2.put("/plusOne", new ab());
    b = Collections.unmodifiableMap(localHashMap2);
  }

  public static void a(WebView paramWebView)
  {
    a(paramWebView, "onshow", "{'version': 'afma-sdk-a-v4.3.1'}");
  }

  public static void a(WebView paramWebView, String paramString)
  {
    com.google.ads.util.a.d("Sending JS to a WebView: " + paramString);
    paramWebView.loadUrl("javascript:" + paramString);
  }

  public static void a(WebView paramWebView, String paramString1, String paramString2)
  {
    if (paramString2 != null)
      a(paramWebView, "AFMA_ReceiveMessage" + "('" + paramString1 + "', " + paramString2 + ");");
    while (true)
    {
      return;
      a(paramWebView, "AFMA_ReceiveMessage" + "('" + paramString1 + "');");
    }
  }

  public static void a(WebView paramWebView, Map<String, Boolean> paramMap)
  {
    a(paramWebView, "openableURLs", new JSONObject(paramMap).toString());
  }

  static void a(d paramd, Map<String, j> paramMap, Uri paramUri, WebView paramWebView)
  {
    HashMap localHashMap = AdUtil.b(paramUri);
    if (localHashMap == null)
      com.google.ads.util.a.e("An error occurred while parsing the message parameters.");
    while (true)
    {
      label198: j localj;
      while (true)
      {
        String str1;
        String str2;
        return;
        if (c(paramUri))
        {
          str2 = paramUri.getHost();
          if (str2 == null)
          {
            com.google.ads.util.a.e("An error occurred while parsing the AMSG parameters.");
            str1 = null;
          }
        }
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
                      if (str1 != null)
                        break label198;
                      com.google.ads.util.a.e("An error occurred while parsing the message.");
                    }
                    if (!(str2.equals("launch")))
                      break;
                    localHashMap.put("a", "intent");
                    localHashMap.put("u", localHashMap.get("url"));
                    localHashMap.remove("url");
                    str1 = "/open";
                  }
                  if (!(str2.equals("closecanvas")))
                    break;
                  str1 = "/close";
                }
                if (!(str2.equals("log")))
                  break;
                str1 = "/log";
              }
              com.google.ads.util.a.e("An error occurred while parsing the AMSG: " + paramUri.toString());
              str1 = null;
            }
            if (!(b(paramUri)))
              break;
            str1 = paramUri.getPath();
          }
          com.google.ads.util.a.e("Message was neither a GMSG nor an AMSG.");
          str1 = null;
        }
        localj = (j)paramMap.get(str1);
        if (localj != null)
          break;
        com.google.ads.util.a.e("No AdResponse found, <message: " + str1 + ">");
      }
      localj.a(paramd, localHashMap, paramWebView);
    }
  }

  public static boolean a(Uri paramUri)
  {
    byte b1 = 0;
    if ((paramUri == null) || (!(paramUri.isHierarchical())));
    while (true)
    {
      do
        return b1;
      while ((!(b(paramUri))) && (!(c(paramUri))));
      b1 = 1;
    }
  }

  public static void b(WebView paramWebView)
  {
    a(paramWebView, "onhide", null);
  }

  private static boolean b(Uri paramUri)
  {
    byte b1 = 0;
    String str1 = paramUri.getScheme();
    if ((str1 == null) || (!(str1.equals("gmsg"))));
    while (true)
    {
      String str2;
      do
      {
        return b1;
        str2 = paramUri.getAuthority();
      }
      while ((str2 == null) || (!(str2.equals("mobileads.google.com"))));
      b1 = 1;
    }
  }

  private static boolean c(Uri paramUri)
  {
    byte b1;
    String str = paramUri.getScheme();
    if ((str == null) || (!(str.equals("admob"))))
      b1 = 0;
    while (true)
    {
      return b1;
      b1 = 1;
    }
  }
}