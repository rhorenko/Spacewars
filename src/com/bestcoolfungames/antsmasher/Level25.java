package com.bestcoolfungames.antsmasher;

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
    this.paceY = (2 + Constants.initial_speed_increment);
    this.paceX = 90;
    this.objectPadding = 250;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 2;
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
    this.beeAngle = new int[5];
    this.beeDirection = new int[5];
    this.beeOrder = new int[5];
    this.beeMovementAngle = new int[5];
    this.numberOfBees = 4;
    this.numberOfObjects = 8;
    boolean[] arrayOfBoolean = new boolean[this.numberOfObjects];
    int i = 0;
    if (i >= this.numberOfObjects)
    {
      b1 = 0;
      if (b1 < 5)
        break label243;
      k = 0;
      if (k < this.numberOfBees)
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
        if (i2 < this.numberOfBees)
          break label480;
        return;
        arrayOfBoolean[i] = false;
        ++i;
      }
      int j = 0;
      while (true)
      {
        while (j >= this.numberOfAntsWithType[b1])
          ++b1;
        this.antAngle[b1][j] = 180;
        ++j;
      }
      label283: this.beeAngle[k] = 180;
      this.beeMovementAngle[k] = 180;
      int[] arrayOfInt5 = this.beeDirection;
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
        while (l >= this.numberOfAntsWithType[b3])
          ++b3;
        do
          i1 = rand.nextInt(this.numberOfObjects);
        while (arrayOfBoolean[i1] != 0);
        arrayOfBoolean[i1] = true;
        this.antOrder[b3][l] = i1;
        this.ants[b3][l] = new SurfaceBitmap();
        this.antCounter = (1 + this.antCounter);
        this.ants[b3][l].setPosition(160 - antWidth / 2 + (-1 + i1 % 3) * antWidth, -50 - this.objectPadding * i1 / 2);
        ++l;
      }
      label480: this.bees[i2] = new SurfaceBitmap();
      this.bees[i2].setPosition(65, -45 - i2 * this.objectPadding);
      ++i2;
    }
  }

  public void updatePositions()
  {
    float f;
    if (this.passed);
    do
    {
      return;
      f = acceleration() / 40.0F;
    }
    while (this.paused);
    byte b = 0;
    if (b >= 5)
    {
      int j = 0;
      while (true)
      {
        if (j >= this.numberOfBees);
        int[] arrayOfInt = this.beeMovementAngle;
        arrayOfInt[j] = (arrayOfInt[j] + 5 * this.beeDirection[j]);
        this.bees[j].setPosition((int)Math.round(this.mCanvasWidth / 2 - this.bees[j].getWidth() / 2 - Math.sin(Math.toRadians(this.beeMovementAngle[j])) * this.paceX), this.bees[j].getTop() + (int)(this.paceY * this.scale * (1F + f)));
        ++j;
      }
    }
    int i = 0;
    while (true)
    {
      while (i >= this.numberOfAntsWithType[b])
        ++b;
      if (this.smashed[b][i] == 0)
        this.ants[b][i].setPosition(this.ants[b][i].getLeft(), this.ants[b][i].getTop() + (int)(this.paceY * this.scale * (1F + f)));
      ++i;
    }
  }
}