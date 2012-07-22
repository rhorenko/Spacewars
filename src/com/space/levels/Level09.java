package com.space.levels;

import java.lang.reflect.Array;
import java.util.Random;

public class Level09 extends GameSurface
{
  public void startPositions()
  {
    byte b1;
    int j;
    byte b2;
    int k;
    byte b3;
    byte b4;
    int i2;
    tempY = (3 + Constants.initial_speed_increment);
    tempX = 4;
    objectPadding = 170;
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
      b1 = 0;
    while (true)
    {
      while (b1 >= 5)
      {
        k = 0;
        if (k < numberOfBees)
          break label311;
        b4 = 0;
        if (b4 < 5)
          break label360;
        i2 = 0;
        if (i2 < numberOfBees)
          break label491;
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
    label311: beeAngle[k] = 180;
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
    label360: int l = 0;
    while (true)
    {
      int i1;
      while (l >= numberOfAntsWithType[b4])
        ++b4;
      do
        i1 = rand.nextInt(numberOfObjects);
      while (arrayOfBoolean[i1] != 0);
      arrayOfBoolean[i1] = true;
      antOrder[b4][l] = i1;
      ants[b4][l] = new SurfaceBitmap();
      antCounter = (1 + antCounter);
      ants[b4][l].setPosition(160 + -75 + rand.nextInt(151), -50 - i1 * objectPadding);
      ++l;
    }
    label491: bees[i2] = new SurfaceBitmap();
    if (beeDirection[i2] == 1)
      bees[i2].setPosition(250, (int)(-60.0D - 2.5D * i2 * objectPadding));
    while (true)
    {
      while (true)
        ++i2;
      bees[i2].setPosition(70, (int)(-60.0D - 2.5D * i2 * objectPadding));
    }
  }

  public void updatePositions()
  {
    if (passed);
    do
      return;
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
      {
        if (ants[b][i].getLeft() > mCanvasWidth - ants[b][i].getWidth())
          antDirection[b][i] = -1;
        if (ants[b][i].getLeft() < 0)
          antDirection[b][i] = 1;
        float f = acceleration() / 50.0F;
        ants[b][i].setPosition(Math.round(ants[b][i].getLeft() + antDirection[b][i] * tempX * (f + 1F)), ants[b][i].getTop() + (int)(tempY * scale * (1F + f / 3.0F)));
        double d = Math.atan2(antDirection[b][i] * tempX * (f + 1F), tempY * scale * (1F + f / 3.0F));
        antAngle[b][i] = (180 + -(int)Math.toDegrees(d));
      }
      ++i;
    }
  }
}