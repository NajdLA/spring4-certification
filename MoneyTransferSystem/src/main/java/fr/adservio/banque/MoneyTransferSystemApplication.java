package fr.adservio.banque;

import java.sql.SQLException;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.test.context.ActiveProfiles;

import fr.adservio.banque.beans.Confirmation;
import fr.adservio.banque.beans.MonetaryAmount;
import fr.adservio.banque.beans.TaxCalculator;
import fr.adservio.banque.repository.JpaAccountRepository;
import fr.adservio.banque.services.TransferService;
import org.junit.Assert;

@SpringBootApplication
@EnableAutoConfiguration
public class MoneyTransferSystemApplication {

	private static final Logger LOGGER = Logger.getLogger(MoneyTransferSystemApplication.class.getName());

	private static final String MONETARYAMOUNT = "300.00";

	private static final String SENDERID = "1";

	private static final String RECEIPTID = "2";

	private static final String ACTIVENV = "dev";

	public static void main(String[] args) throws SQLException {

		// activate profiles
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, ACTIVENV);

		// create the application from the configuration
		ConfigurableApplicationContext context = (ConfigurableApplicationContext) SpringApplication
				.run(MoneyTransferSystemApplication.class, args);

		// display active profiles in logger
		LOGGER.info(context.getEnvironment().getActiveProfiles().toString());

		// look up the application service interface
		TransferService service = context.getBean("transferService", TransferService.class);
		
		Assert.assertNotNull(service);
		
		// use the application
		Confirmation receipt = service.transfer(new MonetaryAmount(MONETARYAMOUNT), SENDERID, RECEIPTID);

		// display new balance receipt
		LOGGER.info(receipt != null ? receipt.getNewBalance() : "insufficient balance");

	}
}
