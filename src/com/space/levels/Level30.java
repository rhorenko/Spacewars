package com.space.levels;

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
    paceY = (3 + Constants.initial_speed_increment);
    paceX = 4;
    objectPadding = 100;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 3;
    arrayOfInt1[1] = 3;
    arrayOfInt1[2] = 4;
    numberOfAntsWithType = arrayOfInt1;
    int[] arrayOfInt2 = new int[2];
    arrayOfInt2[0] = 5;
    arrayOfInt2[1] = 10;
    antAngle = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt2));
    int[] arrayOfInt3 = new int[2];
    arrayOfInt3[0] = 5;
    arrayOfInt3[1] = 10;
    antOrder = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt3));
    int[] arrayOfInt4 = new int[2];
    arrayOfInt4[0] = 5;
    arrayOfInt4[1] = 10;
    antDirection = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt4));
    beeAngle = new int[5];
    beeDirection = new int[5];
    beeOrder = new int[5];
    numberOfObjects = 10;
    boolean[] arrayOfBoolean = new boolean[numberOfObjects];
    int i = 0;
    if (i >= numberOfObjects)
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
        if (j < numberOfAntsWithType[b1])
          break;
        ++b1;
      }
      antAngle[b1][j] = 180;
      int[] arrayOfInt5 = antDirection[b1];
      if (antOrder[b1][j] % 2 == 0)
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
      if (k < numberOfAntsWithType[b3])
        break;
      ++b3;
    }
    do
      l = rand.nextInt(numberOfObjects);
    while (arrayOfBoolean[l] != 0);
    arrayOfBoolean[l] = true;
    antOrder[b3][k] = l;
    int[] arrayOfInt6 = antDirection[b3];
    if (antOrder[b3][k] % 2 == 0)
      b4 = 1;
    while (true)
    {
      while (true)
      {
        arrayOfInt6[k] = b4;
        ants[b3][k] = new SurfaceBitmap();
        antCounter = (1 + antCounter);
        ants[b3][k].setPosition(250 * l % 2, -70 - 50 * l / 2);
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
    if (passed);
    do
    {
      return;
      f = acceleration() / 48.0F;
    }
    while (paused);
    byte b = 0;
    while (true)
    {
      if (b >= 5);
      i = 0;
      if (i < numberOfAntsWithType[b])
        break;
      ++b;
    }
    if (smashed[b][i] == 0)
    {
      int j = ants[b][i].getLeft();
      if (ants[b][i].getTop() <= -ants[b][i].getHeight() / 2)
        break label407;
      d1 = 3.0D * (f + paceY) / 4.0F;
      k = j + (int)(d1 * antDirection[b][i]);
      l = Math.round(f + ants[b][i].getTop() + paceY);
      if ((((k > mContext.getResources().getDisplayMetrics().widthPixels - ants[b][i].getWidth()) || (k < 0))) && (l > 0))
      {
        int[] arrayOfInt = antDirection[b];
        arrayOfInt[i] = (-1 * arrayOfInt[i]);
        int i1 = ants[b][i].getLeft();
        if (ants[b][i].getTop() <= -ants[b][i].getHeight() / 2)
          break label413;
        d4 = 3.0D * (f + paceY) / 4.0F;
        k = i1 + (int)(d4 * antDirection[b][i]);
      }
      if (ants[b][i].getTop() <= -ants[b][i].getHeight() / 2)
        break label419;
      d2 = 3.0D * (f + paceY) / 4.0F;
    }
    while (true)
    {
      while (true)
      {
        while (true)
        {
          while (true)
          {
            double d3 = Math.atan2(d2 * antDirection[b][i], Math.round(f + paceY));
            antAngle[b][i] = (180 + -(int)Math.toDegrees(d3));
            ants[b][i].setPosition(k, l);
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