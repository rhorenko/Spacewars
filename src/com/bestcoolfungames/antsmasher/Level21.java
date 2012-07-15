package com.bestcoolfungames.antsmasher;

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
    this.paceY = (3 + Constants.initial_speed_increment);
    this.paceX = 2;
    this.objectPadding = 150;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 3;
    arrayOfInt1[1] = 3;
    arrayOfInt1[2] = 3;
    this.numberOfAntsWithType = arrayOfInt1;
    int[] arrayOfInt2 = new int[2];
    arrayOfInt2[0] = 5;
    arrayOfInt2[1] = 10;
    this.antAngle = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt2));
    int[] arrayOfInt3 = new int[2];
    arrayOfInt3[0] = 5;
    arrayOfInt3[1] = 10;
    this.antOrder = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt3));
    int[] arrayOfInt4 = new int[2];
    arrayOfInt4[0] = 5;
    arrayOfInt4[1] = 10;
    this.antDirection = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt4));
    int[] arrayOfInt5 = new int[2];
    arrayOfInt5[0] = 5;
    arrayOfInt5[1] = 10;
    this.antTurning = ((boolean[][])Array.newInstance(Boolean.TYPE, arrayOfInt5));
    int[] arrayOfInt6 = new int[2];
    arrayOfInt6[0] = 5;
    arrayOfInt6[1] = 10;
    this.antReboundControl = ((boolean[][])Array.newInstance(Boolean.TYPE, arrayOfInt6));
    int[] arrayOfInt7 = new int[2];
    arrayOfInt7[0] = 5;
    arrayOfInt7[1] = 10;
    this.movementAngle = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt7));
    this.numberOfObjects = 9;
    boolean[] arrayOfBoolean = new boolean[this.numberOfObjects];
    int i = 0;
    if (i >= this.numberOfObjects)
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
      if (j < this.numberOfAntsWithType[b1])
        break;
      ++b1;
    }
    this.antAngle[b1][j] = 180;
    this.movementAngle[b1][j] = 180;
    int[] arrayOfInt8 = this.antDirection[b1];
    if (rand.nextInt(2) == 0)
      b2 = -1;
    while (true)
    {
      while (true)
      {
        arrayOfInt8[j] = b2;
        this.antTurning[b1][j] = 0;
        this.antReboundControl[b1][j] = 0;
        ++j;
      }
      b2 = 1;
    }
    label389: int k = 0;
    while (true)
    {
      int l;
      while (k >= this.numberOfAntsWithType[b3])
        ++b3;
      do
        l = rand.nextInt(this.numberOfObjects);
      while (arrayOfBoolean[l] != 0);
      arrayOfBoolean[l] = true;
      this.antOrder[b3][k] = l;
      this.ants[b3][k] = new SurfaceBitmap();
      this.antCounter = (1 + this.antCounter);
      this.ants[b3][k].setPosition(145 + 30 * (-1 + l % 3), -50 - l * this.objectPadding);
      ++k;
    }
  }

  public void updatePositions()
  {
    byte b;
    if (this.passed);
    do
    {
      do
        return;
      while (this.paused);
      b = 0;
    }
    while (b >= 5);
    int i = 0;
    while (true)
    {
      while (i >= this.numberOfAntsWithType[b])
        ++b;
      if (this.smashed[b][i] == 0)
      {
        if ((this.antTurning[b][i] == 0) && (rand.nextInt(10) == 0))
        {
          int[] arrayOfInt2 = this.antDirection[b];
          arrayOfInt2[i] = (-1 * arrayOfInt2[i]);
        }
        if (this.antTurning[b][i] != 0)
        {
          int[] arrayOfInt1 = this.movementAngle[b];
          arrayOfInt1[i] = (arrayOfInt1[i] + 9 * this.antDirection[b][i]);
        }
        if ((rand.nextInt(10) == 0) && (this.antTurning[b][i] == 0) && (this.ants[b][i].getTop() > this.ants[b][i].getHeight() / 2) && (this.antReboundControl[b][i] == 0))
        {
          this.antTurning[b][i] = 1;
          this.antReboundControl[b][i] = 1;
        }
        if ((this.antTurning[b][i] != 0) && (((this.movementAngle[b][i] > 540) || (this.movementAngle[b][i] < -180))))
        {
          this.antTurning[b][i] = 0;
          this.movementAngle[b][i] = 180;
        }
        float f = acceleration() / 20.0F;
        this.ants[b][i].setPosition(this.ants[b][i].getLeft(), this.ants[b][i].getTop() - (int)(Math.cos(Math.toRadians(this.movementAngle[b][i])) * (f + this.paceY)));
      }
      ++i;
    }
  }
}