package Vehicle;

import java.time.LocalDate;

public class Amphibian extends Vehicle {
    double wadingDepth;

    public Amphibian(String name, double area, double engineDisplacment, LocalDate productionDate, EngineType engineType, double wadingDepth) {
        super(name, area, engineDisplacment, productionDate, engineType);
        this.wadingDepth = wadingDepth;
    }
}
