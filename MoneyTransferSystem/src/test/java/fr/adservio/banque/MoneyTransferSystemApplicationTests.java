package fr.adservio.banque;

import java.sql.SQLException;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import fr.adservio.banque.beans.Confirmation;
import fr.adservio.banque.beans.MonetaryAmount;
import fr.adservio.banque.configuration.ApplicationConfig;
import fr.adservio.banque.configuration.TaxConfig;
import fr.adservio.banque.services.TransferService;
import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@ActiveProfiles(profiles = "prod")
public class MoneyTransferSystemApplicationTests {

	@Autowired
	@Qualifier("transferServiceImpl")
	private TransferService service;

	@Autowired
	private Environment env;

	@Autowired
	ApplicationContext context;

	private static final Logger LOGGER = Logger.getLogger(MoneyTransferSystemApplication.class.getName());

	private static final String MONETARYAMOUNT = "300.00";

	private static final String SENDERID = "1";

	private static final String RECEIPTID = "2";

	@Before
	public void setUp() {

		// look up the application service interface
		context.getBean("transferService", TransferService.class);

	}

	@Test
	public void moneyTransfer() throws SQLException {

		// return active profiles
		LOGGER.info(env.getActiveProfiles().toString());

		// use the application
		Confirmation receipt = service.transfer(new MonetaryAmount(MONETARYAMOUNT), SENDERID, RECEIPTID);

		// test receipt new balance value
		Assert.assertEquals(receipt.getNewBalance(), "5800.0");
	}


}
