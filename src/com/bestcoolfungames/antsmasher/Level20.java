package com.bestcoolfungames.antsmasher;

import java.lang.reflect.Array;
import java.util.Random;

public class Level20 extends GameSurface
{
  public void startPositions()
  {
    byte b1;
    int k;
    byte b4;
    int i2;
    this.paceY = (2 + Constants.initial_speed_increment);
    this.paceX = 4;
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
    this.numberOfBees = 2;
    this.numberOfObjects = 8;
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
      i2 = 0;
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
            if (i2 < this.numberOfBees)
              break label519;
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
      if (i1 % 2 == 1)
        b5 = -1;
      while (true)
      {
        while (true)
        {
          localSurfaceBitmap.setPosition(120 + b5 * 80, -50 - this.objectPadding * i1 / 2 + -30 + rand.nextInt(61));
          ++l;
        }
        b5 = 1;
      }
      label519: this.bees[i2] = new SurfaceBitmap();
      this.bees[i2].setPosition(160 - antWidth / 2, -120 - 3 * i2 * this.objectPadding);
      ++i2;
    }
  }

  public void updatePositions()
  {
    float f;
    int i;
    byte b2;
    byte b3;
    if (this.passed);
    do
    {
      return;
      f = acceleration() / 60.0F;
    }
    while (this.paused);
    byte b1 = 0;
    while (true)
    {
      if (b1 >= 5)
      {
        int j = 0;
        while (true)
        {
          if (j >= this.numberOfBees);
          if (this.bees[j].getLeft() > this.mCanvasWidth - this.bees[j].getWidth())
            this.beeDirection[j] = -1;
          if (this.bees[j].getLeft() < 0)
            this.beeDirection[j] = 1;
          this.bees[j].setPosition(Math.round(this.bees[j].getLeft() + this.beeDirection[j] * this.paceX * (1F + f)), this.bees[j].getTop() + (int)(this.paceY * this.scale * (1F + f / 3.0F)));
          double d2 = Math.atan2(this.beeDirection[j] * this.paceX * (1F + f), this.paceY * this.scale * (1F + f / 3.0F));
          this.beeAngle[j] = (180 + -(int)Math.toDegrees(d2));
          this.bees[j].setPosition(this.bees[j].getLeft() - (int)(Math.sin(Math.toRadians(180 + this.beeAngle[j])) * this.paceX), 2 + this.bees[j].getTop() - (int)(Math.cos(Math.toRadians(this.beeAngle[j])) * this.paceY));
          ++j;
        }
      }
      i = 0;
      if (i < this.numberOfAntsWithType[b1])
        break;
      ++b1;
    }
    if (this.smashed[b1][i] == 0)
    {
      if (this.antOrder[b1][i] % 2 != 1)
        break label605;
      b2 = -1;
      if (120 + b2 * 80 - this.ants[b1][i].getLeft() > 10 + antWidth / 2)
        this.antDirection[b1][i] = 1;
      if (this.antOrder[b1][i] % 2 != 1)
        break label611;
      b3 = -1;
    }
    while (true)
    {
      while (true)
      {
        while (true)
        {
          if (120 + b3 * 80 - this.ants[b1][i].getLeft() < -(10 + antWidth / 2))
            this.antDirection[b1][i] = -1;
          this.ants[b1][i].setPosition(Math.round(this.ants[b1][i].getLeft() + this.antDirection[b1][i] * this.paceX * (1F + f)), this.ants[b1][i].getTop() + (int)(this.paceY * this.scale * (1F + f / 3.0F)));
          double d1 = Math.atan2(this.antDirection[b1][i] * this.paceX * (1F + f), this.paceY * this.scale * (1F + f / 3.0F));
          this.antAngle[b1][i] = (180 + -(int)Math.toDegrees(d1));
          ++i;
        }
        label605: b2 = 1;
      }
      label611: b3 = 1;
    }
  }
}