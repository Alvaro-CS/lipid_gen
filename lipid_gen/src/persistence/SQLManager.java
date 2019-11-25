package persistence;

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

	public void connect() {
		try {
			// Open database connection
			Class.forName("com.mysql.jdbc.Driver").newInstance();// chooses the database type we are using
			this.c = DriverManager.getConnection("jdbc:mysql:./db/lipids.db", "alvaro", "alvaro");// this connects to
																									// the
																									// database
			c.createStatement().execute("PRAGMA foreign_keys=ON");
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

	public void create() {
		try {
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE compounds (" + "compound_id INT NOT NULL AUTOINCREMENT PRIMARY KEY  ,"
					+ "  cas_id varchar(100) UNIQUE DEFAULT NULL," + "  compound_name text not null,"
					+ "  formula varchar(100) DEFAULT ''," + "  mass double DEFAULT 0," + "  charge_type int default 0,"
					+ "  charge_number int default 0," + "  formula_type varchar(20) DEFAULT NULL,"
					+ "  formula_type_int int," + "  compound_type int default 0," + "  compound_status int default 0,"
					+ "  logP double default null," + "  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
					+ "  last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
					+ "  INDEX compounds_cas_id_index (cas_id)," + "  INDEX compounds_formula_index (formula),"
					+ "  INDEX compounds_mass_index (mass)," + "  INDEX compounds_charge_type_index (charge_type),"
					+ "  INDEX compounds_formula_type_index (formula_type),"
					+ "  INDEX compounds_formula_type_int_index (formula_type_int),"
					+ "  INDEX compounds_compound_type_index (compound_type),"
					+ "  INDEX compounds_compound_status_index (compound_status),"
					+ "  INDEX compounds_logP_index (logP)" + ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
			stmt1.executeUpdate(sql1);
			stmt1.close();

			Statement stmt2 = c.createStatement();
			String sql2 = "CREATE TABLE chains (" + "chain_id INT NOT NULL AUTOINCREMENT PRIMARY KEY ,"
					+ "  num_carbons int NOT NULL," + "  double_bonds int NOT NULL,"
					+ "  oxidation VARCHAR(10) default ''," + "  mass double," + "  formula VARCHAR(100),"
					+ "  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
					+ "  last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
					+ "  CONSTRAINT UC_chains_carbons_bonds_oxid UNIQUE (num_carbons,double_bonds,oxidation),"
					+ "  INDEX chains_num_carbons_index (num_carbons),"
					+ "  INDEX chains_double_bonds_index (double_bonds)," + "  INDEX chains_oxidation_index (oxidation)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
			stmt2.executeUpdate(sql2);
			stmt2.close();

			Statement stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE compound_chain (" + "compound_id INT NOT NULL," + "  chain_id int NOT NULL,"
					+ "  number_chains int NOT NULL default 1," + "  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
					+ "  last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
					+ "  FOREIGN KEY (compound_id) REFERENCES compounds(compound_id) on DELETE CASCADE,"
					+ "  FOREIGN KEY (chain_id) REFERENCES chains(chain_id) on DELETE CASCADE,"
					+ "  CONSTRAINT pk_compound_chain PRIMARY KEY (compound_id, chain_id)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
			stmt3.executeUpdate(sql3);
			stmt3.close();
			Statement stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE compounds_lipids_classification (" + "compound_id int NOT NULL,"
					+ "lipid_type varchar(150) DEFAULT ''," + "num_chains int default 0," + "number_carbons int,"
					+ "double_bonds int," + "created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
					+ "last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
					+ "FOREIGN KEY (compound_id) REFERENCES compounds(compound_id) on DELETE CASCADE,"
					+ "CONSTRAINT pk_lipids_classifcation PRIMARY KEY (compound_id),"
					+ "INDEX lipids_classification_lipid_type_index (lipid_type),"
					+ "INDEX lipids_classification_num_chains_index (num_chains),"
					+ "INDEX lipids_classification_number_carbons_index (number_carbons),"
					+ "INDEX lipids_classification_double_bonds_index (double_bonds)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
			stmt4.executeUpdate(sql4);
			stmt4.close();

		} catch (SQLException e) {
			if (e.getMessage().contains("already exists")) {
				System.out.println("Tables are already created.");
			} else {
				e.printStackTrace();
			}
		}
	}

	public int insertLipid(Lipid l) {
		int id = 0;
		try {

			String sql = "INSERT INTO compounds (compound_name,formula,mass,charge_type,charge_number,formula_type,formula_type_int,compound_type,compound_status) "
					+ "VALUES (?,?,?,?,?,?,?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, l.getName());
			prep.setString(2, l.getFormula().toString());
			prep.setDouble(3, l.getMass());
			if (l.getSkeleton().getSke_type().equals(Ske_type.PC)
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

	public void insertLipid_clas(Lipid l) {
		try {

			String sql = "INSERT INTO compounds_lipids_classification (lipid_type,num_chains,number_carbons,double_bonds) "
					+ "VALUES (?,?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, l.getSkeleton().toString() + "(");
			prep.setInt(2, l.getFAs().size());
			prep.setInt(3, l.getLength());
			prep.setInt(4, l.getDoubleBonds());

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

	public boolean checkLipidFAConnection(int compound_id, int chain_id) {
		boolean exists = false;
		try {
			String s = "SELECT * FROM compound_chain WHERE compound_id=? AND chain_id=?";
			PreparedStatement p = c.prepareStatement(s);
			p.setInt(1, compound_id);
			p.setInt(2, chain_id);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				exists = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}

	public int getNumber_chains(int compound_id, int chain_id) {
		Integer number_chains = null;
		try {
			String s = "SELECT number_chains FROM compound_chain WHERE compound_id=? AND chain_id=?";
			PreparedStatement p = c.prepareStatement(s);
			p.setInt(1, compound_id);
			p.setInt(2, chain_id);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				number_chains = rs.getInt("number_chains");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return number_chains;
	}

	public void updateNumber_chains(int compound_id, int chain_id, int number_chains) {
		try {
			PreparedStatement p = c
					.prepareStatement("UPDATE compound_chain SET number_chains=?  WHERE  compound_id=? AND chain_id=?");
			p.setInt(1, number_chains);
			p.setInt(2, compound_id);
			p.setInt(3, chain_id);
			p.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
