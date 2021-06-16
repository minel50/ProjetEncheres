package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Retrait;

public interface RetraitDAO {

	void update(Retrait retrait) throws BusinessException;
	
	void insert(Retrait retrait) throws BusinessException;

	List<Retrait> selectAll() throws BusinessException;

	Retrait selectById(int id) throws BusinessException;

	void delete(Retrait retrait) throws BusinessException;

}
