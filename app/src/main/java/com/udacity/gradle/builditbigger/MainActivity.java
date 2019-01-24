package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.legue.axel.jokeprovider.JokeProvider;

import java.util.ArrayList;

import hmi.swl.renault.displayjoke.DisplayJokeActivity;
import hmi.swl.renault.displayjoke.DisplayJokeConstants;


public class MainActivity extends AppCompatActivity {
    private JokeProvider jokeProvider;
    private ArrayList<String> jokeList;
    private int numberOfJokes;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-4205057744646990~8261001730");
        jokeProvider = new JokeProvider();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        Intent intent = new Intent(this, DisplayJokeActivity.class);
        jokeList = jokeProvider.getJokeList();
        numberOfJokes = jokeList.size();
        if (i + 1 > numberOfJokes) {
            i = 0;
        }
        intent.putExtra(DisplayJokeConstants.JOKE_EXTRA, jokeList.get(i));
        startActivity(intent);
        i++;
    }
}
