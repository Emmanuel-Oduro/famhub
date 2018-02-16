package com.example.nanayawzaza.farmiconn.Application;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

public class AppController  extends Application
{
  private static AppController Instance;
  public String TAG = AppController.class.getSimpleName();
  private RequestQueue requestQueue;
  
  public AppController()
  {
    Log.e(this.TAG, "Constructor called ...");
  }
  
  public static AppController getInstance()
  {
    try
    {
      if (Instance == null) {
        Instance = new AppController();
      }
      AppController localAppController = Instance;
      return localAppController;
    }
    finally {}
  }
  
  private RequestQueue getRequestQueue()
  {
    if (this.requestQueue == null) {
      this.requestQueue = Volley.newRequestQueue(getApplicationContext());
    }
    return this.requestQueue;
  }
  
  public void CancelPenddingRequest(Object paramObject)
  {
    if (this.requestQueue != null) {
      this.requestQueue.cancelAll(paramObject);
    }
  }
  
  public <T> void addToRequestQueue(Request<T> paramRequest)
  {
    paramRequest.setTag(this.TAG);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramRequest.getUrl();
    VolleyLog.d("Adding request", arrayOfObject);
    getRequestQueue().add(paramRequest);
  }
  
  public <T> void addToRequestQueue(Request<T> paramRequest, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      paramString = this.TAG;
    }
    paramRequest.setTag(paramString);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramRequest.getUrl();
    VolleyLog.d("Adding request", arrayOfObject);
    getRequestQueue().add(paramRequest);
  }
  
  public void onCreate()
  {
    super.onCreate();
    Log.i(this.TAG, "Oncreate called......");
    Instance = this;
  }
}



/* Location:           C:\Users\Nana Yaw Zaza\Desktop\work\dex2jar-0.0.9.15\classes_dex2jar.jar

 * Qualified Name:     com.ziptech.volleywork.Application.AppController

 * JD-Core Version:    0.7.0.1

 */