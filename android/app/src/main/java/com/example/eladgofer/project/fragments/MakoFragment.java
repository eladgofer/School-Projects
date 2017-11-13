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
import com.example.eladgofer.project.data.MakoDataSource;
import com.example.eladgofer.project.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MakoFragment extends Fragment implements MakoDataSource.OnMakoArrivedListener {

    RecyclerView v;

    public MakoFragment newInstance(int columns) {

        Bundle args = new Bundle();
        args.putInt("columns", columns);
        MakoFragment fragment = new MakoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = (RecyclerView) inflater.inflate(R.layout.fragment_mako, container, false);
        setRetainInstance(true);
        MakoDataSource.getMako(this);
        return v;
    }

    @Override
    public void onMakoArrived(final List<MakoDataSource.Mako> data, final Exception e) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (e == null) {
                    v.setAdapter(new MakoAdapter(getActivity(), data));
                    v.setLayoutManager(new LinearLayoutManager(getContext()));
                    v.setBackgroundColor(getResources().getColor(R.color.paleOrangeBackground));
                } else {
                    Toast.makeText(getContext(), "Can't Connect To Server MAKO", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    class MakoAdapter extends RecyclerView.Adapter<MakoAdapter.MakoViewHolder> {


        private Context context;
        private LayoutInflater inflater;
        private List<MakoDataSource.Mako> data;
        private FragmentActivity activity;

        public MakoAdapter(FragmentActivity activity, List<MakoDataSource.Mako> data) {
            this.context = context;
            this.inflater = LayoutInflater.from(activity);
            this.data = data;
            this.activity = activity;
        }

        @Override
        public MakoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.inflate(R.layout.mako_item, parent, false);
            return new MakoViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MakoViewHolder holder, final int position) {
            final MakoDataSource.Mako mako = data.get(position);
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
                    MakoDataSource.Mako mako = data.get(position);
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

        public class MakoViewHolder extends RecyclerView.ViewHolder {

            ImageView ivThumb_nail_mako;
            TextView tvTitle, tvContent_mako;
            CardView cvMako;
            FloatingActionButton fabShare;

            public MakoViewHolder(View itemView) {
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
