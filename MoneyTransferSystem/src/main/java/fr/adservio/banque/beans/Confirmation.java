package fr.adservio.banque.beans;

public class Confirmation {

	private String newBalance ;

	public String getNewBalance() {
		return newBalance;
	}

	public void setNewBalance(String newBalance) {
		this.newBalance = newBalance;
	}
	public Confirmation(String newBalance){
		this.newBalance = newBalance;
	}
}
