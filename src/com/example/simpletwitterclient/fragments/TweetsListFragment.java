package com.example.simpletwitterclient.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.simpletwitterclient.R;
import com.example.simpletwitterclient.TweetsAdapter;
import com.example.simpletwitterclient.models.Tweet;

public class TweetsListFragment extends Fragment {
	
	TweetsAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tweets_list, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		adapter = new TweetsAdapter(getActivity(), tweets);
		ListView lv = (ListView) getActivity().findViewById(R.id.lvTweets);
		lv.setAdapter(adapter);
	}
	
	public TweetsAdapter getAdapter() {
		return adapter;
	}
	
	public void hideProgressBar() {
		ProgressBar progressBar = (ProgressBar) getView().findViewById(R.id.pb_horizontal);
		progressBar.setVisibility(View.GONE);
	}
}
