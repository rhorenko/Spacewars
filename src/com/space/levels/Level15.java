package com.space.levels;

import java.lang.reflect.Array;
import java.util.Random;

public class Level15 extends GameSurface
{
  public void startPositions()
  {
    int i;
    byte b2;
    int j;
    byte b3;
    byte b4;
    paceY = (2 + Constants.initial_speed_increment);
    paceX = 4;
    objectPadding = 100;
    int[] arrayOfInt1 = new int[9];
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
    beeAngle = new int[5];
    beeDirection = new int[5];
    beeOrder = new int[5];
    numberOfBees = 4;
    byte b1 = 0;
    while (true)
    {
      if (b1 >= 5)
      {
        j = 0;
        if (j < numberOfBees)
          break label362;
        b4 = 0;
        if (b4 < 5)
          break label411;
        bees[0] = new SurfaceBitmap();
        bees[0].setPosition(160, -255);
        bees[1] = new SurfaceBitmap();
        bees[1].setPosition(90, -150);
        bees[2] = new SurfaceBitmap();
        bees[2].setPosition(160, -75);
        bees[3] = new SurfaceBitmap();
        bees[3].setPosition(220, -150);
        return;
      }
      i = 0;
      if (i < numberOfAntsWithType[b1])
        break;
      ++b1;
    }
    antAngle[b1][i] = 180;
    int[] arrayOfInt5 = antDirection[b1];
    if (rand.nextInt(2) == 0)
      b2 = -1;
    while (true)
    {
      while (true)
      {
        arrayOfInt5[i] = b2;
        ++i;
      }
      b2 = 1;
    }
    label362: beeAngle[j] = 180;
    int[] arrayOfInt6 = beeDirection;
    if (rand.nextInt(2) == 0)
      b3 = -1;
    while (true)
    {
      while (true)
      {
        arrayOfInt6[j] = b3;
        ++j;
      }
      b3 = 1;
    }
    label411: int k = 0;
    while (true)
    {
      while (k >= numberOfAntsWithType[b4])
        ++b4;
      antCounter = (1 + antCounter);
      ants[b4][k] = new SurfaceBitmap();
      ants[b4][k].setPosition(160, -150);
      ++k;
    }
  }

  public void updatePositions()
  {
    int i;
    if (passed);
    do
      return;
    while (paused);
    float f = acceleration() / 100.0F;
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
      if (ants[b][i].getLeft() > -75 + mCanvasWidth - ants[b][i].getWidth())
        antDirection[b][i] = -1;
      if (ants[b][i].getLeft() < 75)
        antDirection[b][i] = 1;
      ants[b][i].setPosition(Math.round(ants[b][i].getLeft() + f * antDirection[b][i] * paceX), ants[b][i].getTop() + (int)(paceY * scale * (1F + f)));
      double d = Math.atan2(f * antDirection[b][i] * paceX, paceY * scale * (1F + f));
      antAngle[b][i] = (180 + -(int)Math.toDegrees(d));
      bees[0].setPosition(ants[b][i].getLeft(), -105 + ants[b][i].getTop());
      bees[1].setPosition(-95 + ants[b][i].getLeft(), ants[b][i].getTop());
      bees[2].setPosition(ants[b][i].getLeft(), 105 + ants[b][i].getTop());
      bees[3].setPosition(85 + ants[b][i].getLeft(), ants[b][i].getTop());
      beeAngle[0] = antAngle[b][i];
      beeAngle[1] = antAngle[b][i];
      beeAngle[2] = antAngle[b][i];
      beeAngle[3] = antAngle[b][i];
    }
    while (true)
    {
      while (true)
        ++i;
      bees[0].setPosition(bees[0].getLeft(), bees[0].getTop() + paceY);
      bees[1].setPosition(bees[1].getLeft(), bees[1].getTop() + paceY);
      bees[2].setPosition(bees[2].getLeft(), bees[2].getTop() + paceY);
      bees[3].setPosition(bees[3].getLeft(), bees[3].getTop() + paceY);
      beeAngle[0] = 180;
      beeAngle[1] = 180;
      beeAngle[2] = 180;
      beeAngle[3] = 180;
    }
  }
}