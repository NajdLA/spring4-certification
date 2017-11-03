package fr.adservio.banque;

import java.sql.SQLException;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.test.context.ActiveProfiles;

import fr.adservio.banque.entities.Confirmation;
import fr.adservio.banque.entities.MonetaryAmount;
import fr.adservio.banque.services.TransferService;
import junit.framework.Assert;

@SpringBootApplication
@EnableAutoConfiguration
public class MoneyTransferSystemApplication {

	private static final Logger LOGGER = Logger.getLogger(MoneyTransferSystemApplication.class.getName());

	private static final String MONETARYAMOUNT = "300.00";

	private static final String SENDER = "1";

	private static final String RECEIPT = "2";
	
	private static final String ACTIVENV = "prod";

	public static void main(String[] args) throws SQLException {

		// activate profiles
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, ACTIVENV);

		// create the application from the configuration
		ApplicationContext context = SpringApplication.run(MoneyTransferSystemApplication.class, args);

		// display active profiles in logger
		LOGGER.info(context.getEnvironment().getActiveProfiles().toString());

		// look up the application service interface
		TransferService service = context.getBean("transferService", TransferService.class);

		// use the application
		Confirmation receipt = service.transfer(new MonetaryAmount(MONETARYAMOUNT), SENDER, RECEIPT);

		// display new balance receipt
		LOGGER.info(receipt.getNewBalance());

	}
}
