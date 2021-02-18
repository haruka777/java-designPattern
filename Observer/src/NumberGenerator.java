import java.util.ArrayList;
import java.util.Iterator;

public abstract class NumberGenerator {
    private ArrayList observers = new ArrayList();
    public void addObserver(java.util.Observer degitObserver) {
        observers.add(degitObserver);
    }
    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }
    public void notifyObservers() {
        Iterator iterator = observers.iterator();
        while (iterator.hasNext()) {
            Observer obserber = (Observer)iterator.next();
            obserber.update(this);
        }
    }

    public abstract int getNumber();
    public abstract void execute();


}
