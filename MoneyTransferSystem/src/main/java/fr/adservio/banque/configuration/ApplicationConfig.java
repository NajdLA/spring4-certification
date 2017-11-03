package fr.adservio.banque.configuration;


import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import fr.adservio.banque.repository.AccountRepository;
import fr.adservio.banque.repository.JdbcAccountRepository;
import fr.adservio.banque.services.TransferService;
import fr.adservio.banque.services.TransferServiceImpl;

@Configuration
@Import({ProdConfig.class,DevConfig.class})
public class ApplicationConfig {
	
@Autowired
private DataSource dataSource;

@Bean
public TransferService transferService(){
	return new TransferServiceImpl(accountRepository());
}	
@Bean
public AccountRepository accountRepository(){
	return new JdbcAccountRepository(dataSource);
}


}
