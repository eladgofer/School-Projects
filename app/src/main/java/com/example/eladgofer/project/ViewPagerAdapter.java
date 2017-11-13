package com.example.eladgofer.project;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.eladgofer.project.fragments.MaarivFragment;
import com.example.eladgofer.project.fragments.MakoFragment;
import com.example.eladgofer.project.fragments.YnetFragment;

/**
 * Created by eladgofer on 18/06/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private final YnetFragment ynet;
    private final MakoFragment mako;
    private final MaarivFragment maariv;


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.ynet = new YnetFragment();
        this.mako = new MakoFragment();
        this.maariv = new MaarivFragment();


    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ynet;
            case 1:
                return mako;
            case 2:
                return maariv;

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }


}
