package com.byethost12.carltonnoronha.movies.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.byethost12.carltonnoronha.movies.MovieActivity;
import com.byethost12.carltonnoronha.movies.R;
import com.byethost12.carltonnoronha.movies.customclass.Movies;


import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<Movies> moviesList;

    public MovieAdapter(Context context, List<Movies> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_list_layout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        Glide.with(context).load(moviesList.get(position).getMovieImageURL()).into(holder.movieImage);
        holder.movieName.setText(moviesList.get(position).getMovieName());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView movieImage;
        private TextView movieName;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movieImage);
            movieName = itemView.findViewById(R.id.movieName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getAdapterPosition() != RecyclerView.NO_POSITION){
                        Intent intent = new Intent(context, MovieActivity.class);
                        intent.putExtra("MovieID", moviesList.get(getAdapterPosition()).getMovieID());
                        intent.putExtra("Description", moviesList.get(getAdapterPosition()).getOverview());
                        intent.putExtra("MovieName", moviesList.get(getAdapterPosition()).getMovieName());
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
