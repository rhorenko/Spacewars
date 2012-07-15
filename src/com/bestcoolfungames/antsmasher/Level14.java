package com.bestcoolfungames.antsmasher;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.lang.reflect.Array;
import java.util.Random;

public class Level14 extends GameSurface
{
  int[][] antAcc;
  int[][] antAccDir;

  public void startPositions()
  {
    byte b1;
    int k;
    byte b2;
    byte b3;
    int i = (int)(this.mContext.getResources().getDisplayMetrics().widthPixels * this.scale);
    this.paceY = (4 + Constants.initial_speed_increment);
    this.paceX = 2;
    this.objectPadding = 230;
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
    int[] arrayOfInt5 = new int[2];
    arrayOfInt5[0] = 5;
    arrayOfInt5[1] = 10;
    this.antAcc = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt5));
    int[] arrayOfInt6 = new int[2];
    arrayOfInt6[0] = 5;
    arrayOfInt6[1] = 10;
    this.antAccDir = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt6));
    this.numberOfObjects = 9;
    boolean[] arrayOfBoolean = new boolean[this.numberOfObjects];
    int j = 0;
    if (j >= this.numberOfObjects)
      b1 = 0;
    while (true)
    {
      while (b1 >= 5)
      {
        b3 = 0;
        if (b3 < 5)
          break label408;
        return;
        arrayOfBoolean[j] = false;
        ++j;
      }
      k = 0;
      if (k < this.numberOfAntsWithType[b1])
        break;
      ++b1;
    }
    this.antAngle[b1][k] = 180;
    int[] arrayOfInt7 = this.antDirection[b1];
    if (rand.nextInt(2) == 0)
      b2 = -1;
    while (true)
    {
      while (true)
      {
        int l;
        arrayOfInt7[k] = b2;
        do
          l = rand.nextInt(this.numberOfObjects);
        while (arrayOfBoolean[l] != 0);
        arrayOfBoolean[l] = true;
        this.antOrder[b1][k] = l;
        this.antAcc[b1][k] = 0;
        this.antAccDir[b1][k] = 0;
        ++k;
      }
      b2 = 1;
    }
    label408: int i1 = 0;
    while (true)
    {
      while (i1 >= this.numberOfAntsWithType[b3])
        ++b3;
      this.antCounter = (1 + this.antCounter);
      this.ants[b3][i1] = new SurfaceBitmap();
      this.ants[b3][i1].setPosition(-25 + i / 2 + rand.nextInt(i / 3) - i / 6, -(int)(50.0F * this.scale) - this.objectPadding * this.antOrder[b3][i1]);
      ++i1;
    }
  }

  public void updatePositions()
  {
    int i;
    byte b2;
    int[] arrayOfInt4;
    byte b3;
    if (this.passed);
    do
      return;
    while (this.paused);
    byte b1 = 0;
    while (true)
    {
      if (b1 >= 5);
      i = 0;
      if (i < this.numberOfAntsWithType[b1])
        break;
      ++b1;
    }
    if (this.smashed[b1][i] == 0)
      if ((this.antAccDir[b1][i] == 0) && (this.ants[b1][i].getTop() > -2 * this.ants[b1][i].getHeight()))
      {
        int[] arrayOfInt3 = this.antAccDir[b1];
        if (rand.nextInt(2) != 0)
          break label466;
        b2 = -1;
        arrayOfInt3[i] = b2;
        arrayOfInt4 = this.antAcc[b1];
        if (rand.nextInt(2) != 0)
          break label472;
        b3 = -2;
      }
    while (true)
    {
      while (true)
      {
        while (true)
        {
          arrayOfInt4[i] = b3;
          if (this.ants[b1][i].getLeft() > this.mCanvasWidth - this.ants[b1][i].getWidth())
            this.antDirection[b1][i] = -1;
          if (this.ants[b1][i].getLeft() < 0)
            this.antDirection[b1][i] = 1;
          int j = this.paceX;
          int k = this.paceY;
          if (Math.abs(this.antAcc[b1][i]) > 3)
          {
            int[] arrayOfInt2 = this.antAccDir[b1];
            arrayOfInt2[i] = (-1 * arrayOfInt2[i]);
          }
          if (this.ants[b1][i].getTop() > -this.ants[b1][i].getHeight())
          {
            int[] arrayOfInt1 = this.antAcc[b1];
            arrayOfInt1[i] = (int)(arrayOfInt1[i] + 0.29999999999999999D * this.antAccDir[b1][i]);
          }
          int l = k + this.antAcc[b1][i];
          float f = acceleration() / 100.0F;
          this.ants[b1][i].setPosition(Math.round(this.ants[b1][i].getLeft() + f * j * this.antDirection[b1][i]), this.ants[b1][i].getTop() + (int)(l * this.scale * (1F + f / 3.0F)));
          double d = Math.atan2(f * j * this.antDirection[b1][i], l * this.scale * (1F + f / 3.0F));
          this.antAngle[b1][i] = (180 + -(int)Math.toDegrees(d));
          ++i;
        }
        label466: b2 = 1;
      }
      label472: b3 = 2;
    }
  }
}