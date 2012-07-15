package com.bestcoolfungames.antsmasher;

import java.lang.reflect.Array;
import java.util.Random;

public class Level11 extends GameSurface
{
  public void startPositions()
  {
    int j;
    byte b3;
    int l;
    this.paceY = (3 + Constants.initial_speed_increment);
    this.paceX = 4;
    this.objectPadding = 100;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[2] = 1;
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
    this.numberOfBees = 1;
    byte b1 = 0;
    byte b2 = 0;
    if (b2 >= 5)
    {
      j = 0;
      if (j < this.numberOfBees)
        break label267;
      b3 = 0;
      if (b3 < 5)
        break label292;
      l = 0;
    }
    while (true)
    {
      while (true)
      {
        int i;
        while (true)
        {
          if (l < this.numberOfBees)
            break label370;
          return;
          i = 0;
          if (i < this.numberOfAntsWithType[b2])
            break;
          ++b2;
        }
        this.antAngle[b2][i] = 180;
        int[] arrayOfInt5 = this.antDirection[b2];
        if (rand.nextInt(2) == 0)
          b1 = -1;
        while (true)
        {
          while (true)
          {
            arrayOfInt5[i] = b1;
            ++i;
          }
          b1 = 1;
        }
        label267: this.beeAngle[j] = 180;
        this.beeDirection[j] = b1;
        ++j;
      }
      label292: int k = 0;
      while (true)
      {
        while (k >= this.numberOfAntsWithType[b3])
          ++b3;
        this.antCounter = (1 + this.antCounter);
        this.ants[b3][k] = new SurfaceBitmap();
        this.ants[b3][k].setPosition(160 - antWidth / 2, -50);
        ++k;
      }
      label370: this.bees[l] = new SurfaceBitmap();
      this.bees[l].setPosition(160 - antWidth / 2, -55);
      ++l;
    }
  }

  public void updatePositions()
  {
    float f1;
    byte b;
    int j;
    float f2;
    if (this.passed);
    do
    {
      do
      {
        return;
        f1 = Math.min(10.0F, acceleration() / 50.0F);
      }
      while (this.paused);
      b = 0;
      if (b < 5)
        break label309;
      j = 0;
    }
    while (j >= this.numberOfBees);
    this.beeAngle[j] = (this.beeAngle[j] + 6 * this.beeDirection[j]);
    if ((this.beeAngle[j] > 258) || (this.beeAngle[j] < 90))
    {
      int[] arrayOfInt2 = this.beeDirection;
      arrayOfInt2[j] = (-1 * arrayOfInt2[j]);
    }
    if (this.bees[j].getLeft() > this.mCanvasWidth - this.bees[j].getWidth())
      this.beeAngle[j] = 225;
    if (this.bees[j].getLeft() < 0)
      this.beeAngle[j] = 135;
    SurfaceBitmap localSurfaceBitmap = this.bees[j];
    int k = (int)Math.round(this.bees[j].getLeft() + this.paceX * Math.sin(Math.toRadians(this.beeAngle[j])));
    if (this.bees[j].getTop() > this.mCanvasHeight / 2)
      f2 = 2F + 3.0F * f1 / 2F;
    while (true)
    {
      while (true)
      {
        localSurfaceBitmap.setPosition(k, (int)Math.round(f2 + this.bees[j].getTop() - (f1 + this.paceY) * Math.cos(Math.toRadians(this.beeAngle[j]))));
        ++j;
      }
      label309: int i = 0;
      while (true)
      {
        while (i >= this.numberOfAntsWithType[b])
          ++b;
        if (this.smashed[b][i] == 0)
        {
          this.antAngle[b][i] = (this.antAngle[b][i] + 6 * this.antDirection[b][i]);
          if ((this.antAngle[b][i] > 258) || (this.antAngle[b][i] < 90))
          {
            int[] arrayOfInt1 = this.antDirection[b];
            arrayOfInt1[i] = (-1 * arrayOfInt1[i]);
          }
          if (this.ants[b][i].getLeft() > this.mCanvasWidth - this.ants[b][i].getWidth())
            this.antAngle[b][i] = 225;
          if (this.ants[b][i].getLeft() < 0)
            this.antAngle[b][i] = 135;
          this.ants[b][i].setPosition((int)Math.round(this.ants[b][i].getLeft() + this.paceX * Math.sin(Math.toRadians(this.antAngle[b][i]))), (int)Math.round(this.ants[b][i].getTop() - (f1 + this.paceY) * Math.cos(Math.toRadians(this.antAngle[b][i]))));
        }
        ++i;
      }
      f2 = 0F;
    }
  }
}