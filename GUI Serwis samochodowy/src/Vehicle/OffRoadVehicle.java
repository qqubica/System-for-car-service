package Vehicle;

import java.time.LocalDate;

public class OffRoadVehicle extends Vehicle {
    double clearance;


    public OffRoadVehicle(String name, double area, double engineDisplacment, LocalDate productionDate, EngineType engineType, double clearance) {
        super(name, area, engineDisplacment, productionDate, engineType);
        this.clearance = clearance;
    }
}
