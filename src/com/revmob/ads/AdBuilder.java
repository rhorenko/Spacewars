package com.revmob.ads;

import android.app.Activity;

public abstract class AdBuilder
{
  protected Activity activity;

  public AdBuilder(Activity paramActivity)
  {
    this.activity = paramActivity;
  }

  public abstract Ad build(String paramString);
}