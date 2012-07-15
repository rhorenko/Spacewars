package com.revmob.ads.fullscreen;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.revmob.ads.Ad;
import com.revmob.ads.AdFetcher;
import com.revmob.ads.HTTPHelper;
import com.revmob.ads.LoadError;
import com.revmob.ads.OnLoadListener;
import com.revmob.android.DeviceInformation;

public class Fullscreen extends Dialog
{
  private static final int BACKGROUND_COLOR = -587202560;
  private static final int CLOSE_BUTTON_MARGIN = 15;
  private static final int CLOSE_BUTTON_SIZE_IN_DIP = 40;
  private final Activity activity;
  private final ImageView adView;
  private boolean clicked;
  private FullscreenAd fullscreenAd;
  private final RelativeLayout layout;
  private final OnLoadListener listener;
  private ProgressBar progressBar;

  public Fullscreen(String paramString, Activity paramActivity)
  {
    super(paramActivity, 16973841);
    this.listener = new OnLoadListener(this)
    {
      public void onAdNotReceived(, String paramString)
      {
        Log.d("RevMob", "response: " + paramString);
        this.this$0.dismiss();
      }

      public void onAdReceived()
      {
        Fullscreen.access$002(this.this$0, (FullscreenAd)paramAd);
        if (Fullscreen.access$100(this.this$0))
        {
          Fullscreen.access$000(this.this$0).click();
          this.this$0.dismiss();
        }
      }
    };
    this.activity = paramActivity;
    this.layout = new RelativeLayout(paramActivity);
    this.adView = new ImageView(paramActivity);
    buildDefaultLayout();
    fetchAdvertisement(paramString, paramActivity, this.listener);
  }

  private void buildDefaultLayout()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
    this.layout.setBackgroundColor(-587202560);
    this.layout.addView(this.adView, localLayoutParams);
    this.layout.addView(createCloseButton());
    setContentView(this.layout);
    setCancelable(true);
    this.adView.setOnClickListener(new View.OnClickListener(this)
    {
      public void onClick()
      {
        if (Fullscreen.access$100(this.this$0));
        while (true)
        {
          while (true)
          {
            return;
            Fullscreen.access$202(this.this$0, new ProgressBar(Fullscreen.access$300(this.this$0)));
            Fullscreen.access$200(this.this$0).setIndeterminate(true);
            RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
            int i = Fullscreen.access$400(this.this$0, 15);
            localLayoutParams.leftMargin = i;
            localLayoutParams.topMargin = i;
            Fullscreen.access$500(this.this$0).addView(Fullscreen.access$200(this.this$0), localLayoutParams);
            if (Fullscreen.access$000(this.this$0) == null)
              break;
            Fullscreen.access$000(this.this$0).click();
            this.this$0.dismiss();
          }
          Fullscreen.access$102(this.this$0, true);
        }
      }
    });
  }

  private View createCloseButton()
  {
    ImageView localImageView = new ImageView(this.activity);
    ((ImageView)localImageView).setImageDrawable(StaticAssets.getCloseButton());
    localImageView.setOnClickListener(new View.OnClickListener(this)
    {
      public void onClick()
      {
        this.this$0.dismiss();
      }
    });
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    int i = dipToPixels(40);
    int j = dipToPixels(15);
    localLayoutParams.height = i;
    localLayoutParams.width = i;
    localLayoutParams.rightMargin = j;
    localLayoutParams.topMargin = j;
    localLayoutParams.addRule(11);
    localLayoutParams.addRule(6);
    localImageView.setLayoutParams(localLayoutParams);
    return localImageView;
  }

  private int dipToPixels(int paramInt)
  {
    return (int)(0.5F + getContext().getResources().getDisplayMetrics().density * paramInt);
  }

  public static void fetchAdvertisement(String paramString, Activity paramActivity, OnLoadListener paramOnLoadListener)
  {
    HTTPHelper localHTTPHelper = new HTTPHelper();
    DeviceInformation localDeviceInformation = new DeviceInformation(paramActivity);
    AdFetcher localAdFetcher = new AdFetcher(localHTTPHelper, localDeviceInformation, new FullscreenBuilder(paramActivity));
    localAdFetcher.fetch(AdFetcher.url("fullscreens", paramString), localAdFetcher.getFetchEntity(localDeviceInformation, null), paramOnLoadListener);
  }

  public boolean isAdLoaded()
  {
    byte b;
    if (this.fullscreenAd != null)
      b = 1;
    while (true)
    {
      return b;
      b = 0;
    }
  }

  public void show()
  {
    this.activity.runOnUiThread(new Runnable(this)
    {
      public void run()
      {
        if (Fullscreen.access$000(this.this$0) != null)
          Fullscreen.access$600(this.this$0).setImageDrawable(Fullscreen.access$000(this.this$0).getAdImage());
        while (true)
        {
          Fullscreen.access$600(this.this$0).setScaleType(ImageView.ScaleType.CENTER_CROP);
          if ((Fullscreen.access$300(this.this$0) != null) && (!(Fullscreen.access$300(this.this$0).isFinishing())))
            Fullscreen.access$701(this.this$0);
          return;
          Fullscreen.access$600(this.this$0).setImageDrawable(StaticAssets.getLocalizedDrawable());
        }
      }
    });
  }
}