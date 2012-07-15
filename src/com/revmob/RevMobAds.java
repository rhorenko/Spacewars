package com.revmob;

import android.app.Activity;
import com.revmob.ads.OnLoadListener;
import com.revmob.ads.fullscreen.Fullscreen;
import com.revmob.ads.popup.Popup;

public class RevMobAds
{
  public static Fullscreen createFullscreenAd(Activity paramActivity, String paramString)
  {
    return ((Fullscreen)new BlockingOnUIRunnable(paramActivity, new BlockingOnUIRunnableListener(paramString, paramActivity)
    {
      public Fullscreen onRunOnUIThread()
      {
        return new Fullscreen(this.val$appId, this.val$activity);
      }
    }).startOnUiAndWait());
  }

  public static void loadPopup(Activity paramActivity, String paramString, OnLoadListener paramOnLoadListener)
  {
    loadPopupWithUserAttributes(paramActivity, paramString, 0, 0, paramOnLoadListener);
  }

  public static void loadPopupWithUserAttributes(Activity paramActivity, String paramString, int paramInt1, int paramInt2, OnLoadListener paramOnLoadListener)
  {
    Popup.fetchAdvertisement(paramActivity, paramString, paramInt1, paramInt2, paramOnLoadListener);
  }

  public static void showFullscreenAd(Activity paramActivity, String paramString)
  {
    paramActivity.runOnUiThread(new Runnable(paramString, paramActivity)
    {
      public void run()
      {
        new Fullscreen(this.val$appId, this.val$activity).show();
      }
    });
  }

  public static void showPopup(Activity paramActivity, String paramString)
  {
    loadPopup(paramActivity, paramString, Popup.SHOW_IN_UI_THREAD_ON_SUCCESS_LISTENER);
  }
}