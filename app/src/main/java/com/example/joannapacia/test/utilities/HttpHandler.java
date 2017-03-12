package com.example.joannapacia.test.utilities;

/**
 * Created by joannapacia on 07/03/17.
 */

public class HttpHandler {
    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler() {
    }

    public String makeServiceCall(String reqUrl) {
        String response = null;
        try {
            java.net.URL url = new java.net.URL(reqUrl);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response
            java.io.InputStream in = new java.io.BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (java.net.MalformedURLException e) {
            android.util.Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (java.net.ProtocolException e) {
            android.util.Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (java.io.IOException e) {
            android.util.Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            android.util.Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    private String convertStreamToString(java.io.InputStream is) {
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
