package com.bestcoolfungames.antsmasher;

import java.lang.reflect.Array;
import java.util.Random;

public class Level16 extends GameSurface
{
  public void startPositions()
  {
    byte b1;
    int j;
    byte b2;
    byte b3;
    this.paceY = (3 + Constants.initial_speed_increment);
    this.paceX = 4;
    this.objectPadding = 100;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 2;
    arrayOfInt1[1] = 2;
    arrayOfInt1[2] = 2;
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
    this.numberOfObjects = 6;
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
          break label281;
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
    int[] arrayOfInt5 = this.antDirection[b1];
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
    label281: int k = 0;
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
      this.ants[b3][k].setPosition(250 * l % 2, -70 - 480 * l / 2);
      ++k;
    }
  }

  public void updatePositions()
  {
    int i;
    int j;
    int i1;
    int i2;
    if (this.passed);
    do
      return;
    while (this.paused);
    float f = acceleration() / 40.0F;
    byte b1 = 0;
    while (true)
    {
      if (b1 >= 3);
      i = 0;
      if (i < this.numberOfAntsWithType[b1])
        break;
      ++b1;
    }
    if (this.smashed[b1][i] == 0)
    {
      j = Math.round(f + this.paceY);
      int k = Math.round(j * 9 / 16.0F);
      if (!(this.ants[b1][i].getTop() < -0.5D * this.ants[b1][i].getHeight()))
        break label225;
      int l = 1;
      i1 = k * l;
      if (this.antOrder[b1][i] % 2 != 0)
        break label231;
      i2 = 1;
    }
    while (true)
    {
      while (true)
      {
        while (true)
        {
          int i3 = i1 * i2;
          double d = Math.atan2(i3, j);
          this.antAngle[b1][i] = (180 + -(int)Math.toDegrees(d));
          this.ants[b1][i].setPosition(i3 + this.ants[b1][i].getLeft(), j + this.ants[b1][i].getTop());
          ++i;
        }
        label225: byte b2 = 0;
      }
      label231: byte b3 = -1;
    }
  }
}