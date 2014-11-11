package com.yogidev.android.livingroom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.ActionBar;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.yogidev.android.livingroom.SwipeListView.SwipeListViewCallback;
import com.yogidev.android.livingroom.data.bean.Reference;

public class ReferenceListActivity extends ListActivity implements SwipeListViewCallback {
	
//	private ListView mListView;
	private ReferenceListAdapter mAdapter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
		// ---------------------------------- begin code for Droid Inpector (search for this line in the project to suppress all occurences)
//		ViewServer.get(this).addWindow(this); 
		// ---------------------------------- end code for Droid Inpector
	    
	    setContentView(R.layout.reference_list_view);
	    
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
	    
	    // Retrieve Bundle object
        final Bundle objetbunble  = getIntent().getExtras();
        
        // get content of the "recherche" object
        String[] rechercheDetails = (String[]) objetbunble.get("recherche");
        
        // AlertDialog to display the content
//		AlertDialog.Builder adbRechercheContent = new AlertDialog.Builder(this);
//		adbRechercheContent.setTitle("Détails de la recherche");
//		adbRechercheContent.setPositiveButton("Ok", null);
//		String message = "Ville : "    + rechercheDetails[0] + "/n";
//		message 	  += "Quartier : " + rechercheDetails[1] + "/n";
//		message 	  += "Type : "     + rechercheDetails[2] + "/n";
//		message 	  += "Location : " + rechercheDetails[3] + "/n";
//		message 	  += "Loyer : " + rechercheDetails[4] + "/n";
//		adbRechercheContent.setMessage(message);
//		adbRechercheContent.show();
	    
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
		Toast.makeText(this, "Item " + item.getTitreRef() + " clicked !", Toast.LENGTH_SHORT).show();
		// set the flag to make the "delete" button disappear
		mAdapter.DELETE_POS = mAdapter.INVALID;
		mAdapter.notifyDataSetChanged();
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
