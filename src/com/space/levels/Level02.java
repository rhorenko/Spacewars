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
    tempY = (3 + Constants.initial_speed_increment);
    tempX = 3;
    objectPadding = 400;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 4;
    arrayOfInt1[1] = 3;
    arrayOfInt1[2] = 1;
    numberOfshipsWithType = arrayOfInt1;
    int[] arrayOfInt2 = new int[2];
    arrayOfInt2[0] = 5;
    arrayOfInt2[1] = 10;
    shipAngle = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt2));
    int[] arrayOfInt3 = new int[2];
    arrayOfInt3[0] = 5;
    arrayOfInt3[1] = 10;
    shipOrder = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt3));
    int[] arrayOfInt4 = new int[2];
    arrayOfInt4[0] = 5;
    arrayOfInt4[1] = 10;
    shipDirection = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt4));
    beeAngle = new int[5];
    beeDirection = new int[5];
    numberOfBees = 1;
    byte b1 = 0;
    if (b1 >= 5)
    {
      j = 0;
      if (j < numberOfBees)return;
      b4 = 0;
    }
    while (true)
    {
      int i;
      byte b2;
      byte b3;
      while (true)
      {
        if (b4 < 5) return;
        l = 0;
        if (l < numberOfBees)
          return;
        
        i = 0;
        if (i < numberOfshipsWithType[b1])
          break;
        ++b1;
      }
      shipAngle[b1][i] = 180;
      if ((b1 != 0) && (b1 != 1));
      int[] arrayOfInt5 = shipDirection[b1];
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
      beeAngle[j] = 180;
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
      if (k < numberOfshipsWithType[b4])
        break;
      ++b4;
    }
    ships[b4][k] = new SurfaceBitmap();
    shipCounter = (1 + shipCounter);
    if (b4 == 0)
      ships[b4][k].setPosition((int)(shipWidth + rand.nextInt(171) * scale), (int)(-50.0D - k * objectPadding * (1D - 0.29999999999999999D * (2 - k % 3))));
    while (true)
    {
      do
        while (true)
        {
          while (true)
            ++k;
          if (b4 != 1)
            break;
          ships[b4][k].setPosition((int)(shipWidth + rand.nextInt(141) * scale), (int)(-200.0D - k * objectPadding * (1D - 0.29999999999999999D * (2 - k % 3))));
        }
      while (b4 != 2);
      ships[b4][k].setPosition((int)(30 + shipWidth + rand.nextInt(211) * scale), -300);
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
          bees[j].setPosition(bees[j].getLeft() - (int)Math.round(Math.sin(Math.toRadians(180 + beeAngle[j])) * (tempX + f / 8.5F)), bees[j].getTop() + 2 * tempY / 3 + Math.round(f / 3.5F) - (int)Math.round(Math.cos(Math.toRadians(beeAngle[j])) * tempY));
          ++j;
        }
      }
      i = 0;
      if (i < numberOfshipsWithType[b])
        break;
      ++b;
    }
    if (smashed[b][i] == false)
    {
      if ((ships[b][i].getLeft() > mCanvasWidth - ships[b][i].getWidth()) && (b != 2))
        shipDirection[b][i] = -1;
      if ((ships[b][i].getLeft() < 0) && (b != 2))
        shipDirection[b][i] = 1;
      if (b != 0)
        break label431;
      ships[b][i].setPosition(Math.round(ships[b][i].getLeft() + f * shipDirection[b][i] * tempX), ships[b][i].getTop() + (int)(tempY * scale * (1F + f / 3.0F)));
      double d2 = Math.atan2(f * shipDirection[b][i] * tempX, tempY * scale * (1F + f / 3.0F));
      shipAngle[b][i] = (180 + -(int)Math.toDegrees(d2));
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
          ships[b][i].setPosition(Math.round(ships[b][i].getLeft() + shipDirection[b][i] * tempX * (1F + f)), ships[b][i].getTop() + (int)(tempY * scale * (1F + f / 3.0F)));
          double d1 = Math.atan2(shipDirection[b][i] * tempX * (1F + f), tempY * scale * (1F + f / 3.0F));
          shipAngle[b][i] = (180 + -(int)Math.toDegrees(d1));
        }
      while (b != 2);
      if (ships[b][i].getLeft() > mCanvasWidth - ships[b][i].getWidth())
        shipAngle[b][i] = 250;
      if (ships[b][i].getLeft() < 0)
        shipAngle[b][i] = 110;
      shipAngle[b][i] = (int)Math.round(shipAngle[b][i] + 3.6000000000000001D * shipDirection[b][i]);
      if ((shipAngle[b][i] > 270) || (shipAngle[b][i] < 90))
      {
        int[] arrayOfInt = shipDirection[b];
        arrayOfInt[i] = (-1 * arrayOfInt[i]);
      }
      ships[b][i].setPosition((int)(ships[b][i].getLeft() + tempX * Math.sin(Math.toRadians(shipAngle[b][i]))), (int)(ships[b][i].getTop() - tempY * Math.cos(Math.toRadians(shipAngle[b][i]))));
    }
  }
}