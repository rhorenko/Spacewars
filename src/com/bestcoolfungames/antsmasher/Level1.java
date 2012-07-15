package com.bestcoolfungames.antsmasher;

import java.util.Random;

public class Level1 extends GameSurface
{
  public void startPositions()
  {
    byte b1;
    int j;
    byte b2;
    byte b3;
    this.paceY = 4;
    this.paceX = 2;
    this.objectPadding = 190;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 3;
    arrayOfInt1[1] = 3;
    arrayOfInt1[2] = 1;
    this.numberOfAntsWithType = arrayOfInt1;
    this.numberOfObjects = 7;
    boolean[] arrayOfBoolean = new boolean[this.numberOfObjects];
    int i = 0;
    if (i >= this.numberOfObjects)
      b1 = 0;
    while (true)
    {
      while (b1 >= 5)
      {
        b3 = 0;
        if (b3 < 5)
          break label203;
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
    int[] arrayOfInt2 = this.antDirection[b1];
    if (rand.nextInt(2) == 0)
      b2 = -1;
    while (true)
    {
      while (true)
      {
        int k;
        arrayOfInt2[j] = b2;
        do
          k = rand.nextInt(this.numberOfObjects);
        while (arrayOfBoolean[k] != 0);
        arrayOfBoolean[k] = true;
        this.antOrder[b1][j] = k;
        ++j;
      }
      b2 = 1;
    }
    label203: int l = 0;
    while (true)
    {
      while (l >= this.numberOfAntsWithType[b3])
        ++b3;
      this.ants[b3][l] = new SurfaceBitmap();
      this.antCounter = (1 + this.antCounter);
      this.ants[b3][l].setPosition(160 - antWidth / 2 + -85 + rand.nextInt(170), -(int)(50.0F * this.scale) - this.objectPadding * this.antOrder[b3][l]);
      ++l;
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