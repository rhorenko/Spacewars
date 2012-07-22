package com.space.levels;

import java.lang.reflect.Array;

import com.space.game.GameSurface;
import com.space.game.SurfaceBitmap;
import com.space.wars.Constants;

public class Level02 extends GameSurface
{
  public void startPositions()
  {
    int j;
    byte b4;
    int k;
    int l;
    paceY = (3 + Constants.initial_speed_increment);
    paceX = 3;
    objectPadding = 400;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 4;
    arrayOfInt1[1] = 3;
    arrayOfInt1[2] = 1;
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
    beeAngle = new int[5];
    beeDirection = new int[5];
    numberOfBees = 1;
    byte b1 = 0;
    if (b1 >= 5)
    {
      j = 0;
      if (j < numberOfBees)
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
        if (l < numberOfBees)
          break label565;
        return;
        i = 0;
        if (i < numberOfAntsWithType[b1])
          break;
        ++b1;
      }
      antAngle[b1][i] = 180;
      if ((b1 != 0) && (b1 != 1));
      int[] arrayOfInt5 = antDirection[b1];
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
      label277: beeAngle[j] = 180;
      int[] arrayOfInt6 = beeDirection;
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
      if (k < numberOfAntsWithType[b4])
        break;
      ++b4;
    }
    ants[b4][k] = new SurfaceBitmap();
    antCounter = (1 + antCounter);
    if (b4 == 0)
      ants[b4][k].setPosition((int)(antWidth + rand.nextInt(171) * scale), (int)(-50.0D - k * objectPadding * (1D - 0.29999999999999999D * (2 - k % 3))));
    while (true)
    {
      do
        while (true)
        {
          while (true)
            ++k;
          if (b4 != 1)
            break;
          ants[b4][k].setPosition((int)(antWidth + rand.nextInt(141) * scale), (int)(-200.0D - k * objectPadding * (1D - 0.29999999999999999D * (2 - k % 3))));
        }
      while (b4 != 2);
      ants[b4][k].setPosition((int)(30 + antWidth + rand.nextInt(211) * scale), -300);
    }
    label565: bees[l] = new SurfaceBitmap();
    if (beeDirection[l] == 1)
      bees[l].setPosition(250, 0 - objectPadding);
    while (true)
    {
      while (true)
        ++l;
      bees[l].setPosition(70, 0 - objectPadding);
    }
  }

  public void updatePositions()
  {
    int i;
    if (passed);
    do
      return;
    while (paused);
    float f = acceleration() / 36.0F;
    byte b = 0;
    while (true)
    {
      if (b >= 5)
      {
        int j = 0;
        while (true)
        {
          if (j >= numberOfBees);
          beeAngle[j] = (int)Math.round(beeAngle[j] + 2.6000000000000001D * beeDirection[j]);
          bees[j].setPosition(bees[j].getLeft() - (int)Math.round(Math.sin(Math.toRadians(180 + beeAngle[j])) * (paceX + f / 8.5F)), bees[j].getTop() + 2 * paceY / 3 + Math.round(f / 3.5F) - (int)Math.round(Math.cos(Math.toRadians(beeAngle[j])) * paceY));
          ++j;
        }
      }
      i = 0;
      if (i < numberOfAntsWithType[b])
        break;
      ++b;
    }
    if (smashed[b][i] == 0)
    {
      if ((ants[b][i].getLeft() > mCanvasWidth - ants[b][i].getWidth()) && (b != 2))
        antDirection[b][i] = -1;
      if ((ants[b][i].getLeft() < 0) && (b != 2))
        antDirection[b][i] = 1;
      if (b != 0)
        break label431;
      ants[b][i].setPosition(Math.round(ants[b][i].getLeft() + f * antDirection[b][i] * paceX), ants[b][i].getTop() + (int)(paceY * scale * (1F + f / 3.0F)));
      double d2 = Math.atan2(f * antDirection[b][i] * paceX, paceY * scale * (1F + f / 3.0F));
      antAngle[b][i] = (180 + -(int)Math.toDegrees(d2));
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
          ants[b][i].setPosition(Math.round(ants[b][i].getLeft() + antDirection[b][i] * paceX * (1F + f)), ants[b][i].getTop() + (int)(paceY * scale * (1F + f / 3.0F)));
          double d1 = Math.atan2(antDirection[b][i] * paceX * (1F + f), paceY * scale * (1F + f / 3.0F));
          antAngle[b][i] = (180 + -(int)Math.toDegrees(d1));
        }
      while (b != 2);
      if (ants[b][i].getLeft() > mCanvasWidth - ants[b][i].getWidth())
        antAngle[b][i] = 250;
      if (ants[b][i].getLeft() < 0)
        antAngle[b][i] = 110;
      antAngle[b][i] = (int)Math.round(antAngle[b][i] + 3.6000000000000001D * antDirection[b][i]);
      if ((antAngle[b][i] > 270) || (antAngle[b][i] < 90))
      {
        int[] arrayOfInt = antDirection[b];
        arrayOfInt[i] = (-1 * arrayOfInt[i]);
      }
      ants[b][i].setPosition((int)(ants[b][i].getLeft() + paceX * Math.sin(Math.toRadians(antAngle[b][i]))), (int)(ants[b][i].getTop() - paceY * Math.cos(Math.toRadians(antAngle[b][i]))));
    }
  }
}