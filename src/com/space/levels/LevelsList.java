package com.space.levels;

public class LevelsList
{
  public static final Class<?>[] c;
  public static final int numberOfLevels;

  static
  {
    c = new Class[32];
    c[0] = Level01.class;
    c[1] = Level02.class;
    c[2] = Level03.class;
    c[3] = Level04.class;
    c[4] = Level05.class;
    c[5] = Level06.class;
    c[6] = Level07.class;
    c[7] = Level08.class;
    c[8] = Level09.class;
    c[9] = Level10.class;
    c[10] = Level11.class;
    c[11] = Level12.class;
    c[12] = Level13.class;
    c[13] = Level14.class;
    c[14] = Level15.class;
    c[15] = Level16.class;
    c[16] = Level17.class;
    c[17] = Level18.class;
    c[18] = Level19.class;
    c[19] = Level20.class;
    c[20] = Level21.class;
    c[21] = Level22.class;
    c[22] = Level23.class;
    c[23] = Level24.class;
    c[24] = Level25.class;
    c[25] = Level26.class;
    c[26] = Level27.class;
    c[27] = Level28.class;
    c[28] = Level29.class;
    c[29] = Level30.class;
    c[30] = Level31.class;
    c[31] = Level32.class;
    
    numberOfLevels = c.length;
  }

  public static Class<?> getLevel(int lvlNum)
  {
    return c[lvlNum];
  }
}