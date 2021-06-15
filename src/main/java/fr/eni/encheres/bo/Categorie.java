  
package fr.eni.encheres.bo;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
	private Integer noCategorie;
	private String libelle;
	
	private List<Article> listeArticles;
	
	// Constructeurs
	public Categorie() {
		listeArticles = new ArrayList<Article>();
	}
	
	public Categorie(Integer noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}
	
	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
		listeArticles = new ArrayList<Article>();
		
	}

	//Setters et getters
	public Integer getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(Integer noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	//toString
	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + "]";
	}
	
	
}