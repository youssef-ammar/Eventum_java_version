package chatbot.products;

import org.bson.Document;

public class Laptop extends ConsumerElectronics {
    private int ramSize;
    private String cpuModel;
    private String os;

    public Laptop(String id, String brand, String model, Double price, Double height, Double width, Double depth, Double weight, Double reviewPoint1,Double reviewPoint2, Double screenSize, int storageSize, int ramSize, String cpuModel, String os) {
        super(id, brand, model, price,"Laptop", height, width, depth, weight, reviewPoint1,reviewPoint2,  screenSize ,storageSize);
        this.ramSize = ramSize;
        this.cpuModel = cpuModel;
        this.os = os;
    }

    public Laptop(String brand, String model, Double price, Double height, Double width, Double depth, Double weight, Double screenSize, int storageSize, int ramSize, String cpuModel, String os) {
        super(brand, model, price,"Laptop", height, width, depth, weight, screenSize ,storageSize);
        this.ramSize = ramSize;
        this.cpuModel = cpuModel;
        this.os = os;
    }

    @Override
    public Document toDocument(){
        return super.toDocument()
                .append("RAM Size", ramSize)
                .append("CPU Model", cpuModel)
                .append("OS", os);
    }

    @Override
    public String toString() {
        return super.toString() + "RAM: " + ramSize +  "GB \nCPU: " + cpuModel + "\n";
    }
}
