package fr.adservio.banque.beans;


public class TaxCalculator {

	private String defaultLocale;
	private Double impotByRegion;
	
	public TaxCalculator() {
		// TODO Auto-generated constructor stub
	}

	public String getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(String defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	public Double getImpotByRegion() {
		return impotByRegion;
	}

	public void setImpotByRegion(Double impotByRegion) {
		this.impotByRegion = impotByRegion;
	}

}
