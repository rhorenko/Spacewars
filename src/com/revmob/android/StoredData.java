package com.revmob.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class StoredData
{
  private Context context;

  public StoredData(Context paramContext)
  {
    this.context = paramContext;
  }

  public boolean isAlreadyTracked()
  {
    return this.context.getSharedPreferences("RevMob", 0).getBoolean("Registered", false);
  }

  public void markAsTracked()
  {
    SharedPreferences.Editor localEditor = this.context.getSharedPreferences("RevMob", 0).edit();
    localEditor.putBoolean("Registered", true);
    localEditor.commit();
  }
}