package persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Fatty_acid;
import model.Lipid;
import model.Ske_type;

public class SQLManager {

	private Connection c;

	public void connect(String archivo) {
		try {
			// Open database connection
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();// chooses the database type we are using
			FileReader f = new FileReader(archivo);
			BufferedReader b = new BufferedReader(f);
			String user = b.readLine();
			String password = b.readLine();
			b.close();
			this.c = DriverManager.getConnection("jdbc:mysql://localhost/lipids?" + "user=" + user + "&password="
					+ password + "&serverTimezone=UTC");

			System.out.println("Database connection opened.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int insertLipid(Lipid l) {
		int id = 0;
		try {

			String sql = "INSERT INTO compounds (compound_name,formula,mass,charge_type,charge_number,formula_type,formula_type_int,compound_type,compound_status) "
					+ "VALUES (?,?,?,?,?,?,?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			prep.setString(1, l.getName());
			prep.setString(2, l.getFormula().toString());
			prep.setDouble(3, l.getMass());
			if (l.getSkeleton() == null) {
				prep.setInt(4, 0);
				prep.setInt(5, 0);
			} else if (l.getSkeleton().getSke_type().equals(Ske_type.PC)
					|| l.getSkeleton().getSke_type().equals(Ske_type.SM)) {
				prep.setInt(4, 1);
				prep.setInt(5, 1);
			} else {
				prep.setInt(4, 0);
				prep.setInt(5, 0);
			}
			prep.setString(6, "CHNOPS");
			prep.setInt(7, 0);
			prep.setInt(8, 1);
			prep.setInt(9, 0);
			prep.executeUpdate();
			ResultSet rs = prep.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public void insertLipid_clas(Lipid l, int compounds_id) {
		try {

			String sql = "INSERT INTO compounds_lipids_classification (compound_id,lipid_type,num_chains,number_carbons,double_bonds) "
					+ "VALUES (?,?,?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, compounds_id);
			if (l.getSkeleton() == null) {
				prep.setString(2, "FA(");
			} else {
				prep.setString(2, l.getSkeleton().getSke_type().toString() + "(");
			}
			prep.setInt(3, l.getFAs().size());
			prep.setInt(4, l.getLength());
			prep.setInt(5, l.getDoubleBonds());

			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertFA(Fatty_acid fa) {
		try {

			String sql = "INSERT INTO chains (num_carbons,double_bonds,mass,formula) " + "VALUES (?,?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, fa.getC());
			prep.setInt(2, fa.getDouble_bonds());
			prep.setDouble(3, fa.getMass());
			prep.setString(4, fa.getFormula().toString());

			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getFAid(Fatty_acid f) {
		try {
			String s = "SELECT chain_id FROM chains WHERE formula=?";
			PreparedStatement p = c.prepareStatement(s);
			p.setString(1, f.getFormula().toString());
			ResultSet rs = p.executeQuery();
			int chain_id = 0;
			while (rs.next()) {
				chain_id = rs.getInt("chain_id");
			}
			rs.close();
			p.close();
			return chain_id;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void connectChainsLipid(int number_chains, int compound_id, int chain_id) {
		try {

			String sql = "INSERT INTO compound_chain (number_chains,compound_id,chain_id) " + "VALUES (?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, number_chains);
			prep.setInt(2, compound_id);
			prep.setInt(3, chain_id);

			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * NOT USED, NOT EFFICIENT public boolean checkLipidFAConnection(int
	 * compound_id, int chain_id) { boolean exists = false; try { String s =
	 * "SELECT * FROM compound_chain WHERE compound_id=? AND chain_id=?";
	 * PreparedStatement p = c.prepareStatement(s); p.setInt(1, compound_id);
	 * p.setInt(2, chain_id); ResultSet rs = p.executeQuery(); while (rs.next()) {
	 * exists = true; } } catch (SQLException e) { e.printStackTrace(); } return
	 * exists; }
	 */

	/*
	 * NOT USED, NOT EFFICIENT public int getNumber_chains(int compound_id, int
	 * chain_id) { Integer number_chains = null; try { String s =
	 * "SELECT number_chains FROM compound_chain WHERE compound_id=? AND chain_id=?"
	 * ; PreparedStatement p = c.prepareStatement(s); p.setInt(1, compound_id);
	 * p.setInt(2, chain_id); ResultSet rs = p.executeQuery(); while (rs.next()) {
	 * number_chains = rs.getInt("number_chains"); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } return number_chains; }
	 */

	/*
	 * UNUSED public void updateNumber_chains(int compound_id, int chain_id, int
	 * number_chains) { try { PreparedStatement p = c
	 * .prepareStatement("UPDATE compound_chain SET number_chains=?  WHERE  compound_id=? AND chain_id=?"
	 * ); p.setInt(1, number_chains); p.setInt(2, compound_id); p.setInt(3,
	 * chain_id); p.executeUpdate();
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } }
	 */

}
