package com.yogidev.android.livingroom.data.bean;

import java.net.URL;
import java.util.Date;
import java.util.List;

import com.yogidev.android.livingroom.data.util.Coordinate;

/**
 * Classe représentant une référence de la base de données 
 * 
 * @author YoGi
 *
 */
public class Reference {
	
	// Ref
	private long id = 0;
	// Titre de la référence
	private String titreRef = "";
	// Type de référence
	private String typeRef = "";
	// Titre pour l'administrateur (non vu par les visiteurs)
	private String titreAdmin = "";
	// Information interne
	private String infoInterne = "";
	// Location ou Vente
	private String locVente = "";
	// Appartement ou Maison
	private String appartementMaison = "";
	// Ville
	private String ville = "";
	// Quartier
	private String quartier = "";
	// Loyer charges comprises (ou Prix FAI dans le cas d'une vente)
	private int loyerOuPrix = 0;
	// Charges (ou Charges de copropriété dans le cas d'une vente)
	private int chargesOuCopro = 0;
	// Dépôt de garantie (ou taxe foncière dans le cas d'une vente)
	private int depotOuTaxe = 0;
	// Frais d'agence (en % dans le cas d'une vente)
	private double fraisAgence = 0.0;
	// Latitude/Longitude
	private Coordinate latLon = null;
	// Descriptif
	private String descriptif = "";
	// Surface (en m2)
	private double surface = 0.0;
	// Nombre de lots de la copropriété
	private int nbLotCopro = 0;
	// DPE
	private String dpe = "";
	// GES
	private String ges = "";
	// Equipements
	private List<String> listeEquipements = null;
	// Vignette/Photo
	private String vignette = null;
	// Video youtube
	private URL videoYoutube = null;
	// Disponible ?
	private boolean disponible = true;
	// Nouveauté ?
	private boolean nouveaute = true;
	// Visible ?
	private boolean visible = true;
	// Libre à partir du
	private Date libreDate = null;
	
	public Reference() {
	}


	/**
	 * Constructeur
	 * 
	 */
	
	public Reference(long id, String titreRef, String typeRef, String ville,
			String quartier, int loyerOuPrix, double surface, String vignette) {
		this.id = id;
		this.titreRef = titreRef;
		this.typeRef = typeRef;
		this.ville = ville;
		this.quartier = quartier;
		this.loyerOuPrix = loyerOuPrix;
		this.surface = surface;
		this.vignette = vignette;
	}
	

	public Reference(long id, String titreRef, String typeRef,
			String titreAdmin, String infoInterne, String locVente,
			String appartementMaison, String ville, String quartier,
			int loyerOuPrix, int chargesOuCopro, int depotOuTaxe,
			double fraisAgence, Coordinate latLon, String descriptif,
			double surface, int nbLotCopro, String dpe, String ges,
			List<String> listeEquipements, String vignette, URL videoYoutube,
			boolean disponible, boolean nouveaute, boolean visible,
			Date libreDate) {
		super();
		this.id = id;
		this.titreRef = titreRef;
		this.typeRef = typeRef;
		this.titreAdmin = titreAdmin;
		this.infoInterne = infoInterne;
		this.locVente = locVente;
		this.appartementMaison = appartementMaison;
		this.ville = ville;
		this.quartier = quartier;
		this.loyerOuPrix = loyerOuPrix;
		this.chargesOuCopro = chargesOuCopro;
		this.depotOuTaxe = depotOuTaxe;
		this.fraisAgence = fraisAgence;
		this.latLon = latLon;
		this.descriptif = descriptif;
		this.surface = surface;
		this.nbLotCopro = nbLotCopro;
		this.dpe = dpe;
		this.ges = ges;
		this.listeEquipements = listeEquipements;
		this.vignette = vignette;
		this.videoYoutube = videoYoutube;
		this.disponible = disponible;
		this.nouveaute = nouveaute;
		this.visible = visible;
		this.libreDate = libreDate;
	}
	

