package fr.eni.encheres;

import java.util.List;

/**
 * classe modelisant une classe BusinessException
 * ensemble des erreurs (par leur code) pouvant survenir sur n'importe quelle couche à l'origine
 * @author utilisateur
 *
 */
public class BusinessException extends Exception{

	
	private static final long serialVersionUID = 6769293434571109323L;
	private List<Integer> listeCodesErreur;
	
	//Constructeur
	public BusinessException(List<Integer> listeCodesErreur) {
		super();
		this.listeCodesErreur = listeCodesErreur;
	}
	//Code de l'erreur. Doit avoir un message associé dans un fichier properties.
	public void ajouterErreur(int code) {
		if(!this.listeCodesErreur.contains(code)) {
			this.listeCodesErreur.add(code);
		}
	}
	public boolean hasErreurs() {
		return this.listeCodesErreur.size()>0;
	}
	

	//Getter
	public List<Integer> getListeCodesErreur()
	{
	return this.listeCodesErreur;
	}
}