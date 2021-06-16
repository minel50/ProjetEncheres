package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Article;

public interface ArticleDAO {
	public void insert(Article article);
	public List<Article> selectAll();
	public Article selectById(int id);
	public void update(Article article);
	public void delete(Article article);
}
