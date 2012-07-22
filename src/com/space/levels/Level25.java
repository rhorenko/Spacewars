package com.space.levels;

import java.lang.reflect.Array;
import java.util.Random;

public class Level25 extends GameSurface
{
  int[] beeMovementAngle;

  public void startPositions()
  {
    byte b1;
    int k;
    byte b3;
    int i2;
    tempY = (2 + Constants.initial_speed_increment);
    tempX = 90;
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
    beeMovementAngle = new int[5];
    numberOfBees = 4;
    numberOfObjects = 8;
    boolean[] arrayOfBoolean = new boolean[numberOfObjects];
    int i = 0;
    if (i >= numberOfObjects)
    {
      b1 = 0;
      if (b1 < 5)
        break label243;
      k = 0;
      if (k < numberOfBees)
        break label283;
      b3 = 0;
      if (b3 < 5)
        break label342;
      i2 = 0;
    }
    while (true)
    {
      label243: byte b2;
      while (true)
      {
        if (i2 < numberOfBees)
          break label480;
        return;
        arrayOfBoolean[i] = false;
        ++i;
      }
      int j = 0;
      while (true)
      {
        while (j >= numberOfAntsWithType[b1])
          ++b1;
        antAngle[b1][j] = 180;
        ++j;
      }
      label283: beeAngle[k] = 180;
      beeMovementAngle[k] = 180;
      int[] arrayOfInt5 = beeDirection;
      if (rand.nextInt(2) == 0)
        b2 = -1;
      while (true)
      {
        while (true)
        {
          arrayOfInt5[k] = b2;
          ++k;
        }
        b2 = 1;
      }
      label342: int l = 0;
      while (true)
      {
        int i1;
        while (l >= numberOfAntsWithType[b3])
          ++b3;
        do
          i1 = rand.nextInt(numberOfObjects);
        while (arrayOfBoolean[i1] != 0);
        arrayOfBoolean[i1] = true;
        antOrder[b3][l] = i1;
        ants[b3][l] = new SurfaceBitmap();
        antCounter = (1 + antCounter);
        ants[b3][l].setPosition(160 - antWidth / 2 + (-1 + i1 % 3) * antWidth, -50 - objectPadding * i1 / 2);
        ++l;
      }
      label480: bees[i2] = new SurfaceBitmap();
      bees[i2].setPosition(65, -45 - i2 * objectPadding);
      ++i2;
    }
  }

  public void updatePositions()
  {
    float f;
    if (passed);
    do
    {
      return;
      f = acceleration() / 40.0F;
    }
    while (paused);
    byte b = 0;
    if (b >= 5)
    {
      int j = 0;
      while (true)
      {
        if (j >= numberOfBees);
        int[] arrayOfInt = beeMovementAngle;
        arrayOfInt[j] = (arrayOfInt[j] + 5 * beeDirection[j]);
        bees[j].setPosition((int)Math.round(mCanvasWidth / 2 - bees[j].getWidth() / 2 - Math.sin(Math.toRadians(beeMovementAngle[j])) * tempX), bees[j].getTop() + (int)(tempY * scale * (1F + f)));
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