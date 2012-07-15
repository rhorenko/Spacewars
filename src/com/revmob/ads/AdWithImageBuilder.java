package com.revmob.ads;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AdWithImageBuilder extends AdBuilder
{
  public AdWithImageBuilder(Activity paramActivity)
  {
    super(paramActivity);
  }

  public static Drawable downloadImage(String paramString)
  {
    return Drawable.createFromStream(new HTTPHelper().getAndReturnTheStream(paramString), "src");
  }

  protected String getClickUrl(JSONArray paramJSONArray)
    throws JSONException
  {
    String str;
    int i = 0;
    if (i < paramJSONArray.length())
      if (paramJSONArray.getJSONObject(i).getString("rel").equals("clicks"))
        str = paramJSONArray.getJSONObject(i).getString("href");
    while (true)
    {
      while (true)
      {
        return str;
        ++i;
      }
      ??? = null;
    }
  }

  protected Drawable retrieveDrawable(JSONArray paramJSONArray)
    throws JSONException
  {
    Drawable localDrawable;
    int i = 0;
    if (i < paramJSONArray.length())
      if (paramJSONArray.getJSONObject(i).getString("rel").equals("image"))
        localDrawable = downloadImage(paramJSONArray.getJSONObject(i).getString("href"));
    while (true)
    {
      while (true)
      {
        return localDrawable;
        ++i;
      }
      ??? = null;
    }
  }
}