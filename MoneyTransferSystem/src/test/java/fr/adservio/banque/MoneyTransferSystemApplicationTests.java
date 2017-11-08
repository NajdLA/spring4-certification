package fr.adservio.banque;

import java.sql.SQLException;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import fr.adservio.banque.beans.Confirmation;
import fr.adservio.banque.beans.MonetaryAmount;
import fr.adservio.banque.configuration.ApplicationConfig;
import fr.adservio.banque.services.TransferService;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
//@ActiveProfiles(profiles = "dev")
public class MoneyTransferSystemApplicationTests {

	@Autowired
	@Qualifier("transferServiceImpl")
	private TransferService service;

	@Autowired
	private Environment env;
	
	private static final Logger LOGGER = Logger.getLogger(MoneyTransferSystemApplication.class.getName());
	

	private static final String MONETARYAMOUNT = "300.00";

	private static final String SENDER = "1";

	private static final String RECEIPT = "2";
	
	private static final String ACTIVENV = "dev";
	
	@Before
	public void setUp() {

		// activate profiles
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, ACTIVENV);

		// create the application from the configuration
		ApplicationContext context = SpringApplication.run(ApplicationConfig.class);

		// look up the application service interface
		service = context.getBean("transferService", TransferService.class);

	}

	@Test
	public void moneyTransfer() throws SQLException {
		
		//return active profiles
		LOGGER.info(env.getActiveProfiles().toString());

		// use the application
		Confirmation receipt = service.transfer(new MonetaryAmount(MONETARYAMOUNT), SENDER, RECEIPT);

		// test receipt new balance value
		Assert.assertEquals(receipt.getNewBalance(), "5800.0");
	}

}
