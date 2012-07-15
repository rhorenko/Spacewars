package com.revmob.ads.banner;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.revmob.ads.Ad;
import com.revmob.ads.AdFetcher;
import com.revmob.ads.HTTPHelper;
import com.revmob.ads.LoadError;
import com.revmob.ads.OnLoadListener;
import com.revmob.android.DeviceInformation;
import org.json.JSONException;
import org.json.JSONObject;

public class Banner extends RelativeLayout
  implements OnLoadListener
{
  private static final String MMA_BANNER = "mma_banner";
  private final Activity activity;
  private View.OnClickListener adClickedListener;
  private ImageView bannerImageView;

  public Banner(String paramString, Activity paramActivity)
  {
    super(paramActivity);
    this.activity = paramActivity;
    this.bannerImageView = new ImageView(paramActivity);
    buildDefaultLayout();
    fetchAdvertisement(paramString);
  }

  public Banner(String paramString, Activity paramActivity, View.OnClickListener paramOnClickListener)
  {
    super(paramActivity);
    this.activity = paramActivity;
    this.bannerImageView = new ImageView(paramActivity);
    this.adClickedListener = paramOnClickListener;
    buildDefaultLayout();
    fetchAdvertisement(paramString);
  }

  public void buildDefaultLayout()
  {
    addView(this.bannerImageView);
  }

  public void fetchAdvertisement(String paramString)
  {
    HTTPHelper localHTTPHelper = new HTTPHelper();
    DeviceInformation localDeviceInformation = new DeviceInformation(this.activity);
    AdFetcher localAdFetcher = new AdFetcher(localHTTPHelper, localDeviceInformation, new BannerBuilder(this.activity));
    try
    {
      JSONObject localJSONObject2 = new JSONObject().put("size", "mma_banner");
      JSONObject localJSONObject1 = localJSONObject2;
      localAdFetcher.fetch(AdFetcher.url("banners", paramString), localAdFetcher.getFetchEntity(localDeviceInformation, localJSONObject1), this);
      return;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        ??? = null;
    }
  }

  public void onAdNotReceived(LoadError paramLoadError, String paramString)
  {
  }

  public void onAdReceived(Ad paramAd)
  {
    BannerAd localBannerAd = (BannerAd)paramAd;
    this.activity.runOnUiThread(new Runnable(this, localBannerAd)
    {
      public void run()
      {
        int j;
        int k;
        int l;
        Banner.access$000(this.this$0).setImageDrawable(this.val$bannerAd.getDrawable());
        this.this$0.setOnClickListener(new View.OnClickListener(this)
        {
          public void onClick()
          {
            if (Banner.access$100(this.this$1.this$0) != null)
              Banner.access$100(this.this$1.this$0).onClick(paramView);
            this.this$1.val$bannerAd.click();
          }
        });
        View localView = (View)this.this$0.getParent();
        Drawable localDrawable = this.val$bannerAd.getDrawable();
        int i = Banner.access$200(this.this$0).getWindowManager().getDefaultDisplay().getWidth();
        if ((localView != null) && (localView.getWidth() != 0))
        {
          j = localView.getWidth();
          k = j * localDrawable.getIntrinsicHeight() / localDrawable.getIntrinsicWidth();
          if ((localView == null) || (localView.getHeight() == 0))
            break label173;
          l = Math.min(localView.getHeight(), k);
        }
        while (true)
        {
          while (true)
          {
            Banner.access$000(this.this$0).setScaleType(ImageView.ScaleType.FIT_XY);
            Banner.access$000(this.this$0).setLayoutParams(new RelativeLayout.LayoutParams(j, l));
            if (localView != null)
              localView.setVisibility(0);
            return;
            j = i;
          }
          label173: l = k;
        }
      }
    });
  }
}