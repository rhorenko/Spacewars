package com.space.levels;

import java.lang.reflect.Array;
import java.util.Random;

public class Level20 extends GameSurface
{
  public void startPositions()
  {
    byte b1;
    int k;
    byte b4;
    int i2;
    paceY = (2 + Constants.initial_speed_increment);
    paceX = 4;
    objectPadding = 250;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 2;
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
    beeAngle = new int[5];
    beeDirection = new int[5];
    beeOrder = new int[5];
    numberOfBees = 2;
    numberOfObjects = 8;
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
      i2 = 0;
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
            if (i2 < numberOfBees)
              break label519;
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
      if (i1 % 2 == 1)
        b5 = -1;
      while (true)
      {
        while (true)
        {
          localSurfaceBitmap.setPosition(120 + b5 * 80, -50 - objectPadding * i1 / 2 + -30 + rand.nextInt(61));
          ++l;
        }
        b5 = 1;
      }
      label519: bees[i2] = new SurfaceBitmap();
      bees[i2].setPosition(160 - antWidth / 2, -120 - 3 * i2 * objectPadding);
      ++i2;
    }
  }

  public void updatePositions()
  {
    float f;
    int i;
    byte b2;
    byte b3;
    if (passed);
    do
    {
      return;
      f = acceleration() / 60.0F;
    }
    while (paused);
    byte b1 = 0;
    while (true)
    {
      if (b1 >= 5)
      {
        int j = 0;
        while (true)
        {
          if (j >= numberOfBees);
          if (bees[j].getLeft() > mCanvasWidth - bees[j].getWidth())
            beeDirection[j] = -1;
          if (bees[j].getLeft() < 0)
            beeDirection[j] = 1;
          bees[j].setPosition(Math.round(bees[j].getLeft() + beeDirection[j] * paceX * (1F + f)), bees[j].getTop() + (int)(paceY * scale * (1F + f / 3.0F)));
          double d2 = Math.atan2(beeDirection[j] * paceX * (1F + f), paceY * scale * (1F + f / 3.0F));
          beeAngle[j] = (180 + -(int)Math.toDegrees(d2));
          bees[j].setPosition(bees[j].getLeft() - (int)(Math.sin(Math.toRadians(180 + beeAngle[j])) * paceX), 2 + bees[j].getTop() - (int)(Math.cos(Math.toRadians(beeAngle[j])) * paceY));
          ++j;
        }
      }
      i = 0;
      if (i < numberOfAntsWithType[b1])
        break;
      ++b1;
    }
    if (smashed[b1][i] == 0)
    {
      if (antOrder[b1][i] % 2 != 1)
        break label605;
      b2 = -1;
      if (120 + b2 * 80 - ants[b1][i].getLeft() > 10 + antWidth / 2)
        antDirection[b1][i] = 1;
      if (antOrder[b1][i] % 2 != 1)
        break label611;
      b3 = -1;
    }
    while (true)
    {
      while (true)
      {
        while (true)
        {
          if (120 + b3 * 80 - ants[b1][i].getLeft() < -(10 + antWidth / 2))
            antDirection[b1][i] = -1;
          ants[b1][i].setPosition(Math.round(ants[b1][i].getLeft() + antDirection[b1][i] * paceX * (1F + f)), ants[b1][i].getTop() + (int)(paceY * scale * (1F + f / 3.0F)));
          double d1 = Math.atan2(antDirection[b1][i] * paceX * (1F + f), paceY * scale * (1F + f / 3.0F));
          antAngle[b1][i] = (180 + -(int)Math.toDegrees(d1));
          ++i;
        }
        label605: b2 = 1;
      }
      label611: b3 = 1;
    }
  }
}