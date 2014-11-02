package com.yogidev.android.livingroom.data.bean;

/**
 * Classe représentant une recherche de référence 
 * 
 * @author YoGi
 *
 */

public class Recherche {
	
	private long id = 0;
	
	private String ville = "";
	
	private String quartier = "";
	
	private String type = "";
	
	private boolean isLocation = true;
	
	private String loyer = "";
	
	
	/**
	 * Constructeur
	 * 
	 */

	public Recherche(long id, String ville, String quartier, String type,
			boolean isLocation, String loyer) {
		this.id = id;
		this.ville = ville;
		this.quartier = quartier;
		this.type = type;
		this.isLocation = isLocation;
		this.loyer = loyer;
	}
	

	public Recherche(String ville, String quartier, String type,
			boolean isLocation, String loyer) {
		this.ville = ville;
		this.quartier = quartier;
		this.type = type;
		this.isLocation = isLocation;
		this.loyer = loyer;
	}




	/**
	 * toString()
	 */
	public String toString(){
		String str = 	"Recherche : " + this.getId() + "\n";
		str += 			"Ville : " + this.getVille() + "\n";
		str += 			"Quartier : " + this.getQuartier() + "\n";
		str += 			"Type : " + this.getType() + "\n";
		str += 			"Location/Vente : " + (this.isLocation()?"Location":"Vente") + "\n";
		str += 			"Loyer : " + this.getLoyer() + "\n";
		str +=			"\n.....................................\n";
		
		return str;
	}
	
	
	/** 
	 * Accesseurs
	 * 
	 */

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getQuartier() {
		return quartier;
	}

	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isLocation() {
		return isLocation;
	}

	public void setLocation(boolean isLocation) {
		this.isLocation = isLocation;
	}

	public String getLoyer() {
		return loyer;
	}

	public void setLoyer(String loyer) {
		this.loyer = loyer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	

}
