package com.byethost12.carltonnoronha.movies;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.byethost12.carltonnoronha.movies.adapters.MovieAdapter;
import com.byethost12.carltonnoronha.movies.constants.Constants;
import com.byethost12.carltonnoronha.movies.customclass.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private List<Movies> movies;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = new ArrayList<>();

        adapter = new MovieAdapter(this, movies);
        recyclerView = findViewById(R.id.movieImageRecyclerView);




        String url = Constants.MOVIE_BASE_URL + Constants.API_KEY + Constants.language_Type;
        Bundle link = new Bundle();
        link.putString(Constants.MOVIE_URL_KEY, url);

        if(getSupportLoaderManager().getLoader(0) != null){
            getSupportLoaderManager().initLoader(0, null, this);
        }else {
            Log.i("Main Activity", "In Loader");
            getSupportLoaderManager().restartLoader(0, link, this);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {

        String url = "";
        if (args != null) {
            url = args.getString(Constants.MOVIE_URL_KEY);
        }
        return new FetchData(MainActivity.this, url);

    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        if(!data.equals("")){
            Log.i("MainActivity", data);
            try {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray results = jsonObject.getJSONArray("results");
                for(int i = 0; i < results.length(); ++i){
                    movies.add(new Movies(
                            results.getJSONObject(i).getInt("id"),
                            results.getJSONObject(i).getString("title"),
                            "https://image.tmdb.org/t/p/w400" + results.getJSONObject(i).getString("poster_path"),
                            results.getJSONObject(i).getString("overview")
                    ));
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

    @Override
    protected void onResume() {
        super.onResume();
        movies.clear();
    }
}