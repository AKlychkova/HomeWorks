public class Passport extends Decorator{
    String passportNumber;
    String issuedBy;

    public Passport(Card component, String passportNumber, String issuedBy) {
        super(component);
        this.passportNumber = passportNumber;
        this.issuedBy = issuedBy;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.printf("Passport number: %s, Issued by: %s\n",passportNumber,issuedBy);
    }
}
