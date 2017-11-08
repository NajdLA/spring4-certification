package fr.adservio.banque.beans;

public class MonetaryAmount {
	
	private String amount;

	public MonetaryAmount(){
		
	}
	public MonetaryAmount(String amount){
		this.setAmount(amount);
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}


}
