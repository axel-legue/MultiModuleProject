package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-4205057744646990~8261001730");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                loadJoke();
            }
        });

        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mTellJokeBtn = findViewById(R.id.tv_display_joke);
        mTellJokeBtn.setOnClickListener(view -> tellJoke());
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
