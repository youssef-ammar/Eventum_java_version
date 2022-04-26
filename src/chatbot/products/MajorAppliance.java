package chatbot.products;

import org.bson.Document;

public abstract class MajorAppliance extends Product {

    private int capacity;
    private String energyEfficiency;

    public MajorAppliance(String id, String brand, String model, Double price, String type, Double height, Double width, Double depth, Double weight, Double reviewPoint1,Double reviewPoint2, int capacity, String energyEfficiency) {
        super(id, brand, model, price, "Major Appliance", type, height, width, depth, weight, reviewPoint1,reviewPoint2);
        this.capacity = capacity;
        this.energyEfficiency = energyEfficiency;
    }

    public MajorAppliance(String brand, String model, Double price, String type, Double height, Double width, Double depth, Double weight, int capacity, String energyEfficiency) {
        super(brand, model, price, "Major Appliance", type, height, width, depth, weight);
        this.capacity = capacity;
        this.energyEfficiency = energyEfficiency;
    }

    @Override
    public Document toDocument(){
        return super.toDocument()
                .append("Capacity", capacity)
                .append("Energy Efficiency", energyEfficiency);
    }

    @Override
    public String toString(){
        return super.toString() + "Capacity: " + capacity + "\nEnergy Efficiency: " + energyEfficiency + "\n";
    }

}
