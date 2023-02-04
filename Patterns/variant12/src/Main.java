public class Main {
    public static void main(String[] args) {
        SamsungFactory factory1 = new SamsungFactory();
        factory1.executeOrder();

        IPhoneFactory factory2 = new IPhoneFactory();
        factory2.executeOrder();
    }
}