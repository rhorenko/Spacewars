package com.bestcoolfungames.antsmasher;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class AdsUtils
{
  public static void configureBanner(Context paramContext, RelativeLayout paramRelativeLayout, Integer paramInteger)
  {
    View localView;
    byte b;
    try
    {
      localView = InitialView.getBannerView();
      if (!(noAds(paramContext)))
      {
        if (paramInteger == null)
          break label155;
        if (paramInteger.intValue() != 3)
          break label155:
      }
      while (true)
      {
        if ((localView != null) && (b == 0) && (!(InitialView.mmAdShowing)))
        {
          RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)localView.getLayoutParams();
          if (localView == null)
            break;
          RelativeLayout localRelativeLayout = (RelativeLayout)localView.getParent();
          if (localRelativeLayout != null)
            localRelativeLayout.removeAllViews();
          paramRelativeLayout.addView(localView, localLayoutParams);
          localLayoutParams.addRule(12);
          localView.setLayoutParams(localLayoutParams);
          if ((localView instanceof AdView) && (!(((AdView)localView).isReady())))
            ((AdView)localView).loadAd(new AdRequest());
        }
        return;
        b = 1;
      }
      label155: ??? = null;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      while (true)
      {
        while (true)
          localIllegalStateException.printStackTrace();
        b = 0;
      }
    }
  }

  public static boolean noAds(Context paramContext)
  {
    return paramContext.getSharedPreferences("AntSmasherShop", 0).getBoolean("NoAds", false);
  }

  public static void removeAllAdViews(Context paramContext, RelativeLayout paramRelativeLayout)
  {
    View localView;
    byte b;
    if (!(noAds(paramContext)))
    {
      localView = InitialView.getBannerView();
      if (!(localView instanceof AdView))
        break label41;
      b = 0;
      if (b < 5)
        break label26;
    }
    while (true)
    {
      while (true)
      {
        return;
        label26: paramRelativeLayout.removeView(InitialView.ad[b]);
        ++b;
      }
      label41: paramRelativeLayout.removeView(localView);
    }
  }
}