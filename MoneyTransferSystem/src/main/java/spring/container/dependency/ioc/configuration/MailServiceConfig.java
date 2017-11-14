package spring.container.dependency.ioc.configuration;

public class MailServiceConfig {

	private String maxHourlyEmailLimit;

	public String getMaxHourlyEmailLimit() {
		return maxHourlyEmailLimit;
	}

	public void setMaxHourlyEmailLimit(String maxHourlyEmailLimit) {
		this.maxHourlyEmailLimit = maxHourlyEmailLimit;
	}
}
