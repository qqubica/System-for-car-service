package Service;

import Exception.Info;
import Person.Person;
import Vehicle.Vehicle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Service {
    double serviceArea;
    double storageArea;
    double storageForTiresArea;
    public ParkingSpace[] parkingSpaces;
    public ServiceWarehous serviceWarehous;
    public TiresWarehouse tiresWarehouse;
    public ArrayList<ConsumerWarehouse> consumerWarehouses;
    public CarServiceSpot[] serviceSpots;
    public IndependentCarServiceSpot[] independentCarServiceSpots;
    public static LocalDate programDate = LocalDate.now();
    Queue<Vehicle> repairQue;
    Queue<Integer> vehicleInQue;
    ArrayList<Person> clients;
    int id;
    static int autoNumber = 0;

    public Service(double serviceArea, double storageArea, double storageForTiresArea, int numberOfParkingSpaces,
                   double storageForPartsArea, int numberOfServiceSpots, int numberOfIndependentServiceSpots) {
        repairQue = new LinkedList<Vehicle>();
        vehicleInQue = new LinkedList<>();
        this.serviceArea = serviceArea;
        this.storageArea = storageArea;
        while (storageForPartsArea + storageForTiresArea > storageArea) {
            System.out.println("Powiezchnia serwisu musi uwzgledniać powierzchnie przechowalni opon i czesci(" + storageForPartsArea + " + " + storageForTiresArea + " <= " + storageArea + "(laczna pownierzchnia magazynowa))");
            System.out.println("Wprowadz ponownie powierzchnie przechowalni opon:");
            Scanner scanner = new Scanner(System.in);
            storageForTiresArea = scanner.nextDouble();
            System.out.println("Wprowadz ponownie powierzchnie magazynu na czesci:");
            storageForPartsArea = scanner.nextDouble();
        }
        this.storageForTiresArea = storageForTiresArea;
        parkingSpaces = new ParkingSpace[numberOfParkingSpaces];

        for (int i = 0; i < parkingSpaces.length; i++) {
            parkingSpaces[i] = new ParkingSpace();
        }

        this.serviceWarehous = new ServiceWarehous(storageForPartsArea);
        this.tiresWarehouse = new TiresWarehouse(storageForTiresArea);
        this.consumerWarehouses = new ArrayList<ConsumerWarehouse>();
        serviceSpots = new CarServiceSpot[numberOfServiceSpots];
        for (int i = 0; i < numberOfServiceSpots; i++) {
            serviceSpots[i] = new CarServiceSpot();
        }

        while (numberOfIndependentServiceSpots < 1) {
            System.out.println("W serwisie musi byc przynajmniej 1 miejsce do samodzielnej naprawy");
            System.out.print("Wprowadz liczbe miejsc do samodzielnej naprawy(minimum 1)");
            Scanner scanner = new Scanner(System.in);
            numberOfIndependentServiceSpots = Integer.parseInt(scanner.next());
        }

        independentCarServiceSpots = new IndependentCarServiceSpot[numberOfIndependentServiceSpots];
        for (int i = 0; i < numberOfIndependentServiceSpots; i++) {
            independentCarServiceSpots[i] = new IndependentCarServiceSpot();
        }
        id = autoNumber++;
    }

    public Service(double serviceWidth, double serviceLength, double storageArea, double storageForTiresArea, int numberOfParkingSpaces,
                   double storageForPartsArea, int numberOfServiceSpots, int numberOfIndependentServiceSpots) {
        repairQue = new LinkedList<Vehicle>();
        vehicleInQue = new LinkedList<>();
        this.serviceArea = serviceWidth * serviceLength;
        this.storageArea = storageArea;
        while (storageForPartsArea + storageForTiresArea > storageArea) {
            System.out.println("Powiezchnia serwisu musi uwzgledniać powierzchnie przechowalni opon i czesci(" + storageForPartsArea + " + " + storageForTiresArea + " <= " + storageArea + "(laczna pownierzchnia magazynowa))");
            System.out.println("Wprowadz ponownie powierzchnie przechowalni opon:");
            Scanner scanner = new Scanner(System.in);
            storageForTiresArea = scanner.nextDouble();
            System.out.println("Wprowadz ponownie powierzchnie magazynu na czesci:");
            storageForPartsArea = scanner.nextDouble();
        }
        this.storageForTiresArea = storageForTiresArea;
        parkingSpaces = new ParkingSpace[numberOfParkingSpaces];
        this.serviceWarehous = new ServiceWarehous(storageForPartsArea);
        this.tiresWarehouse = new TiresWarehouse(storageForTiresArea);
        this.consumerWarehouses = new ArrayList<ConsumerWarehouse>();
        serviceSpots = new CarServiceSpot[numberOfServiceSpots];
        while (numberOfIndependentServiceSpots < 1) {
            System.out.println("W serwisie musi byc przynajmniej 1 miejsce do samodzielnej naprawy");
            System.out.print("Wprowadz liczbe miejsc do samodzielnej naprawy(minimum 1)");
            Scanner scanner = new Scanner(System.in);
            numberOfIndependentServiceSpots = scanner.nextInt();
        }
        independentCarServiceSpots = new IndependentCarServiceSpot[numberOfIndependentServiceSpots];
        id = autoNumber++;
    }

    public void simTime(LocalDate simDay) {
        programDate = simDay;
        for (ParkingSpace parkingSpace : parkingSpaces) {
            if ((parkingSpace.payedUnitill != null) && (parkingSpace.payedUnitill.isBefore(programDate))) {
                System.out.println("Wynajem miejsca parkingowego sie skoczył " + parkingSpace);
                for (Person person : clients) {
                    for (Vehicle vehicle : person.ownedVehicles) {
                        if (parkingSpace.parkedVehicle != null && parkingSpace.parkedVehicle.id == vehicle.id) {
                            person.addInfo(new Info());
                            parkingSpace.endOfPArking();
                        }
                    }
                }
            }
        }
        for (ConsumerWarehouse consumerWarehouse : consumerWarehouses) {
            if (consumerWarehouse.endOfRent != null && consumerWarehouse.endOfRent.isBefore(simDay)) {
                clients.get(consumerWarehouse.withPermission.get(0).id).addInfo(new Info());
                System.out.println("Wynajem magazynu sie skonczyl " + consumerWarehouse);
                consumerWarehouse.endOfRent();
            }
        }
        for (CarServiceSpot carServiceSpot : serviceSpots) {
            if (carServiceSpot.vehicleInRepairProcess != null && carServiceSpot.repairFinish.isBefore(simDay)) {
                clients.get(carServiceSpot.whosVehicle(clients).id).addInfo(new Info());
                System.out.println("Naprawa zakonczona " + carServiceSpot.repairFinished());
                if (!repairQue.isEmpty()){
                    carServiceSpot.simpleRepair(repairQue.poll().vehicleOwner, vehicleInQue.poll());
                }
            }
        }
        for (IndependentCarServiceSpot independentCarServiceSpot : independentCarServiceSpots) {
            if (independentCarServiceSpot.repairFinish != null && independentCarServiceSpot.repairFinish.isBefore(simDay)) {
                System.out.println(programDate);
                clients.get(independentCarServiceSpot.whosVehicle(clients).id).addInfo(new Info());
                System.out.println("Naprawa na miejscu do samodzielnej naprawy zakonczona " + independentCarServiceSpot.repairFinished());
            }
        }
    }

    public boolean addToRepairQue(Vehicle vehicle, int vehicleID){
        for (ParkingSpace parkingSpace :parkingSpaces ) {
            if (parkingSpace.parkedVehicle==null){
                parkingSpace.simplePark(vehicle.vehicleOwner);
                repairQue.add(vehicle);
                vehicleInQue.add(vehicleID);
                System.out.println("Twoj pojazd zostal wstawiony do kolejki(FIFO) gdzie bedzie oczekiwac na zwolnie sie miejsca naprawczego");
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String parkingSpacesList = "";
        for (ParkingSpace parkingSpace : parkingSpaces) {
            parkingSpacesList += parkingSpace.toString();
        }
        String carsInServiceList = "";
        for (CarServiceSpot carServiceSpot : serviceSpots) {
            carsInServiceList += carServiceSpot.toString();
        }
        String independentSpotsList = "";
        for (IndependentCarServiceSpot independentCarServiceSpot : independentCarServiceSpots) {
            independentSpotsList += independentCarServiceSpot.toString();
        }
        return "Service id " + id + ", area = " + serviceArea + ", storage area = " + storageArea + ", storage for tires area = " + storageForTiresArea +
                "\nService warhouse:\n" + serviceWarehous.toString() + "\nParking spaces:\n" + parkingSpacesList + tiresWarehouse.toString() +
                "\nConsumer warhouses list:\n" + consumerWarehouses.toString() + "\nService spots status:\n" + carsInServiceList +
                "Indepentent repair spots status:\n" + independentSpotsList + '\n';
    }

    public boolean freeParkingSpace() {
        for (ParkingSpace parkingSpace : parkingSpaces) {
            if (parkingSpace.parkedVehicle == null)
                return true;
        }
        return false;
    }

    public void addConsumerWarehouse(double areaOfWarehouse) {
        consumerWarehouses.add(new ConsumerWarehouse(areaOfWarehouse));
    }

    public void getClientsList(ArrayList<Person> clients) {
        this.clients = clients;
    }
}
