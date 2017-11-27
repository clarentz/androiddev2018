package group5.ansnewsreader;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SportFragment extends Fragment {

    public ArrayList<NewsItem> newsArrayList = new ArrayList<>();
    ListView sportListView;
    NewsAdapter sportNewsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View newsView = inflater.inflate(R.layout.fragment_sport, container, false);

        sportNewsAdapter = new NewsAdapter(getContext(), newsArrayList);
        sportListView = newsView.findViewById(R.id.sport_fragment);
        sportListView.setAdapter(sportNewsAdapter);

        ArrayList<String> urlList = new ArrayList<>();

        //URL List and getting JSON from them
        urlList.add("https://newsapi.org/v2/top-headlines?sources=bleacher-report&apiKey=7e553ac61ba547218e4e700910752186"); //Bleacher Report
        for (int i = 0; i < 2; i++) {
            JSONfunction(urlList.get(0), i);
            }

        urlList.add("https://newsapi.org/v2/top-headlines?sources=espn&apiKey=7e553ac61ba547218e4e700910752186"); //ESPN
        for (int i = 0; i < 2; i++) {
            JSONfunction(urlList.get(1), i);
        }


        urlList.add("https://newsapi.org/v2/top-headlines?sources=fox-sports&apiKey=7e553ac61ba547218e4e700910752186"); //Fox Sports
        for (int i = 0; i < 2; i++) {
            JSONfunction(urlList.get(2), i);
        }

        urlList.add("https://newsapi.org/v2/top-headlines?sources=talksport&apiKey=7e553ac61ba547218e4e700910752186"); //TalkSport
        for (int i = 0; i < 2; i++) {
            JSONfunction(urlList.get(3), i);
        }

        urlList.add("https://newsapi.org/v2/top-headlines?sources=bbc-sport&apiKey=7e553ac61ba547218e4e700910752186"); //BBC Sport
        for (int i = 0; i < 2; i++) {
            JSONfunction(urlList.get(4), i);
        }

        //Open a new activity when click on a newsItem
        sportListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsItem newsItem = (NewsItem) sportListView.getItemAtPosition(position);
                Intent intent = new Intent(getContext(),WebActivity.class);
                intent.putExtra("url",newsItem.url);
                intent.putExtra("source",newsItem.source);
                startActivity(intent);
            }
        });

        return newsView;
    }

    public void JSONfunction(String url, final int index) {

        JsonObjectRequest newsRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String titleJson = null;
                        String sourceJson = null;
                        String descriptionJson = null;
                        String thumbnailJson = null;
                        String urlJson = null;

                        //Log
                        Log.i("SportJSON", "responsed!");

                        try {

                            //getting the first JSON object of the JSON array
                            JSONArray articlesArray = response.getJSONArray("articles");
                            JSONObject theJson = articlesArray.getJSONObject(index);

                            //true JSON extraction begin
                            titleJson = theJson.getString("title");
                            thumbnailJson = theJson.getString("urlToImage");
                            sourceJson = "from " + theJson.getJSONObject("source").getString("name");
                            descriptionJson = theJson.getString("description");
                            urlJson = theJson.getString("url");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //parse to the newsItem
                        NewsItem newsItem = new NewsItem(getContext(), titleJson, sourceJson, descriptionJson, urlJson);
                        newsArrayList.add(newsItem);
                        final int position = newsArrayList.indexOf(newsItem);
                        sportNewsAdapter.notifyDataSetChanged();

                        //Bitmap Listener
                        Response.Listener<Bitmap>bitmapListener = new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                newsArrayList.get(position).thumbnail = response;
                                sportNewsAdapter.notifyDataSetChanged();
                            }
                        };

                        //make Image request
                        ImageRequest imageRequest = new ImageRequest(
                                thumbnailJson,
                                bitmapListener, 0, 0, ImageView.ScaleType.CENTER,
                                Bitmap.Config.ARGB_8888,null);

                        ((NewsQueueApp)getActivity().getApplication()).getQueue().add(imageRequest);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        ((NewsQueueApp)getActivity().getApplication()).getQueue().add(newsRequest);//JSON Request
    }

}

