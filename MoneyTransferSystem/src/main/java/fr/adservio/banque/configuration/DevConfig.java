package fr.adservio.banque.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@Profile("dev")
@PropertySource(value={"classpath:app-${spring.profiles.active}.properties"})
public class DevConfig {

	@Autowired
	public Environment env;

	@Bean(name = "dataSource")
	public DataSource dataSourceForDev() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		return builder.setName(env.getProperty("db.name"))
				.setType(EmbeddedDatabaseType.HSQL)
				.addScript(env.getProperty("db.schema"))
				.addScript(env.getProperty("db.testdata")).build();
	}

}
