package com.space.levels;

import java.lang.reflect.Array;
import java.util.Random;

public class Level04 extends GameSurface
{
  public void startPositions()
  {
    byte b1;
    int j;
    byte b2;
    byte b3;
    paceY = (4 + Constants.initial_speed_increment);
    paceX = 5;
    objectPadding = 450;
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
          break label303;
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
    if (rand.nextInt(2) == 0)
      b2 = 1;
    while (true)
    {
      while (true)
      {
        int k;
        arrayOfInt5[j] = b2;
        do
          k = new Random().nextInt(numberOfObjects);
        while (arrayOfBoolean[k] != 0);
        arrayOfBoolean[k] = true;
        antOrder[b1][j] = k;
        ++j;
      }
      b2 = -1;
    }
    label303: int l = 0;
    while (true)
    {
      while (l >= numberOfAntsWithType[b3])
        ++b3;
      ants[b3][l] = new SurfaceBitmap();
      antCounter = (1 + antCounter);
      ants[b3][l].setPosition(160 + -100 + rand.nextInt(201), -50 - objectPadding * antOrder[b3][l]);
      ++l;
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
        if (ants[b][i].getLeft() > mCanvasWidth - ants[b][i].getWidth())
          antDirection[b][i] = -1;
        if (ants[b][i].getLeft() < 0)
          antDirection[b][i] = 1;
        float f = acceleration() / 60.0F;
        ants[b][i].setPosition(Math.round(ants[b][i].getLeft() + f * antDirection[b][i] * paceX), ants[b][i].getTop() + (int)(paceY * scale * (1F + f / 3.0F)));
        double d = Math.atan2((int)(f * antDirection[b][i] * paceX), (int)(paceY * scale * (1F + f / 3.0F)));
        antAngle[b][i] = (180 + -(int)Math.toDegrees(d));
      }
      ++i;
    }
  }
}