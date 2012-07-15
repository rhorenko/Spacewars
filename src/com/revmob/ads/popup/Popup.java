package com.revmob.ads.popup;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.WindowManager.BadTokenException;
import com.revmob.ads.Ad;
import com.revmob.ads.AdFetcher;
import com.revmob.ads.HTTPHelper;
import com.revmob.ads.LoadError;
import com.revmob.ads.OnLoadListener;
import com.revmob.android.DeviceInformation;

public class Popup extends Ad
{
  public static final OnLoadListener SHOW_IN_UI_THREAD_ON_SUCCESS_LISTENER = new OnLoadListener()
  {
    public void onAdNotReceived(, String paramString)
    {
    }

    public void onAdReceived()
    {
      ((Popup)paramAd).show(this);
    }
  };
  private final DialogInterface.OnClickListener POPUP_CLICK_LISTENER_NO;
  private final DialogInterface.OnClickListener POPUP_CLICK_LISTENER_YES;
  private String message;

  public Popup(String paramString1, String paramString2, Activity paramActivity)
  {
    super(paramActivity, paramString2);
    this.POPUP_CLICK_LISTENER_YES = new DialogInterface.OnClickListener(this)
    {
      public void onClick(, int paramInt)
      {
        this.this$0.click();
      }
    };
    this.POPUP_CLICK_LISTENER_NO = new DialogInterface.OnClickListener(this)
    {
      public void onClick(, int paramInt)
      {
        paramDialogInterface.dismiss();
      }
    };
    this.message = paramString1;
  }

  private void buildAndDisplayDialog(OnLoadListener paramOnLoadListener)
  {
    try
    {
      if ((this.activity != null) && (!(this.activity.isFinishing())))
        new AlertDialog.Builder(this.activity).setTitle(this.message).setPositiveButton("Yes, sure!", this.POPUP_CLICK_LISTENER_YES).setNegativeButton("No, thanks.", this.POPUP_CLICK_LISTENER_NO).show();
      return;
    }
    catch (WindowManager.BadTokenException localBadTokenException)
    {
      while (true)
      {
        Log.i("RevMob", "Invalid activity as argument: is there an activity running?");
        paramOnLoadListener.onAdNotReceived(LoadError.INVALID_ACTIVITY, null);
      }
    }
  }

  public static void fetchAdvertisement(Activity paramActivity, String paramString, int paramInt1, int paramInt2, OnLoadListener paramOnLoadListener)
  {
    HTTPHelper localHTTPHelper = new HTTPHelper();
    DeviceInformation localDeviceInformation = new DeviceInformation(paramActivity);
    AdFetcher localAdFetcher = new AdFetcher(localHTTPHelper, localDeviceInformation, new PopupBuilder(paramActivity));
    localAdFetcher.fetch(AdFetcher.url("pop_ups", paramString), localAdFetcher.getFetchEntity(localDeviceInformation, null), paramOnLoadListener);
  }

  public String getMessage()
  {
    return this.message;
  }

  public void show(OnLoadListener paramOnLoadListener)
  {
    this.activity.runOnUiThread(new Runnable(this, paramOnLoadListener)
    {
      public void run()
      {
        Popup.access$000(this.this$0, this.val$listener);
      }
    });
  }
}