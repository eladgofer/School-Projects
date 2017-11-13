package com.example.eladgofer.project.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eladgofer.project.R;
import com.example.eladgofer.project.shoppingViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingNewsFragment extends Fragment {

    boolean mDualPane;
    int mCurCheckPosition = 0;
    RecyclerView r;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private shoppingViewPagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_shopping_naews, container, false);
        setRetainInstance(true);
        tabLayout = (TabLayout) v.findViewById(R.id.Shopping_tab_layout);
        viewPager = (ViewPager) v.findViewById(R.id.shopping_view_pager);

        adapter = new shoppingViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);


        final TabLayout.Tab ynet = tabLayout.newTab();
        final TabLayout.Tab mako = tabLayout.newTab();
        final TabLayout.Tab maariv = tabLayout.newTab();

        tabLayout.addTab(ynet, 0);
        tabLayout.addTab(mako, 1);
        tabLayout.addTab(maariv, 2);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.paleGreenBackground));
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
        maariv.setCustomView(view3);


        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:

                        return;
                    case 1:
                        break;
                    case 2:
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
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
