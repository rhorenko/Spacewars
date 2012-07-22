package com.space.levels;

import java.lang.reflect.Array;
import java.util.Random;

public class Level11 extends GameSurface
{
  public void startPositions()
  {
    int j;
    byte b3;
    int l;
    tempY = (3 + Constants.initial_speed_increment);
    tempX = 4;
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
    numberOfBees = 1;
    byte b1 = 0;
    byte b2 = 0;
    if (b2 >= 5)
    {
      j = 0;
      if (j < numberOfBees)
        break label267;
      b3 = 0;
      if (b3 < 5)
        break label292;
      l = 0;
    }
    while (true)
    {
      while (true)
      {
        int i;
        while (true)
        {
          if (l < numberOfBees)
            break label370;
          return;
          i = 0;
          if (i < numberOfAntsWithType[b2])
            break;
          ++b2;
        }
        antAngle[b2][i] = 180;
        int[] arrayOfInt5 = antDirection[b2];
        if (rand.nextInt(2) == 0)
          b1 = -1;
        while (true)
        {
          while (true)
          {
            arrayOfInt5[i] = b1;
            ++i;
          }
          b1 = 1;
        }
        label267: beeAngle[j] = 180;
        beeDirection[j] = b1;
        ++j;
      }
      label292: int k = 0;
      while (true)
      {
        while (k >= numberOfAntsWithType[b3])
          ++b3;
        antCounter = (1 + antCounter);
        ants[b3][k] = new SurfaceBitmap();
        ants[b3][k].setPosition(160 - antWidth / 2, -50);
        ++k;
      }
      label370: bees[l] = new SurfaceBitmap();
      bees[l].setPosition(160 - antWidth / 2, -55);
      ++l;
    }
  }

  public void updatePositions()
  {
    float f1;
    byte b;
    int j;
    float f2;
    if (passed);
    do
    {
      do
      {
        return;
        f1 = Math.min(10.0F, acceleration() / 50.0F);
      }
      while (paused);
      b = 0;
      if (b < 5)
        break label309;
      j = 0;
    }
    while (j >= numberOfBees);
    beeAngle[j] = (beeAngle[j] + 6 * beeDirection[j]);
    if ((beeAngle[j] > 258) || (beeAngle[j] < 90))
    {
      int[] arrayOfInt2 = beeDirection;
      arrayOfInt2[j] = (-1 * arrayOfInt2[j]);
    }
    if (bees[j].getLeft() > mCanvasWidth - bees[j].getWidth())
      beeAngle[j] = 225;
    if (bees[j].getLeft() < 0)
      beeAngle[j] = 135;
    SurfaceBitmap localSurfaceBitmap = bees[j];
    int k = (int)Math.round(bees[j].getLeft() + tempX * Math.sin(Math.toRadians(beeAngle[j])));
    if (bees[j].getTop() > mCanvasHeight / 2)
      f2 = 2F + 3.0F * f1 / 2F;
    while (true)
    {
      while (true)
      {
        localSurfaceBitmap.setPosition(k, (int)Math.round(f2 + bees[j].getTop() - (f1 + tempY) * Math.cos(Math.toRadians(beeAngle[j]))));
        ++j;
      }
      label309: int i = 0;
      while (true)
      {
        while (i >= numberOfAntsWithType[b])
          ++b;
        if (smashed[b][i] == 0)
        {
          antAngle[b][i] = (antAngle[b][i] + 6 * antDirection[b][i]);
          if ((antAngle[b][i] > 258) || (antAngle[b][i] < 90))
          {
            int[] arrayOfInt1 = antDirection[b];
            arrayOfInt1[i] = (-1 * arrayOfInt1[i]);
          }
          if (ants[b][i].getLeft() > mCanvasWidth - ants[b][i].getWidth())
            antAngle[b][i] = 225;
          if (ants[b][i].getLeft() < 0)
            antAngle[b][i] = 135;
          ants[b][i].setPosition((int)Math.round(ants[b][i].getLeft() + tempX * Math.sin(Math.toRadians(antAngle[b][i]))), (int)Math.round(ants[b][i].getTop() - (f1 + tempY) * Math.cos(Math.toRadians(antAngle[b][i]))));
        }
        ++i;
      }
      f2 = 0F;
    }
  }
}