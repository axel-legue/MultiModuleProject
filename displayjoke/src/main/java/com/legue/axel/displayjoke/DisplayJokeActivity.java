package com.legue.axel.displayjoke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

public class DisplayJokeActivity extends AppCompatActivity {

    private String jokeToDisplay;
    private TextView mJokeTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);

        Intent intent = getIntent();
        if (intent.hasExtra(DisplayJokeConstants.JOKE_EXTRA)) {
            jokeToDisplay = intent.getStringExtra(DisplayJokeConstants.JOKE_EXTRA);
        } else {
            jokeToDisplay = "";
        }


        mJokeTextView = (TextView) findViewById(R.id.tv_joke_displayed);

        displayJoke(jokeToDisplay);
    }

    private void displayJoke(String jokeToDisplay) {
        if (jokeToDisplay != null && !TextUtils.isEmpty(jokeToDisplay)) {
            mJokeTextView.setText(jokeToDisplay);
        } else {
            mJokeTextView.setText("No Joke provided to the Intent");
        }
    }
}
