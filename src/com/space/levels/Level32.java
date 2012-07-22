package com.space.levels;

import java.lang.reflect.Array;

import com.space.game.GameSurface;
import com.space.game.SurfaceBitmap;
import com.space.wars.Constants;

public class Level32 extends GameSurface
{
  int[][] antMovementAngle;
  int[] barataoCounter;
  int[] beeMovementAngle;

  public void startPositions()
  {
    byte b1;
    int j;
    byte b2;
    byte b3;
    tempY = (2 + Constants.initial_speed_increment);
    tempX = 3;
    objectPadding = 120;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[7] = 3;
    numberOfshipsWithType = arrayOfInt1;
    int[] arrayOfInt2 = new int[2];
    arrayOfInt2[0] = 9;
    arrayOfInt2[1] = 10;
    shipAngle = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt2));
    int[] arrayOfInt3 = new int[2];
    arrayOfInt3[0] = 9;
    arrayOfInt3[1] = 10;
    shipOrder = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt3));
    int[] arrayOfInt4 = new int[2];
    arrayOfInt4[0] = 9;
    arrayOfInt4[1] = 10;
    shipDirection = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt4));
    int[] arrayOfInt5 = new int[2];
    arrayOfInt5[0] = 9;
    arrayOfInt5[1] = 10;
    shipMovementAngle = ((int[][])Array.newInstance(Integer.TYPE, arrayOfInt5));
    beeAngle = new int[9];
    beeDirection = new int[9];
    beeOrder = new int[9];
    beeMovementAngle = new int[9];
    numberOfBees = 0;
    numberOfObjects = 3;
    barataoCounter = new int[3];
    boolean[] arrayOfBoolean = new boolean[numberOfObjects];
    int i = 0;
    if (i >= numberOfObjects)
      b1 = 0;
    while (true)
    {
      while (b1 >= 9)
      {
        b3 = 0;
        if (b3 < 9)
          break label346;
        return;
        arrayOfBoolean[i] = false;
        ++i;
      }
      j = 0;
      if (j < numberOfshipsWithType[b1])
        break;
      ++b1;
    }
    shipAngle[b1][j] = 180;
    shipMovementAngle[b1][j] = 180;
    int[] arrayOfInt6 = shipDirection[b1];
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
    label346: int k = 0;
    while (true)
    {
      int l;
      while (k >= numberOfshipsWithType[b3])
        ++b3;
      do
        l = rand.nextInt(numberOfObjects);
      while (arrayOfBoolean[l] != 0);
      arrayOfBoolean[l] = true;
      shipOrder[b3][k] = l;
      ships[b3][k] = new SurfaceBitmap();
      shipCounter = (1 + shipCounter);
      ships[b3][k].setPosition(160 - shipWidth / 2, -250 + k * objectPadding);
      ++k;
    }
  }

  public void updatePositions()
  {
    int i;
    int j;
    if (passed);
    do
      return;
    while (paused);
    byte b = 0;
    while (true)
    {
      if (b >= 9);
      i = 0;
      if (i < numberOfshipsWithType[b])
        break;
      ++b;
    }
    if (smashed[b][i] == 0)
    {
      if (ships[b][i].getLeft() > mCanvasWidth - ships[b][i].getWidth())
        shipMovementAngle[b][i] = 216;
      if (ships[b][i].getLeft() < 0)
        shipMovementAngle[b][i] = 144;
      if (rand.nextInt(10) < 2)
      {
        int[] arrayOfInt2 = shipDirection[b];
        arrayOfInt2[i] = (-1 * arrayOfInt2[i]);
      }
      if (shipMovementAngle[b][i] > 300)
        shipMovementAngle[b][i] = 144;
      if (shipMovementAngle[b][i] < 60)
        shipMovementAngle[b][i] = 216;
      int[] arrayOfInt1 = shipMovementAngle[b];
      arrayOfInt1[i] = (arrayOfInt1[i] + 5 * shipDirection[b][i]);
      if (shipLife[b][i] != 2)
        break label412;
      j = 0;
    }
    while (true)
    {
      while (true)
      {
        float f = acceleration() / 100.0F;
        ships[b][i].setPosition((int)Math.round(ships[b][i].getLeft() - Math.sin(Math.toRadians(180 + shipMovementAngle[b][i])) * (j + tempX)), ships[b][i].getTop() + (int)((j + tempY) * scale * (1F + f)));
        double d = Math.atan2(-Math.sin(Math.toRadians(180 + shipMovementAngle[b][i])) * (j + tempX), (int)((j + tempY) * scale * (1F + f)));
        shipAngle[b][i] = (180 + -(int)Math.toDegrees(d));
        ++i;
      }
      label412: j = 1;
    }
  }
}