package com.example.joannapacia.newsreader.utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.example.joannapacia.newsreader.model.LinkData;
import com.example.joannapacia.newsreader.adapters.LinkAdapter;

import java.util.ArrayList;

/**
 * Created by joannapacia on 07/03/17.
 */

public class AsyncListViewLoader extends AsyncTask<String, Void, Void> {

    private String TAG = AsyncListViewLoader.class.getSimpleName();

    private ProgressDialog pDialog;
    private Context mContext;
    ListView newsList;

    ArrayList<LinkData> dataList = new ArrayList<>();

    // Constructor
    public AsyncListViewLoader(Context context, ListView list)
    {
        mContext = context;
        newsList = list;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected Void doInBackground(String... aurl) {
        HttpHandler sh = new HttpHandler();

        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(aurl[0]);

        Log.e(TAG, "Response from url: " + jsonStr);

        if (jsonStr != null) {
            try {
                org.json.JSONObject jsonObj = new org.json.JSONObject(jsonStr);

                // Getting JSON Array node
                // JSONArray news = jsonObj.getJSONArray("contacts");
                org.json.JSONArray news = jsonObj.getJSONArray("articles");
                Log.v(TAG, "Size of the jsonarray: " + String.valueOf(news.length()));
                // looping through all news
                for (int i = 0; i < news.length(); i++) {
                    org.json.JSONObject c = news.getJSONObject(i);

                    org.json.JSONObject image = c.getJSONObject("image");
                    String image_url = image.getString("url");
                    String headline = c.getString("headline_override");
                    String description = c.getString("headline");
                    String link = c.getString("link");

                    // tmp model for single news
                    LinkData data = new LinkData();
                    data.setHeadline(headline);
                    data.setDesciption(description);
                    data.setImage(image_url);
                    data.setLink(link);
                    // adding news to list
                    dataList.add(data);
                }
            } catch (final org.json.JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        // Dismiss the progress dialog
        if (pDialog.isShowing())
            pDialog.dismiss();

        // Updating parsed JSON data into ListView
        LinkAdapter customAdapter = new LinkAdapter(mContext, dataList);
        newsList.setAdapter(customAdapter);
    }
}

