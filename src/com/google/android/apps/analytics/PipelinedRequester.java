package com.google.android.apps.analytics;

import android.util.Log;
import java.io.IOException;
import java.net.Socket;
import org.apache.http.Header;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.params.BasicHttpParams;

class PipelinedRequester
{
  Callbacks callbacks;
  boolean canPipeline;
  DefaultHttpClientConnection connection;
  HttpHost host;
  int lastStatusCode;
  SocketFactory socketFactory;

  public PipelinedRequester(HttpHost paramHttpHost)
  {
    this(paramHttpHost, new PlainSocketFactory());
  }

  public PipelinedRequester(HttpHost paramHttpHost, SocketFactory paramSocketFactory)
  {
    this.connection = new DefaultHttpClientConnection();
    this.canPipeline = true;
    this.host = paramHttpHost;
    this.socketFactory = paramSocketFactory;
  }

  private void closeConnection()
  {
    if ((this.connection != null) && (this.connection.isOpen()));
    try
    {
      this.connection.close();
      label24: return;
    }
    catch (IOException localIOException)
    {
      break label24:
    }
  }

  private void maybeOpenConnection()
    throws IOException
  {
    if ((this.connection == null) || (!(this.connection.isOpen())))
    {
      BasicHttpParams localBasicHttpParams = new BasicHttpParams();
      Socket localSocket1 = this.socketFactory.createSocket();
      Socket localSocket2 = this.socketFactory.connectSocket(localSocket1, this.host.getHostName(), this.host.getPort(), null, 0, localBasicHttpParams);
      this.connection.bind(localSocket2, localBasicHttpParams);
    }
  }

  public void addRequest(HttpRequest paramHttpRequest)
    throws HttpException, IOException
  {
    maybeOpenConnection();
    this.connection.sendRequestHeader(paramHttpRequest);
  }

  public void finishedCurrentRequests()
  {
    closeConnection();
  }

  public void installCallbacks(Callbacks paramCallbacks)
  {
    this.callbacks = paramCallbacks;
  }

  public void sendRequests()
    throws IOException, HttpException
  {
    HttpResponse localHttpResponse;
    this.connection.flush();
    HttpConnectionMetrics localHttpConnectionMetrics = this.connection.getMetrics();
    if (localHttpConnectionMetrics.getResponseCount() > localHttpConnectionMetrics.getRequestCount())
    {
      localHttpResponse = this.connection.receiveResponseHeader();
      if (!(localHttpResponse.getStatusLine().getProtocolVersion().greaterEquals(HttpVersion.HTTP_1_1)))
      {
        this.callbacks.pipelineModeChanged(false);
        this.canPipeline = false;
      }
      Header[] arrayOfHeader = localHttpResponse.getHeaders("Connection");
      if (arrayOfHeader != null)
      {
        int i = arrayOfHeader.length;
        for (int j = 0; j < i; ++j)
          if ("close".equalsIgnoreCase(arrayOfHeader[j].getValue()))
          {
            this.callbacks.pipelineModeChanged(false);
            this.canPipeline = false;
          }
      }
      this.lastStatusCode = localHttpResponse.getStatusLine().getStatusCode();
      if (this.lastStatusCode == 200)
        break label182;
      this.callbacks.serverError(this.lastStatusCode);
      closeConnection();
    }
    while (true)
    {
      do
      {
        return;
        label182: this.connection.receiveResponseEntity(localHttpResponse);
        localHttpResponse.getEntity().consumeContent();
        this.callbacks.requestSent();
        if (GoogleAnalyticsTracker.getInstance().getDebug())
          Log.v("GoogleAnalyticsTracker", "HTTP Response Code: " + localHttpResponse.getStatusLine().getStatusCode());
      }
      while (this.canPipeline);
      closeConnection();
    }
  }

  static abstract interface Callbacks
  {
    public abstract void pipelineModeChanged(boolean paramBoolean);

    public abstract void requestSent();

    public abstract void serverError(int paramInt);
  }
}