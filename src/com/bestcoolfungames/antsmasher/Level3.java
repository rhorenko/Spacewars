package com.bestcoolfungames.antsmasher;

import java.lang.reflect.Array;
import java.util.Random;

public class Level3 extends GameSurface
{
  private int[][] lastTop;

  public Level3()
  {
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = 5;
    arrayOfInt[1] = 10;
    this.lastTop = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt));
  }

  public void startPositions()
  {
    byte b1;
    int l;
    int i2;
    int i3;
    int i4;
    int[] arrayOfInt5;
    int[] arrayOfInt6;
    int[] arrayOfInt7;
    int[] arrayOfInt8;
    int i5;
    byte b2;
    this.paceY = (3 + Constants.initial_speed_increment);
    this.paceX = 7;
    this.objectPadding = 190;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 5;
    arrayOfInt1[1] = 5;
    arrayOfInt1[2] = 5;
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
    this.numberOfBees = 0;
    this.numberOfObjects = 15;
    boolean[] arrayOfBoolean = new boolean[this.numberOfObjects];
    int i = 0;
    if (i >= this.numberOfObjects)
    {
      b1 = 0;
      if (b1 < 5)
        break label273;
      l = 180;
      if (rand.nextInt(2) != 0)
        break label355;
      i2 = -1;
      i3 = -antHeight;
      if (i2 != 1)
        break label361;
      i4 = 75;
      arrayOfInt5 = new int[this.numberOfObjects];
      arrayOfInt6 = new int[this.numberOfObjects];
      arrayOfInt7 = new int[this.numberOfObjects];
      arrayOfInt8 = new int[this.numberOfObjects];
      i5 = 0;
    }
    while (true)
    {
      while (true)
      {
        while (true)
        {
          while (true)
          {
            if (i5 < 8 * this.numberOfObjects)
              break label369;
            b2 = 0;
            if (b2 < 5)
              break label509;
            return;
            arrayOfBoolean[i] = false;
            ++i;
          }
          label273: int j = 0;
          while (true)
          {
            int k;
            while (j >= this.numberOfAntsWithType[b1])
              ++b1;
            this.antAngle[b1][j] = 180;
            do
              k = new Random().nextInt(this.numberOfObjects);
            while (arrayOfBoolean[k] != 0);
            arrayOfBoolean[k] = true;
            this.antOrder[b1][j] = k;
            ++j;
          }
          label355: i2 = 1;
        }
        label361: i4 = 240;
      }
      if (i5 % 4 == 0)
      {
        label369: arrayOfInt5[(i5 / 8)] = i4;
        arrayOfInt6[(i5 / 8)] = i3;
        arrayOfInt7[(i5 / 8)] = l;
        arrayOfInt8[(i5 / 8)] = (i2 * -1);
      }
      i4 = (int)(i4 + Math.sin(Math.toRadians(l + 180)) * this.paceX);
      i3 = (int)(i3 + 2.0D * Math.cos(Math.toRadians(l)) * this.paceY);
      if ((l > 225) || (l < 135))
        i2 = i2 * -1;
      int i1 = l + i2 * 4;
      ++i5;
    }
    label509: int i6 = 0;
    while (true)
    {
      while (i6 >= this.numberOfAntsWithType[b2])
        ++b2;
      this.antCounter = (1 + this.antCounter);
      this.ants[b2][i6] = new SurfaceBitmap();
      this.antAngle[b2][i6] = arrayOfInt7[this.antOrder[b2][i6]];
      this.antDirection[b2][i6] = arrayOfInt8[this.antOrder[b2][i6]];
      this.lastTop[b2][i6] = arrayOfInt6[this.antOrder[b2][i6]];
      this.ants[b2][i6].setPosition(arrayOfInt5[this.antOrder[b2][i6]], -80);
      ++i6;
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
        float f = 1F + acceleration() / 80.0F;
        this.antAngle[b][i] = Math.round(this.antAngle[b][i] + 4 * this.antDirection[b][i]);
        if ((this.antAngle[b][i] > 225) || (this.antAngle[b][i] < 135))
        {
          int[] arrayOfInt = this.antDirection[b];
          arrayOfInt[i] = (-1 * arrayOfInt[i]);
        }
        this.lastTop[b][i] = (int)Math.round(this.lastTop[b][i] - f * this.paceY * Math.cos(Math.toRadians(this.antAngle[b][i])));
        this.ants[b][i].setPosition((int)Math.round(this.ants[b][i].getLeft() - this.paceX * Math.sin(Math.toRadians(180 + this.antAngle[b][i]))), Math.max(-180, this.lastTop[b][i]));
      }
      ++i;
    }
  }
}