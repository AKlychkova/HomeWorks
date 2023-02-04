public class InsurancePolicy extends Decorator{
    String insurancePolicyNumber;

    public InsurancePolicy(Card component, String insurancePolicyNumber) {
        super(component);
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.printf("Insurance policy number: %s\n",insurancePolicyNumber);
    }
}
