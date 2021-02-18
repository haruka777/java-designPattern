public class People {
	String name;
	String sex;
	int age;

	People(String name, String sex, int age) {
		if (name == null || sex == null)
			throw new NullPointerException();

		this.name = name;
		this.sex = sex;
		this.age = age;
	}

}
