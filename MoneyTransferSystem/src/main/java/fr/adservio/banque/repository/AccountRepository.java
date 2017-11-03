package fr.adservio.banque.repository;

import java.sql.SQLException;

import fr.adservio.banque.entities.Account;

public interface AccountRepository {

	public Account loadAccount(String account) throws SQLException;

	public void updateAccount(Account a) throws SQLException;
}
