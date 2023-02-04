public class SamsungFactory extends SmartphoneFactory{
    @Override
    Smartphone createSmartphone() {
        return new Samsung(5.5,512,"plastic","Android");
    }
}
