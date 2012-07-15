package com.revmob.ads.banner;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import com.revmob.ads.Ad;
import com.revmob.ads.AdWithImageBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BannerBuilder extends AdWithImageBuilder
{
  public BannerBuilder(Activity paramActivity)
  {
    super(paramActivity);
  }

  public Ad build(String paramString)
  {
    JSONArray localJSONArray1;
    try
    {
      localJSONArray1 = new JSONObject(paramString).getJSONArray("banners");
      int i = 0;
      if (i >= localJSONArray1.length())
        break label101;
      JSONArray localJSONArray2 = ((JSONObject)localJSONArray1.get(i)).getJSONArray("links");
      Drawable localDrawable = retrieveDrawable(localJSONArray2);
      String str = getClickUrl(localJSONArray2);
      if ((localDrawable != null) && (str != null))
      {
        BannerAd localBannerAd = new BannerAd(localDrawable, str, this.activity);
        return localBannerAd;
      }
      label101: ++i;
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        while (true)
          ??? = null;
        ??? = null;
      }
    }
  }
}