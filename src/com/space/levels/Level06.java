package com.space.levels;

import java.lang.reflect.Array;
import java.util.Random;

public class Level06 extends GameSurface
{
  public void startPositions()
  {
    byte b1;
    byte b3;
    int[] arrayOfInt6;
    int k;
    int i2;
    paceY = (3 + Constants.initial_speed_increment);
    paceX = 2;
    objectPadding = 190;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 6;
    arrayOfInt1[1] = 7;
    arrayOfInt1[2] = 7;
    numberOfAntsWithType = arrayOfInt1;
    int[] arrayOfInt2 = new int[2];
    arrayOfInt2[0] = 5;
    arrayOfInt2[1] = 10;
    antAngle = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt2));
    int[] arrayOfInt3 = new int[2];
    arrayOfInt3[0] = 5;
    arrayOfInt3[1] = 10;
    antOrder = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt3));
    int[] arrayOfInt4 = new int[2];
    arrayOfInt4[0] = 5;
    arrayOfInt4[1] = 10;
    antDirection = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt4));
    numberOfObjects = 20;
    SurfaceBitmap[] arrayOfSurfaceBitmap = new SurfaceBitmap[numberOfObjects];
    boolean[] arrayOfBoolean = new boolean[numberOfObjects];
    int i = 0;
    if (i >= numberOfObjects)
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
          arrayOfInt6 = new int[numberOfObjects];
          k = 0;
          if (k < 5)
            break label313;
          i2 = 0;
          if (i2 < numberOfObjects)
            break label490;
          return;
          arrayOfBoolean[i] = false;
          ++i;
        }
        j = 0;
        if (j < numberOfAntsWithType[b1])
          break;
        ++b1;
      }
      antAngle[b1][j] = 180;
      int[] arrayOfInt5 = antDirection[b1];
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
      while (l >= numberOfAntsWithType[k])
        ++k;
      do
        i1 = rand.nextInt(numberOfObjects);
      while (arrayOfBoolean[i1] != 0);
      arrayOfBoolean[i1] = true;
      ants[k][l] = new SurfaceBitmap();
      antCounter = (1 + antCounter);
      antOrder[k][l] = i1;
      arrayOfSurfaceBitmap[i1] = ants[k][l];
      arrayOfInt6[i1] = (l + k * numberOfObjects);
      ants[k][l].setPosition(160 + rand.nextInt(2 * antWidth) - antWidth, -50 - -30 + rand.nextInt(61) - 480 * i1 / 5);
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
        antDirection[(arrayOfInt6[i2] / numberOfObjects)][(arrayOfInt6[i2] % numberOfObjects)] = b3;
        ++i2;
      }
      label544: b3 = -1;
    }
  }

  public void updatePositions()
  {
    byte b;
    if (!(paused))
    {
      b = 0;
      if (b < 5)
        break label15;
    }
    return;
    label15: int i = 0;
    while (true)
    {
      while (i >= numberOfAntsWithType[b])
        ++b;
      if (smashed[b][i] == 0)
      {
        if (ants[b][i].getLeft() > mCanvasWidth - ants[b][i].getWidth())
          antDirection[b][i] = -1;
        if (ants[b][i].getLeft() < 0)
          antDirection[b][i] = 1;
        float f = acceleration() / 50.0F;
        ants[b][i].setPosition(Math.round(ants[b][i].getLeft() + f * antDirection[b][i] * paceX), ants[b][i].getTop() + (int)(paceY * scale * (1F + f / 3.0F)));
        double d = Math.atan2(f * antDirection[b][i] * paceX, paceY * scale * (1F + f / 3.0F));
        antAngle[b][i] = (180 + -(int)Math.toDegrees(d));
      }
      ++i;
    }
  }
}