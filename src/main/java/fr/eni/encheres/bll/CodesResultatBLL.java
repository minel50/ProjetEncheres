package fr.eni.encheres.bll;

/**
 * Plages de codes entre 20000 et 29999
 */
public class CodesResultatBLL {

	/**
	 * Un objet transmis est NULL.
	 */
	public static final int OBJECT_NULL = 20000;
	
	/**
	 * Le nom de l'article est obligatoire et doit comporter au maximum 30 caractères.
	 */
	public static final int ERREUR_NOM_ARTICLE = 20001;
	
	/**
	 * La description de l'article est obligatoire et doit comporter au maximum 300 caractères.
	 */
	public static final int ERREUR_DESCRIPTION_ARTICLE = 20002;
	
	/**
	 * Les dates de début et fin d'enchères doivent être renseignées.
	 */
	public static final int ERREUR_DATE_ENCHERES_NULL = 20003;
	
	/**
	 * La date de début d'enchère ne peut pas être dans le passé.
	 */
	public static final int ERREUR_DATE_DEBUT_ENCHERE = 20004;
	
	/**
	 * La date de fin d'enchère ne peut pas être antérieure à la date de début d'enchères.
	 */
	public static final int ERREUR_DATE_FIN_ENCHERE = 20005;
	
	/**
	 * Les prix ne peuvent pas être négatifs.
	 */
	public static final int ERREUR_PRIX_NEGATIF = 20006;
	
	/**
	 * Aucun utilisateur n'est associé à cet article.
	 */
	public static final int ERREUR_MANQUE_UTILISATEUR = 20007;
	
	/**
	 * Aucune catégorie n'est associée à cet article.
	 */
	public static final int ERREUR_MANQUE_CATEGORIE = 20008;
	
}
