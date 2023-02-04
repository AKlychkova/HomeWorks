public class Main {
    public static void main(String[] args) {
        Card a = new BankCard("1234 5678 9012 3456",123,"01/23");
        Card b = new Passport(a,"1116 263892","УМВД");
        Card c = new InsurancePolicy(b,"123-456-789 00");
        c.printInfo();
    }
}