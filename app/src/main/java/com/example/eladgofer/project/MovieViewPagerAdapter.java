package com.example.eladgofer.project;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.eladgofer.project.fragments.MovieFragment;

/**
 * Created by eladgofer on 18/06/2017.
 */

public class MovieViewPagerAdapter extends FragmentStatePagerAdapter {
    private final MovieFragment movie;


    public MovieViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.movie = new MovieFragment();


    }


    @Override
    public Fragment getItem(int position) {

        return null;
    }

    @Override
    public int getCount() {
        return 1;
    }


}
