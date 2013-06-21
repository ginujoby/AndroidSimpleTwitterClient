package com.example.simpletwitterclient;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simpletwitterclient.fragments.UserTimelineFragment;
import com.example.simpletwitterclient.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileActivity extends FragmentActivity {

	String screenName = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		screenName = getIntent().getStringExtra("user_screen_name");
		
		loadProfileInfo(screenName);
		loadUserTimeline(screenName);
	}

	private void loadProfileInfo(String screenName) {
		if(screenName == null) {
			SimpleTwitterApp.getRestClient().getMyInfo(new JsonHttpResponseHandler() {
				@Override
				public void onSuccess(JSONObject profile) {
					User u = User.fromJson(profile);
					getActionBar().setTitle("@" + u.getScreenName());
					populateProfileHeader(u);
				}
			});
		} else {
			SimpleTwitterApp.getRestClient().getUserInfo(screenName, new JsonHttpResponseHandler() {
				@Override
				public void onSuccess(JSONObject profile) {
					User u = User.fromJson(profile);
					getActionBar().setTitle("@" + u.getScreenName());
					populateProfileHeader(u);
				}
			});
		}
	}

	private void populateProfileHeader(User u) {
		TextView tvName = (TextView) findViewById(R.id.tv_name);
		TextView tvTagline = (TextView) findViewById(R.id.tv_tagline);
		TextView tvFollowers = (TextView) findViewById(R.id.tv_followers);
		TextView tvFollowing = (TextView) findViewById(R.id.tv_following);
		ImageView ivProfileImage = (ImageView) findViewById(R.id.iv_profile_image);
		
		tvName.setText(u.getName());
		tvTagline.setText(u.getTagline());
		tvFollowers.setText(u.getFollowersCount() + " Followers");
		tvFollowing.setText(u.getFriendsCount() + " Following");
		ImageLoader.getInstance().displayImage(u.getProfileImageUrl(), ivProfileImage);
	}
	
	private void loadUserTimeline(String screenName2) {
		android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = manager.beginTransaction();
		fts.replace(R.id.frame_user_timeline, new UserTimelineFragment());
		fts.commit();
	}

	public String getScreenName() {
		return screenName;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

}
