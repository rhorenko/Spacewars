package com.space.levels;

import java.lang.reflect.Array;
import java.util.Random;

public class Level21 extends GameSurface
{
  boolean[][] antReboundControl;
  boolean[][] antTurning;
  int[][] movementAngle;

  public void startPositions()
  {
    byte b1;
    int j;
    byte b2;
    byte b3;
    tempY = (3 + Constants.initial_speed_increment);
    tempX = 2;
    objectPadding = 150;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 3;
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
    int[] arrayOfInt5 = new int[2];
    arrayOfInt5[0] = 5;
    arrayOfInt5[1] = 10;
    antTurning = ((boolean[][])Array.newInstance(Boolean.TYPE, arrayOfInt5));
    int[] arrayOfInt6 = new int[2];
    arrayOfInt6[0] = 5;
    arrayOfInt6[1] = 10;
    antReboundControl = ((boolean[][])Array.newInstance(Boolean.TYPE, arrayOfInt6));
    int[] arrayOfInt7 = new int[2];
    arrayOfInt7[0] = 5;
    arrayOfInt7[1] = 10;
    movementAngle = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt7));
    numberOfObjects = 9;
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
          break label389;
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
    movementAngle[b1][j] = 180;
    int[] arrayOfInt8 = antDirection[b1];
    if (rand.nextInt(2) == 0)
      b2 = -1;
    while (true)
    {
      while (true)
      {
        arrayOfInt8[j] = b2;
        antTurning[b1][j] = 0;
        antReboundControl[b1][j] = 0;
        ++j;
      }
      b2 = 1;
    }
    label389: int k = 0;
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
      ants[b3][k].setPosition(145 + 30 * (-1 + l % 3), -50 - l * objectPadding);
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
    while (b >= 5);
    int i = 0;
    while (true)
    {
      while (i >= numberOfAntsWithType[b])
        ++b;
      if (smashed[b][i] == 0)
      {
        if ((antTurning[b][i] == 0) && (rand.nextInt(10) == 0))
        {
          int[] arrayOfInt2 = antDirection[b];
          arrayOfInt2[i] = (-1 * arrayOfInt2[i]);
        }
        if (antTurning[b][i] != 0)
        {
          int[] arrayOfInt1 = movementAngle[b];
          arrayOfInt1[i] = (arrayOfInt1[i] + 9 * antDirection[b][i]);
        }
        if ((rand.nextInt(10) == 0) && (antTurning[b][i] == 0) && (ants[b][i].getTop() > ants[b][i].getHeight() / 2) && (antReboundControl[b][i] == 0))
        {
          antTurning[b][i] = 1;
          antReboundControl[b][i] = 1;
        }
        if ((antTurning[b][i] != 0) && (((movementAngle[b][i] > 540) || (movementAngle[b][i] < -180))))
        {
          antTurning[b][i] = 0;
          movementAngle[b][i] = 180;
        }
        float f = acceleration() / 20.0F;
        ants[b][i].setPosition(ants[b][i].getLeft(), ants[b][i].getTop() - (int)(Math.cos(Math.toRadians(movementAngle[b][i])) * (f + tempY)));
      }
      ++i;
    }
  }
}