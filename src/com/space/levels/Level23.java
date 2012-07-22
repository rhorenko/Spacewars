package com.space.levels;

import java.lang.reflect.Array;
import java.util.Random;

public class Level23 extends GameSurface
{
  int[][] antDirDev;

  public void startPositions()
  {
    byte b1;
    int j;
    byte b2;
    int[] arrayOfInt7;
    byte b3;
    byte b4;
    paceY = (3 + Constants.initial_speed_increment);
    paceX = 4;
    objectPadding = 150;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 2;
    arrayOfInt1[1] = 2;
    arrayOfInt1[2] = 2;
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
    antDirDev = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt5));
    numberOfObjects = 6;
    boolean[] arrayOfBoolean = new boolean[numberOfObjects];
    int i = 0;
    if (i >= numberOfObjects)
      b1 = 0;
    while (true)
    {
      while (b1 >= 5)
      {
        b4 = 0;
        if (b4 < 5)
          break label346;
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
    int[] arrayOfInt6 = antDirection[b1];
    if (rand.nextInt(2) == 0)
    {
      b2 = -1;
      arrayOfInt6[j] = b2;
      antDirection[b1][j] = rand.nextInt(4);
      arrayOfInt7 = antDirDev[b1];
      if (rand.nextInt(2) != 1)
        break label339;
      b3 = 1;
    }
    while (true)
    {
      while (true)
      {
        while (true)
        {
          arrayOfInt7[j] = b3;
          ++j;
        }
        b2 = 1;
      }
      label339: b3 = -1;
    }
    label346: int k = 0;
    while (true)
    {
      int l;
      while (k >= numberOfAntsWithType[b4])
        ++b4;
      do
        l = rand.nextInt(numberOfObjects);
      while (arrayOfBoolean[l] != 0);
      arrayOfBoolean[l] = true;
      ants[b4][k] = new SurfaceBitmap();
      antCounter = (1 + antCounter);
      antOrder[b4][k] = l;
      ants[b4][k].setPosition(160 - antWidth / 2 + (-1 + l % 3) * antWidth, -50 - l * objectPadding);
      ++k;
    }
  }

  public void updatePositions()
  {
    byte b1;
    int i;
    int[] arrayOfInt1;
    byte b3;
    if (!(paused))
      b1 = 0;
    while (true)
    {
      if (b1 >= 5)
        return;
      i = 0;
      if (i < numberOfAntsWithType[b1])
        break;
      ++b1;
    }
    if (smashed[b1][i] == 0)
    {
      float f = acceleration() / 30.0F;
      SurfaceBitmap localSurfaceBitmap = ants[b1][i];
      int j = ants[b1][i].getLeft();
      int k = antDirDev[b1][i] * antDirection[b1][i] / 3 % 2;
      if (antDirection[b1][i] / 3 % 4 != 1)
        break label258;
      int l = 1;
      localSurfaceBitmap.setPosition(Math.round(j + l * k * paceX), Math.round(ants[b1][i].getTop() + (f + paceY) * (1 + antDirection[b1][i] / 3) % 2));
      if (counter % 6 == 0)
      {
        int[] arrayOfInt2 = antDirection[b1];
        arrayOfInt2[i] = (1 + arrayOfInt2[i]);
      }
      arrayOfInt1 = antAngle[b1];
      if (antDirection[b1][i] / 3 % 2 != 0)
        break label265;
      b3 = 1;
    }
    while (true)
    {
      while (true)
      {
        while (true)
        {
          while (true)
          {
            arrayOfInt1[i] = (90 * (b3 + 1) * antDirDev[b1][i]);
            ++i;
          }
          label258: byte b2 = -1;
        }
        label265: if (antDirection[b1][i] / 3 % 4 != 1)
          break;
        b3 = 0;
      }
      b3 = 2;
    }
  }
}