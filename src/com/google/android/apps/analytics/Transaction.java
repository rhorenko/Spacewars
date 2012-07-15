package com.google.android.apps.analytics;

public class Transaction
{
  private final String orderId;
  private final double shippingCost;
  private final String storeName;
  private final double totalCost;
  private final double totalTax;

  private Transaction(Builder paramBuilder)
  {
    this.orderId = Builder.access$000(paramBuilder);
    this.totalCost = Builder.access$100(paramBuilder);
    this.storeName = Builder.access$200(paramBuilder);
    this.totalTax = Builder.access$300(paramBuilder);
    this.shippingCost = Builder.access$400(paramBuilder);
  }

  String getOrderId()
  {
    return this.orderId;
  }

  double getShippingCost()
  {
    return this.shippingCost;
  }

  String getStoreName()
  {
    return this.storeName;
  }

  double getTotalCost()
  {
    return this.totalCost;
  }

  double getTotalTax()
  {
    return this.totalTax;
  }

  public static class Builder
  {
    private final String orderId;
    private double shippingCost;
    private String storeName = null;
    private final double totalCost;
    private double totalTax = 0D;

    public Builder(String paramString, double paramDouble)
    {
      this.shippingCost = 0D;
      if ((paramString == null) || (paramString.trim().length() == 0))
        throw new IllegalArgumentException("orderId must not be empty or null");
      this.orderId = paramString;
      this.totalCost = paramDouble;
    }

    public Transaction build()
    {
      return new Transaction(this, null);
    }

    public Builder setShippingCost(double paramDouble)
    {
      this.shippingCost = paramDouble;
      return this;
    }

    public Builder setStoreName(String paramString)
    {
      this.shippingCost = paramString;
      return this;
    }

    public Builder setTotalTax(double paramDouble)
    {
      this.totalTax = paramDouble;
      return this;
    }
  }
}