package spring.container.dependency.ioc.JavaConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import spring.container.dependency.ioc.XMLconfig.MailService;


@ComponentScan("spring.container.dependency.ioc")
public class MainApp {

	public static void main(String[] args) {
		// creating the application context
		ApplicationContext context = SpringApplication.run(MainApp.class, args);
		
		//look up the application service interface
		//use typed method to avoid cast
		MailService mailService =context.getBean("mailService",MailService.class);
		
		//use the application 
		mailService.hashCode();
		

	}

}
