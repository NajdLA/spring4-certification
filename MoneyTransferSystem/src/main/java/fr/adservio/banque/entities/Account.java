package fr.adservio.banque.entities;

import java.util.logging.Logger;

import fr.adservio.banque.MoneyTransferSystemApplication;

public class Account {

	private String id;
	private String balance;
	private String moneyToSend;
	private String moneyToReceive;
	
	private static final Logger LOGGER = Logger.getLogger(MoneyTransferSystemApplication.class.getName());

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean debit(String moneyToSend) {
		Double moneyToSendInt = convertFromStringToInt(moneyToSend);
		Double balanceInt = convertFromStringToInt(this.balance);
		if (balanceInt > moneyToSendInt) {
			this.moneyToSend = moneyToSend;
			balanceInt = balanceInt - moneyToSendInt;
			this.balance = convertFromIntToString(balanceInt);
			return true;
		} else {
			LOGGER.warning("insufficient balance!");
			return false;
		}

	}

	public void credit(String moneyToReceive) {
		this.moneyToReceive = moneyToReceive;
		Double moneyToReceiveInt = convertFromStringToInt(moneyToReceive);
		Double balanceInt = convertFromStringToInt(this.balance.replaceAll("\\s", ""));
		Double newBalance = moneyToReceiveInt + balanceInt;

		this.balance = convertFromIntToString(newBalance);
	}

	public Account() {

	}

	public Account(String id, String balance) {
		this.id = id;
		this.balance = balance;

	}

	public String getMoneyToSend() {
		return moneyToSend;
	}

	public void setMoneyToSend(String moneyToSend) {
		this.moneyToSend = moneyToSend;
	}

	public String getMoneyToReceive() {
		return moneyToReceive;
	}

	public void setMoneyToReceive(String moneyToReceive) {
		this.moneyToReceive = moneyToReceive;
	}

	public Double convertFromStringToInt(String value) {
		Double intValue = Double.parseDouble(value);
		return intValue;
	}

	public String convertFromIntToString(Double value) {
		String stringValue = Double.toString(value);
		return stringValue;
	}

}
