package fr.adservio.banque.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import fr.adservio.banque.beans.Account;
import fr.adservio.banque.beans.Confirmation;
import fr.adservio.banque.beans.MonetaryAmount;
import fr.adservio.banque.beans.TaxCalculator;
import fr.adservio.banque.configuration.DevConfig;
import fr.adservio.banque.configuration.ProdConfig;
import fr.adservio.banque.repository.AccountRepository;

@Component
@PropertySource(value = { "classpath:application.properties" })
public class TransferServiceImpl implements TransferService {

	private AccountRepository accountRepository;

	private TaxCalculator taxCalculator;

	@Value("${daily.limit}")
	private Double maxTransfersPerDay;

	@Autowired
	public TransferServiceImpl(@Qualifier("jdbcAccountRepository") AccountRepository repo) {
		this.accountRepository = repo;
	}

	@Autowired
	public void setTaxCalculator(@Qualifier("taxCalculator") TaxCalculator taxCalculator) {
		this.taxCalculator = taxCalculator;
	}

	public void setMaxTransfersPerDay(Double maxTransfersPerDay) {
		this.maxTransfersPerDay = maxTransfersPerDay;
	}

	public TransferServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Confirmation transfer(MonetaryAmount ma, String senderID, String receiverID) throws SQLException {
		Confirmation conf = null;
		Account a1 = accountRepository.VerifBalanceSender(senderID, ma, taxCalculator, maxTransfersPerDay);
		if (a1 != null) {
			Account a2 = accountRepository.loadAccount(receiverID);
			if (a1.debit(ma.getAmount()) == true) {
				a2.credit(ma.getAmount());
				accountRepository.updateAccount(a1);
				accountRepository.updateAccount(a2);
				conf = new Confirmation(a2.getBalance());
			}

		}
		return conf;
	}

	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public Double getMaxTransfersPerDay() {
		return maxTransfersPerDay;
	}

	public TaxCalculator getTaxCalculator() {
		return taxCalculator;
	}

}
