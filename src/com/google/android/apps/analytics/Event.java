package com.google.android.apps.analytics;

class Event
{
  static final String INSTALL_EVENT_CATEGORY = "__##GOOGLEINSTALL##__";
  static final String ITEM_CATEGORY = "__##GOOGLEITEM##__";
  static final String PAGEVIEW_EVENT_CATEGORY = "__##GOOGLEPAGEVIEW##__";
  static final String TRANSACTION_CATEGORY = "__##GOOGLETRANSACTION##__";
  final String accountId;
  final String action;
  final String category;
  CustomVariableBuffer customVariableBuffer;
  final long eventId;
  private Item item;
  final String label;
  final int randomVal;
  final int screenHeight;
  final int screenWidth;
  final int timestampCurrent;
  final int timestampFirst;
  final int timestampPrevious;
  private Transaction transaction;
  final int userId;
  final int value;
  final int visits;

  Event(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt2, int paramInt3, int paramInt4)
  {
    this(-1, paramInt1, paramString1, -1, -1, -1, -1, -1, paramString2, paramString3, paramString4, paramInt2, paramInt3, paramInt4);
  }

  Event(long paramLong, int paramInt1, String paramString1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, String paramString2, String paramString3, String paramString4, int paramInt7, int paramInt8, int paramInt9)
  {
    this.eventId = paramLong;
    this.userId = paramInt1;
    this.accountId = paramString1;
    this.randomVal = paramInt2;
    this.timestampFirst = paramInt3;
    this.timestampPrevious = paramInt4;
    this.timestampCurrent = paramInt5;
    this.visits = paramInt6;
    this.category = paramString2;
    this.action = paramString3;
    this.label = paramString4;
    this.value = paramInt7;
    this.screenHeight = paramInt9;
    this.screenWidth = paramInt8;
  }

  public CustomVariableBuffer getCustomVariableBuffer()
  {
    return this.customVariableBuffer;
  }

  public Item getItem()
  {
    return this.item;
  }

  public Transaction getTransaction()
  {
    return this.transaction;
  }

  public void setCustomVariableBuffer(CustomVariableBuffer paramCustomVariableBuffer)
  {
    this.customVariableBuffer = paramCustomVariableBuffer;
  }

  public void setItem(Item paramItem)
  {
    if (!(this.category.equals("__##GOOGLEITEM##__")))
      throw new IllegalStateException("Attempted to add an item to an event of type " + this.category);
    this.item = paramItem;
  }

  public void setTransaction(Transaction paramTransaction)
  {
    if (!(this.category.equals("__##GOOGLETRANSACTION##__")))
      throw new IllegalStateException("Attempted to add a transction to an event of type " + this.category);
    this.transaction = paramTransaction;
  }

  public String toString()
  {
    return "id:" + this.eventId + " " + "random:" + this.randomVal + " " + "timestampCurrent:" + this.timestampCurrent + " " + "timestampPrevious:" + this.timestampPrevious + " " + "timestampFirst:" + this.timestampFirst + " " + "visits:" + this.visits + " " + "value:" + this.value + " " + "category:" + this.category + " " + "action:" + this.action + " " + "label:" + this.label + " " + "width:" + this.screenWidth + " " + "height:" + this.screenHeight;
  }
}