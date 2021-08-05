package Vehicle;

import java.time.LocalDate;

public class MotorCycle extends Vehicle {
    boolean haveSideCar;

    public MotorCycle(String name, double area, double engineDisplacment, LocalDate productionDate, EngineType engineType, boolean haveSideCar) {
        super(name, area, engineDisplacment, productionDate, engineType);
        this.haveSideCar = haveSideCar;
    }

}
