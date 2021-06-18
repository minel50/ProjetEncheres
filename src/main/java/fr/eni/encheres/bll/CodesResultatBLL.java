package fr.eni.encheres.bll;

/**
 * Plages de codes entre 20000 et 29999
 */
public class CodesResultatBLL {

	/**
	 * Un objet transmis est NULL.
	 */
	public static final int OBJECT_NULL = 20000;
	
	//-------Erreurs pour ArticleManager-------------
	/**
	 * Le nom de l'article est obligatoire et doit comporter au maximum 30 caractères.
	 */
	public static final int ERREUR_NOM_ARTICLE = 20100;
	
	/**
	 * La description de l'article est obligatoire et doit comporter au maximum 300 caractères.
	 */
	public static final int ERREUR_DESCRIPTION_ARTICLE = 20101;
	
	/**
	 * Les dates de début et fin d'enchères doivent être renseignées.
	 */
	public static final int ERREUR_DATE_ENCHERES_NULL = 20102;
	
	/**
	 * La date de début d'enchère ne peut pas être dans le passé.
	 */
	public static final int ERREUR_DATE_DEBUT_ENCHERE = 20103;
	
	/**
	 * La date de fin d'enchère ne peut pas être antérieure à la date de début d'enchères.
	 */
	public static final int ERREUR_DATE_FIN_ENCHERE = 20104;
	
	/**
	 * Les prix ne peuvent pas être négatifs.
	 */
	public static final int ERREUR_PRIX_NEGATIF = 20105;
	
	/**
	 * Aucun utilisateur n'est associé à cet article.
	 */
	public static final int ERREUR_MANQUE_UTILISATEUR = 20106;
	
	/**
	 * Aucune catégorie n'est associée à cet article.
	 */
	public static final int ERREUR_MANQUE_CATEGORIE = 20107;
	
	//-------Erreurs pour EnchereManager-------------
	/**
	 * Aucun utilisateur n'est associé à cette enchère.
	 */
	public static final int ERREUR_ENCHERE_MANQUE_UTILISATEUR = 20200;
	
	/**
	 * Aucun article n'est associé à cette enchère.
	 */
	public static final int ERREUR_ENCHERE_MANQUE_ARTICLE = 20201;
	
	/**
	 * Le montant de l'enchère doit être positif.
	 */
	public static final int ERREUR_ENCHERE_MONTANT_NEGATIF = 20202;
	
	//-------Erreurs pour CategorieManager-------------
	/**
	 * Le libelle de la catégorie est obligatoire et doit comporter au maximum 30 caractères.
	 */
	public static final int ERREUR_NOM_CATEGORIE = 20300;
	
	//-------Erreurs pour RetraitManager-------------
	/**
	 * Le champ rue du point de retrait est obligatoire et doit comporter au maximum 30 caractères.
	 */
	public static final int ERREUR_RUE_RETRAIT = 20400;	
	
	/**
	 * Le champ code postal du point de retrait est obligatoire et doit comporter au maximum 15 caractères.
	 */
	public static final int ERREUR_CODE_POSTAL_RETRAIT = 20401;	
	
	/**
	 * Le champ ville du point de retrait est obligatoire et doit comporter au maximum 30 caractères.
	 * 
	 */
	public static final int ERREUR_VILLE_RETRAIT = 20402;
	
	//le champ pseudo utilisateur est obligatoire et doit comporter   caractères
	public static final int ERREUR_PSEUDO_USER = 20011;
	//le champ nom utilisateur est obligatoire et doit comporter   caractères
	public static final int ERREUR_NOM_USER = 20012;
	//le champ prenom utilisateur est obligatoire et doit comporter   caractères
	public static final int ERREUR_PRENOM_USER = 20013;
	//le champ prenom utilisateur est obligatoire et doit comporter   caractères
	public static final int ERREUR_EMAIL_USER = 20014;
	//le champ telephone utilisateur est obligatoire et doit comporter   caractères
	public static final int ERREUR_TELEPHONE_USER = 20015;
	//le champ prenom utilisateur est obligatoire et doit comporter   caractères
	public static final int ERREUR_RUE_USER = 20016;
	//le champ code postal utilisateur est obligatoire et doit comporter   caractères
	public static final int ERREUR_CODEPOSTAL_USER = 20017;
	//le champ ville utilisateur est obligatoire et doit comporter   caractères
	public static final int ERREUR_VILLE_USER = 20018;
	//le champ mot de passe utilisateur est obligatoire et doit comporter   caractères
	public static final int ERREUR_MOTDEPASSE_USER = 20019;
	//le champ credit  utilisateur ne doit pas être négatif
	public static final int ERREUR_CREDIT_NEGATIF = 20009;
	//le champ administarteur utilisateur  ne peut être que 0 et 1
	public static final int ERREUR_CODE_ADMIN = 20010;	
	
}
