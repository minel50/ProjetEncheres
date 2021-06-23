package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

public interface EnchereDAO {
	public void insert (Enchere enchere) throws BusinessException;
	Enchere getMeilleureEnchereByArticle(int noArticle) throws BusinessException;
	public List<Enchere> selectByUtilisateur(int noUtilisateur) throws BusinessException;
	public List<Enchere> selectByArticle(int noArticle) throws BusinessException;
	public Enchere selectByUtilisateurEtArticle(int noUtilisateur, int noArticle) throws BusinessException;
	void update(Enchere enchere) throws BusinessException;
}	
