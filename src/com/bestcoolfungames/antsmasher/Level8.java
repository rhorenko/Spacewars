package com.bestcoolfungames.antsmasher;

import java.lang.reflect.Array;
import java.util.Random;

public class Level8 extends GameSurface
{
  public void startPositions()
  {
    byte b1;
    int k;
    byte b4;
    int i2;
    this.paceY = (3 + Constants.initial_speed_increment);
    this.paceX = 4;
    this.objectPadding = 170;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 3;
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
    this.numberOfBees = 3;
    this.numberOfObjects = 12;
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
      label235: int j;
      byte b2;
      byte b3;
      int i3;
      while (true)
      {
        while (true)
        {
          if (i2 < this.numberOfBees)
            break label498;
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
      label311: this.beeAngle[k] = 180;
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
      label360: int l = 0;
      while (true)
      {
        int i1;
        while (l >= this.numberOfAntsWithType[b4])
          ++b4;
        do
          i1 = rand.nextInt(this.numberOfObjects);
        while (arrayOfBoolean[i1] != 0);
        arrayOfBoolean[i1] = true;
        this.antOrder[b4][l] = i1;
        this.ants[b4][l] = new SurfaceBitmap();
        this.antCounter = (1 + this.antCounter);
        this.ants[b4][l].setPosition(160 - antWidth / 2 + 95 * (-1 + rand.nextInt(3)), -50 - i1 * this.objectPadding);
        ++l;
      }
      do
        label498: i3 = rand.nextInt(this.numberOfObjects);
      while (arrayOfBoolean[i3] != 0);
      arrayOfBoolean[i3] = true;
      this.beeOrder[i2] = i3;
      this.bees[i2] = new SurfaceBitmap();
      this.bees[i2].setPosition(160 - antWidth / 2 + 95 * (-1 + rand.nextInt(3)), -50 - i3 * this.objectPadding);
      ++i2;
    }
  }

  public void updatePositions()
  {
    float f;
    if (this.passed);
    do
    {
      return;
      f = acceleration() / 48.0F;
    }
    while (this.paused);
    byte b = 0;
    if (b >= 5)
    {
      int j = 0;
      while (true)
      {
        if (j >= this.numberOfBees);
        this.bees[j].setPosition(this.bees[j].getLeft(), this.bees[j].getTop() + (int)(this.paceY * this.scale * (1F + f)));
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