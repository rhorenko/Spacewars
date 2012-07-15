package com.google.android.apps.analytics;

import [Ljava.lang.String;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.HashMap;

public class AnalyticsReceiver extends BroadcastReceiver
{
  private static final String INSTALL_ACTION = "com.android.vending.INSTALL_REFERRER";

  static String formatReferrer(String paramString)
  {
    byte b1;
    byte b2;
    byte b3;
    String str1;
    String[] arrayOfString9;
    String[] arrayOfString1 = paramString.split("&");
    HashMap localHashMap = new HashMap();
    int i = arrayOfString1.length;
    int j = 0;
    if (j < i)
    {
      arrayOfString9 = arrayOfString1[j].split("=");
      if (arrayOfString9.length == 2)
        break label110;
    }
    if (localHashMap.get("utm_campaign") != null)
    {
      b1 = 1;
      if (localHashMap.get("utm_medium") == null)
        break label135;
      b2 = 1;
      if (localHashMap.get("utm_source") == null)
        break label141;
      b3 = 1;
      if ((b1 != 0) && (b2 != 0) && (b3 != 0))
        break label147;
      Log.w("GoogleAnalyticsTracker", "Badly formatted referrer missing campaign, name or source");
      str1 = null;
    }
    while (true)
    {
      while (true)
      {
        while (true)
        {
          while (true)
          {
            while (true)
            {
              return str1;
              label110: localHashMap.put(arrayOfString9[0], arrayOfString9[1]);
              ++j;
            }
            b1 = 0;
          }
          label135: b2 = 0;
        }
        label141: b3 = 0;
      }
      label147: String[] arrayOfString; = new String[7][];
      String[] arrayOfString2 = new String[2];
      arrayOfString2[0] = "utmcid";
      arrayOfString2[1] = ((String)localHashMap.get("utm_id"));
      arrayOfString;[0] = arrayOfString2;
      String[] arrayOfString3 = new String[2];
      arrayOfString3[0] = "utmcsr";
      arrayOfString3[1] = ((String)localHashMap.get("utm_source"));
      arrayOfString;[1] = arrayOfString3;
      String[] arrayOfString4 = new String[2];
      arrayOfString4[0] = "utmgclid";
      arrayOfString4[1] = ((String)localHashMap.get("gclid"));
      arrayOfString;[2] = arrayOfString4;
      String[] arrayOfString5 = new String[2];
      arrayOfString5[0] = "utmccn";
      arrayOfString5[1] = ((String)localHashMap.get("utm_campaign"));
      arrayOfString;[3] = arrayOfString5;
      String[] arrayOfString6 = new String[2];
      arrayOfString6[0] = "utmcmd";
      arrayOfString6[1] = ((String)localHashMap.get("utm_medium"));
      arrayOfString;[4] = arrayOfString6;
      String[] arrayOfString7 = new String[2];
      arrayOfString7[0] = "utmctr";
      arrayOfString7[1] = ((String)localHashMap.get("utm_term"));
      arrayOfString;[5] = arrayOfString7;
      String[] arrayOfString8 = new String[2];
      arrayOfString8[0] = "utmcct";
      arrayOfString8[1] = ((String)localHashMap.get("utm_content"));
      arrayOfString;[6] = arrayOfString8;
      StringBuilder localStringBuilder = new StringBuilder();
      int k = 0;
      byte b4 = 1;
      if (k < arrayOfString;.length)
      {
        String str2;
        if (arrayOfString;[k][1] != null)
        {
          str2 = arrayOfString;[k][1].replace("+", "%20").replace(" ", "%20");
          if (b4 == 0)
            break label465;
          b4 = 0;
        }
        while (true)
        {
          while (true)
          {
            localStringBuilder.append(arrayOfString;[k][0]).append("=").append(str2);
            ++k;
          }
          label465: localStringBuilder.append("|");
        }
      }
      str1 = localStringBuilder.toString();
    }
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str1 = paramIntent.getStringExtra("referrer");
    if ((!("com.android.vending.INSTALL_REFERRER".equals(paramIntent.getAction()))) || (str1 == null));
    while (true)
    {
      while (true)
      {
        return;
        String str2 = formatReferrer(str1);
        if (str2 == null)
          break;
        new PersistentEventStore(new PersistentEventStore.DataBaseHelper(paramContext)).setReferrer(str2);
        Log.d("GoogleAnalyticsTracker", "Stored referrer:" + str2);
      }
      Log.w("GoogleAnalyticsTracker", "Badly formatted referrer, ignored");
    }
  }
}