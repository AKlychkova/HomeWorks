public abstract class SmartphoneFactory {
    abstract Smartphone createSmartphone();
    void executeOrder() {
        Smartphone phone = createSmartphone();
        pack(phone);
        System.out.printf("New smartphone %s with %f\" screen size, %d Gb memory, %s body material and %s OS is ready\n",
                phone.getClass().getName(),phone.screenSize,phone.memory,phone.bodyMaterial,phone.operatingSystem);
    }
    void pack(Smartphone smartphone) {};
}
