package com.bestcoolfungames.antsmasher;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.StateListDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.bestcoolfungames.antsmasher.inapp.BillingService;
import com.bestcoolfungames.antsmasher.inapp.BillingService.RequestPurchase;
import com.bestcoolfungames.antsmasher.inapp.BillingService.RestoreTransactions;
import com.bestcoolfungames.antsmasher.inapp.Consts.PurchaseState;
import com.bestcoolfungames.antsmasher.inapp.Consts.ResponseCode;
import com.bestcoolfungames.antsmasher.inapp.PurchaseObserver;
import com.bestcoolfungames.antsmasher.inapp.ResponseHandler;
import com.bestcoolfungames.util.Util;
import com.google.ads.AdView;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class StoreActivity extends Activity
{
  private static final String DB_INITIALIZED = "db_initialized";
  private static final int DIALOG_BILLING_NOT_SUPPORTED_ID = 2;
  private static final int DIALOG_CANNOT_CONNECT_ID = 1;
  private static final String LOG_TEXT_KEY = "AntSmasher_LOG_TEXT";
  private static final String TAG = "AntSmasher";
  static boolean alreadyDoneScreenFix;
  static String curPayKey;
  static Context mContext;
  static int product;
  public static String[] productKey;
  public static String[] productKeyPaid;
  public static String[] productName;
  private DialogInterface.OnClickListener _neutralPopupOnClickListener;
  private DialogInterface.OnClickListener _noPopupOnClickListener;
  private DialogInterface.OnClickListener _yesPopupOnClickListener;
  private Boolean analyticsAlreadyDone;
  boolean billingSupported;
  public TextView coinText;
  boolean isChangingScreen = false;
  private BillingService mBillingService;
  private Handler mHandler;
  private Set<String> mOwnedItems;
  private String mPayloadContents;
  private StorePurchaseObserver mStorePurchaseObserver;
  boolean mustKillSound = false;

  static
  {
    String[] arrayOfString1 = new String[9];
    arrayOfString1[0] = "Kids Game Mode";
    arrayOfString1[1] = "Fun Game Mode";
    arrayOfString1[2] = "No Ads";
    arrayOfString1[3] = "Increase Max Lifes from 3 to 4";
    arrayOfString1[4] = "Increase Max Lifes from 3 to 5";
    arrayOfString1[5] = "GameOver Protection";
    arrayOfString1[6] = "Increase Max Lifes from 4 to 5";
    arrayOfString1[7] = "Baby Mode";
    arrayOfString1[8] = "Deluxe Pack";
    productName = arrayOfString1;
    String[] arrayOfString2 = new String[9];
    arrayOfString2[0] = "com.bcfg.antsmasherfree.kidsgamemode";
    arrayOfString2[1] = "com.bcfg.antsmasherfree.fungamemode";
    arrayOfString2[2] = "com.bcfg.antsmasherfree.noads";
    arrayOfString2[3] = "com.bcfg.antsmasherfree.fourlives";
    arrayOfString2[4] = "com.bcfg.antsmasherfree.fivelivesfromthree";
    arrayOfString2[5] = "com.bcfg.antsmasherfree.gameoverprotection";
    arrayOfString2[6] = "com.bcfg.antsmasherfree.fivelivesfromfour";
    arrayOfString2[7] = "com.bcfg.antsmasherfree.babygamemode";
    arrayOfString2[8] = "com.bcfg.antsmasherfree.deluxepack";
    productKey = arrayOfString2;
    String[] arrayOfString3 = new String[9];
    arrayOfString3[0] = "com.bcfg.antsmasherpaid.kidsgamemode";
    arrayOfString3[1] = "com.bcfg.antsmasherpaid.fungamemode";
    arrayOfString3[2] = "com.bcfg.antsmasherpaid.noads";
    arrayOfString3[3] = "com.bcfg.antsmasherpaid.fourlives";
    arrayOfString3[4] = "com.bcfg.antsmasherpaid.fivelivesfromthree";
    arrayOfString3[5] = "com.bcfg.antsmasherpaid.gameoverprotection";
    arrayOfString3[6] = "com.bcfg.antsmasherpaid.fivelivesfromfour";
    arrayOfString3[7] = "com.bcfg.antsmasherpaid.babygamemode";
    arrayOfString3[8] = "com.bcfg.antsmasherpaid.deluxepack";
    productKeyPaid = arrayOfString3;
  }

  public StoreActivity()
  {
    this.analyticsAlreadyDone = Boolean.valueOf(false);
    this.mOwnedItems = new HashSet();
    this.mPayloadContents = null;
  }

  private Dialog createDialog(String paramString1, String paramString2)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(paramString1).setIcon(17301642).setMessage(paramString2).setCancelable(false).setPositiveButton(17039370, null);
    return localBuilder.create();
  }

  private DialogInterface.OnClickListener getNeutralPopupOnClickListener()
  {
    if (this._neutralPopupOnClickListener == null)
      this._neutralPopupOnClickListener = new DialogInterface.OnClickListener(this)
      {
        public void onClick(, int paramInt)
        {
          paramDialogInterface.dismiss();
          Log.i("AS", "Bought product with TapJoyPoints");
          InitialView.deliverTapJoyProduct(InitialView.shownActivity);
        }
      };
    return this._neutralPopupOnClickListener;
  }

  private DialogInterface.OnClickListener getNoPopupOnClickListener()
  {
    if (this._noPopupOnClickListener == null)
      this._noPopupOnClickListener = new DialogInterface.OnClickListener(this)
      {
        public void onClick(, int paramInt)
        {
          paramDialogInterface.dismiss();
        }
      };
    return this._noPopupOnClickListener;
  }

  private DialogInterface.OnClickListener getYesPopupOnClickListener()
  {
    if (this._yesPopupOnClickListener == null)
      this._yesPopupOnClickListener = new DialogInterface.OnClickListener(this)
      {
        public void onClick(, int paramInt)
        {
          this.this$0.showPaymentActivity();
        }
      };
    return this._yesPopupOnClickListener;
  }

  private void startAnalytics()
  {
    try
    {
      GoogleAnalyticsTracker.getInstance().start("UA-21142514-5", 10, this);
      if (!(this.analyticsAlreadyDone.booleanValue()))
      {
        GoogleAnalyticsTracker.getInstance().trackEvent("Activity", "Activity_Store_Enter", "", -1);
        this.analyticsAlreadyDone = Boolean.valueOf(true);
      }
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  boolean checkProductInfo()
  {
    return false;
  }

  public void onBackPressed()
  {
    InitialView.playButtonSound("Out", mContext);
    this.analyticsAlreadyDone = true;
    SharedPreferences.Editor localEditor = getSharedPreferences("AppData", 0).edit();
    localEditor.putInt("State", 5);
    localEditor.commit();
  }

  public void onCreate(Bundle paramBundle)
  {
    SharedPreferences localSharedPreferences1 = getSharedPreferences("AntSmasherShop", 0);
    Util.setContext(getApplicationContext());
    mContext = Util.getContext();
    alreadyDoneScreenFix = false;
    Log.i("bug", "BUG CATCHER 0");
    Log.i("Values", localSharedPreferences1.getBoolean("KidsMode", false) + ", " + localSharedPreferences1.getBoolean("FunMode", false) + ", " + AdsUtils.noAds(this) + ", " + localSharedPreferences1.getBoolean("Protection", false) + ", " + localSharedPreferences1.getInt("MaxLifes", 3) + ", " + localSharedPreferences1.getBoolean("DeluxePack", false));
    super.onCreate(paramBundle);
    setContentView(2130903053);
    setRequestedOrientation(1);
    findViewById(2131230801).setVisibility(4);
    Button localButton1 = (Button)findViewById(2131230803);
    ImageView localImageView = (ImageView)findViewById(2131230820);
    this.coinText = ((TextView)findViewById(2131230821));
    Button localButton2 = (Button)findViewById(2131230807);
    Button localButton3 = (Button)findViewById(2131230811);
    Button localButton4 = (Button)findViewById(2131230809);
    Button localButton5 = (Button)findViewById(2131230739);
    Button localButton6 = (Button)findViewById(2131230814);
    Button localButton7 = (Button)findViewById(2131230819);
    Button localButton8 = (Button)findViewById(2131230816);
    Button localButton9 = (Button)findViewById(2131230817);
    Button localButton10 = (Button)findViewById(2131230818);
    TextView localTextView1 = (TextView)findViewById(2131230806);
    TextView localTextView2 = (TextView)findViewById(2131230808);
    TextView localTextView3 = (TextView)findViewById(2131230810);
    TextView localTextView4 = (TextView)findViewById(2131230812);
    TextView localTextView5 = (TextView)findViewById(2131230813);
    TextView localTextView6 = (TextView)findViewById(2131230815);
    TextView localTextView7 = (TextView)findViewById(2131230789);
    TextView localTextView8 = (TextView)findViewById(2131230785);
    Typeface localTypeface1 = Typeface.createFromAsset(getAssets(), "fonts/House-A-Rama-League-Night.otf");
    this.coinText.setTypeface(localTypeface1);
    Typeface localTypeface2 = Typeface.createFromAsset(getAssets(), "fonts/House-A-Rama-Kingpin.otf");
    String str = mContext.getResources().getConfiguration().locale.getLanguage();
    Log.i("AS", "language: " + str);
    if ((!(str.equals("iw"))) && (!(str.equals("zh"))) && (!(str.equals("ja"))))
    {
      localTextView1.setTypeface(localTypeface1);
      localTextView2.setTypeface(localTypeface1);
      localTextView3.setTypeface(localTypeface1);
      localTextView4.setTypeface(localTypeface1);
      localTextView5.setTypeface(localTypeface1);
      localTextView6.setTypeface(localTypeface2);
      localTextView7.setTypeface(localTypeface2);
      localTextView8.setTypeface(localTypeface1);
    }
    if (str.equals("en12341234"))
    {
      StateListDrawable localStateListDrawable1 = new StateListDrawable();
      localStateListDrawable1.addState(new int[0], getResources().getDrawable(2130837597));
      localButton2.setBackgroundDrawable(localStateListDrawable1);
      StateListDrawable localStateListDrawable2 = new StateListDrawable();
      localStateListDrawable2.addState(new int[0], getResources().getDrawable(2130837595));
      localButton4.setBackgroundDrawable(localStateListDrawable2);
      StateListDrawable localStateListDrawable3 = new StateListDrawable();
      localStateListDrawable3.addState(new int[0], getResources().getDrawable(2130837654));
      findViewById(2131230804).setBackgroundDrawable(localStateListDrawable3);
      StateListDrawable localStateListDrawable4 = new StateListDrawable();
      localStateListDrawable4.addState(new int[0], getResources().getDrawable(2130837655));
      findViewById(2131230805).setBackgroundDrawable(localStateListDrawable4);
      StateListDrawable localStateListDrawable5 = new StateListDrawable();
      localStateListDrawable5.addState(new int[0], getResources().getDrawable(2130837617));
      localButton5.setBackgroundDrawable(localStateListDrawable5);
    }
    localTextView1.setVisibility(4);
    localTextView2.setVisibility(4);
    localTextView3.setVisibility(4);
    localTextView4.setVisibility(4);
    localTextView5.setVisibility(4);
    localTextView6.setVisibility(4);
    localTextView7.setVisibility(4);
    localTextView8.setVisibility(4);
    SharedPreferences localSharedPreferences2 = getSharedPreferences("AntSmasherShop", 0);
    localImageView.setOnTouchListener(new View.OnTouchListener(this, localSharedPreferences2)
    {
      public boolean onTouch(, MotionEvent paramMotionEvent)
      {
        this.this$0.coinText.setText(this.val$shopSettings.getInt("TapJoyPoints", 0) - this.val$shopSettings.getInt("TapJoySpentPoints", 0));
        return false;
      }
    });
    localButton2.setOnClickListener(new View.OnClickListener(this, localSharedPreferences2, this, localSharedPreferences1)
    {
      public void onClick()
      {
        this.this$0.coinText.setText(this.val$shopSettings.getInt("TapJoyPoints", 0) - this.val$shopSettings.getInt("TapJoySpentPoints", 0));
        InitialView.playButtonSound("In", StoreActivity.mContext);
        try
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("Clicked", "Kids");
          localHashMap.put("All", "Kids");
          GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_Kids", -1);
          StoreActivity.product = 0;
          StorePopups.showDetails(this.val$activity, this.val$sp, StoreActivity.product, StoreActivity.access$1(this.this$0), StoreActivity.access$2(this.this$0), StoreActivity.access$3(this.this$0));
          return;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
    });
    localButton3.setOnClickListener(new View.OnClickListener(this, localSharedPreferences2, this, localSharedPreferences1)
    {
      public void onClick()
      {
        this.this$0.coinText.setText(this.val$shopSettings.getInt("TapJoyPoints", 0) - this.val$shopSettings.getInt("TapJoySpentPoints", 0));
        InitialView.playButtonSound("In", StoreActivity.mContext);
        try
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("Clicked", "Fun");
          localHashMap.put("All", "Fun");
          GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_Fun", -1);
          StoreActivity.product = 1;
          StorePopups.showDetails(this.val$activity, this.val$sp, StoreActivity.product, StoreActivity.access$1(this.this$0), StoreActivity.access$2(this.this$0), StoreActivity.access$3(this.this$0));
          return;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
    });
    localButton6.setOnClickListener(new View.OnClickListener(this, localSharedPreferences2, this, localSharedPreferences1)
    {
      public void onClick()
      {
        this.this$0.coinText.setText(this.val$shopSettings.getInt("TapJoyPoints", 0) - this.val$shopSettings.getInt("TapJoySpentPoints", 0));
        InitialView.playButtonSound("In", StoreActivity.mContext);
        try
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("Clicked", "NoAds");
          localHashMap.put("All", "NoAds");
          GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_NoAds", -1);
          StoreActivity.product = 2;
          StorePopups.showDetails(this.val$activity, this.val$sp, StoreActivity.product, StoreActivity.access$1(this.this$0), StoreActivity.access$2(this.this$0), StoreActivity.access$3(this.this$0));
          return;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
    });
    localButton8.setOnClickListener(new View.OnClickListener(this, localSharedPreferences2, this, localSharedPreferences1)
    {
      public void onClick()
      {
        this.this$0.coinText.setText(this.val$shopSettings.getInt("TapJoyPoints", 0) - this.val$shopSettings.getInt("TapJoySpentPoints", 0));
        InitialView.playButtonSound("In", StoreActivity.mContext);
        try
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("Clicked", "Lives3To4");
          localHashMap.put("All", "Lives3To4");
          GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_Lives3To4", -1);
          StoreActivity.product = 3;
          StorePopups.showDetails(this.val$activity, this.val$sp, StoreActivity.product, StoreActivity.access$1(this.this$0), StoreActivity.access$2(this.this$0), StoreActivity.access$3(this.this$0));
          return;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
    });
    localButton9.setOnClickListener(new View.OnClickListener(this, localSharedPreferences2, this, localSharedPreferences1)
    {
      public void onClick()
      {
        this.this$0.coinText.setText(this.val$shopSettings.getInt("TapJoyPoints", 0) - this.val$shopSettings.getInt("TapJoySpentPoints", 0));
        InitialView.playButtonSound("In", StoreActivity.mContext);
        try
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("Clicked", "Lives3To5");
          localHashMap.put("All", "Lives3To5");
          GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_Lives3To5", -1);
          StoreActivity.product = 4;
          StorePopups.showDetails(this.val$activity, this.val$sp, StoreActivity.product, StoreActivity.access$1(this.this$0), StoreActivity.access$2(this.this$0), StoreActivity.access$3(this.this$0));
          return;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
    });
    localButton7.setOnClickListener(new View.OnClickListener(this, localSharedPreferences2, this, localSharedPreferences1)
    {
      public void onClick()
      {
        this.this$0.coinText.setText(this.val$shopSettings.getInt("TapJoyPoints", 0) - this.val$shopSettings.getInt("TapJoySpentPoints", 0));
        InitialView.playButtonSound("In", StoreActivity.mContext);
        try
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("Clicked", "GameOverProtection");
          localHashMap.put("All", "GameOverProtection");
          GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_GameOverProtection", -1);
          StoreActivity.product = 5;
          StorePopups.showDetails(this.val$activity, this.val$sp, StoreActivity.product, StoreActivity.access$1(this.this$0), StoreActivity.access$2(this.this$0), StoreActivity.access$3(this.this$0));
          return;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
    });
    localButton4.setOnClickListener(new View.OnClickListener(this, localSharedPreferences2, this, localSharedPreferences1)
    {
      public void onClick()
      {
        this.this$0.coinText.setText(this.val$shopSettings.getInt("TapJoyPoints", 0) - this.val$shopSettings.getInt("TapJoySpentPoints", 0));
        InitialView.playButtonSound("In", StoreActivity.mContext);
        try
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("Clicked", "Baby");
          localHashMap.put("All", "Baby");
          GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_Baby", -1);
          StoreActivity.product = 7;
          StorePopups.showDetails(this.val$activity, this.val$sp, StoreActivity.product, StoreActivity.access$1(this.this$0), StoreActivity.access$2(this.this$0), StoreActivity.access$3(this.this$0));
          return;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
    });
    localButton10.setOnClickListener(new View.OnClickListener(this, localSharedPreferences2, this, localSharedPreferences1)
    {
      public void onClick()
      {
        this.this$0.coinText.setText(this.val$shopSettings.getInt("TapJoyPoints", 0) - this.val$shopSettings.getInt("TapJoySpentPoints", 0));
        InitialView.playButtonSound("In", StoreActivity.mContext);
        try
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("Clicked", "Lives4To5");
          localHashMap.put("All", "Lives4To5");
          GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_Lives4To5", -1);
          StoreActivity.product = 6;
          StorePopups.showDetails(this.val$activity, this.val$sp, StoreActivity.product, StoreActivity.access$1(this.this$0), StoreActivity.access$2(this.this$0), StoreActivity.access$3(this.this$0));
          return;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
    });
    localButton1.setOnClickListener(new View.OnClickListener(this, localSharedPreferences2, this, localSharedPreferences1)
    {
      public void onClick()
      {
        this.this$0.coinText.setText(this.val$shopSettings.getInt("TapJoyPoints", 0) - this.val$shopSettings.getInt("TapJoySpentPoints", 0));
        InitialView.playButtonSound("In", StoreActivity.mContext);
        try
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("Clicked", "Deluxe");
          localHashMap.put("All", "Deluxe");
          GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_Deluxe", -1);
          StoreActivity.product = 8;
          StorePopups.showDetails(this.val$activity, this.val$sp, StoreActivity.product, StoreActivity.access$1(this.this$0), StoreActivity.access$2(this.this$0), StoreActivity.access$3(this.this$0));
          return;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
    });
    localButton5.setOnClickListener(new View.OnClickListener(this)
    {
      public void onClick()
      {
        InitialView.playButtonSound("Out", StoreActivity.mContext);
        this.this$0.isChangingScreen = true;
        SharedPreferences.Editor localEditor = this.this$0.getSharedPreferences("AppData", 0).edit();
        localEditor.putInt("State", 5);
        localEditor.commit();
      }
    });
    boolean bool1 = AdsUtils.noAds(this);
    boolean bool2 = getSharedPreferences("AntSmasherShop", 0).getBoolean("FunMode", false);
    boolean bool3 = getSharedPreferences("AntSmasherShop", 0).getBoolean("KidsMode", false);
    boolean bool4 = getSharedPreferences("AntSmasherShop", 0).getBoolean("Protection", false);
    boolean bool5 = getSharedPreferences("AntSmasherShop", 0).getBoolean("BabyMode", false);
    int i = getSharedPreferences("AntSmasherShop", 0).getInt("MaxLifes", 3);
    boolean bool6 = getSharedPreferences("AntSmasherShop", 0).getBoolean("DeluxePack", false);
    if (bool1)
      localButton6.setVisibility(4);
    if (bool2)
      localButton3.setVisibility(4);
    if (bool3)
      localButton2.setVisibility(4);
    if (bool4)
      localButton7.setVisibility(4);
    if (bool5)
      localButton4.setVisibility(4);
    if (i == 3)
      localButton10.setVisibility(4);
    while (true)
    {
      do
        while (true)
        {
          if ((bool6) || ((i == 5) && (bool1) && (bool2) && (bool3) && (bool4) && (bool5)))
            localButton1.setVisibility(4);
          this.mHandler = new Handler();
          this.mStorePurchaseObserver = new StorePurchaseObserver(this, this.mHandler);
          this.mBillingService = new BillingService();
          this.mBillingService.setContext(this);
          ResponseHandler.register(this.mStorePurchaseObserver);
          if (!(this.mBillingService.checkBillingSupported()))
            showDialog(1);
          SharedPreferences localSharedPreferences3 = getSharedPreferences("AppData", 0);
          int j = localSharedPreferences3.getInt("gameOverProduct", -1);
          Log.i("AS", "Shop product: " + product + ", GOP: " + j);
          if (j != -1)
          {
            product = j;
            showPaymentActivity();
            SharedPreferences.Editor localEditor = localSharedPreferences3.edit();
            localEditor.putInt("gameOverProduct", -1);
            localEditor.commit();
          }
          findViewById(2131230807).getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(this, this, localTextView1, localButton2, localButton4, localButton3, localTextView2, localTextView3, localTextView4, localButton1, localTextView5, str, localButton6, localButton8, localButton9, localButton10, localButton7, localButton5, localImageView, localTextView6, localTextView7, localTextView8)
          {
            public boolean onPreDraw()
            {
              int j;
              int k;
              RelativeLayout.LayoutParams localLayoutParams1;
              if ((!(StoreActivity.alreadyDoneScreenFix)) && (this.val$activity.getResources().getConfiguration().orientation == 1))
              {
                int i = StoreActivity.mContext.getResources().getDisplayMetrics().heightPixels;
                j = StoreActivity.mContext.getResources().getDisplayMetrics().widthPixels;
                k = i - Util.dipToPx(50);
                localLayoutParams1 = (RelativeLayout.LayoutParams)this.this$0.findViewById(2131230802).getLayoutParams();
                if (localLayoutParams1.height < k)
                  break label1853;
                localLayoutParams1.height = (k - Util.dipToPx(15));
                if (localLayoutParams1.width < j)
                  break label1892;
                localLayoutParams1.width = j;
              }
              while (true)
              {
                label1853: label1892: 
                do
                  while (true)
                  {
                    do
                    {
                      localLayoutParams1.topMargin = ((k - localLayoutParams1.height) / 2);
                      float f1 = localLayoutParams1.width / 316.0F;
                      float f2 = localLayoutParams1.height / 485.0F;
                      RelativeLayout.LayoutParams localLayoutParams2 = (RelativeLayout.LayoutParams)this.this$0.findViewById(2131230804).getLayoutParams();
                      localLayoutParams2.width = (int)(163.0F * f2);
                      localLayoutParams2.height = (int)(157.0F * f2);
                      float f3 = localLayoutParams2.width / 183.0F;
                      float f4 = localLayoutParams2.height / 157.0F;
                      this.this$0.findViewById(2131230804).setLayoutParams(localLayoutParams2);
                      RelativeLayout.LayoutParams localLayoutParams3 = (RelativeLayout.LayoutParams)this.val$gmodesText.getLayoutParams();
                      localLayoutParams3.leftMargin = (5 * localLayoutParams2.width / 173);
                      localLayoutParams3.topMargin = (-5 * localLayoutParams2.height / 151);
                      localLayoutParams3.width = (160 * localLayoutParams2.width / 173);
                      localLayoutParams3.height = (50 * localLayoutParams2.height / 173);
                      this.val$gmodesText.setLayoutParams(localLayoutParams3);
                      int l = 180 * localLayoutParams3.width / 225;
                      int i1 = 45 * localLayoutParams3.height / 65;
                      TextViewResizer.resizeTextView(this.val$gmodesText, l, i1);
                      Util.fixViewScale(this.val$kidsModeButton, 100, 60, -7, 25, f3, f4);
                      Util.fixViewScale(this.val$babyModeButton, 109, 65, 0, 55, f3, f4);
                      Util.fixViewScale(this.val$funModeButton, 115, 63, -5, -7, f3, f4);
                      RelativeLayout.LayoutParams localLayoutParams4 = (RelativeLayout.LayoutParams)this.val$kidsModeButton.getLayoutParams();
                      RelativeLayout.LayoutParams localLayoutParams5 = (RelativeLayout.LayoutParams)this.val$kidsText.getLayoutParams();
                      localLayoutParams5.leftMargin = (14 * localLayoutParams4.width / 100);
                      localLayoutParams5.topMargin = (12 * localLayoutParams4.height / 60);
                      localLayoutParams5.width = (70 * localLayoutParams4.width / 100);
                      localLayoutParams5.height = (32 * localLayoutParams4.height / 60);
                      this.val$kidsText.setLayoutParams(localLayoutParams5);
                      int i2 = 142 * localLayoutParams5.width / 158;
                      int i3 = 67 * localLayoutParams5.height / 83;
                      TextViewResizer.resizeTextView(this.val$kidsText, i2, i3);
                      RelativeLayout.LayoutParams localLayoutParams6 = (RelativeLayout.LayoutParams)this.val$babyModeButton.getLayoutParams();
                      RelativeLayout.LayoutParams localLayoutParams7 = (RelativeLayout.LayoutParams)this.val$babyText.getLayoutParams();
                      localLayoutParams7.leftMargin = (33 * localLayoutParams6.width / 168);
                      localLayoutParams7.topMargin = (15 * localLayoutParams6.height / 100);
                      localLayoutParams7.width = (90 * localLayoutParams6.width / 168);
                      localLayoutParams7.height = (70 * localLayoutParams6.height / 100);
                      this.val$babyText.setLayoutParams(localLayoutParams7);
                      int i4 = 73 * localLayoutParams7.width / 88;
                      int i5 = 51 * localLayoutParams7.height / 58;
                      TextViewResizer.resizeTextView(this.val$babyText, i4, i5);
                      RelativeLayout.LayoutParams localLayoutParams8 = (RelativeLayout.LayoutParams)this.val$funModeButton.getLayoutParams();
                      RelativeLayout.LayoutParams localLayoutParams9 = (RelativeLayout.LayoutParams)this.val$funText.getLayoutParams();
                      localLayoutParams9.leftMargin = (35 * localLayoutParams8.width / 115);
                      localLayoutParams9.topMargin = (0 * localLayoutParams8.height / 63);
                      localLayoutParams9.width = (53 * localLayoutParams8.width / 115);
                      localLayoutParams9.height = (63 * localLayoutParams8.height / 63);
                      this.val$funText.setLayoutParams(localLayoutParams9);
                      int i6 = 69 * localLayoutParams9.width / 80;
                      int i7 = 55 * localLayoutParams9.height / 68;
                      TextViewResizer.resizeTextView(this.val$funText, i6, i7);
                      RelativeLayout.LayoutParams localLayoutParams10 = (RelativeLayout.LayoutParams)this.val$deluxeButton.getLayoutParams();
                      localLayoutParams10.width = (localLayoutParams1.width - localLayoutParams2.width);
                      localLayoutParams10.height = (87 * localLayoutParams10.width / 151);
                      if (localLayoutParams10.height > localLayoutParams2.height)
                      {
                        localLayoutParams10.height = localLayoutParams2.height;
                        localLayoutParams10.width = (151 * localLayoutParams10.height / 87);
                      }
                      localLayoutParams10.leftMargin = (int)(f1 * (localLayoutParams1.width - localLayoutParams2.width - localLayoutParams10.width) / 2F);
                      localLayoutParams10.topMargin = (int)(f2 * (localLayoutParams2.height - localLayoutParams10.height) / 2F);
                      RelativeLayout.LayoutParams localLayoutParams11 = (RelativeLayout.LayoutParams)this.this$0.findViewById(2131230805).getLayoutParams();
                      localLayoutParams11.leftMargin = (int)(8.0F * f1);
                      localLayoutParams11.width = localLayoutParams1.width;
                      localLayoutParams11.height = (int)(328.0F * f2);
                      float f5 = localLayoutParams11.width / 316.0F;
                      float f6 = localLayoutParams11.height / 328.0F;
                      this.this$0.findViewById(2131230805).setLayoutParams(localLayoutParams11);
                      RelativeLayout.LayoutParams localLayoutParams12 = (RelativeLayout.LayoutParams)this.val$gplayText.getLayoutParams();
                      localLayoutParams12.rightMargin = (5 * localLayoutParams11.width / 303);
                      localLayoutParams12.topMargin = (-7 * localLayoutParams11.height / 316);
                      localLayoutParams12.width = (135 * localLayoutParams11.width / 303);
                      localLayoutParams12.height = (50 * localLayoutParams11.height / 303);
                      this.val$gplayText.setLayoutParams(localLayoutParams12);
                      int i8 = 135 * localLayoutParams12.width / 135;
                      if (this.val$language.equals("ja"))
                        i8 = 100 * localLayoutParams12.width / 135;
                      int i9 = 40 * localLayoutParams12.height / 50;
                      TextViewResizer.resizeTextView(this.val$gplayText, i8, i9);
                      Util.fixViewScale(this.val$noAdsButton, 161, 85, 10, 10, f5, f6);
                      Util.fixViewScale(this.val$lives34Button, 151, 91, -17, 50, f5, f6);
                      Util.fixViewScale(this.val$lives35Button, 171, 98, 17, 100, f5, f6);
                      Util.fixViewScale(this.val$lives45Button, 171, 98, 17, 100, f5, f6);
                      Util.fixViewScale(this.val$gopButton, 180, 100, -17, -65, f5, f6);
                      Util.fixViewScale(this.val$menuButton, 151, 79, 17, -20, f5, f6);
                      int i10 = this.this$0.coinText.getHeight();
                      Util.fixViewScale(this.val$coinImage, i10, i10, -10, -10, 1F, 1F);
                      RelativeLayout.LayoutParams localLayoutParams13 = (RelativeLayout.LayoutParams)this.val$noAdsButton.getLayoutParams();
                      RelativeLayout.LayoutParams localLayoutParams14 = (RelativeLayout.LayoutParams)this.val$noadsText.getLayoutParams();
                      localLayoutParams14.leftMargin = (34 * localLayoutParams13.width / 160);
                      localLayoutParams14.topMargin = (26 * localLayoutParams13.height / 85);
                      localLayoutParams14.width = (98 * localLayoutParams13.width / 160);
                      localLayoutParams14.height = (30 * localLayoutParams13.height / 85);
                      this.val$noadsText.setLayoutParams(localLayoutParams14);
                      int i11 = 142 * localLayoutParams14.width / 158;
                      int i12 = 67 * localLayoutParams14.height / 83;
                      TextViewResizer.resizeTextView(this.val$noadsText, i11, i12);
                      RelativeLayout.LayoutParams localLayoutParams15 = (RelativeLayout.LayoutParams)this.val$gopButton.getLayoutParams();
                      RelativeLayout.LayoutParams localLayoutParams16 = (RelativeLayout.LayoutParams)this.val$gopText.getLayoutParams();
                      localLayoutParams16.leftMargin = (21 * localLayoutParams15.width / 180);
                      localLayoutParams16.topMargin = (25 * localLayoutParams15.height / 100);
                      localLayoutParams16.width = (120 * localLayoutParams15.width / 180);
                      localLayoutParams16.height = (45 * localLayoutParams15.height / 100);
                      this.val$gopText.setLayoutParams(localLayoutParams16);
                      int i13 = 165 * localLayoutParams16.width / 180;
                      if ((this.val$language.equals("fr")) || (this.val$language.equals("pt")))
                        i13 = 17 * 165 * localLayoutParams16.width / 180 / 10;
                      if (this.val$language.equals("ja"))
                        i13 = 14 * 165 * localLayoutParams16.width / 180 / 10;
                      int i14 = 48 * localLayoutParams16.height / 68;
                      TextViewResizer.resizeTextView(this.val$gopText, i13, i14);
                      RelativeLayout.LayoutParams localLayoutParams17 = (RelativeLayout.LayoutParams)this.val$menuButton.getLayoutParams();
                      RelativeLayout.LayoutParams localLayoutParams18 = (RelativeLayout.LayoutParams)this.val$menuText.getLayoutParams();
                      localLayoutParams18.leftMargin = (24 * localLayoutParams17.width / 132);
                      localLayoutParams18.topMargin = (9 * localLayoutParams17.height / 69);
                      localLayoutParams18.width = (80 * localLayoutParams17.width / 132);
                      localLayoutParams18.height = (55 * localLayoutParams17.height / 69);
                      this.val$menuText.setLayoutParams(localLayoutParams18);
                      int i15 = 123 * localLayoutParams18.width / 137;
                      int i16 = 45 * localLayoutParams18.height / 60;
                      TextViewResizer.resizeTextView(this.val$menuText, i15, i16);
                      Log.i("bug", "BUG CATCHER 3");
                      this.this$0.findViewById(2131230801).setVisibility(0);
                      StoreActivity.alreadyDoneScreenFix = true;
                      return true;
                    }
                    while ((localLayoutParams1.height >= k) || (k - localLayoutParams1.height <= Util.dipToPx(100)));
                    localLayoutParams1.height = (k - Util.dipToPx(75));
                  }
                while (localLayoutParams1.width >= j - Util.dipToPx(30));
                localLayoutParams1.width = (j - Util.dipToPx(30));
              }
            }
          });
          int k = getSharedPreferences("AntSmasherShop", 0).getInt("TapJoyPoints", 0) - getSharedPreferences("AntSmasherShop", 0).getInt("TapJoySpentPoints", 0);
          this.coinText.setText(k);
          return;
          if (i != 4)
            break;
          localButton9.setVisibility(4);
          localButton8.setVisibility(4);
        }
      while (i != 5);
      localButton10.setVisibility(4);
      localButton8.setVisibility(4);
      localButton9.setVisibility(4);
    }
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    Dialog localDialog = null;
    switch (paramInt)
    {
    default:
    case 1:
    case 2:
    }
    while (true)
    {
      while (true)
      {
        return localDialog;
        localDialog = createDialog("Can't connect to market", "Verify your Market App version");
        localDialog.setOnDismissListener(new DialogInterface.OnDismissListener(this)
        {
          public void onDismiss()
          {
          }
        });
      }
      localDialog = createDialog("Not supported", null);
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.mBillingService.unbind();
  }

  protected void onPause()
  {
    if ((this.mustKillSound) && (InitialView.backgroundMp != null) && (!(InitialView.backgroundMpErrored.booleanValue())) && (InitialView.backgroundMp.isPlaying()))
      InitialView.backgroundMp.stop();
    if (this.analyticsAlreadyDone == 0)
    {
      if ((InitialView.backgroundMp != null) && (!(InitialView.backgroundMpErrored.booleanValue())) && (InitialView.backgroundMp.isPlaying()))
        InitialView.backgroundMp.stop();
      Log.i("AS", "Going to quit app");
      SharedPreferences.Editor localEditor2 = getSharedPreferences("AntSmasherShop", 0).edit();
      localEditor2.putBoolean("InitialProductAlreadyOffered", false);
      localEditor2.commit();
      SharedPreferences.Editor localEditor1 = getSharedPreferences("AppData", 0).edit();
      localEditor1.putBoolean("GamePaused", true);
      localEditor1.commit();
    }
    try
    {
      GoogleAnalyticsTracker.getInstance().stop();
      super.onPause();
      return;
      Log.i("AS", "Going to change screen");
      this.analyticsAlreadyDone = false;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void onResume()
  {
    super.onResume();
    InitialView.createBackgroundMusic(this);
    SharedPreferences.Editor localEditor = getSharedPreferences("AppData", 0).edit();
    localEditor.putBoolean("GamePaused", false);
    localEditor.commit();
  }

  public void onStart()
  {
    super.onStart();
    InitialView.shownActivity = this;
    InitialView.createBackgroundMusic(this);
    ResponseHandler.register(this.mStorePurchaseObserver);
    startAnalytics();
  }

  protected void onStop()
  {
    super.onStop();
    ResponseHandler.unregister(this.mStorePurchaseObserver);
  }

  void registerPurchase()
  {
  }

  public String returnID()
  {
    SharedPreferences localSharedPreferences;
    String str = Settings.Secure.getString(getContentResolver(), "android_id");
    if (str == null)
    {
      localSharedPreferences = getSharedPreferences("AntSmasherShop", 0);
      if (!(localSharedPreferences.contains("deviceID")))
        break label49;
      str = localSharedPreferences.getString("deviceID", null);
    }
    while (true)
    {
      return str;
      label49: SharedPreferences.Editor localEditor = getSharedPreferences("AntSmasherShop", 0).edit();
      localEditor.putString("deviceID", GameSurface.rand.nextInt(1000) + GameSurface.rand.nextInt(1000) + GameSurface.rand.nextInt(1000) + GameSurface.rand.nextInt(1000));
      localEditor.commit();
      str = localSharedPreferences.getString("deviceID", null);
    }
  }

  void setPurchaseInMemory()
  {
    SharedPreferences.Editor localEditor = getSharedPreferences("AntSmasherShop", 0).edit();
    switch (product)
    {
    default:
      return;
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    }
    try
    {
      HashMap localHashMap9 = new HashMap();
      localHashMap9.put("Purchased", "Kids");
      localHashMap9.put("All", "Kids_Purchased");
      GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_Kids_Purchased", -1);
      localEditor.putBoolean("KidsMode", true);
      localEditor.commit();
      label1241: findViewById(2131230807).setVisibility(4);
    }
    catch (Exception localException9)
    {
      while (true)
        localException9.printStackTrace();
      try
      {
        HashMap localHashMap8 = new HashMap();
        localHashMap8.put("Purchased", "Fun");
        localHashMap8.put("All", "Fun_Purchased");
        GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_Fun_Purchased", -1);
        localEditor.putBoolean("FunMode", true);
        localEditor.commit();
        findViewById(2131230811).setVisibility(4);
      }
      catch (Exception localException8)
      {
        while (true)
          localException8.printStackTrace();
        try
        {
          byte b3;
          do
          {
            do
            {
              HashMap localHashMap7 = new HashMap();
              localHashMap7.put("Purchased", "NoAds");
              localHashMap7.put("All", "NoAds_Purchased");
              GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_NoAds_Purchased", -1);
              localEditor.putBoolean("NoAds", true);
              localEditor.commit();
              findViewById(2131230814).setVisibility(4);
            }
            while (InitialView.ad == null);
            b3 = 0;
          }
          while (b3 >= 5);
          InitialView.ad[b3].setVisibility(4);
          ++b3;
        }
        catch (Exception localException7)
        {
          while (true)
            localException7.printStackTrace();
          try
          {
            HashMap localHashMap6 = new HashMap();
            localHashMap6.put("Purchased", "Lives3To4");
            localHashMap6.put("All", "Lives3To4_Purchased");
            GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_Lives3To4_Purchased", -1);
            localEditor.putInt("MaxLifes", 4);
            localEditor.commit();
            findViewById(2131230816).setVisibility(4);
            findViewById(2131230817).setVisibility(4);
            findViewById(2131230818).setVisibility(0);
          }
          catch (Exception localException6)
          {
            while (true)
              localException6.printStackTrace();
            try
            {
              HashMap localHashMap5 = new HashMap();
              localHashMap5.put("Purchased", "Lives3To5");
              localHashMap5.put("All", "Lives3To5_Purchased");
              GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_Lives3To5_Purchased", -1);
              localEditor.putInt("MaxLifes", 5);
              localEditor.commit();
              findViewById(2131230816).setVisibility(4);
              findViewById(2131230817).setVisibility(4);
              findViewById(2131230818).setVisibility(4);
            }
            catch (Exception localException5)
            {
              while (true)
                localException5.printStackTrace();
              try
              {
                HashMap localHashMap4 = new HashMap();
                localHashMap4.put("Purchased", "GameOverProtection");
                localHashMap4.put("All", "GameOverProtection_Purchased");
                GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_GameOverProtection_Purchased", -1);
                localEditor.putBoolean("Protection", true);
                localEditor.putBoolean("ProtectionEnabled", true);
                localEditor.commit();
                findViewById(2131230819).setVisibility(4);
              }
              catch (Exception localException4)
              {
                while (true)
                  localException4.printStackTrace();
                try
                {
                  HashMap localHashMap3 = new HashMap();
                  localHashMap3.put("Purchased", "Lives4To5");
                  localHashMap3.put("All", "Lives4To5_Purchased");
                  GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_Lives4To5_Purchased", -1);
                  localEditor.putInt("MaxLifes", 5);
                  localEditor.commit();
                  findViewById(2131230816).setVisibility(4);
                  findViewById(2131230817).setVisibility(4);
                  findViewById(2131230818).setVisibility(4);
                }
                catch (Exception localException2)
                {
                  try
                  {
                    HashMap localHashMap2 = new HashMap();
                    localHashMap2.put("Purchased", "Baby");
                    localHashMap2.put("All", "Baby_Purchased");
                    GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_Baby_Purchased", -1);
                    localEditor.putBoolean("BabyMode", true);
                    localEditor.commit();
                    findViewById(2131230809).setVisibility(4);
                  }
                  catch (Exception localException2)
                  {
                    try
                    {
                      while (true)
                      {
                        do
                        {
                          HashMap localHashMap1 = new HashMap();
                          localHashMap1.put("Purchased", "DeluxePack");
                          localHashMap1.put("All", "DeluxePack_Purchased");
                          GoogleAnalyticsTracker.getInstance().trackEvent("Store", "Store_Item", "Store_Item_DeluxePack_Purchased", -1);
                          localEditor.putInt("MaxLifes", 5);
                          localEditor.putBoolean("BabyMode", true);
                          localEditor.putBoolean("FunMode", true);
                          localEditor.putBoolean("KidsMode", true);
                          localEditor.putBoolean("Protection", true);
                          localEditor.putBoolean("ProtectionEnabled", true);
                          localEditor.putBoolean("NoAds", true);
                          localEditor.putBoolean("DeluxePack", true);
                          localEditor.commit();
                          findViewById(2131230816).setVisibility(4);
                          findViewById(2131230817).setVisibility(4);
                          findViewById(2131230818).setVisibility(4);
                          findViewById(2131230809).setVisibility(4);
                          findViewById(2131230807).setVisibility(4);
                          findViewById(2131230811).setVisibility(4);
                          findViewById(2131230819).setVisibility(4);
                          findViewById(2131230814).setVisibility(4);
                          findViewById(2131230803).setVisibility(4);
                        }
                        while (InitialView.ad == null);
                        byte b1 = 0;
                        while (true)
                        {
                          byte b2;
                          do
                          {
                            if (5 >= InitialView.ad.length)
                              break label1241;
                            b2 = 5;
                          }
                          while (b1 >= b2);
                          if (InitialView.ad[b1] != null)
                            InitialView.ad[b1].setVisibility(4);
                          ++b1;
                        }
                        localException3 = localException3;
                        localException3.printStackTrace();
                      }
                      localException2 = localException2;
                      localException2.printStackTrace();
                    }
                    catch (Exception localException1)
                    {
                      while (true)
                      {
                        while (true)
                          localException1.printStackTrace();
                        int i = InitialView.ad.length;
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  void showPaymentActivity()
  {
    try
    {
      this.analyticsAlreadyDone = true;
      this.mustKillSound = true;
      if (Util.isDebug)
        this.mBillingService.requestPurchase("android.test.purchased", null);
      else if (Constants.isPaidVersion())
        this.mBillingService.requestPurchase(productKeyPaid[product], null);
      else
        this.mBillingService.requestPurchase(productKey[product], null);
      label72: return;
    }
    catch (Exception localException)
    {
      break label72:
    }
  }

  void unsetPurchaseInMemory()
  {
    byte b;
    Log.i("", "REFUND !!!!!");
    SharedPreferences.Editor localEditor = getSharedPreferences("AntSmasherShop", 0).edit();
    localEditor.putBoolean("KidsMode", false);
    localEditor.commit();
    findViewById(2131230807).setVisibility(0);
    localEditor.putBoolean("FunMode", false);
    localEditor.commit();
    findViewById(2131230811).setVisibility(0);
    localEditor.putBoolean("BabyMode", false);
    localEditor.commit();
    findViewById(2131230809).setVisibility(0);
    localEditor.putBoolean("NoAds", false);
    localEditor.commit();
    if (InitialView.ad != null)
      b = 0;
    while (true)
    {
      if (b >= 5)
      {
        localEditor.putInt("MaxLifes", 3);
        localEditor.commit();
        findViewById(2131230816).setVisibility(0);
        findViewById(2131230817).setVisibility(0);
        localEditor.putBoolean("Protection", false);
        localEditor.commit();
        findViewById(2131230819).setVisibility(0);
        localEditor.putBoolean("DeluxePack", false);
        localEditor.commit();
        findViewById(2131230803).setVisibility(0);
        return;
      }
      InitialView.ad[b].setVisibility(0);
      ++b;
    }
  }

  private static enum Managed
  {
    static
    {
      Managed[] arrayOfManaged = new Managed[2];
      arrayOfManaged[0] = MANAGED;
      arrayOfManaged[1] = UNMANAGED;
      ENUM$VALUES = arrayOfManaged;
    }
  }

  private class StorePurchaseObserver extends PurchaseObserver
  {
    public StorePurchaseObserver(, Handler paramHandler)
    {
      super(paramStoreActivity, paramHandler);
    }

    public void onBillingSupported()
    {
      if (paramBoolean)
        this.this$0.billingSupported = true;
      while (true)
      {
        return;
        this.this$0.showDialog(2);
      }
    }

    public void onPurchaseStateChange(, String paramString1, int paramInt, long paramLong, String paramString2)
    {
      if (paramPurchaseState == Consts.PurchaseState.PURCHASED)
        StoreActivity.access$0(this.this$0).add(paramString1);
      if (paramPurchaseState == Consts.PurchaseState.REFUNDED)
      {
        Log.i("", "REFUND");
        this.this$0.unsetPurchaseInMemory();
      }
    }

    public void onRequestPurchaseResponse(, Consts.ResponseCode paramResponseCode)
    {
      SharedPreferences localSharedPreferences;
      if (paramResponseCode == Consts.ResponseCode.RESULT_OK)
      {
        Log.i("AntSmasher", "purchase was successfully sent to server");
        this.this$0.setPurchaseInMemory();
        int i = StoreActivity.product;
        Log.d("AntSmasher", "onRequestPurchaseResponse.product = " + StoreActivity.product);
        localSharedPreferences = this.this$0.getSharedPreferences("AntSmasherShop", 0);
        if (!(AdsUtils.noAds(this.this$0)))
        {
          StoreActivity.product = 2;
          StorePopups.showDetails(this.this$0, localSharedPreferences, StoreActivity.product, StoreActivity.access$1(this.this$0), StoreActivity.access$2(this.this$0), StoreActivity.access$3(this.this$0));
          Log.d("AntSmasher", "onRequestPurchaseResponse.product = " + StoreActivity.product);
          StorePopups.showUseInstructionDetails(this.this$0, localSharedPreferences, i);
        }
      }
      while (true)
        do
          while (true)
          {
            do
              while (true)
              {
                return;
                if (localSharedPreferences.getBoolean("KidsMode", false))
                  break;
                StoreActivity.product = 0;
                StorePopups.showDetails(this.this$0, localSharedPreferences, StoreActivity.product, StoreActivity.access$1(this.this$0), StoreActivity.access$2(this.this$0), StoreActivity.access$3(this.this$0));
              }
            while (localSharedPreferences.getBoolean("FunMode", false));
            StoreActivity.product = 1;
            StorePopups.showDetails(this.this$0, localSharedPreferences, StoreActivity.product, StoreActivity.access$1(this.this$0), StoreActivity.access$2(this.this$0), StoreActivity.access$3(this.this$0));
          }
        while (paramResponseCode != Consts.ResponseCode.RESULT_USER_CANCELED);
    }

    public void onRestoreTransactionsResponse(, Consts.ResponseCode paramResponseCode)
    {
      if (paramResponseCode == Consts.ResponseCode.RESULT_OK)
      {
        SharedPreferences.Editor localEditor = this.this$0.getPreferences(0).edit();
        localEditor.putBoolean("db_initialized", true);
        localEditor.commit();
      }
    }
  }
}