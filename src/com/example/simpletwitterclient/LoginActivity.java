package com.example.simpletwitterclient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.codepath.oauth.OAuthLoginActivity;

public class LoginActivity extends OAuthLoginActivity<TwitterClient> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	// Inflate the menu; this adds items to the action bar if it is present.
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	// OAuth authenticated successfully, launch primary authenticated activity
	// i.e Display application "homepage"
    @Override
    public void onLoginSuccess() {
    	Intent i = new Intent(this, TweetsActivity.class);
    	i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(i);
    	Log.d("DEBUG", "Login succeeded!");
    	
    	/*getClient().getHomeTimeline(1, new JsonHttpResponseHandler() {
    		
    		@Override
    		public void onSuccess(JSONArray jsonTweets) {
    			Log.d("DEBUG", jsonTweets.toString());
    		}
    		
    		@Override
    		public void onFailure(Throwable arg0, JSONArray arg1) {
    			// TODO Auto-generated method stub
    			super.onFailure(arg0, arg1);
    		}
    		
    	});*/
    }
    
    // OAuth authentication flow failed, handle the error
    // i.e Display an error dialog or toast
    @Override
    public void onLoginFailure(Exception e) {
        e.printStackTrace();
        Log.d("DEBUG", "Login failed!");
    }
    
    // Click handler method for the button used to start OAuth flow
    // Uses the client to initiate OAuth authorization
    // This should be tied to a button used to login
    public void loginToRest(View view) {
        getClient().connect();
    }

}
