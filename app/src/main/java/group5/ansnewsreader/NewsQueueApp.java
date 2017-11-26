package group5.ansnewsreader;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by clarentz on 11/23/17.
 */
//This is a singleton, which can be access globally

public class NewsQueueApp extends Application {
    private RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(this.getApplicationContext());
    }

    public RequestQueue getQueue() {
        return queue;
    }
}
