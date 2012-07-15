package com.revmob.ads.fullscreen;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import com.revmob.ads.Ad;

public class FullscreenAd extends Ad
{
  private Drawable adImage;

  public FullscreenAd(Drawable paramDrawable, String paramString, Activity paramActivity)
  {
    super(paramActivity, paramString);
    this.adImage = paramDrawable;
  }

  public Drawable getAdImage()
  {
    Drawable localDrawable;
    if (this.adImage != null)
      localDrawable = this.adImage;
    while (true)
    {
      return localDrawable;
      localDrawable = StaticAssets.getLocalizedDrawable();
    }
  }
}