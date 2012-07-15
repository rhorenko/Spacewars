package com.google.ads;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.webkit.WebView;
import com.google.ads.searchads.SearchAdRequest;
import com.google.ads.util.AdUtil;
import com.google.ads.util.AdUtil.a;
import com.google.ads.util.a;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;

public final class c
  implements Runnable
{
  private String a;
  private String b;
  private b c;
  private d d;
  private AdRequest e;
  private WebView f;
  private String g;
  private LinkedList<String> h;
  private volatile boolean i;
  private AdRequest.ErrorCode j;
  private boolean k;
  private int l;
  private Thread m;
  private boolean n;

  public c(d paramd)
  {
    this.d = paramd;
    this.g = null;
    this.a = null;
    this.b = null;
    this.h = new LinkedList();
    this.j = null;
    this.k = false;
    this.l = -1;
    Activity localActivity = paramd.e();
    if (localActivity != null)
    {
      this.f = new h(localActivity, null);
      this.f.setWebViewClient(new i(paramd, a.a, false, false));
      this.f.setVisibility(8);
      this.f.setWillNotDraw(true);
      this.c = new b(this, paramd);
    }
    while (true)
    {
      return;
      this.f = null;
      this.c = null;
      com.google.ads.util.a.e("activity was null while trying to create an AdLoader.");
    }
  }

  private String a(AdRequest paramAdRequest, Activity paramActivity)
    throws c.b, c.d
  {
    String str6;
    String str8;
    Context localContext = paramActivity.getApplicationContext();
    Map localMap = paramAdRequest.getRequestMap(localContext);
    f localf = this.d.l();
    long l1 = localf.h();
    if (l1 > 0L)
      localMap.put("prl", Long.valueOf(l1));
    String str1 = localf.g();
    if (str1 != null)
      localMap.put("ppcl", str1);
    String str2 = localf.f();
    if (str2 != null)
      localMap.put("pcl", str2);
    long l2 = localf.e();
    if (l2 > 0L)
      localMap.put("pcc", Long.valueOf(l2));
    localMap.put("preqs", Long.valueOf(f.i()));
    String str3 = localf.j();
    if (str3 != null)
      localMap.put("pai", str3);
    if (localf.k())
      localMap.put("aoi_timeout", "true");
    if (localf.m())
      localMap.put("aoi_nofill", "true");
    String str4 = localf.p();
    if (str4 != null)
      localMap.put("pit", str4);
    localf.a();
    localf.d();
    if (this.d.f() instanceof InterstitialAd)
    {
      localMap.put("format", "interstitial_mb");
      localMap.put("slotname", this.d.h());
      localMap.put("js", "afma-sdk-a-v4.3.1");
      str6 = localContext.getPackageName();
    }
    try
    {
      AdSize localAdSize;
      while (true)
      {
        PackageInfo localPackageInfo = localContext.getPackageManager().getPackageInfo(str6, 0);
        int i1 = localPackageInfo.versionCode;
        String str7 = AdUtil.f(localContext);
        if (!(TextUtils.isEmpty(str7)))
          localMap.put("mv", str7);
        localMap.put("msid", localContext.getPackageName());
        localMap.put("app_name", i1 + ".android." + localContext.getPackageName());
        localMap.put("isu", AdUtil.a(localContext));
        str8 = AdUtil.d(localContext);
        if (str8 != null)
          break label549;
        throw new c.d(this, "NETWORK_ERROR");
        localAdSize = this.d.k();
        String str5 = localAdSize.toString();
        if (str5 == null)
          break;
        localMap.put("format", str5);
      }
      HashMap localHashMap = new HashMap();
      localHashMap.put("w", Integer.valueOf(localAdSize.getWidth()));
      localHashMap.put("h", Integer.valueOf(localAdSize.getHeight()));
      label549: localMap.put("ad_frame", localHashMap);
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      String str11;
      throw new c.b(this, "NameNotFoundException");
      localMap.put("net", str8);
      String str9 = AdUtil.e(localContext);
      if ((str9 != null) && (str9.length() != 0))
        localMap.put("cap", str9);
      localMap.put("u_audio", Integer.valueOf(AdUtil.g(localContext).ordinal()));
      DisplayMetrics localDisplayMetrics = AdUtil.a(paramActivity);
      localMap.put("u_sd", Float.valueOf(localDisplayMetrics.density));
      localMap.put("u_h", Integer.valueOf(AdUtil.a(localContext, localDisplayMetrics)));
      localMap.put("u_w", Integer.valueOf(AdUtil.b(localContext, localDisplayMetrics)));
      localMap.put("hl", Locale.getDefault().getLanguage());
      if (AdUtil.c())
        localMap.put("simulator", Integer.valueOf(1));
      String str10 = AdUtil.a(localMap);
      StringBuilder localStringBuilder = new StringBuilder();
      if (this.e instanceof SearchAdRequest)
        str11 = "<html><head><script src=\"http://www.gstatic.com/safa/sdk-core-v40.js\"></script><script>";
      while (true)
      {
        String str12 = str11 + "AFMA_buildAdURL" + "(" + str10 + ");" + "</script></head><body></body></html>";
        com.google.ads.util.a.c("adRequestUrlHtml: " + str12);
        return str12;
        str11 = "<html><head><script src=\"http://media.admob.com/sdk-core-v40.js\"></script><script>";
      }
    }
  }

  private void a(AdRequest.ErrorCode paramErrorCode, boolean paramBoolean)
  {
    this.c.a();
    this.d.a(new a(this, this.d, this.f, this.c, paramErrorCode, paramBoolean));
  }

  final void a()
  {
    com.google.ads.util.a.a("AdLoader cancelled.");
    this.f.stopLoading();
    this.f.destroy();
    if (this.m != null)
    {
      this.m.interrupt();
      this.m = null;
    }
    this.c.a();
    this.i = true;
  }

  /**
   * @deprecated
   */
  // ERROR //
  public final void a(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: putfield 65	com/google/ads/c:l	I
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_2
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_2
    //   14: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }

  /**
   * @deprecated
   */
  // ERROR //
  public final void a(AdRequest.ErrorCode paramErrorCode)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield 61	com/google/ads/c:j	Lcom/google/ads/AdRequest$ErrorCode;
    //   7: aload_0
    //   8: invokevirtual 417	java/lang/Object:notify	()V
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: astore_2
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_2
    //   18: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	11	14	finally
  }

  final void a(AdRequest paramAdRequest)
  {
    this.e = paramAdRequest;
    this.i = false;
    this.m = new Thread(this);
    this.m.start();
  }

  /**
   * @deprecated
   */
  // ERROR //
  final void a(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 59	com/google/ads/c:h	Ljava/util/LinkedList;
    //   6: aload_1
    //   7: invokevirtual 427	java/util/LinkedList:add	(Ljava/lang/Object;)Z
    //   10: pop
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: astore_2
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_2
    //   18: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	11	14	finally
  }

  /**
   * @deprecated
   */
  // ERROR //
  final void a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_2
    //   4: putfield 52	com/google/ads/c:a	Ljava/lang/String;
    //   7: aload_0
    //   8: aload_1
    //   9: putfield 54	com/google/ads/c:b	Ljava/lang/String;
    //   12: aload_0
    //   13: invokevirtual 417	java/lang/Object:notify	()V
    //   16: aload_0
    //   17: monitorexit
    //   18: return
    //   19: astore_3
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_3
    //   23: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	16	19	finally
  }

  public final void a(boolean paramBoolean)
  {
    this.n = paramBoolean;
  }

  /**
   * @deprecated
   */
  // ERROR //
  final void b()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_1
    //   4: putfield 63	com/google/ads/c:k	Z
    //   7: aload_0
    //   8: invokevirtual 417	java/lang/Object:notify	()V
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: astore_1
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_1
    //   18: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	11	14	finally
  }

  /**
   * @deprecated
   */
  // ERROR //
  public final void b(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield 50	com/google/ads/c:g	Ljava/lang/String;
    //   7: aload_0
    //   8: invokevirtual 417	java/lang/Object:notify	()V
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: astore_2
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_2
    //   18: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	11	14	finally
  }

  // ERROR //
  public final void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 77	com/google/ads/c:f	Landroid/webkit/WebView;
    //   6: ifnull +10 -> 16
    //   9: aload_0
    //   10: getfield 108	com/google/ads/c:c	Lcom/google/ads/b;
    //   13: ifnonnull +20 -> 33
    //   16: ldc_w 437
    //   19: invokestatic 115	com/google/ads/util/a:e	(Ljava/lang/String;)V
    //   22: aload_0
    //   23: getstatic 442	com/google/ads/AdRequest$ErrorCode:INTERNAL_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   26: iconst_0
    //   27: invokespecial 444	com/google/ads/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   30: aload_0
    //   31: monitorexit
    //   32: return
    //   33: aload_0
    //   34: getfield 48	com/google/ads/c:d	Lcom/google/ads/d;
    //   37: invokevirtual 70	com/google/ads/d:e	()Landroid/app/Activity;
    //   40: astore_3
    //   41: aload_3
    //   42: ifnonnull +27 -> 69
    //   45: ldc_w 446
    //   48: invokestatic 115	com/google/ads/util/a:e	(Ljava/lang/String;)V
    //   51: aload_0
    //   52: getstatic 442	com/google/ads/AdRequest$ErrorCode:INTERNAL_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   55: iconst_0
    //   56: invokespecial 444	com/google/ads/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   59: aload_0
    //   60: monitorexit
    //   61: goto -29 -> 32
    //   64: astore_2
    //   65: aload_0
    //   66: monitorexit
    //   67: aload_2
    //   68: athrow
    //   69: aload_0
    //   70: getfield 48	com/google/ads/c:d	Lcom/google/ads/d;
    //   73: invokevirtual 448	com/google/ads/d:n	()J
    //   76: lstore 4
    //   78: invokestatic 453	android/os/SystemClock:elapsedRealtime	()J
    //   81: lstore 6
    //   83: aload_0
    //   84: getfield 368	com/google/ads/c:e	Lcom/google/ads/AdRequest;
    //   87: aload_3
    //   88: invokevirtual 130	com/google/ads/AdRequest:getRequestMap	(Landroid/content/Context;)Ljava/util/Map;
    //   91: ldc_w 455
    //   94: invokeinterface 459 2 0
    //   99: astore 8
    //   101: aload 8
    //   103: instanceof 148
    //   106: ifeq +75 -> 181
    //   109: aload 8
    //   111: checkcast 148	java/util/Map
    //   114: astore 31
    //   116: aload 31
    //   118: ldc_w 461
    //   121: invokeinterface 459 2 0
    //   126: astore 32
    //   128: aload 32
    //   130: instanceof 308
    //   133: ifeq +12 -> 145
    //   136: aload_0
    //   137: aload 32
    //   139: checkcast 308	java/lang/String
    //   142: putfield 52	com/google/ads/c:a	Ljava/lang/String;
    //   145: aload 31
    //   147: ldc_w 463
    //   150: invokeinterface 459 2 0
    //   155: astore 33
    //   157: aload 33
    //   159: instanceof 308
    //   162: ifeq +19 -> 181
    //   165: aload 33
    //   167: ldc_w 464
    //   170: invokevirtual 467	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   173: ifeq +96 -> 269
    //   176: aload_0
    //   177: iconst_1
    //   178: putfield 65	com/google/ads/c:l	I
    //   181: aload_0
    //   182: getfield 52	com/google/ads/c:a	Ljava/lang/String;
    //   185: astore 9
    //   187: aload 9
    //   189: ifnonnull +468 -> 657
    //   192: aload_0
    //   193: aload_0
    //   194: getfield 368	com/google/ads/c:e	Lcom/google/ads/AdRequest;
    //   197: aload_3
    //   198: invokespecial 469	com/google/ads/c:a	(Lcom/google/ads/AdRequest;Landroid/app/Activity;)Ljava/lang/String;
    //   201: astore 18
    //   203: aload_0
    //   204: getfield 48	com/google/ads/c:d	Lcom/google/ads/d;
    //   207: new 11	com/google/ads/c$c
    //   210: dup
    //   211: aload_0
    //   212: aload_0
    //   213: getfield 77	com/google/ads/c:f	Landroid/webkit/WebView;
    //   216: aconst_null
    //   217: aload 18
    //   219: invokespecial 472	com/google/ads/c$c:<init>	(Lcom/google/ads/c;Landroid/webkit/WebView;Ljava/lang/String;Ljava/lang/String;)V
    //   222: invokevirtual 394	com/google/ads/d:a	(Ljava/lang/Runnable;)V
    //   225: invokestatic 453	android/os/SystemClock:elapsedRealtime	()J
    //   228: lstore 19
    //   230: lload 4
    //   232: lload 19
    //   234: lload 6
    //   236: lsub
    //   237: lsub
    //   238: lstore 21
    //   240: lload 21
    //   242: lconst_0
    //   243: lcmp
    //   244: ifle +9 -> 253
    //   247: aload_0
    //   248: lload 21
    //   250: invokevirtual 476	java/lang/Object:wait	(J)V
    //   253: aload_0
    //   254: getfield 413	com/google/ads/c:i	Z
    //   257: istore 23
    //   259: iload 23
    //   261: ifeq +157 -> 418
    //   264: aload_0
    //   265: monitorexit
    //   266: goto -234 -> 32
    //   269: aload 33
    //   271: ldc_w 477
    //   274: invokevirtual 467	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   277: ifeq -96 -> 181
    //   280: aload_0
    //   281: iconst_0
    //   282: putfield 65	com/google/ads/c:l	I
    //   285: goto -104 -> 181
    //   288: astore_1
    //   289: ldc_w 479
    //   292: aload_1
    //   293: invokestatic 482	com/google/ads/util/a:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   296: aload_0
    //   297: getstatic 442	com/google/ads/AdRequest$ErrorCode:INTERNAL_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   300: iconst_1
    //   301: invokespecial 444	com/google/ads/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   304: aload_0
    //   305: monitorexit
    //   306: goto -274 -> 32
    //   309: astore 17
    //   311: new 249	java/lang/StringBuilder
    //   314: dup
    //   315: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   318: ldc_w 484
    //   321: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   324: aload 17
    //   326: invokevirtual 487	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   329: invokevirtual 262	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   332: invokestatic 384	com/google/ads/util/a:c	(Ljava/lang/String;)V
    //   335: aload_0
    //   336: getstatic 489	com/google/ads/AdRequest$ErrorCode:NETWORK_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   339: iconst_0
    //   340: invokespecial 444	com/google/ads/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   343: aload_0
    //   344: monitorexit
    //   345: goto -313 -> 32
    //   348: astore 16
    //   350: new 249	java/lang/StringBuilder
    //   353: dup
    //   354: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   357: ldc_w 491
    //   360: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   363: aload 16
    //   365: invokevirtual 487	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   368: invokevirtual 262	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   371: invokestatic 384	com/google/ads/util/a:c	(Ljava/lang/String;)V
    //   374: aload_0
    //   375: getstatic 442	com/google/ads/AdRequest$ErrorCode:INTERNAL_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   378: iconst_0
    //   379: invokespecial 444	com/google/ads/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   382: aload_0
    //   383: monitorexit
    //   384: goto -352 -> 32
    //   387: astore 30
    //   389: new 249	java/lang/StringBuilder
    //   392: dup
    //   393: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   396: ldc_w 493
    //   399: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   402: aload 30
    //   404: invokevirtual 487	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   407: invokevirtual 262	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   410: invokestatic 398	com/google/ads/util/a:a	(Ljava/lang/String;)V
    //   413: aload_0
    //   414: monitorexit
    //   415: goto -383 -> 32
    //   418: aload_0
    //   419: getfield 61	com/google/ads/c:j	Lcom/google/ads/AdRequest$ErrorCode;
    //   422: ifnull +17 -> 439
    //   425: aload_0
    //   426: aload_0
    //   427: getfield 61	com/google/ads/c:j	Lcom/google/ads/AdRequest$ErrorCode;
    //   430: iconst_0
    //   431: invokespecial 444	com/google/ads/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   434: aload_0
    //   435: monitorexit
    //   436: goto -404 -> 32
    //   439: aload_0
    //   440: getfield 50	com/google/ads/c:g	Ljava/lang/String;
    //   443: ifnonnull +46 -> 489
    //   446: new 249	java/lang/StringBuilder
    //   449: dup
    //   450: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   453: ldc_w 495
    //   456: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   459: lload 4
    //   461: invokevirtual 498	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   464: ldc_w 500
    //   467: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   470: invokevirtual 262	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   473: invokestatic 384	com/google/ads/util/a:c	(Ljava/lang/String;)V
    //   476: aload_0
    //   477: getstatic 489	com/google/ads/AdRequest$ErrorCode:NETWORK_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   480: iconst_0
    //   481: invokespecial 444	com/google/ads/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   484: aload_0
    //   485: monitorexit
    //   486: goto -454 -> 32
    //   489: aload_0
    //   490: getfield 108	com/google/ads/c:c	Lcom/google/ads/b;
    //   493: aload_0
    //   494: getfield 430	com/google/ads/c:n	Z
    //   497: invokevirtual 502	com/google/ads/b:a	(Z)V
    //   500: aload_0
    //   501: getfield 108	com/google/ads/c:c	Lcom/google/ads/b;
    //   504: aload_0
    //   505: getfield 50	com/google/ads/c:g	Ljava/lang/String;
    //   508: invokevirtual 503	com/google/ads/b:a	(Ljava/lang/String;)V
    //   511: invokestatic 453	android/os/SystemClock:elapsedRealtime	()J
    //   514: lstore 24
    //   516: lload 4
    //   518: lload 24
    //   520: lload 6
    //   522: lsub
    //   523: lsub
    //   524: lstore 26
    //   526: lload 26
    //   528: lconst_0
    //   529: lcmp
    //   530: ifle +9 -> 539
    //   533: aload_0
    //   534: lload 26
    //   536: invokevirtual 476	java/lang/Object:wait	(J)V
    //   539: aload_0
    //   540: getfield 413	com/google/ads/c:i	Z
    //   543: istore 28
    //   545: iload 28
    //   547: ifeq +39 -> 586
    //   550: aload_0
    //   551: monitorexit
    //   552: goto -520 -> 32
    //   555: astore 29
    //   557: new 249	java/lang/StringBuilder
    //   560: dup
    //   561: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   564: ldc_w 505
    //   567: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   570: aload 29
    //   572: invokevirtual 487	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   575: invokevirtual 262	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   578: invokestatic 398	com/google/ads/util/a:a	(Ljava/lang/String;)V
    //   581: aload_0
    //   582: monitorexit
    //   583: goto -551 -> 32
    //   586: aload_0
    //   587: getfield 61	com/google/ads/c:j	Lcom/google/ads/AdRequest$ErrorCode;
    //   590: ifnull +17 -> 607
    //   593: aload_0
    //   594: aload_0
    //   595: getfield 61	com/google/ads/c:j	Lcom/google/ads/AdRequest$ErrorCode;
    //   598: iconst_0
    //   599: invokespecial 444	com/google/ads/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   602: aload_0
    //   603: monitorexit
    //   604: goto -572 -> 32
    //   607: aload_0
    //   608: getfield 54	com/google/ads/c:b	Ljava/lang/String;
    //   611: ifnonnull +46 -> 657
    //   614: new 249	java/lang/StringBuilder
    //   617: dup
    //   618: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   621: ldc_w 495
    //   624: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   627: lload 4
    //   629: invokevirtual 498	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   632: ldc_w 507
    //   635: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   638: invokevirtual 262	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   641: invokestatic 384	com/google/ads/util/a:c	(Ljava/lang/String;)V
    //   644: aload_0
    //   645: getstatic 489	com/google/ads/AdRequest$ErrorCode:NETWORK_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   648: iconst_0
    //   649: invokespecial 444	com/google/ads/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   652: aload_0
    //   653: monitorexit
    //   654: goto -622 -> 32
    //   657: aload_0
    //   658: getfield 48	com/google/ads/c:d	Lcom/google/ads/d;
    //   661: invokevirtual 510	com/google/ads/d:i	()Lcom/google/ads/h;
    //   664: astore 10
    //   666: aload_0
    //   667: getfield 48	com/google/ads/c:d	Lcom/google/ads/d;
    //   670: invokevirtual 513	com/google/ads/d:j	()Lcom/google/ads/i;
    //   673: invokevirtual 515	com/google/ads/i:b	()V
    //   676: aload_0
    //   677: getfield 48	com/google/ads/c:d	Lcom/google/ads/d;
    //   680: new 11	com/google/ads/c$c
    //   683: dup
    //   684: aload_0
    //   685: aload 10
    //   687: aload_0
    //   688: getfield 52	com/google/ads/c:a	Ljava/lang/String;
    //   691: aload_0
    //   692: getfield 54	com/google/ads/c:b	Ljava/lang/String;
    //   695: invokespecial 472	com/google/ads/c$c:<init>	(Lcom/google/ads/c;Landroid/webkit/WebView;Ljava/lang/String;Ljava/lang/String;)V
    //   698: invokevirtual 394	com/google/ads/d:a	(Ljava/lang/Runnable;)V
    //   701: invokestatic 453	android/os/SystemClock:elapsedRealtime	()J
    //   704: lstore 11
    //   706: lload 4
    //   708: lload 11
    //   710: lload 6
    //   712: lsub
    //   713: lsub
    //   714: lstore 13
    //   716: lload 13
    //   718: lconst_0
    //   719: lcmp
    //   720: ifle +9 -> 729
    //   723: aload_0
    //   724: lload 13
    //   726: invokevirtual 476	java/lang/Object:wait	(J)V
    //   729: aload_0
    //   730: getfield 63	com/google/ads/c:k	Z
    //   733: ifeq +64 -> 797
    //   736: aload_0
    //   737: getfield 48	com/google/ads/c:d	Lcom/google/ads/d;
    //   740: new 8	com/google/ads/c$e
    //   743: dup
    //   744: aload_0
    //   745: aload_0
    //   746: getfield 48	com/google/ads/c:d	Lcom/google/ads/d;
    //   749: aload_0
    //   750: getfield 59	com/google/ads/c:h	Ljava/util/LinkedList;
    //   753: aload_0
    //   754: getfield 65	com/google/ads/c:l	I
    //   757: invokespecial 518	com/google/ads/c$e:<init>	(Lcom/google/ads/c;Lcom/google/ads/d;Ljava/util/LinkedList;I)V
    //   760: invokevirtual 394	com/google/ads/d:a	(Ljava/lang/Runnable;)V
    //   763: goto -459 -> 304
    //   766: astore 15
    //   768: new 249	java/lang/StringBuilder
    //   771: dup
    //   772: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   775: ldc_w 520
    //   778: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   781: aload 15
    //   783: invokevirtual 487	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   786: invokevirtual 262	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   789: invokestatic 398	com/google/ads/util/a:a	(Ljava/lang/String;)V
    //   792: aload_0
    //   793: monitorexit
    //   794: goto -762 -> 32
    //   797: new 249	java/lang/StringBuilder
    //   800: dup
    //   801: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   804: ldc_w 495
    //   807: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   810: lload 4
    //   812: invokevirtual 498	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   815: ldc_w 522
    //   818: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   821: invokevirtual 262	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   824: invokestatic 384	com/google/ads/util/a:c	(Ljava/lang/String;)V
    //   827: aload_0
    //   828: getstatic 489	com/google/ads/AdRequest$ErrorCode:NETWORK_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   831: iconst_1
    //   832: invokespecial 444	com/google/ads/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   835: goto -531 -> 304
    //
    // Exception table:
    //   from	to	target	type
    //   2	30	64	finally
    //   30	32	64	finally
    //   33	59	64	finally
    //   59	61	64	finally
    //   69	187	64	finally
    //   192	203	64	finally
    //   203	230	64	finally
    //   247	253	64	finally
    //   253	259	64	finally
    //   264	266	64	finally
    //   269	285	64	finally
    //   289	306	64	finally
    //   311	343	64	finally
    //   343	345	64	finally
    //   350	382	64	finally
    //   382	384	64	finally
    //   389	413	64	finally
    //   413	415	64	finally
    //   418	434	64	finally
    //   434	436	64	finally
    //   439	484	64	finally
    //   484	486	64	finally
    //   489	516	64	finally
    //   533	539	64	finally
    //   539	545	64	finally
    //   550	552	64	finally
    //   557	581	64	finally
    //   581	583	64	finally
    //   586	602	64	finally
    //   602	604	64	finally
    //   607	652	64	finally
    //   652	654	64	finally
    //   657	706	64	finally
    //   723	729	64	finally
    //   729	792	64	finally
    //   792	794	64	finally
    //   797	835	64	finally
    //   2	30	288	java/lang/Exception
    //   33	59	288	java/lang/Exception
    //   69	187	288	java/lang/Exception
    //   192	203	288	java/lang/Exception
    //   203	230	288	java/lang/Exception
    //   247	253	288	java/lang/Exception
    //   253	259	288	java/lang/Exception
    //   269	285	288	java/lang/Exception
    //   311	343	288	java/lang/Exception
    //   350	382	288	java/lang/Exception
    //   389	413	288	java/lang/Exception
    //   418	434	288	java/lang/Exception
    //   439	484	288	java/lang/Exception
    //   489	516	288	java/lang/Exception
    //   533	539	288	java/lang/Exception
    //   539	545	288	java/lang/Exception
    //   557	581	288	java/lang/Exception
    //   586	602	288	java/lang/Exception
    //   607	652	288	java/lang/Exception
    //   657	706	288	java/lang/Exception
    //   723	729	288	java/lang/Exception
    //   729	792	288	java/lang/Exception
    //   797	835	288	java/lang/Exception
    //   192	203	309	c$d
    //   192	203	348	c$b
    //   247	253	387	java/lang/InterruptedException
    //   533	539	555	java/lang/InterruptedException
    //   723	729	766	java/lang/InterruptedException
  }

  private class e
  implements Runnable
  {
    private final d b;
    private final LinkedList<String> c;
    private final int d;

    public e(, LinkedList<String> paramLinkedList, int paramInt)
    {
      this.b = paramLinkedList;
      this.c = paramInt;
      this.d = b1;
    }

    public final void run()
    {
      this.b.a(this.c);
      this.b.a(this.d);
      this.b.q();
    }
  }

  private class c
  implements Runnable
  {
    private final String b;
    private final String c;
    private final WebView d;

    public c(, WebView paramWebView, String paramString1, String paramString2)
    {
      this.d = paramWebView;
      this.b = paramString1;
      this.c = paramString2;
    }

    public final void run()
    {
      if (this.c != null)
        this.d.loadDataWithBaseURL(this.b, this.c, "text/html", "utf-8", null);
      while (true)
      {
        return;
        this.d.loadUrl(this.b);
      }
    }
  }

  private class a
  implements Runnable
  {
    private final d b;
    private final WebView c;
    private final b d;
    private final AdRequest.ErrorCode e;
    private final boolean f;

    public a(, d paramd, WebView paramWebView, b paramb, AdRequest.ErrorCode paramErrorCode, boolean paramBoolean)
    {
      this.b = paramd;
      this.c = paramWebView;
      this.d = paramb;
      this.e = paramErrorCode;
      this.f = paramBoolean;
    }

    public final void run()
    {
      if (this.c != null)
      {
        this.c.stopLoading();
        this.c.destroy();
      }
      if (this.d != null)
        this.d.a();
      if (this.f)
      {
        h localh = this.b.i();
        localh.stopLoading();
        localh.setVisibility(8);
      }
      this.b.a(this.e);
    }
  }

  private class b extends Exception
  {
    public b(, String paramString)
    {
      super(paramString);
    }
  }

  private class d extends Exception
  {
    public d(, String paramString)
    {
      super(paramString);
    }
  }
}