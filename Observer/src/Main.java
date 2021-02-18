import java.util.Observer;

public class Main {

	public static void main(String[] args) {
        NumberGenerator generator = new RandomNumberGenerator();
        Observer degitObserver = (Observer) new DegitObserver();
        Observer graphObserver = (Observer) new GraphObserver();
        generator.addObserver(degitObserver);
        generator.addObserver(graphObserver);
        generator.execute();
	}

}
