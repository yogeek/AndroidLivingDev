package com.yogidev.android.livingroom;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class HomeActivity extends ActionBarActivity {
	
	private static final int SETTING_OPTIONS_CODE = 1;
	// L'identifiant de la cha�ne de caract�res qui contient le r�sultat de l'intent
	public final static String SETTINGS_BUTTONS = "com.yogidev.android.intent.settings.Boutons";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		// Use of the "logo" defined in the Manifest instead of the default "icon" for the ActionBar
		getActionBar().setDisplayUseLogoEnabled(true);
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			
			// Launch Settings Activity
			Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
			startActivityForResult(intent, SETTING_OPTIONS_CODE);
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// On v�rifie tout d'abord � quel intent on fait r�f�rence ici � l'aide de notre identifiant
	    if (requestCode == SETTING_OPTIONS_CODE) {
	      // On v�rifie aussi que l'op�ration s'est bien d�roul�e
	      if (resultCode == RESULT_OK) {
	        // On affiche le bouton qui a �t� choisi
	      	Toast.makeText(this, "Les notifications ont �t� " + ((data.getStringExtra(SETTINGS_BUTTONS).equals(SettingsActivity.NOTIFICATION_ON))?"activ�es":"d�sactiv�es"), Toast.LENGTH_SHORT).show();
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
			View rootView = inflater.inflate(R.layout.fragment_home, container,
					false);
			return rootView;
		}
	}
}