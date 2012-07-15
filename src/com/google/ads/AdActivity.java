package com.google.ads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.google.ads.util.AdUtil;
import com.google.ads.util.a;
import java.util.HashMap;

public class AdActivity extends Activity
  implements View.OnClickListener
{
  public static final String BASE_URL_PARAM = "baseurl";
  public static final String HTML_PARAM = "html";
  public static final String INTENT_ACTION_PARAM = "i";
  public static final String ORIENTATION_PARAM = "o";
  public static final String TYPE_PARAM = "m";
  public static final String URL_PARAM = "u";
  private static final Object a = new Object();
  private static AdActivity b = null;
  private static d c = null;
  private static AdActivity d = null;
  private static AdActivity e = null;
  private h f;
  private boolean g;
  private long h;
  private RelativeLayout i;
  private AdActivity j = null;
  private boolean k;
  private g l;

  private static RelativeLayout.LayoutParams a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramInt3, paramInt4);
    localLayoutParams.setMargins(paramInt1, paramInt2, 0, 0);
    localLayoutParams.addRule(10);
    localLayoutParams.addRule(9);
    return localLayoutParams;
  }

  private void a()
  {
    Object localObject1;
    if (!(this.g))
    {
      if (this.f != null)
      {
        a.b(this.f);
        this.f.a(null);
      }
      if (this.l != null)
      {
        this.l.d();
        this.l = null;
      }
      if (this == b)
        b = null;
      e = this.j;
      localObject1 = a;
      monitorenter;
    }
    try
    {
      if ((c != null) && (this.f != null))
      {
        if (this.f == c.i())
          c.a();
        this.f.stopLoading();
      }
      if (this == d)
      {
        d = null;
        if (c == null)
          break label151;
        c.r();
        c = null;
      }
      monitorexit;
      this.g = true;
      com.google.ads.util.a.a("AdActivity is closing.");
      label151: return;
    }
    finally
    {
      monitorexit;
    }
  }

  // ERROR //
  private void a(d paramd)
  {
    // Byte code:
    //   0: aload_0
    //   1: aconst_null
    //   2: putfield 82	com/google/ads/AdActivity:f	Lcom/google/ads/h;
    //   5: aload_0
    //   6: invokestatic 129	android/os/SystemClock:elapsedRealtime	()J
    //   9: putfield 131	com/google/ads/AdActivity:h	J
    //   12: aload_0
    //   13: iconst_1
    //   14: putfield 133	com/google/ads/AdActivity:k	Z
    //   17: getstatic 53	com/google/ads/AdActivity:a	Ljava/lang/Object;
    //   20: astore_2
    //   21: aload_2
    //   22: monitorenter
    //   23: getstatic 55	com/google/ads/AdActivity:b	Lcom/google/ads/AdActivity;
    //   26: ifnonnull +11 -> 37
    //   29: aload_0
    //   30: putstatic 55	com/google/ads/AdActivity:b	Lcom/google/ads/AdActivity;
    //   33: aload_1
    //   34: invokevirtual 136	com/google/ads/d:t	()V
    //   37: aload_2
    //   38: monitorexit
    //   39: return
    //   40: astore_3
    //   41: aload_2
    //   42: monitorexit
    //   43: aload_3
    //   44: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   23	39	40	finally
  }

  private void a(h paramh, boolean paramBoolean, int paramInt)
  {
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    if (paramh.getParent() != null)
      a("Interstitial created with an AdWebView that has a parent.");
    while (true)
    {
      do
      {
        while (true)
        {
          return;
          if (paramh.b() == null)
            break;
          a("Interstitial created with an AdWebView that is already in use by another AdActivity.");
        }
        setRequestedOrientation(paramInt);
        paramh.a(this);
        ImageButton localImageButton = new ImageButton(getApplicationContext());
        localImageButton.setImageResource(17301527);
        localImageButton.setBackgroundColor(0);
        localImageButton.setOnClickListener(this);
        localImageButton.setPadding(0, 0, 0, 0);
        int i1 = (int)TypedValue.applyDimension(1, 32.0F, getResources().getDisplayMetrics());
        FrameLayout localFrameLayout = new FrameLayout(getApplicationContext());
        localFrameLayout.addView(localImageButton, i1, i1);
        this.i.addView(paramh, -1, -1);
        RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
        localLayoutParams.addRule(10);
        localLayoutParams.addRule(9);
        this.i.addView(localFrameLayout, localLayoutParams);
        this.i.setKeepScreenOn(true);
        setContentView(this.i);
      }
      while (!(paramBoolean));
      a.a(paramh);
    }
  }

  private void a(String paramString)
  {
    com.google.ads.util.a.b(paramString);
    finish();
  }

  private void a(String paramString, Throwable paramThrowable)
  {
    com.google.ads.util.a.a(paramString, paramThrowable);
    finish();
  }

  // ERROR //
  public static boolean isShowing()
  {
    // Byte code:
    //   0: getstatic 53	com/google/ads/AdActivity:a	Ljava/lang/Object;
    //   3: astore_0
    //   4: aload_0
    //   5: monitorenter
    //   6: getstatic 59	com/google/ads/AdActivity:d	Lcom/google/ads/AdActivity;
    //   9: ifnull +9 -> 18
    //   12: iconst_1
    //   13: istore_2
    //   14: aload_0
    //   15: monitorexit
    //   16: iload_2
    //   17: ireturn
    //   18: iconst_0
    //   19: istore_2
    //   20: goto -6 -> 14
    //   23: astore_1
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_1
    //   27: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   6	16	23	finally
  }

  public static void launchAdActivity(d paramd, e parame)
  {
    Activity localActivity;
    Object localObject1 = a;
    monitorenter;
    try
    {
      if (c == null)
      {
        c = paramd;
        monitorexit;
        localActivity = paramd.e();
        if (localActivity != null)
          break label57;
      }
    }
    finally
    {
      monitorexit;
      throw localObject2;
      label57: Intent localIntent = new Intent(localActivity.getApplicationContext(), AdActivity.class);
      localIntent.putExtra("com.google.ads.AdOpener", parame.a());
      try
      {
        com.google.ads.util.a.a("Launching AdActivity.");
        localActivity.startActivity(localIntent);
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        while (true)
          com.google.ads.util.a.a(localActivityNotFoundException.getMessage(), localActivityNotFoundException);
      }
    }
  }

  public g getAdVideoView()
  {
    return this.l;
  }

  public h getOpeningAdWebView()
  {
    Object localObject1 = null;
    if (this.j != null)
    {
      localObject1 = this.j.f;
      return localObject1;
    }
    Object localObject2 = a;
    monitorenter;
    try
    {
    }
    finally
    {
      while (true)
      {
        while (true)
        {
          monitorexit;
          throw localObject3;
          h localh = c.i();
          if (localh == this.f)
            break;
          monitorexit;
          localObject1 = localh;
        }
        monitorexit;
      }
    }
  }

  public void moveAdVideoView(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.l != null)
    {
      this.l.setLayoutParams(a(paramInt1, paramInt2, paramInt3, paramInt4));
      this.l.requestLayout();
    }
  }

  public void newAdVideoView(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.l == null)
    {
      this.l = new g(this, this.f);
      this.i.addView(this.l, 0, a(paramInt1, paramInt2, paramInt3, paramInt4));
      Object localObject1 = a;
      monitorenter;
      try
      {
        if (c == null)
        {
          com.google.ads.util.a.e("currentAdManager was null while trying to get the opening AdWebView.");
          monitorexit;
          return;
        }
        c.j().a();
        monitorexit;
        return;
      }
      finally
      {
        localObject2 = finally;
        monitorexit;
        throw localObject2;
      }
    }
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    String str2;
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((getOpeningAdWebView() != null) && (paramIntent != null) && (paramIntent.getExtras() != null) && (paramIntent.getExtras().getString("com.google.circles.platform.result.extra.CONFIRMATION") != null) && (paramIntent.getExtras().getString("com.google.circles.platform.result.extra.ACTION") != null))
    {
      String str1 = paramIntent.getExtras().getString("com.google.circles.platform.result.extra.CONFIRMATION");
      str2 = paramIntent.getExtras().getString("com.google.circles.platform.result.extra.ACTION");
      if (str1.equals("yes"))
      {
        if (!(str2.equals("insert")))
          break label110;
        z.a(getOpeningAdWebView(), true);
      }
    }
    while (true)
    {
      do
      {
        finish();
        label110: return;
      }
      while (!(str2.equals("delete")));
      z.a(getOpeningAdWebView(), false);
    }
  }

  public void onClick(View paramView)
  {
    finish();
  }

  public void onCreate(Bundle paramBundle)
  {
    d locald;
    Ad localAd;
    super.onCreate(paramBundle);
    this.g = false;
    Object localObject1 = a;
    monitorenter;
    try
    {
      if (c != null)
      {
        locald = c;
        if (d == null)
        {
          d = this;
          locald.s();
        }
        if ((this.j == null) && (e != null))
          this.j = e;
        e = this;
        localAd = locald.f();
      }
    }
    finally
    {
      Bundle localBundle;
      Intent localIntent1;
      monitorexit;
      throw localObject2;
      e locale = new e(localBundle);
      String str1 = locale.b();
      HashMap localHashMap = locale.c();
      if (str1.equals("plusone"))
      {
        localIntent1 = new Intent();
        localIntent1.setComponent(new ComponentName("com.google.android.apps.plus", "com.google.android.apps.circles.platform.PlusOneActivity"));
        localIntent1.addCategory("android.intent.category.LAUNCHER");
        localIntent1.putExtras(getIntent().getExtras());
        localIntent1.putExtra("com.google.circles.platform.intent.extra.ENTITY", (String)localHashMap.get("u"));
        localIntent1.putExtra("com.google.circles.platform.intent.extra.ENTITY_TYPE", ab.b.a.c);
        localIntent1.putExtra("com.google.circles.platform.intent.extra.ACTION", (String)localHashMap.get("a"));
        a(locald);
      }
      try
      {
        com.google.ads.util.a.a("Launching Google+ intent from AdActivity.");
        label519: label728: startActivityForResult(localIntent1, 0);
      }
      catch (ActivityNotFoundException localActivityNotFoundException1)
      {
        String str6;
        Intent localIntent2;
        while (true)
        {
          while (true)
          {
            while (true)
              a(localActivityNotFoundException1.getMessage(), localActivityNotFoundException1);
            if (!(str1.equals("intent")))
              break label519;
            if (localHashMap != null)
              break;
            a("Could not get the paramMap in launchIntent()");
          }
          str6 = (String)localHashMap.get("u");
          if (str6 != null)
            break;
          a("Could not get the URL parameter in launchIntent().");
        }
        String str7 = (String)localHashMap.get("i");
        String str8 = (String)localHashMap.get("m");
        Uri localUri = Uri.parse(str6);
        if (str7 == null)
        {
          localIntent2 = new Intent("android.intent.action.VIEW", localUri);
          if (str8 != null)
            localIntent2.setDataAndType(localUri, str8);
          a(locald);
        }
        try
        {
          com.google.ads.util.a.a("Launching an intent from AdActivity.");
          startActivity(localIntent2);
        }
        catch (ActivityNotFoundException localActivityNotFoundException2)
        {
          while (true)
          {
            while (true)
            {
              while (true)
              {
                while (true)
                  a(localActivityNotFoundException2.getMessage(), localActivityNotFoundException2);
                localIntent2 = new Intent(str7, localUri);
              }
              this.i = new RelativeLayout(getApplicationContext());
              this.i.setGravity(17);
              if (str1.equals("webapp"))
              {
                String str5;
                int i2;
                this.f = new h(getApplicationContext(), null);
                i locali = new i(locald, a.b, true, true);
                locali.c();
                this.f.setWebViewClient(locali);
                String str2 = (String)localHashMap.get("u");
                String str3 = (String)localHashMap.get("baseurl");
                String str4 = (String)localHashMap.get("html");
                if (str2 != null)
                {
                  this.f.loadUrl(str2);
                  str5 = (String)localHashMap.get("o");
                  if (!("p".equals(str5)))
                    break label728;
                  i2 = AdUtil.b();
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
                            a(this.f, false, i2);
                          if (str4 == null)
                            break;
                          this.f.loadDataWithBaseURL(str3, str4, "text/html", "utf-8", null);
                        }
                        a("Could not get the URL or HTML parameter to show a web app.");
                      }
                      if (!("l".equals(str5)))
                        break;
                      i2 = AdUtil.a();
                    }
                    if (this != d)
                      break;
                    i2 = locald.m();
                  }
                  i2 = -1;
                }
              }
              if (!(str1.equals("interstitial")))
                break;
              this.f = locald.i();
              int i1 = locald.m();
              a(this.f, true, i1);
            }
            a("Unknown AdOpener, <action: " + str1 + ">");
          }
        }
      }
    }
  }

  public void onDestroy()
  {
    if (this.i != null)
      this.i.removeAllViews();
    if (isFinishing())
    {
      a();
      if (this.f != null)
      {
        this.f.stopLoading();
        this.f.destroy();
        this.f = null;
      }
    }
    super.onDestroy();
  }

  public void onPause()
  {
    if (isFinishing())
      a();
    super.onPause();
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    if ((this.k) && (paramBoolean) && (SystemClock.elapsedRealtime() - this.h > 250))
    {
      com.google.ads.util.a.d("Launcher AdActivity got focus and is closing.");
      finish();
    }
    super.onWindowFocusChanged(paramBoolean);
  }
}