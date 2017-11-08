package fr.adservio.banque.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import fr.adservio.banque.beans.TaxCalculator;

@Configuration
@PropertySource("classpath:taxeByCountry.properties")
public class TaxConfig {

	@Value("#{systemProperties['user.country']}")
	private String country;
	
	@Autowired
	Environment env;

	@Bean("taxCalculator")
	public TaxCalculator taxCalculator() throws IOException {
		TaxCalculator tc = new TaxCalculator();
		tc.setDefaultLocale(country);
		tc.setImpotByRegion(Double.valueOf(env.getProperty(country))/100);
		return tc;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
