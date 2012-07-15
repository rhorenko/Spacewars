package com.google.ads.searchads;

import android.content.Context;
import android.graphics.Color;
import android.util.Pair;
import com.google.ads.AdRequest;
import java.util.Map;

public class SearchAdRequest extends AdRequest
{
  private String a;
  private int b;
  private Pair<Integer, Integer> c;
  private int d;
  private int e;
  private int f;
  private String g;
  private int h;
  private int i;
  private BorderType j;
  private int k;

  private static String a(int paramInt)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(0xFFFFFF & paramInt);
    return String.format("#%06x", arrayOfObject);
  }

  public Map<String, Object> getRequestMap(Context paramContext)
  {
    if (this.a != null)
      addExtra("q", this.a);
    if (Color.alpha(this.b) != 0)
      addExtra("bgcolor", a(this.b));
    if ((this.c != null) && (this.c.first != null) && (this.c.second != null))
    {
      addExtra("gradientfrom", a(((Integer)this.c.first).intValue()));
      addExtra("gradientto", a(((Integer)this.c.second).intValue()));
    }
    if (Color.alpha(this.d) != 0)
      addExtra("hcolor", a(this.d));
    if (Color.alpha(this.e) != 0)
      addExtra("dcolor", a(this.e));
    if (Color.alpha(this.f) != 0)
      addExtra("acolor", a(this.f));
    if (this.g != null)
      addExtra("font", this.g);
    addExtra("headersize", Integer.toString(this.h));
    if (Color.alpha(this.i) != 0)
      addExtra("bcolor", a(this.i));
    if (this.j != null)
      addExtra("btype", this.j.toString());
    addExtra("bthick", Integer.toString(this.k));
    return super.getRequestMap(paramContext);
  }

  public void setAnchorTextColor(int paramInt)
  {
    this.f = paramInt;
  }

  public void setBackgroundColor(int paramInt)
  {
    if (Color.alpha(paramInt) == 255)
    {
      this.b = paramInt;
      this.c = null;
    }
  }

  public void setBackgroundGradient(int paramInt1, int paramInt2)
  {
    if ((Color.alpha(paramInt1) == 255) && (Color.alpha(paramInt2) == 255))
    {
      this.b = Color.argb(0, 0, 0, 0);
      this.c = Pair.create(new Integer(paramInt1), new Integer(paramInt2));
    }
  }

  public void setBorderColor(int paramInt)
  {
    this.i = paramInt;
  }

  public void setBorderThickness(int paramInt)
  {
    this.k = paramInt;
  }

  public void setBorderType(BorderType paramBorderType)
  {
    this.j = paramBorderType;
  }

  public void setDescriptionTextColor(int paramInt)
  {
    this.e = paramInt;
  }

  public void setFontFace(String paramString)
  {
    this.g = paramString;
  }

  public void setHeaderTextColor(int paramInt)
  {
    this.d = paramInt;
  }

  public void setHeaderTextSize(int paramInt)
  {
    this.h = paramInt;
  }

  public void setQuery(String paramString)
  {
    this.a = paramString;
  }

  public static enum BorderType
  {
    private String a;

    static
    {
      DASHED = new BorderType("DASHED", 1, "dashed");
      DOTTED = new BorderType("DOTTED", 2, "dotted");
      SOLID = new BorderType("SOLID", 3, "solid");
      BorderType[] arrayOfBorderType = new BorderType[4];
      arrayOfBorderType[0] = NONE;
      arrayOfBorderType[1] = DASHED;
      arrayOfBorderType[2] = DOTTED;
      arrayOfBorderType[3] = SOLID;
      b = arrayOfBorderType;
    }

    public final String toString()
    {
      return this.a;
    }
  }
}