	/**
	 * toString()
	 */
	public String toString(){
		String str = 	"Reference : " + this.getId() + "\n";
		str += 			"Titre : " + this.getTitreRef() + "\n";
		str += 			"Type : " + this.getTypeRef() + "\n";
		str += 			"Titre admin : " + this.getTitreAdmin() + "\n";
		str += 			"Info interne : " + this.getInfoInterne() + "\n";
		str += 			"Location/Vente : " + this.getLocVente() + "\n";
		str += 			"Appartement/Maison : " + this.getAppartementMaison() + "\n";
		str += 			"Ville : " + this.getVille() + "\n";
		str += 			"Quartier : " + this.getQuartier() + "\n";
		str += 			"Loyer ou Prix : " + this.getLoyerOuPrix() + "\n";
		str +=			"\n.....................................\n";
		
		return str;
	}


	/** 
	 * Accesseurs
	 * 
	 */
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTypeRef() {
		return typeRef;
	}


	public void setTypeRef(String typeRef) {
		this.typeRef = typeRef;
	}


	public String getTitreAdmin() {
		return titreAdmin;
	}


	public void setTitreAdmin(String titreAdmin) {
		this.titreAdmin = titreAdmin;
	}


	public String getInfoInterne() {
		return infoInterne;
	}


	public void setInfoInterne(String infoInterne) {
		this.infoInterne = infoInterne;
	}


	public String getLocVente() {
		return locVente;
	}


	public void setLocVente(String locVente) {
		this.locVente = locVente;
	}


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


	public int getPrix() {
		return loyerOuPrix;
	}


	public void setPrix(int prix) {
		this.loyerOuPrix = prix;
	}
	
	
	public boolean isDisponible() {
		return disponible;
	}


	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}


	public boolean isNouveaute() {
		return nouveaute;
	}


	public void setNouveaute(boolean nouveaute) {
		this.nouveaute = nouveaute;
	}


	public boolean isVisible() {
		return visible;
	}


	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	

	public String getTitreRef() {
		return titreRef;
	}


	public void setTitreRef(String titreRef) {
		this.titreRef = titreRef;
	}


	public String getAppartementMaison() {
		return appartementMaison;
	}


	public void setAppartementMaison(String appartementMaison) {
		this.appartementMaison = appartementMaison;
	}


	public int getLoyerOuPrix() {
		return loyerOuPrix;
	}


	public void setLoyerOuPrix(int loyerOuPrix) {
		this.loyerOuPrix = loyerOuPrix;
	}


	public int getChargesOuCopro() {
		return chargesOuCopro;
	}


	public void setChargesOuCopro(int chargesOuCopro) {
		this.chargesOuCopro = chargesOuCopro;
	}


	public int getDepotOuTaxe() {
		return depotOuTaxe;
	}


	public void setDepotOuTaxe(int depotOuTaxe) {
		this.depotOuTaxe = depotOuTaxe;
	}


	public double getFraisAgence() {
		return fraisAgence;
	}


	public void setFraisAgence(double fraisAgence) {
		this.fraisAgence = fraisAgence;
	}


	public Coordinate getLatLon() {
		return latLon;
	}


	public void setLatLon(Coordinate latLon) {
		this.latLon = latLon;
	}


	public String getDescriptif() {
		return descriptif;
	}


	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}


	public double getSurface() {
		return surface;
	}


	public void setSurface(double surface) {
		this.surface = surface;
	}


	public int getNbLotCopro() {
		return nbLotCopro;
	}


	public void setNbLotCopro(int nbLotCopro) {
		this.nbLotCopro = nbLotCopro;
	}


	public String getDpe() {
		return dpe;
	}


	public void setDpe(String dpe) {
		this.dpe = dpe;
	}


	public String getGes() {
		return ges;
	}


	public void setGes(String ges) {
		this.ges = ges;
	}


	public List<String> getListeEquipements() {
		return listeEquipements;
	}


	public void setListeEquipements(List<String> listeEquipements) {
		this.listeEquipements = listeEquipements;
	}


	public String getVignette() {
		return vignette;
	}


	public void setVignette(String vignette) {
		this.vignette = vignette;
	}


	public URL getVideoYoutube() {
		return videoYoutube;
	}


	public void setVideoYoutube(URL videoYoutube) {
		this.videoYoutube = videoYoutube;
	}


	public Date getLibreDate() {
		return libreDate;
	}


	public void setLibreDate(Date libreDate) {
		this.libreDate = libreDate;
	}


}
