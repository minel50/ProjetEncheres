package fr.eni.encheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;



public interface UtilisateurDAO {
	public void insert(Utilisateur data) throws BusinessException;
	public Utilisateur selectById(int id) throws BusinessException ;
	public List<Utilisateur> selectAll() throws BusinessException ;
	public void delete(Utilisateur utilisateurCritere) throws BusinessException;
	public void update(Utilisateur data) throws BusinessException ;
}
