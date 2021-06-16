package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;



public interface UtilisateurDAO {
	public void insert(Utilisateur data);
	public Utilisateur selectById(Utilisateur utilisateurCritere);
	public List<Utilisateur> selectAll();
	public void delete(Utilisateur utilisateurCritere);
	public void update(Utilisateur data);
}
