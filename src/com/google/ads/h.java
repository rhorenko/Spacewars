package com.google.ads;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View.MeasureSpec;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.ads.util.AdUtil;
import com.google.ads.util.a;
import java.lang.ref.WeakReference;

public final class h extends WebView
{
  private WeakReference<AdActivity> a;
  private AdSize b;

  public h(Context paramContext, AdSize paramAdSize)
  {
    super(paramContext);
    this.b = paramAdSize;
    this.a = null;
    setBackgroundColor(0);
    AdUtil.a(this);
    getSettings().setJavaScriptEnabled(true);
    setScrollBarStyle(0);
  }

  public final void a()
  {
    AdActivity localAdActivity = b();
    if (localAdActivity != null)
      localAdActivity.finish();
  }

  public final void a(AdActivity paramAdActivity)
  {
    this.a = new WeakReference(paramAdActivity);
  }

  public final AdActivity b()
  {
    AdActivity localAdActivity;
    if (this.a != null)
      localAdActivity = (AdActivity)this.a.get();
    while (true)
    {
      return localAdActivity;
      ??? = null;
    }
  }

  public final void destroy()
  {
    try
    {
      super.destroy();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        a.a("An error occurred while destroying an AdWebView:", localException);
    }
  }

  public final void loadDataWithBaseURL(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    try
    {
      super.loadDataWithBaseURL(paramString1, paramString2, paramString3, paramString4, paramString5);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        a.a("An error occurred while loading data in AdWebView:", localException);
    }
  }

  public final void loadUrl(String paramString)
  {
    try
    {
      super.loadUrl(paramString);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        a.a("An error occurred while loading a URL in AdWebView:", localException);
    }
  }

  protected final void onMeasure(int paramInt1, int paramInt2)
  {
    if (isInEditMode())
      super.onMeasure(paramInt1, paramInt2);
    while (true)
    {
      while (true)
      {
        int j;
        int l;
        float f;
        int i1;
        int i2;
        while (true)
        {
          while (true)
          {
            return;
            if (this.b != null)
              break;
            super.onMeasure(paramInt1, paramInt2);
          }
          int i = View.MeasureSpec.getMode(paramInt1);
          j = View.MeasureSpec.getSize(paramInt1);
          int k = View.MeasureSpec.getMode(paramInt2);
          l = View.MeasureSpec.getSize(paramInt2);
          f = getContext().getResources().getDisplayMetrics().density;
          i1 = (int)(f * this.b.getWidth());
          i2 = (int)(f * this.b.getHeight());
          if ((i != 0) && (k != 0))
            break;
          super.onMeasure(paramInt1, paramInt2);
        }
        if ((!(i1 - 6.0F * f < j)) && (i2 <= l))
          break;
        a.e("Not enough space to show ad! Wants: <" + i1 + ", " + i2 + ">, Has: <" + j + ", " + l + ">");
        setVisibility(8);
        setMeasuredDimension(0, 0);
      }
      super.onMeasure(paramInt1, paramInt2);
    }
  }

  public final void stopLoading()
  {
    try
    {
      super.stopLoading();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        a.a("An error occurred while stopping loading in AdWebView:", localException);
    }
  }
}