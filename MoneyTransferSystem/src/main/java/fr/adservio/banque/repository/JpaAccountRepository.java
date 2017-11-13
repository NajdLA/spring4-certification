package fr.adservio.banque.repository;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.stereotype.Component;
import fr.adservio.banque.beans.Account;
import fr.adservio.banque.beans.MonetaryAmount;
import fr.adservio.banque.beans.TaxCalculator;

@Component("jpaAccountRepository")
public class JpaAccountRepository implements AccountRepository {

	private DataSource basicDataSource;

	public JpaAccountRepository(DataSource db) {
		this.setBasicDataSource(db);
	}

	@Override
	public Account loadAccount(String account) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAccount(Account a) throws SQLException {
		// TODO Auto-generated method stub

	}

	public DataSource getBasicDataSource() {
		return basicDataSource;
	}

	public void setBasicDataSource(DataSource basicDataSource) {
		this.basicDataSource = basicDataSource;
	}

	@Override
	public Account VerifBalanceSender(String idAccount, MonetaryAmount monetaryAmount, TaxCalculator taxCalCulator,
			Double maxTransfersPerDay) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void populateCache() throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
