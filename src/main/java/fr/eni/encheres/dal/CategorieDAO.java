package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;

public interface CategorieDAO {

	void insert(Categorie categorie) throws BusinessException;

	List<Categorie> selectAll() throws BusinessException;

	Categorie selectById(int id) throws BusinessException;

	void delete(Categorie cat) throws BusinessException;

	void update(Categorie cat) throws BusinessException;

}