package com.revmob.ads;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.protocol.HttpContext;

public class HTTPHelper
{
  private static final String REVMOB = "RevMob";
  private LastRedirectHandler lastRedirectHandler;

  public HTTPHelper()
  {
    this.lastRedirectHandler = new LastRedirectHandler(this);
  }

  private String getResponse(HttpEntity paramHttpEntity)
  {
    StringBuffer localStringBuffer;
    InputStreamReader localInputStreamReader;
    if (paramHttpEntity == null)
    {
      str = null;
      return str;
    }
    String str = "";
    try
    {
      localStringBuffer = new StringBuffer(1024);
      localInputStreamReader = new InputStreamReader(paramHttpEntity.getContent(), "UTF-8");
      char[] arrayOfChar = new char[1024];
      int i = localInputStreamReader.read(arrayOfChar, 0, 1023);
      if (i <= 0)
        break label90;
      label90: localStringBuffer.append(arrayOfChar, 0, i);
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        while (true)
          Log.w("RevMob", "Read error.");
        str = localStringBuffer.toString();
        localInputStreamReader.close();
      }
    }
  }

  private HttpResponse request(HttpRequestBase paramHttpRequestBase)
    throws SSLException
  {
    Log.d("RevMob", paramHttpRequestBase.getRequestLine().toString());
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
    localDefaultHttpClient.setRedirectHandler(this.lastRedirectHandler);
    try
    {
      HttpResponse localHttpResponse = localDefaultHttpClient.execute(paramHttpRequestBase);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(localHttpResponse.getStatusLine().getStatusCode());
      Log.d("RevMob", String.format("HTTP status code: %s", arrayOfObject));
      return localHttpResponse;
    }
    catch (UnknownHostException localUnknownHostException)
    {
      Log.w("RevMob", "Error on requesting path " + paramHttpRequestBase.getRequestLine() + ". Is the device connected to the internet?", localUnknownHostException);
      ??? = null;
    }
    catch (SSLException localSSLException)
    {
      throw localSSLException;
    }
    catch (IOException localIOException)
    {
      while (true)
        Log.w("RevMob", "Error on requesting path " + paramHttpRequestBase.getRequestLine() + ". Did the device lost its connection?", localIOException);
    }
  }

  private HttpResponse requestDealingWithSSLErrors(HttpRequestBase paramHttpRequestBase)
  {
    Object localObject;
    HttpResponse localHttpResponse2;
    try
    {
      localHttpResponse2 = request(paramHttpRequestBase);
      localObject = localHttpResponse2;
      label79: return localObject;
    }
    catch (SSLException localSSLException1)
    {
      try
      {
        if (!(paramHttpRequestBase.getURI().toString().startsWith("https://")))
          break label79;
        paramHttpRequestBase.setURI(new URI(paramHttpRequestBase.getURI().toString().replace("https://", "http://")));
        HttpResponse localHttpResponse1 = request(paramHttpRequestBase);
        localObject = localHttpResponse1;
      }
      catch (SSLException localSSLException2)
      {
        Log.i("RevMob", "Problem with SSL. What is the version of your Android?");
        localObject = null;
      }
      catch (URISyntaxException localURISyntaxException)
      {
        while (true)
          Log.e("RevMob", "Invalid url: " + paramHttpRequestBase.getURI().toString());
      }
    }
  }

  public HttpResponse get(String paramString)
  {
    return requestDealingWithSSLErrors(new HttpGet(paramString));
  }

  public InputStream getAndReturnTheStream(String paramString)
  {
    InputStream localInputStream2;
    try
    {
      localInputStream2 = get(paramString).getEntity().getContent();
      InputStream localInputStream1 = localInputStream2;
      return localInputStream1;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      Log.e("RevMob", "Read error.");
      ??? = null;
    }
    catch (IOException localIOException)
    {
      while (true)
        Log.w("RevMob", "Read error.");
    }
  }

  public LastRedirectHandler getLastRedirectHandler()
  {
    return this.lastRedirectHandler;
  }

  public HttpResponse post(String paramString1, String paramString2)
  {
    HttpPost localHttpPost;
    try
    {
      localHttpPost = new HttpPost(paramString1);
      localHttpPost.setEntity(new StringEntity(paramString2));
      localHttpPost.setHeader("content-type", "application/json");
      HttpResponse localHttpResponse2 = requestDealingWithSSLErrors(localHttpPost);
      HttpResponse localHttpResponse1 = localHttpResponse2;
      return localHttpResponse1;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      Log.e("RevMob", "Encoding error.", localUnsupportedEncodingException);
      ??? = null;
    }
    catch (RuntimeException localRuntimeException)
    {
      while (true)
        Log.e("RevMob", "Unknown error", localRuntimeException);
    }
  }

  public String postAndGetResponse(String paramString1, String paramString2)
  {
    String str2;
    try
    {
      str2 = getResponse(post(paramString1, paramString2).getEntity());
      String str1 = str2;
      return str1;
    }
    catch (NullPointerException localNullPointerException)
    {
      while (true)
        ??? = null;
    }
  }

  public int postAndGetStatusCode(String paramString1, String paramString2)
  {
    int i;
    int j;
    try
    {
      j = post(paramString1, paramString2).getStatusLine().getStatusCode();
      i = j;
      return i;
    }
    catch (NullPointerException localNullPointerException)
    {
      while (true)
        i = 0;
    }
  }

  public class LastRedirectHandler extends DefaultRedirectHandler
  {
    private URI lastRedirectedUri;

    public String getLastRedirectedUrl()
    {
      if (this.lastRedirectedUri != null)
        paramString = this.lastRedirectedUri.toString();
      return paramString;
    }

    public URI getLocationURI(, HttpContext paramHttpContext)
      throws ProtocolException
    {
      this.lastRedirectedUri = super.getLocationURI(paramHttpResponse, paramHttpContext);
      return this.lastRedirectedUri;
    }

    public boolean isRedirectRequested(, HttpContext paramHttpContext)
    {
      return super.isRedirectRequested(paramHttpResponse, paramHttpContext);
    }
  }
}