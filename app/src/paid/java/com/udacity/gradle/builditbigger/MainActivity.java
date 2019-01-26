package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.legue.axel.displayjoke.DisplayJokeActivity;
import com.legue.axel.displayjoke.DisplayJokeConstants;

public class MainActivity extends AppCompatActivity implements EndPointsAsyncTask.EndPointCallback {
    private EndPointsAsyncTask endPointsAsyncTask;
    private Button mTellJokeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTellJokeBtn = findViewById(R.id.tv_display_joke);
        mTellJokeBtn.setOnClickListener(view -> tellJoke());
    }
    private void tellJoke() {
        endPointsAsyncTask = new EndPointsAsyncTask(this);
        endPointsAsyncTask.execute();
    }

    @Override
    public void processFinish(String output) {
        Intent intent = new Intent(this, DisplayJokeActivity.class);
        intent.putExtra(DisplayJokeConstants.JOKE_EXTRA, output);
        startActivity(intent);
    }
}
