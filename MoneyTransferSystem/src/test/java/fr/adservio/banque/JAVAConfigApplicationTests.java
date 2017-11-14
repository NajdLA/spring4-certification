package fr.adservio.banque;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import spring.container.dependency.ioc.configuration.JavaConfig;
import spring.container.dependency.ioc.services.DataBaseService;
import spring.container.dependency.ioc.services.LogService;
import spring.container.dependency.ioc.services.MailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=JavaConfig.class, loader=AnnotationConfigContextLoader.class)
public class JAVAConfigApplicationTests  {
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private DataBaseService dataBaseService;

	@Test
	public void testServices() {
		Assert.assertNotNull(mailService);
	}

}
