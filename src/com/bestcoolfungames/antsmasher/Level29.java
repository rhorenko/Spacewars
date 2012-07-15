package com.bestcoolfungames.antsmasher;

import java.lang.reflect.Array;
import java.util.Random;

public class Level29 extends GameSurface
{
  int[][] antMovementAngle;
  int[] beeMovementAngle;

  public void startPositions()
  {
    byte b1;
    int k;
    byte b4;
    int i2;
    this.paceY = (2 + Constants.initial_speed_increment);
    this.paceX = 4;
    this.objectPadding = 120;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 6;
    arrayOfInt1[4] = 1;
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
    int[] arrayOfInt5 = new int[2];
    arrayOfInt5[0] = 5;
    arrayOfInt5[1] = 10;
    this.antMovementAngle = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt5));
    this.beeAngle = new int[5];
    this.beeDirection = new int[5];
    this.beeOrder = new int[5];
    this.beeMovementAngle = new int[5];
    this.numberOfBees = 0;
    this.numberOfObjects = 7;
    boolean[] arrayOfBoolean = new boolean[this.numberOfObjects];
    int i = 0;
    if (i >= this.numberOfObjects)
    {
      b1 = 0;
      if (b1 < 5)
        break label269;
      k = 0;
      if (k < this.numberOfBees)
        break label358;
      b4 = 0;
      if (b4 < 5)
        break label417;
      i2 = 0;
    }
    while (true)
    {
      label269: int j;
      byte b2;
      byte b3;
      int i3;
      while (true)
      {
        while (true)
        {
          if (i2 < this.numberOfBees)
            break label541;
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
      this.antMovementAngle[b1][j] = 180;
      int[] arrayOfInt6 = this.antDirection[b1];
      if (rand.nextInt(2) == 0)
        b2 = -1;
      while (true)
      {
        while (true)
        {
          arrayOfInt6[j] = b2;
          ++j;
        }
        b2 = 1;
      }
      label358: this.beeAngle[k] = 180;
      this.beeMovementAngle[k] = 180;
      int[] arrayOfInt7 = this.beeDirection;
      if (rand.nextInt(2) == 0)
        b3 = -1;
      while (true)
      {
        while (true)
        {
          arrayOfInt7[k] = b3;
          ++k;
        }
        b3 = 1;
      }
      label417: int l = 0;
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
        this.ants[b4][l].setPosition(160 - antWidth / 2, -50 - i1 * this.objectPadding);
        ++l;
      }
      do
        label541: i3 = rand.nextInt(this.numberOfObjects);
      while (arrayOfBoolean[i3] != 0);
      arrayOfBoolean[i3] = true;
      this.beeOrder[i2] = i3;
      this.bees[i2] = new SurfaceBitmap();
      this.bees[i2].setPosition(160 - antWidth / 2, -50 - i3 * this.objectPadding);
      ++i2;
    }
  }

  public void updatePositions()
  {
    if (this.passed);
    do
      return;
    while (this.paused);
    float f = acceleration() / 48.0F;
    byte b = 0;
    if (b >= 5)
    {
      int j = 0;
      while (true)
      {
        if (j >= this.numberOfBees);
        if (this.bees[j].getLeft() > this.mCanvasWidth - this.bees[j].getWidth())
          this.beeMovementAngle[j] = 216;
        if (this.bees[j].getLeft() < 0)
          this.beeMovementAngle[j] = 144;
        if (rand.nextInt(10) < 2)
        {
          int[] arrayOfInt4 = this.beeDirection;
          arrayOfInt4[j] = (-1 * arrayOfInt4[j]);
        }
        if (this.beeMovementAngle[j] > 300)
          this.beeMovementAngle[j] = 144;
        if (this.beeMovementAngle[j] < 60)
          this.beeMovementAngle[j] = 216;
        int[] arrayOfInt3 = this.beeMovementAngle;
        arrayOfInt3[j] = (arrayOfInt3[j] + 5 * this.beeDirection[j]);
        this.bees[j].setPosition((int)Math.round(this.bees[j].getLeft() - Math.sin(Math.toRadians(180 + this.beeMovementAngle[j])) * this.paceX), this.bees[j].getTop() + (int)(this.paceY * this.scale * (1F + f)));
        double d2 = Math.atan2(-Math.sin(Math.toRadians(180 + this.beeMovementAngle[j])) * this.paceX, (int)(this.paceY * this.scale * (1F + f)));
        this.beeAngle[j] = (180 + -(int)Math.toDegrees(d2));
        ++j;
      }
    }
    int i = 0;
    while (true)
    {
      while (i >= this.numberOfAntsWithType[b])
        ++b;
      if (this.smashed[b][i] == 0)
      {
        if (this.ants[b][i].getLeft() > this.mCanvasWidth - this.ants[b][i].getWidth())
          this.antMovementAngle[b][i] = 216;
        if (this.ants[b][i].getLeft() < 0)
          this.antMovementAngle[b][i] = 144;
        if (rand.nextInt(10) < 2)
        {
          int[] arrayOfInt2 = this.antDirection[b];
          arrayOfInt2[i] = (-1 * arrayOfInt2[i]);
        }
        if (this.antMovementAngle[b][i] > 300)
          this.antMovementAngle[b][i] = 144;
        if (this.antMovementAngle[b][i] < 60)
          this.antMovementAngle[b][i] = 216;
        int[] arrayOfInt1 = this.antMovementAngle[b];
        arrayOfInt1[i] = (arrayOfInt1[i] + 5 * this.antDirection[b][i]);
        this.ants[b][i].setPosition((int)Math.round(this.ants[b][i].getLeft() - Math.sin(Math.toRadians(180 + this.antMovementAngle[b][i])) * this.paceX), this.ants[b][i].getTop() + (int)(this.paceY * this.scale * (1F + f)));
        double d1 = Math.atan2(-Math.sin(Math.toRadians(180 + this.antMovementAngle[b][i])) * this.paceX, (int)(this.paceY * this.scale * (1F + f)));
        this.antAngle[b][i] = (180 + -(int)Math.toDegrees(d1));
      }
      ++i;
    }
  }
}