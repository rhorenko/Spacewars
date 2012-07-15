package com.bestcoolfungames.antsmasher.inapp;

import android.text.TextUtils;
import android.util.Log;
import com.bestcoolfungames.antsmasher.inapp.utils.Base64;
import com.bestcoolfungames.antsmasher.inapp.utils.Base64DecoderException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Security
{
  private static final String KEY_FACTORY_ALGORITHM = "RSA";
  private static final SecureRandom RANDOM = new SecureRandom();
  private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
  private static final String TAG = "Security";
  private static HashSet<Long> sKnownNonces = new HashSet();

  public static long generateNonce()
  {
    long l = RANDOM.nextLong();
    sKnownNonces.add(Long.valueOf(l));
    return l;
  }

  public static PublicKey generatePublicKey(String paramString)
  {
    byte[] arrayOfByte;
    try
    {
      arrayOfByte = Base64.decode(paramString);
      PublicKey localPublicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(arrayOfByte));
      return localPublicKey;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new RuntimeException(localNoSuchAlgorithmException);
    }
    catch (InvalidKeySpecException localInvalidKeySpecException)
    {
      Log.e("Security", "Invalid key specification.");
      throw new IllegalArgumentException(localInvalidKeySpecException);
    }
    catch (Base64DecoderException localBase64DecoderException)
    {
      Log.e("Security", "Base64 decoding failed.");
      throw new IllegalArgumentException(localBase64DecoderException);
    }
  }

  public static boolean isNonceKnown(long paramLong)
  {
    return sKnownNonces.contains(Long.valueOf(paramLong));
  }

  public static void removeNonce(long paramLong)
  {
    sKnownNonces.remove(Long.valueOf(paramLong));
  }

  public static boolean verify(PublicKey paramPublicKey, String paramString1, String paramString2)
  {
    byte b = 0;
    try
    {
      Signature localSignature = Signature.getInstance("SHA1withRSA");
      localSignature.initVerify(paramPublicKey);
      localSignature.update(paramString1.getBytes());
      if (!(localSignature.verify(Base64.decode(paramString2))))
      {
        Log.e("Security", "Signature verification failed.");
        return b;
      }
      b = 1;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      Log.e("Security", "NoSuchAlgorithmException.");
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      Log.e("Security", "Invalid key specification.");
    }
    catch (SignatureException localSignatureException)
    {
      Log.e("Security", "Signature exception.");
    }
    catch (Base64DecoderException localBase64DecoderException)
    {
      while (true)
        Log.e("Security", "Base64 decoding failed.");
    }
  }

  public static ArrayList<VerifiedPurchase> verifyPurchase(String paramString1, String paramString2)
  {
    boolean bool;
    ArrayList localArrayList;
    long l1;
    JSONArray localJSONArray;
    if (paramString1 == null)
    {
      Log.e("Security", "data is null");
      localArrayList = null;
    }
    while (true)
    {
      return localArrayList;
      byte b1 = 0;
      if (TextUtils.isEmpty(paramString2))
        break;
      bool = verify(generatePublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArf5JM1tVwJDpodSQpq62GkXZPeew/z3EYg+kbQ7VQ9AJ5ZAFxoqiFWKaeR3778yUEAKjlgl1hgqwgTKtOJVb/okZaxiNmViQCsw+1+FGHErooMF75Egmz3/pKm0k2HfaME8EspWCeWx2/wILw8Z/353x5Xfcg3ujt1c9I/l3bLH9IWay57GYE0Q1g3E5SX/piDoK6ts1NQ18c0drHWk/GRf+BKWLtJ9rHGezba3X0Xko9ZVCZv6KUnkks+uWvWNnKfRlV6LgulMetUIeOMuMtct2nlgmNOvqiY3klMD7uPgo6edsbRSJ/MmjK+xzY58SAcFkoQSWf0yf2UmyHyXzfQIDAQAB"), paramString1, paramString2);
      if (bool)
        break;
      Log.w("Security", "signature does not match data.");
      localArrayList = null;
    }
    byte b2 = 0;
    try
    {
      JSONObject localJSONObject1 = new JSONObject(paramString1);
      l1 = localJSONObject1.optLong("nonce");
      localJSONArray = localJSONObject1.optJSONArray("orders");
      if (localJSONArray != null)
      {
        int i = localJSONArray.length();
        b2 = i;
      }
      if (isNonceKnown(l1))
        break label146;
      Log.w("Security", "Nonce not found: " + l1);
      label146: label328: localArrayList = null;
    }
    catch (JSONException localJSONException1)
    {
      while (true)
        localArrayList = null;
      localArrayList = new ArrayList();
      byte b3 = 0;
      while (true)
      {
        while (b3 >= b2)
          removeNonce(l1);
        try
        {
          JSONObject localJSONObject2 = localJSONArray.getJSONObject(b3);
          Consts.PurchaseState localPurchaseState = Consts.PurchaseState.valueOf(localJSONObject2.getInt("purchaseState"));
          String str1 = localJSONObject2.getString("productId");
          localJSONObject2.getString("packageName");
          long l2 = localJSONObject2.getLong("purchaseTime");
          String str2 = localJSONObject2.optString("orderId", "");
          String str3 = null;
          if (localJSONObject2.has("notificationId"))
            str3 = localJSONObject2.getString("notificationId");
          String str4 = localJSONObject2.optString("developerPayload", null);
          if ((localPurchaseState == Consts.PurchaseState.PURCHASED) && (!(bool)))
            break label328:
          VerifiedPurchase localVerifiedPurchase = new VerifiedPurchase(localPurchaseState, str3, str1, str2, l2, str4);
          localArrayList.add(localVerifiedPurchase);
        }
        catch (JSONException localJSONException2)
        {
          while (true)
          {
            Log.e("Security", "JSON exception: ", localJSONException2);
            ??? = null;
          }
        }
        ++b3;
      }
    }
  }

  public static class VerifiedPurchase
  {
    public String developerPayload;
    public String notificationId;
    public String orderId;
    public String productId;
    public Consts.PurchaseState purchaseState;
    public long purchaseTime;

    public VerifiedPurchase(Consts.PurchaseState paramPurchaseState, String paramString1, String paramString2, String paramString3, long paramLong, String paramString4)
    {
      this.purchaseState = paramPurchaseState;
      this.notificationId = paramString1;
      this.productId = paramString2;
      this.orderId = paramString3;
      this.purchaseTime = paramLong;
      this.developerPayload = paramString4;
    }
  }
}