package com.space.levels;

import java.lang.reflect.Array;
import java.util.Random;

public class Level24 extends GameSurface
{
  int[][] antRunningStart;

  public void startPositions()
  {
    byte b1;
    int j;
    byte b2;
    byte b3;
    paceY = (2 + Constants.initial_speed_increment);
    paceX = 2;
    objectPadding = 120;
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
    antRunningStart = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt5));
    numberOfObjects = 6;
    boolean[] arrayOfBoolean = new boolean[numberOfObjects];
    int i = 0;
    if (i >= numberOfObjects)
      b1 = 0;
    while (true)
    {
      while (b1 >= 5)
      {
        b3 = 0;
        if (b3 < 5)
          break label310;
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
      b2 = -1;
    while (true)
    {
      while (true)
      {
        arrayOfInt6[j] = b2;
        antRunningStart[b1][j] = rand.nextInt(251);
        ++j;
      }
      b2 = 1;
    }
    label310: int k = 0;
    while (true)
    {
      int l;
      while (k >= numberOfAntsWithType[b3])
        ++b3;
      ants[b3][k] = new SurfaceBitmap();
      antCounter = (1 + antCounter);
      do
        l = rand.nextInt(numberOfObjects);
      while (arrayOfBoolean[l] != 0);
      arrayOfBoolean[l] = true;
      antOrder[b3][k] = l;
      ants[b3][k].setPosition(160 - antWidth / 2 + (-1 + l % 3) * antWidth, -(int)(50.0F * scale) - objectPadding * antOrder[b3][k]);
      ++k;
    }
  }

  public void updatePositions()
  {
    byte b;
    if (passed);
    do
    {
      do
        return;
      while (paused);
      b = 0;
    }
    while (b >= 5);
    int i = 0;
    while (true)
    {
      while (i >= numberOfAntsWithType[b])
        ++b;
      if (smashed[b][i] == 0)
      {
        int j = 0;
        if ((ants[b][i].getTop() > antRunningStart[b][i]) && (ants[b][i].getTop() < 200 + antRunningStart[b][i]))
          j = 6;
        float f = Math.min(10.0F, acceleration() / 24.0F);
        ants[b][i].setPosition(ants[b][i].getLeft(), Math.round(f + j + ants[b][i].getTop() + paceY));
      }
      ++i;
    }
  }
}