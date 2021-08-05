package Vehicle;

import Item.Item;
import Person.Person;

import java.time.LocalDate;

public abstract class Vehicle extends Item {
    double engineDisplacment;
    LocalDate productionDate;
    EngineType engineType;
    public Person vehicleOwner;
    static int autoCount = 0;
    public int id;

    public Vehicle(String name, double area, double engineDisplacment, LocalDate productionDate, EngineType engineType) {
        super(name, area);
        this.engineDisplacment = engineDisplacment;
        this.productionDate = productionDate;
        this.engineType = engineType;
        id = autoCount++;
    }

    @Override
    public String toString() {
        return "id " + id + " name " + name + " engine displacment " + engineDisplacment + " engine type " + engineType;
    }

    public String getOwner(){
        return vehicleOwner.toString();
    }
}
