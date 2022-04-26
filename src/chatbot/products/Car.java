package chatbot.products;

public class Car extends Vehicle {

    private int numberOfSeats;
    private String airConditionerType;

    public Car(String id, String brand, String model, Double price, Double height, Double width, Double depth, Double weight, Double reviewPoint1,Double reviewPoint2, int power, String fuelType, int numberOfSeats, String airConditionerType) {
        super(id, brand, model, price, "Car", height, width, depth, weight, reviewPoint1,reviewPoint2, power, fuelType);
        this.numberOfSeats = numberOfSeats;
        this.airConditionerType = airConditionerType;
    }

    public Car(String brand, String model, Double price, Double height, Double width, Double depth, Double weight, int power, String fuelType, int numberOfSeats, String airConditionerType) {
        super(brand, model, price, "Car", height, width, depth, weight, power, fuelType);
        this.numberOfSeats = numberOfSeats;
        this.airConditionerType = airConditionerType;
    }

    @Override
    public org.bson.Document toDocument(){
        return super.toDocument()
                .append("Number of Seats", numberOfSeats)
                .append("Air Conditioner Type", airConditionerType);
    }

    @Override
    public String toString(){
        return super.toString() + "Number of Seats: " + numberOfSeats +"\nAir Conditioner Type: " + airConditionerType + "\n";
    }
}