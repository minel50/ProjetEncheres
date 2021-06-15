package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Categorie;

public interface CategorieDAO {

	void insert(Categorie categorie);

	List<Categorie> selectAll();

}