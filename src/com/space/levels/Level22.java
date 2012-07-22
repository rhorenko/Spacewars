package com.space.levels;

import java.lang.reflect.Array;
import java.util.Random;

public class Level22 extends GameSurface
{
  public void startPositions()
  {
    byte b1;
    byte b2;
    int k;
    int l;
    byte b3;
    SurfaceBitmap localSurfaceBitmap;
    int i1;
    byte b4;
    tempY = (4 + Constants.initial_speed_increment);
    tempX = 4;
    objectPadding = 170;
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
    beeAngle = new int[5];
    beeDirection = new int[5];
    beeOrder = new int[5];
    numberOfBees = 0;
    numberOfObjects = 6;
    boolean[] arrayOfBoolean = new boolean[numberOfObjects];
    int i = 0;
    if (i >= numberOfObjects)
    {
      b1 = 0;
      if (b1 < 5)
        break label211;
      b2 = 0;
    }
    while (true)
    {
      while (true)
      {
        if (b2 < 5)
          break label251;
        return;
        arrayOfBoolean[i] = false;
        ++i;
      }
      label211: int j = 0;
      while (true)
      {
        while (j >= numberOfAntsWithType[b1])
          ++b1;
        antAngle[b1][j] = 180;
        ++j;
      }
      label251: k = 0;
      if (k < numberOfAntsWithType[b2])
        break;
      ++b2;
    }
    do
      l = rand.nextInt(numberOfObjects);
    while (arrayOfBoolean[l] != 0);
    arrayOfBoolean[l] = true;
    antOrder[b2][k] = l;
    int[] arrayOfInt5 = antDirection[b2];
    if (l % 2 == 1)
    {
      b3 = 1;
      arrayOfInt5[k] = b3;
      ants[b2][k] = new SurfaceBitmap();
      antCounter = (1 + antCounter);
      localSurfaceBitmap = ants[b2][k];
      i1 = 160 - antWidth / 2;
      if (l % 2 != 1)
        break label437;
      b4 = -1;
    }
    while (true)
    {
      while (true)
      {
        while (true)
        {
          localSurfaceBitmap.setPosition(i1 + b4 * 20, -50 - objectPadding * l / 2);
          ++k;
        }
        b3 = -1;
      }
      label437: b4 = 1;
    }
  }

  public void updatePositions()
  {
    float f;
    byte b;
    if (passed);
    do
    {
      do
        return;
      while (paused);
      f = acceleration() / 60.0F;
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
        if (ants[b][i].getLeft() > mCanvasWidth - ants[b][i].getWidth())
          antAngle[b][i] = 225;
        if (ants[b][i].getLeft() < 0)
          antAngle[b][i] = 135;
        antAngle[b][i] = (int)Math.round(antAngle[b][i] + 9.5999999999999996D * antDirection[b][i]);
        if ((antAngle[b][i] > 270) || (antAngle[b][i] < 90))
        {
          int[] arrayOfInt = antDirection[b];
          arrayOfInt[i] = (-1 * arrayOfInt[i]);
        }
        ants[b][i].setPosition((int)Math.round(ants[b][i].getLeft() + tempX * Math.sin(Math.toRadians(antAngle[b][i]))), (int)Math.round(ants[b][i].getTop() - (f + tempY) * Math.cos(Math.toRadians(antAngle[b][i]))));
      }
      ++i;
    }
  }
}