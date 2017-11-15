package spring.container.dependency.ioc.configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.container.dependency.ioc.services.DataBaseService;
import spring.container.dependency.ioc.services.LogService;
import spring.container.dependency.ioc.services.MailService;
@Configuration //let the spring framework knows this class is used as configuration
public class JavaConfig {
	
	//<bean id="logServiceRef" class="spring.container.dependency.ioc.XMLconfig.LogService" />
	@Bean
	public LogService logServiceRef(){
		LogService logService = new LogService();
		return logService;	
	}
	/*
	<bean id="mailService" class="spring.container.dependency.ioc.XMLconfig.MailService">
	<!-- Setter dependency injection -->
	<!-- mainly used for optional properties -->
	<property name="logService" ref="logServiceRef" /> <!-- bean reference -->
	</bean>
	*/
	@Bean
	public MailService mailService(){
		MailService mailService = new MailService();
		mailService.setLogService(logServiceRef());
		return mailService;
	}
	/*
	<bean id="mailServiceCons" class="spring.container.dependency.ioc.XMLconfig.MailService">
	<!-- constructor dependency injection -->
	<!-- mainly used for mandatory properties -->
	<constructor-arg ref="dataBaseService" /> <!-- bean reference -->
	</bean>
	*/
	@Bean
	public MailService mailServiceCons(){
		MailService mailService = new MailService(dataBaseService());
		return mailService;
	}
	//<bean id="dataBaseService" class="spring.container.dependency.ioc.XMLconfig.DataBaseService" />
	@Bean
	public DataBaseService dataBaseService(){
		DataBaseService dataBaseService = new DataBaseService();
		return dataBaseService;
	}
}
