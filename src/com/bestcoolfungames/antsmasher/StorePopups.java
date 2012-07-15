package com.bestcoolfungames.antsmasher;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.bestcoolfungames.util.Util;

public class StorePopups
{
  public static void showDetails(Activity paramActivity, SharedPreferences paramSharedPreferences, int paramInt, DialogInterface.OnClickListener paramOnClickListener1, DialogInterface.OnClickListener paramOnClickListener2, DialogInterface.OnClickListener paramOnClickListener3)
  {
    SharedPreferences.Editor localEditor = paramSharedPreferences.edit();
    Object localObject1 = null;
    Object localObject2 = null;
    int i = paramSharedPreferences.getInt("TapJoyPoints", 0);
    int j = 0;
    short s = 1;
    if (Util.isDebug)
      s = 100;
    switch (paramInt)
    {
    default:
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(paramInt);
      Log.e("NotImplemented", String.format("Não implementado product = %s", arrayOfObject2));
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
    while (true)
    {
      localEditor.putInt("TapJoyProduct", paramInt);
      localEditor.commit();
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity);
      localBuilder.setTitle((CharSequence)localObject1);
      localBuilder.setMessage((CharSequence)localObject2);
      localBuilder.setPositiveButton(paramActivity.getString(2131165209), paramOnClickListener1);
      localBuilder.setNegativeButton(paramActivity.getString(2131165210), paramOnClickListener2);
      localBuilder.setOnCancelListener(new DialogInterface.OnCancelListener(paramActivity)
      {
        public void onCancel()
        {
          Log.i("AS", "OnCancel");
          SharedPreferences.Editor localEditor = this.val$c.getSharedPreferences("AntSmasherShop", 0).edit();
          localEditor.putBoolean("InitialProductAlreadyOffered", true);
          localEditor.commit();
        }
      });
      int k = j + paramSharedPreferences.getInt("TapJoySpentPoints", 0);
      if ((k <= i) && (paramInt != 2))
      {
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Integer.valueOf(k - paramSharedPreferences.getInt("TapJoySpentPoints", 0));
        localBuilder.setNeutralButton(paramActivity.getString(2131165267, arrayOfObject1), paramOnClickListener3);
      }
      localBuilder.create();
      localBuilder.show();
      do
        while (true)
        {
          do
            while (true)
            {
              do
                while (true)
                {
                  do
                    while (true)
                    {
                      do
                        while (true)
                        {
                          do
                            while (true)
                            {
                              do
                                while (true)
                                {
                                  do
                                    while (true)
                                    {
                                      do
                                        return;
                                      while (paramSharedPreferences.getBoolean("KidsMode", false));
                                      j = 1000 / s;
                                      localObject1 = paramActivity.getString(2131165227);
                                      localObject2 = paramActivity.getString(2131165237);
                                    }
                                  while (paramSharedPreferences.getBoolean("FunMode", false));
                                  j = 500 / s;
                                  localObject1 = paramActivity.getString(2131165228);
                                  localObject2 = paramActivity.getString(2131165238);
                                }
                              while (paramSharedPreferences.getBoolean("NoAds", false));
                              localObject1 = paramActivity.getString(2131165230);
                              localObject2 = paramActivity.getString(2131165236);
                            }
                          while (paramSharedPreferences.getInt("MaxLifes", 0) > 3);
                          j = 300 / s;
                          localObject1 = paramActivity.getString(2131165231);
                          localObject2 = paramActivity.getString(2131165241);
                        }
                      while (paramSharedPreferences.getInt("MaxLifes", 0) > 3);
                      j = 600 / s;
                      localObject1 = paramActivity.getString(2131165232);
                      localObject2 = paramActivity.getString(2131165242);
                    }
                  while (paramSharedPreferences.getBoolean("Protection", false));
                  j = 500 / s;
                  localObject1 = paramActivity.getString(2131165234);
                  localObject2 = paramActivity.getString(2131165240);
                }
              while (paramSharedPreferences.getInt("MaxLifes", 0) != 4);
              j = 300 / s;
              localObject1 = paramActivity.getString(2131165233);
              localObject2 = paramActivity.getString(2131165243);
            }
          while (paramSharedPreferences.getBoolean("BabyMode", false));
          j = 1000 / s;
          localObject1 = paramActivity.getString(2131165229);
          localObject2 = paramActivity.getString(2131165239);
        }
      while (paramSharedPreferences.getBoolean("DeluxePack", false));
      j = 2500 / s;
      localObject1 = paramActivity.getString(2131165235);
      localObject2 = paramActivity.getString(2131165244);
    }
  }

  public static void showUseInstructionDetails(Context paramContext, SharedPreferences paramSharedPreferences, int paramInt)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    switch (paramInt)
    {
    default:
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      Log.e("NotImplemented", String.format("Não implementado product = %s", arrayOfObject));
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
    while (true)
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
      localBuilder.setTitle((CharSequence)localObject1);
      localBuilder.setMessage((CharSequence)localObject2);
      localBuilder.setNeutralButton(paramContext.getString(2131165211), new DialogInterface.OnClickListener()
      {
        public void onClick(, int paramInt)
        {
          paramDialogInterface.dismiss();
        }
      });
      localBuilder.create();
      localBuilder.show();
      do
        while (true)
        {
          do
            while (true)
            {
              do
                while (true)
                {
                  do
                    while (true)
                    {
                      do
                        while (true)
                        {
                          do
                            while (true)
                            {
                              do
                                while (true)
                                {
                                  do
                                    while (true)
                                    {
                                      do
                                        return;
                                      while (!(paramSharedPreferences.getBoolean("KidsMode", false)));
                                      localObject1 = paramContext.getString(2131165245);
                                      localObject2 = paramContext.getString(2131165254);
                                    }
                                  while (!(paramSharedPreferences.getBoolean("FunMode", false)));
                                  localObject1 = paramContext.getString(2131165246);
                                  localObject2 = paramContext.getString(2131165255);
                                }
                              while (!(paramSharedPreferences.getBoolean("NoAds", false)));
                              localObject1 = paramContext.getString(2131165248);
                              localObject2 = paramContext.getString(2131165257);
                            }
                          while (paramSharedPreferences.getInt("MaxLifes", 0) <= 3);
                          localObject1 = paramContext.getString(2131165249);
                          localObject2 = paramContext.getString(2131165258);
                        }
                      while (paramSharedPreferences.getInt("MaxLifes", 0) <= 3);
                      localObject1 = paramContext.getString(2131165250);
                      localObject2 = paramContext.getString(2131165259);
                    }
                  while (!(paramSharedPreferences.getBoolean("Protection", false)));
                  localObject1 = paramContext.getString(2131165251);
                  localObject2 = paramContext.getString(2131165260);
                }
              while (paramSharedPreferences.getInt("MaxLifes", 0) == 4);
              localObject1 = paramContext.getString(2131165252);
              localObject2 = paramContext.getString(2131165261);
            }
          while (!(paramSharedPreferences.getBoolean("BabyMode", false)));
          localObject1 = paramContext.getString(2131165247);
          localObject2 = paramContext.getString(2131165256);
        }
      while (!(paramSharedPreferences.getBoolean("DeluxePack", false)));
      localObject1 = paramContext.getString(2131165253);
      localObject2 = paramContext.getString(2131165262);
    }
  }
}