package com.space.levels;
import com.space.game.GameSurface;
import com.space.game.SurfaceBitmap;
public class Level01 extends GameSurface {
	public final static String TAG = "SpaceWars->Level01";
	
  public void startPositions()
  {
    byte b1=0,b2=0,b3=0;
    int j;
    
    tempY = 4;//vertical speed
    tempX = 2;//horizontal speed
    objectPadding = 190;
    int[] arrayOfInt1 = new int[9];
    arrayOfInt1[0] = 3;
    arrayOfInt1[1] = 3;
    arrayOfInt1[2] = 1;
    numberOfshipsWithType = arrayOfInt1;
    numberOfObjects = 7;
    boolean[] arrayOfBoolean = new boolean[numberOfObjects];
    int i = 0;
    //if (i >= numberOfObjects)
      b1 = 0;
    while (true)
    {
      while (b1 >= 5)
      {
        b3 = 0;
        if (b3 < 5)  break;
        arrayOfBoolean[i] = false;
        ++i;
      }
      j = 0;
      if (j < numberOfshipsWithType[b1])
        break;
      ++b1;
    }
    shipAngle[b1][j] = 180;
    int[] arrayOfInt2 = shipDirection[b1];
    //if (rand.nextInt(2) == 0)
      b2 = -1;
    while (true)
    {
      while (true)
      {
        int k;
        arrayOfInt2[j] = b2;
        do
          k = rand.nextInt(numberOfObjects);
        while (arrayOfBoolean[k] != false);
        arrayOfBoolean[k] = true;
        shipOrder[b1][j] = k;
        ++j;
      }
      //b2 = 1;
    }
    ; 


    
    
    int l=0;
    while (true)
    {
      while (l >= numberOfshipsWithType[b3])
        ++b3;
      
      ships[b3][l] = new SurfaceBitmap();
      
      shipCounter = (1 + shipCounter);
      ships[b3][l].setPosition(160 - shipWidth / 2 + -85 + rand.nextInt(170), -(int)(50.0F * scale) - objectPadding * shipOrder[b3][l]);
      ++l;
    }
}

  public void updatePositions(){
    byte visibleShips=0;//number ships that visible
    if (!(paused))
    	{
    	 visibleShips = 0;
    	 //if (visibleShips < 5) return;//I do not understand
    	}
    
    
    int i = 0;
    while (true) {
      while (i >= numberOfshipsWithType[visibleShips])
        ++visibleShips;
      if (smashed[visibleShips][i] == false)
      {
        if (ships[visibleShips][i].getLeft() > mCanvasWidth - ships[visibleShips][i].getWidth())
          shipDirection[visibleShips][i] = -1;
        if (ships[visibleShips][i].getLeft() < 0)
          shipDirection[visibleShips][i] = 1;
        float f = acceleration() / 50.0F;
        ships[visibleShips][i].setPosition(Math.round(ships[visibleShips][i].getLeft() + f * shipDirection[visibleShips][i] * tempX), ships[visibleShips][i].getTop() + (int)(tempY * scale * (1F + f / 3.0F)));
        double d = Math.atan2(f * shipDirection[visibleShips][i] * tempX, tempY * scale * (1F + f / 3.0F));
        shipAngle[visibleShips][i] = (180 + -(int)Math.toDegrees(d));
      }
     ++i;
    }
}
    
}