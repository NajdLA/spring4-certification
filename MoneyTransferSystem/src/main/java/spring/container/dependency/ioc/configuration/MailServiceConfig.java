package spring.container.dependency.ioc.configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class MailServiceConfig {

	private String maxHourlyEmailLimit;

	public String getMaxHourlyEmailLimit() {
		return maxHourlyEmailLimit;
	}

	public void setMaxHourlyEmailLimit(String maxHourlyEmailLimit) {
		this.maxHourlyEmailLimit = maxHourlyEmailLimit;
	}
	

}
