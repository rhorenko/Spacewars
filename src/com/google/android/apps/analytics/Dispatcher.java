package com.google.android.apps.analytics;

abstract interface Dispatcher
{
  public abstract void dispatchEvents(Event[] paramArrayOfEvent);

  public abstract void init(Callbacks paramCallbacks, String paramString);

  public abstract boolean isDryRun();

  public abstract void setDryRun(boolean paramBoolean);

  public abstract void stop();

  public static abstract interface Callbacks
  {
    public abstract void dispatchFinished();

    public abstract void eventDispatched(long paramLong);
  }
}