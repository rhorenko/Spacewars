package com.space.levels;

import java.lang.reflect.Array;
import java.util.Random;

public class Level19 extends GameSurface
{
  public void startPositions()
  {
    byte b1;
    int k;
    byte b4;
    int i3;
    tempY = (2 + Constants.initial_speed_increment);
    tempX = 3;
    objectPadding = 280;
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
    numberOfBees = 2;
    numberOfObjects = 10;
    boolean[] arrayOfBoolean = new boolean[numberOfObjects];
    int i = 0;
    if (i >= numberOfObjects)
    {
      b1 = 0;
      if (b1 < 5)
        break label235;
      k = 0;
      if (k < numberOfBees)
        break label311;
      b4 = 0;
      if (b4 < 5)
        break label360;
      i3 = 0;
    }
    while (true)
    {
      label235: int l;
      label311: label360: int i1;
      byte b5;
      while (true)
      {
        int j;
        byte b2;
        byte b3;
        while (true)
        {
          while (true)
          {
            if (i3 < numberOfBees)
              break label521;
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
          b2 = -1;
        while (true)
        {
          while (true)
          {
            arrayOfInt5[j] = b2;
            ++j;
          }
          b2 = 1;
        }
        beeAngle[k] = 180;
        int[] arrayOfInt6 = beeDirection;
        if (rand.nextInt(2) == 0)
          b3 = -1;
        while (true)
        {
          while (true)
          {
            arrayOfInt6[k] = b3;
            ++k;
          }
          b3 = 1;
        }
        l = 0;
        if (l < numberOfAntsWithType[b4])
          break;
        ++b4;
      }
      do
        i1 = rand.nextInt(numberOfObjects);
      while (arrayOfBoolean[i1] != 0);
      arrayOfBoolean[i1] = true;
      antOrder[b4][l] = i1;
      ants[b4][l] = new SurfaceBitmap();
      antCounter = (1 + antCounter);
      SurfaceBitmap localSurfaceBitmap = ants[b4][l];
      int i2 = 100 + 85 * i1 % 2;
      if (i1 / 2 % 2 == 1)
        b5 = -1;
      while (true)
      {
        while (true)
        {
          localSurfaceBitmap.setPosition(i2 + b5 * 30, -50 - objectPadding * i1 / 2);
          ++l;
        }
        b5 = 1;
      }
      label521: bees[i3] = new SurfaceBitmap();
      bees[i3].setPosition(160 - antWidth / 2, -120 - 3 * i3 * objectPadding);
      ++i3;
    }
  }

  public void updatePositions()
  {
    float f;
    if (passed);
    do
    {
      return;
      f = acceleration() / 36.0F;
    }
    while (paused);
    byte b = 0;
    if (b >= 5)
    {
      int j = 0;
      while (true)
      {
        if (j >= numberOfBees);
        beeAngle[j] = (int)Math.round(beeAngle[j] + 2.6000000000000001D * beeDirection[j]);
        bees[j].setPosition(bees[j].getLeft() - (int)(Math.sin(Math.toRadians(180 + beeAngle[j])) * tempX), 2 + bees[j].getTop() - (int)(Math.cos(Math.toRadians(beeAngle[j])) * tempY));
        ++j;
      }
    }
    int i = 0;
    while (true)
    {
      while (i >= numberOfAntsWithType[b])
        ++b;
      if (smashed[b][i] == 0)
        ants[b][i].setPosition(ants[b][i].getLeft(), ants[b][i].getTop() + (int)(tempY * scale * (1F + f)));
      ++i;
    }
  }
}