package group5.ansnewsreader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by clarentz on 11/25/17.
 */

public class NewsItem {

    private Context context;
    public String title = "default title";
    public String source = "Broken source";
    public String description = "default description";
    public String url;
    public Bitmap thumbnail = null;

    public NewsItem(Context context, String title, String source, String description, String url) {
        this.context = context;
        this.title = title;
        this.source = source;
        this.description = description;
        this.url = url;
        this.thumbnail = BitmapFactory.decodeStream(context.getResources().openRawResource(R.raw.fail));
    }
}
