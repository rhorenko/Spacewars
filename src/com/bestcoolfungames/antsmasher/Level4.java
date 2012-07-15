package com.bestcoolfungames.antsmasher;

import java.lang.reflect.Array;
import java.util.Random;

public class Level4 extends GameSurface
{
  public void startPositions()
  {
    byte b1;
    int j;
    byte b2;
    byte b3;
    this.paceY = (4 + Constants.initial_speed_increment);
    this.paceX = 5;
    this.objectPadding = 450;
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
          break label303;
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
      b2 = 1;
    while (true)
    {
      while (true)
      {
        int k;
        arrayOfInt5[j] = b2;
        do
          k = new Random().nextInt(this.numberOfObjects);
        while (arrayOfBoolean[k] != 0);
        arrayOfBoolean[k] = true;
        this.antOrder[b1][j] = k;
        ++j;
      }
      b2 = -1;
    }
    label303: int l = 0;
    while (true)
    {
      while (l >= this.numberOfAntsWithType[b3])
        ++b3;
      this.ants[b3][l] = new SurfaceBitmap();
      this.antCounter = (1 + this.antCounter);
      this.ants[b3][l].setPosition(160 + -100 + rand.nextInt(201), -50 - this.objectPadding * this.antOrder[b3][l]);
      ++l;
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
        if (this.ants[b][i].getLeft() > this.mCanvasWidth - this.ants[b][i].getWidth())
          this.antDirection[b][i] = -1;
        if (this.ants[b][i].getLeft() < 0)
          this.antDirection[b][i] = 1;
        float f = acceleration() / 60.0F;
        this.ants[b][i].setPosition(Math.round(this.ants[b][i].getLeft() + f * this.antDirection[b][i] * this.paceX), this.ants[b][i].getTop() + (int)(this.paceY * this.scale * (1F + f / 3.0F)));
        double d = Math.atan2((int)(f * this.antDirection[b][i] * this.paceX), (int)(this.paceY * this.scale * (1F + f / 3.0F)));
        this.antAngle[b][i] = (180 + -(int)Math.toDegrees(d));
      }
      ++i;
    }
  }
}