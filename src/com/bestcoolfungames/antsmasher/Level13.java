package com.bestcoolfungames.antsmasher;

import java.lang.reflect.Array;
import java.util.Random;

public class Level13 extends GameSurface
{
  public void startPositions()
  {
    int j;
    int i1;
    byte b;
    this.paceY = (3 + Constants.initial_speed_increment);
    this.paceX = 2;
    this.objectPadding = 150;
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
    this.numberOfBees = 5;
    this.numberOfObjects = 5;
    this.beeAngle = new int[5];
    boolean[] arrayOfBoolean = new boolean[this.numberOfObjects];
    int[] arrayOfInt5 = new int[this.numberOfObjects];
    int i = 0;
    while (true)
    {
      if (i >= this.numberOfObjects)
      {
        j = 0;
        if (j < 5)
          break;
        i1 = 0;
        if (i1 < this.numberOfObjects)
          break label310;
        return;
      }
      arrayOfBoolean[i] = false;
      ++i;
    }
    int k = 0;
    while (true)
    {
      int l;
      while (k >= this.numberOfAntsWithType[j])
      {
        this.beeAngle[j] = 180;
        ++j;
      }
      this.antAngle[j][k] = 180;
      do
        l = rand.nextInt(this.numberOfObjects);
      while (arrayOfBoolean[l] != 0);
      arrayOfBoolean[l] = true;
      this.antOrder[j][k] = l;
      arrayOfInt5[l] = (k + j * this.numberOfObjects);
      ++k;
    }
    label310: this.ants[(arrayOfInt5[i1] / this.numberOfObjects)][(arrayOfInt5[i1] % this.numberOfObjects)] = new SurfaceBitmap();
    this.antCounter = (1 + this.antCounter);
    if (rand.nextInt(2) == 0)
      b = -1;
    while (true)
    {
      while (true)
      {
        this.ants[(arrayOfInt5[i1] / this.numberOfObjects)][(arrayOfInt5[i1] % this.numberOfObjects)].setPosition(130 + b * 40, -(int)(50.0F * this.scale) - i1 * this.objectPadding);
        this.bees[i1] = new SurfaceBitmap();
        this.bees[i1].setPosition(125 - b * 40, -(int)(60.0F * this.scale) - i1 * this.objectPadding);
        ++i1;
      }
      b = 1;
    }
  }

  public void updatePositions()
  {
    float f;
    byte b1;
    byte b2;
    if (!(this.paused))
    {
      f = acceleration() / 60.0F;
      b1 = 0;
      if (b1 < 5)
        break label32;
      b2 = 0;
    }
    while (true)
    {
      if (b2 >= 5)
      {
        return;
        label32: int i = 0;
        while (true)
        {
          while (i >= this.numberOfAntsWithType[b1])
            ++b1;
          if (this.smashed[b1][i] == 0)
            this.ants[b1][i].setPosition(this.ants[b1][i].getLeft(), this.ants[b1][i].getTop() + Math.round(this.paceY * this.scale * (1F + f)));
          ++i;
        }
      }
      this.bees[b2].setPosition(this.bees[b2].getLeft(), this.bees[b2].getTop() + Math.round(this.paceY * this.scale * (1F + f)));
      ++b2;
    }
  }
}