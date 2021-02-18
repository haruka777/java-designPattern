
public class PeopleEffectiveJava {
	String name;
	  String sex;
	  int age;

	  static class Builder {
	    String name;
	    String sex;
	    int age;

	    Builder(String name, String sex) {
	      if (name == null || sex == null) throw new NullPointerException();

	      this.name = name;
	      this.sex = sex;
	    }

	    Builder age(int age) {
	      this.age = age;
	      return this;
	    }

	    PeopleEffectiveJava build() {
	      if (name == null || sex == null) throw new NullPointerException();

	      return new PeopleEffectiveJava();
	    }
	  }
}
