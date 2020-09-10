package com.byethost12.carltonnoronha.movies.networkUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    public static String fetchJSONData(String url){
        String JSONData = "";
        URL mURL = null;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        BufferedReader mReader = null;

        try {
            mURL = new URL(url);
            httpURLConnection = (HttpURLConnection) mURL.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            inputStream = httpURLConnection.getInputStream();
            mReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder jsonStringBuilder = new StringBuilder();
            String line = "";
            while((line = mReader.readLine()) != null) {
                jsonStringBuilder.append(line);
            }

            JSONData = jsonStringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if(httpURLConnection != null)
                httpURLConnection.disconnect();

            if(mReader != null){
                try {
                    mReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return JSONData;
    }
}
