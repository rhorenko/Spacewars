package com.bestcoolfungames.antsmasher.inapp.utils;

public class Base64
{
  private static final byte[] ALPHABET;
  private static final byte[] DECODABET;
  public static final boolean DECODE = 0;
  public static final boolean ENCODE = 1;
  private static final byte EQUALS_SIGN = 61;
  private static final byte EQUALS_SIGN_ENC = -1;
  private static final byte NEW_LINE = 10;
  private static final byte[] WEBSAFE_ALPHABET;
  private static final byte[] WEBSAFE_DECODABET;
  private static final byte WHITE_SPACE_ENC = -5;

  static
  {
    byte b;
    if (!(Base64.class.desiredAssertionStatus()))
      b = 1;
    while (true)
    {
      $assertionsDisabled = b;
      byte[] arrayOfByte1 = new byte[64];
      arrayOfByte1[0] = 65;
      arrayOfByte1[1] = 66;
      arrayOfByte1[2] = 67;
      arrayOfByte1[3] = 68;
      arrayOfByte1[4] = 69;
      arrayOfByte1[5] = 70;
      arrayOfByte1[6] = 71;
      arrayOfByte1[7] = 72;
      arrayOfByte1[8] = 73;
      arrayOfByte1[9] = 74;
      arrayOfByte1[10] = 75;
      arrayOfByte1[11] = 76;
      arrayOfByte1[12] = 77;
      arrayOfByte1[13] = 78;
      arrayOfByte1[14] = 79;
      arrayOfByte1[15] = 80;
      arrayOfByte1[16] = 81;
      arrayOfByte1[17] = 82;
      arrayOfByte1[18] = 83;
      arrayOfByte1[19] = 84;
      arrayOfByte1[20] = 85;
      arrayOfByte1[21] = 86;
      arrayOfByte1[22] = 87;
      arrayOfByte1[23] = 88;
      arrayOfByte1[24] = 89;
      arrayOfByte1[25] = 90;
      arrayOfByte1[26] = 97;
      arrayOfByte1[27] = 98;
      arrayOfByte1[28] = 99;
      arrayOfByte1[29] = 100;
      arrayOfByte1[30] = 101;
      arrayOfByte1[31] = 102;
      arrayOfByte1[32] = 103;
      arrayOfByte1[33] = 104;
      arrayOfByte1[34] = 105;
      arrayOfByte1[35] = 106;
      arrayOfByte1[36] = 107;
      arrayOfByte1[37] = 108;
      arrayOfByte1[38] = 109;
      arrayOfByte1[39] = 110;
      arrayOfByte1[40] = 111;
      arrayOfByte1[41] = 112;
      arrayOfByte1[42] = 113;
      arrayOfByte1[43] = 114;
      arrayOfByte1[44] = 115;
      arrayOfByte1[45] = 116;
      arrayOfByte1[46] = 117;
      arrayOfByte1[47] = 118;
      arrayOfByte1[48] = 119;
      arrayOfByte1[49] = 120;
      arrayOfByte1[50] = 121;
      arrayOfByte1[51] = 122;
      arrayOfByte1[52] = 48;
      arrayOfByte1[53] = 49;
      arrayOfByte1[54] = 50;
      arrayOfByte1[55] = 51;
      arrayOfByte1[56] = 52;
      arrayOfByte1[57] = 53;
      arrayOfByte1[58] = 54;
      arrayOfByte1[59] = 55;
      arrayOfByte1[60] = 56;
      arrayOfByte1[61] = 57;
      arrayOfByte1[62] = 43;
      arrayOfByte1[63] = 47;
      ALPHABET = arrayOfByte1;
      byte[] arrayOfByte2 = new byte[64];
      arrayOfByte2[0] = 65;
      arrayOfByte2[1] = 66;
      arrayOfByte2[2] = 67;
      arrayOfByte2[3] = 68;
      arrayOfByte2[4] = 69;
      arrayOfByte2[5] = 70;
      arrayOfByte2[6] = 71;
      arrayOfByte2[7] = 72;
      arrayOfByte2[8] = 73;
      arrayOfByte2[9] = 74;
      arrayOfByte2[10] = 75;
      arrayOfByte2[11] = 76;
      arrayOfByte2[12] = 77;
      arrayOfByte2[13] = 78;
      arrayOfByte2[14] = 79;
      arrayOfByte2[15] = 80;
      arrayOfByte2[16] = 81;
      arrayOfByte2[17] = 82;
      arrayOfByte2[18] = 83;
      arrayOfByte2[19] = 84;
      arrayOfByte2[20] = 85;
      arrayOfByte2[21] = 86;
      arrayOfByte2[22] = 87;
      arrayOfByte2[23] = 88;
      arrayOfByte2[24] = 89;
      arrayOfByte2[25] = 90;
      arrayOfByte2[26] = 97;
      arrayOfByte2[27] = 98;
      arrayOfByte2[28] = 99;
      arrayOfByte2[29] = 100;
      arrayOfByte2[30] = 101;
      arrayOfByte2[31] = 102;
      arrayOfByte2[32] = 103;
      arrayOfByte2[33] = 104;
      arrayOfByte2[34] = 105;
      arrayOfByte2[35] = 106;
      arrayOfByte2[36] = 107;
      arrayOfByte2[37] = 108;
      arrayOfByte2[38] = 109;
      arrayOfByte2[39] = 110;
      arrayOfByte2[40] = 111;
      arrayOfByte2[41] = 112;
      arrayOfByte2[42] = 113;
      arrayOfByte2[43] = 114;
      arrayOfByte2[44] = 115;
      arrayOfByte2[45] = 116;
      arrayOfByte2[46] = 117;
      arrayOfByte2[47] = 118;
      arrayOfByte2[48] = 119;
      arrayOfByte2[49] = 120;
      arrayOfByte2[50] = 121;
      arrayOfByte2[51] = 122;
      arrayOfByte2[52] = 48;
      arrayOfByte2[53] = 49;
      arrayOfByte2[54] = 50;
      arrayOfByte2[55] = 51;
      arrayOfByte2[56] = 52;
      arrayOfByte2[57] = 53;
      arrayOfByte2[58] = 54;
      arrayOfByte2[59] = 55;
      arrayOfByte2[60] = 56;
      arrayOfByte2[61] = 57;
      arrayOfByte2[62] = 45;
      arrayOfByte2[63] = 95;
      WEBSAFE_ALPHABET = arrayOfByte2;
      byte[] arrayOfByte3 = new byte[128];
      arrayOfByte3[0] = -9;
      arrayOfByte3[1] = -9;
      arrayOfByte3[2] = -9;
      arrayOfByte3[3] = -9;
      arrayOfByte3[4] = -9;
      arrayOfByte3[5] = -9;
      arrayOfByte3[6] = -9;
      arrayOfByte3[7] = -9;
      arrayOfByte3[8] = -9;
      arrayOfByte3[9] = -5;
      arrayOfByte3[10] = -5;
      arrayOfByte3[11] = -9;
      arrayOfByte3[12] = -9;
      arrayOfByte3[13] = -5;
      arrayOfByte3[14] = -9;
      arrayOfByte3[15] = -9;
      arrayOfByte3[16] = -9;
      arrayOfByte3[17] = -9;
      arrayOfByte3[18] = -9;
      arrayOfByte3[19] = -9;
      arrayOfByte3[20] = -9;
      arrayOfByte3[21] = -9;
      arrayOfByte3[22] = -9;
      arrayOfByte3[23] = -9;
      arrayOfByte3[24] = -9;
      arrayOfByte3[25] = -9;
      arrayOfByte3[26] = -9;
      arrayOfByte3[27] = -9;
      arrayOfByte3[28] = -9;
      arrayOfByte3[29] = -9;
      arrayOfByte3[30] = -9;
      arrayOfByte3[31] = -9;
      arrayOfByte3[32] = -5;
      arrayOfByte3[33] = -9;
      arrayOfByte3[34] = -9;
      arrayOfByte3[35] = -9;
      arrayOfByte3[36] = -9;
      arrayOfByte3[37] = -9;
      arrayOfByte3[38] = -9;
      arrayOfByte3[39] = -9;
      arrayOfByte3[40] = -9;
      arrayOfByte3[41] = -9;
      arrayOfByte3[42] = -9;
      arrayOfByte3[43] = 62;
      arrayOfByte3[44] = -9;
      arrayOfByte3[45] = -9;
      arrayOfByte3[46] = -9;
      arrayOfByte3[47] = 63;
      arrayOfByte3[48] = 52;
      arrayOfByte3[49] = 53;
      arrayOfByte3[50] = 54;
      arrayOfByte3[51] = 55;
      arrayOfByte3[52] = 56;
      arrayOfByte3[53] = 57;
      arrayOfByte3[54] = 58;
      arrayOfByte3[55] = 59;
      arrayOfByte3[56] = 60;
      arrayOfByte3[57] = 61;
      arrayOfByte3[58] = -9;
      arrayOfByte3[59] = -9;
      arrayOfByte3[60] = -9;
      arrayOfByte3[61] = -1;
      arrayOfByte3[62] = -9;
      arrayOfByte3[63] = -9;
      arrayOfByte3[64] = -9;
      arrayOfByte3[66] = 1;
      arrayOfByte3[67] = 2;
      arrayOfByte3[68] = 3;
      arrayOfByte3[69] = 4;
      arrayOfByte3[70] = 5;
      arrayOfByte3[71] = 6;
      arrayOfByte3[72] = 7;
      arrayOfByte3[73] = 8;
      arrayOfByte3[74] = 9;
      arrayOfByte3[75] = 10;
      arrayOfByte3[76] = 11;
      arrayOfByte3[77] = 12;
      arrayOfByte3[78] = 13;
      arrayOfByte3[79] = 14;
      arrayOfByte3[80] = 15;
      arrayOfByte3[81] = 16;
      arrayOfByte3[82] = 17;
      arrayOfByte3[83] = 18;
      arrayOfByte3[84] = 19;
      arrayOfByte3[85] = 20;
      arrayOfByte3[86] = 21;
      arrayOfByte3[87] = 22;
      arrayOfByte3[88] = 23;
      arrayOfByte3[89] = 24;
      arrayOfByte3[90] = 25;
      arrayOfByte3[91] = -9;
      arrayOfByte3[92] = -9;
      arrayOfByte3[93] = -9;
      arrayOfByte3[94] = -9;
      arrayOfByte3[95] = -9;
      arrayOfByte3[96] = -9;
      arrayOfByte3[97] = 26;
      arrayOfByte3[98] = 27;
      arrayOfByte3[99] = 28;
      arrayOfByte3[100] = 29;
      arrayOfByte3[101] = 30;
      arrayOfByte3[102] = 31;
      arrayOfByte3[103] = 32;
      arrayOfByte3[104] = 33;
      arrayOfByte3[105] = 34;
      arrayOfByte3[106] = 35;
      arrayOfByte3[107] = 36;
      arrayOfByte3[108] = 37;
      arrayOfByte3[109] = 38;
      arrayOfByte3[110] = 39;
      arrayOfByte3[111] = 40;
      arrayOfByte3[112] = 41;
      arrayOfByte3[113] = 42;
      arrayOfByte3[114] = 43;
      arrayOfByte3[115] = 44;
      arrayOfByte3[116] = 45;
      arrayOfByte3[117] = 46;
      arrayOfByte3[118] = 47;
      arrayOfByte3[119] = 48;
      arrayOfByte3[120] = 49;
      arrayOfByte3[121] = 50;
      arrayOfByte3[122] = 51;
      arrayOfByte3[123] = -9;
      arrayOfByte3[124] = -9;
      arrayOfByte3[125] = -9;
      arrayOfByte3[126] = -9;
      arrayOfByte3[127] = -9;
      DECODABET = arrayOfByte3;
      byte[] arrayOfByte4 = new byte[128];
      arrayOfByte4[0] = -9;
      arrayOfByte4[1] = -9;
      arrayOfByte4[2] = -9;
      arrayOfByte4[3] = -9;
      arrayOfByte4[4] = -9;
      arrayOfByte4[5] = -9;
      arrayOfByte4[6] = -9;
      arrayOfByte4[7] = -9;
      arrayOfByte4[8] = -9;
      arrayOfByte4[9] = -5;
      arrayOfByte4[10] = -5;
      arrayOfByte4[11] = -9;
      arrayOfByte4[12] = -9;
      arrayOfByte4[13] = -5;
      arrayOfByte4[14] = -9;
      arrayOfByte4[15] = -9;
      arrayOfByte4[16] = -9;
      arrayOfByte4[17] = -9;
      arrayOfByte4[18] = -9;
      arrayOfByte4[19] = -9;
      arrayOfByte4[20] = -9;
      arrayOfByte4[21] = -9;
      arrayOfByte4[22] = -9;
      arrayOfByte4[23] = -9;
      arrayOfByte4[24] = -9;
      arrayOfByte4[25] = -9;
      arrayOfByte4[26] = -9;
      arrayOfByte4[27] = -9;
      arrayOfByte4[28] = -9;
      arrayOfByte4[29] = -9;
      arrayOfByte4[30] = -9;
      arrayOfByte4[31] = -9;
      arrayOfByte4[32] = -5;
      arrayOfByte4[33] = -9;
      arrayOfByte4[34] = -9;
      arrayOfByte4[35] = -9;
      arrayOfByte4[36] = -9;
      arrayOfByte4[37] = -9;
      arrayOfByte4[38] = -9;
      arrayOfByte4[39] = -9;
      arrayOfByte4[40] = -9;
      arrayOfByte4[41] = -9;
      arrayOfByte4[42] = -9;
      arrayOfByte4[43] = -9;
      arrayOfByte4[44] = -9;
      arrayOfByte4[45] = 62;
      arrayOfByte4[46] = -9;
      arrayOfByte4[47] = -9;
      arrayOfByte4[48] = 52;
      arrayOfByte4[49] = 53;
      arrayOfByte4[50] = 54;
      arrayOfByte4[51] = 55;
      arrayOfByte4[52] = 56;
      arrayOfByte4[53] = 57;
      arrayOfByte4[54] = 58;
      arrayOfByte4[55] = 59;
      arrayOfByte4[56] = 60;
      arrayOfByte4[57] = 61;
      arrayOfByte4[58] = -9;
      arrayOfByte4[59] = -9;
      arrayOfByte4[60] = -9;
      arrayOfByte4[61] = -1;
      arrayOfByte4[62] = -9;
      arrayOfByte4[63] = -9;
      arrayOfByte4[64] = -9;
      arrayOfByte4[66] = 1;
      arrayOfByte4[67] = 2;
      arrayOfByte4[68] = 3;
      arrayOfByte4[69] = 4;
      arrayOfByte4[70] = 5;
      arrayOfByte4[71] = 6;
      arrayOfByte4[72] = 7;
      arrayOfByte4[73] = 8;
      arrayOfByte4[74] = 9;
      arrayOfByte4[75] = 10;
      arrayOfByte4[76] = 11;
      arrayOfByte4[77] = 12;
      arrayOfByte4[78] = 13;
      arrayOfByte4[79] = 14;
      arrayOfByte4[80] = 15;
      arrayOfByte4[81] = 16;
      arrayOfByte4[82] = 17;
      arrayOfByte4[83] = 18;
      arrayOfByte4[84] = 19;
      arrayOfByte4[85] = 20;
      arrayOfByte4[86] = 21;
      arrayOfByte4[87] = 22;
      arrayOfByte4[88] = 23;
      arrayOfByte4[89] = 24;
      arrayOfByte4[90] = 25;
      arrayOfByte4[91] = -9;
      arrayOfByte4[92] = -9;
      arrayOfByte4[93] = -9;
      arrayOfByte4[94] = -9;
      arrayOfByte4[95] = 63;
      arrayOfByte4[96] = -9;
      arrayOfByte4[97] = 26;
      arrayOfByte4[98] = 27;
      arrayOfByte4[99] = 28;
      arrayOfByte4[100] = 29;
      arrayOfByte4[101] = 30;
      arrayOfByte4[102] = 31;
      arrayOfByte4[103] = 32;
      arrayOfByte4[104] = 33;
      arrayOfByte4[105] = 34;
      arrayOfByte4[106] = 35;
      arrayOfByte4[107] = 36;
      arrayOfByte4[108] = 37;
      arrayOfByte4[109] = 38;
      arrayOfByte4[110] = 39;
      arrayOfByte4[111] = 40;
      arrayOfByte4[112] = 41;
      arrayOfByte4[113] = 42;
      arrayOfByte4[114] = 43;
      arrayOfByte4[115] = 44;
      arrayOfByte4[116] = 45;
      arrayOfByte4[117] = 46;
      arrayOfByte4[118] = 47;
      arrayOfByte4[119] = 48;
      arrayOfByte4[120] = 49;
      arrayOfByte4[121] = 50;
      arrayOfByte4[122] = 51;
      arrayOfByte4[123] = -9;
      arrayOfByte4[124] = -9;
      arrayOfByte4[125] = -9;
      arrayOfByte4[126] = -9;
      arrayOfByte4[127] = -9;
      WEBSAFE_DECODABET = arrayOfByte4;
      return;
      b = 0;
    }
  }

