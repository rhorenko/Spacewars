package com.bestcoolfungames.antsmasher;

import java.lang.reflect.Array;
import java.util.Random;

public class Level22 extends GameSurface
{
  public void startPositions()
  {
    byte b1;
    byte b2;
    int k;
    int l;
    byte b3;
    SurfaceBitmap localSurfaceBitmap;
    int i1;
    byte b4;
    this.paceY = (4 + Constants.initial_speed_increment);
    this.paceX = 4;
    this.objectPadding = 170;
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
    this.numberOfBees = 0;
    this.numberOfObjects = 6;
    boolean[] arrayOfBoolean = new boolean[this.numberOfObjects];
    int i = 0;
    if (i >= this.numberOfObjects)
    {
      b1 = 0;
      if (b1 < 5)
        break label211;
      b2 = 0;
    }
    while (true)
    {
      while (true)
      {
        if (b2 < 5)
          break label251;
        return;
        arrayOfBoolean[i] = false;
        ++i;
      }
      label211: int j = 0;
      while (true)
      {
        while (j >= this.numberOfAntsWithType[b1])
          ++b1;
        this.antAngle[b1][j] = 180;
        ++j;
      }
      label251: k = 0;
      if (k < this.numberOfAntsWithType[b2])
        break;
      ++b2;
    }
    do
      l = rand.nextInt(this.numberOfObjects);
    while (arrayOfBoolean[l] != 0);
    arrayOfBoolean[l] = true;
    this.antOrder[b2][k] = l;
    int[] arrayOfInt5 = this.antDirection[b2];
    if (l % 2 == 1)
    {
      b3 = 1;
      arrayOfInt5[k] = b3;
      this.ants[b2][k] = new SurfaceBitmap();
      this.antCounter = (1 + this.antCounter);
      localSurfaceBitmap = this.ants[b2][k];
      i1 = 160 - antWidth / 2;
      if (l % 2 != 1)
        break label437;
      b4 = -1;
    }
    while (true)
    {
      while (true)
      {
        while (true)
        {
          localSurfaceBitmap.setPosition(i1 + b4 * 20, -50 - this.objectPadding * l / 2);
          ++k;
        }
        b3 = -1;
      }
      label437: b4 = 1;
    }
  }

  public void updatePositions()
  {
    float f;
    byte b;
    if (this.passed);
    do
    {
      do
        return;
      while (this.paused);
      f = acceleration() / 60.0F;
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
          this.antAngle[b][i] = 225;
        if (this.ants[b][i].getLeft() < 0)
          this.antAngle[b][i] = 135;
        this.antAngle[b][i] = (int)Math.round(this.antAngle[b][i] + 9.5999999999999996D * this.antDirection[b][i]);
        if ((this.antAngle[b][i] > 270) || (this.antAngle[b][i] < 90))
        {
          int[] arrayOfInt = this.antDirection[b];
          arrayOfInt[i] = (-1 * arrayOfInt[i]);
        }
        this.ants[b][i].setPosition((int)Math.round(this.ants[b][i].getLeft() + this.paceX * Math.sin(Math.toRadians(this.antAngle[b][i]))), (int)Math.round(this.ants[b][i].getTop() - (f + this.paceY) * Math.cos(Math.toRadians(this.antAngle[b][i]))));
      }
      ++i;
    }
  }
}