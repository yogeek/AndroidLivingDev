package com.yogidev.android.livingroom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SettingsActivity extends ActionBarActivity {
	
	
	public final static String NOTIFICATION_ON = "Oui";
	public final String NOTIFICATION_OFF = "Non";
	private String notificationChoice = NOTIFICATION_OFF;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    setContentView(R.layout.settings);
	    
	    // Get the Action Bar 
	    ActionBar actionBar = getSupportActionBar();
	    // Enable the app icon as an Up button
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    // Set title
	    actionBar.setTitle("Paramètres");
	    // Set logo
	    actionBar.setLogo(R.drawable.rouages);
	    
	
	}
	
	public void onToggleClicked(View view) {
	    // Is the toggle on?
	    boolean on = ((ToggleButton) view).isChecked();
	    
	    if (on) {
	        // Enable notification
	    	this.notificationChoice = NOTIFICATION_ON;
	    	Toast.makeText(this, "Notification activées !", Toast.LENGTH_SHORT).show();
	        
	    } else {
	        // Disable notification
	    	this.notificationChoice = NOTIFICATION_OFF;
	    	Toast.makeText(this, "Notification désactivées !", Toast.LENGTH_SHORT).show();
	    }
	}
	
	public void onValiderClicked(View view) {
		
		// Return notification choice
    	Intent result = new Intent();
        result.putExtra(HomeActivity.SETTINGS_BUTTONS, this.notificationChoice);
        setResult(RESULT_OK, result);
		finish();
	}
	
	public void onAnnulerClicked(View view) {
		
		// Return 
		finish();
	}
	
	

}
