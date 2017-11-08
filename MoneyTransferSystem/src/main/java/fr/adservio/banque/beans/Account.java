package fr.adservio.banque.beans;

import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.adservio.banque.MoneyTransferSystemApplication;

@Entity
@Table(name = "account")
public class Account {

	@Column(name = "id")
	@Id
	private String id;

	@Column(name = "balance")
	private String balance;

	@Transient
	private String moneyToSend;

	@Transient
	private String moneyToReceive;

	@Transient
	private Double moneyImpot;

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
			balanceInt = balanceInt - (moneyToSendInt + this.moneyImpot);
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

	public Account(String idAccount, String balanceString, Double moneyImpot) {
		this.id = idAccount;
		this.balance = balanceString;
		this.moneyImpot = moneyImpot;
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

	@Override
	public String toString() {
		return "The Account id = " + id + ", balance=" + balance + "]";
	}

	public Double getMoneyImpot() {
		return moneyImpot;
	}

	public void setMoneyImpot(Double moneyImpot) {
		this.moneyImpot = moneyImpot;
	}

}
