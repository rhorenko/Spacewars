package com.google.ads;

public class AdSize
{
  public static final AdSize BANNER = new AdSize(320, 50, "320x50_mb");
  public static final AdSize IAB_BANNER;
  public static final AdSize IAB_LEADERBOARD;
  public static final AdSize IAB_MRECT = new AdSize(300, 250, "300x250_as");
  private int a;
  private int b;
  private String c;

  static
  {
    IAB_BANNER = new AdSize(468, 60, "468x60_as");
    IAB_LEADERBOARD = new AdSize(728, 90, "728x90_as");
  }

  public AdSize(int paramInt1, int paramInt2)
  {
    this(paramInt1, paramInt2, null);
  }

  private AdSize(int paramInt1, int paramInt2, String paramString)
  {
    this.a = paramInt1;
    this.b = paramInt2;
    this.c = paramString;
  }

  public int getHeight()
  {
    return this.b;
  }

  public int getWidth()
  {
    return this.a;
  }

  public String toString()
  {
    return this.c;
  }
}