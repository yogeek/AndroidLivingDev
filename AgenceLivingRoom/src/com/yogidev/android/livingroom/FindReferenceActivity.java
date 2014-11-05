package com.yogidev.android.livingroom;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.yogidev.android.livingroom.data.bean.Recherche;

public class FindReferenceActivity extends Activity {
	
	protected static final int CODE_FIND_REFERENCE = 2;
	
	private Spinner spinnerVille;
	private Spinner spinnerQuartier;
	private Spinner spinnerType;
	private Spinner spinnerLoyer;
	private ToggleButton toggleLocationVente;
	private ImageButton buttonRechercher;
	private TextView textQuartier;
	
	Bundle objetbunble;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.find_reference);
	    
	    objetbunble = new Bundle();

		// Spinner Ville
	    spinnerVille = (Spinner) findViewById(R.id.SpinnerVille);
	    // Spinner Quartier
	    textQuartier = (TextView) findViewById(R.id.TextViewQuartier);
	    spinnerQuartier = (Spinner) findViewById(R.id.SpinnerQuartier);
	    spinnerQuartier.setVisibility(View.VISIBLE);
	    // Spinner Type
	    spinnerType = (Spinner) findViewById(R.id.SpinnerType);
	    // Toggle location/vente
	    toggleLocationVente = (ToggleButton) findViewById(R.id.ToggleLocationVente);
	    // Spinner Loyer
	    spinnerLoyer = (Spinner) findViewById(R.id.SpinnerLoyer);
	    
	    // Button Rechercher
	    buttonRechercher = (ImageButton) findViewById(R.id.ButtonRechercher);
	    
	    // hide the SpinnerQuartier if ville != "Toulouse"
	    spinnerVille.setOnItemSelectedListener( 
	    		new OnItemSelectedListener() {

	    			@Override
	    			public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long id) {
	    				String currentVille = parent.getItemAtPosition(pos).toString();
	    				if (currentVille.equals(getResources().getString(R.string.Toulouse))) {
	    					textQuartier.setVisibility(View.VISIBLE);
	    					spinnerQuartier.setVisibility(View.VISIBLE);
	    				}
	    				else {
	    					textQuartier.setVisibility(View.GONE);
	    					spinnerQuartier.setVisibility(View.GONE);
	    				}
	    			}

	    			@Override
	    			public void onNothingSelected(AdapterView<?> arg0) {
	    			}

	    		});
	    

	    buttonRechercher.setOnClickListener(
	    		new OnClickListener() {
	    			@Override
	    			public void onClick(View v) {
	    				try {
	    					// get values
	    					String ville = (String)spinnerVille.getSelectedItem();
	    					String quartier = (String)spinnerQuartier.getSelectedItem();
	    					String type = (String)spinnerType.getSelectedItem();
	    					boolean isLocation = toggleLocationVente.isChecked();
	    					String loyer = (String)spinnerLoyer.getSelectedItem();
	    						    					
	    					Recherche recherche = new Recherche(ville, quartier, type, isLocation, loyer);
	    					// TODO : Enregistrer la recherche
	    					// SerialTool.saveRecherche(recherche, getApplicationContext());
	    					objetbunble.putStringArray("recherche", new String[]{ville,quartier,type,Boolean.toString(isLocation),loyer});
	    					
	    					// Launch RechercheListActivity
	    					Intent intent = new Intent(FindReferenceActivity.this, ReferenceListActivity.class);
	    					intent.putExtras(objetbunble);
	    					startActivityForResult(intent, CODE_FIND_REFERENCE );

	    				} catch (Exception e) {
	    					e.printStackTrace();
	    				}
	    			}
	    		}
	    		);
	    
	    
	    // Get the Action Bar 
	    ActionBar actionBar = getActionBar();
	    // Enable the app icon as an Up button
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    // Set title
	    actionBar.setTitle("Trouver un bien");
	    // Set logo
	    //actionBar.setLogo(R.drawable.loupe);
	    actionBar.setDisplayUseLogoEnabled(true);
	    
	}
	

	/**
	 * Listener on the toggle button "A louer / A vendre"
	 * 
	 * Behaviour : hide SpinnerLoyer if toggleLocationVente is off (i.e. Vente)
	 */
	public void onToggleLocationVenteClicked(View view) {
		if (toggleLocationVente.isChecked()) {
			spinnerLoyer.setVisibility(View.VISIBLE);
		}
		else {
			spinnerLoyer.setVisibility(View.GONE);
		}
	}
	

}