  public static byte[] decode(String paramString)
    throws Base64DecoderException
  {
    byte[] arrayOfByte = paramString.getBytes();
    return decode(arrayOfByte, 0, arrayOfByte.length);
  }

  public static byte[] decode(byte[] paramArrayOfByte)
    throws Base64DecoderException
  {
    return decode(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public static byte[] decode(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws Base64DecoderException
  {
    return decode(paramArrayOfByte, paramInt1, paramInt2, DECODABET);
  }

  public static byte[] decode(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2)
    throws Base64DecoderException
  {
    byte b2;
    byte b4;
    byte[] arrayOfByte1 = new byte[2 + paramInt2 * 3 / 4];
    int i = 0;
    byte[] arrayOfByte2 = new byte[4];
    int j = 0;
    byte b1 = 0;
    if (j >= paramInt2);
    do
    {
      if (b1 == 0)
        break label375;
      if (b1 != 1)
        break label327;
      throw new Base64DecoderException("single trailing character at offset " + (paramInt2 - 1));
      b2 = (byte)(0x7F & paramArrayOfByte1[(j + paramInt1)]);
      byte b3 = paramArrayOfByte2[b2];
      if (b3 < -5)
        break label283;
      if (b3 < -1)
        break label381;
      if (b2 != 61)
        break label235;
      int l = paramInt2 - j;
      b4 = (byte)(0x7F & paramArrayOfByte1[(paramInt1 + paramInt2 - 1)]);
      if ((b1 == 0) || (b1 == 1))
        throw new Base64DecoderException("invalid padding byte '=' at byte offset " + j);
      if (((b1 == 3) && (l > 2)) || ((b1 == 4) && (l > 1)))
        throw new Base64DecoderException("padding byte '=' falsely signals end of encoded value at offset " + j);
    }
    while ((b4 == 61) || (b4 == 10));
    throw new Base64DecoderException("encoded value has invalid trailing byte");
    label235: int k = b1 + 1;
    arrayOfByte2[b1] = b2;
    if (k == 4)
    {
      i = i + decode4to3(arrayOfByte2, 0, arrayOfByte1, i, paramArrayOfByte2);
      k = 0;
    }
    while (true)
    {
      while (true)
      {
        ++j;
        b1 = k;
      }
      label283: throw new Base64DecoderException("Bad Base64 input character at " + j + ": " + paramArrayOfByte1[(j + paramInt1)] + "(decimal)");
      label327: (b1 + 1);
      arrayOfByte2[b1] = 61;
      i = i + decode4to3(arrayOfByte2, 0, arrayOfByte1, i, paramArrayOfByte2);
      while (true)
      {
        byte[] arrayOfByte3 = new byte[i];
        System.arraycopy(arrayOfByte1, 0, arrayOfByte3, 0, i);
        label375: return arrayOfByte3;
      }
      label381: k = b1;
    }
  }

  private static int decode4to3(byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2, byte[] paramArrayOfByte3)
  {
    byte b;
    if (paramArrayOfByte1[(paramInt1 + 2)] == 61)
    {
      paramArrayOfByte2[paramInt2] = (byte)((paramArrayOfByte3[paramArrayOfByte1[paramInt1]] << 24 >>> 6 | paramArrayOfByte3[paramArrayOfByte1[(paramInt1 + 1)]] << 24 >>> 12) >>> 16);
      b = 1;
    }
    while (true)
    {
      while (true)
      {
        return b;
        if (paramArrayOfByte1[(paramInt1 + 3)] != 61)
          break;
        int j = paramArrayOfByte3[paramArrayOfByte1[paramInt1]] << 24 >>> 6 | paramArrayOfByte3[paramArrayOfByte1[(paramInt1 + 1)]] << 24 >>> 12 | paramArrayOfByte3[paramArrayOfByte1[(paramInt1 + 2)]] << 24 >>> 18;
        paramArrayOfByte2[paramInt2] = (byte)(j >>> 16);
        paramArrayOfByte2[(paramInt2 + 1)] = (byte)(j >>> 8);
        b = 2;
      }
      int i = paramArrayOfByte3[paramArrayOfByte1[paramInt1]] << 24 >>> 6 | paramArrayOfByte3[paramArrayOfByte1[(paramInt1 + 1)]] << 24 >>> 12 | paramArrayOfByte3[paramArrayOfByte1[(paramInt1 + 2)]] << 24 >>> 18 | paramArrayOfByte3[paramArrayOfByte1[(paramInt1 + 3)]] << 24 >>> 24;
      paramArrayOfByte2[paramInt2] = (byte)(i >> 16);
      paramArrayOfByte2[(paramInt2 + 1)] = (byte)(i >> 8);
      paramArrayOfByte2[(paramInt2 + 2)] = (byte)i;
      b = 3;
    }
  }

  public static byte[] decodeWebSafe(String paramString)
    throws Base64DecoderException
  {
    byte[] arrayOfByte = paramString.getBytes();
    return decodeWebSafe(arrayOfByte, 0, arrayOfByte.length);
  }

  public static byte[] decodeWebSafe(byte[] paramArrayOfByte)
    throws Base64DecoderException
  {
    return decodeWebSafe(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public static byte[] decodeWebSafe(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws Base64DecoderException
  {
    return decode(paramArrayOfByte, paramInt1, paramInt2, WEBSAFE_DECODABET);
  }

  public static String encode(byte[] paramArrayOfByte)
  {
    return encode(paramArrayOfByte, 0, paramArrayOfByte.length, ALPHABET, true);
  }

  public static String encode(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2, boolean paramBoolean)
  {
    byte[] arrayOfByte = encode(paramArrayOfByte1, paramInt1, paramInt2, paramArrayOfByte2, 2147483647);
    int i = arrayOfByte.length;
    while (true)
    {
      if ((paramBoolean) || (i <= 0));
      do
        return new String(arrayOfByte, 0, i);
      while (arrayOfByte[(i - 1)] != 61);
      --i;
    }
  }

  public static byte[] encode(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2, int paramInt3)
  {
    int i = 4 * (paramInt2 + 2) / 3;
    byte[] arrayOfByte = new byte[i + i / paramInt3];
    int j = 0;
    int k = 0;
    int l = paramInt2 - 2;
    byte b = 0;
    while (true)
    {
      if (j >= l)
      {
        if (j < paramInt2)
        {
          encode3to4(paramArrayOfByte1, j + paramInt1, paramInt2 - j, arrayOfByte, k, paramArrayOfByte2);
          if (b + 4 == paramInt3)
          {
            arrayOfByte[(k + 4)] = 10;
            ++k;
          }
          k += 4;
        }
        if (($assertionsDisabled) || (k == arrayOfByte.length))
          break;
        throw new AssertionError();
      }
      int i1 = paramArrayOfByte1[(j + paramInt1)] << 24 >>> 8 | paramArrayOfByte1[(paramInt1 + j + 1)] << 24 >>> 16 | paramArrayOfByte1[(paramInt1 + j + 2)] << 24 >>> 24;
      arrayOfByte[k] = paramArrayOfByte2[(i1 >>> 18)];
      arrayOfByte[(k + 1)] = paramArrayOfByte2[(0x3F & i1 >>> 12)];
      arrayOfByte[(k + 2)] = paramArrayOfByte2[(0x3F & i1 >>> 6)];
      arrayOfByte[(k + 3)] = paramArrayOfByte2[(i1 & 0x3F)];
      if ((b += 4) == paramInt3)
      {
        arrayOfByte[(k + 4)] = 10;
        ++k;
        b = 0;
      }
      j += 3;
      k += 4;
    }
    return arrayOfByte;
  }

  private static byte[] encode3to4(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2, int paramInt3, byte[] paramArrayOfByte3)
  {
    int j;
    int k;
    int i1;
    int i = 0;
    if (paramInt2 > 0)
    {
      j = paramArrayOfByte1[paramInt1] << 24 >>> 8;
      if (paramInt2 <= 1)
        break label104;
      k = paramArrayOfByte1[(paramInt1 + 1)] << 24 >>> 16;
      int l = k | j;
      if (paramInt2 > 2)
        i = paramArrayOfByte1[(paramInt1 + 2)] << 24 >>> 24;
      i1 = l | i;
      switch (paramInt2)
      {
      default:
      case 3:
      case 2:
      case 1:
      }
    }
    while (true)
    {
      while (true)
      {
        while (true)
        {
          while (true)
          {
            while (true)
            {
              return paramArrayOfByte2;
              j = 0;
            }
            label104: k = 0;
          }
          paramArrayOfByte2[paramInt3] = paramArrayOfByte3[(i1 >>> 18)];
          paramArrayOfByte2[(paramInt3 + 1)] = paramArrayOfByte3[(0x3F & i1 >>> 12)];
          paramArrayOfByte2[(paramInt3 + 2)] = paramArrayOfByte3[(0x3F & i1 >>> 6)];
          paramArrayOfByte2[(paramInt3 + 3)] = paramArrayOfByte3[(i1 & 0x3F)];
        }
        paramArrayOfByte2[paramInt3] = paramArrayOfByte3[(i1 >>> 18)];
        paramArrayOfByte2[(paramInt3 + 1)] = paramArrayOfByte3[(0x3F & i1 >>> 12)];
        paramArrayOfByte2[(paramInt3 + 2)] = paramArrayOfByte3[(0x3F & i1 >>> 6)];
        paramArrayOfByte2[(paramInt3 + 3)] = 61;
      }
      paramArrayOfByte2[paramInt3] = paramArrayOfByte3[(i1 >>> 18)];
      paramArrayOfByte2[(paramInt3 + 1)] = paramArrayOfByte3[(0x3F & i1 >>> 12)];
      paramArrayOfByte2[(paramInt3 + 2)] = 61;
      paramArrayOfByte2[(paramInt3 + 3)] = 61;
    }
  }

  public static String encodeWebSafe(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    return encode(paramArrayOfByte, 0, paramArrayOfByte.length, WEBSAFE_ALPHABET, paramBoolean);
  }
}