package Vehicle;

import java.time.LocalDate;

public class CityCar extends Vehicle {
    int co2emision;

    public CityCar(String name, double area, double engineDisplacment, LocalDate productionDate, EngineType engineType, int co2emision) {
        super(name, area, engineDisplacment, productionDate, engineType);
        this.co2emision = co2emision;
    }
}
