package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.legue.axel.displayjoke.DisplayJokeActivity;
import com.legue.axel.displayjoke.DisplayJokeConstants;

public class MainActivity extends AppCompatActivity implements EndPointsAsyncTask.EndPointCallback {
    private EndPointsAsyncTask endPointsAsyncTask;
    private Button mTellJokeBtn;
    private ProgressBar mLoadingJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTellJokeBtn = findViewById(R.id.tv_display_joke);
        mTellJokeBtn.setOnClickListener(view -> tellJoke());
        mLoadingJoke = findViewById(R.id.pb_loading_joke);
        mLoadingJoke.setVisibility(View.GONE);
    }

    private void tellJoke() {
        mLoadingJoke.setVisibility(View.VISIBLE);
        endPointsAsyncTask = new EndPointsAsyncTask(this);
        endPointsAsyncTask.execute();
    }

    @Override
    public void processFinish(String output) {
        mLoadingJoke.setVisibility(View.GONE);
        Intent intent = new Intent(this, DisplayJokeActivity.class);
        intent.putExtra(DisplayJokeConstants.JOKE_EXTRA, output);
        startActivity(intent);
    }
}
