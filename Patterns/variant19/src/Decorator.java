public abstract class Decorator implements Card{
    Card component;

    public Decorator(Card component) {
        this.component = component;
    }

    @Override
    public void printInfo() {
        component.printInfo();
    }
}
