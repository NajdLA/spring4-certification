package spring.container.dependency.ioc.XMLconfig;

public class MailService {
	

	private LogService logService;
	private DataBaseService dataBaseService;
	private String mailServer;
	private String defaultLocale;
	
	private MailServiceConfig mailServiceConfig;
	
	//constructor injection
	public MailService(DataBaseService dataBaseService) {
		this.dataBaseService = dataBaseService;
	}

	//setter injection
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public MailService() {
		// TODO Auto-generated constructor stub
	}

	public LogService getLogService() {
		return logService;
	}

	public String getMailServer() {
		return mailServer;
	}

	public void setMailServer(String mailServer) {
		this.mailServer = mailServer;
	}

	public String getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(String defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	public MailServiceConfig getMailServiceConfig() {
		return mailServiceConfig;
	}

	public void setMailServiceConfig(MailServiceConfig mailServiceConfig) {
		this.mailServiceConfig = mailServiceConfig;
	}

}
