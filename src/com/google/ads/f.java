package com.google.ads;

import android.os.SystemClock;
import com.google.ads.util.a;
import java.util.LinkedList;

public final class f
{
  private static long f = 0L;
  public String a;
  private LinkedList<Long> b;
  private long c;
  private long d;
  private LinkedList<Long> e;
  private String g;
  private boolean h = false;
  private boolean i = false;

  f()
  {
    this.b = new LinkedList();
    this.e = new LinkedList();
    a();
  }

  static long i()
  {
    return f;
  }

  final void a()
  {
    this.b.clear();
    this.c = 0L;
    this.d = 0L;
    this.e.clear();
    this.g = null;
    this.b = false;
    this.i = false;
  }

  public final void a(String paramString)
  {
    a.d("Prior ad identifier = " + paramString);
    this.g = paramString;
  }

  final void b()
  {
    a.d("Ad clicked.");
    this.b.add(Long.valueOf(SystemClock.elapsedRealtime()));
  }

  public final void b(String paramString)
  {
    a.d("Prior impression ticket = " + paramString);
    this.a = paramString;
  }

  final void c()
  {
    a.d("Ad request loaded.");
    this.c = SystemClock.elapsedRealtime();
  }

  final void d()
  {
    a.d("Ad request started.");
    this.d = SystemClock.elapsedRealtime();
    f = 1L + f;
  }

  final long e()
  {
    long l;
    if (this.b.size() != this.e.size())
      l = -1;
    while (true)
    {
      return l;
      l = this.b.size();
    }
  }

  final String f()
  {
    String str;
    if ((this.b.isEmpty()) || (this.b.size() != this.e.size()))
      str = null;
    while (true)
    {
      return str;
      StringBuilder localStringBuilder = new StringBuilder();
      for (int j = 0; j < this.b.size(); ++j)
      {
        if (j != 0)
          localStringBuilder.append(",");
        localStringBuilder.append(Long.toString(((Long)this.e.get(j)).longValue() - ((Long)this.b.get(j)).longValue()));
      }
      str = localStringBuilder.toString();
    }
  }

  final String g()
  {
    String str;
    if (this.b.isEmpty())
      str = null;
    while (true)
    {
      return str;
      StringBuilder localStringBuilder = new StringBuilder();
      for (int j = 0; j < this.b.size(); ++j)
      {
        if (j != 0)
          localStringBuilder.append(",");
        localStringBuilder.append(Long.toString(((Long)this.b.get(j)).longValue() - this.c));
      }
      str = localStringBuilder.toString();
    }
  }

  final long h()
  {
    return (this.c - this.d);
  }

  final String j()
  {
    return this.g;
  }

  final boolean k()
  {
    return this.b;
  }

  final void l()
  {
    a.d("Interstitial network error.");
    this.b = true;
  }

  final boolean m()
  {
    return this.i;
  }

  final void n()
  {
    a.d("Interstitial no fill.");
    this.i = true;
  }

  public final void o()
  {
    a.d("Landing page dismissed.");
    this.e.add(Long.valueOf(SystemClock.elapsedRealtime()));
  }

  final String p()
  {
    return this.a;
  }
}