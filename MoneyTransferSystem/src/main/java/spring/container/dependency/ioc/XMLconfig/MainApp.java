package spring.container.dependency.ioc.XMLconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		
		// creating the application context
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		MailService mailService = context.getBean(MailService.class);
		
		System.out.println(mailService.getMailServer());
		//get reference of bean
		LogService logServiceRef = mailService.getLogService();
		System.out.println("Log service reference "+logServiceRef);
	}

}
