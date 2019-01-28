package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class EndPointAsyncTaskTest {

    @Test
    public void testVerifyAsyncResponse() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);
        new EndPointsAsyncTask(new EndPointsAsyncTask.EndPointCallback() {
            @Override
            public void processFinish(String output) {
                // TEST response data
                assertNotNull(output);
                assertTrue(output.length() > 0);
                signal.countDown();
            }
        }).execute();
        signal.await();
    }


}
