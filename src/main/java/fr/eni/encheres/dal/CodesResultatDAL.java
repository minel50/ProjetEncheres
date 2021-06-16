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
	 * Echec de l'insertion d'un avis à cause de la note
	 */
	public static final int INSERT_ECHEC=10002;
	
}
