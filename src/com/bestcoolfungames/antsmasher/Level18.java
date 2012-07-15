package com.bestcoolfungames.antsmasher;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.lang.reflect.Array;
import java.util.Random;

public class Level18 extends GameSurface
{
  private int[][] antLastTop;
  SurfaceBitmap[] antsInOrder;
  private final int back;
  private int[] beeLastLeft;
  private int[] beeLastTop;
  int[] k;

  public Level18()
  {
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = 5;
    arrayOfInt[1] = 10;
    this.antLastTop = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt));
    this.beeLastTop = new int[5];
    this.beeLastLeft = new int[5];
    this.back = -100;
  }

  private int fixLeft(int paramInt)
  {
    return Math.min(-80 + this.mContext.getResources().getDisplayMetrics().widthPixels, Math.max(0, paramInt));
  }

  public void startPositions()
  {
    int j;
    int i2;
    this.paceY = (2 + Constants.initial_speed_increment);
    this.paceX = 2;
    this.objectPadding = 350;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 1;
    arrayOfInt1[1] = 1;
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
    this.numberOfBees = 3;
    this.numberOfObjects = 3;
    this.beeAngle = new int[3];
    this.beeDirection = new int[3];
    this.antsInOrder = new SurfaceBitmap[this.numberOfObjects];
    boolean[] arrayOfBoolean = new boolean[this.numberOfObjects];
    this.k = new int[this.numberOfObjects];
    int i = 0;
    if (i >= this.numberOfObjects)
    {
      j = 0;
      if (j < this.numberOfObjects)
        break label230;
      i2 = 0;
    }
    while (true)
    {
      label230: int[] arrayOfInt6;
      byte b2;
      while (true)
      {
        if (i2 < this.numberOfObjects)
          break label415;
        return;
        arrayOfBoolean[i] = false;
        ++i;
      }
      int l = 0;
      if (l >= this.numberOfAntsWithType[j])
      {
        this.beeAngle[j] = (18 * 18 * rand.nextInt(10));
        arrayOfInt6 = this.beeDirection;
        if (rand.nextInt(2) != 0)
          break label408;
        b2 = 1;
      }
      while (true)
      {
        byte b1;
        while (true)
        {
          arrayOfInt6[j] = b2;
          ++j;
        }
        this.antAngle[j][l] = 180;
        int[] arrayOfInt5 = this.antDirection[j];
        if (rand.nextInt(2) == 0)
          b1 = -1;
        while (true)
        {
          while (true)
          {
            int i1;
            arrayOfInt5[l] = b1;
            do
              i1 = rand.nextInt(this.numberOfObjects);
            while (arrayOfBoolean[i1] != 0);
            arrayOfBoolean[i1] = true;
            this.antOrder[j][l] = i1;
            this.k[i1] = (l + j * this.numberOfObjects);
            ++l;
          }
          b1 = 1;
        }
        label408: b2 = -1;
      }
      label415: this.antCounter = (1 + this.antCounter);
      int i3 = this.k[i2] / this.numberOfObjects;
      int i4 = this.k[i2] % this.numberOfObjects;
      this.antLastTop[i3][i4] = (-90 - i2 * this.objectPadding);
      this.ants[i3][i4] = new SurfaceBitmap();
      this.ants[i3][i4].setPosition(160 - antWidth / 2 + -30 + InitialView.rand.nextInt(61), Math.max(this.antLastTop[i3][i4], -100));
      this.antsInOrder[i2] = this.ants[(this.k[i2] / this.numberOfObjects)][(this.k[i2] % this.numberOfObjects)];
      this.bees[i2] = new SurfaceBitmap();
      this.beeLastTop[i2] = (int)(this.antLastTop[i3][i4] - Math.cos(this.beeAngle[i2]) * this.paceX);
      this.beeLastLeft[i2] = (int)(this.ants[i3][i4].getLeft() - Math.sin(3.1415000000000002D + this.beeAngle[i2]) * this.paceX);
      this.bees[i2].setPosition(fixLeft(this.beeLastLeft[i2]), Math.max(this.beeLastTop[i2], -100));
      ++i2;
    }
  }

  public void updatePositions()
  {
    int i;
    if (!(this.paused))
    {
      i = 0;
      if (i < this.numberOfObjects)
        break label18;
    }
    return;
    label18: int j = this.k[i] / this.numberOfObjects;
    int l = this.k[i] % this.numberOfObjects;
    if (this.smashed[j][l] == 0)
    {
      if (this.ants[j][l].getLeft() > this.mCanvasWidth - this.ants[j][l].getWidth())
        this.antDirection[j][l] = -1;
      if (this.ants[j][l].getLeft() < 0)
        this.antDirection[j][l] = 1;
      float f = acceleration() / 48.0F;
      this.antLastTop[j][l] = (this.antLastTop[j][l] + (int)(this.paceY * this.scale * (1F + f / 3.0F)));
      this.ants[j][l].setPosition(Math.round(this.ants[j][l].getLeft() + f * this.antDirection[j][l] * this.paceX), Math.max(this.antLastTop[j][l], -100));
      double d = Math.atan2(f * this.antDirection[j][l] * this.paceX, this.paceY * this.scale * (1F + f / 3.0F));
      this.antAngle[j][l] = (180 + -(int)Math.toDegrees(d));
    }
    if (this.smashed[j][l] == 0)
    {
      int[] arrayOfInt = this.beeAngle;
      arrayOfInt[i] = (arrayOfInt[i] + 5 * this.beeDirection[i]);
      int i2 = this.ants[j][l].getLeft() - this.ants[j][l].getWidth() / 4;
      int i3 = this.antLastTop[j][l] - this.ants[j][l].getHeight() / 4;
      this.beeLastTop[i] = (int)(i3 + 120.0D * Math.cos(Math.toRadians(this.beeAngle[i] + 90 * this.beeDirection[i])));
      this.beeLastLeft[i] = (int)Math.round(i2 + 120.0D * Math.sin(Math.toRadians(180 + this.beeAngle[i] + 90 * this.beeDirection[i])));
      this.bees[i].setPosition(fixLeft(this.beeLastLeft[i]), Math.max(this.beeLastTop[i], -100));
    }
    while (true)
    {
      while (true)
        ++i;
      int i1 = this.mContext.getResources().getDisplayMetrics().heightPixels;
      this.bees[i].setPosition(this.bees[i].getLeft(), Math.min(12 + this.bees[i].getTop(), i1));
    }
  }
}