package com.google.android.apps.analytics;

abstract interface EventStore
{
  public abstract void deleteEvent(long paramLong);

  public abstract int getNumStoredEvents();

  public abstract String getReferrer();

  public abstract int getStoreId();

  public abstract String getVisitorCustomVar(int paramInt);

  public abstract Event[] peekEvents();

  public abstract Event[] peekEvents(int paramInt);

  public abstract void putEvent(Event paramEvent);

  public abstract void setReferrer(String paramString);

  public abstract void startNewVisit();
}