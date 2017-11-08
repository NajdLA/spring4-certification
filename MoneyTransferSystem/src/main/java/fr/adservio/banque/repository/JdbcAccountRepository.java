package fr.adservio.banque.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import fr.adservio.banque.MoneyTransferSystemApplication;
import fr.adservio.banque.beans.Account;
import fr.adservio.banque.beans.MonetaryAmount;
import fr.adservio.banque.beans.TaxCalculator;

@Component("jdbcAccountRepository")
public class JdbcAccountRepository implements AccountRepository {

	private static final Logger LOGGER = Logger.getLogger(MoneyTransferSystemApplication.class.getName());
	private DataSource basicDataSource;
	private Connection conn;

	public JdbcAccountRepository(DataSource db) {

		this.setBasicDataSource(db);
	}

	@PostConstruct
	@Override
	public void populateCache() throws SQLException {
		if (conn == null) {
			conn = basicDataSource.getConnection();
			System.out.println(conn);
		}

	}

	@PreDestroy
	@Override
	public void clearCache() {
		ConfigurableApplicationContext context = (ConfigurableApplicationContext) SpringApplication
				.run(JpaAccountRepository.class);
		context.close();
		System.out.println("kill beans");
	}

	@Override
	public Account loadAccount(String idAccount) throws SQLException {
		PreparedStatement stmt = null;
		Account clientAccount = null;
		// getConnection();
		String req = "SELECT * FROM account WHERE id = ?";
		stmt = conn.prepareStatement(req);
		stmt.setString(1, idAccount);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			clientAccount = new Account(idAccount, rs.getString("balance").replaceAll("\\s", ""));
			LOGGER.info(clientAccount.toString());
		}
		return clientAccount;
	}

	@Override
	public void updateAccount(Account a) throws SQLException {
		PreparedStatement stmt = null;
		// getConnection();
		String req = "UPDATE account SET balance = ? WHERE id = ?";
		stmt = conn.prepareStatement(req);
		stmt.setString(1, a.getBalance().replaceAll("\\s", ""));
		stmt.setString(2, a.getId());
		stmt.executeUpdate();
	}

	@Override
	public Account VerifBalanceSender(String idAccount, MonetaryAmount monetaryAmount, TaxCalculator taxCalCulator,
			Double maxTransfersPerDay) throws SQLException {
		PreparedStatement stmt = null;
		Account clientAccount = null;
		Double balanceInt = null;
		String balanceString = "";
		Double moneyImpot = null;
		Double moneyToSend = null;

		String req = "SELECT * FROM account WHERE id = ?";
		stmt = conn.prepareStatement(req);
		stmt.setString(1, idAccount);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			balanceString = rs.getString("balance").replaceAll("\\s", "");
		}
		balanceInt = convertFromStringToInt(balanceString);
		Double MoneyToSendInt = convertFromStringToInt(monetaryAmount.getAmount());
		moneyImpot = MoneyToSendInt * taxCalCulator.getImpotByRegion();
		moneyToSend = balanceInt - (MoneyToSendInt + moneyImpot);
		if (moneyToSend > 0.0 && moneyToSend < maxTransfersPerDay) {
			clientAccount = new Account(idAccount, balanceString, moneyImpot);
			LOGGER.info(clientAccount.toString());
			return clientAccount;
		} else {
			LOGGER.warning("insufficient balance");
			return clientAccount;
		}
	}

	public DataSource getBasicDataSource() {
		return basicDataSource;
	}

	public void setBasicDataSource(DataSource basicDataSource) {
		this.basicDataSource = basicDataSource;
	}

	public Double convertFromStringToInt(String value) {
		Double intValue = Double.parseDouble(value);
		return intValue;
	}

}
