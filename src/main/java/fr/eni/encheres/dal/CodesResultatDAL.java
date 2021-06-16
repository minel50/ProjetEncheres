package fr.eni.encheres.dal;
/**
 * classe modelisant les codes d'erreur sur la DAL/de 10000 à 19999
 * @author utilisateur
 *
 */
public class CodesResultatDAL {
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJET_NULL=10000;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int INSERT_OBJET_ECHEC=10001;
	
	/**
	 * Echec général quand tentative de modifier avec un objet null
	 */
	public static final int UPDATE_OBJET_NULL=10002;
	
	/**
	 * Echec général quand tentative de supprimer un objet null
	 */
	public static final int DELETE_OBJET_NULL=10003;
	
	/**
	 * Echec lecture des données en base de données
	 */
	public static final int READ_DATA_ECHEC=10004;
	
	/**
	 * Echec modification des données en base de données
	 */
	public static final int UDPATE_DATA_ECHEC=10005;
	
	/**
	 * Echec suppression des données en base de données
	 */
	public static final int DELETE_DATA_ECHEC=10006;
	
}
