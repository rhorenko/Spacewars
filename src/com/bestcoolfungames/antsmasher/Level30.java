package com.bestcoolfungames.antsmasher;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.lang.reflect.Array;
import java.util.Random;

public class Level30 extends GameSurface
{
  public void startPositions()
  {
    byte b1;
    byte b3;
    int k;
    int l;
    byte b4;
    this.paceY = (3 + Constants.initial_speed_increment);
    this.paceX = 4;
    this.objectPadding = 100;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 3;
    arrayOfInt1[1] = 3;
    arrayOfInt1[2] = 4;
    this.numberOfAntsWithType = arrayOfInt1;
    int[] arrayOfInt2 = new int[2];
    arrayOfInt2[0] = 5;
    arrayOfInt2[1] = 10;
    this.antAngle = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt2));
    int[] arrayOfInt3 = new int[2];
    arrayOfInt3[0] = 5;
    arrayOfInt3[1] = 10;
    this.antOrder = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt3));
    int[] arrayOfInt4 = new int[2];
    arrayOfInt4[0] = 5;
    arrayOfInt4[1] = 10;
    this.antDirection = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt4));
    this.beeAngle = new int[5];
    this.beeDirection = new int[5];
    this.beeOrder = new int[5];
    this.numberOfObjects = 10;
    boolean[] arrayOfBoolean = new boolean[this.numberOfObjects];
    int i = 0;
    if (i >= this.numberOfObjects)
    {
      b1 = 0;
      if (b1 < 5)
        break label205;
      b3 = 0;
    }
    while (true)
    {
      label205: int j;
      byte b2;
      while (true)
      {
        while (true)
        {
          if (b3 < 5)
            break label286;
          return;
          arrayOfBoolean[i] = false;
          ++i;
        }
        j = 0;
        if (j < this.numberOfAntsWithType[b1])
          break;
        ++b1;
      }
      this.antAngle[b1][j] = 180;
      int[] arrayOfInt5 = this.antDirection[b1];
      if (this.antOrder[b1][j] % 2 == 0)
        b2 = 1;
      while (true)
      {
        while (true)
        {
          arrayOfInt5[j] = b2;
          ++j;
        }
        b2 = -1;
      }
      label286: k = 0;
      if (k < this.numberOfAntsWithType[b3])
        break;
      ++b3;
    }
    do
      l = rand.nextInt(this.numberOfObjects);
    while (arrayOfBoolean[l] != 0);
    arrayOfBoolean[l] = true;
    this.antOrder[b3][k] = l;
    int[] arrayOfInt6 = this.antDirection[b3];
    if (this.antOrder[b3][k] % 2 == 0)
      b4 = 1;
    while (true)
    {
      while (true)
      {
        arrayOfInt6[k] = b4;
        this.ants[b3][k] = new SurfaceBitmap();
        this.antCounter = (1 + this.antCounter);
        this.ants[b3][k].setPosition(250 * l % 2, -70 - 50 * l / 2);
        ++k;
      }
      b4 = -1;
    }
  }

  public void updatePositions()
  {
    float f;
    int i;
    double d1;
    int k;
    int l;
    double d2;
    double d4;
    if (this.passed);
    do
    {
      return;
      f = acceleration() / 48.0F;
    }
    while (this.paused);
    byte b = 0;
    while (true)
    {
      if (b >= 5);
      i = 0;
      if (i < this.numberOfAntsWithType[b])
        break;
      ++b;
    }
    if (this.smashed[b][i] == 0)
    {
      int j = this.ants[b][i].getLeft();
      if (this.ants[b][i].getTop() <= -this.ants[b][i].getHeight() / 2)
        break label407;
      d1 = 3.0D * (f + this.paceY) / 4.0F;
      k = j + (int)(d1 * this.antDirection[b][i]);
      l = Math.round(f + this.ants[b][i].getTop() + this.paceY);
      if ((((k > this.mContext.getResources().getDisplayMetrics().widthPixels - this.ants[b][i].getWidth()) || (k < 0))) && (l > 0))
      {
        int[] arrayOfInt = this.antDirection[b];
        arrayOfInt[i] = (-1 * arrayOfInt[i]);
        int i1 = this.ants[b][i].getLeft();
        if (this.ants[b][i].getTop() <= -this.ants[b][i].getHeight() / 2)
          break label413;
        d4 = 3.0D * (f + this.paceY) / 4.0F;
        k = i1 + (int)(d4 * this.antDirection[b][i]);
      }
      if (this.ants[b][i].getTop() <= -this.ants[b][i].getHeight() / 2)
        break label419;
      d2 = 3.0D * (f + this.paceY) / 4.0F;
    }
    while (true)
    {
      while (true)
      {
        while (true)
        {
          while (true)
          {
            double d3 = Math.atan2(d2 * this.antDirection[b][i], Math.round(f + this.paceY));
            this.antAngle[b][i] = (180 + -(int)Math.toDegrees(d3));
            this.ants[b][i].setPosition(k, l);
            ++i;
          }
          label407: d1 = 0D;
        }
        label413: d4 = 0D;
      }
      label419: d2 = 0D;
    }
  }
}