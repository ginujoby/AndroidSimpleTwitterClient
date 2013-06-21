package com.example.simpletwitterclient.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import android.os.Bundle;

import com.example.simpletwitterclient.SimpleTwitterApp;
import com.example.simpletwitterclient.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MentionsFragment extends TweetsListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SimpleTwitterApp.getRestClient().getMentions(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, JSONArray jsonTweets) {
				hideProgressBar();
				
				ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
				getAdapter().addAll(tweets);
			}
		});
	}
}
