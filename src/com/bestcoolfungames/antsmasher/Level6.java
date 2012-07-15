package com.bestcoolfungames.antsmasher;

import java.lang.reflect.Array;
import java.util.Random;

public class Level6 extends GameSurface
{
  public void startPositions()
  {
    byte b1;
    byte b3;
    int[] arrayOfInt6;
    int k;
    int i2;
    this.paceY = (3 + Constants.initial_speed_increment);
    this.paceX = 2;
    this.objectPadding = 190;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 6;
    arrayOfInt1[1] = 7;
    arrayOfInt1[2] = 7;
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
    this.numberOfObjects = 20;
    SurfaceBitmap[] arrayOfSurfaceBitmap = new SurfaceBitmap[this.numberOfObjects];
    boolean[] arrayOfBoolean = new boolean[this.numberOfObjects];
    int i = 0;
    if (i >= this.numberOfObjects)
    {
      b1 = 0;
      if (b1 < 5)
        break label230;
      if (rand.nextInt(2) != 0)
        break label306;
      b3 = 1;
    }
    while (true)
    {
      label230: int j;
      byte b2;
      while (true)
      {
        while (true)
        {
          arrayOfInt6 = new int[this.numberOfObjects];
          k = 0;
          if (k < 5)
            break label313;
          i2 = 0;
          if (i2 < this.numberOfObjects)
            break label490;
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
      label306: b3 = -1;
    }
    label313: int l = 0;
    while (true)
    {
      int i1;
      while (l >= this.numberOfAntsWithType[k])
        ++k;
      do
        i1 = rand.nextInt(this.numberOfObjects);
      while (arrayOfBoolean[i1] != 0);
      arrayOfBoolean[i1] = true;
      this.ants[k][l] = new SurfaceBitmap();
      this.antCounter = (1 + this.antCounter);
      this.antOrder[k][l] = i1;
      arrayOfSurfaceBitmap[i1] = this.ants[k][l];
      arrayOfInt6[i1] = (l + k * this.numberOfObjects);
      this.ants[k][l].setPosition(160 + rand.nextInt(2 * antWidth) - antWidth, -50 - -30 + rand.nextInt(61) - 480 * i1 / 5);
      ++l;
    }
    if (i2 % 5 == 0)
    {
      label490: if (rand.nextInt(2) != 0)
        break label544;
      b3 = 1;
    }
    while (true)
    {
      while (true)
      {
        this.antDirection[(arrayOfInt6[i2] / this.numberOfObjects)][(arrayOfInt6[i2] % this.numberOfObjects)] = b3;
        ++i2;
      }
      label544: b3 = -1;
    }
  }

  public void updatePositions()
  {
    byte b;
    if (!(this.paused))
    {
      b = 0;
      if (b < 5)
        break label15;
    }
    return;
    label15: int i = 0;
    while (true)
    {
      while (i >= this.numberOfAntsWithType[b])
        ++b;
      if (this.smashed[b][i] == 0)
      {
        if (this.ants[b][i].getLeft() > this.mCanvasWidth - this.ants[b][i].getWidth())
          this.antDirection[b][i] = -1;
        if (this.ants[b][i].getLeft() < 0)
          this.antDirection[b][i] = 1;
        float f = acceleration() / 50.0F;
        this.ants[b][i].setPosition(Math.round(this.ants[b][i].getLeft() + f * this.antDirection[b][i] * this.paceX), this.ants[b][i].getTop() + (int)(this.paceY * this.scale * (1F + f / 3.0F)));
        double d = Math.atan2(f * this.antDirection[b][i] * this.paceX, this.paceY * this.scale * (1F + f / 3.0F));
        this.antAngle[b][i] = (180 + -(int)Math.toDegrees(d));
      }
      ++i;
    }
  }
}