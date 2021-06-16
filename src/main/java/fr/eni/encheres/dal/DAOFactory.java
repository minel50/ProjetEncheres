package fr.eni.encheres.dal;

public abstract class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}
	
	/*public static RetraitDAO getRetraitDAO() {
		return new RetraitDAOJdbcImpl();
	}*/
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}
	
	/*public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}*/
}
