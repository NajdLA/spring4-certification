package fr.adservio.banque.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import fr.adservio.banque.MoneyTransferSystemApplication;
import fr.adservio.banque.entities.Account;

public class JdbcAccountRepository implements AccountRepository {

	private DataSource basicDataSource;
	private static final Logger LOGGER = Logger.getLogger(MoneyTransferSystemApplication.class.getName());
	
	
	public JdbcAccountRepository() {
		// TODO Auto-generated constructor stub
	}
	
	public JdbcAccountRepository(DataSource db) {
		
		this.setBasicDataSource(db);
	}
	
	@Override
	public Account loadAccount (String account) throws SQLException {
		Connection conn = null;
        PreparedStatement stmt = null;
        Account clientAccount = null;
        try {
            //
            // Get a connection from the data source and do some
            // database query with the obtained connection.
            //
            conn = basicDataSource.getConnection();
            String req = "SELECT * FROM account WHERE id = ?";
            stmt = conn.prepareStatement(req);
            stmt.setString(1, account);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	LOGGER.info("la balance du compte "+account+" est : " + rs.getString("balance"));
                clientAccount= new Account(account, rs.getString("balance").replaceAll("\\s",""));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
                return clientAccount;
            }
        }
		return clientAccount;
		
	}

	@Override
	public void updateAccount(Account a) throws SQLException{
		Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = basicDataSource.getConnection();
            String req = "UPDATE account SET balance = ? WHERE id = ?";
            stmt = conn.prepareStatement(req);
            stmt.setString(1, a.getBalance().replaceAll("\\s",""));
            stmt.setString(2, a.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
		
	}

	public DataSource getBasicDataSource() {
		return basicDataSource;
	}

	public void setBasicDataSource(DataSource basicDataSource) {
		this.basicDataSource = basicDataSource;
	}

}
