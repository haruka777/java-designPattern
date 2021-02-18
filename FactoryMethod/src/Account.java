
public class Account extends Product {

	private String owner;

	Account(String owner) {
		System.out.println("Create Account" + owner);
		this.owner = owner;
	}

	public void use() {
		System.out.println("Use Account" + owner);
	}

	public String getOwner() {
		return owner;
	}
}
