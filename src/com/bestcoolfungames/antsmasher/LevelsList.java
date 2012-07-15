package com.bestcoolfungames.antsmasher;

public class LevelsList
{
  public static final Class<?>[] c;
  public static final int numberOfLevels;

  static
  {
    Class[] arrayOfClass = new Class[32];
    arrayOfClass[0] = Level1.class;
    arrayOfClass[1] = Level2.class;
    arrayOfClass[2] = Level3.class;
    arrayOfClass[3] = Level4.class;
    arrayOfClass[4] = Level5.class;
    arrayOfClass[5] = Level6.class;
    arrayOfClass[6] = Level7.class;
    arrayOfClass[7] = Level8.class;
    arrayOfClass[8] = Level9.class;
    arrayOfClass[9] = Level10.class;
    arrayOfClass[10] = Level11.class;
    arrayOfClass[11] = Level12.class;
    arrayOfClass[12] = Level13.class;
    arrayOfClass[13] = Level14.class;
    arrayOfClass[14] = Level15.class;
    arrayOfClass[15] = Level16.class;
    arrayOfClass[16] = Level17.class;
    arrayOfClass[17] = Level18.class;
    arrayOfClass[18] = Level19.class;
    arrayOfClass[19] = Level20.class;
    arrayOfClass[20] = Level21.class;
    arrayOfClass[21] = Level22.class;
    arrayOfClass[22] = Level23.class;
    arrayOfClass[23] = Level24.class;
    arrayOfClass[24] = Level25.class;
    arrayOfClass[25] = Level26.class;
    arrayOfClass[26] = Level27.class;
    arrayOfClass[27] = Level28.class;
    arrayOfClass[28] = Level29.class;
    arrayOfClass[29] = Level30.class;
    arrayOfClass[30] = Level31.class;
    arrayOfClass[31] = Level32.class;
    c = arrayOfClass;
    numberOfLevels = c.length;
  }

  public static Class<?> getLevel(int paramInt)
  {
    return c[paramInt];
  }
}