public abstract class Smartphone {
    double screenSize;
    int memory;
    String bodyMaterial;
    String operatingSystem;

    public Smartphone(double screenSize, int memory, String bodyMaterial, String operatingSystem) {
        this.screenSize = screenSize;
        this.memory = memory;
        this.bodyMaterial = bodyMaterial;
        this.operatingSystem = operatingSystem;
    }
}
