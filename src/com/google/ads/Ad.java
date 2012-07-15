package com.google.ads;

public abstract interface Ad
{
  public abstract boolean isReady();

  public abstract void loadAd(AdRequest paramAdRequest);

  public abstract void setAdListener(AdListener paramAdListener);

  public abstract void stopLoading();
}