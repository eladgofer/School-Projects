package com.example.eladgofer.project.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.eladgofer.project.R;
import com.example.eladgofer.project.data.MovieDataSource;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements MovieDataSource.OnMoviesArriveListener {

    RecyclerView rvMovies;


    public MovieFragment newInstance(int columns) {

        Bundle args = new Bundle();
        args.putInt("columns", columns);
        MovieFragment fragment = new MovieFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie, container, false);
        MovieDataSource.getMovie(this);
        rvMovies = (RecyclerView) v.findViewById(R.id.rvMovies);
        return v;
    }

    @Override
    public void onMovieArrived(final List<MovieDataSource.Movie> data, final Exception e) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (e == null) {
                    rvMovies.setAdapter(new MovieAdapter(getActivity(), data));
                    rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
                } else {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("fuck", e.getMessage());
                }
            }
        });

    }


    class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

        private Context context;
        private LayoutInflater inflater;
        private FragmentActivity activity;
        private List<MovieDataSource.Movie> data;


        public MovieAdapter(FragmentActivity activity, List<MovieDataSource.Movie> data) {
            this.context = context;
            this.inflater = LayoutInflater.from(activity);
            this.activity = activity;
            this.data = data;
        }

        @Override
        public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = inflater.inflate(R.layout.movie_item, parent, false);


            return new MovieViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MovieViewHolder holder, final int position) {
            final MovieDataSource.Movie movie = data.get(position);
            holder.cvMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewsDetailFragment movieFragment = NewsDetailFragment.newInstance(movie.getUrl());
                    activity.
                            getSupportFragmentManager().
                            beginTransaction().
                            addToBackStack(null).
                            replace(R.id.frame, movieFragment).commit();
                }
            });

            Picasso.with(getContext()).load(movie.getThumbNail()).into(holder.ivThumb_nail);
            /*if (movie.getThumbNail().isEmpty() || movie.getThumbNail() == null) {
                holder.ivThumb_nail.setImageResource(R.drawable.ic_movie_black);
            }*/


        }

        @Override
        public int getItemCount() {
            return data.size();
        }


        class MovieViewHolder extends RecyclerView.ViewHolder {
            //proporties:
            ImageView ivThumb_nail;
            CardView cvMovie;


            public MovieViewHolder(View itemView) {
                super(itemView);
                ivThumb_nail = (ImageView) itemView.findViewById(R.id.ivThumb_nail);
                cvMovie = (CardView) itemView.findViewById(R.id.cvMovie);


            }
        }
    }
}
