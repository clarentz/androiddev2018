package group5.ansnewsreader;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URL;

public class NewsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout newsDrawerLayout;
    private ActionBarDrawerToggle newsToggle;
    private TabLayout tabLayout;
    private NavigationView navView;
    private CharSequence newsDrawerTitle;
    private CharSequence newsTitle;


    private RequestQueue queue;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("LOG", " ANS Created ");
        setContentView(R.layout.activity_news);

        //View Pager
        PagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setOffscreenPageLimit(getResources().getInteger(R.integer.offscreenpagelimit));
        pager.setAdapter(adapter);

        //Tab
        tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        //Toolbar
        Toolbar menutoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(menutoolbar);

        //Navigation Drawer
        newsDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        newsToggle= new ActionBarDrawerToggle(this, newsDrawerLayout, R.string.open, R.string.close);
        //set click listener on drawer icon to open
        menutoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsDrawerLayout.openDrawer(Gravity.START);
            }
        });

        newsDrawerLayout.addDrawerListener(newsToggle);
        newsToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(getResources().getBoolean(R.bool.yes));

        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
        //Volley
        //queue = Volley.newRequestQueue(this);
    }

    //Navigation
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        newsDrawerLayout.closeDrawers();
        switch (item.getItemId()) {

            case R.id.nav_trending:
                tabLayout.getTabAt(0).select();
                break;

            case R.id.nav_weather:
                tabLayout.getTabAt(1).select();
                break;

            case R.id.nav_science:
                tabLayout.getTabAt(2).select();
                break;

            case R.id.nav_sport:
                tabLayout.getTabAt(3).select();
                break;

            case R.id.nav_tech:
                tabLayout.getTabAt(4).select();
                break;
        }
        return true;
    }

    //Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            /*
            //Unnecessary
            case R.id.action_refresh:

                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("ANSNewsReader", "Json response " + response);
                    }
                };

                Response.ErrorListener errorlistener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                };

                url = "http://www.google.com";
                StringRequest urlrequest = new StringRequest(Request.Method.GET, url, listener, errorlistener);

                queue.add(urlrequest);
                queue.start();

                break;
            */

            case R.id.action_setting:
                Intent settingIntert = new Intent(NewsActivity.this, SettingActivity.class);
                NewsActivity.this.startActivity(settingIntert);
                break;

            case R.id.action_search:

                break;

            case R.id.action_about:
                Intent aboutIntert = new Intent(NewsActivity.this, AboutActivity.class);
                NewsActivity.this.startActivity(aboutIntert);
                break;

            case R.id.action_help:
                Intent actionIntert = new Intent(NewsActivity.this, HelpActivity.class);
                NewsActivity.this.startActivity(actionIntert);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuinflater = getMenuInflater();
        menuinflater.inflate(R.menu.news_option_menu, menu);
        return true;
    }

    //Tab
    public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
        private final int PAGE_COUNT = getResources().getInteger(R.integer.pagecount);
        private String titles[] = {getResources().getString(R.string.trending), getResources().getString(R.string.weather), getResources().getString(R.string.science), getResources().getString(R.string.sport), getResources().getString(R.string.tech)};

        public HomeFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int page) {
            //return an instance of Fragment corresponding to the specified page
            switch (page) {
                case 0: return new TrendingFragment();
                case 1: return new WeatherFragment();
                case 2: return new ScienceFragment();
                case 3: return new SportFragment();
                case 4: return new TechnologyFragment();
            }
            return new Fragment(); //failcase
        }

        @Override
        public CharSequence getPageTitle(int page) {
            //return a tab title corresponding to the specified page
            return titles[page];
        }
    }

/*
    //Tracking Logs

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("LOG", " ANS Started ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LOG", " ANS Resumed ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LOG", " ANS Paused ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LOG", " ANS Stopped ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("LOG", " ANS Destroyed ");
    }
*/

}
