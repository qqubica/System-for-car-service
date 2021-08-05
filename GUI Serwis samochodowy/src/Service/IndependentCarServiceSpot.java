package Service;

import Person.Person;

public class IndependentCarServiceSpot extends CarServiceSpot {
    public IndependentCarServiceSpot() {
        super();
        pricePerDay = 20;
    }
    public void simpleRepair(Person person, int vehicleNumber, int rentFor){
        vehicleInRepairProcess = person.ownedVehicles.get(vehicleNumber);
        repairStart= Service.programDate;
        repairFinish=repairStart.plusDays(rentFor);
    }
}
