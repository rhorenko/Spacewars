package com.space.levels;

import java.lang.reflect.Array;
import java.util.Random;

public class Level26 extends GameSurface
{
  int[][] antMovementAngle;
  int[] beeMovementAngle;

  public void startPositions()
  {
    byte b1;
    int k;
    byte b4;
    int i2;
    tempY = (3 + Constants.initial_speed_increment);
    tempX = 4;
    objectPadding = 120;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 2;
    arrayOfInt1[1] = 2;
    arrayOfInt1[2] = 2;
    arrayOfInt1[3] = 2;
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
    antMovementAngle = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt5));
    beeAngle = new int[5];
    beeDirection = new int[5];
    beeOrder = new int[5];
    beeMovementAngle = new int[5];
    numberOfBees = 4;
    numberOfObjects = 12;
    boolean[] arrayOfBoolean = new boolean[numberOfObjects];
    int i = 0;
    if (i >= numberOfObjects)
    {
      b1 = 0;
      if (b1 < 5)
        break label276;
      k = 0;
      if (k < numberOfBees)
        break label365;
      b4 = 0;
      if (b4 < 5)
        break label424;
      i2 = 0;
    }
    while (true)
    {
      label276: int j;
      byte b2;
      byte b3;
      int i3;
      while (true)
      {
        while (true)
        {
          if (i2 < numberOfBees)
            break label548;
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
      antMovementAngle[b1][j] = 180;
      int[] arrayOfInt6 = antDirection[b1];
      if (rand.nextInt(2) == 0)
        b2 = -1;
      while (true)
      {
        while (true)
        {
          arrayOfInt6[j] = b2;
          ++j;
        }
        b2 = 1;
      }
      label365: beeAngle[k] = 180;
      beeMovementAngle[k] = 180;
      int[] arrayOfInt7 = beeDirection;
      if (rand.nextInt(2) == 0)
        b3 = -1;
      while (true)
      {
        while (true)
        {
          arrayOfInt7[k] = b3;
          ++k;
        }
        b3 = 1;
      }
      label424: int l = 0;
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
        ants[b4][l].setPosition(160 - antWidth / 2, -50 - i1 * objectPadding);
        ++l;
      }
      do
        label548: i3 = rand.nextInt(numberOfObjects);
      while (arrayOfBoolean[i3] != 0);
      arrayOfBoolean[i3] = true;
      beeOrder[i2] = i3;
      bees[i2] = new SurfaceBitmap();
      bees[i2].setPosition(160 - antWidth / 2, -50 - i3 * objectPadding);
      ++i2;
    }
  }

  public void updatePositions()
  {
    if (passed);
    do
      return;
    while (paused);
    float f = acceleration() / 48.0F;
    byte b = 0;
    if (b >= 5)
    {
      int j = 0;
      while (true)
      {
        if (j >= numberOfBees);
        if (bees[j].getLeft() > mCanvasWidth - bees[j].getWidth())
          beeMovementAngle[j] = 216;
        if (bees[j].getLeft() < 0)
          beeMovementAngle[j] = 144;
        if (rand.nextInt(10) < 2)
        {
          int[] arrayOfInt4 = beeDirection;
          arrayOfInt4[j] = (-1 * arrayOfInt4[j]);
        }
        if (beeMovementAngle[j] > 300)
          beeMovementAngle[j] = 144;
        if (beeMovementAngle[j] < 60)
          beeMovementAngle[j] = 216;
        int[] arrayOfInt3 = beeMovementAngle;
        arrayOfInt3[j] = (arrayOfInt3[j] + 5 * beeDirection[j]);
        bees[j].setPosition((int)Math.round(bees[j].getLeft() - Math.sin(Math.toRadians(180 + beeMovementAngle[j])) * tempX), bees[j].getTop() + (int)(tempY * scale * (1F + f)));
        double d2 = Math.atan2(-Math.sin(Math.toRadians(180 + beeMovementAngle[j])) * tempX, (int)(tempY * scale * (1F + f)));
        beeAngle[j] = (180 + -(int)Math.toDegrees(d2));
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
          antMovementAngle[b][i] = 216;
        if (ants[b][i].getLeft() < 0)
          antMovementAngle[b][i] = 144;
        if (rand.nextInt(10) < 2)
        {
          int[] arrayOfInt2 = antDirection[b];
          arrayOfInt2[i] = (-1 * arrayOfInt2[i]);
        }
        if (antMovementAngle[b][i] > 300)
          antMovementAngle[b][i] = 144;
        if (antMovementAngle[b][i] < 60)
          antMovementAngle[b][i] = 216;
        int[] arrayOfInt1 = antMovementAngle[b];
        arrayOfInt1[i] = (arrayOfInt1[i] + 5 * antDirection[b][i]);
        ants[b][i].setPosition((int)Math.round(ants[b][i].getLeft() - Math.sin(Math.toRadians(180 + antMovementAngle[b][i])) * tempX), ants[b][i].getTop() + (int)(tempY * scale * (1F + f)));
        double d1 = Math.atan2(-Math.sin(Math.toRadians(180 + antMovementAngle[b][i])) * tempX, (int)(tempY * scale * (1F + f)));
        antAngle[b][i] = (180 + -(int)Math.toDegrees(d1));
      }
      ++i;
    }
  }
}