package com.revmob.ads.popup;

import android.app.Activity;
import com.revmob.ads.Ad;
import com.revmob.ads.AdBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PopupBuilder extends AdBuilder
{
  public PopupBuilder(Activity paramActivity)
  {
    super(paramActivity);
  }

  public Ad build(String paramString)
  {
    JSONObject localJSONObject;
    try
    {
      localJSONObject = new JSONObject(paramString);
      Popup localPopup = new Popup(localJSONObject.getJSONObject("pop_up").getString("message"), localJSONObject.getJSONObject("pop_up").getJSONArray("links").getJSONObject(0).getString("href"), this.activity);
      return localPopup;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        ??? = null;
    }
  }
}