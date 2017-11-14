package spring.container.dependency.ioc.beans;

public class Customer {
	
	private String username;
	private String password;
	private boolean active;
	
	private String taxe;
	private String address;

	public String getTaxe() {
		return taxe;
	}
	public void setTaxe(String taxe) {
		this.taxe = taxe;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

}
