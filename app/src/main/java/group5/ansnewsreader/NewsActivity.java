package group5.ansnewsreader;

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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class NewsActivity extends AppCompatActivity {

    private DrawerLayout newsDrawerLayout;
    private ActionBarDrawerToggle newsToggle;

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
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        //Toolbar
        Toolbar menutoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(menutoolbar);

        //Navigation Drawer
        newsDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        newsToggle= new ActionBarDrawerToggle(this, newsDrawerLayout, R.string.open, R.string.close);

        newsDrawerLayout.addDrawerListener(newsToggle);
        newsToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(getResources().getBoolean(R.bool.yes));

    }

    //Nav Bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (newsToggle.onOptionsItemSelected(item)) {
            return getResources().getBoolean(R.bool.yes);
        }
        return super.onOptionsItemSelected(item);
    }

    //Menu
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
                case 2: return new TrendingFragment();
                case 3: return new TrendingFragment();
                case 4: return new TrendingFragment();
            }
            return new Fragment(); //failcase
        }

        @Override
        public CharSequence getPageTitle(int page) {
            //return a tab title corresponding to the specified page
            return titles[page];
        }
    }

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

}
