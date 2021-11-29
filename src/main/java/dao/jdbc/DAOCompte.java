package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import model.*;


public class DAOCompte implements IDAO<Compte,Integer> {
@Override
	public Compte findById(Integer id) {
		
		return null;
	}

	@Override
	public List<Compte> findAll() {
	
		return null;
	}

	@Override
	public void insert(Compte objet) {
		
		
	}

	@Override
	public void update(Compte objet) {
		
		
	}

	@Override
	public void delete(Integer id) {
		
		
	}
	
	
	public Compte connect(String login,String password) 
	{
		Compte c=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/parc","root","");
			
			PreparedStatement ps = conn.prepareStatement("SELECT * from compte where login=? and password=?");
			ps.setString(1, login);
			ps.setString(2, password);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) 
			{
				if(rs.getString("type_compte").equals("Gerant")) 
				{
					c = new Gerant(rs.getInt("id"), rs.getString("login"),rs.getString("password"));
				}
				else if(rs.getString("type_compte").equals("Caissier"))
				{
					c = new Caissier(rs.getInt("id"),rs.getString("login"),rs.getString("password"));
				}
				else
				{
					c = new Operateur(rs.getInt("id"),rs.getString("login"),rs.getString("password"));
				}
			}
			
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}


}

