package com.bestcoolfungames.antsmasher;

import java.lang.reflect.Array;
import java.util.Random;

public class Level2 extends GameSurface
{
  public void startPositions()
  {
    int j;
    byte b4;
    int k;
    int l;
    this.paceY = (3 + Constants.initial_speed_increment);
    this.paceX = 3;
    this.objectPadding = 400;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 4;
    arrayOfInt1[1] = 3;
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
    this.numberOfBees = 1;
    byte b1 = 0;
    if (b1 >= 5)
    {
      j = 0;
      if (j < this.numberOfBees)
        break label277;
      b4 = 0;
    }
    while (true)
    {
      int i;
      byte b2;
      byte b3;
      while (true)
      {
        if (b4 < 5)
          break label326;
        l = 0;
        if (l < this.numberOfBees)
          break label565;
        return;
        i = 0;
        if (i < this.numberOfAntsWithType[b1])
          break;
        ++b1;
      }
      this.antAngle[b1][i] = 180;
      if ((b1 != 0) && (b1 != 1));
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
      label277: this.beeAngle[j] = 180;
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
      label326: k = 0;
      if (k < this.numberOfAntsWithType[b4])
        break;
      ++b4;
    }
    this.ants[b4][k] = new SurfaceBitmap();
    this.antCounter = (1 + this.antCounter);
    if (b4 == 0)
      this.ants[b4][k].setPosition((int)(antWidth + rand.nextInt(171) * this.scale), (int)(-50.0D - k * this.objectPadding * (1D - 0.29999999999999999D * (2 - k % 3))));
    while (true)
    {
      do
        while (true)
        {
          while (true)
            ++k;
          if (b4 != 1)
            break;
          this.ants[b4][k].setPosition((int)(antWidth + rand.nextInt(141) * this.scale), (int)(-200.0D - k * this.objectPadding * (1D - 0.29999999999999999D * (2 - k % 3))));
        }
      while (b4 != 2);
      this.ants[b4][k].setPosition((int)(30 + antWidth + rand.nextInt(211) * this.scale), -300);
    }
    label565: this.bees[l] = new SurfaceBitmap();
    if (this.beeDirection[l] == 1)
      this.bees[l].setPosition(250, 0 - this.objectPadding);
    while (true)
    {
      while (true)
        ++l;
      this.bees[l].setPosition(70, 0 - this.objectPadding);
    }
  }

  public void updatePositions()
  {
    int i;
    if (this.passed);
    do
      return;
    while (this.paused);
    float f = acceleration() / 36.0F;
    byte b = 0;
    while (true)
    {
      if (b >= 5)
      {
        int j = 0;
        while (true)
        {
          if (j >= this.numberOfBees);
          this.beeAngle[j] = (int)Math.round(this.beeAngle[j] + 2.6000000000000001D * this.beeDirection[j]);
          this.bees[j].setPosition(this.bees[j].getLeft() - (int)Math.round(Math.sin(Math.toRadians(180 + this.beeAngle[j])) * (this.paceX + f / 8.5F)), this.bees[j].getTop() + 2 * this.paceY / 3 + Math.round(f / 3.5F) - (int)Math.round(Math.cos(Math.toRadians(this.beeAngle[j])) * this.paceY));
          ++j;
        }
      }
      i = 0;
      if (i < this.numberOfAntsWithType[b])
        break;
      ++b;
    }
    if (this.smashed[b][i] == 0)
    {
      if ((this.ants[b][i].getLeft() > this.mCanvasWidth - this.ants[b][i].getWidth()) && (b != 2))
        this.antDirection[b][i] = -1;
      if ((this.ants[b][i].getLeft() < 0) && (b != 2))
        this.antDirection[b][i] = 1;
      if (b != 0)
        break label431;
      this.ants[b][i].setPosition(Math.round(this.ants[b][i].getLeft() + f * this.antDirection[b][i] * this.paceX), this.ants[b][i].getTop() + (int)(this.paceY * this.scale * (1F + f / 3.0F)));
      double d2 = Math.atan2(f * this.antDirection[b][i] * this.paceX, this.paceY * this.scale * (1F + f / 3.0F));
      this.antAngle[b][i] = (180 + -(int)Math.toDegrees(d2));
    }
    while (true)
    {
      do
        while (true)
        {
          while (true)
            ++i;
          label431: if (b != 1)
            break;
          this.ants[b][i].setPosition(Math.round(this.ants[b][i].getLeft() + this.antDirection[b][i] * this.paceX * (1F + f)), this.ants[b][i].getTop() + (int)(this.paceY * this.scale * (1F + f / 3.0F)));
          double d1 = Math.atan2(this.antDirection[b][i] * this.paceX * (1F + f), this.paceY * this.scale * (1F + f / 3.0F));
          this.antAngle[b][i] = (180 + -(int)Math.toDegrees(d1));
        }
      while (b != 2);
      if (this.ants[b][i].getLeft() > this.mCanvasWidth - this.ants[b][i].getWidth())
        this.antAngle[b][i] = 250;
      if (this.ants[b][i].getLeft() < 0)
        this.antAngle[b][i] = 110;
      this.antAngle[b][i] = (int)Math.round(this.antAngle[b][i] + 3.6000000000000001D * this.antDirection[b][i]);
      if ((this.antAngle[b][i] > 270) || (this.antAngle[b][i] < 90))
      {
        int[] arrayOfInt = this.antDirection[b];
        arrayOfInt[i] = (-1 * arrayOfInt[i]);
      }
      this.ants[b][i].setPosition((int)(this.ants[b][i].getLeft() + this.paceX * Math.sin(Math.toRadians(this.antAngle[b][i]))), (int)(this.ants[b][i].getTop() - this.paceY * Math.cos(Math.toRadians(this.antAngle[b][i]))));
    }
  }
}