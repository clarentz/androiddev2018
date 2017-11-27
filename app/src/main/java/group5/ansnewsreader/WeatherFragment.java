package group5.ansnewsreader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View weatherView = inflater.inflate(R.layout.fragment_weather, container, false);

        String weatherURL = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22hanoi%2C%20vi%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

        final TextView temperature = weatherView.findViewById(R.id.temperature);
        final TextView condition = weatherView.findViewById(R.id.condition);
        final TextView day1day = weatherView.findViewById(R.id.forecast1day);
        final TextView day1temp = weatherView.findViewById(R.id.forecast1temp);
        final TextView day1text = weatherView.findViewById(R.id.forecast1text);
        final TextView day2day = weatherView.findViewById(R.id.forecast2day);
        final TextView day2temp = weatherView.findViewById(R.id.forecast2temp);
        final TextView day2text = weatherView.findViewById(R.id.forecast2text);
        final TextView day3day = weatherView.findViewById(R.id.forecast3day);
        final TextView day3temp = weatherView.findViewById(R.id.forecast3temp);
        final TextView day3text = weatherView.findViewById(R.id.forecast3text);


        JsonObjectRequest weatherRequest = new JsonObjectRequest(Request.Method.GET, weatherURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String temperatureJson = null;
                        String conditionJson = null;
                        String day1dayJson = null;
                        String day1tempJson = null;
                        String day1textJson = null;
                        String day2dayJson = null;
                        String day2tempJson = null;
                        String day2textJson = null;
                        String day3dayJson = null;
                        String day3tempJson = null;
                        String day3textJson = null;


                        JSONObject channel;

                        try {
                            //Initalize dem shits
                            channel = response.getJSONObject("query")
                                    .getJSONObject("results")
                                    .getJSONObject("channel");
                            JSONArray forecastArray = channel.getJSONObject("item")
                                    .getJSONArray("forecast");
                            JSONObject forecastJson1 = forecastArray.getJSONObject(0);
                            JSONObject forecastJson2 = forecastArray.getJSONObject(1);
                            JSONObject forecastJson3 = forecastArray.getJSONObject(2);

                            //JSON extraction begin
                            temperatureJson = channel.getJSONObject("item")
                                    .getJSONObject("condition")
                                    .getString("temp") + "℉";
                            conditionJson = "Weather: " + channel.getJSONObject("item")
                                    .getJSONObject("condition")
                                    .getString("text");
                            day1dayJson = forecastJson1.getString("day");
                            day1tempJson = forecastJson1.getString("low") + "℉" + " ~ " + forecastJson1.getString("high") + "℉";
                            day1textJson = forecastJson1.getString("text");
                            day2dayJson = forecastJson2.getString("day");
                            day2tempJson = forecastJson2.getString("low") + "℉" + " ~ " + forecastJson2.getString("high" + "℉");
                            day2textJson = forecastJson2.getString("text");
                            day3dayJson = forecastJson3.getString("day");
                            day3tempJson = forecastJson3.getString("low") + "℉" + " ~ " + forecastJson3.getString("high" + "℉");
                            day3textJson = forecastJson3.getString("text");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        temperature.setText(temperatureJson);
                        condition.setText(conditionJson);
                        day1day.setText(day1dayJson);
                        day1temp.setText(day1tempJson);
                        day1text.setText(day1textJson);
                        day2day.setText(day2dayJson);
                        day2temp.setText(day2tempJson);
                        day2text.setText(day2textJson);
                        day3day.setText(day3dayJson);
                        day3temp.setText(day3tempJson);
                        day3text.setText(day3textJson);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        ((NewsQueueApp)getActivity().getApplication()).getQueue().add(weatherRequest);//JSON Request
        return weatherView;
    }
}