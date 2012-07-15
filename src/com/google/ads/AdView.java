package com.google.ads;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.google.ads.util.AdUtil;
import com.google.ads.util.a;

public class AdView extends RelativeLayout
  implements Ad
{
  private d a;

  public AdView(Activity paramActivity, AdSize paramAdSize, String paramString)
  {
    super(paramActivity.getApplicationContext());
    a(paramActivity, paramAdSize, null);
    b(paramActivity, paramAdSize, null);
    a(paramActivity, paramAdSize, paramString);
  }

  public AdView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramContext, paramAttributeSet);
  }

  public AdView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    this(paramContext, paramAttributeSet);
  }

  private void a(Activity paramActivity, AdSize paramAdSize, String paramString)
  {
    this.a = new d(paramActivity, this, paramAdSize, paramString, false);
    setGravity(17);
    setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
    int i = (int)TypedValue.applyDimension(1, paramAdSize.getWidth(), paramActivity.getResources().getDisplayMetrics());
    int j = (int)TypedValue.applyDimension(1, paramAdSize.getHeight(), paramActivity.getResources().getDisplayMetrics());
    addView(this.a.i(), i, j);
  }

  private void a(Context paramContext, AttributeSet paramAttributeSet)
  {
    String str1;
    AdSize localAdSize;
    String str2;
    String str3;
    String str9;
    String str10;
    TypedValue localTypedValue2;
    if (paramAttributeSet == null);
    while (true)
    {
      return;
      str1 = paramAttributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.google.ads", "adSize");
      if (str1 != null)
        break;
      a(paramContext, "AdView missing required XML attribute \"adSize\".", AdSize.BANNER, paramAttributeSet);
    }
    if ("BANNER".equals(str1))
    {
      localAdSize = AdSize.BANNER;
      str2 = paramAttributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.google.ads", "testDevices");
      if ((str2 != null) && (str2.startsWith("@string/")))
      {
        str9 = str2.substring("@string/".length());
        str10 = paramContext.getPackageName();
        localTypedValue2 = new TypedValue();
      }
    }
    try
    {
      while (true)
      {
        while (true)
        {
          while (true)
          {
            while (true)
            {
              getResources().getValue(str10 + ":string/" + str9, localTypedValue2, true);
              if (localTypedValue2.string == null)
                break label310;
              str2 = localTypedValue2.string.toString();
              str3 = paramAttributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.google.ads", "adUnitId");
              if (str3 != null)
                break label346;
              a(paramContext, "AdView missing required XML attribute \"adUnitId\".", localAdSize, paramAttributeSet);
            }
            if (!("IAB_MRECT".equals(str1)))
              break;
            localAdSize = AdSize.IAB_MRECT;
          }
          if (!("IAB_BANNER".equals(str1)))
            break;
          localAdSize = AdSize.IAB_BANNER;
        }
        if (!("IAB_LEADERBOARD".equals(str1)))
          break;
        localAdSize = AdSize.IAB_LEADERBOARD;
      }
      label673: label683: label310: label346: label764: label637: a(paramContext, "Invalid \"adSize\" value in XML layout: " + str1 + ".", AdSize.BANNER, paramAttributeSet);
    }
    catch (Resources.NotFoundException localNotFoundException2)
    {
      AdRequest localAdRequest;
      String str6;
      String str7;
      String str8;
      TypedValue localTypedValue1;
      while (true)
      {
        while (true)
        {
          while (true)
            a(paramContext, "Could not find resource for \"testDevices\": \"" + str2 + "\".", localAdSize, paramAttributeSet);
          a(paramContext, "\"testDevices\" was not a string: \"" + str2 + "\".", localAdSize, paramAttributeSet);
        }
        if (!(isInEditMode()))
          break;
        a(paramContext, "Ads by Google", -1, localAdSize, paramAttributeSet);
      }
      if (str3.startsWith("@string/"))
      {
        str7 = str3.substring("@string/".length());
        str8 = paramContext.getPackageName();
        localTypedValue1 = new TypedValue();
      }
      try
      {
        boolean bool;
        do
        {
          getResources().getValue(str8 + ":string/" + str7, localTypedValue1, true);
          if (localTypedValue1.string == null)
            break label637;
          str3 = localTypedValue1.string.toString();
          bool = paramAttributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/lib/com.google.ads", "loadAdOnCreate", false);
          if (!(paramContext instanceof Activity))
            break label764;
          Activity localActivity = (Activity)paramContext;
          a(localActivity, localAdSize, paramAttributeSet);
          b(localActivity, localAdSize, paramAttributeSet);
          a(localActivity, localAdSize, str3);
        }
        while (!(bool));
        localAdRequest = new AdRequest();
        if (str2 == null)
          break label683;
        String[] arrayOfString2 = str2.split(",");
        int k = arrayOfString2.length;
        int l = 0;
        if (l >= k)
          break label683;
        str6 = arrayOfString2[l].trim();
        if (str6.length() != 0)
        {
          if (!(str6.equals("TEST_EMULATOR")))
            break label673;
          localAdRequest.addTestDevice(AdRequest.TEST_EMULATOR);
        }
        ++l;
      }
      catch (Resources.NotFoundException localNotFoundException1)
      {
        while (true)
        {
          while (true)
          {
            while (true)
            {
              while (true)
              {
                while (true)
                  a(paramContext, "Could not find resource for \"adUnitId\": \"" + str3 + "\".", localAdSize, paramAttributeSet);
                a(paramContext, "\"adUnitId\" was not a string: \"" + str3 + "\".", localAdSize, paramAttributeSet);
              }
              localAdRequest.addTestDevice(str6);
            }
            String str4 = paramAttributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.google.ads", "keywords");
            if (str4 != null)
            {
              String[] arrayOfString1 = str4.split(",");
              int i = arrayOfString1.length;
              for (int j = 0; j < i; ++j)
              {
                String str5 = arrayOfString1[j].trim();
                if (str5.length() != 0)
                  localAdRequest.addKeyword(str5);
              }
            }
            loadAd(localAdRequest);
          }
          a.b("AdView was initialized with a Context that wasn't an Activity.");
        }
      }
    }
  }

  private void a(Context paramContext, String paramString, int paramInt, AdSize paramAdSize, AttributeSet paramAttributeSet)
  {
    TextView localTextView;
    LinearLayout localLinearLayout1;
    LinearLayout localLinearLayout2;
    if (getChildCount() == 0)
    {
      if (paramAttributeSet != null)
        break label172;
      localTextView = new TextView(paramContext);
      localTextView.setGravity(17);
      localTextView.setText(paramString);
      localTextView.setTextColor(paramInt);
      localTextView.setBackgroundColor(-16777216);
      if (paramAttributeSet != null)
        break label187;
      localLinearLayout1 = new LinearLayout(paramContext);
      localLinearLayout1.setGravity(17);
      if (paramAttributeSet != null)
        break label202;
      localLinearLayout2 = new LinearLayout(paramContext);
    }
    while (true)
    {
      while (true)
      {
        while (true)
        {
          localLinearLayout2.setGravity(17);
          localLinearLayout2.setBackgroundColor(paramInt);
          int i = (int)TypedValue.applyDimension(1, paramAdSize.getWidth(), paramContext.getResources().getDisplayMetrics());
          int j = (int)TypedValue.applyDimension(1, paramAdSize.getHeight(), paramContext.getResources().getDisplayMetrics());
          localLinearLayout1.addView(localTextView, i - 2, j - 2);
          localLinearLayout2.addView(localLinearLayout1);
          addView(localLinearLayout2, i, j);
          return;
          label172: localTextView = new TextView(paramContext, paramAttributeSet);
        }
        label187: localLinearLayout1 = new LinearLayout(paramContext, paramAttributeSet);
      }
      label202: localLinearLayout2 = new LinearLayout(paramContext, paramAttributeSet);
    }
  }

  private void a(Context paramContext, String paramString, AdSize paramAdSize, AttributeSet paramAttributeSet)
  {
    a.b(paramString);
    a(paramContext, paramString, -65536, paramAdSize, paramAttributeSet);
    if (!(isInEditMode()))
    {
      if (!(paramContext instanceof Activity))
        break label43;
      a((Activity)paramContext, paramAdSize, "");
    }
    while (true)
    {
      return;
      label43: a.b("AdView was initialized with a Context that wasn't an Activity.");
    }
  }

  private boolean a(Context paramContext, AdSize paramAdSize, AttributeSet paramAttributeSet)
  {
    byte b;
    if (!(AdUtil.c(paramContext)))
    {
      a(paramContext, "You must have AdActivity declared in AndroidManifest.xml with configChanges.", paramAdSize, paramAttributeSet);
      b = 0;
    }
    while (true)
    {
      return b;
      b = 1;
    }
  }

  private boolean b(Context paramContext, AdSize paramAdSize, AttributeSet paramAttributeSet)
  {
    byte b;
    if (!(AdUtil.b(paramContext)))
    {
      a(paramContext, "You must have INTERNET and ACCESS_NETWORK_STATE permissions in AndroidManifest.xml.", paramAdSize, paramAttributeSet);
      b = 0;
    }
    while (true)
    {
      return b;
      b = 1;
    }
  }

  public void destroy()
  {
    this.a.b();
  }

  public boolean isReady()
  {
    byte b;
    if (this.a == null)
      b = 0;
    while (true)
    {
      return b;
      boolean bool = this.a.o();
    }
  }

  public boolean isRefreshing()
  {
    return this.a.p();
  }

  public void loadAd(AdRequest paramAdRequest)
  {
    if (isRefreshing())
      this.a.c();
    this.a.a(paramAdRequest);
  }

  public void setAdListener(AdListener paramAdListener)
  {
    this.a.a(paramAdListener);
  }

  public void stopLoading()
  {
    this.a.z();
  }
}