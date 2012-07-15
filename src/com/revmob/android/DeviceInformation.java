package com.revmob.android;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import com.revmob.ads.EnvironmentConfig;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceInformation
{
  private static Map<Integer, String> ORIENTATIONS = new HashMap();
  private String android_id;
  private Context context;
  private String email;
  private String locale;
  private String macAddress;
  private String manufacturer;
  private String mobile_id;
  private String model;
  private String networkConnectionType;
  private String osVersion;

  static
  {
    ORIENTATIONS.put(Integer.valueOf(2), "landscape");
    ORIENTATIONS.put(Integer.valueOf(1), "portrait");
    ORIENTATIONS.put(Integer.valueOf(3), "square");
  }

  public DeviceInformation(Context paramContext)
  {
    this.context = paramContext;
    this.locale = getLocale();
    if (PermissionChecker.isPermissionEnabled(paramContext, "READ_PHONE_STATE"));
    try
    {
      this.mobile_id = EnvironmentConfig.getMobileId(paramContext);
      label131: if (PermissionChecker.isPermissionEnabled(paramContext, "ACCESS_WIFI_STATE"));
    }
    catch (Throwable localThrowable5)
    {
      try
      {
        this.macAddress = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        if (PermissionChecker.isPermissionEnabled(paramContext, "ACCESS_NETWORK_STATE"));
      }
      catch (Throwable localThrowable5)
      {
        try
        {
          this.networkConnectionType = networkConnection(paramContext);
          if (PermissionChecker.isPermissionEnabled(paramContext, "GET_ACCOUNTS"));
        }
        catch (Throwable localThrowable5)
        {
          try
          {
            String str = android.accounts.AccountManager.get(paramContext).getAccounts()[0].name;
            if (str.contains("@"))
              this.email = str;
          }
          catch (Throwable localThrowable5)
          {
            try
            {
              this.android_id = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
              this.model = Build.MODEL;
              this.manufacturer = Build.MANUFACTURER;
              this.osVersion = Build.VERSION.RELEASE;
              return;
            }
            catch (Throwable localThrowable1)
            {
              while (true)
              {
                while (true)
                {
                  while (true)
                  {
                    while (true)
                    {
                      break label131:
                      localThrowable2 = localThrowable2;
                    }
                    localThrowable3 = localThrowable3;
                  }
                  localThrowable4 = localThrowable4;
                }
                localThrowable5 = localThrowable5;
              }
            }
          }
        }
      }
    }
  }

  private JSONObject getIdentitiesJSON()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    putIfNotEmpty(localJSONObject, "email", this.email);
    putIfNotEmpty(localJSONObject, "mac_address", this.macAddress);
    putIfNotEmpty(localJSONObject, "mobile_id", this.mobile_id);
    putIfNotEmpty(localJSONObject, "android_id", this.android_id);
    return localJSONObject;
  }

  public static String getLanguage()
  {
    return Locale.getDefault().getLanguage();
  }

  public static String getLocale()
  {
    return Locale.getDefault().toString().replace('_', '-');
  }

  private boolean isConnectedTo(NetworkInfo paramNetworkInfo)
  {
    byte b = 0;
    if (paramNetworkInfo == null);
    while (true)
    {
      do
        return b;
      while ((paramNetworkInfo.getState() != NetworkInfo.State.CONNECTED) && (paramNetworkInfo.getState() != NetworkInfo.State.CONNECTING));
      b = 1;
    }
  }

  private String networkConnection(Context paramContext)
  {
    String str = null;
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (localConnectivityManager == null);
    while (true)
    {
      do
        while (true)
        {
          return str;
          if (!(isConnectedTo(localConnectivityManager.getNetworkInfo(0))))
            break;
          str = "wwan";
        }
      while (!(isConnectedTo(localConnectivityManager.getNetworkInfo(1))));
      str = "wifi";
    }
  }

  private void putIfNotEmpty(JSONObject paramJSONObject, String paramString1, String paramString2)
    throws JSONException
  {
    if ((paramString2 != null) && (!(paramString2.equals(""))))
      paramJSONObject.put(paramString1, paramString2);
  }

  public String getAndroidID()
  {
    return this.android_id;
  }

  public JSONObject getDeviceJSON()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject().put("identities", getIdentitiesJSON());
    putIfNotEmpty(localJSONObject, "model", this.model);
    putIfNotEmpty(localJSONObject, "manufacturer", this.manufacturer);
    putIfNotEmpty(localJSONObject, "os_version", this.osVersion);
    putIfNotEmpty(localJSONObject, "connection_speed", this.networkConnectionType);
    putIfNotEmpty(localJSONObject, "locale", this.locale);
    return localJSONObject;
  }

  public String getEmail()
  {
    return this.email;
  }

  public String getMACAddress()
  {
    return this.macAddress;
  }

  public String getManufacturer()
  {
    return this.manufacturer;
  }

  public String getMobileID()
  {
    return this.mobile_id;
  }

  public String getModel()
  {
    return this.model;
  }

  public String getNetworkConnectionType()
  {
    return this.networkConnectionType;
  }

  public String getOsVersion()
  {
    return this.osVersion;
  }

  public String getScreenOrientation()
  {
    Configuration localConfiguration = this.context.getResources().getConfiguration();
    return ((String)ORIENTATIONS.get(Integer.valueOf(localConfiguration.orientation)));
  }

  public boolean hasIdentifier()
  {
    byte b;
    if ((this.macAddress != null) || (this.email != null) || (this.mobile_id != null) || (this.android_id != null))
      b = 1;
    while (true)
    {
      return b;
      b = 0;
    }
  }
}