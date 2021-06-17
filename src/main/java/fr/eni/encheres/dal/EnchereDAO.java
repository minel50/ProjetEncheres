package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {
	public void insert (Enchere enchere) throws BusinessException;
	public List<Enchere> selectAll() throws BusinessException;
	public List<Enchere> selectByUtilisateur(int idUtilisateur) throws BusinessException;
	public List<Enchere> selectByArticle(int idArticle) throws BusinessException;
	public Enchere selectByUtilisateurEtArticle(int idUtilisateur, int idArticle) throws BusinessException;
}	
