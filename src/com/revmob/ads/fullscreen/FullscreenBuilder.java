package com.revmob.ads.fullscreen;

import android.app.Activity;
import com.revmob.ads.Ad;
import com.revmob.ads.AdWithImageBuilder;
import org.json.JSONException;
import org.json.JSONObject;

public class FullscreenBuilder extends AdWithImageBuilder
{
  public FullscreenBuilder(Activity paramActivity)
  {
    super(paramActivity);
  }

  public Ad build(String paramString)
  {
    Object localObject = null;
    try
    {
      String str = getClickUrl(new JSONObject(paramString).getJSONObject("fullscreen").getJSONArray("links"));
      if (str == null)
        break label57:
      FullscreenAd localFullscreenAd = new FullscreenAd(null, str, this.activity);
      localObject = localFullscreenAd;
    }
    catch (JSONException localJSONException)
    {
    }
    label57: return localObject;
  }
}