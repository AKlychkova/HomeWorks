
public class BankCard implements Card {
    String cardNumber;
    int CVC;
    String validThru;

    public BankCard(String cardNumber, int CVC, String validThru) {
        this.cardNumber = cardNumber;
        this.CVC = CVC;
        this.validThru = validThru;
    }

    @Override
    public void printInfo() {
        System.out.printf("Bank card number: %s, CVC: %d, valid thru : %s\n",cardNumber,CVC,validThru);
    }
}
