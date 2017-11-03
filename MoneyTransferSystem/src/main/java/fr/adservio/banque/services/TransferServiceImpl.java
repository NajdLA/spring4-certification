package fr.adservio.banque.services;

import java.sql.SQLException;

import org.springframework.stereotype.Component;

import fr.adservio.banque.entities.Account;
import fr.adservio.banque.entities.Confirmation;
import fr.adservio.banque.entities.MonetaryAmount;
import fr.adservio.banque.repository.AccountRepository;

@Component
public class TransferServiceImpl implements TransferService {

	private AccountRepository accountRepository;
	
	public TransferServiceImpl(AccountRepository ar) {
		this.setAccountRepository(ar);
	}

	public TransferServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Confirmation transfer(MonetaryAmount ma, String senderID, String receiverID) throws SQLException {
		Account a1 = accountRepository.loadAccount(senderID);
		Account a2 = accountRepository.loadAccount(receiverID);
		if (a1.debit(ma.getAmount()) == true) {
			a2.credit(ma.getAmount());
			 accountRepository.updateAccount(a1);
			 accountRepository.updateAccount(a2);
		}
		Confirmation conf = new Confirmation(a2.getBalance());
		return conf;
	}

	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
}
