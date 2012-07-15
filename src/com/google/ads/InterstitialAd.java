package com.google.ads;

import android.app.Activity;
import com.google.ads.util.a;

public class InterstitialAd
  implements Ad
{
  private d a;

  public InterstitialAd(Activity paramActivity, String paramString)
  {
    this(paramActivity, paramString, false);
  }

  public InterstitialAd(Activity paramActivity, String paramString, boolean paramBoolean)
  {
    this.a = new d(paramActivity, this, null, paramString, paramBoolean);
  }

  public boolean isReady()
  {
    return this.a.o();
  }

  public void loadAd(AdRequest paramAdRequest)
  {
    this.a.a(paramAdRequest);
  }

  public void setAdListener(AdListener paramAdListener)
  {
    this.a.a(paramAdListener);
  }

  public void show()
  {
    if (isReady())
    {
      this.a.y();
      this.a.v();
      AdActivity.launchAdActivity(this.a, new e("interstitial"));
    }
    while (true)
    {
      return;
      a.c("Cannot show interstitial because it is not loaded and ready.");
    }
  }

  public void stopLoading()
  {
    this.a.z();
  }
}