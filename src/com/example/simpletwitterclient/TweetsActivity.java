package com.example.simpletwitterclient;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.simpletwitterclient.fragments.HomeTimelineFragment;
import com.example.simpletwitterclient.fragments.MentionsFragment;

public class TweetsActivity extends FragmentActivity implements TabListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tweets);
		
		/*fragmentTweets = (TweetsListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.fragmentTweets);

		SimpleTwitterApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, JSONArray jsonTweets) {
				ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
//				TweetsAdapter adapter = new TweetsAdapter(getBaseContext(), tweets);
//				ListView lv = (ListView) findViewById(R.id.lvTweets);
//				lv.setAdapter(adapter);
				fragmentTweets.getAdapter().addAll(tweets);
			}
		});*/
		
		setupNavigationTabs();
	}

	private void setupNavigationTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		
		Tab tabHome = actionBar.newTab().setText("Home")
				.setTag("HomeTimelineFragment").setIcon(R.drawable.ic_home)
				.setTabListener(this);
		Tab tabMentions = actionBar.newTab().setText("Mentions")
				.setTag("MentionsFragment").setIcon(R.drawable.ic_mentions)
				.setTabListener(this);
		
		actionBar.addTab(tabHome);
		actionBar.addTab(tabMentions);
		actionBar.selectTab(tabHome);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tweets, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_compose:
			Intent ic = new Intent(getApplicationContext(), ComposeTweetActivity.class);
			startActivity(ic);
			break;
		case R.id.action_profile:
			Intent ip = new Intent(getApplicationContext(), ProfileActivity.class);
			startActivity(ip);
			break;
		default:
			break;
		}

		return true;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = manager.beginTransaction();
		
		if(tab.getTag() == "HomeTimelineFragment") {
			fts.replace(R.id.frame_container, new HomeTimelineFragment());
		} else {
			fts.replace(R.id.frame_container, new MentionsFragment());
		}
		
		fts.commit();
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
	}

}
