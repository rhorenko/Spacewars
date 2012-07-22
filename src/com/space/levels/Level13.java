package com.space.levels;

import java.lang.reflect.Array;
import java.util.Random;

public class Level13 extends GameSurface
{
  public void startPositions()
  {
    int j;
    int i1;
    byte b;
    tempY = (3 + Constants.initial_speed_increment);
    tempX = 2;
    objectPadding = 150;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 1;
    arrayOfInt1[1] = 2;
    arrayOfInt1[2] = 2;
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
    numberOfBees = 5;
    numberOfObjects = 5;
    beeAngle = new int[5];
    boolean[] arrayOfBoolean = new boolean[numberOfObjects];
    int[] arrayOfInt5 = new int[numberOfObjects];
    int i = 0;
    while (true)
    {
      if (i >= numberOfObjects)
      {
        j = 0;
        if (j < 5)
          break;
        i1 = 0;
        if (i1 < numberOfObjects)
          break label310;
        return;
      }
      arrayOfBoolean[i] = false;
      ++i;
    }
    int k = 0;
    while (true)
    {
      int l;
      while (k >= numberOfAntsWithType[j])
      {
        beeAngle[j] = 180;
        ++j;
      }
      antAngle[j][k] = 180;
      do
        l = rand.nextInt(numberOfObjects);
      while (arrayOfBoolean[l] != 0);
      arrayOfBoolean[l] = true;
      antOrder[j][k] = l;
      arrayOfInt5[l] = (k + j * numberOfObjects);
      ++k;
    }
    label310: ants[(arrayOfInt5[i1] / numberOfObjects)][(arrayOfInt5[i1] % numberOfObjects)] = new SurfaceBitmap();
    antCounter = (1 + antCounter);
    if (rand.nextInt(2) == 0)
      b = -1;
    while (true)
    {
      while (true)
      {
        ants[(arrayOfInt5[i1] / numberOfObjects)][(arrayOfInt5[i1] % numberOfObjects)].setPosition(130 + b * 40, -(int)(50.0F * scale) - i1 * objectPadding);
        bees[i1] = new SurfaceBitmap();
        bees[i1].setPosition(125 - b * 40, -(int)(60.0F * scale) - i1 * objectPadding);
        ++i1;
      }
      b = 1;
    }
  }

  public void updatePositions()
  {
    float f;
    byte b1;
    byte b2;
    if (!(paused))
    {
      f = acceleration() / 60.0F;
      b1 = 0;
      if (b1 < 5)
        break label32;
      b2 = 0;
    }
    while (true)
    {
      if (b2 >= 5)
      {
        return;
        label32: int i = 0;
        while (true)
        {
          while (i >= numberOfAntsWithType[b1])
            ++b1;
          if (smashed[b1][i] == 0)
            ants[b1][i].setPosition(ants[b1][i].getLeft(), ants[b1][i].getTop() + Math.round(tempY * scale * (1F + f)));
          ++i;
        }
      }
      bees[b2].setPosition(bees[b2].getLeft(), bees[b2].getTop() + Math.round(tempY * scale * (1F + f)));
      ++b2;
    }
  }
}