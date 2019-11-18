package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import model.Fatty_acid;
import model.Lipid;
import model.Skeleton;

public class SQLManager {

	private Connection c;

	public void connect() {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");// chooses the database type we are using
			this.c = DriverManager.getConnection("jdbc:sqlite:./db/lipid_gen.db");// this connects to the database
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
			String sql1 = "CREATE TABLE compounds (" + "  compound_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
					+ "  cas_id varchar(100) UNIQUE DEFAULT NULL," + "  compound_name text not null,"//
					+ "  formula varchar(100) DEFAULT '',"//
					+ "  mass double DEFAULT 0,"//
					+ "  charge_type int default 0, -- charge 0 for neutral, 1 for positive 2 for negative"
					+ "  charge_number int default 0, -- number of charges (negative or positive)"
					+ "  formula_type varchar(20) DEFAULT NULL, -- CHNOPS, CHNOPSD, CHNOPSCL, CHNOPSCLD, ALLD or ALL"
					+ "  formula_type_int int, -- 0 CHNOPS, 1 CHNOPSD, 2 CHNOPSCL, 3 CHNOPSCLD, 4 ALL, 5 ALLD"
					+ "  compound_type int default 0, -- type of compound: 0 for metabolite, 1 for lipids, 2 for peptide"
					+ "  compound_status int default 0, -- status of compound: 0 expected, 1 detected, 2 quantified, 3 predicted (HMDB)"
					+ "  logP double default null, -- LogP of the compound. By default 0"
					+ "  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
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
			String sql2 = "CREATE TABLE chains(" + "  chain_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,"
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
			String sql3 = "CREATE TABLE compound_chain (" + "  compound_id INT NOT NULL," + "  chain_id int NOT NULL,"
					+ "  number_chains int NOT NULL default 1," + "  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
					+ "  last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
					+ "  FOREIGN KEY (compound_id) REFERENCES compounds(compound_id) on DELETE CASCADE,"
					+ "  FOREIGN KEY (chain_id) REFERENCES chains(chain_id) on DELETE CASCADE,"
					+ "  CONSTRAINT pk_compound_chain PRIMARY KEY (compound_id, chain_id)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
			stmt3.executeUpdate(sql3);
			stmt3.close();

		} catch (SQLException e) {
			if (e.getMessage().contains("already exists")) {
				System.out.println("Tables are already created.");
			} else {
				e.printStackTrace();
			}
		}
	}

	public void insertSkeleton(Skeleton s) {
		try {

			String sql = "INSERT INTO compounds (compound_name,formula,mass) " + "VALUES (?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, s.getSke_type().toString());
			prep.setString(2, s.getFormula().toString());
			prep.setDouble(3, s.getMass());

			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertFA(Fatty_acid fa) {
		try {

			String sql = "INSERT INTO chains (num_carbons,double_bonds,mass) " + "VALUES (?,?,?,?);";
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

	public void insertLipid(Lipid l) {
		try {

			String sql = "INSERT INTO compound_chain (number_chains) " + "VALUES (?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, l.getFAs().size());
//TODO unir tablas??
			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
