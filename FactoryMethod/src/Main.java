
public class Main {

	public static void main(String[] args) {

		Factory factory = new AccountFactory();
		Product account1 = factory.create("佐藤");
		Product account2 = factory.create("鈴木");
		Product account3 = factory.create("田中");
		Product account4 = factory.create("齊藤");

		account1.use();
		account2.use();
		account3.use();
		account4.use();

	}

}
