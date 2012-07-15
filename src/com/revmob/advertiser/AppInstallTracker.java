package com.revmob.advertiser;

import android.app.Activity;
import android.util.Log;
import com.revmob.ads.EnvironmentConfig;
import com.revmob.ads.HTTPHelper;
import com.revmob.android.DeviceInformation;
import com.revmob.android.StoredData;
import org.json.JSONException;
import org.json.JSONObject;

public class AppInstallTracker
{
  private static final String REVMOB = "RevMob";
  private String appId;
  private StoredData data;
  private DeviceInformation device;
  private HTTPHelper httpHelper;
  private Thread registerThread;

  public AppInstallTracker(Activity paramActivity, String paramString)
  {
    this(paramString, new HTTPHelper(), new DeviceInformation(paramActivity), new StoredData(paramActivity));
  }

  public AppInstallTracker(String paramString, HTTPHelper paramHTTPHelper, DeviceInformation paramDeviceInformation, StoredData paramStoredData)
  {
    Log.i("RevMob", "Tracking install with SDK version 3.1.1");
    this.data = paramStoredData;
    this.appId = paramString;
    this.device = paramDeviceInformation;
    this.httpHelper = paramHTTPHelper;
  }

  private String getInstallTrackingPayload()
  {
    String str2;
    try
    {
      str2 = new JSONObject().put("device", this.device.getDeviceJSON()).toString();
      String str1 = str2;
      return str1;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        ??? = null;
    }
  }

  public int postInstall()
  {
    int i;
    String str1 = "api/v4/mobile_apps/" + this.appId + "/install.json";
    String str2 = getInstallTrackingPayload();
    if (str2 == null)
      i = 0;
    while (true)
    {
      return i;
      String str3 = EnvironmentConfig.getFullServerUrl("https", str1);
      i = this.httpHelper.postAndGetStatusCode(str3, str2);
    }
  }

  public void registerInstall()
  {
    if (this.data.isAlreadyTracked())
      Log.i("RevMob", "Install already marked as registered, not registered.");
    while (true)
    {
      while (true)
      {
        return;
        if (this.device.hasIdentifier())
          break;
        Log.i("RevMob", "Device doesn't have identifier, not registered.");
      }
      Log.i("RevMob", "Registering Install");
      this.registerThread = new Thread(new Runnable(this)
      {
        public void run()
        {
          int i = this.this$0.postInstall();
          if ((i == 200) || (i == 202))
          {
            Log.i("RevMob", "Install registered on server");
            AppInstallTracker.access$000(this.this$0).markAsTracked();
          }
          while (true)
          {
            return;
            Log.i("RevMob", "Install not registered on server. Did you set a valid App ID? If not, collect one at http://revmob.com Status:" + i);
          }
        }
      });
      this.registerThread.start();
    }
  }
}