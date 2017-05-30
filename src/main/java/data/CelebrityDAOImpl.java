package data;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.swing.colorchooser.ColorSelectionModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import com.mysql.jdbc.Statement;

public class CelebrityDAOImpl implements CelebDao {

	private static String url = "jdbc:mysql://localhost:3306/celebritydb";
	private String user = "user";
	private String pass = "user";

	private ArrayList<String> colors = new ArrayList<String>();

	@PostConstruct
	public void addColors() {
		colors.add("#8E24AA");
		colors.add("#00796B");
		colors.add("#0277BD");
		colors.add("#F9A825");
		colors.add("#E64A19");
		colors.add("#C2185B");
	}

	public CelebrityDAOImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver!!!");
		}
	}

	public List<Celebrity> getAllCelebrities() {
		List<Celebrity> celeb = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "select id, name, job_title, image, quote from celebrity";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String job_title = rs.getString(3);
				String image = rs.getString(4);
				String quote = rs.getString(5);
				celeb.add(new Celebrity(id, name, job_title, image, quote));
				System.out.println("List: " + celeb);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return celeb;
	}
	

	
	@Override
	public Celebrity randomCeleb(Celebrity oldCeleb) {
		Celebrity c = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "select id, name, job_title, image, quote from celebrity order by RAND() LIMIT 1";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				c = new Celebrity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		if(oldCeleb.getId()==c.getId()){
			c = randomCeleb(oldCeleb);
		}
		return c;
	}

	@Override
	public String randomColor() {
		Collections.shuffle(colors);
		return colors.get(0);
	}

	@Override
	public Celebrity getCelebrityByName(String name) {
		Celebrity c = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "select id, name, job_title, image, quote from celebrity where name = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				c = new Celebrity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return c;
	}

	@Override
	public Celebrity getCelebrityByJobTitle(String jobTitle) {
		Celebrity c = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "select id, name, job_title, image, quote from celebrity where job_title = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, jobTitle);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				c = new Celebrity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return c;

	}
	
	@Override
	public Celebrity getCelebrityById(int id) {
		Celebrity c = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "select id, name, job_title, image, quote from celebrity where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				c = new Celebrity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return c;
		
	}
	
	@Override
	public List<String> getJobTitles() {
		List<String> jobTitles = new ArrayList<String>();
		for (Celebrity c : getAllCelebrities()) {
			jobTitles.add(c.getJobTitle());
		}
		return jobTitles;
		
	}
	
	@Override
	public List<String> getCelebNames() {
		List<String> celebNames = new ArrayList<String>();
		for (Celebrity c : getAllCelebrities()) {
			celebNames.add(c.getName());
		}
		return celebNames;
		
	}

	@Override
	public Celebrity addCelebrity(Celebrity c)  {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "insert into celebrity (name, job_title, image, quote)  values(?,?,?,?);";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, c.getName());
			stmt.setString(2, c.getJobTitle());
			stmt.setString(3, c.getImage());
			stmt.setString(4, c.getQuote());
			stmt.executeUpdate();
			ResultSet keys = stmt.getGeneratedKeys();
			int newId=0;
			if (keys.next()) {
				newId = keys.getInt(1);
			}
			c.setId(newId);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public void removeCelebrity(Celebrity c) {
		int id = c.getId();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "delete from celebrity where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Celebrity getNextCelebrity(Celebrity celeb) {

		return null;
	}

	@Override
	public Celebrity getPreviousCelebrity(Celebrity celeb) {

		return null;
	}
	//name, job_title, image, quote
	@Override
	public Celebrity editCelebrity(Celebrity newCeleb) {
		try {
			System.out.println(newCeleb.getId());
//			System.out.println(newCeleb.getQuote());
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "UPDATE celebrity SET name  = ?, job_title = ?, "
					+ "image = ?, quote = ? WHERE name = ?;"; 
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, newCeleb.getName());
			stmt.setString(2, newCeleb.getJobTitle());
			stmt.setString(3, newCeleb.getImage());
			stmt.setString(4, newCeleb.getQuote());
			stmt.setString(5, newCeleb.getName());
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newCeleb;
	}

	@Override
	public void persistCelebrityList() {
		// TODO Auto-generated method stub

	}

}
