package fr.eni.encheres.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer noUtilisateur;
	private String pseudo;
	private String nom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String motDePasse;
	private int credit;
	private int administrateur;
	
	private List<Article> listeArticlesAchetes = new ArrayList<Article>();  
	private List<Article> listeArticlesVendus = new ArrayList<Article>();  
	
	private List<Enchere> listeEncheres = new ArrayList<Enchere>();
	
	public Utilisateur() {
		
	}
	
	
	public Utilisateur(String pseudo, String nom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, int credit, int administrateur, List<Article> listeArticlesAchetes, List<Article> listeArticlesVendus, List<Enchere> listeEncheres) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
		
		this.listeArticlesAchetes = listeArticlesAchetes;
		this.listeArticlesVendus = listeArticlesVendus;
		
		this.listeEncheres = listeEncheres;
	}
	
	public Utilisateur(Integer noUtilisateur, String pseudo, String nom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, int credit, int administrateur, List<Article> listeArticlesAchetes, List<Article> listeArticlesVendus, List<Enchere> listeEncheres) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
		
		this.listeArticlesAchetes = new ArrayList<Article>();
		this.listeArticlesVendus = new ArrayList<Article>();
		
		this.listeEncheres = new ArrayList<Enchere>();
	}


	public Integer getNoUtilisateur() {
		return noUtilisateur;
	}


	public void setNoUtilisateur(Integer noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}


	public String getPseudo() {
		return pseudo;
	}


	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getRue() {
		return rue;
	}


	public void setRue(String rue) {
		this.rue = rue;
	}


	public String getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public String getMotDePasse() {
		return motDePasse;
	}


	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}


	public int getCredit() {
		return credit;
	}


	public void setCredit(int credit) {
		this.credit = credit;
	}


	public int isAdministrateur() {
		return administrateur;
	}


	public void setAdministrateur(int administrateur) {
		this.administrateur = administrateur;
	}


	public List<Article> getListeArticlesAchetes() {
		return listeArticlesAchetes;
	}


	public void setListeArticlesAchetes(List<Article> listeArticlesAchetes) {
		this.listeArticlesAchetes = listeArticlesAchetes;
	}


	public List<Article> getListeArticlesVendus() {
		return listeArticlesVendus;
	}


	public void setListeArticlesVendus(List<Article> listeArticlesVendus) {
		this.listeArticlesVendus = listeArticlesVendus;
	}


	public List<Enchere> getListeEncheres() {
		return listeEncheres;
	}


	public void setListeEncheres(List<Enchere> listeEncheres) {
		this.listeEncheres = listeEncheres;
	}


	@Override
	public String toString() {
		return "Utilisateur [noUtilisateur=" + noUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", email="
				+ email + ", telephone=" + telephone + ", rue=" + rue + ", codePostal=" + codePostal + ", ville="
				+ ville + ", motDePasse=" + motDePasse + ", credit=" + credit + ", administrateur=" + administrateur
				+ ", listeArticlesAchetes=" + listeArticlesAchetes + ", listeArticlesVendus=" + listeArticlesVendus
				+ ", listeEncheres=" + listeEncheres + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + administrateur;
		result = prime * result + ((codePostal == null) ? 0 : codePostal.hashCode());
		result = prime * result + credit;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((listeArticlesAchetes == null) ? 0 : listeArticlesAchetes.hashCode());
		result = prime * result + ((listeArticlesVendus == null) ? 0 : listeArticlesVendus.hashCode());
		result = prime * result + ((listeEncheres == null) ? 0 : listeEncheres.hashCode());
		result = prime * result + ((motDePasse == null) ? 0 : motDePasse.hashCode());
		result = prime * result + ((noUtilisateur == null) ? 0 : noUtilisateur.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
		result = prime * result + ((rue == null) ? 0 : rue.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		if (administrateur != other.administrateur)
			return false;
		if (codePostal == null) {
			if (other.codePostal != null)
				return false;
		} else if (!codePostal.equals(other.codePostal))
			return false;
		if (credit != other.credit)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (listeArticlesAchetes == null) {
			if (other.listeArticlesAchetes != null)
				return false;
		} else if (!listeArticlesAchetes.equals(other.listeArticlesAchetes))
			return false;
		if (listeArticlesVendus == null) {
			if (other.listeArticlesVendus != null)
				return false;
		} else if (!listeArticlesVendus.equals(other.listeArticlesVendus))
			return false;
		if (listeEncheres == null) {
			if (other.listeEncheres != null)
				return false;
		} else if (!listeEncheres.equals(other.listeEncheres))
			return false;
		if (motDePasse == null) {
			if (other.motDePasse != null)
				return false;
		} else if (!motDePasse.equals(other.motDePasse))
			return false;
		if (noUtilisateur == null) {
			if (other.noUtilisateur != null)
				return false;
		} else if (!noUtilisateur.equals(other.noUtilisateur))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (pseudo == null) {
			if (other.pseudo != null)
				return false;
		} else if (!pseudo.equals(other.pseudo))
			return false;
		if (rue == null) {
			if (other.rue != null)
				return false;
		} else if (!rue.equals(other.rue))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		if (ville == null) {
			if (other.ville != null)
				return false;
		} else if (!ville.equals(other.ville))
			return false;
		return true;
	}


	


	
	
}