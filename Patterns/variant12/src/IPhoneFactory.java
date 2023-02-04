public class IPhoneFactory extends SmartphoneFactory{

    @Override
    Smartphone createSmartphone() {
        return new IPhone(6.1,265,"metal", "Apple iOS");
    }
}
