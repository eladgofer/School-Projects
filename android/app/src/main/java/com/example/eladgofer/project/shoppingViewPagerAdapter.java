package com.example.eladgofer.project;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.eladgofer.project.fragments.MaarivShopFragment;
import com.example.eladgofer.project.fragments.MakoShopFragment;
import com.example.eladgofer.project.fragments.YnetShopFragment;


public class shoppingViewPagerAdapter extends FragmentStatePagerAdapter {
    private final YnetShopFragment ynet;
    private final MakoShopFragment mako;
    private final MaarivShopFragment maariv;


    public shoppingViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.ynet = new YnetShopFragment();
        this.mako = new MakoShopFragment();
        this.maariv = new MaarivShopFragment();


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

    public void refreshCurrent() {


    }

}
