package com.space.levels;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.lang.reflect.Array;
import java.util.Random;

public class Level18 extends GameSurface
{
  private int[][] antLastTop;
  SurfaceBitmap[] antsInOrder;
  private final int back;
  private int[] beeLastLeft;
  private int[] beeLastTop;
  int[] k;

  public Level18()
  {
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = 5;
    arrayOfInt[1] = 10;
    antLastTop = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt));
    beeLastTop = new int[5];
    beeLastLeft = new int[5];
    back = -100;
  }

  private int fixLeft(int paramInt)
  {
    return Math.min(-80 + mContext.getResources().getDisplayMetrics().widthPixels, Math.max(0, paramInt));
  }

  public void startPositions()
  {
    int j;
    int i2;
    tempY = (2 + Constants.initial_speed_increment);
    tempX = 2;
    objectPadding = 350;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 1;
    arrayOfInt1[1] = 1;
    arrayOfInt1[2] = 1;
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
    numberOfBees = 3;
    numberOfObjects = 3;
    beeAngle = new int[3];
    beeDirection = new int[3];
    antsInOrder = new SurfaceBitmap[numberOfObjects];
    boolean[] arrayOfBoolean = new boolean[numberOfObjects];
    k = new int[numberOfObjects];
    int i = 0;
    if (i >= numberOfObjects)
    {
      j = 0;
      if (j < numberOfObjects)
        break label230;
      i2 = 0;
    }
    while (true)
    {
      label230: int[] arrayOfInt6;
      byte b2;
      while (true)
      {
        if (i2 < numberOfObjects)
          break label415;
        return;
        arrayOfBoolean[i] = false;
        ++i;
      }
      int l = 0;
      if (l >= numberOfAntsWithType[j])
      {
        beeAngle[j] = (18 * 18 * rand.nextInt(10));
        arrayOfInt6 = beeDirection;
        if (rand.nextInt(2) != 0)
          break label408;
        b2 = 1;
      }
      while (true)
      {
        byte b1;
        while (true)
        {
          arrayOfInt6[j] = b2;
          ++j;
        }
        antAngle[j][l] = 180;
        int[] arrayOfInt5 = antDirection[j];
        if (rand.nextInt(2) == 0)
          b1 = -1;
        while (true)
        {
          while (true)
          {
            int i1;
            arrayOfInt5[l] = b1;
            do
              i1 = rand.nextInt(numberOfObjects);
            while (arrayOfBoolean[i1] != 0);
            arrayOfBoolean[i1] = true;
            antOrder[j][l] = i1;
            k[i1] = (l + j * numberOfObjects);
            ++l;
          }
          b1 = 1;
        }
        label408: b2 = -1;
      }
      label415: antCounter = (1 + antCounter);
      int i3 = k[i2] / numberOfObjects;
      int i4 = k[i2] % numberOfObjects;
      antLastTop[i3][i4] = (-90 - i2 * objectPadding);
      ants[i3][i4] = new SurfaceBitmap();
      ants[i3][i4].setPosition(160 - antWidth / 2 + -30 + InitialView.rand.nextInt(61), Math.max(antLastTop[i3][i4], -100));
      antsInOrder[i2] = ants[(k[i2] / numberOfObjects)][(k[i2] % numberOfObjects)];
      bees[i2] = new SurfaceBitmap();
      beeLastTop[i2] = (int)(antLastTop[i3][i4] - Math.cos(beeAngle[i2]) * tempX);
      beeLastLeft[i2] = (int)(ants[i3][i4].getLeft() - Math.sin(3.1415000000000002D + beeAngle[i2]) * tempX);
      bees[i2].setPosition(fixLeft(beeLastLeft[i2]), Math.max(beeLastTop[i2], -100));
      ++i2;
    }
  }

  public void updatePositions()
  {
    int i;
    if (!(paused))
    {
      i = 0;
      if (i < numberOfObjects)
        break label18;
    }
    return;
    label18: int j = k[i] / numberOfObjects;
    int l = k[i] % numberOfObjects;
    if (smashed[j][l] == 0)
    {
      if (ants[j][l].getLeft() > mCanvasWidth - ants[j][l].getWidth())
        antDirection[j][l] = -1;
      if (ants[j][l].getLeft() < 0)
        antDirection[j][l] = 1;
      float f = acceleration() / 48.0F;
      antLastTop[j][l] = (antLastTop[j][l] + (int)(tempY * scale * (1F + f / 3.0F)));
      ants[j][l].setPosition(Math.round(ants[j][l].getLeft() + f * antDirection[j][l] * tempX), Math.max(antLastTop[j][l], -100));
      double d = Math.atan2(f * antDirection[j][l] * tempX, tempY * scale * (1F + f / 3.0F));
      antAngle[j][l] = (180 + -(int)Math.toDegrees(d));
    }
    if (smashed[j][l] == 0)
    {
      int[] arrayOfInt = beeAngle;
      arrayOfInt[i] = (arrayOfInt[i] + 5 * beeDirection[i]);
      int i2 = ants[j][l].getLeft() - ants[j][l].getWidth() / 4;
      int i3 = antLastTop[j][l] - ants[j][l].getHeight() / 4;
      beeLastTop[i] = (int)(i3 + 120.0D * Math.cos(Math.toRadians(beeAngle[i] + 90 * beeDirection[i])));
      beeLastLeft[i] = (int)Math.round(i2 + 120.0D * Math.sin(Math.toRadians(180 + beeAngle[i] + 90 * beeDirection[i])));
      bees[i].setPosition(fixLeft(beeLastLeft[i]), Math.max(beeLastTop[i], -100));
    }
    while (true)
    {
      while (true)
        ++i;
      int i1 = mContext.getResources().getDisplayMetrics().heightPixels;
      bees[i].setPosition(bees[i].getLeft(), Math.min(12 + bees[i].getTop(), i1));
    }
  }
}