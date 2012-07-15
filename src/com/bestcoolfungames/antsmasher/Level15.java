package com.bestcoolfungames.antsmasher;

import java.lang.reflect.Array;
import java.util.Random;

public class Level15 extends GameSurface
{
  public void startPositions()
  {
    int i;
    byte b2;
    int j;
    byte b3;
    byte b4;
    this.paceY = (2 + Constants.initial_speed_increment);
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
    this.numberOfBees = 4;
    byte b1 = 0;
    while (true)
    {
      if (b1 >= 5)
      {
        j = 0;
        if (j < this.numberOfBees)
          break label362;
        b4 = 0;
        if (b4 < 5)
          break label411;
        this.bees[0] = new SurfaceBitmap();
        this.bees[0].setPosition(160, -255);
        this.bees[1] = new SurfaceBitmap();
        this.bees[1].setPosition(90, -150);
        this.bees[2] = new SurfaceBitmap();
        this.bees[2].setPosition(160, -75);
        this.bees[3] = new SurfaceBitmap();
        this.bees[3].setPosition(220, -150);
        return;
      }
      i = 0;
      if (i < this.numberOfAntsWithType[b1])
        break;
      ++b1;
    }
    this.antAngle[b1][i] = 180;
    int[] arrayOfInt5 = this.antDirection[b1];
    if (rand.nextInt(2) == 0)
      b2 = -1;
    while (true)
    {
      while (true)
      {
        arrayOfInt5[i] = b2;
        ++i;
      }
      b2 = 1;
    }
    label362: this.beeAngle[j] = 180;
    int[] arrayOfInt6 = this.beeDirection;
    if (rand.nextInt(2) == 0)
      b3 = -1;
    while (true)
    {
      while (true)
      {
        arrayOfInt6[j] = b3;
        ++j;
      }
      b3 = 1;
    }
    label411: int k = 0;
    while (true)
    {
      while (k >= this.numberOfAntsWithType[b4])
        ++b4;
      this.antCounter = (1 + this.antCounter);
      this.ants[b4][k] = new SurfaceBitmap();
      this.ants[b4][k].setPosition(160, -150);
      ++k;
    }
  }

  public void updatePositions()
  {
    int i;
    if (this.passed);
    do
      return;
    while (this.paused);
    float f = acceleration() / 100.0F;
    byte b = 0;
    while (true)
    {
      if (b >= 5);
      i = 0;
      if (i < this.numberOfAntsWithType[b])
        break;
      ++b;
    }
    if (this.smashed[b][i] == 0)
    {
      if (this.ants[b][i].getLeft() > -75 + this.mCanvasWidth - this.ants[b][i].getWidth())
        this.antDirection[b][i] = -1;
      if (this.ants[b][i].getLeft() < 75)
        this.antDirection[b][i] = 1;
      this.ants[b][i].setPosition(Math.round(this.ants[b][i].getLeft() + f * this.antDirection[b][i] * this.paceX), this.ants[b][i].getTop() + (int)(this.paceY * this.scale * (1F + f)));
      double d = Math.atan2(f * this.antDirection[b][i] * this.paceX, this.paceY * this.scale * (1F + f));
      this.antAngle[b][i] = (180 + -(int)Math.toDegrees(d));
      this.bees[0].setPosition(this.ants[b][i].getLeft(), -105 + this.ants[b][i].getTop());
      this.bees[1].setPosition(-95 + this.ants[b][i].getLeft(), this.ants[b][i].getTop());
      this.bees[2].setPosition(this.ants[b][i].getLeft(), 105 + this.ants[b][i].getTop());
      this.bees[3].setPosition(85 + this.ants[b][i].getLeft(), this.ants[b][i].getTop());
      this.beeAngle[0] = this.antAngle[b][i];
      this.beeAngle[1] = this.antAngle[b][i];
      this.beeAngle[2] = this.antAngle[b][i];
      this.beeAngle[3] = this.antAngle[b][i];
    }
    while (true)
    {
      while (true)
        ++i;
      this.bees[0].setPosition(this.bees[0].getLeft(), this.bees[0].getTop() + this.paceY);
      this.bees[1].setPosition(this.bees[1].getLeft(), this.bees[1].getTop() + this.paceY);
      this.bees[2].setPosition(this.bees[2].getLeft(), this.bees[2].getTop() + this.paceY);
      this.bees[3].setPosition(this.bees[3].getLeft(), this.bees[3].getTop() + this.paceY);
      this.beeAngle[0] = 180;
      this.beeAngle[1] = 180;
      this.beeAngle[2] = 180;
      this.beeAngle[3] = 180;
    }
  }
}