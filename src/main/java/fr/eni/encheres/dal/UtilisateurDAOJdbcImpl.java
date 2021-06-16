package fr.eni.encheres.dal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{

	private static final  String sqlInsert ="INSERT  INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)"
			+ "values(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String sqlSelectById = "select no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur from UTILISATEURS where no_utilisateur=?";
	private  static final String sqlSelectAll = "select* from utilisateurs";
	private static final String sqlDelete = "delete from utilisateurs where no_utilisateur=?";
	private static final String sqlUpdate = "update UTILISATEURS set pseudo=?,nom=?,prenom=?,email=?,telephone=?,rue=?,code_postal=?,ville=?,mot_de_passe=?,credit=?, administrateur =? where no_utilisateur=?";
	
	public void insert(Utilisateur data ) throws BusinessException {
		if(data==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		 
		 
		Connection cnx = null;
		PreparedStatement stmt =null;
		ResultSet resultSet = null;
		
		try 
		{	cnx = ConnectionProvider.getConnection();
			stmt = cnx.prepareStatement(sqlInsert,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,data.getPseudo());
			stmt.setString(2, data.getNom());
			stmt.setString(3, data.getPrenom());
			stmt.setString(4,data.getEmail());
			stmt.setString(5,data.getTelephone());
			stmt.setString(6,data.getRue());
			stmt.setString(7,data.getCodePostal());
			stmt.setString(8,data.getVille());
			stmt.setString(9,data.getMotDePasse());
			stmt.setInt(10,data.getCredit());
			stmt.setInt(11,0);//on considere que les utilisateurs ne sont pas admin en dur dans BDD
			int nbRows = stmt.executeUpdate();
			if(nbRows==1) {
				 resultSet= stmt.getGeneratedKeys();
				
				
				if(resultSet.next()) {
					data.setNoUtilisateur(resultSet.getInt(1));
				
				}
			
			}
			
			} catch (SQLException e) 
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
			
		}finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (cnx != null) {
					cnx.close();
				}
			
				}catch (SQLException e) {
			e.printStackTrace();
			}
		
		}
		
	}
	
	public Utilisateur selectById(int id)  throws BusinessException{
		PreparedStatement stmt =null;
		ResultSet resultSet=null;
		Utilisateur utilisateur = null;
		
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			stmt=cnx.prepareStatement(sqlSelectById);
			stmt.setInt(1,id);
			resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				utilisateur = new Utilisateur (resultSet.getInt("no_utilisateur"),
											   resultSet.getString("pseudo"),
											   resultSet.getString("nom"),
											   resultSet.getString("prenom"),
											   resultSet.getString("email"),
											   resultSet.getString("telephone"),
											   resultSet.getString("rue"),
											   resultSet.getString("code_postal"),
											   resultSet.getString("ville"),
											   resultSet.getString("mot_de_passe"),
											
											   resultSet.getInt("credit"),
											   resultSet.getInt("administrateur"));
												
											  
				
										
			}
			
			
		} catch (SQLException e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.READ_DATA_ECHEC);
			throw businessException;
		
		}
		return utilisateur;
	}

	public List<Utilisateur> selectAll() throws BusinessException{
	
		PreparedStatement stmt =null;
		ResultSet resultSet = null; 
		List<Utilisateur> listeUtilisateurs = new ArrayList<>();
		Connection cnx =null;
		try  {
			cnx= ConnectionProvider.getConnection();
		
			stmt=cnx.prepareStatement(sqlSelectAll);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
			Utilisateur utilisateur = new Utilisateur( resultSet.getInt("no_utilisateur"),
											   		   resultSet.getString("pseudo"),
											   		   resultSet.getString("nom"),
													   resultSet.getString("prenom"),
													   resultSet.getString("email"),
													   resultSet.getString("telephone"),
													   resultSet.getString("rue"),
													   resultSet.getString("code_postal"),
													   resultSet.getString("ville"),
													   resultSet.getString("mot_de_passe"),	
													   resultSet.getInt("credit"),
													   resultSet.getInt("administrateur"));
													
			listeUtilisateurs.add(utilisateur);
			}}
			catch (SQLException e) {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.READ_DATA_ECHEC);
				throw businessException;
			} finally {
				try {
					if (resultSet != null){
						resultSet.close();
					}
					if (stmt != null){
						stmt.close();
					}
					if(cnx!=null){
						cnx.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		return listeUtilisateurs;



	}
	public void delete(Utilisateur utilisateurCritere) throws BusinessException{
		PreparedStatement stmt =null;
		Connection cnx = null;
	try {
		cnx= ConnectionProvider.getConnection();
		stmt=cnx.prepareStatement(sqlDelete);
		stmt.setInt(1, utilisateurCritere.getNoUtilisateur());
		stmt.executeUpdate();

	} catch (SQLException e) {
		BusinessException businessException = new BusinessException();
		businessException.ajouterErreur(CodesResultatDAL.DELETE_DATA_ECHEC);
		throw businessException;
	} finally {
		try {
			if (stmt != null){
				stmt.close();
			}
			if(cnx!=null){
				cnx.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	}

	public void update(Utilisateur data) throws BusinessException {
		PreparedStatement stmt =null;
		Connection cnx = null;
		try {
			cnx= ConnectionProvider.getConnection();
			stmt=cnx.prepareStatement(sqlUpdate);
			stmt.setString(1,data.getPseudo());
			stmt.setString(2, data.getNom());
			stmt.setString(3, data.getPrenom());
			stmt.setString(4,data.getEmail());
			stmt.setString(5,data.getTelephone());
			stmt.setString(6,data.getRue());
			stmt.setString(7,data.getCodePostal());
			stmt.setString(8,data.getVille());
			stmt.setString(9,data.getMotDePasse());
			stmt.setInt(10,data.getCredit());
			stmt.setInt(11, 0);
			stmt.setInt(12,data.getNoUtilisateur());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UDPATE_DATA_ECHEC);
			throw businessException;
		
		}
		finally {
			try {
				if (stmt != null){
					stmt.close();
				}
				if(cnx !=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	
	
	
	
	
	
	
	
	
}
