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
import com.example.eladgofer.project.data.YnetShopDataSource;
import com.example.eladgofer.project.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * ynet shopping news headers.
 */
public class YnetShopFragment extends Fragment implements YnetShopDataSource.OnYnetShopArriveListener {


    RecyclerView v;

    public YnetShopFragment newInstance(int columns) {

        Bundle args = new Bundle();
        args.putInt("columns", columns);
        YnetShopFragment fragment = new YnetShopFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = (RecyclerView) inflater.inflate(R.layout.fragment_ynet, container, false);
        YnetShopDataSource.getYnet(this);
        return v;
    }

    @Override
    public void onYnetShopArrived(final List<YnetShopDataSource.YnetShop> data, final Exception e) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (e == null) {
                    v.setAdapter(new YnetShopAdapter(getActivity(), data));
                    v.setLayoutManager(new LinearLayoutManager(getActivity()));
                    v.setBackgroundColor(getResources().getColor(R.color.paleGreenBackground));
                } else {
                    Toast.makeText(getActivity(), "Cant connect to server....", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void reload() {

    }

    class YnetShopAdapter extends RecyclerView.Adapter<YnetShopAdapter.YnetShopViewHolder> {

        private Context context;
        private LayoutInflater inflater;
        private FragmentActivity activity;
        private List<YnetShopDataSource.YnetShop> data;


        public YnetShopAdapter(FragmentActivity activity, List<YnetShopDataSource.YnetShop> data) {
            this.context = context;
            this.inflater = LayoutInflater.from(activity);
            this.activity = activity;
            this.data = data;
        }

        @Override
        public YnetShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = inflater.inflate(R.layout.ynet_item, parent, false);


            return new YnetShopViewHolder(v);
        }

        @Override
        public void onBindViewHolder(YnetShopViewHolder holder, final int position) {
            final YnetShopDataSource.YnetShop ynet = data.get(position);
            holder.tvTitle.setText(ynet.getTitle());
            String str = ynet.getContent();
            Utils utils = new Utils();
            holder.tvContent.setText(utils.makeTheDots(str));
            holder.fabShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, ynet.getUrl());
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }
            });
            Picasso.with(context).load(ynet.getThumbnail()).into(holder.ivThumb_nail);
            if (ynet.getThumbnail().isEmpty() || ynet.getThumbnail() == null) {
                holder.ivThumb_nail.setImageResource(R.drawable.ynet_logo);
            }
            holder.cvYnet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    YnetShopDataSource.YnetShop ynet = data.get(position);

                    NewsDetailFragment ynetDetailFragment = NewsDetailFragment.newInstance(ynet.getUrl());

                    activity.getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.frame, ynetDetailFragment).
                            addToBackStack(null).
                            commit();

                }
            });


        }

        @Override
        public int getItemCount() {
            return data.size();
        }


        class YnetShopViewHolder extends RecyclerView.ViewHolder {
            //proporties:
            ImageView ivThumb_nail;
            TextView tvTitle, tvContent;
            CardView cvYnet;
            FloatingActionButton fabShare;

            public YnetShopViewHolder(View itemView) {
                super(itemView);
                ivThumb_nail = (ImageView) itemView.findViewById(R.id.ivThumb_nail);
                tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
                tvContent = (TextView) itemView.findViewById(R.id.tvContent);
                cvYnet = (CardView) itemView.findViewById(R.id.cvYnet);
                fabShare = (FloatingActionButton) itemView.findViewById(R.id.fabShare);


            }
        }
    }
}
