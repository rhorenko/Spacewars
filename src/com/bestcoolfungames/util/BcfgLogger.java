package com.bestcoolfungames.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class BcfgLogger
{
  public static final String from_to_format = "About_Updated_from_v%s_to_v%s";
  public static final String param_about = "About";
  public static final String param_about_installation_source = "About_TheInstallationSource";
  public static final String param_about_updated = "About_Updated";
  private SharedPreferences.Editor editor = null;
  private SharedPreferences settings;
  private Context theContext;

  private BcfgLogger(Context paramContext)
  {
    if (paramContext == null)
      throw new NullPointerException();
    this.theContext = paramContext;
    this.settings = this.theContext.getSharedPreferences("About", 0);
    reportInstallerPackageNameIfNecessary();
    didUpdateAppIfNecessary();
    commitEditorIfNecessary();
  }

  public static void SendInfoIfNecessary(Context paramContext)
  {
    new BcfgLogger(paramContext);
  }

  private void commitEditorIfNecessary()
  {
    if (this.editor != null)
      this.editor.commit();
  }

  private void didUpdateAppIfNecessary()
  {
    String str3;
    String str1 = this.settings.getString("About_Updated", "0.0.00");
    String str2 = getAppVersion();
    Log.i("AppVersion", "Old: " + str1 + " New: " + str2);
    if (!(str1.equals(str2)))
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = str1;
      arrayOfObject[1] = str2;
      str3 = String.format("About_Updated_from_v%s_to_v%s", arrayOfObject);
      getEditor().putString("About_Updated", str2);
    }
    try
    {
      GoogleAnalyticsTracker.getInstance().trackEvent("About", "About_Updated", str3, -1);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  private SharedPreferences.Editor getEditor()
  {
    if (this.editor == null)
      this.editor = this.settings.edit();
    return this.editor;
  }

  private void reportInstallerPackageNameIfNecessary()
  {
    String str;
    if (!(Boolean.valueOf(this.settings.getBoolean("About_TheInstallationSource", false)).booleanValue()))
    {
      str = this.theContext.getPackageManager().getInstallerPackageName(this.theContext.getPackageName());
      if (str == null)
        str = "null";
      getEditor().putBoolean("About_TheInstallationSource", true);
      Log.i("AppSource", "InstalationSource: " + str);
    }
    try
    {
      GoogleAnalyticsTracker.getInstance().trackEvent("About", "About_TheInstallationSource", str, -1);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public String getAppVersion()
  {
    String str;
    try
    {
      str = this.theContext.getPackageManager().getPackageInfo(this.theContext.getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
      {
        localNameNotFoundException.printStackTrace();
        ??? = null;
      }
    }
  }
}