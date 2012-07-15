package com.revmob;

import android.app.Activity;
import com.revmob.advertiser.AppInstallTracker;

public class RevMobAdvertiser
{
  public static void registerInstall(Activity paramActivity, String paramString)
  {
    new AppInstallTracker(paramActivity, paramString).registerInstall();
  }
}