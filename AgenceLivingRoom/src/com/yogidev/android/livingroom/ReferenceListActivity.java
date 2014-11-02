package com.yogidev.android.livingroom;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yogidev.android.livingroom.data.bean.Reference;

public class ReferenceListActivity extends ActionBarActivity {
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.reference_list_view);
	    
	    // Retrieve Bundle object
        final Bundle objetbunble  = getIntent().getExtras();
        
        // get content of the "recherche" object
        String[] rechercheDetails = (String[]) objetbunble.get("recherche");
        
        // AlertDialog to display the content
		AlertDialog.Builder adbRechercheContent = new AlertDialog.Builder(this);
		adbRechercheContent.setTitle("Détails de la recherche");
		adbRechercheContent.setPositiveButton("Ok", null);
		String message = "Ville : "    + rechercheDetails[0] + "/n";
		message 	  += "Quartier : " + rechercheDetails[1] + "/n";
		message 	  += "Type : "     + rechercheDetails[2] + "/n";
		message 	  += "Location : " + rechercheDetails[3] + "/n";
		message 	  += "Loyer : " + rechercheDetails[4] + "/n";
		adbRechercheContent.setMessage(message);
		adbRechercheContent.show();
	    
	    // ref
	    Reference r1 = new Reference(1, "T3 - 60 m² NEUF BBC, avec 1 parking", "T3", "Toulouse", "Les Chalets", "Vente", 194250, 60, "http://www.agence-livingroom.com/references/1042/photo_reference.jpg");
	    Reference r2 = new Reference(1, "T2 - 51m² avec parking", "T3", "Toulouse", "Argoulets", "Location", 800, 51, "http://www.agence-livingroom.com/references/1057/photo_reference.jpg");
	    Reference r3 = new Reference(1, "T4 - 90 m²", "T4", "Toulouse", "Matabiau", "Vente", 253150, 90, "http://www.agence-livingroom.com/references/1100/photo_reference.jpg");
	    Reference r4 = new Reference(1, "T1 - 20 m² ", "T1", "Toulouse", "Hypercentre", "Location", 350, 20, "http://www.agence-livingroom.com/references/1110/photo_reference.jpg");

	    final ArrayList<Reference> list = new ArrayList<Reference>();
	    list.add(r1);
	    list.add(r2);
	    list.add(r3);
	    list.add(r4);
	    list.add(r1);
	    list.add(r2);
	    list.add(r3);
	    list.add(r4);
	    list.add(r1);
	    list.add(r2);
	    list.add(r3);
	    list.add(r4);
	    list.add(r1);
	    list.add(r2);
	    list.add(r3);
	    list.add(r4);
	    list.add(r1);
	    list.add(r2);
	    list.add(r3);
	    list.add(r4);
	    list.add(r1);
	    list.add(r2);
	    list.add(r3);
	    list.add(r4);
	    list.add(r1);
	    list.add(r2);
	    list.add(r3);
	    list.add(r4);
	    final ReferenceListAdapter adapter = new ReferenceListAdapter(this, list);
	    
	    // List View des references
	    ListView listview = (ListView) findViewById(R.id.referencelistview);
	    listview.setAdapter(adapter);
	    
	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	    	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	    	@Override
	    	public void onItemClick(AdapterView<?> parent, final View view,
	    			int position, long id) {
	    		final Reference item = (Reference) parent.getItemAtPosition(position);
	    		view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
	    			@Override
	    			public void run() {
	    				list.remove(item);
	    				adapter.notifyDataSetChanged();
	    				view.setAlpha(1);
	    			}
	    		});
	    	}

	    });
 
	    
	    // Get the Action Bar 
	    ActionBar actionBar = getSupportActionBar();
	    // Enable the app icon as an Up button
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    // Set title
	    actionBar.setTitle("References");
	    // Set logo
	    actionBar.setLogo(R.drawable.partage);
	    
	
	}
	

}
