package fr.eni.encheres.messages;

import java.util.ResourceBundle;

/**
 * classe modelisant LecteurMessage 
 * permet de lire le contenu du fichier messages_erreur_properties
 * @author utilisateur
 *
 */
public class LecteurMessage {
	private static ResourceBundle rb;
	static {
		try
		{
			rb = ResourceBundle.getBundle("fr.eni.encheres.message.messages_erreur");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	//retourne le message
	
	public static  String getMessageErreur(int code)
	{
		String message="";
		try
		{
			if(rb!=null)
			{
				message = rb.getString(String.valueOf(code));
			}
			else
			{
				message="Problème à la lecture du fichier contenant les messages";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			message="Une erreur inconnue est survenue";
		}
		return message;
	}
}
