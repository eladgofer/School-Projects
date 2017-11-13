package com.example.eladgofer.project.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.eladgofer.project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsDetailFragment extends Fragment {

    //Properties:
    private WebView webView;

    //Factory method:
    public static NewsDetailFragment newInstance(String link) {
        Bundle args = new Bundle();
        args.putString("Link", link);
        NewsDetailFragment fragment = new NewsDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ynet_detail, container, false);
        String link = getArguments().getString("Link");


        webView = (WebView) v.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }
        });
        configWebView(webView);
        webView.loadUrl(link);


        return v;
    }


    private void configWebView(WebView webView) {
        webView.getSettings().setJavaScriptEnabled(true);//enable js

    }


}
