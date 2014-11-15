package com.yogidev.android.livingroom;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class HomeActivity extends Activity {
	
	private static final int SETTING_OPTIONS_CODE = 1;
	private static final int ABOUT_OPTIONS_CODE = 2;
	private static final int FIND_REFERENCE_OPTIONS_CODE = 3;
	private static final int CONTACT_CARD_OPTIONS_CODE = 4;
	
	// to save the current theme of the activity
	private int mThemeId = -1;
	// to save current background
	private static final int GARONNE_BACKGROUND = R.drawable.garonne;
	private static final int CANAL_BACKGROUND = R.drawable.canal_midi;
	private int mBackgroundId = GARONNE_BACKGROUND;
	private static View fragmentView;
	
	// L'identifiant de la chaîne de caractères qui contient le résultat de l'intent
	public final static String SETTINGS_BUTTONS = "com.yogidev.android.intent.settings.Boutons";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if(savedInstanceState != null) {
            if (savedInstanceState.getInt("theme", -1) != -1) {
              mThemeId = savedInstanceState.getInt("theme");
              this.setTheme(mThemeId);
            }
        }
		
		// ---------------------------------- begin code for Droid Inpector (search for this line in the project to suppress all occurences)
//		ViewServer.get(this).addWindow(this); 
		// ---------------------------------- end code for Droid Inpector
		
		
		setContentView(R.layout.activity_home);
		
		// Use of the "logo" defined in the Manifest instead of the default "icon" for the ActionBar
		getActionBar().setDisplayUseLogoEnabled(true);
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", mThemeId);
    }
	
    /**
     * Go to "FindReferenceActivity"
     * 
     * @param view
     */
	public void onFindClicked(View view) {
		// Launch ReferenceListActivity
		Intent intent = new Intent(HomeActivity.this, FindReferenceActivity.class);
		startActivityForResult(intent, FIND_REFERENCE_OPTIONS_CODE);
	}
	
	/**
     * Go to "ContactCardFlipActivity"
     * 
     * @param view
     */
	public void onContactClicked(View view) {
		// Launch ReferenceListActivity
		Intent intent = new Intent(HomeActivity.this, ContactCardFlipActivity.class);
		startActivityForResult(intent, CONTACT_CARD_OPTIONS_CODE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	/**
	 * Manage the action bar options
	 * 
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		Intent intent = null;
		switch (item.getItemId()) {
			case R.id.action_settings:
			
				// Launch Settings Activity
				intent = new Intent(HomeActivity.this, SettingsActivity.class);
				startActivityForResult(intent, SETTING_OPTIONS_CODE);
				return true;
				
			case R.id.menu_toggleTheme:
				if (mThemeId == R.style.AppTheme_Dark) {
					mThemeId = R.style.AppTheme_Light;
					mBackgroundId = CANAL_BACKGROUND;
				} else {
					mThemeId = R.style.AppTheme_Dark;
					mBackgroundId = GARONNE_BACKGROUND;
				}
				changeBackground(mBackgroundId);
				this.recreate();
				return true;
				
			case R.id.action_about:
				
				// Launch About Activity
				intent = new Intent(HomeActivity.this, InfosActivity.class);
				startActivityForResult(intent, ABOUT_OPTIONS_CODE);
				return true;
			
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	public void changeBackground(int backgroundId) {
		fragmentView.setBackgroundResource(backgroundId);
		Toast.makeText(this, "Background => " + backgroundId, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// On vérifie tout d'abord à quel intent on fait référence ici à l'aide de notre identifiant
	    if (requestCode == SETTING_OPTIONS_CODE) {
	      // On vérifie aussi que l'opération s'est bien déroulée
	      if (resultCode == RESULT_OK) {
	        // On affiche le bouton qui a été choisi
	      	Toast.makeText(this, "Les notifications ont été " + ((data.getStringExtra(SETTINGS_BUTTONS).equals(SettingsActivity.NOTIFICATION_ON))?"activées":"désactivées"), Toast.LENGTH_SHORT).show();
	      }
	    }
	}
	
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_home, container,	false);
			fragmentView = rootView;
			return rootView;
		}
		
	}
	
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//		// ---------------------------------- begin code for Droid Inpector (search for this line in the project to suppress all occurences)
//		ViewServer.get(this).addWindow(this); 
//		// ---------------------------------- end code for Droid Inpector
//    }
// 
//    @Override
//    public void onResume() {
//        super.onResume();
//		// ---------------------------------- begin code for Droid Inpector (search for this line in the project to suppress all occurences)
//		ViewServer.get(this).addWindow(this); 
//		// ---------------------------------- end code for Droid Inpector
//    }
}
