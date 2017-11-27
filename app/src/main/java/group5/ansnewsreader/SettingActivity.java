package group5.ansnewsreader;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class SettingActivity extends AppCompatActivity {
    Button b1, b2;
    LinearLayout newsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //Toolbar
        Toolbar menutoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(menutoolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.menu_action_about));
        getSupportActionBar().setDisplayShowHomeEnabled(getResources().getBoolean(R.bool.yes));
        getSupportActionBar().setDisplayHomeAsUpEnabled(getResources().getBoolean(R.bool.yes));

        //Button
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        newsLayout = (LinearLayout) findViewById(R.id.news_item);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.normal_mode));
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.night_mode));
            }
        });
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

