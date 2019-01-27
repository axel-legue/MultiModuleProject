package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.legue.axel.displayjoke.DisplayJokeActivity;
import com.legue.axel.displayjoke.DisplayJokeConstants;

public class MainActivity extends AppCompatActivity implements EndPointsAsyncTask.EndPointCallback {

    private EndPointsAsyncTask endPointsAsyncTask;
    private Button mTellJokeBtn;
    private InterstitialAd mInterstitialAd;
    private ProgressBar mLoadingJoke;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, BuildConfig.ADMOB_API_APPLICATION_CODE);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(BuildConfig.ADMOB_INTERSTITIAL_API_KEY_TEST);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                loadJoke();
            }
        });

        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mTellJokeBtn = findViewById(R.id.tv_display_joke);
        mTellJokeBtn.setOnClickListener(view -> tellJoke());
        mLoadingJoke = findViewById(R.id.pb_loading_joke);
        mLoadingJoke.setVisibility(View.GONE);
    }

    private void tellJoke() {
        showInterstitial();
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            loadJoke();
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }

    }

    private void loadJoke() {
        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }
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
