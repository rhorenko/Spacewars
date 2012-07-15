package com.bestcoolfungames.antsmasher.inapp;

public class Consts
{
  public static final String ACTION_CONFIRM_NOTIFICATION = "com.bestcoolfungames.antsmasher.inapp.CONFIRM_NOTIFICATION";
  public static final String ACTION_GET_PURCHASE_INFORMATION = "com.bestcoolfungames.antsmasher.inapp.GET_PURCHASE_INFORMATION";
  public static final String ACTION_NOTIFY = "com.android.vending.billing.IN_APP_NOTIFY";
  public static final String ACTION_PURCHASE_STATE_CHANGED = "com.android.vending.billing.PURCHASE_STATE_CHANGED";
  public static final String ACTION_RESPONSE_CODE = "com.android.vending.billing.RESPONSE_CODE";
  public static final String ACTION_RESTORE_TRANSACTIONS = "com.bestcoolfungames.antsmasher.inapp.RESTORE_TRANSACTIONS";
  public static final String BILLING_REQUEST_API_VERSION = "API_VERSION";
  public static final String BILLING_REQUEST_DEVELOPER_PAYLOAD = "DEVELOPER_PAYLOAD";
  public static final String BILLING_REQUEST_ITEM_ID = "ITEM_ID";
  public static final String BILLING_REQUEST_METHOD = "BILLING_REQUEST";
  public static final String BILLING_REQUEST_NONCE = "NONCE";
  public static final String BILLING_REQUEST_NOTIFY_IDS = "NOTIFY_IDS";
  public static final String BILLING_REQUEST_PACKAGE_NAME = "PACKAGE_NAME";
  public static long BILLING_RESPONSE_INVALID_REQUEST_ID = 0;
  public static final String BILLING_RESPONSE_PURCHASE_INTENT = "PURCHASE_INTENT";
  public static final String BILLING_RESPONSE_REQUEST_ID = "REQUEST_ID";
  public static final String BILLING_RESPONSE_RESPONSE_CODE = "RESPONSE_CODE";
  public static final boolean DEBUG = 0;
  public static final String INAPP_REQUEST_ID = "request_id";
  public static final String INAPP_RESPONSE_CODE = "response_code";
  public static final String INAPP_SIGNATURE = "inapp_signature";
  public static final String INAPP_SIGNED_DATA = "inapp_signed_data";
  public static final String MARKET_BILLING_SERVICE_ACTION = "com.android.vending.billing.MarketBillingService.BIND";
  public static final String NOTIFICATION_ID = "notification_id";

  public static enum PurchaseState
  {
    static
    {
      CANCELED = new PurchaseState("CANCELED", 1);
      REFUNDED = new PurchaseState("REFUNDED", 2);
      PurchaseState[] arrayOfPurchaseState = new PurchaseState[3];
      arrayOfPurchaseState[0] = PURCHASED;
      arrayOfPurchaseState[1] = CANCELED;
      arrayOfPurchaseState[2] = REFUNDED;
      ENUM$VALUES = arrayOfPurchaseState;
    }

    public static PurchaseState valueOf(int paramInt)
    {
      PurchaseState localPurchaseState;
      PurchaseState[] arrayOfPurchaseState = values();
      if ((paramInt < 0) || (paramInt >= arrayOfPurchaseState.length))
        localPurchaseState = CANCELED;
      while (true)
      {
        return localPurchaseState;
        localPurchaseState = arrayOfPurchaseState[paramInt];
      }
    }
  }

  public static enum ResponseCode
  {
    static
    {
      RESULT_SERVICE_UNAVAILABLE = new ResponseCode("RESULT_SERVICE_UNAVAILABLE", 2);
      RESULT_BILLING_UNAVAILABLE = new ResponseCode("RESULT_BILLING_UNAVAILABLE", 3);
      RESULT_ITEM_UNAVAILABLE = new ResponseCode("RESULT_ITEM_UNAVAILABLE", 4);
      RESULT_DEVELOPER_ERROR = new ResponseCode("RESULT_DEVELOPER_ERROR", 5);
      RESULT_ERROR = new ResponseCode("RESULT_ERROR", 6);
      ResponseCode[] arrayOfResponseCode = new ResponseCode[7];
      arrayOfResponseCode[0] = RESULT_OK;
      arrayOfResponseCode[1] = RESULT_USER_CANCELED;
      arrayOfResponseCode[2] = RESULT_SERVICE_UNAVAILABLE;
      arrayOfResponseCode[3] = RESULT_BILLING_UNAVAILABLE;
      arrayOfResponseCode[4] = RESULT_ITEM_UNAVAILABLE;
      arrayOfResponseCode[5] = RESULT_DEVELOPER_ERROR;
      arrayOfResponseCode[6] = RESULT_ERROR;
      ENUM$VALUES = arrayOfResponseCode;
    }

    public static ResponseCode valueOf(int paramInt)
    {
      ResponseCode localResponseCode;
      ResponseCode[] arrayOfResponseCode = values();
      if ((paramInt < 0) || (paramInt >= arrayOfResponseCode.length))
        localResponseCode = RESULT_ERROR;
      while (true)
      {
        return localResponseCode;
        localResponseCode = arrayOfResponseCode[paramInt];
      }
    }
  }
}