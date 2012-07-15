package com.revmob.ads;

public enum LoadError
{
  static
  {
    INVALID_ACTIVITY = new LoadError("INVALID_ACTIVITY", 3);
    LoadError[] arrayOfLoadError = new LoadError[4];
    arrayOfLoadError[0] = DEVICE_NOT_IDENTIFIED;
    arrayOfLoadError[1] = DOWNLOAD_ERROR;
    arrayOfLoadError[2] = PARSE_FAILED;
    arrayOfLoadError[3] = INVALID_ACTIVITY;
    $VALUES = arrayOfLoadError;
  }
}