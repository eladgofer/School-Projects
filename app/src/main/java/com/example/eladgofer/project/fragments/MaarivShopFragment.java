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
import com.example.eladgofer.project.data.MaarivShopDataSource;
import com.example.eladgofer.project.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MaarivShopFragment extends Fragment implements MaarivShopDataSource.OnMaarivShopArrivedListener {

    RecyclerView v;

    public MaarivShopFragment newInstance(int columns) {

        Bundle args = new Bundle();
        args.putInt("columns", columns);
        MaarivShopFragment fragment = new MaarivShopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = (RecyclerView) inflater.inflate(R.layout.fragment_maariv, container, false);

        MaarivShopDataSource.getMaarivShop(this);
        return v;
    }

    @Override
    public void onMaarivShopArrived(final List<MaarivShopDataSource.MaarivShop> data, final Exception e) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (e == null) {
                    v.setAdapter(new MaarivShopAdapter(getActivity(), data));
                    v.setLayoutManager(new LinearLayoutManager(getContext()));
                    v.setBackgroundColor(getResources().getColor(R.color.paleGreenBackground));

                } else {
                    Toast.makeText(getContext(), "Maariv Can't Connect To Server", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    class MaarivShopAdapter extends RecyclerView.Adapter<MaarivShopAdapter.MaarivShopViewHolder> {

        private Context context;
        private LayoutInflater inflater;
        private List<MaarivShopDataSource.MaarivShop> data;
        private FragmentActivity activity;

        public MaarivShopAdapter(FragmentActivity activity, List<MaarivShopDataSource.MaarivShop> data) {
            this.context = context;
            this.inflater = LayoutInflater.from(activity);
            this.data = data;
            this.activity = activity;
        }

        @Override
        public MaarivShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = inflater.inflate(R.layout.maariv_item, parent, false);
            return new MaarivShopViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MaarivShopViewHolder holder, final int position) {
            final MaarivShopDataSource.MaarivShop maariv = data.get(position);
            holder.tvTitle_maariv.setText(maariv.getTitle());
            String str = maariv.getContent();
            Utils utils = new Utils();
            holder.tvContent_maariv.setText(utils.makeTheDots(str));
            Picasso.with(context).load(maariv.getThumbnail()).into(holder.ivThumbnail_maariv);
            if (maariv.getThumbnail().isEmpty() || maariv.getThumbnail() == null) {
                holder.ivThumbnail_maariv.setImageResource(R.drawable.maariv_logo);
            }
            holder.fabShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, maariv.getUrl());
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }
            });
            holder.cvMaariv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MaarivShopDataSource.MaarivShop maariv = data.get(position);
                    NewsDetailFragment maarivDetailFragment = NewsDetailFragment.newInstance(maariv.getUrl());
                    activity.getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.frame, maarivDetailFragment).
                            addToBackStack(null).
                            commit();

                }
            });

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class MaarivShopViewHolder extends RecyclerView.ViewHolder {

            ImageView ivThumbnail_maariv;
            TextView tvTitle_maariv, tvContent_maariv;
            CardView cvMaariv;
            FloatingActionButton fabShare;

            public MaarivShopViewHolder(View itemView) {
                super(itemView);

                ivThumbnail_maariv = (ImageView) itemView.findViewById(R.id.ivThumb_nail_maariv);
                tvTitle_maariv = (TextView) itemView.findViewById(R.id.tvTitle_maariv);
                tvContent_maariv = (TextView) itemView.findViewById(R.id.tvContent_maariv);
                cvMaariv = (CardView) itemView.findViewById(R.id.cvMaariv);
                fabShare = (FloatingActionButton) itemView.findViewById(R.id.fabShare);

            }
        }
    }
}
