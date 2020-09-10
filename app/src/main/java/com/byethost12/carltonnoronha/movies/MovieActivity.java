package com.byethost12.carltonnoronha.movies;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.byethost12.carltonnoronha.movies.adapters.ReviewAdapter;
import com.byethost12.carltonnoronha.movies.constants.Constants;
import com.byethost12.carltonnoronha.movies.customclass.Review;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private ReviewAdapter adapter;
    private RecyclerView reviewRecyclerView;
    private List<Review> reviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        reviewList = new ArrayList<>();
        reviewList.clear();
        adapter = new ReviewAdapter(this, reviewList);

        reviewRecyclerView = findViewById(R.id.reviewRecyclerView);
        TextView description = findViewById(R.id.descriptionContent);

        String url = "";

        Intent intent = getIntent();
        if(intent != null){
            ActionBar actionBar = getSupportActionBar();
            if(actionBar!= null){
                actionBar.setTitle(intent.getStringExtra("MovieName"));
            }
            description.setText(intent.getStringExtra("Description"));
            url = Constants.REVIEW_BASE_URL + intent.getIntExtra("MovieID", 734309)
                    + Constants.CONTINUED_URL + Constants.API_KEY + Constants.language_Type;

            Bundle link = new Bundle();
            link.putString(Constants.REVIEW_URL_KEY, url);

            if(getSupportLoaderManager().getLoader(1) != null){
                getSupportLoaderManager().initLoader(1, null, this);
            }else {
                Log.i("Main Activity", "In Loader");
                getSupportLoaderManager().restartLoader(1, link, this);
            }
        }

        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        reviewRecyclerView.setAdapter(adapter);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String url = "";
        if (args != null) {
            url = args.getString(Constants.REVIEW_URL_KEY);
        }
        return new FetchData(MovieActivity.this, url);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        if(!data.equals("")){
            try {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray reviews = jsonObject.getJSONArray("results");
                int length = reviews.length();
                if(length != 0){
                    for(int i = 0; i < length; ++i) {
                        reviewList.add(new Review(
                                reviews.getJSONObject(i).getString("content"),
                                reviews.getJSONObject(i).getString("author")
                                )
                        );
                    }
                }else {
                    reviewList.add(new Review("No reviews yet", "Administrator"));
                }
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}