package com.example.eladgofer.project.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eladgofer.project.R;
import com.example.eladgofer.project.ViewPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {


    RecyclerView r;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    public NewsFragment newInstance(int columns) {
        Bundle args = new Bundle();
        args.putInt("columns", columns);
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_news, container, false);
        setRetainInstance(true);
        tabLayout = (TabLayout) v.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) v.findViewById(R.id.view_pager);
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);


        final TabLayout.Tab ynet = tabLayout.newTab();
        final TabLayout.Tab mako = tabLayout.newTab();
        final TabLayout.Tab walla = tabLayout.newTab();


        tabLayout.addTab(ynet, 0);
        tabLayout.addTab(mako, 1);
        tabLayout.addTab(walla, 2);


        tabLayout.setBackgroundColor(getResources().getColor(R.color.paleOrangeBackground));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.darkGrey));
        tabLayout.setSelectedTabIndicatorHeight(10);


        View view1 = inflater.inflate(R.layout.costum_tab, container, false);
        view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.tab_ynet);
        ynet.setCustomView(view1);

        View view2 = inflater.inflate(R.layout.costum_tab, container, false);
        view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.mako_logo);
        mako.setCustomView(view2);

        View view3 = inflater.inflate(R.layout.costum_tab, container, false);
        view3.findViewById(R.id.icon).setBackgroundResource(R.drawable.maarin_logo_png);
        walla.setCustomView(view3);


        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        adapter.getItem(position);
                        break;
                    case 1:
                        adapter.getItem(position);
                        break;
                    case 2:
                        adapter.getItem(position);
                        break;
                }

            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {


            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), true);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.getCurrentItem();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), true);
            }
        });

        return v;

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("-pause--------------", " paused-------");


    }


    @Override
    public void onResume() {
        super.onResume();


        Log.d("-resume--------------", " came back");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("-stop--------------", " app not visible");
    }

    @Override
    public void onStart() {
        super.onStart();


    }
}
