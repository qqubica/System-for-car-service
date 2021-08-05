package Service;

import Exception.ProblematicTenantException;
import Person.Person;
import Vehicle.Vehicle;

import java.time.LocalDate;
import java.util.Scanner;

public class ParkingSpace {
    static double parkingSpaceArea;
    public Vehicle parkedVehicle;
    LocalDate parkingDate;
    public LocalDate payedUnitill;
    public double pricePerDay;
    private static int autoCount = 0;
    public int id;

    public ParkingSpace() {
        parkingSpaceArea = 12.5;
        pricePerDay = 6;
        this.parkedVehicle = null;
        parkingDate = null;
        payedUnitill = null;
        this.id = autoCount++;
    }

    @Override
    public String toString() {
        return "ParkingSpace id " + id + ", area of parking space = " + parkingSpaceArea +
                (parkedVehicle == null ? ". Parking space is empty at this moment" : " on this parking place parks " + parkedVehicle +
                        " it parked here on " + parkingDate + " and is expected to park here until " + payedUnitill) + '\n';
    }

    public void endOfPArking() {
        this.parkedVehicle = null;
        parkingDate = null;
        payedUnitill = null;
    }

    public boolean ParkVehicle(Person person, int parkingFor) throws ProblematicTenantException {
        if (person.infoList.size() >= 3) {
            try {
                throw new ProblematicTenantException();
            } catch (Throwable t) {
                System.out.println(t.getMessage());
            }
        }
        if (parkedVehicle == null) {
            if (person.ownedVehicles.isEmpty()) {
                System.out.println(person + " nie posiada zadnego pojazdu");
                return false;
            } else {
                if (person.ownedVehicles.size() == 1) {
                    parkedVehicle = person.ownedVehicles.get(0);
                } else {
                    System.out.println("Wybierz nr pojazdu ktory chcesz zaparkowac:");
                    System.out.println(person.ownVehicleList());
                    Scanner scanner = new Scanner(System.in);
                    int index = Integer.parseInt(scanner.next()) - 1;
                    parkedVehicle = person.ownedVehicles.get(index);
                    parkingDate = LocalDate.now();
                    payedUnitill = LocalDate.now().plusDays(parkingFor);
                }
                return true;
            }
        } else {
            System.out.println("Miejsce parkingowe jest juz zajete");
        }
        return false;
    }

    public void simplePark(Person person, int parkingFor) {
        person.canAford(pricePerDay * parkingFor);
        parkedVehicle = person.ownedVehicles.get(0);
        parkingDate = Service.programDate;
        payedUnitill = parkingDate.plusDays(parkingFor);
    }

    public void simplePark(Person person) {
        person.canAford(pricePerDay);
        parkedVehicle = person.ownedVehicles.get(0);
        parkingDate = Service.programDate;
    }

    void chcecForProblematicException(Person person) throws ProblematicTenantException {
        try {
            if (person.infoList.size() >= 3) {
                throw new ProblematicTenantException();
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }
}
