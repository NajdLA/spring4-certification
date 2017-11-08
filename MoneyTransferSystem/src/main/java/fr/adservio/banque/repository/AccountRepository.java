package fr.adservio.banque.repository;

import java.sql.SQLException;
import fr.adservio.banque.beans.Account;
import fr.adservio.banque.beans.MonetaryAmount;
import fr.adservio.banque.beans.TaxCalculator;

public interface AccountRepository {

	public Account loadAccount(String account) throws SQLException;

	public void updateAccount(Account idAccount) throws SQLException;

	public Account VerifBalanceSender(String idAccount, MonetaryAmount monetaryAmount, TaxCalculator taxCalCulator,
			Double maxTransfersPerDay) throws SQLException;

	public void clearCache();
	
	public void populateCache() throws SQLException;
}
