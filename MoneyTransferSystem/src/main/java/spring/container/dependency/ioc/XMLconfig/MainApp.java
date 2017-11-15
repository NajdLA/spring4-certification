package spring.container.dependency.ioc.XMLconfig;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.container.dependency.ioc.beans.Customer;
import spring.container.dependency.ioc.beans.Developper;
import spring.container.dependency.ioc.beans.Person;
import spring.container.dependency.ioc.services.LogService;
import spring.container.dependency.ioc.services.MailService;

public class MainApp {

	private static final Logger LOGGER = Logger.getLogger(MainApp.class.getName());

	public static void main(String[] args) {

		// creating the application context
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		MailService mailService = context.getBean(MailService.class);

		LOGGER.info("Default locale system " + mailService.getDefaultLocale());

		// get reference of bean
		LogService logServiceRef = mailService.getLogService();

		LOGGER.info("Log service reference " + logServiceRef);

		LOGGER.info("Get property from inner class " + mailService.getMailServiceConfig().getMaxHourlyEmailLimit());

		// bean inheritance example

		// parent class
		Person person = context.getBean("person", Person.class);
		LOGGER.info("get username" + person.getUsername());

		// child class
		Customer customer = context.getBean("customer", Customer.class);

		LOGGER.info("get username from parent class " + customer.getUsername());
		LOGGER.info("get taxe using SPEL " + customer.getTaxe());
		LOGGER.info("check if customer is active or not " + customer.isActive());
		LOGGER.info("get address " + customer.getAddress());

		Developper developper = context.getBean("developper", Developper.class);
		LOGGER.info("company skills " + developper.getSkills());
		// get property from abstact bean
		LOGGER.info("company Name " + developper.getCompanyName());
		LOGGER.info("company location " + developper.getCompanylocation());

		// close application and release all sources
		((ConfigurableApplicationContext) context).close();

	}


}
