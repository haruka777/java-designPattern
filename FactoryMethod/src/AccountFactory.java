import java.util.ArrayList;

public class AccountFactory extends Factory {

	private ArrayList<String> owners = new ArrayList<String>();

	protected Product createProduct(String owner) {
		return new Account(owner);
	}

	protected void registerProduct(Product product) {
		owners.add(((Account) product).getOwner());
	}

	public ArrayList<String> getOwners() {
		return owners;
	}
}
