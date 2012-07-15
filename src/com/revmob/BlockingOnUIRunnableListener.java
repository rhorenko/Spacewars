package com.revmob;

abstract interface BlockingOnUIRunnableListener<T>
{
  public abstract T onRunOnUIThread();
}