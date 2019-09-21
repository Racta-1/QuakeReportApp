/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.example.android.quakereport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    public static final String TAG = EarthquakeActivity.class.getName();

    /** Sample JSON response for a USGS query */
    private static final String SAMPLE_JSON_RESPONSE = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2015-12-01&endtime=2017-01-02&format=geojson&minmagnitude=2.0";
    static final int LOADER_ID = 11;

    TextView mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);


        mEmptyView = (TextView) findViewById(R.id.empty_view);


//        new EarthQuakeAsyncTask().execute(SAMPLE_JSON_RESPONSE);
        getSupportLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();



    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<List<Earthquake>>(this) {
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                forceLoad();

            }


            @Override
            public List<Earthquake> loadInBackground() {
                // Get the list of earthquakes
                List<Earthquake> earthquakeList = QueryUtils.fetchEarthquakeData(SAMPLE_JSON_RESPONSE);

                return earthquakeList;

            }
        };
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> data) {
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthquakeAdapter adapter = new EarthquakeAdapter(this,data);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
        earthquakeListView.setEmptyView(mEmptyView);


    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {

    }


//    private class EarthQuakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>> {
//
//        @Override
//        protected List<Earthquake> doInBackground(String... strings) {
//            // Get the list of earthquakes
//            List<Earthquake> earthquakeList = QueryUtils.fetchEarthquakeData(SAMPLE_JSON_RESPONSE);
//            return earthquakeList;
//        }
//
//        @Override
//        protected void onPostExecute(List<Earthquake> earthquakes) {
//            super.onPostExecute(earthquakes);
//            // Find a reference to the {@link ListView} in the layout
//            ListView earthquakeListView = (ListView) findViewById(R.id.list);
//
//            // Create a new {@link ArrayAdapter} of earthquakes
//            EarthquakeAdapter adapter = new EarthquakeAdapter(getApplicationContext(),earthquakes);
//
//            // Set the adapter on the {@link ListView}
//            // so the list can be populated in the user interface
//            earthquakeListView.setAdapter(adapter);
//        }
//    }

}
