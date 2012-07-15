package com.google.android.apps.analytics;

public class Item
{
  private final String itemCategory;
  private final long itemCount;
  private final String itemName;
  private final double itemPrice;
  private final String itemSKU;
  private final String orderId;

  private Item(Builder paramBuilder)
  {
    this.orderId = Builder.access$000(paramBuilder);
    this.itemSKU = Builder.access$100(paramBuilder);
    this.itemPrice = Builder.access$200(paramBuilder);
    this.itemCount = Builder.access$300(paramBuilder);
    this.itemName = Builder.access$400(paramBuilder);
    this.itemCategory = Builder.access$500(paramBuilder);
  }

  String getItemCategory()
  {
    return this.itemCategory;
  }

  long getItemCount()
  {
    return this.itemCount;
  }

  String getItemName()
  {
    return this.itemName;
  }

  double getItemPrice()
  {
    return this.itemPrice;
  }

  String getItemSKU()
  {
    return this.itemSKU;
  }

  String getOrderId()
  {
    return this.orderId;
  }

  public static class Builder
  {
    private String itemCategory;
    private final long itemCount;
    private String itemName = null;
    private final double itemPrice;
    private final String itemSKU;
    private final String orderId;

    public Builder(String paramString1, String paramString2, double paramDouble, long paramLong)
    {
      this.itemCategory = null;
      if ((paramString1 == null) || (paramString1.trim().length() == 0))
        throw new IllegalArgumentException("orderId must not be empty or null");
      if ((paramString2 == null) || (paramString2.trim().length() == 0))
        throw new IllegalArgumentException("itemSKU must not be empty or null");
      this.orderId = paramString1;
      this.itemSKU = paramString2;
      this.itemPrice = paramDouble;
      this.itemCount = paramLong;
    }

    public Item build()
    {
      return new Item(this, null);
    }

    public Builder setItemCategory(String paramString)
    {
      this.itemCategory = paramString;
      return this;
    }

    public Builder setItemName(String paramString)
    {
      this.itemCategory = paramString;
      return this;
    }
  }
}