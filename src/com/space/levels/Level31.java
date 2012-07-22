package com.space.levels;

import java.lang.reflect.Array;
import java.util.Random;

public class Level31 extends GameSurface
{
  int[][] antMovementAngle;
  int[] beeMovementAngle;

  public void startPositions()
  {
    byte b1;
    int j;
    byte b2;
    byte b3;
    paceY = (2 + Constants.initial_speed_increment);
    paceX = 5;
    objectPadding = 120;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[6] = 1;
    numberOfAntsWithType = arrayOfInt1;
    int[] arrayOfInt2 = new int[2];
    arrayOfInt2[0] = 7;
    arrayOfInt2[1] = 10;
    antAngle = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt2));
    int[] arrayOfInt3 = new int[2];
    arrayOfInt3[0] = 7;
    arrayOfInt3[1] = 10;
    antOrder = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt3));
    int[] arrayOfInt4 = new int[2];
    arrayOfInt4[0] = 7;
    arrayOfInt4[1] = 10;
    antDirection = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt4));
    int[] arrayOfInt5 = new int[2];
    arrayOfInt5[0] = 7;
    arrayOfInt5[1] = 10;
    antMovementAngle = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt5));
    beeAngle = new int[7];
    beeDirection = new int[7];
    beeOrder = new int[7];
    beeMovementAngle = new int[7];
    numberOfBees = 0;
    numberOfObjects = 7;
    boolean[] arrayOfBoolean = new boolean[numberOfObjects];
    int i = 0;
    if (i >= numberOfObjects)
      b1 = 0;
    while (true)
    {
      while (b1 >= 7)
      {
        b3 = 0;
        if (b3 < 7)
          break label340;
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
    label340: int k = 0;
    while (true)
    {
      int l;
      while (k >= numberOfAntsWithType[b3])
        ++b3;
      do
        l = rand.nextInt(numberOfObjects);
      while (arrayOfBoolean[l] != 0);
      arrayOfBoolean[l] = true;
      antOrder[b3][k] = l;
      ants[b3][k] = new SurfaceBitmap();
      antCounter = (1 + antCounter);
      ants[b3][k].setPosition(160 - antWidth / 2, -250);
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
    while (b >= 7);
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
        float f = acceleration() / 100.0F;
        ants[b][i].setPosition((int)Math.round(ants[b][i].getLeft() - Math.sin(Math.toRadians(180 + antMovementAngle[b][i])) * paceX), ants[b][i].getTop() + (int)(paceY * scale * (1F + f)));
        double d = Math.atan2(-Math.sin(Math.toRadians(180 + antMovementAngle[b][i])) * paceX, (int)(paceY * scale * (1F + f)));
        antAngle[b][i] = (180 + -(int)Math.toDegrees(d));
      }
      ++i;
    }
  }
}