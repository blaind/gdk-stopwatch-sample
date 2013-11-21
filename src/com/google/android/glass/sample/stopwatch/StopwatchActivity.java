/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.glass.sample.stopwatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceView;

/**
 * Service owning the LiveCard living in the timeline.
 */
public class StopwatchActivity extends Activity {

    private ChronometerDrawer mCallback;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /*
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }*/

    @Override
    public void onStart() {
        super.onStart();

        mCallback = new ChronometerDrawer(this);

        SurfaceView sV = new SurfaceView(this);
        sV.getHolder().addCallback(mCallback);
        setContentView(sV);


        //Intent menuIntent = new Intent(this, MenuActivity.class);
        //mLiveCard.setAction(PendingIntent.getActivity(this, 0, menuIntent, 0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.stopwatch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection.
        switch (item.getItemId()) {
            case R.id.stop:
                stopService(new Intent(this, StopwatchService.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        // Nothing else to do, closing the Activity.
        finish();
    }

}
