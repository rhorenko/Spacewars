package com.bestcoolfungames.antsmasher;

import java.lang.reflect.Array;
import java.util.Random;

public class Level32 extends GameSurface
{
  int[][] antMovementAngle;
  int[] barataoCounter;
  int[] beeMovementAngle;

  public void startPositions()
  {
    byte b1;
    int j;
    byte b2;
    byte b3;
    this.paceY = (2 + Constants.initial_speed_increment);
    this.paceX = 3;
    this.objectPadding = 120;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[7] = 3;
    this.numberOfAntsWithType = arrayOfInt1;
    int[] arrayOfInt2 = new int[2];
    arrayOfInt2[0] = 9;
    arrayOfInt2[1] = 10;
    this.antAngle = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt2));
    int[] arrayOfInt3 = new int[2];
    arrayOfInt3[0] = 9;
    arrayOfInt3[1] = 10;
    this.antOrder = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt3));
    int[] arrayOfInt4 = new int[2];
    arrayOfInt4[0] = 9;
    arrayOfInt4[1] = 10;
    this.antDirection = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt4));
    int[] arrayOfInt5 = new int[2];
    arrayOfInt5[0] = 9;
    arrayOfInt5[1] = 10;
    this.antMovementAngle = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt5));
    this.beeAngle = new int[9];
    this.beeDirection = new int[9];
    this.beeOrder = new int[9];
    this.beeMovementAngle = new int[9];
    this.numberOfBees = 0;
    this.numberOfObjects = 3;
    this.barataoCounter = new int[3];
    boolean[] arrayOfBoolean = new boolean[this.numberOfObjects];
    int i = 0;
    if (i >= this.numberOfObjects)
      b1 = 0;
    while (true)
    {
      while (b1 >= 9)
      {
        b3 = 0;
        if (b3 < 9)
          break label346;
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
    this.antMovementAngle[b1][j] = 180;
    int[] arrayOfInt6 = this.antDirection[b1];
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
    label346: int k = 0;
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
      this.ants[b3][k].setPosition(160 - antWidth / 2, -250 + k * this.objectPadding);
      ++k;
    }
  }

  public void updatePositions()
  {
    int i;
    int j;
    if (this.passed);
    do
      return;
    while (this.paused);
    byte b = 0;
    while (true)
    {
      if (b >= 9);
      i = 0;
      if (i < this.numberOfAntsWithType[b])
        break;
      ++b;
    }
    if (this.smashed[b][i] == 0)
    {
      if (this.ants[b][i].getLeft() > this.mCanvasWidth - this.ants[b][i].getWidth())
        this.antMovementAngle[b][i] = 216;
      if (this.ants[b][i].getLeft() < 0)
        this.antMovementAngle[b][i] = 144;
      if (rand.nextInt(10) < 2)
      {
        int[] arrayOfInt2 = this.antDirection[b];
        arrayOfInt2[i] = (-1 * arrayOfInt2[i]);
      }
      if (this.antMovementAngle[b][i] > 300)
        this.antMovementAngle[b][i] = 144;
      if (this.antMovementAngle[b][i] < 60)
        this.antMovementAngle[b][i] = 216;
      int[] arrayOfInt1 = this.antMovementAngle[b];
      arrayOfInt1[i] = (arrayOfInt1[i] + 5 * this.antDirection[b][i]);
      if (this.antLife[b][i] != 2)
        break label412;
      j = 0;
    }
    while (true)
    {
      while (true)
      {
        float f = acceleration() / 100.0F;
        this.ants[b][i].setPosition((int)Math.round(this.ants[b][i].getLeft() - Math.sin(Math.toRadians(180 + this.antMovementAngle[b][i])) * (j + this.paceX)), this.ants[b][i].getTop() + (int)((j + this.paceY) * this.scale * (1F + f)));
        double d = Math.atan2(-Math.sin(Math.toRadians(180 + this.antMovementAngle[b][i])) * (j + this.paceX), (int)((j + this.paceY) * this.scale * (1F + f)));
        this.antAngle[b][i] = (180 + -(int)Math.toDegrees(d));
        ++i;
      }
      label412: j = 1;
    }
  }
}