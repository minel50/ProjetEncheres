package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;

public class CategorieDAOJdbcImpl implements CategorieDAO {
	
	//Instructions SQL
	private static final String sqlInsert = "INSERT INTO categories(libelle) VALUES(?);";
	private static final String sqlSelectById = "SELECT no_categorie, libelle FROM categories WHERE no_categorie = ?;";
	private static final String sqlSelectAll = "SELECT no_categorie, libelle FROM categories = ?;";
	private static final String sqlUpdate = "UPDATE categories SET libelle=? WHERE no_categorie = ?;";
	private static final String sqlDelete = "DELETE FROM categories WHERE no_categorie = ?;";
	
	@Override
	public void insert(Categorie categorie) {
		
		// Ajt erreur exception si (categorie == null)
		
		try(Connection con = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = con.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, categorie.getLibelle());
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				categorie.setNoCategorie(rs.getInt(1));
			}
		} catch(Exception e) 
		{
			// Ajt erreur exception si echec
		}
	}
	
	
	@Override
	public List<Categorie> selectAll() {
		
		Statement stmt = null;
		ResultSet rs = null;
		List<Categorie> liste = new ArrayList<Categorie>();
		
		try(Connection con = ConnectionProvider.getConnection())
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlSelectAll);
			//
			
			while(rs.next()) {
				Categorie cat = new Categorie(rs.getInt("no_categorie"),
												rs.getString("libelle"));
				liste.add(cat);
			}
		} catch(Exception e) 
		{
			// Ajt erreur exception si echec
		} finally 
		{
			try {
				if(rs != null) {
					rs.close();
				}
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return liste;
	}
}
