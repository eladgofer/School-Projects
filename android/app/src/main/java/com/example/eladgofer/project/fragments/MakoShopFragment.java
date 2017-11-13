package com.example.eladgofer.project.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eladgofer.project.R;
import com.example.eladgofer.project.data.MakoShopDataSource;
import com.example.eladgofer.project.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MakoShopFragment extends Fragment implements MakoShopDataSource.OnMakoShopArrivedListener {

    RecyclerView v;

    public MakoShopFragment newInstance(int columns) {

        Bundle args = new Bundle();
        args.putInt("columns", columns);
        MakoShopFragment fragment = new MakoShopFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = (RecyclerView) inflater.inflate(R.layout.fragment_mako, container, false);

        MakoShopDataSource.getMako(this);
        return v;
    }

    @Override
    public void onMakoShopArrived(final List<MakoShopDataSource.MakoShop> data, final Exception e) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (e == null) {
                    v.setAdapter(new MakoShopAdapter(getActivity(), data));
                    v.setLayoutManager(new LinearLayoutManager(getContext()));
                    v.setBackgroundColor(getResources().getColor(R.color.paleGreenBackground));
                } else {
                    Toast.makeText(getContext(), "Can't Connect To Server MAKO", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class MakoShopAdapter extends RecyclerView.Adapter<MakoShopAdapter.MakoShopViewHolder> {

        @BindView(R.id.rvMako)
        RecyclerView rvMako;
        private Context context;
        private LayoutInflater inflater;
        private List<MakoShopDataSource.MakoShop> data;
        private FragmentActivity activity;

        public MakoShopAdapter(FragmentActivity activity, List<MakoShopDataSource.MakoShop> data) {
            this.context = context;
            this.inflater = LayoutInflater.from(activity);
            this.data = data;
            this.activity = activity;
        }

        @Override
        public MakoShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.inflate(R.layout.mako_item, parent, false);
            return new MakoShopViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MakoShopViewHolder holder, final int position) {
            final MakoShopDataSource.MakoShop mako = data.get(position);
            holder.tvTitle.setText(mako.getTitle());
            String str = mako.getContent();
            Utils utils = new Utils();
            holder.tvContent_mako.setText(utils.makeTheDots(str));
            Picasso.with(activity).load(mako.getImage()).into(holder.ivThumb_nail_mako);
            if (mako.getImage().isEmpty() || mako.getImage() == null) {
                holder.ivThumb_nail_mako.setImageResource(R.drawable.mako_logo);
            }
            holder.fabShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, mako.getUrl());
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }
            });
            holder.cvMako.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MakoShopDataSource.MakoShop mako = data.get(position);
                    NewsDetailFragment makoDetailsFragment = NewsDetailFragment.newInstance(mako.getUrl());
                    activity.getSupportFragmentManager().
                            beginTransaction().
                            addToBackStack(null).
                            replace(R.id.frame, makoDetailsFragment).
                            commit();
                }
            });

        }

        @Override
        public int getItemCount() {
            return data.size();
        }


        public class MakoShopViewHolder extends RecyclerView.ViewHolder {
            ImageView ivThumb_nail_mako;
            TextView tvTitle, tvContent_mako;
            CardView cvMako;
            FloatingActionButton fabShare;

            public MakoShopViewHolder(View itemView) {
                super(itemView);
                ivThumb_nail_mako = (ImageView) itemView.findViewById(R.id.ivThumb_nail_mako);
                tvTitle = (TextView) itemView.findViewById(R.id.tvTitle_mako);
                tvContent_mako = (TextView) itemView.findViewById(R.id.tvContent_mako);
                cvMako = (CardView) itemView.findViewById(R.id.cvMako);
                fabShare = (FloatingActionButton) itemView.findViewById(R.id.fabShare);
            }
        }
    }
}
