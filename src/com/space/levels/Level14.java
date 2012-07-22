package com.space.levels;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.lang.reflect.Array;
import java.util.Random;

public class Level14 extends GameSurface
{
  int[][] antAcc;
  int[][] antAccDir;

  public void startPositions()
  {
    byte b1;
    int k;
    byte b2;
    byte b3;
    int i = (int)(mContext.getResources().getDisplayMetrics().widthPixels * scale);
    paceY = (4 + Constants.initial_speed_increment);
    paceX = 2;
    objectPadding = 230;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 3;
    arrayOfInt1[1] = 3;
    arrayOfInt1[2] = 3;
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
    int[] arrayOfInt5 = new int[2];
    arrayOfInt5[0] = 5;
    arrayOfInt5[1] = 10;
    antAcc = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt5));
    int[] arrayOfInt6 = new int[2];
    arrayOfInt6[0] = 5;
    arrayOfInt6[1] = 10;
    antAccDir = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt6));
    numberOfObjects = 9;
    boolean[] arrayOfBoolean = new boolean[numberOfObjects];
    int j = 0;
    if (j >= numberOfObjects)
      b1 = 0;
    while (true)
    {
      while (b1 >= 5)
      {
        b3 = 0;
        if (b3 < 5)
          break label408;
        return;
        arrayOfBoolean[j] = false;
        ++j;
      }
      k = 0;
      if (k < numberOfAntsWithType[b1])
        break;
      ++b1;
    }
    antAngle[b1][k] = 180;
    int[] arrayOfInt7 = antDirection[b1];
    if (rand.nextInt(2) == 0)
      b2 = -1;
    while (true)
    {
      while (true)
      {
        int l;
        arrayOfInt7[k] = b2;
        do
          l = rand.nextInt(numberOfObjects);
        while (arrayOfBoolean[l] != 0);
        arrayOfBoolean[l] = true;
        antOrder[b1][k] = l;
        antAcc[b1][k] = 0;
        antAccDir[b1][k] = 0;
        ++k;
      }
      b2 = 1;
    }
    label408: int i1 = 0;
    while (true)
    {
      while (i1 >= numberOfAntsWithType[b3])
        ++b3;
      antCounter = (1 + antCounter);
      ants[b3][i1] = new SurfaceBitmap();
      ants[b3][i1].setPosition(-25 + i / 2 + rand.nextInt(i / 3) - i / 6, -(int)(50.0F * scale) - objectPadding * antOrder[b3][i1]);
      ++i1;
    }
  }

  public void updatePositions()
  {
    int i;
    byte b2;
    int[] arrayOfInt4;
    byte b3;
    if (passed);
    do
      return;
    while (paused);
    byte b1 = 0;
    while (true)
    {
      if (b1 >= 5);
      i = 0;
      if (i < numberOfAntsWithType[b1])
        break;
      ++b1;
    }
    if (smashed[b1][i] == 0)
      if ((antAccDir[b1][i] == 0) && (ants[b1][i].getTop() > -2 * ants[b1][i].getHeight()))
      {
        int[] arrayOfInt3 = antAccDir[b1];
        if (rand.nextInt(2) != 0)
          break label466;
        b2 = -1;
        arrayOfInt3[i] = b2;
        arrayOfInt4 = antAcc[b1];
        if (rand.nextInt(2) != 0)
          break label472;
        b3 = -2;
      }
    while (true)
    {
      while (true)
      {
        while (true)
        {
          arrayOfInt4[i] = b3;
          if (ants[b1][i].getLeft() > mCanvasWidth - ants[b1][i].getWidth())
            antDirection[b1][i] = -1;
          if (ants[b1][i].getLeft() < 0)
            antDirection[b1][i] = 1;
          int j = paceX;
          int k = paceY;
          if (Math.abs(antAcc[b1][i]) > 3)
          {
            int[] arrayOfInt2 = antAccDir[b1];
            arrayOfInt2[i] = (-1 * arrayOfInt2[i]);
          }
          if (ants[b1][i].getTop() > -ants[b1][i].getHeight())
          {
            int[] arrayOfInt1 = antAcc[b1];
            arrayOfInt1[i] = (int)(arrayOfInt1[i] + 0.29999999999999999D * antAccDir[b1][i]);
          }
          int l = k + antAcc[b1][i];
          float f = acceleration() / 100.0F;
          ants[b1][i].setPosition(Math.round(ants[b1][i].getLeft() + f * j * antDirection[b1][i]), ants[b1][i].getTop() + (int)(l * scale * (1F + f / 3.0F)));
          double d = Math.atan2(f * j * antDirection[b1][i], l * scale * (1F + f / 3.0F));
          antAngle[b1][i] = (180 + -(int)Math.toDegrees(d));
          ++i;
        }
        label466: b2 = 1;
      }
      label472: b3 = 2;
    }
  }
}