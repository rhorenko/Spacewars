package com.revmob.ads;

public abstract interface OnLoadListener
{
  public abstract void onAdNotReceived(LoadError paramLoadError, String paramString);

  public abstract void onAdReceived(Ad paramAd);
}