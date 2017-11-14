package fr.adservio.banque;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import org.junit.Assert;
import org.junit.Test;

import spring.container.dependency.ioc.services.MailService;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class XMLConfigApplicationTests extends AbstractJUnit4SpringContextTests {

	@Test
	public void TestMailService() {
		MailService mailService = applicationContext.getBean(MailService.class);
		Assert.assertNotNull(mailService);
	}

}
