package group5.ansnewsreader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by clarentz on 11/25/17.
 */

public class NewsAdapter extends BaseAdapter {
    private ArrayList<NewsItem> newsArrayList;
    private LayoutInflater newslayoutinflater;
    private Context context;

    public NewsAdapter(Context context, ArrayList<NewsItem> newsArrayList) {
        this.context = context;
        this.newslayoutinflater = LayoutInflater.from(context);
        this.newsArrayList = newsArrayList;
    }

    @Override
    public int getCount() {
        return newsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        TextView title;
        ImageView thumbnail;
        TextView source;
        TextView description;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder newsHolder;
        if (convertView == null) {
            convertView = newslayoutinflater.inflate(R.layout.item_news, null);
            newsHolder = new ViewHolder();
            newsHolder.thumbnail = convertView.findViewById(R.id.thumbnail);
            newsHolder.title = convertView.findViewById(R.id.title);
            newsHolder.source = convertView.findViewById(R.id.source);
            newsHolder.description = convertView.findViewById(R.id.description);
            convertView.setTag(newsHolder);
        }
        else {
            newsHolder = (ViewHolder)convertView.getTag();
        }

        NewsItem newsItem = this.newsArrayList.get(position);
        newsHolder.title.setText(newsItem.title);
        newsHolder.source.setText(newsItem.source);
        newsHolder.description.setText(newsItem.description);
        newsHolder.thumbnail.setImageBitmap(newsItem.thumbnail);

        return convertView;
    }
}
