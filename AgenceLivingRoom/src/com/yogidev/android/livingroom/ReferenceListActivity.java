package com.yogidev.android.livingroom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.yogidev.android.livingroom.SwipeListView.SwipeListViewCallback;
import com.yogidev.android.livingroom.data.bean.Reference;

public class ReferenceListActivity extends ListActivity implements SwipeListViewCallback {
	
//	private ListView mListView;
	private ReferenceListAdapter mAdapter;
	
	private static final int REFERENCE_DESCRIPTION_CODE = 4;
	
	// The bundle to pass and receive data to and from other activities
	Bundle objetbunble;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    // Restore pref theme
	    setTheme(PreferencesManager.getInstance().getThemePref());
	    
	    // Get the Bundle sent by the previous activity
	    objetbunble  = getIntent().getExtras();
		
	    // Create the bundle if null
	    if (objetbunble == null) {
	    	objetbunble = new Bundle();
	    }
	    
		// ---------------------------------- begin code for Droid Inpector (search for this line in the project to suppress all occurences)
	    //		ViewServer.get(this).addWindow(this); 
		// ---------------------------------- end code for Droid Inpector
	    
	    // Inflate the view from XML
	    setContentView(R.layout.reference_list_view);
	    
		// set transparency 
		getWindow().getDecorView().getRootView().setAlpha(PreferencesManager.TRANPARENCY);
	    
	    // List View (accessible by getListView() since extends ListActivity)
	    // or : mListView = (ListView) findViewById(android.R.id.list);
//	    mListView = getListView();
	    
	    // SimpleListView to manage the swipe on items
	    SwipeListView l = new SwipeListView(this, this);
		l.exec();
		
		List<Reference> referenceList =   new ArrayList<Reference>(Arrays.asList(
				new Reference(1, "T3 - 60 m² NEUF BBC, avec 1 parking", "T3", "Toulouse", "Les Chalets", "Vente", 194250, 60, "http://www.agence-livingroom.com/references/1042/photo_reference.jpg"),
			    new Reference(1, "T2 - 51m² avec parking", "T3", "Toulouse", "Argoulets", "Location", 800, 51, "http://www.agence-livingroom.com/references/1057/photo_reference.jpg"),
			    new Reference(1, "T4 - 90 m²", "T4", "Toulouse", "Matabiau", "Vente", 253150, 90, "http://www.agence-livingroom.com/references/1100/photo_reference.jpg"),
			    new Reference(1, "T1 - 20 m² ", "T1", "Toulouse", "Hypercentre", "Location", 350, 20, "http://www.agence-livingroom.com/references/1110/photo_reference.jpg")
				));
		
        mAdapter = new ReferenceListAdapter(this, referenceList);
//	    mListView.setAdapter(mAdapter);
	    setListAdapter(mAdapter);
	    
        // get content of the "recherche" object
//        String[] rechercheDetails;
//        if (objetbunble != null) {
//        	rechercheDetails = (String[]) objetbunble.get("recherche");
//        
//	        // AlertDialog to display the content
//			AlertDialog.Builder adbRechercheContent = new AlertDialog.Builder(this);
//			adbRechercheContent.setTitle("Détails de la recherche");
//			adbRechercheContent.setPositiveButton("Ok", null);
//			String message = "Ville : "    + rechercheDetails[0] + "/n";
//			message 	  += "Quartier : " + rechercheDetails[1] + "/n";
//			message 	  += "Type : "     + rechercheDetails[2] + "/n";
//			message 	  += "Location : " + rechercheDetails[3] + "/n";
//			message 	  += "Loyer : " + rechercheDetails[4] + "/n";
//			adbRechercheContent.setMessage(message);
//			adbRechercheContent.show();
//        }
//        else {
//        	AlertDialog.Builder adbRechercheContent = new AlertDialog.Builder(this);
//			adbRechercheContent.setTitle("Détails de la recherche");
//			adbRechercheContent.setPositiveButton("Ok", null);
//			adbRechercheContent.setMessage("Recherche vide !!!");
//        }
	    
	    // Get the Action Bar 
	    ActionBar actionBar = getActionBar();
	    // Enable the app icon as an Up button
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    
	    // Set title
	    actionBar.setTitle("Résultats (" + mAdapter.getCount() + ")");
	    // Set logo
	    //actionBar.setLogo(R.drawable.partage);
	    actionBar.setDisplayUseLogoEnabled(true);
	
	}
	
	
	@Override
	public void onSwipeItem(boolean isRight, int position) {
		// Call the onSwipeItem of the adapter
		mAdapter.onSwipeItem(isRight, position);
	}

	
	@Override
	public void onItemClickListener(ListAdapter adapter, int position) {
		
		System.out.println("EVENT 'onItemClickListener' on " + position);
		
		final Reference item = (Reference) adapter.getItem(position);	
		Toast.makeText(this, "Affichage de la référence '" + item.getTitreRef() + "' !", Toast.LENGTH_SHORT).show();
		// set the flag to make the "delete" button disappear
		mAdapter.DELETE_POS = mAdapter.INVALID;
		mAdapter.notifyDataSetChanged();
		
		// Launch the Description activity
		Intent intent = new Intent(ReferenceListActivity.this, ReferenceDescriptionActivity.class);
		// Store the parceable Reference
		intent.putExtra("currentReference", item);
		startActivityForResult(intent, REFERENCE_DESCRIPTION_CODE);
	}
	
    @Override
    public void onDestroy() {
        super.onDestroy();
		// ---------------------------------- begin code for Droid Inpector (search for this line in the project to suppress all occurences)
        //        ViewServer.get(this).removeWindow(this); 
		// ---------------------------------- end code for Droid Inpector
    }
 
    @Override
    public void onResume() {
        super.onResume();
		// ---------------------------------- begin code for Droid Inpector (search for this line in the project to suppress all occurences)
        //        ViewServer.get(this).setFocusedWindow(this);
		// ---------------------------------- end code for Droid Inpector
    }
	

}
