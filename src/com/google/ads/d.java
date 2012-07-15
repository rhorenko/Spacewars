package com.google.ads;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import com.google.ads.util.AdUtil;
import com.google.ads.util.a;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public final class d
{
  private static final Object a = new Object();
  private WeakReference<Activity> b;
  private Ad c;
  private AdListener d;
  private c e;
  private AdRequest f;
  private AdSize g;
  private f h;
  private String i;
  private h j;
  private i k;
  private Handler l;
  private long m;
  private boolean n;
  private boolean o;
  private SharedPreferences p;
  private long q;
  private x r;
  private boolean s;
  private LinkedList<String> t;
  private LinkedList<String> u;
  private int v;

  public d(Activity paramActivity, Ad paramAd, AdSize paramAdSize, String paramString, boolean paramBoolean)
  {
    this.b = new WeakReference(paramActivity);
    this.c = paramAd;
    this.g = paramAdSize;
    this.i = paramString;
    this.s = paramBoolean;
    this.h = new f();
    this.d = null;
    this.e = null;
    this.f = null;
    this.n = false;
    this.l = new Handler();
    this.q = 0L;
    this.o = false;
    Object localObject1 = a;
    monitorenter;
    try
    {
      this.p = paramActivity.getApplicationContext().getSharedPreferences("GoogleAdMobAdsPrefs", 0);
      if (!(paramBoolean))
        break label231;
      l1 = this.p.getLong("Timeout" + paramString, -1);
      if (l1 > 0L)
      {
        this.m = 5000;
        monitorexit;
        this.r = new x(this);
        this.t = new LinkedList();
        label231: this.u = new LinkedList();
      }
    }
    finally
    {
      while (true)
      {
        monitorexit;
        throw localObject2;
        this.m = 60000;
      }
    }
  }

  /**
   * @deprecated
   */
  // ERROR //
  private boolean A()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 78	com/google/ads/d:e	Lcom/google/ads/c;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull +9 -> 17
    //   11: iconst_1
    //   12: istore_3
    //   13: aload_0
    //   14: monitorexit
    //   15: iload_3
    //   16: ireturn
    //   17: iconst_0
    //   18: istore_3
    //   19: goto -6 -> 13
    //   22: astore_1
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_1
    //   26: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	7	22	finally
  }

  /**
   * @deprecated
   */
  private void B()
  {
    Activity localActivity;
    Iterator localIterator;
    monitorenter;
    try
    {
      localActivity = (Activity)this.b.get();
      if (localActivity == null)
        com.google.ads.util.a.e("activity was null while trying to ping click tracking URLs.");
      do
      {
        monitorexit;
        return;
        localIterator = this.u.iterator();
      }
      while (!(localIterator.hasNext()));
    }
    finally
    {
      monitorexit;
    }
  }

  /**
   * @deprecated
   */
  public final void a()
  {
    monitorenter;
    try
    {
      Activity localActivity = e();
      if (localActivity == null)
      {
        com.google.ads.util.a.a("activity was null while trying to create an AdWebView.");
        monitorexit;
        return;
      }
      this.j = new h(localActivity.getApplicationContext(), this.g);
    }
    finally
    {
      while (true)
      {
        monitorexit;
        throw localObject;
        this.k = new i(this, a.b, true, true);
      }
    }
  }

  /**
   * @deprecated
   */
  // ERROR //
  public final void a(float paramFloat)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 234
    //   4: fload_1
    //   5: fmul
    //   6: f2l
    //   7: lstore_2
    //   8: aload_0
    //   9: lload_2
    //   10: putfield 89	com/google/ads/d:q	J
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: astore 4
    //   18: aload_0
    //   19: monitorexit
    //   20: aload 4
    //   22: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   8	13	16	finally
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
    //   4: putfield 236	com/google/ads/d:v	I
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

  // ERROR //
  public final void a(long paramLong)
  {
    // Byte code:
    //   0: getstatic 53	com/google/ads/d:a	Ljava/lang/Object;
    //   3: astore_3
    //   4: aload_3
    //   5: monitorenter
    //   6: aload_0
    //   7: getfield 107	com/google/ads/d:p	Landroid/content/SharedPreferences;
    //   10: invokeinterface 241 1 0
    //   15: astore 5
    //   17: aload 5
    //   19: new 109	java/lang/StringBuilder
    //   22: dup
    //   23: invokespecial 110	java/lang/StringBuilder:<init>	()V
    //   26: ldc 112
    //   28: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: aload_0
    //   32: getfield 67	com/google/ads/d:i	Ljava/lang/String;
    //   35: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: invokevirtual 120	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: lload_1
    //   42: invokeinterface 247 4 0
    //   47: pop
    //   48: aload 5
    //   50: invokeinterface 250 1 0
    //   55: pop
    //   56: aload_0
    //   57: getfield 69	com/google/ads/d:s	Z
    //   60: ifeq +8 -> 68
    //   63: aload_0
    //   64: lload_1
    //   65: putfield 132	com/google/ads/d:m	J
    //   68: aload_3
    //   69: monitorexit
    //   70: return
    //   71: astore 4
    //   73: aload_3
    //   74: monitorexit
    //   75: aload 4
    //   77: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   6	70	71	finally
  }

  /**
   * @deprecated
   */
  // ERROR //
  public final void a(AdListener paramAdListener)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield 76	com/google/ads/d:d	Lcom/google/ads/AdListener;
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
  public final void a(AdRequest.ErrorCode paramErrorCode)
  {
    monitorenter;
    try
    {
      this.e = null;
      if (this.c instanceof InterstitialAd)
      {
        if (paramErrorCode != AdRequest.ErrorCode.NO_FILL)
          break label84;
        this.h.n();
      }
      do
      {
        com.google.ads.util.a.c("onFailedToReceiveAd(" + paramErrorCode + ")");
        if (this.d != null)
          this.d.onFailedToReceiveAd(this.c, paramErrorCode);
        monitorexit;
        label84: return;
      }
      while (paramErrorCode != AdRequest.ErrorCode.NETWORK_ERROR);
    }
    finally
    {
      monitorexit;
    }
  }

  /**
   * @deprecated
   */
  public final void a(AdRequest paramAdRequest)
  {
    byte b1 = 0;
    monitorenter;
    try
    {
    }
    finally
    {
      Activity localActivity;
      do
        while (true)
        {
          monitorexit;
          throw localObject;
          localActivity = e();
          if (localActivity != null)
            break;
          com.google.ads.util.a.e("activity is null while trying to load an ad.");
        }
      while ((!(AdUtil.c(localActivity.getApplicationContext()))) || (!(AdUtil.b(localActivity.getApplicationContext()))));
      long l1 = this.p.getLong("GoogleAdMobDoritosLife", 60000);
      SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(localActivity.getApplicationContext());
      if (aa.a(localActivity))
      {
        if ((!(localSharedPreferences.contains("drt"))) || (!(localSharedPreferences.contains("drt_ts"))))
          break label223;
        if (localSharedPreferences.getLong("drt_ts", 0L) > new Date().getTime() - l1);
      }
      while (true)
      {
        while (true)
        {
          if (b1 != 0)
            z.a(localActivity);
          this.n = false;
          this.t.clear();
          this.f = paramAdRequest;
          this.e = new c(this);
          this.e.a(paramAdRequest);
        }
        label223: b1 = 1;
      }
    }
  }

  final void a(Runnable paramRunnable)
  {
    this.l.post(paramRunnable);
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
    //   2: new 109	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 110	java/lang/StringBuilder:<init>	()V
    //   9: ldc_w 347
    //   12: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: aload_1
    //   16: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: invokevirtual 120	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   22: invokestatic 203	com/google/ads/util/a:a	(Ljava/lang/String;)V
    //   25: aload_0
    //   26: getfield 144	com/google/ads/d:t	Ljava/util/LinkedList;
    //   29: aload_1
    //   30: invokevirtual 351	java/util/LinkedList:add	(Ljava/lang/Object;)Z
    //   33: pop
    //   34: aload_0
    //   35: monitorexit
    //   36: return
    //   37: astore_2
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_2
    //   41: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	34	37	finally
  }

  /**
   * @deprecated
   */
  final void a(LinkedList<String> paramLinkedList)
  {
    monitorenter;
    try
    {
    }
    finally
    {
      Iterator localIterator;
      String str;
      monitorexit;
      throw localObject;
      this.u = paramLinkedList;
      monitorexit;
    }
  }

  /**
   * @deprecated
   */
  // ERROR //
  public final void b()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aconst_null
    //   4: invokevirtual 356	com/google/ads/d:a	(Lcom/google/ads/AdListener;)V
    //   7: aload_0
    //   8: invokevirtual 359	com/google/ads/d:z	()V
    //   11: aload_0
    //   12: getfield 210	com/google/ads/d:j	Lcom/google/ads/h;
    //   15: invokevirtual 362	com/google/ads/h:destroy	()V
    //   18: aload_0
    //   19: monitorexit
    //   20: return
    //   21: astore_1
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_1
    //   25: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	18	21	finally
  }

  /**
   * @deprecated
   */
  // ERROR //
  public final void b(long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: lload_1
    //   3: lconst_0
    //   4: lcmp
    //   5: ifle +27 -> 32
    //   8: aload_0
    //   9: getfield 107	com/google/ads/d:p	Landroid/content/SharedPreferences;
    //   12: invokeinterface 241 1 0
    //   17: ldc_w 303
    //   20: lload_1
    //   21: invokeinterface 247 4 0
    //   26: invokeinterface 250 1 0
    //   31: pop
    //   32: aload_0
    //   33: monitorexit
    //   34: return
    //   35: astore_3
    //   36: aload_0
    //   37: monitorexit
    //   38: aload_3
    //   39: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   8	32	35	finally
  }

  /**
   * @deprecated
   */
  public final void c()
  {
    monitorenter;
    try
    {
      if (this.o)
      {
        com.google.ads.util.a.a("Disabling refreshing.");
        this.l.removeCallbacks(this.r);
        this.o = false;
        monitorexit;
        return;
      }
    }
    finally
    {
      monitorexit;
    }
  }

  /**
   * @deprecated
   */
  public final void d()
  {
    monitorenter;
    try
    {
      if (!(this.c instanceof AdView))
        break label89;
      if (!(this.o))
      {
        com.google.ads.util.a.a("Enabling refreshing every " + this.q + " milliseconds.");
        label89: this.l.postDelayed(this.r, this.q);
      }
    }
    finally
    {
      while (true)
      {
        monitorexit;
        throw localObject;
        com.google.ads.util.a.a("Tried to enable refreshing on something other than an AdView.");
      }
    }
  }

  public final Activity e()
  {
    return ((Activity)this.b.get());
  }

  public final Ad f()
  {
    return this.c;
  }

  /**
   * @deprecated
   */
  public final c g()
  {
    monitorenter;
    try
    {
      c localc = this.e;
      monitorexit;
      return localc;
    }
    finally
    {
      localObject = finally;
      monitorexit;
      throw localObject;
    }
  }

  final String h()
  {
    return this.i;
  }

  /**
   * @deprecated
   */
  public final h i()
  {
    monitorenter;
    try
    {
      h localh = this.j;
      monitorexit;
      return localh;
    }
    finally
    {
      localObject = finally;
      monitorexit;
      throw localObject;
    }
  }

  /**
   * @deprecated
   */
  public final i j()
  {
    monitorenter;
    try
    {
      i locali = this.k;
      monitorexit;
      return locali;
    }
    finally
    {
      localObject = finally;
      monitorexit;
      throw localObject;
    }
  }

  public final AdSize k()
  {
    return this.g;
  }

  public final f l()
  {
    return this.h;
  }

  /**
   * @deprecated
   */
  public final int m()
  {
    monitorenter;
    try
    {
      int i1 = this.v;
      monitorexit;
      return i1;
    }
    finally
    {
      localObject = finally;
      monitorexit;
      throw localObject;
    }
  }

  public final long n()
  {
    return this.m;
  }

  /**
   * @deprecated
   */
  public final boolean o()
  {
    monitorenter;
    try
    {
      boolean bool = this.n;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
      throw localObject;
    }
  }

  /**
   * @deprecated
   */
  public final boolean p()
  {
    monitorenter;
    try
    {
      boolean bool = this.o;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
      throw localObject;
    }
  }

  /**
   * @deprecated
   */
  // ERROR //
  final void q()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aconst_null
    //   4: putfield 78	com/google/ads/d:e	Lcom/google/ads/c;
    //   7: aload_0
    //   8: iconst_1
    //   9: putfield 82	com/google/ads/d:n	Z
    //   12: aload_0
    //   13: getfield 210	com/google/ads/d:j	Lcom/google/ads/h;
    //   16: iconst_0
    //   17: invokevirtual 214	com/google/ads/h:setVisibility	(I)V
    //   20: aload_0
    //   21: getfield 74	com/google/ads/d:h	Lcom/google/ads/f;
    //   24: invokevirtual 393	com/google/ads/f:c	()V
    //   27: aload_0
    //   28: getfield 63	com/google/ads/d:c	Lcom/google/ads/Ad;
    //   31: instanceof 216
    //   34: ifeq +7 -> 41
    //   37: aload_0
    //   38: invokevirtual 395	com/google/ads/d:v	()V
    //   41: ldc_w 397
    //   44: invokestatic 271	com/google/ads/util/a:c	(Ljava/lang/String;)V
    //   47: aload_0
    //   48: getfield 76	com/google/ads/d:d	Lcom/google/ads/AdListener;
    //   51: ifnull +16 -> 67
    //   54: aload_0
    //   55: getfield 76	com/google/ads/d:d	Lcom/google/ads/AdListener;
    //   58: aload_0
    //   59: getfield 63	com/google/ads/d:c	Lcom/google/ads/Ad;
    //   62: invokeinterface 401 2 0
    //   67: aload_0
    //   68: monitorexit
    //   69: return
    //   70: astore_1
    //   71: aload_0
    //   72: monitorexit
    //   73: aload_1
    //   74: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	67	70	finally
  }

  /**
   * @deprecated
   */
  // ERROR //
  public final void r()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 74	com/google/ads/d:h	Lcom/google/ads/f;
    //   6: invokevirtual 403	com/google/ads/f:o	()V
    //   9: ldc_w 405
    //   12: invokestatic 271	com/google/ads/util/a:c	(Ljava/lang/String;)V
    //   15: aload_0
    //   16: getfield 76	com/google/ads/d:d	Lcom/google/ads/AdListener;
    //   19: ifnull +16 -> 35
    //   22: aload_0
    //   23: getfield 76	com/google/ads/d:d	Lcom/google/ads/AdListener;
    //   26: aload_0
    //   27: getfield 63	com/google/ads/d:c	Lcom/google/ads/Ad;
    //   30: invokeinterface 408 2 0
    //   35: aload_0
    //   36: monitorexit
    //   37: return
    //   38: astore_1
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_1
    //   42: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	35	38	finally
  }

  /**
   * @deprecated
   */
  // ERROR //
  public final void s()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc_w 410
    //   5: invokestatic 271	com/google/ads/util/a:c	(Ljava/lang/String;)V
    //   8: aload_0
    //   9: getfield 76	com/google/ads/d:d	Lcom/google/ads/AdListener;
    //   12: ifnull +16 -> 28
    //   15: aload_0
    //   16: getfield 76	com/google/ads/d:d	Lcom/google/ads/AdListener;
    //   19: aload_0
    //   20: getfield 63	com/google/ads/d:c	Lcom/google/ads/Ad;
    //   23: invokeinterface 413 2 0
    //   28: aload_0
    //   29: monitorexit
    //   30: return
    //   31: astore_1
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_1
    //   35: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	28	31	finally
  }

  /**
   * @deprecated
   */
  // ERROR //
  public final void t()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc_w 415
    //   5: invokestatic 271	com/google/ads/util/a:c	(Ljava/lang/String;)V
    //   8: aload_0
    //   9: getfield 76	com/google/ads/d:d	Lcom/google/ads/AdListener;
    //   12: ifnull +16 -> 28
    //   15: aload_0
    //   16: getfield 76	com/google/ads/d:d	Lcom/google/ads/AdListener;
    //   19: aload_0
    //   20: getfield 63	com/google/ads/d:c	Lcom/google/ads/Ad;
    //   23: invokeinterface 418 2 0
    //   28: aload_0
    //   29: monitorexit
    //   30: return
    //   31: astore_1
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_1
    //   35: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	28	31	finally
  }

  public final void u()
  {
    this.h.b();
    B();
  }

  /**
   * @deprecated
   */
  public final void v()
  {
    Activity localActivity;
    Iterator localIterator;
    monitorenter;
    try
    {
      localActivity = (Activity)this.b.get();
      if (localActivity == null)
        com.google.ads.util.a.e("activity was null while trying to ping tracking URLs.");
      do
      {
        monitorexit;
        return;
        localIterator = this.t.iterator();
      }
      while (!(localIterator.hasNext()));
    }
    finally
    {
      monitorexit;
    }
  }

  /**
   * @deprecated
   */
  // ERROR //
  final boolean w()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 146	com/google/ads/d:u	Ljava/util/LinkedList;
    //   6: invokevirtual 428	java/util/LinkedList:isEmpty	()Z
    //   9: istore_2
    //   10: iload_2
    //   11: ifne +9 -> 20
    //   14: iconst_1
    //   15: istore_3
    //   16: aload_0
    //   17: monitorexit
    //   18: iload_3
    //   19: ireturn
    //   20: iconst_0
    //   21: istore_3
    //   22: goto -6 -> 16
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	10	25	finally
  }

  /**
   * @deprecated
   */
  public final void x()
  {
    monitorenter;
    try
    {
      if (this.f == null)
        break label94;
      if (!(this.c instanceof AdView))
        break label85;
      label85: label94: if ((((AdView)this.c).isShown()) && (AdUtil.d()));
    }
    finally
    {
      while (true)
      {
        while (true)
        {
          monitorexit;
          throw localObject;
          com.google.ads.util.a.a("Tried to refresh an ad that wasn't an AdView.");
        }
        com.google.ads.util.a.a("Tried to refresh before calling loadAd().");
      }
    }
  }

  /**
   * @deprecated
   */
  // ERROR //
  public final void y()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_0
    //   4: putfield 82	com/google/ads/d:n	Z
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_1
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
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
  public final void z()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 78	com/google/ads/d:e	Lcom/google/ads/c;
    //   6: ifnull +15 -> 21
    //   9: aload_0
    //   10: getfield 78	com/google/ads/d:e	Lcom/google/ads/c;
    //   13: invokevirtual 445	com/google/ads/c:a	()V
    //   16: aload_0
    //   17: aconst_null
    //   18: putfield 78	com/google/ads/d:e	Lcom/google/ads/c;
    //   21: aload_0
    //   22: getfield 210	com/google/ads/d:j	Lcom/google/ads/h;
    //   25: invokevirtual 448	com/google/ads/h:stopLoading	()V
    //   28: aload_0
    //   29: monitorexit
    //   30: return
    //   31: astore_1
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_1
    //   35: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	28	31	finally
  }
}