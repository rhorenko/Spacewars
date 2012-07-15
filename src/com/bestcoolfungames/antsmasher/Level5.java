package com.bestcoolfungames.antsmasher;

import java.lang.reflect.Array;
import java.util.Random;

public class Level5 extends GameSurface
{
  public void startPositions()
  {
    byte b1;
    int j;
    byte b2;
    byte b3;
    this.paceY = (2 + Constants.initial_speed_increment);
    this.paceX = 2;
    this.objectPadding = 190;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 1;
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
    this.numberOfObjects = 5;
    SurfaceBitmap[] arrayOfSurfaceBitmap = new SurfaceBitmap[this.numberOfObjects];
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
          break label329;
        arrayOfSurfaceBitmap[0].setPosition(140, -105);
        arrayOfSurfaceBitmap[1].setPosition(30, -45);
        arrayOfSurfaceBitmap[2].setPosition(250, -45);
        arrayOfSurfaceBitmap[3].setPosition(30, -165);
        arrayOfSurfaceBitmap[4].setPosition(250, -165);
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
    label329: int k = 0;
    while (true)
    {
      int l;
      while (k >= this.numberOfAntsWithType[b3])
        ++b3;
      do
        l = rand.nextInt(this.numberOfObjects);
      while (arrayOfBoolean[l] != 0);
      arrayOfBoolean[l] = true;
      this.ants[b3][k] = new SurfaceBitmap();
      this.antCounter = (1 + this.antCounter);
      this.antOrder[b3][k] = l;
      arrayOfSurfaceBitmap[l] = this.ants[b3][k];
      ++k;
    }
  }

  public void updatePositions()
  {
    byte b;
    if (!(this.paused))
    {
      b = 0;
      if (b < 5)
        break label15;
    }
    return;
    label15: int i = 0;
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
        float f = acceleration() / 28.0F;
        this.ants[b][i].setPosition(this.ants[b][i].getLeft(), this.ants[b][i].getTop() + (int)(this.paceY * this.scale * (1F + f)));
      }
      ++i;
    }
  }
}