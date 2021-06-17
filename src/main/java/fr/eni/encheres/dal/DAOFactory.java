package fr.eni.encheres.dal;

public abstract class DAOFactory {
	
	private static UtilisateurDAO utilisateurDAO;
	private static CategorieDAO categorieDAO;
	private static RetraitDAO retraitDAO;
	private static ArticleDAO articleDAO;
	private static EnchereDAO enchereDAO;
	
	public static UtilisateurDAO getUtilisateurDAO() {
		if (utilisateurDAO == null) {
			utilisateurDAO = new UtilisateurDAOJdbcImpl();
		}
		return utilisateurDAO;
	}
	
	public static CategorieDAO getCategorieDAO() {
		if (categorieDAO == null) {
			categorieDAO = new CategorieDAOJdbcImpl();
		}
		return categorieDAO;
	}
	
	public static RetraitDAO getRetraitDAO() {
		if (retraitDAO == null) {
			retraitDAO = new RetraitDAOJdbcImpl();
		}
		return retraitDAO;
	}
	
	public static ArticleDAO getArticleDAO() {
		if (articleDAO == null) {
			articleDAO = new ArticleDAOJdbcImpl();
		}
		return articleDAO;
	}
	
	public static EnchereDAO getEnchereDAO() {
		if (enchereDAO == null) {
			enchereDAO = new EnchereDAOJdbcImpl();
		}
		return enchereDAO;
	}
}
