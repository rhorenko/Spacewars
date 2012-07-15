package com.revmob.ads;

import android.app.Activity;
import android.util.Log;
import com.revmob.android.DeviceInformation;
import org.json.JSONException;
import org.json.JSONObject;

public class AdFetcher
{
  private static final String REVMOB = "RevMob";
  private static final String REVMOB_API_PATH = "api/v4/mobile_apps/%s/%s/fetch.json";
  protected Activity activity;
  protected String appId;
  protected AdBuilder builder;
  protected DeviceInformation device;
  private Thread fetchThread;
  protected HTTPHelper httpHelper;

  public AdFetcher(HTTPHelper paramHTTPHelper, DeviceInformation paramDeviceInformation, AdBuilder paramAdBuilder)
  {
    this.httpHelper = paramHTTPHelper;
    this.device = paramDeviceInformation;
    this.builder = paramAdBuilder;
  }

  public static String url(String paramString1, String paramString2)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramString2;
    arrayOfObject[1] = paramString1;
    return EnvironmentConfig.getFullServerUrl("https", String.format("api/v4/mobile_apps/%s/%s/fetch.json", arrayOfObject));
  }

  public void fetch(String paramString, JSONObject paramJSONObject, OnLoadListener paramOnLoadListener)
  {
    Log.d("RevMob", "Fetching Ad with SDK version 3.1.1");
    Log.d("RevMob", "url: " + paramString);
    Log.d("RevMob", "POST: " + paramJSONObject);
    if (!(this.device.hasIdentifier()))
    {
      Log.i("RevMob", "Ad retrieval failed: Device Not Identified");
      paramOnLoadListener.onAdNotReceived(LoadError.DEVICE_NOT_IDENTIFIED, null);
    }
    while (true)
    {
      return;
      this.fetchThread = new Thread(this, paramString, paramJSONObject, paramOnLoadListener)
      {
        public void run()
        {
          String str = this.this$0.httpHelper.postAndGetResponse(this.val$url, this.val$entity.toString());
          if (str == null)
          {
            Log.i("RevMob", "Ad retrieval failed: Advertisement couldn't be fetched");
            this.val$listener.onAdNotReceived(LoadError.DOWNLOAD_ERROR, null);
          }
          while (true)
          {
            Ad localAd;
            while (true)
            {
              return;
              localAd = this.this$0.builder.build(str);
              if (localAd != null)
                break;
              Log.i("RevMob", "No ad retrieved: did you set a valid App ID? If not, collect one at http://revmob.com");
              this.val$listener.onAdNotReceived(LoadError.PARSE_FAILED, str);
            }
            Log.d("RevMob", "Ad retrieved");
            this.val$listener.onAdReceived(localAd);
          }
        }
      };
      this.fetchThread.start();
    }
  }

  public JSONObject getFetchEntity(DeviceInformation paramDeviceInformation, JSONObject paramJSONObject)
  {
    JSONObject localJSONObject;
    try
    {
      localJSONObject = new JSONObject();
      localJSONObject.put("device", paramDeviceInformation.getDeviceJSON());
      localJSONObject.put("sdk", new JSONObject().put("version", "3.1.1"));
      if (paramJSONObject != null)
        localJSONObject.put("ad", paramJSONObject);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        ??? = null;
    }
  }
}