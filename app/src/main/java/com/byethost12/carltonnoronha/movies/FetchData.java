package com.byethost12.carltonnoronha.movies;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.byethost12.carltonnoronha.movies.networkUtils.NetworkUtils;

public class FetchData extends AsyncTaskLoader<String> {

    private String mUrl;
    public FetchData(@NonNull Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        onForceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.fetchJSONData(mUrl);
    }
}
