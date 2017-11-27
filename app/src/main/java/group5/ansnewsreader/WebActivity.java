package group5.ansnewsreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        //Toolbar
        Toolbar menutoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(menutoolbar);

        String webViewsource = getIntent().getStringExtra("source");
        getSupportActionBar().setTitle(webViewsource);
        getSupportActionBar().setDisplayShowHomeEnabled(getResources().getBoolean(R.bool.yes));
        getSupportActionBar().setDisplayHomeAsUpEnabled(getResources().getBoolean(R.bool.yes));

        //Web View
        String webViewURL = getIntent().getStringExtra("url"); //the url is parsed from NewsItem when the WebActivity created in the main NewsActivity
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setUserAgentString("Android"); //on order to get the mobile look, now the web look
        webView.setInitialScale(80); //scaling with max value is 100
        webView.getSettings().setLoadWithOverviewMode(true); //
        webView.getSettings().setUseWideViewPort(true); //it does som
        //webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(webViewURL);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
