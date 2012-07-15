package com.bestcoolfungames.antsmasher;

import java.lang.reflect.Array;
import java.util.Random;

public class Level19 extends GameSurface
{
  public void startPositions()
  {
    byte b1;
    int k;
    byte b4;
    int i3;
    this.paceY = (2 + Constants.initial_speed_increment);
    this.paceX = 3;
    this.objectPadding = 280;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 3;
    arrayOfInt1[1] = 3;
    arrayOfInt1[2] = 4;
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
    this.numberOfBees = 2;
    this.numberOfObjects = 10;
    boolean[] arrayOfBoolean = new boolean[this.numberOfObjects];
    int i = 0;
    if (i >= this.numberOfObjects)
    {
      b1 = 0;
      if (b1 < 5)
        break label235;
      k = 0;
      if (k < this.numberOfBees)
        break label311;
      b4 = 0;
      if (b4 < 5)
        break label360;
      i3 = 0;
    }
    while (true)
    {
      label235: int l;
      label311: label360: int i1;
      byte b5;
      while (true)
      {
        int j;
        byte b2;
        byte b3;
        while (true)
        {
          while (true)
          {
            if (i3 < this.numberOfBees)
              break label521;
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
        this.beeAngle[k] = 180;
        int[] arrayOfInt6 = this.beeDirection;
        if (rand.nextInt(2) == 0)
          b3 = -1;
        while (true)
        {
          while (true)
          {
            arrayOfInt6[k] = b3;
            ++k;
          }
          b3 = 1;
        }
        l = 0;
        if (l < this.numberOfAntsWithType[b4])
          break;
        ++b4;
      }
      do
        i1 = rand.nextInt(this.numberOfObjects);
      while (arrayOfBoolean[i1] != 0);
      arrayOfBoolean[i1] = true;
      this.antOrder[b4][l] = i1;
      this.ants[b4][l] = new SurfaceBitmap();
      this.antCounter = (1 + this.antCounter);
      SurfaceBitmap localSurfaceBitmap = this.ants[b4][l];
      int i2 = 100 + 85 * i1 % 2;
      if (i1 / 2 % 2 == 1)
        b5 = -1;
      while (true)
      {
        while (true)
        {
          localSurfaceBitmap.setPosition(i2 + b5 * 30, -50 - this.objectPadding * i1 / 2);
          ++l;
        }
        b5 = 1;
      }
      label521: this.bees[i3] = new SurfaceBitmap();
      this.bees[i3].setPosition(160 - antWidth / 2, -120 - 3 * i3 * this.objectPadding);
      ++i3;
    }
  }

  public void updatePositions()
  {
    float f;
    if (this.passed);
    do
    {
      return;
      f = acceleration() / 36.0F;
    }
    while (this.paused);
    byte b = 0;
    if (b >= 5)
    {
      int j = 0;
      while (true)
      {
        if (j >= this.numberOfBees);
        this.beeAngle[j] = (int)Math.round(this.beeAngle[j] + 2.6000000000000001D * this.beeDirection[j]);
        this.bees[j].setPosition(this.bees[j].getLeft() - (int)(Math.sin(Math.toRadians(180 + this.beeAngle[j])) * this.paceX), 2 + this.bees[j].getTop() - (int)(Math.cos(Math.toRadians(this.beeAngle[j])) * this.paceY));
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