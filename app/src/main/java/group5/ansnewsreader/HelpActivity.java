package group5.ansnewsreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //Toolbar
        Toolbar menutoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(menutoolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.menu_action_help));
        getSupportActionBar().setDisplayShowHomeEnabled(getResources().getBoolean(R.bool.yes));
        getSupportActionBar().setDisplayHomeAsUpEnabled(getResources().getBoolean(R.bool.yes));
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
