package Service;

import Person.Person;
import Vehicle.Vehicle;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class CarServiceSpot {
    double area;
    double pricePerDay;
    public Vehicle vehicleInRepairProcess;
    LocalDate repairStart;
    LocalDate repairFinish;
    static String repairLog = "";
    int id;
    static int autoNumber;

    public CarServiceSpot() {
        area = 25;
        pricePerDay = 40;
        vehicleInRepairProcess = null;
        repairStart = null;
        repairFinish = null;
        id=autoNumber++;
    }

    public Person whosVehicle(ArrayList<Person> allClients) {
        for (Person person : allClients) {
            for (Vehicle vehicle : person.ownedVehicles) {
                if (vehicle.id == vehicleInRepairProcess.id) {
                    return person;
                }
            }
        }
        return null;
    }

    public String getReapirLog() {
        return repairLog;
    }

    public String repairFinished(){
        String str = vehicleInRepairProcess.toString()+'\n';
        vehicleInRepairProcess = null;
        repairStart = null;
        repairFinish = null;
        return str;
    }

    @Override
    public String toString() {
        return "CarServiceSpot id " + id + ", area = " + area + ", it cost " + pricePerDay + " to rent, " +
                (vehicleInRepairProcess == null ?
                        "there is no vehicle in a repair process now" :
                        "the vehicle in repair is " + vehicleInRepairProcess + " the repair started " +
                                repairStart + " and its estemated it will be finished on " + repairFinish+" the repair will cost "+repairCost())+"zl\n";
    }

    public void simpleRepair(Person person, int vehicleNumber) {
        if (vehicleInRepairProcess==null) {
            repairStart = Service.programDate;
            repairFinish = repairStart.plusDays((int) (Math.random() * 5 + 1));
            if (person.canAford(repairCost())) {
                vehicleInRepairProcess = person.ownedVehicles.get(vehicleNumber);
                for (Vehicle vehicle : person.ownedVehicles) {
                    if (vehicle.id == vehicleInRepairProcess.id) {
                        person.debtCounter(howLongRepair() * pricePerDay);
                    }
                }
                repairLog += "Repair was carried on service spot id " + id + " vehicle in repair " + vehicleInRepairProcess.toString() + " repair started on " + repairStart + " end of the repair " + repairFinish + " it costs " + repairCost() + '\n';
            } else {
                System.out.println("Your monthly expenses exced 1250 zl");
            }
        }else
            System.out.println("Service spot is already taken");
    }

    public void reversSimpleRepair(){
        vehicleInRepairProcess=null;
        repairStart=null;
        repairFinish=null;
        repairLog.substring(0,repairLog.length()-1);
        repairLog+= " - Repair Failed\n";
    }

    public long howLongRepair(){
        return repairStart.until(repairFinish, ChronoUnit.DAYS);
    }

    public double repairCost(){
        return howLongRepair()*pricePerDay;
    }
}
