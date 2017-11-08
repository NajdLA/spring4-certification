package fr.adservio.banque.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import fr.adservio.banque.repository.AccountRepository;
import fr.adservio.banque.repository.JdbcAccountRepository;
import fr.adservio.banque.repository.JpaAccountRepository;

@Configuration
@Import({ ProdConfig.class, DevConfig.class })
public class CurrentAccountRepo {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public AccountRepository accountRepositoryForJpa() {
		return new JpaAccountRepository(dataSource);
	}
	
	@Bean
	public AccountRepository accountRepositoryForJdbc() {
		return new JdbcAccountRepository(dataSource);
	}
}
