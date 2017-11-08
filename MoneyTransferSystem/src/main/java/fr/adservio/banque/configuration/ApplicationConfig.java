package fr.adservio.banque.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import fr.adservio.banque.repository.AccountRepository;
import fr.adservio.banque.services.TransferService;
import fr.adservio.banque.services.TransferServiceImpl;

@Configuration
@Import(CurrentAccountRepo.class)
public class ApplicationConfig {

	@Autowired
	@Qualifier("accountRepositoryForJdbc")
	private AccountRepository accountRepository;

	@Bean
	public TransferService transferService() {
		return new TransferServiceImpl(accountRepository);
	}

}
