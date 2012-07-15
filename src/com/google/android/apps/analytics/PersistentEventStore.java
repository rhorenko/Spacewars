package com.google.android.apps.analytics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

class PersistentEventStore
  implements EventStore
{
  private static final String ACCOUNT_ID = "account_id";
  private static final String ACTION = "action";
  private static final String CATEGORY = "category";
  private static final String CREATE_CUSTOM_VARIABLES_TABLE;
  private static final String CREATE_CUSTOM_VAR_CACHE_TABLE;
  private static final String CREATE_EVENTS_TABLE;
  private static final String CREATE_INSTALL_REFERRER_TABLE = "CREATE TABLE install_referrer (referrer TEXT PRIMARY KEY NOT NULL);";
  private static final String CREATE_ITEM_EVENTS_TABLE;
  private static final String CREATE_SESSION_TABLE;
  private static final String CREATE_TRANSACTION_EVENTS_TABLE;
  private static final String CUSTOMVAR_ID = "cv_id";
  private static final String CUSTOMVAR_INDEX = "cv_index";
  private static final String CUSTOMVAR_NAME = "cv_name";
  private static final String CUSTOMVAR_SCOPE = "cv_scope";
  private static final String CUSTOMVAR_VALUE = "cv_value";
  private static final String CUSTOM_VARIABLE_COLUMN_TYPE = "CHAR(64) NOT NULL";
  private static final String DATABASE_NAME = "google_analytics.db";
  private static final int DATABASE_VERSION = 3;
  private static final String EVENT_ID = "event_id";
  private static final String ITEM_CATEGORY = "item_category";
  private static final String ITEM_COUNT = "item_count";
  private static final String ITEM_ID = "item_id";
  private static final String ITEM_NAME = "item_name";
  private static final String ITEM_PRICE = "item_price";
  private static final String ITEM_SKU = "item_sku";
  private static final String LABEL = "label";
  private static final int MAX_EVENTS = 1000;
  private static final String ORDER_ID = "order_id";
  private static final String RANDOM_VAL = "random_val";
  private static final String REFERRER = "referrer";
  private static final String SCREEN_HEIGHT = "screen_height";
  private static final String SCREEN_WIDTH = "screen_width";
  private static final String SHIPPING_COST = "tran_shippingcost";
  private static final String STORE_ID = "store_id";
  private static final String STORE_NAME = "tran_storename";
  private static final String TIMESTAMP_CURRENT = "timestamp_current";
  private static final String TIMESTAMP_FIRST = "timestamp_first";
  private static final String TIMESTAMP_PREVIOUS = "timestamp_previous";
  private static final String TOTAL_COST = "tran_totalcost";
  private static final String TOTAL_TAX = "tran_totaltax";
  private static final String TRANSACTION_ID = "tran_id";
  private static final String USER_ID = "user_id";
  private static final String VALUE = "value";
  private static final String VISITS = "visits";
  private SQLiteStatement compiledCountStatement = null;
  private DataBaseHelper databaseHelper;
  private int numStoredEvents;
  private boolean sessionUpdated;
  private int storeId;
  private long timestampCurrent;
  private long timestampFirst;
  private long timestampPrevious;
  private boolean useStoredVisitorVars;
  private int visits;

  static
  {
    StringBuilder localStringBuilder1 = new StringBuilder().append("CREATE TABLE events (");
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = "event_id";
    StringBuilder localStringBuilder2 = localStringBuilder1.append(String.format(" '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,", arrayOfObject1));
    Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = "user_id";
    StringBuilder localStringBuilder3 = localStringBuilder2.append(String.format(" '%s' INTEGER NOT NULL,", arrayOfObject2));
    Object[] arrayOfObject3 = new Object[1];
    arrayOfObject3[0] = "account_id";
    StringBuilder localStringBuilder4 = localStringBuilder3.append(String.format(" '%s' CHAR(256) NOT NULL,", arrayOfObject3));
    Object[] arrayOfObject4 = new Object[1];
    arrayOfObject4[0] = "random_val";
    StringBuilder localStringBuilder5 = localStringBuilder4.append(String.format(" '%s' INTEGER NOT NULL,", arrayOfObject4));
    Object[] arrayOfObject5 = new Object[1];
    arrayOfObject5[0] = "timestamp_first";
    StringBuilder localStringBuilder6 = localStringBuilder5.append(String.format(" '%s' INTEGER NOT NULL,", arrayOfObject5));
    Object[] arrayOfObject6 = new Object[1];
    arrayOfObject6[0] = "timestamp_previous";
    StringBuilder localStringBuilder7 = localStringBuilder6.append(String.format(" '%s' INTEGER NOT NULL,", arrayOfObject6));
    Object[] arrayOfObject7 = new Object[1];
    arrayOfObject7[0] = "timestamp_current";
    StringBuilder localStringBuilder8 = localStringBuilder7.append(String.format(" '%s' INTEGER NOT NULL,", arrayOfObject7));
    Object[] arrayOfObject8 = new Object[1];
    arrayOfObject8[0] = "visits";
    StringBuilder localStringBuilder9 = localStringBuilder8.append(String.format(" '%s' INTEGER NOT NULL,", arrayOfObject8));
    Object[] arrayOfObject9 = new Object[1];
    arrayOfObject9[0] = "category";
    StringBuilder localStringBuilder10 = localStringBuilder9.append(String.format(" '%s' CHAR(256) NOT NULL,", arrayOfObject9));
    Object[] arrayOfObject10 = new Object[1];
    arrayOfObject10[0] = "action";
    StringBuilder localStringBuilder11 = localStringBuilder10.append(String.format(" '%s' CHAR(256) NOT NULL,", arrayOfObject10));
    Object[] arrayOfObject11 = new Object[1];
    arrayOfObject11[0] = "label";
    StringBuilder localStringBuilder12 = localStringBuilder11.append(String.format(" '%s' CHAR(256), ", arrayOfObject11));
    Object[] arrayOfObject12 = new Object[1];
    arrayOfObject12[0] = "value";
    StringBuilder localStringBuilder13 = localStringBuilder12.append(String.format(" '%s' INTEGER,", arrayOfObject12));
    Object[] arrayOfObject13 = new Object[1];
    arrayOfObject13[0] = "screen_width";
    StringBuilder localStringBuilder14 = localStringBuilder13.append(String.format(" '%s' INTEGER,", arrayOfObject13));
    Object[] arrayOfObject14 = new Object[1];
    arrayOfObject14[0] = "screen_height";
    CREATE_EVENTS_TABLE = String.format(" '%s' INTEGER);", arrayOfObject14);
    StringBuilder localStringBuilder15 = new StringBuilder().append("CREATE TABLE session (");
    Object[] arrayOfObject15 = new Object[1];
    arrayOfObject15[0] = "timestamp_first";
    StringBuilder localStringBuilder16 = localStringBuilder15.append(String.format(" '%s' INTEGER PRIMARY KEY,", arrayOfObject15));
    Object[] arrayOfObject16 = new Object[1];
    arrayOfObject16[0] = "timestamp_previous";
    StringBuilder localStringBuilder17 = localStringBuilder16.append(String.format(" '%s' INTEGER NOT NULL,", arrayOfObject16));
    Object[] arrayOfObject17 = new Object[1];
    arrayOfObject17[0] = "timestamp_current";
    StringBuilder localStringBuilder18 = localStringBuilder17.append(String.format(" '%s' INTEGER NOT NULL,", arrayOfObject17));
    Object[] arrayOfObject18 = new Object[1];
    arrayOfObject18[0] = "visits";
    StringBuilder localStringBuilder19 = localStringBuilder18.append(String.format(" '%s' INTEGER NOT NULL,", arrayOfObject18));
    Object[] arrayOfObject19 = new Object[1];
    arrayOfObject19[0] = "store_id";
    CREATE_SESSION_TABLE = String.format(" '%s' INTEGER NOT NULL);", arrayOfObject19);
    StringBuilder localStringBuilder20 = new StringBuilder().append("CREATE TABLE custom_variables (");
    Object[] arrayOfObject20 = new Object[1];
    arrayOfObject20[0] = "cv_id";
    StringBuilder localStringBuilder21 = localStringBuilder20.append(String.format(" '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,", arrayOfObject20));
    Object[] arrayOfObject21 = new Object[1];
    arrayOfObject21[0] = "event_id";
    StringBuilder localStringBuilder22 = localStringBuilder21.append(String.format(" '%s' INTEGER NOT NULL,", arrayOfObject21));
    Object[] arrayOfObject22 = new Object[1];
    arrayOfObject22[0] = "cv_index";
    StringBuilder localStringBuilder23 = localStringBuilder22.append(String.format(" '%s' INTEGER NOT NULL,", arrayOfObject22));
    Object[] arrayOfObject23 = new Object[1];
    arrayOfObject23[0] = "cv_name";
    StringBuilder localStringBuilder24 = localStringBuilder23.append(String.format(" '%s' CHAR(64) NOT NULL,", arrayOfObject23));
    Object[] arrayOfObject24 = new Object[1];
    arrayOfObject24[0] = "cv_value";
    StringBuilder localStringBuilder25 = localStringBuilder24.append(String.format(" '%s' CHAR(64) NOT NULL,", arrayOfObject24));
    Object[] arrayOfObject25 = new Object[1];
    arrayOfObject25[0] = "cv_scope";
    CREATE_CUSTOM_VARIABLES_TABLE = String.format(" '%s' INTEGER NOT NULL);", arrayOfObject25);
    StringBuilder localStringBuilder26 = new StringBuilder().append("CREATE TABLE custom_var_cache (");
    Object[] arrayOfObject26 = new Object[1];
    arrayOfObject26[0] = "cv_id";
    StringBuilder localStringBuilder27 = localStringBuilder26.append(String.format(" '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,", arrayOfObject26));
    Object[] arrayOfObject27 = new Object[1];
    arrayOfObject27[0] = "event_id";
    StringBuilder localStringBuilder28 = localStringBuilder27.append(String.format(" '%s' INTEGER NOT NULL,", arrayOfObject27));
    Object[] arrayOfObject28 = new Object[1];
    arrayOfObject28[0] = "cv_index";
    StringBuilder localStringBuilder29 = localStringBuilder28.append(String.format(" '%s' INTEGER NOT NULL,", arrayOfObject28));
    Object[] arrayOfObject29 = new Object[1];
    arrayOfObject29[0] = "cv_name";
    StringBuilder localStringBuilder30 = localStringBuilder29.append(String.format(" '%s' CHAR(64) NOT NULL,", arrayOfObject29));
    Object[] arrayOfObject30 = new Object[1];
    arrayOfObject30[0] = "cv_value";
    StringBuilder localStringBuilder31 = localStringBuilder30.append(String.format(" '%s' CHAR(64) NOT NULL,", arrayOfObject30));
    Object[] arrayOfObject31 = new Object[1];
    arrayOfObject31[0] = "cv_scope";
    CREATE_CUSTOM_VAR_CACHE_TABLE = String.format(" '%s' INTEGER NOT NULL);", arrayOfObject31);
    StringBuilder localStringBuilder32 = new StringBuilder().append("CREATE TABLE transaction_events (");
    Object[] arrayOfObject32 = new Object[1];
    arrayOfObject32[0] = "tran_id";
    StringBuilder localStringBuilder33 = localStringBuilder32.append(String.format(" '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,", arrayOfObject32));
    Object[] arrayOfObject33 = new Object[1];
    arrayOfObject33[0] = "event_id";
    StringBuilder localStringBuilder34 = localStringBuilder33.append(String.format(" '%s' INTEGER NOT NULL,", arrayOfObject33));
    Object[] arrayOfObject34 = new Object[1];
    arrayOfObject34[0] = "order_id";
    StringBuilder localStringBuilder35 = localStringBuilder34.append(String.format(" '%s' TEXT NOT NULL,", arrayOfObject34));
    Object[] arrayOfObject35 = new Object[1];
    arrayOfObject35[0] = "tran_storename";
    StringBuilder localStringBuilder36 = localStringBuilder35.append(String.format(" '%s' TEXT,", arrayOfObject35));
    Object[] arrayOfObject36 = new Object[1];
    arrayOfObject36[0] = "tran_totalcost";
    StringBuilder localStringBuilder37 = localStringBuilder36.append(String.format(" '%s' TEXT NOT NULL,", arrayOfObject36));
    Object[] arrayOfObject37 = new Object[1];
    arrayOfObject37[0] = "tran_totaltax";
    StringBuilder localStringBuilder38 = localStringBuilder37.append(String.format(" '%s' TEXT,", arrayOfObject37));
    Object[] arrayOfObject38 = new Object[1];
    arrayOfObject38[0] = "tran_shippingcost";
    CREATE_TRANSACTION_EVENTS_TABLE = String.format(" '%s' TEXT);", arrayOfObject38);
    StringBuilder localStringBuilder39 = new StringBuilder().append("CREATE TABLE item_events (");
    Object[] arrayOfObject39 = new Object[1];
    arrayOfObject39[0] = "item_id";
    StringBuilder localStringBuilder40 = localStringBuilder39.append(String.format(" '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,", arrayOfObject39));
    Object[] arrayOfObject40 = new Object[1];
    arrayOfObject40[0] = "event_id";
    StringBuilder localStringBuilder41 = localStringBuilder40.append(String.format(" '%s' INTEGER NOT NULL,", arrayOfObject40));
    Object[] arrayOfObject41 = new Object[1];
    arrayOfObject41[0] = "order_id";
    StringBuilder localStringBuilder42 = localStringBuilder41.append(String.format(" '%s' TEXT NOT NULL,", arrayOfObject41));
    Object[] arrayOfObject42 = new Object[1];
    arrayOfObject42[0] = "item_sku";
    StringBuilder localStringBuilder43 = localStringBuilder42.append(String.format(" '%s' TEXT NOT NULL,", arrayOfObject42));
    Object[] arrayOfObject43 = new Object[1];
    arrayOfObject43[0] = "item_name";
    StringBuilder localStringBuilder44 = localStringBuilder43.append(String.format(" '%s' TEXT,", arrayOfObject43));
    Object[] arrayOfObject44 = new Object[1];
    arrayOfObject44[0] = "item_category";
    StringBuilder localStringBuilder45 = localStringBuilder44.append(String.format(" '%s' TEXT,", arrayOfObject44));
    Object[] arrayOfObject45 = new Object[1];
    arrayOfObject45[0] = "item_price";
    StringBuilder localStringBuilder46 = localStringBuilder45.append(String.format(" '%s' TEXT NOT NULL,", arrayOfObject45));
    Object[] arrayOfObject46 = new Object[1];
    arrayOfObject46[0] = "item_count";
    CREATE_ITEM_EVENTS_TABLE = String.format(" '%s' TEXT NOT NULL);", arrayOfObject46);
  }

  PersistentEventStore(DataBaseHelper paramDataBaseHelper)
  {
    this.databaseHelper = paramDataBaseHelper;
    try
    {
      paramDataBaseHelper.getWritableDatabase().close();
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      while (true)
        Log.e("GoogleAnalyticsTracker", localSQLiteException.toString());
    }
  }

  public void deleteEvent(long paramLong)
  {
    String str = "event_id=" + paramLong;
    try
    {
      SQLiteDatabase localSQLiteDatabase = this.databaseHelper.getWritableDatabase();
      if (localSQLiteDatabase.delete("events", str, null) != 0)
      {
        this.numStoredEvents = (-1 + this.numStoredEvents);
        localSQLiteDatabase.delete("custom_variables", str, null);
        localSQLiteDatabase.delete("transaction_events", str, null);
        localSQLiteDatabase.delete("item_events", str, null);
      }
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      while (true)
        Log.e("GoogleAnalyticsTracker", localSQLiteException.toString());
    }
  }

  // ERROR //
  CustomVariableBuffer getCustomVariables(long paramLong)
  {
    // Byte code:
    //   0: new 270	com/google/android/apps/analytics/CustomVariableBuffer
    //   3: dup
    //   4: invokespecial 271	com/google/android/apps/analytics/CustomVariableBuffer:<init>	()V
    //   7: astore_3
    //   8: aload_0
    //   9: getfield 221	com/google/android/apps/analytics/PersistentEventStore:databaseHelper	Lcom/google/android/apps/analytics/PersistentEventStore$DataBaseHelper;
    //   12: invokevirtual 274	com/google/android/apps/analytics/PersistentEventStore$DataBaseHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   15: ldc_w 262
    //   18: aconst_null
    //   19: new 146	java/lang/StringBuilder
    //   22: dup
    //   23: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   26: ldc 249
    //   28: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: lload_1
    //   32: invokevirtual 252	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   35: invokevirtual 177	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   38: aconst_null
    //   39: aconst_null
    //   40: aconst_null
    //   41: aconst_null
    //   42: invokevirtual 278	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   45: astore 8
    //   47: aload 8
    //   49: astore 5
    //   51: aload 5
    //   53: invokeinterface 284 1 0
    //   58: ifeq +108 -> 166
    //   61: aload_3
    //   62: new 286	com/google/android/apps/analytics/CustomVariable
    //   65: dup
    //   66: aload 5
    //   68: aload 5
    //   70: ldc 34
    //   72: invokeinterface 290 2 0
    //   77: invokeinterface 294 2 0
    //   82: aload 5
    //   84: aload 5
    //   86: ldc 37
    //   88: invokeinterface 290 2 0
    //   93: invokeinterface 298 2 0
    //   98: aload 5
    //   100: aload 5
    //   102: ldc 43
    //   104: invokeinterface 290 2 0
    //   109: invokeinterface 298 2 0
    //   114: aload 5
    //   116: aload 5
    //   118: ldc 40
    //   120: invokeinterface 290 2 0
    //   125: invokeinterface 294 2 0
    //   130: invokespecial 301	com/google/android/apps/analytics/CustomVariable:<init>	(ILjava/lang/String;Ljava/lang/String;I)V
    //   133: invokevirtual 305	com/google/android/apps/analytics/CustomVariableBuffer:setCustomVariable	(Lcom/google/android/apps/analytics/CustomVariable;)V
    //   136: goto -85 -> 51
    //   139: astore 4
    //   141: ldc 232
    //   143: aload 4
    //   145: invokevirtual 233	android/database/sqlite/SQLiteException:toString	()Ljava/lang/String;
    //   148: invokestatic 239	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   151: pop
    //   152: aload 5
    //   154: ifnull +10 -> 164
    //   157: aload 5
    //   159: invokeinterface 306 1 0
    //   164: aload_3
    //   165: areturn
    //   166: aload 5
    //   168: ifnull -4 -> 164
    //   171: aload 5
    //   173: invokeinterface 306 1 0
    //   178: goto -14 -> 164
    //   181: astore 6
    //   183: aconst_null
    //   184: astore 5
    //   186: aload 5
    //   188: ifnull +10 -> 198
    //   191: aload 5
    //   193: invokeinterface 306 1 0
    //   198: aload 6
    //   200: athrow
    //   201: astore 6
    //   203: goto -17 -> 186
    //   206: astore 4
    //   208: aconst_null
    //   209: astore 5
    //   211: goto -70 -> 141
    //
    // Exception table:
    //   from	to	target	type
    //   51	136	139	android/database/sqlite/SQLiteException
    //   8	47	181	finally
    //   51	136	201	finally
    //   141	152	201	finally
    //   8	47	206	android/database/sqlite/SQLiteException
  }

  // ERROR //
  Item getItem(long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 221	com/google/android/apps/analytics/PersistentEventStore:databaseHelper	Lcom/google/android/apps/analytics/PersistentEventStore$DataBaseHelper;
    //   4: invokevirtual 274	com/google/android/apps/analytics/PersistentEventStore$DataBaseHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   7: ldc_w 266
    //   10: aconst_null
    //   11: new 146	java/lang/StringBuilder
    //   14: dup
    //   15: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   18: ldc 249
    //   20: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: lload_1
    //   24: invokevirtual 252	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   27: invokevirtual 177	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   30: aconst_null
    //   31: aconst_null
    //   32: aconst_null
    //   33: aconst_null
    //   34: invokevirtual 278	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   37: astore 9
    //   39: aload 9
    //   41: astore 4
    //   43: aload 4
    //   45: invokeinterface 311 1 0
    //   50: ifeq +136 -> 186
    //   53: new 313	com/google/android/apps/analytics/Item$Builder
    //   56: dup
    //   57: aload 4
    //   59: aload 4
    //   61: ldc 81
    //   63: invokeinterface 290 2 0
    //   68: invokeinterface 298 2 0
    //   73: aload 4
    //   75: aload 4
    //   77: ldc 73
    //   79: invokeinterface 290 2 0
    //   84: invokeinterface 298 2 0
    //   89: aload 4
    //   91: aload 4
    //   93: ldc 70
    //   95: invokeinterface 290 2 0
    //   100: invokeinterface 317 2 0
    //   105: aload 4
    //   107: aload 4
    //   109: ldc 61
    //   111: invokeinterface 290 2 0
    //   116: invokeinterface 321 2 0
    //   121: invokespecial 324	com/google/android/apps/analytics/Item$Builder:<init>	(Ljava/lang/String;Ljava/lang/String;DJ)V
    //   124: aload 4
    //   126: aload 4
    //   128: ldc 67
    //   130: invokeinterface 290 2 0
    //   135: invokeinterface 298 2 0
    //   140: invokevirtual 328	com/google/android/apps/analytics/Item$Builder:setItemName	(Ljava/lang/String;)Lcom/google/android/apps/analytics/Item$Builder;
    //   143: aload 4
    //   145: aload 4
    //   147: ldc 58
    //   149: invokeinterface 290 2 0
    //   154: invokeinterface 298 2 0
    //   159: invokevirtual 331	com/google/android/apps/analytics/Item$Builder:setItemCategory	(Ljava/lang/String;)Lcom/google/android/apps/analytics/Item$Builder;
    //   162: invokevirtual 335	com/google/android/apps/analytics/Item$Builder:build	()Lcom/google/android/apps/analytics/Item;
    //   165: astore 10
    //   167: aload 10
    //   169: astore 8
    //   171: aload 4
    //   173: ifnull +10 -> 183
    //   176: aload 4
    //   178: invokeinterface 306 1 0
    //   183: aload 8
    //   185: areturn
    //   186: aload 4
    //   188: ifnull +10 -> 198
    //   191: aload 4
    //   193: invokeinterface 306 1 0
    //   198: aconst_null
    //   199: astore 8
    //   201: goto -18 -> 183
    //   204: astore 5
    //   206: aconst_null
    //   207: astore 6
    //   209: ldc 232
    //   211: aload 5
    //   213: invokevirtual 233	android/database/sqlite/SQLiteException:toString	()Ljava/lang/String;
    //   216: invokestatic 239	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   219: pop
    //   220: aload 6
    //   222: ifnull -24 -> 198
    //   225: aload 6
    //   227: invokeinterface 306 1 0
    //   232: goto -34 -> 198
    //   235: astore_3
    //   236: aconst_null
    //   237: astore 4
    //   239: aload 4
    //   241: ifnull +10 -> 251
    //   244: aload 4
    //   246: invokeinterface 306 1 0
    //   251: aload_3
    //   252: athrow
    //   253: astore_3
    //   254: goto -15 -> 239
    //   257: astore_3
    //   258: aload 6
    //   260: astore 4
    //   262: goto -23 -> 239
    //   265: astore 5
    //   267: aload 4
    //   269: astore 6
    //   271: goto -62 -> 209
    //
    // Exception table:
    //   from	to	target	type
    //   0	39	204	android/database/sqlite/SQLiteException
    //   0	39	235	finally
    //   43	167	253	finally
    //   209	220	257	finally
    //   43	167	265	android/database/sqlite/SQLiteException
  }

  public int getNumStoredEvents()
  {
    int i;
    try
    {
      if (this.compiledCountStatement == null)
        this.compiledCountStatement = this.databaseHelper.getReadableDatabase().compileStatement("SELECT COUNT(*) from events");
      long l = this.compiledCountStatement.simpleQueryForLong();
      i = (int)l;
      return i;
    }
    catch (SQLiteException localSQLiteException)
    {
      while (true)
      {
        Log.e("GoogleAnalyticsTracker", localSQLiteException.toString());
        i = 0;
      }
    }
  }

  // ERROR //
  public String getReferrer()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: getfield 221	com/google/android/apps/analytics/PersistentEventStore:databaseHelper	Lcom/google/android/apps/analytics/PersistentEventStore$DataBaseHelper;
    //   6: invokevirtual 274	com/google/android/apps/analytics/PersistentEventStore$DataBaseHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   9: astore 7
    //   11: iconst_1
    //   12: anewarray 159	java/lang/String
    //   15: astore 8
    //   17: aload 8
    //   19: iconst_0
    //   20: ldc 87
    //   22: aastore
    //   23: aload 7
    //   25: ldc_w 352
    //   28: aload 8
    //   30: aconst_null
    //   31: aconst_null
    //   32: aconst_null
    //   33: aconst_null
    //   34: aconst_null
    //   35: invokevirtual 278	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   38: astore 9
    //   40: aload 9
    //   42: astore 4
    //   44: aload 4
    //   46: invokeinterface 311 1 0
    //   51: ifeq +88 -> 139
    //   54: aload 4
    //   56: iconst_0
    //   57: invokeinterface 298 2 0
    //   62: astore 10
    //   64: aload 10
    //   66: astore 6
    //   68: aload 4
    //   70: ifnull +10 -> 80
    //   73: aload 4
    //   75: invokeinterface 306 1 0
    //   80: aload 6
    //   82: areturn
    //   83: astore_3
    //   84: aconst_null
    //   85: astore 4
    //   87: ldc 232
    //   89: aload_3
    //   90: invokevirtual 233	android/database/sqlite/SQLiteException:toString	()Ljava/lang/String;
    //   93: invokestatic 239	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   96: pop
    //   97: aload 4
    //   99: ifnull +10 -> 109
    //   102: aload 4
    //   104: invokeinterface 306 1 0
    //   109: aconst_null
    //   110: astore 6
    //   112: goto -32 -> 80
    //   115: astore_2
    //   116: aload_1
    //   117: ifnull +9 -> 126
    //   120: aload_1
    //   121: invokeinterface 306 1 0
    //   126: aload_2
    //   127: athrow
    //   128: astore_2
    //   129: aload 4
    //   131: astore_1
    //   132: goto -16 -> 116
    //   135: astore_3
    //   136: goto -49 -> 87
    //   139: aconst_null
    //   140: astore 6
    //   142: goto -74 -> 68
    //
    // Exception table:
    //   from	to	target	type
    //   2	40	83	android/database/sqlite/SQLiteException
    //   2	40	115	finally
    //   44	64	128	finally
    //   87	97	128	finally
    //   44	64	135	android/database/sqlite/SQLiteException
  }

  public int getStoreId()
  {
    return this.storeId;
  }

  // ERROR //
  Transaction getTransaction(long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 221	com/google/android/apps/analytics/PersistentEventStore:databaseHelper	Lcom/google/android/apps/analytics/PersistentEventStore$DataBaseHelper;
    //   4: invokevirtual 274	com/google/android/apps/analytics/PersistentEventStore$DataBaseHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   7: ldc_w 264
    //   10: aconst_null
    //   11: new 146	java/lang/StringBuilder
    //   14: dup
    //   15: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   18: ldc 249
    //   20: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: lload_1
    //   24: invokevirtual 252	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   27: invokevirtual 177	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   30: aconst_null
    //   31: aconst_null
    //   32: aconst_null
    //   33: aconst_null
    //   34: invokevirtual 278	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   37: astore 8
    //   39: aload 8
    //   41: astore 4
    //   43: aload 4
    //   45: invokeinterface 311 1 0
    //   50: ifeq +123 -> 173
    //   53: new 359	com/google/android/apps/analytics/Transaction$Builder
    //   56: dup
    //   57: aload 4
    //   59: aload 4
    //   61: ldc 81
    //   63: invokeinterface 290 2 0
    //   68: invokeinterface 298 2 0
    //   73: aload 4
    //   75: aload 4
    //   77: ldc 114
    //   79: invokeinterface 290 2 0
    //   84: invokeinterface 317 2 0
    //   89: invokespecial 362	com/google/android/apps/analytics/Transaction$Builder:<init>	(Ljava/lang/String;D)V
    //   92: aload 4
    //   94: aload 4
    //   96: ldc 102
    //   98: invokeinterface 290 2 0
    //   103: invokeinterface 298 2 0
    //   108: invokevirtual 366	com/google/android/apps/analytics/Transaction$Builder:setStoreName	(Ljava/lang/String;)Lcom/google/android/apps/analytics/Transaction$Builder;
    //   111: aload 4
    //   113: aload 4
    //   115: ldc 117
    //   117: invokeinterface 290 2 0
    //   122: invokeinterface 317 2 0
    //   127: invokevirtual 370	com/google/android/apps/analytics/Transaction$Builder:setTotalTax	(D)Lcom/google/android/apps/analytics/Transaction$Builder;
    //   130: aload 4
    //   132: aload 4
    //   134: ldc 96
    //   136: invokeinterface 290 2 0
    //   141: invokeinterface 317 2 0
    //   146: invokevirtual 373	com/google/android/apps/analytics/Transaction$Builder:setShippingCost	(D)Lcom/google/android/apps/analytics/Transaction$Builder;
    //   149: invokevirtual 376	com/google/android/apps/analytics/Transaction$Builder:build	()Lcom/google/android/apps/analytics/Transaction;
    //   152: astore 9
    //   154: aload 9
    //   156: astore 7
    //   158: aload 4
    //   160: ifnull +10 -> 170
    //   163: aload 4
    //   165: invokeinterface 306 1 0
    //   170: aload 7
    //   172: areturn
    //   173: aload 4
    //   175: ifnull +10 -> 185
    //   178: aload 4
    //   180: invokeinterface 306 1 0
    //   185: aconst_null
    //   186: astore 7
    //   188: goto -18 -> 170
    //   191: astore 5
    //   193: aconst_null
    //   194: astore 4
    //   196: ldc 232
    //   198: aload 5
    //   200: invokevirtual 233	android/database/sqlite/SQLiteException:toString	()Ljava/lang/String;
    //   203: invokestatic 239	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   206: pop
    //   207: aload 4
    //   209: ifnull -24 -> 185
    //   212: aload 4
    //   214: invokeinterface 306 1 0
    //   219: goto -34 -> 185
    //   222: astore_3
    //   223: aconst_null
    //   224: astore 4
    //   226: aload 4
    //   228: ifnull +10 -> 238
    //   231: aload 4
    //   233: invokeinterface 306 1 0
    //   238: aload_3
    //   239: athrow
    //   240: astore_3
    //   241: goto -15 -> 226
    //   244: astore 5
    //   246: goto -50 -> 196
    //
    // Exception table:
    //   from	to	target	type
    //   0	39	191	android/database/sqlite/SQLiteException
    //   0	39	222	finally
    //   43	154	240	finally
    //   196	207	240	finally
    //   43	154	244	android/database/sqlite/SQLiteException
  }

  // ERROR //
  public String getVisitorCustomVar(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: getfield 221	com/google/android/apps/analytics/PersistentEventStore:databaseHelper	Lcom/google/android/apps/analytics/PersistentEventStore$DataBaseHelper;
    //   6: invokevirtual 274	com/google/android/apps/analytics/PersistentEventStore$DataBaseHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   9: astore 8
    //   11: aload 8
    //   13: ldc_w 379
    //   16: aconst_null
    //   17: new 146	java/lang/StringBuilder
    //   20: dup
    //   21: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   24: ldc_w 381
    //   27: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: iload_1
    //   31: invokevirtual 384	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   34: invokevirtual 177	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   37: aconst_null
    //   38: aconst_null
    //   39: aconst_null
    //   40: aconst_null
    //   41: invokevirtual 278	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   44: astore 9
    //   46: aload 9
    //   48: invokeinterface 387 1 0
    //   53: ifle +53 -> 106
    //   56: aload 9
    //   58: invokeinterface 311 1 0
    //   63: pop
    //   64: aload 9
    //   66: aload 9
    //   68: ldc 43
    //   70: invokeinterface 290 2 0
    //   75: invokeinterface 298 2 0
    //   80: astore 10
    //   82: aload 8
    //   84: invokevirtual 230	android/database/sqlite/SQLiteDatabase:close	()V
    //   87: aload 9
    //   89: ifnull +10 -> 99
    //   92: aload 9
    //   94: invokeinterface 306 1 0
    //   99: aload 10
    //   101: astore 7
    //   103: aload 7
    //   105: areturn
    //   106: aconst_null
    //   107: astore 10
    //   109: goto -27 -> 82
    //   112: astore 4
    //   114: aconst_null
    //   115: astore 5
    //   117: ldc 232
    //   119: aload 4
    //   121: invokevirtual 233	android/database/sqlite/SQLiteException:toString	()Ljava/lang/String;
    //   124: invokestatic 239	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   127: pop
    //   128: aload 5
    //   130: ifnull +10 -> 140
    //   133: aload 5
    //   135: invokeinterface 306 1 0
    //   140: aconst_null
    //   141: astore 7
    //   143: goto -40 -> 103
    //   146: astore_3
    //   147: aload_2
    //   148: ifnull +9 -> 157
    //   151: aload_2
    //   152: invokeinterface 306 1 0
    //   157: aload_3
    //   158: athrow
    //   159: astore_3
    //   160: aload 9
    //   162: astore_2
    //   163: goto -16 -> 147
    //   166: astore_3
    //   167: aload 5
    //   169: astore_2
    //   170: goto -23 -> 147
    //   173: astore 4
    //   175: aload 9
    //   177: astore 5
    //   179: goto -62 -> 117
    //
    // Exception table:
    //   from	to	target	type
    //   2	46	112	android/database/sqlite/SQLiteException
    //   2	46	146	finally
    //   46	87	159	finally
    //   117	128	166	finally
    //   46	87	173	android/database/sqlite/SQLiteException
  }

  CustomVariableBuffer getVisitorVarBuffer()
  {
    Cursor localCursor;
    CustomVariableBuffer localCustomVariableBuffer = new CustomVariableBuffer();
    try
    {
      localCursor = this.databaseHelper.getReadableDatabase().query("custom_var_cache", null, "cv_scope=1", null, null, null, null);
      if (!(localCursor.moveToNext()))
        break label132;
      label132: localCustomVariableBuffer.setCustomVariable(new CustomVariable(localCursor.getInt(localCursor.getColumnIndex("cv_index")), localCursor.getString(localCursor.getColumnIndex("cv_name")), localCursor.getString(localCursor.getColumnIndex("cv_value")), localCursor.getInt(localCursor.getColumnIndex("cv_scope"))));
    }
    catch (SQLiteException localSQLiteException)
    {
      Log.e("GoogleAnalyticsTracker", localSQLiteException.toString());
      while (true)
      {
        return localCustomVariableBuffer;
        localCursor.close();
      }
    }
  }

  public Event[] peekEvents()
  {
    return peekEvents(1000);
  }

  // ERROR //
  public Event[] peekEvents(int paramInt)
  {
    // Byte code:
    //   0: new 398	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 399	java/util/ArrayList:<init>	()V
    //   7: astore_2
    //   8: aload_0
    //   9: getfield 221	com/google/android/apps/analytics/PersistentEventStore:databaseHelper	Lcom/google/android/apps/analytics/PersistentEventStore$DataBaseHelper;
    //   12: invokevirtual 274	com/google/android/apps/analytics/PersistentEventStore$DataBaseHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   15: ldc 254
    //   17: aconst_null
    //   18: aconst_null
    //   19: aconst_null
    //   20: aconst_null
    //   21: aconst_null
    //   22: ldc 55
    //   24: iload_1
    //   25: invokestatic 403	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   28: invokevirtual 406	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   31: astore 9
    //   33: aload 9
    //   35: astore 6
    //   37: aload 6
    //   39: invokeinterface 284 1 0
    //   44: ifeq +358 -> 402
    //   47: new 408	com/google/android/apps/analytics/Event
    //   50: dup
    //   51: aload 6
    //   53: iconst_0
    //   54: invokeinterface 321 2 0
    //   59: aload 6
    //   61: iconst_1
    //   62: invokeinterface 294 2 0
    //   67: aload 6
    //   69: iconst_2
    //   70: invokeinterface 298 2 0
    //   75: aload 6
    //   77: iconst_3
    //   78: invokeinterface 294 2 0
    //   83: aload 6
    //   85: iconst_4
    //   86: invokeinterface 294 2 0
    //   91: aload 6
    //   93: iconst_5
    //   94: invokeinterface 294 2 0
    //   99: aload 6
    //   101: bipush 6
    //   103: invokeinterface 294 2 0
    //   108: aload 6
    //   110: bipush 7
    //   112: invokeinterface 294 2 0
    //   117: aload 6
    //   119: bipush 8
    //   121: invokeinterface 298 2 0
    //   126: aload 6
    //   128: bipush 9
    //   130: invokeinterface 298 2 0
    //   135: aload 6
    //   137: bipush 10
    //   139: invokeinterface 298 2 0
    //   144: aload 6
    //   146: bipush 11
    //   148: invokeinterface 294 2 0
    //   153: aload 6
    //   155: bipush 12
    //   157: invokeinterface 294 2 0
    //   162: aload 6
    //   164: bipush 13
    //   166: invokeinterface 294 2 0
    //   171: invokespecial 411	com/google/android/apps/analytics/Event:<init>	(JILjava/lang/String;IIIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;III)V
    //   174: astore 10
    //   176: aload 6
    //   178: ldc 55
    //   180: invokeinterface 290 2 0
    //   185: istore 11
    //   187: aload 6
    //   189: iload 11
    //   191: invokeinterface 321 2 0
    //   196: lstore 12
    //   198: ldc_w 413
    //   201: aload 10
    //   203: getfield 415	com/google/android/apps/analytics/Event:category	Ljava/lang/String;
    //   206: invokevirtual 419	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   209: ifeq +98 -> 307
    //   212: aload_0
    //   213: lload 12
    //   215: invokevirtual 421	com/google/android/apps/analytics/PersistentEventStore:getTransaction	(J)Lcom/google/android/apps/analytics/Transaction;
    //   218: astore 17
    //   220: aload 17
    //   222: ifnonnull +30 -> 252
    //   225: ldc 232
    //   227: new 146	java/lang/StringBuilder
    //   230: dup
    //   231: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   234: ldc_w 423
    //   237: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   240: lload 12
    //   242: invokevirtual 252	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   245: invokevirtual 177	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   248: invokestatic 426	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   251: pop
    //   252: aload 10
    //   254: aload 17
    //   256: invokevirtual 430	com/google/android/apps/analytics/Event:setTransaction	(Lcom/google/android/apps/analytics/Transaction;)V
    //   259: aload_2
    //   260: aload 10
    //   262: invokeinterface 435 2 0
    //   267: pop
    //   268: goto -231 -> 37
    //   271: astore_3
    //   272: aload 6
    //   274: astore 4
    //   276: ldc 232
    //   278: aload_3
    //   279: invokevirtual 233	android/database/sqlite/SQLiteException:toString	()Ljava/lang/String;
    //   282: invokestatic 239	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   285: pop
    //   286: iconst_0
    //   287: anewarray 408	com/google/android/apps/analytics/Event
    //   290: astore 8
    //   292: aload 4
    //   294: ifnull +10 -> 304
    //   297: aload 4
    //   299: invokeinterface 306 1 0
    //   304: aload 8
    //   306: areturn
    //   307: ldc_w 437
    //   310: aload 10
    //   312: getfield 415	com/google/android/apps/analytics/Event:category	Ljava/lang/String;
    //   315: invokevirtual 419	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   318: ifeq +70 -> 388
    //   321: aload_0
    //   322: lload 12
    //   324: invokevirtual 439	com/google/android/apps/analytics/PersistentEventStore:getItem	(J)Lcom/google/android/apps/analytics/Item;
    //   327: astore 15
    //   329: aload 15
    //   331: ifnonnull +30 -> 361
    //   334: ldc 232
    //   336: new 146	java/lang/StringBuilder
    //   339: dup
    //   340: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   343: ldc_w 441
    //   346: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   349: lload 12
    //   351: invokevirtual 252	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   354: invokevirtual 177	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   357: invokestatic 426	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   360: pop
    //   361: aload 10
    //   363: aload 15
    //   365: invokevirtual 445	com/google/android/apps/analytics/Event:setItem	(Lcom/google/android/apps/analytics/Item;)V
    //   368: goto -109 -> 259
    //   371: astore 5
    //   373: aload 6
    //   375: ifnull +10 -> 385
    //   378: aload 6
    //   380: invokeinterface 306 1 0
    //   385: aload 5
    //   387: athrow
    //   388: aload 10
    //   390: aload_0
    //   391: lload 12
    //   393: invokevirtual 447	com/google/android/apps/analytics/PersistentEventStore:getCustomVariables	(J)Lcom/google/android/apps/analytics/CustomVariableBuffer;
    //   396: invokevirtual 451	com/google/android/apps/analytics/Event:setCustomVariableBuffer	(Lcom/google/android/apps/analytics/CustomVariableBuffer;)V
    //   399: goto -140 -> 259
    //   402: aload 6
    //   404: ifnull +10 -> 414
    //   407: aload 6
    //   409: invokeinterface 306 1 0
    //   414: aload_2
    //   415: aload_2
    //   416: invokeinterface 454 1 0
    //   421: anewarray 408	com/google/android/apps/analytics/Event
    //   424: invokeinterface 458 2 0
    //   429: checkcast 460	[Lcom/google/android/apps/analytics/Event;
    //   432: astore 8
    //   434: goto -130 -> 304
    //   437: astore 5
    //   439: aconst_null
    //   440: astore 6
    //   442: goto -69 -> 373
    //   445: astore 5
    //   447: aload 4
    //   449: astore 6
    //   451: goto -78 -> 373
    //   454: astore_3
    //   455: aconst_null
    //   456: astore 4
    //   458: goto -182 -> 276
    //
    // Exception table:
    //   from	to	target	type
    //   37	268	271	android/database/sqlite/SQLiteException
    //   307	368	271	android/database/sqlite/SQLiteException
    //   388	399	271	android/database/sqlite/SQLiteException
    //   37	268	371	finally
    //   307	368	371	finally
    //   388	399	371	finally
    //   8	33	437	finally
    //   276	292	445	finally
    //   8	33	454	android/database/sqlite/SQLiteException
  }

  void putCustomVariables(Event paramEvent, long paramLong)
  {
    CustomVariableBuffer localCustomVariableBuffer1;
    byte b2;
    byte b1 = 1;
    try
    {
      SQLiteDatabase localSQLiteDatabase = this.databaseHelper.getWritableDatabase();
      localCustomVariableBuffer1 = paramEvent.getCustomVariableBuffer();
      if (!(this.useStoredVisitorVars))
        break label281;
      if (localCustomVariableBuffer1 == null)
      {
        localCustomVariableBuffer1 = new CustomVariableBuffer();
        paramEvent.setCustomVariableBuffer(localCustomVariableBuffer1);
      }
      CustomVariableBuffer localCustomVariableBuffer2 = getVisitorVarBuffer();
      b2 = b1;
      if (b2 <= 5)
      {
        CustomVariable localCustomVariable2 = localCustomVariableBuffer2.getCustomVariableAt(b2);
        CustomVariable localCustomVariable3 = localCustomVariableBuffer1.getCustomVariableAt(b2);
        if ((localCustomVariable2 == null) || (localCustomVariable3 != null))
          break label275;
        localCustomVariableBuffer1.setCustomVariable(localCustomVariable2);
        break label275:
      }
      this.useStoredVisitorVars = false;
      break label281:
      if (b1 > 5)
        break label274;
      if (!(localCustomVariableBuffer1.isIndexAvailable(b1)))
      {
        CustomVariable localCustomVariable1 = localCustomVariableBuffer1.getCustomVariableAt(b1);
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("event_id", Long.valueOf(paramLong));
        localContentValues.put("cv_index", Integer.valueOf(localCustomVariable1.getIndex()));
        localContentValues.put("cv_name", localCustomVariable1.getName());
        localContentValues.put("cv_scope", Integer.valueOf(localCustomVariable1.getScope()));
        localContentValues.put("cv_value", localCustomVariable1.getValue());
        localSQLiteDatabase.insert("custom_variables", "event_id", localContentValues);
        localSQLiteDatabase.update("custom_var_cache", localContentValues, "cv_index=" + localCustomVariable1.getIndex(), null);
      }
      label274: label275: label281: ++b1;
    }
    catch (SQLiteException localSQLiteException)
    {
      while (true)
      {
        Log.e("GoogleAnalyticsTracker", localSQLiteException.toString());
        do
          while (true)
          {
            return;
            ++b2;
          }
        while (localCustomVariableBuffer1 == null);
      }
    }
  }

  // ERROR //
  public void putEvent(Event paramEvent)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: getfield 260	com/google/android/apps/analytics/PersistentEventStore:numStoredEvents	I
    //   6: sipush 1000
    //   9: if_icmplt +13 -> 22
    //   12: ldc 232
    //   14: ldc_w 525
    //   17: invokestatic 426	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   20: pop
    //   21: return
    //   22: aload_0
    //   23: getfield 527	com/google/android/apps/analytics/PersistentEventStore:sessionUpdated	Z
    //   26: ifne +7 -> 33
    //   29: aload_0
    //   30: invokevirtual 530	com/google/android/apps/analytics/PersistentEventStore:storeUpdatedSession	()V
    //   33: aload_0
    //   34: getfield 221	com/google/android/apps/analytics/PersistentEventStore:databaseHelper	Lcom/google/android/apps/analytics/PersistentEventStore$DataBaseHelper;
    //   37: invokevirtual 225	com/google/android/apps/analytics/PersistentEventStore$DataBaseHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   40: astore 6
    //   42: aload 6
    //   44: invokevirtual 533	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   47: new 479	android/content/ContentValues
    //   50: dup
    //   51: invokespecial 480	android/content/ContentValues:<init>	()V
    //   54: astore 9
    //   56: aload 9
    //   58: ldc 123
    //   60: aload_1
    //   61: getfield 536	com/google/android/apps/analytics/Event:userId	I
    //   64: invokestatic 496	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   67: invokevirtual 499	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   70: aload 9
    //   72: ldc 13
    //   74: aload_1
    //   75: getfield 539	com/google/android/apps/analytics/Event:accountId	Ljava/lang/String;
    //   78: invokevirtual 505	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   81: aload 9
    //   83: ldc 84
    //   85: ldc2_w 540
    //   88: invokestatic 547	java/lang/Math:random	()D
    //   91: dmul
    //   92: d2i
    //   93: invokestatic 496	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   96: invokevirtual 499	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   99: aload 9
    //   101: ldc 108
    //   103: aload_0
    //   104: getfield 549	com/google/android/apps/analytics/PersistentEventStore:timestampFirst	J
    //   107: invokestatic 486	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   110: invokevirtual 490	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   113: aload 9
    //   115: ldc 111
    //   117: aload_0
    //   118: getfield 551	com/google/android/apps/analytics/PersistentEventStore:timestampPrevious	J
    //   121: invokestatic 486	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   124: invokevirtual 490	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   127: aload 9
    //   129: ldc 105
    //   131: aload_0
    //   132: getfield 553	com/google/android/apps/analytics/PersistentEventStore:timestampCurrent	J
    //   135: invokestatic 486	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   138: invokevirtual 490	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   141: aload 9
    //   143: ldc 129
    //   145: aload_0
    //   146: getfield 555	com/google/android/apps/analytics/PersistentEventStore:visits	I
    //   149: invokestatic 496	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   152: invokevirtual 499	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   155: aload 9
    //   157: ldc 19
    //   159: aload_1
    //   160: getfield 415	com/google/android/apps/analytics/Event:category	Ljava/lang/String;
    //   163: invokevirtual 505	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   166: aload 9
    //   168: ldc 16
    //   170: aload_1
    //   171: getfield 557	com/google/android/apps/analytics/Event:action	Ljava/lang/String;
    //   174: invokevirtual 505	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   177: aload 9
    //   179: ldc 76
    //   181: aload_1
    //   182: getfield 559	com/google/android/apps/analytics/Event:label	Ljava/lang/String;
    //   185: invokevirtual 505	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   188: aload 9
    //   190: ldc 126
    //   192: aload_1
    //   193: getfield 561	com/google/android/apps/analytics/Event:value	I
    //   196: invokestatic 496	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   199: invokevirtual 499	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   202: aload 9
    //   204: ldc 93
    //   206: aload_1
    //   207: getfield 564	com/google/android/apps/analytics/Event:screenWidth	I
    //   210: invokestatic 496	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   213: invokevirtual 499	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   216: aload 9
    //   218: ldc 90
    //   220: aload_1
    //   221: getfield 567	com/google/android/apps/analytics/Event:screenHeight	I
    //   224: invokestatic 496	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   227: invokevirtual 499	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   230: aload 6
    //   232: ldc 254
    //   234: ldc 55
    //   236: aload 9
    //   238: invokevirtual 515	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   241: ldc2_w 568
    //   244: lcmp
    //   245: ifeq +190 -> 435
    //   248: aload_0
    //   249: iconst_1
    //   250: aload_0
    //   251: getfield 260	com/google/android/apps/analytics/PersistentEventStore:numStoredEvents	I
    //   254: iadd
    //   255: putfield 260	com/google/android/apps/analytics/PersistentEventStore:numStoredEvents	I
    //   258: iconst_1
    //   259: anewarray 159	java/lang/String
    //   262: astore 11
    //   264: aload 11
    //   266: iconst_0
    //   267: ldc 55
    //   269: aastore
    //   270: aload 6
    //   272: ldc 254
    //   274: aload 11
    //   276: aconst_null
    //   277: aconst_null
    //   278: aconst_null
    //   279: aconst_null
    //   280: ldc_w 571
    //   283: aconst_null
    //   284: invokevirtual 406	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   287: astore 12
    //   289: aload 12
    //   291: iconst_0
    //   292: invokeinterface 574 2 0
    //   297: pop
    //   298: aload 12
    //   300: iconst_0
    //   301: invokeinterface 321 2 0
    //   306: lstore 14
    //   308: aload 12
    //   310: invokeinterface 306 1 0
    //   315: aload_1
    //   316: getfield 415	com/google/android/apps/analytics/Event:category	Ljava/lang/String;
    //   319: ldc_w 413
    //   322: invokevirtual 419	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   325: ifeq +28 -> 353
    //   328: aload_0
    //   329: aload_1
    //   330: lload 14
    //   332: invokevirtual 577	com/google/android/apps/analytics/PersistentEventStore:putTransaction	(Lcom/google/android/apps/analytics/Event;J)V
    //   335: aload 6
    //   337: invokevirtual 580	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   340: aload 6
    //   342: ifnull -321 -> 21
    //   345: aload 6
    //   347: invokevirtual 583	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   350: goto -329 -> 21
    //   353: aload_1
    //   354: getfield 415	com/google/android/apps/analytics/Event:category	Ljava/lang/String;
    //   357: ldc_w 437
    //   360: invokevirtual 419	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   363: ifeq +42 -> 405
    //   366: aload_0
    //   367: aload_1
    //   368: lload 14
    //   370: invokevirtual 586	com/google/android/apps/analytics/PersistentEventStore:putItem	(Lcom/google/android/apps/analytics/Event;J)V
    //   373: goto -38 -> 335
    //   376: astore 8
    //   378: aload 6
    //   380: astore_2
    //   381: aload 8
    //   383: astore_3
    //   384: ldc 232
    //   386: aload_3
    //   387: invokevirtual 233	android/database/sqlite/SQLiteException:toString	()Ljava/lang/String;
    //   390: invokestatic 239	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   393: pop
    //   394: aload_2
    //   395: ifnull -374 -> 21
    //   398: aload_2
    //   399: invokevirtual 583	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   402: goto -381 -> 21
    //   405: aload_0
    //   406: aload_1
    //   407: lload 14
    //   409: invokevirtual 588	com/google/android/apps/analytics/PersistentEventStore:putCustomVariables	(Lcom/google/android/apps/analytics/Event;J)V
    //   412: goto -77 -> 335
    //   415: astore 7
    //   417: aload 6
    //   419: astore_2
    //   420: aload 7
    //   422: astore 4
    //   424: aload_2
    //   425: ifnull +7 -> 432
    //   428: aload_2
    //   429: invokevirtual 583	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   432: aload 4
    //   434: athrow
    //   435: ldc 232
    //   437: ldc_w 590
    //   440: invokestatic 239	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   443: pop
    //   444: goto -104 -> 340
    //   447: astore 4
    //   449: goto -25 -> 424
    //   452: astore_3
    //   453: goto -69 -> 384
    //
    // Exception table:
    //   from	to	target	type
    //   42	340	376	android/database/sqlite/SQLiteException
    //   353	373	376	android/database/sqlite/SQLiteException
    //   405	412	376	android/database/sqlite/SQLiteException
    //   435	444	376	android/database/sqlite/SQLiteException
    //   42	340	415	finally
    //   353	373	415	finally
    //   405	412	415	finally
    //   435	444	415	finally
    //   33	42	447	finally
    //   384	394	447	finally
    //   33	42	452	android/database/sqlite/SQLiteException
  }

  void putItem(Event paramEvent, long paramLong)
  {
    Item localItem = paramEvent.getItem();
    if (localItem == null)
    {
      Log.w("GoogleAnalyticsTracker", "missing item details for event " + paramLong);
      return;
    }
    try
    {
      SQLiteDatabase localSQLiteDatabase = this.databaseHelper.getWritableDatabase();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("event_id", Long.valueOf(paramLong));
      localContentValues.put("order_id", localItem.getOrderId());
      localContentValues.put("item_sku", localItem.getItemSKU());
      localContentValues.put("item_name", localItem.getItemName());
      localContentValues.put("item_category", localItem.getItemCategory());
      localContentValues.put("item_price", localItem.getItemPrice() + "");
      localContentValues.put("item_count", localItem.getItemCount() + "");
      localSQLiteDatabase.insert("item_events", "event_id", localContentValues);
    }
    catch (SQLiteException localSQLiteException)
    {
      while (true)
        Log.e("GoogleAnalyticsTracker", localSQLiteException.toString());
    }
  }

  void putTransaction(Event paramEvent, long paramLong)
  {
    Transaction localTransaction = paramEvent.getTransaction();
    if (localTransaction == null)
    {
      Log.w("GoogleAnalyticsTracker", "missing transaction details for event " + paramLong);
      return;
    }
    try
    {
      SQLiteDatabase localSQLiteDatabase = this.databaseHelper.getWritableDatabase();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("event_id", Long.valueOf(paramLong));
      localContentValues.put("order_id", localTransaction.getOrderId());
      localContentValues.put("tran_storename", localTransaction.getStoreName());
      localContentValues.put("tran_totalcost", localTransaction.getTotalCost() + "");
      localContentValues.put("tran_totaltax", localTransaction.getTotalTax() + "");
      localContentValues.put("tran_shippingcost", localTransaction.getShippingCost() + "");
      localSQLiteDatabase.insert("transaction_events", "event_id", localContentValues);
    }
    catch (SQLiteException localSQLiteException)
    {
      while (true)
        Log.e("GoogleAnalyticsTracker", localSQLiteException.toString());
    }
  }

  public void setReferrer(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase;
    try
    {
      localSQLiteDatabase = this.databaseHelper.getWritableDatabase();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("referrer", paramString);
      localSQLiteDatabase.insert("install_referrer", null, localContentValues);
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      while (true)
        Log.e("GoogleAnalyticsTracker", localSQLiteException.toString());
    }
  }

  // ERROR //
  public void startNewVisit()
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_0
    //   2: putfield 527	com/google/android/apps/analytics/PersistentEventStore:sessionUpdated	Z
    //   5: aload_0
    //   6: iconst_1
    //   7: putfield 467	com/google/android/apps/analytics/PersistentEventStore:useStoredVisitorVars	Z
    //   10: aload_0
    //   11: aload_0
    //   12: invokevirtual 643	com/google/android/apps/analytics/PersistentEventStore:getNumStoredEvents	()I
    //   15: putfield 260	com/google/android/apps/analytics/PersistentEventStore:numStoredEvents	I
    //   18: aload_0
    //   19: getfield 221	com/google/android/apps/analytics/PersistentEventStore:databaseHelper	Lcom/google/android/apps/analytics/PersistentEventStore$DataBaseHelper;
    //   22: invokevirtual 225	com/google/android/apps/analytics/PersistentEventStore$DataBaseHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   25: astore 5
    //   27: aload 5
    //   29: ldc_w 645
    //   32: aconst_null
    //   33: aconst_null
    //   34: aconst_null
    //   35: aconst_null
    //   36: aconst_null
    //   37: aconst_null
    //   38: invokevirtual 278	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   41: astore 6
    //   43: aload 6
    //   45: astore_2
    //   46: aload_2
    //   47: invokeinterface 311 1 0
    //   52: ifne +156 -> 208
    //   55: invokestatic 650	java/lang/System:currentTimeMillis	()J
    //   58: ldc2_w 651
    //   61: ldiv
    //   62: lstore 7
    //   64: aload_0
    //   65: lload 7
    //   67: putfield 549	com/google/android/apps/analytics/PersistentEventStore:timestampFirst	J
    //   70: aload_0
    //   71: lload 7
    //   73: putfield 551	com/google/android/apps/analytics/PersistentEventStore:timestampPrevious	J
    //   76: aload_0
    //   77: lload 7
    //   79: putfield 553	com/google/android/apps/analytics/PersistentEventStore:timestampCurrent	J
    //   82: aload_0
    //   83: iconst_1
    //   84: putfield 555	com/google/android/apps/analytics/PersistentEventStore:visits	I
    //   87: aload_0
    //   88: ldc_w 653
    //   91: new 655	java/security/SecureRandom
    //   94: dup
    //   95: invokespecial 656	java/security/SecureRandom:<init>	()V
    //   98: invokevirtual 659	java/security/SecureRandom:nextInt	()I
    //   101: iand
    //   102: putfield 355	com/google/android/apps/analytics/PersistentEventStore:storeId	I
    //   105: new 479	android/content/ContentValues
    //   108: dup
    //   109: invokespecial 480	android/content/ContentValues:<init>	()V
    //   112: astore 9
    //   114: aload 9
    //   116: ldc 108
    //   118: aload_0
    //   119: getfield 549	com/google/android/apps/analytics/PersistentEventStore:timestampFirst	J
    //   122: invokestatic 486	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   125: invokevirtual 490	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   128: aload 9
    //   130: ldc 111
    //   132: aload_0
    //   133: getfield 551	com/google/android/apps/analytics/PersistentEventStore:timestampPrevious	J
    //   136: invokestatic 486	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   139: invokevirtual 490	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   142: aload 9
    //   144: ldc 105
    //   146: aload_0
    //   147: getfield 553	com/google/android/apps/analytics/PersistentEventStore:timestampCurrent	J
    //   150: invokestatic 486	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   153: invokevirtual 490	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   156: aload 9
    //   158: ldc 129
    //   160: aload_0
    //   161: getfield 555	com/google/android/apps/analytics/PersistentEventStore:visits	I
    //   164: invokestatic 496	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   167: invokevirtual 499	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   170: aload 9
    //   172: ldc 99
    //   174: aload_0
    //   175: getfield 355	com/google/android/apps/analytics/PersistentEventStore:storeId	I
    //   178: invokestatic 496	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   181: invokevirtual 499	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   184: aload 5
    //   186: ldc_w 645
    //   189: ldc 108
    //   191: aload 9
    //   193: invokevirtual 515	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   196: pop2
    //   197: aload_2
    //   198: ifnull +9 -> 207
    //   201: aload_2
    //   202: invokeinterface 306 1 0
    //   207: return
    //   208: aload_0
    //   209: aload_2
    //   210: iconst_0
    //   211: invokeinterface 321 2 0
    //   216: putfield 549	com/google/android/apps/analytics/PersistentEventStore:timestampFirst	J
    //   219: aload_0
    //   220: aload_2
    //   221: iconst_2
    //   222: invokeinterface 321 2 0
    //   227: putfield 551	com/google/android/apps/analytics/PersistentEventStore:timestampPrevious	J
    //   230: aload_0
    //   231: invokestatic 650	java/lang/System:currentTimeMillis	()J
    //   234: ldc2_w 651
    //   237: ldiv
    //   238: putfield 553	com/google/android/apps/analytics/PersistentEventStore:timestampCurrent	J
    //   241: aload_0
    //   242: iconst_1
    //   243: aload_2
    //   244: iconst_3
    //   245: invokeinterface 294 2 0
    //   250: iadd
    //   251: putfield 555	com/google/android/apps/analytics/PersistentEventStore:visits	I
    //   254: aload_0
    //   255: aload_2
    //   256: iconst_4
    //   257: invokeinterface 294 2 0
    //   262: putfield 355	com/google/android/apps/analytics/PersistentEventStore:storeId	I
    //   265: goto -68 -> 197
    //   268: astore_1
    //   269: ldc 232
    //   271: aload_1
    //   272: invokevirtual 233	android/database/sqlite/SQLiteException:toString	()Ljava/lang/String;
    //   275: invokestatic 239	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   278: pop
    //   279: aload_2
    //   280: ifnull -73 -> 207
    //   283: aload_2
    //   284: invokeinterface 306 1 0
    //   289: goto -82 -> 207
    //   292: astore_3
    //   293: aconst_null
    //   294: astore_2
    //   295: aload_2
    //   296: ifnull +9 -> 305
    //   299: aload_2
    //   300: invokeinterface 306 1 0
    //   305: aload_3
    //   306: athrow
    //   307: astore_3
    //   308: goto -13 -> 295
    //   311: astore_1
    //   312: aconst_null
    //   313: astore_2
    //   314: goto -45 -> 269
    //
    // Exception table:
    //   from	to	target	type
    //   46	197	268	android/database/sqlite/SQLiteException
    //   208	265	268	android/database/sqlite/SQLiteException
    //   18	43	292	finally
    //   46	197	307	finally
    //   208	265	307	finally
    //   269	279	307	finally
    //   18	43	311	android/database/sqlite/SQLiteException
  }

  void storeUpdatedSession()
  {
    SQLiteDatabase localSQLiteDatabase;
    try
    {
      localSQLiteDatabase = this.databaseHelper.getWritableDatabase();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("timestamp_previous", Long.valueOf(this.timestampPrevious));
      localContentValues.put("timestamp_current", Long.valueOf(this.timestampCurrent));
      localContentValues.put("visits", Integer.valueOf(this.visits));
      String[] arrayOfString = new String[1];
      arrayOfString[0] = Long.toString(this.timestampFirst);
      localSQLiteDatabase.update("session", localContentValues, "timestamp_first=?", arrayOfString);
      this.sessionUpdated = true;
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      while (true)
        Log.e("GoogleAnalyticsTracker", localSQLiteException.toString());
    }
  }

  static class DataBaseHelper extends SQLiteOpenHelper
  {
    private final int databaseVersion;

    public DataBaseHelper(Context paramContext)
    {
      this(paramContext, "google_analytics.db", 3);
    }

    public DataBaseHelper(Context paramContext, String paramString)
    {
      this(paramContext, paramString, 3);
    }

    DataBaseHelper(Context paramContext, String paramString, int paramInt)
    {
      super(paramContext, paramString, null, paramInt);
      this.databaseVersion = paramInt;
    }

    private void createECommerceTables(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS transaction_events;");
      paramSQLiteDatabase.execSQL(PersistentEventStore.access$400());
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS item_events;");
      paramSQLiteDatabase.execSQL(PersistentEventStore.access$500());
    }

    void createCustomVariableTables(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS custom_variables;");
      paramSQLiteDatabase.execSQL(PersistentEventStore.access$200());
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS custom_var_cache;");
      paramSQLiteDatabase.execSQL(PersistentEventStore.access$300());
      for (byte b = 1; b <= 5; ++b)
      {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("event_id", Integer.valueOf(0));
        localContentValues.put("cv_index", Integer.valueOf(b));
        localContentValues.put("cv_name", "");
        localContentValues.put("cv_scope", Integer.valueOf(3));
        localContentValues.put("cv_value", "");
        paramSQLiteDatabase.insert("custom_var_cache", "event_id", localContentValues);
      }
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS events;");
      paramSQLiteDatabase.execSQL(PersistentEventStore.access$000());
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS session;");
      paramSQLiteDatabase.execSQL(PersistentEventStore.access$100());
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS install_referrer;");
      paramSQLiteDatabase.execSQL("CREATE TABLE install_referrer (referrer TEXT PRIMARY KEY NOT NULL);");
      if (this.databaseVersion > 1)
        createCustomVariableTables(paramSQLiteDatabase);
      if (this.databaseVersion > 2)
        createECommerceTables(paramSQLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      Log.w("GoogleAnalyticsTracker", "Downgrading database version from " + paramInt1 + " to " + paramInt2 + " not recommended.");
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      if ((paramInt1 < 2) && (paramInt2 > 1))
        createCustomVariableTables(paramSQLiteDatabase);
      if ((paramInt1 < 3) && (paramInt2 > 2))
        createECommerceTables(paramSQLiteDatabase);
    }
  }
}