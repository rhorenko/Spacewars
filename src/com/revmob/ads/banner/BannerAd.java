package com.revmob.ads.banner;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import com.revmob.ads.Ad;

public class BannerAd extends Ad
{
  private Drawable drawable;

  public BannerAd(Drawable paramDrawable, String paramString, Activity paramActivity)
  {
    super(paramActivity, paramString);
    this.drawable = paramDrawable;
  }

  public Drawable getDrawable()
  {
    return this.drawable;
  }
}