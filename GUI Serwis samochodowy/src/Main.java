import Exception.ProblematicTenantException;
import Item.Item;
import Person.Owner;
import Person.Person;
import Service.*;
import Vehicle.*;

import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ProblematicTenantException{

        Owner tworca = new Owner("Jakub", "Wudarski", "99042303211", "Warszawa", LocalDate.of(1999, 4, 23));

        Service service1 = new Service(350, 80, 25, 4, 20, 4, 1);

        //^^ tworzy się serwis

        tworca.services.add(service1);

        //^^ serwis jest przydzielany właścicielowi

        TimeSimulation ts = new TimeSimulation();
        ts.passService(service1);
        try {
            ts.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ts.start();

       //^^ uruchamian jest symulacja upływu czasu i sprawdzanie czy nie skonczył się wynajem lub naprawa

        ArrayList<Person> personArray = new ArrayList<Person>();
        personArray.add(tworca);
        personArray.add(new Person("Makary", "Kwiatkowski", "58040953990", "ul. Graniczna 119", LocalDate.of(1958, 4, 9)));
        personArray.add(new Person("Jowita", "Sokolkowska", "56061825528", "ul. Niepodległości 129", LocalDate.of(1956, 6, 18)));
        personArray.add(new Person("Krystian", "Wieczorek", "53110954415", "ul. Jałtańska 81", LocalDate.of(1953, 11, 9)));
        personArray.add(new Person("Rafal", "Zielinski", "52122150172", "ul. Dolna 145", LocalDate.of(1952, 12, 21)));
        personArray.add(new Person("Mikolaj", "Wysocki", "80112676553", "ul. Magnoliowa 28", LocalDate.of(1980, 11, 26)));
        service1.getClientsList(personArray);

        //^^ tworzone są osoby i przypisywane do arraylisty osoby

        service1.addConsumerWarehouse(2);
        service1.addConsumerWarehouse(4);
        service1.addConsumerWarehouse(7);
        service1.addConsumerWarehouse(10);
        service1.addConsumerWarehouse(3);
        service1.addConsumerWarehouse(2.1);
        service1.addConsumerWarehouse(2.7);
        service1.addConsumerWarehouse(1.8);
        service1.addConsumerWarehouse(1.5);
        service1.addConsumerWarehouse(1.4);
        service1.addConsumerWarehouse(2.5);
        service1.addConsumerWarehouse(5);

        //^^ w serwisie powstają magazyny do wynajmu

        service1.consumerWarehouses.get(0).rentConsumerWarehouse(personArray.get(1), 3);
        service1.consumerWarehouses.get(1).rentConsumerWarehouse(personArray.get(1), 75);
        service1.consumerWarehouses.get(2).rentConsumerWarehouse(personArray.get(2), 31);
        service1.consumerWarehouses.get(3).rentConsumerWarehouse(personArray.get(4), 35);
        service1.consumerWarehouses.get(4).rentConsumerWarehouse(personArray.get(3), 60);
        service1.consumerWarehouses.get(5).rentConsumerWarehouse(personArray.get(3), 123);
        service1.consumerWarehouses.get(6).rentConsumerWarehouse(personArray.get(4), 230);
        service1.consumerWarehouses.get(7).rentConsumerWarehouse(personArray.get(2), 7);
        service1.consumerWarehouses.get(8).rentConsumerWarehouse(personArray.get(2), 14);
        service1.consumerWarehouses.get(9).rentConsumerWarehouse(personArray.get(4), 21);
        service1.consumerWarehouses.get(10).rentConsumerWarehouse(personArray.get(1), 2);

        //^^ magazyny są wynajmowane

        ArrayList<Vehicle> vehicleArrayList = new ArrayList<Vehicle>();
        vehicleArrayList.add(new Amphibian("Borsuk", 11.8, 6.2, LocalDate.of(2005, 2, 23), EngineType.DIESEL_ENGINE, 1.43));
        vehicleArrayList.add(new Amphibian("Rosomak", 11, 4.8, LocalDate.of(2012, 8, 1), EngineType.DIESEL_ENGINE, 1.32));
        vehicleArrayList.add(new Amphibian("Alvis Stalwart", 12.5, 6.5, LocalDate.of(1973, 12, 10), EngineType.PETROL_ENGINE, 2.19));
        vehicleArrayList.add(new OffRoadVehicle("Land rover Defender", 7, 1.998, LocalDate.of(1989, 5, 16), EngineType.DIESEL_ENGINE, 0.45));
        vehicleArrayList.add(new OffRoadVehicle("Suzuki samurai", 6.2, 2.5, LocalDate.of(1999, 3, 29), EngineType.PETROL_ENGINE, 0.38));
        vehicleArrayList.add(new OffRoadVehicle("Jeep wrangler", 7.9, 3.998, LocalDate.of(2008, 7, 11), EngineType.PETROL_ENGINE, 0.33));
        vehicleArrayList.add(new CityCar("Nissan Leaf", 7.2, 0, LocalDate.of(2020, 11, 4), EngineType.ELECTRICAL_ENGINES, 0));
        vehicleArrayList.add(new CityCar("BMW 320i", 8.3, 2, LocalDate.of(2015, 10, 6), EngineType.PETROL_ENGINE, 145));
        vehicleArrayList.add(new CityCar("Tesla model 3", 7.7, 0, LocalDate.of(2019, 4, 26), EngineType.ELECTRICAL_ENGINES, 0));
        vehicleArrayList.add(new MotorCycle("Aprila RS", 2.17, 0.125, LocalDate.of(2011, 8, 18), EngineType.PETROL_ENGINE, false));
        vehicleArrayList.add(new MotorCycle("Hornet CJ750Y", 3.87, 0.750, LocalDate.of(1971, 2, 17), EngineType.PETROL_ENGINE, true));
        vehicleArrayList.add(new MotorCycle("Yamaha MT", 2.67, 0.998, LocalDate.of(2015, 10, 1), EngineType.PETROL_ENGINE, false));

        //^^ pojazdy są tworzone i zapisywane do ArrayListy

        for (int i = 0; i < personArray.size(); i++) {
            personArray.get(i).addVehicle(vehicleArrayList.get(i));
            personArray.get(i).addVehicle(vehicleArrayList.get(vehicleArrayList.size() - i - 1));
        }

        //^^ pojazdy przypisywane są do właścicieli

        service1.parkingSpaces[0].simplePark(personArray.get(1), 7);
        service1.parkingSpaces[1].simplePark(personArray.get(2), 21);
        service1.parkingSpaces[2].simplePark(personArray.get(3), 31);

        //^^ pojazdy wstawiane sa na miejsca parkingowe

        service1.serviceSpots[0].simpleRepair(personArray.get(2), 0);
        service1.serviceSpots[1].simpleRepair(personArray.get(3), 1);
        service1.serviceSpots[2].simpleRepair(personArray.get(4), 0);
        service1.serviceSpots[3].simpleRepair(personArray.get(5), 1);

        //^^ pojazdy wstawiane sa na miesca naprawcze

        service1.addToRepairQue(personArray.get(1).ownedVehicles.get(1),1);

        // ^^ pojazd jest wstawiany do kolejki oczekujacej na serwis


        String repairLog = "";
        boolean endTheProgram = false;
        int menu;
        int login = -1;
        boolean noFreeConsumerWarehouse = true;
        System.setProperty("1", "true");
        Scanner scanner = new Scanner(System.in);
        String listaOsob = "";
        String name;
        double area;
        double engineDisplacment;
        int day;
        int month = 0;
        int year;
        int engine;
        boolean sideCar;
        double clearance;
        double wadingDepth;
        int co2emision;
        for (int i = 0; i < personArray.size(); i++) {
            listaOsob += personArray.get(i) + "\n";
        }

        //^^ deklaruje i wstepnie inicjuje zmienne potrzebne do dzialania programu

        while (!endTheProgram) {

            System.out.println("Wybierz co chcesz zrobic wpisujac nr z listy");
            System.out.println("1. by zalogowac sie jako wybrana osoba");
            System.out.println("2. by wypisac swoje dane i wynajete pomieszczenia");
            System.out.println("3. by zobaczyc wolne pomieszczenia");
            System.out.println("4. by wynajac pomieszczenie");
            System.out.println("5. by wybrac osobe a nastepnie sprawdzic co znajduje sie w wynajmowanym przez nia pomieszczeniu");
            System.out.println("6. by zaparkowac nowy pojazd lub schowac kolejny przedmiot do magazynu");
            System.out.println("7. by zwolnic miejsce parkingowe lub wyciagnac przedmiot z magazynu");
            System.out.println("8. by rozpoczac zgloszenie serwisowe(naprawa moze byc wykonana przez serwis lub samodzielnie)");
            System.out.println("9. by zapisac aktualny stan do plikow");
            System.out.println("0. by zakonczyc program");

            menu = scanner.nextInt();

            if (login != -1 || menu == 1 || menu == 9 || menu == 0 || menu == 3 || menu == 5) {
                switch (menu) {
                    case 0:
                        endTheProgram = true;
                        ts.stop();
                        break;
                    case 1:
                        System.out.println("Id Imie\tNazwisko\tPesel");
                        System.out.println(listaOsob);
                        System.out.println("Wybierz Id osoby jak ktora chcesz sie zalogowac");
                        login = scanner.nextInt();
                        while (login < 0 || login > personArray.size() - 1) {
                            System.out.println("wybierz liczbe z zakresu od 0 do " + (personArray.size() - 1));
                            login = Integer.parseInt(scanner.next());
                        }
                        System.out.println("Zalogowano jako " + personArray.get(login).toString());
                        break;
                    case 2:
                        System.out.println("\n" + personArray.get(login).toString() + '\n');

                        for (int i = 0; i < service1.consumerWarehouses.size(); i++) {
                            if (!service1.consumerWarehouses.get(i).withPermission.isEmpty()) {
                                if (service1.consumerWarehouses.get(i).withPermission.get(0).pesel.equals(personArray.get(login).pesel)) {
                                    System.out.println(service1.consumerWarehouses.get(i).warehouseDiscription());
                                }
                            }
                        }
                        System.out.println();
                        break;
                    case 3:
                        System.out.println();
                        for (ConsumerWarehouse consumerWarehouse : service1.consumerWarehouses) {
                            if (!consumerWarehouse.isRented()) {
                                System.out.println(consumerWarehouse.warehouseBeforRentInfo());
                                noFreeConsumerWarehouse = false;
                            }
                        }
                        if (noFreeConsumerWarehouse)
                            System.out.println("Aktualnie wszystkie magazyny sa wynajete\n");
                        else
                            System.out.println("\nPowyzej mozesz zobaczyc magazyny ktore mozna wynajac\n");
                        noFreeConsumerWarehouse = true;
                        break;
                    case 4:
                        personArray.get(login).checkIfProblematic();
                        System.out.println();
                        for (ConsumerWarehouse consumerWarehouse : service1.consumerWarehouses)
                            if (!consumerWarehouse.isRented()) {
                                System.out.println(consumerWarehouse.warehouseBeforRentInfo());
                                noFreeConsumerWarehouse = false;
                            }
                        if (noFreeConsumerWarehouse)
                            System.out.println("Aktualnie wszystkie magazyny sa wynajete\n");
                        else {

                            System.out.println("\nWybierz nr magazynu, ktorych chcesz wynajac");
                            int rentWarehouseIndex = scanner.nextInt();
                            System.out.println("Na ile dni chcesz wynajac magazyn?");
                            day = scanner.nextInt();
                            if (service1.consumerWarehouses.get(rentWarehouseIndex).rentConsumerWarehouse(personArray.get(login), day))
                            if (personArray.get(login).canAford(service1.consumerWarehouses.get(rentWarehouseIndex).rentPerDay)) {
                                System.out.println("\nwynajolej magazyn nr " + rentWarehouseIndex + " na "
                                        + service1.consumerWarehouses.get(rentWarehouseIndex).howLongRent() + " dni, caly wynajem bedzie cie kosztowac "
                                        + service1.consumerWarehouses.get(rentWarehouseIndex).rentPerDay * (service1.consumerWarehouses.get(rentWarehouseIndex).howLongRent()) + "zl\n");
                            }
                        }
                        noFreeConsumerWarehouse = true;
                        break;
                    case 5:
                        System.out.println(listaOsob);
                        System.out.println("wybierz id osoby ktorej magazyny chcesz przejrzec");
                        int tmpLogin = scanner.nextInt();
                        String warehouseLog = "";
                        for (ConsumerWarehouse consumerWarehouse : service1.consumerWarehouses) {
                            if (!consumerWarehouse.withPermission.isEmpty())
                            if (consumerWarehouse.withPermission.get(0).id == tmpLogin)
                                warehouseLog += consumerWarehouse.consumerWarehouseContent();
                        }
                        if (!warehouseLog.isEmpty()) {
                            System.out.println(personArray.get(tmpLogin).toString() + " ma wynajete nastepujace magazyny:");
                            for (int i = 0; i < service1.consumerWarehouses.size(); i++)
                                if (service1.consumerWarehouses.get(i).withPermission.get(0).id == tmpLogin)
                                    System.out.println(service1.consumerWarehouses.get(i).warehouseDiscription() + ". W nim sa " + service1.consumerWarehouses.get(i).items);
                        } else
                            System.out.println(personArray.get(tmpLogin).toString() + " nie posiada zadnego wynajetego magazynu");
                        System.out.println();
                        break;
                    case 6:
                        System.out.println("\n1. by zaparkowac nowy pojazd\n2. by schowac nowy przedmiot do magazynu");
                        switch (scanner.next()) {
                            case "1" -> {
                                System.out.println("wybierz jaki pojazd chcesz dodac\n1. by dodac motocykl\n2. by dodac samochod miejski\n3. by dodac samochod terenowy\n4. by dodac amfibie");
                                switch (scanner.next()) {
                                    case "1" -> {
                                        System.out.println("Wprowadz nazwe motocykla");
                                        name = scanner.next();
                                        System.out.println("Wprowadz powiezchnie jaka zajmuje");
                                        area = Double.parseDouble(scanner.next());
                                        System.out.println("Wprowadz pojemnosc silnika");
                                        engineDisplacment = Double.parseDouble(scanner.next());
                                        System.out.println("Wprowadz dzien w ktorym motocykl byl wyprodukowany");
                                        day = Integer.parseInt(scanner.next());
                                        System.out.println("Wprowadz miesiac");
                                        month = Integer.parseInt(scanner.next());
                                        System.out.println("Wprowadz rok");
                                        year = Integer.parseInt(scanner.next());
                                        System.out.println("Wybierz:\n1. jezeli twoj motocykl ma silnik spalinowy\n2. jezeli ma silnik diesel\n3. jezeli ma silnik elektryczny");
                                        engine = Integer.parseInt(scanner.next());
                                        System.out.println("czy twoj motocykl ma kosz(wpisz 1 lub 0)");
                                        sideCar = Boolean.getBoolean(scanner.next());
                                        personArray.get(login).addVehicle(
                                                new MotorCycle(name, area, engineDisplacment, LocalDate.of(year, month, day),
                                                        engine == 1 ? EngineType.PETROL_ENGINE : engine == 2 ? EngineType.DIESEL_ENGINE : EngineType.ELECTRICAL_ENGINES, sideCar
                                                )
                                        );
                                        vehicleArrayList.add(personArray.get(login).ownedVehicles.get(personArray.get(login).ownedVehicles.size() - 1));
                                        System.out.println("Masz nowy motocykl " + name + " zajmujacy " + area + " o pojemnosci silnika " + engineDisplacment + " zostal wyprodukowany " + day + '.' + month + '.' + year + " ma silnik " +
                                                (engine == 1 ? EngineType.PETROL_ENGINE : engine == 2 ? EngineType.DIESEL_ENGINE : EngineType.ELECTRICAL_ENGINES) + (sideCar ? " i ma wozek boczny" : " i nie ma wozka bocznego") + '\n');
                                    }
                                    case "2" -> {
                                        System.out.println("Wprowadz nazwe samochodu miejskiego");
                                        name = scanner.next();
                                        System.out.println("Wprowadz powiezchnie jaka zajmuje");
                                        area = Double.parseDouble(scanner.next());
                                        System.out.println("Wprowadz pojemnosc silnika");
                                        engineDisplacment = Double.parseDouble(scanner.next());
                                        System.out.println("Wprowadz dzien w ktorym samochod byl wyprodukowany");
                                        day = Integer.parseInt(scanner.next());
                                        System.out.println("Wprowadz miesiac");
                                        month = Integer.parseInt(scanner.next());
                                        System.out.println("Wprowadz rok");
                                        year = Integer.parseInt(scanner.next());
                                        System.out.println("Wybierz:\n1. jezeli twoj samochod ma silnik spalinowy\n2. jezeli ma silnik diesel\n3. jezeli ma silnik elektryczny");
                                        engine = Integer.parseInt(scanner.next());
                                        System.out.println("Wprowadz emisje co2 samochodu");
                                        co2emision = Integer.parseInt(scanner.next());
                                        personArray.get(login).addVehicle(
                                                new CityCar(name, area, engineDisplacment, LocalDate.of(year, month, day),
                                                        engine == 1 ? EngineType.PETROL_ENGINE : engine == 2 ? EngineType.DIESEL_ENGINE : EngineType.ELECTRICAL_ENGINES,
                                                        co2emision
                                                )
                                        );
                                        vehicleArrayList.add(personArray.get(login).ownedVehicles.get(personArray.get(login).ownedVehicles.size() - 1));
                                        System.out.println("Masz nowy samochod miejski " + name + " zajmujacy " + area + " o pojemnosci silnika " + engineDisplacment + " zostal wyprodukowany " + day + '.' + month + '.' + year + " ma silnik " +
                                                (engine == 1 ? EngineType.PETROL_ENGINE : engine == 2 ? EngineType.DIESEL_ENGINE : EngineType.ELECTRICAL_ENGINES) + " a jego emisja CO2 to "+co2emision+"g/100KM\n");
                                    }
                                    case "3" -> {
                                        System.out.println("Wprowadz nazwe samochodu terenowego");
                                        name = scanner.next();
                                        System.out.println("Wprowadz powiezchnie jaka zajmuje");
                                        area = Double.parseDouble(scanner.next());
                                        System.out.println("Wprowadz pojemnosc silnika");
                                        engineDisplacment = Double.parseDouble(scanner.next());
                                        System.out.println("Wprowadz dzien w ktorym samochod byl wyprodukowany");
                                        day = Integer.parseInt(scanner.next());
                                        System.out.println("Wprowadz miesiac");
                                        month = Integer.parseInt(scanner.next());
                                        System.out.println("Wprowadz rok");
                                        year = Integer.parseInt(scanner.next());
                                        System.out.println("Wybierz:\n1. jezeli twoj samochod ma silnik spalinowy\n2. jezeli ma silnik diesel\n3. jezeli ma silnik elektryczny");
                                        engine = Integer.parseInt(scanner.next());
                                        System.out.println("Wprowadz przeswit swojego samochodu terenowego");
                                        clearance = Double.parseDouble(scanner.next());
                                        personArray.get(login).addVehicle(
                                                new OffRoadVehicle(name, area, engineDisplacment, LocalDate.of(year, month, day),
                                                        engine == 1 ? EngineType.PETROL_ENGINE : engine == 2 ? EngineType.DIESEL_ENGINE : EngineType.ELECTRICAL_ENGINES,
                                                        clearance)
                                        );
                                        vehicleArrayList.add(personArray.get(login).ownedVehicles.get(personArray.get(login).ownedVehicles.size() - 1));
                                        System.out.println("Masz nowy samochod terenowy " + name + " zajmujacy " + area + " o pojemnosci silnika " + engineDisplacment + " zostal wyprodukowany " + day + '.' + month + '.' + year + " ma silnik " +
                                                (engine == 1 ? EngineType.PETROL_ENGINE : engine == 2 ? EngineType.DIESEL_ENGINE : EngineType.ELECTRICAL_ENGINES) + " a jego przeswit to " + clearance+'\n');
                                    }
                                    case "4" -> {
                                        System.out.println("Wprowadz nazwe amfibii");
                                        name = scanner.next();
                                        System.out.println("Wprowadz powiezchnie jaka zajmuje");
                                        area = Double.parseDouble(scanner.next());
                                        System.out.println("Wprowadz pojemnosc silnika");
                                        engineDisplacment = Double.parseDouble(scanner.next());
                                        System.out.println("Wprowadz dzien w ktorym amfibia byl wyprodukowany");
                                        day = Integer.parseInt(scanner.next());
                                        System.out.println("Wprowadz miesiac");
                                        month = Integer.parseInt(scanner.next());
                                        System.out.println("Wprowadz rok");
                                        year = Integer.parseInt(scanner.next());
                                        System.out.println("Wybierz:\n1. jezeli twoj amfibia ma silnik spalinowy\n2. jezeli ma silnik diesel\n3. jezeli ma silnik elektryczny");
                                        engine = Integer.parseInt(scanner.next());
                                        System.out.println("Wprowadz glebokosc zanuzenia swojej amfibii");
                                        wadingDepth = Double.parseDouble(scanner.next());
                                        personArray.get(login).addVehicle(
                                                new Amphibian(name, area, engineDisplacment, LocalDate.of(year, month, day),
                                                        engine == 1 ? EngineType.PETROL_ENGINE : engine == 2 ? EngineType.DIESEL_ENGINE : EngineType.ELECTRICAL_ENGINES,
                                                        wadingDepth)
                                        );
                                        vehicleArrayList.add(personArray.get(login).ownedVehicles.get(personArray.get(login).ownedVehicles.size() - 1));
                                        System.out.println("Masz nowa amfibie " + name + " zajmujacy " + area + " o pojemnosci silnika " + engineDisplacment + " zostal wyprodukowany " + day + '.' + month + '.' + year + " ma silnik " +
                                                (engine == 1 ? EngineType.PETROL_ENGINE : engine == 2 ? EngineType.DIESEL_ENGINE : EngineType.ELECTRICAL_ENGINES) + " jej glebokosc zanurzenia to " + wadingDepth+'\n');
                                    }
                                    default -> System.out.println("nastepnym razem wybierz nr z listy");
                                }
                            }
                            case "2" -> {
                                boolean haveAcces = false;
                                day = 0;
                                for (int i = 0; i < service1.consumerWarehouses.size(); i++) {
                                    for (Person person : service1.consumerWarehouses.get(i).withPermission) {
                                        if (person.id == personArray.get(login).id) {
                                            haveAcces = true;
                                            day++;
                                            if (haveAcces && day == 1) {
                                                System.out.println("\nMasz prawo kozystania z magazynow:");
                                            }
                                            System.out.println(i+". by wybrac "+service1.consumerWarehouses.get(i).warehouseDiscription());
                                        }
                                    }
                                }
                                if (haveAcces) {
                                    System.out.println("\nWybierz id magazynu do ktorego chcesz cos schowac");
                                    int warehouseID = scanner.nextInt();
                                    System.out.println("\nWprowadz nazwe przedmiotu");
                                    String itemName = scanner.next();
                                    System.out.println("\nWprowadz powierzchnie przedmiotu");
                                    double itemArea = scanner.nextDouble();
                                    System.out.println();
                                    service1.consumerWarehouses.get(warehouseID).insertItem(new Item(itemName, itemArea));
                                    System.out.println();
                                }else {
                                    System.out.println("aby schowac cos do magazynu musisz miec co najmniej 1 wynajety badz musisz miec dostep do czyjegos magazynu\n");
                                }
                            }
                            default -> System.out.println("Nastepnym razem wybierz liczbe z listy");
                        }
                        break;
                    case 7:
                        System.out.println("\n1. by zwolnic miejsce parkingowe\n2. by wyciagnac przedmiot z magazynu");
                        switch (scanner.nextInt()) {
                            case 1:
                                if (!personArray.get(login).ownedVehicles.isEmpty()) {
                                    System.out.println("\ntwoje zaparkowane pojazdy to:");
                                    for (int i = 0; i < service1.parkingSpaces.length; i++) {
                                        for (Vehicle vehicle : personArray.get(login).ownedVehicles) {
                                            if (service1.parkingSpaces[i].id == vehicle.id) {
                                                System.out.println(i + " " + vehicle.name);
                                            }
                                        }
                                    }
                                    System.out.println("\nWybierz numer pojazdu ktory chcesz usunac z miejsca parkingowego");
                                    service1.parkingSpaces[scanner.nextInt()] = new ParkingSpace();
                                    System.out.println("\nMiejsce parkingowe zostalo zwolnione\n");
                                } else {
                                    System.out.println("\nnie posiadasz zadengo pojazdu\n");
                                }
                                break;
                            case 2:
                                boolean haveAcces = false;
                                for (ConsumerWarehouse consumerWarehouse : service1.consumerWarehouses) {
                                    for (Person person : consumerWarehouse.withPermission) {
                                        if (person.id == login)
                                            haveAcces = true;
                                    }
                                }
                                if (haveAcces) {
                                    System.out.println("Masz dostep do nastepujacych magazynow:");
                                    for (ConsumerWarehouse consumerWarehouse : service1.consumerWarehouses) {
                                        for (Person person : consumerWarehouse.withPermission) {
                                            if (person.id == login)
                                                System.out.println(consumerWarehouse);
                                        }
                                    }
                                    System.out.println("\nWybierz ten z ktorego chcesz cos wyjac");
                                    day = scanner.nextInt();
                                    if (!service1.consumerWarehouses.get(day).items.isEmpty()) {
                                        for (int i = 0; i < service1.consumerWarehouses.get(day).items.size(); i++) {
                                            System.out.println(i + " " + service1.consumerWarehouses.get(day).items.get(i).name);
                                        }
                                        System.out.println("\nWpisz nr przedmiotu ktory chcesz wyjac");
                                        month = scanner.nextInt();
                                        System.out.println(service1.consumerWarehouses.get(day).items.get(month).toString() + " zostal wyjety z magazynu");
                                        service1.consumerWarehouses.get(day).removeItem(month);
                                    } else
                                        System.out.println("Magazyn jest pusty");
                                } else
                                    System.out.println("\nNie masz dostepu do zadnego magazynu");
                                System.out.println();
                                break;
                            default:
                                System.out.println("\nWybierz poprawna liczbe\n");
                        }
                        break;
                    case 8:
                        personArray.get(login).checkIfProblematic();
                        System.out.println("\n1. by zlecic naprawe do serwisu\n2. by wynajac miejsce do naprawy samodzielnej");
                        switch (scanner.nextInt()) {
                            case 1 -> {
                                System.out.println("\nWybierz ktory pojazd chcesz naprawic:" + personArray.get(login).ownedVehicles.toString() + '\n');
                                boolean freeCarServiceSpot = false;
                                day = scanner.nextInt();
                                for (int i = 0; i < service1.serviceSpots.length; i++) {
                                    if (service1.serviceSpots[i].vehicleInRepairProcess == null) {
                                        freeCarServiceSpot = true;
                                        month = i;
                                        break;
                                    }
                                }
                                if (freeCarServiceSpot) {
                                    service1.serviceSpots[month].simpleRepair(personArray.get(login), day);
                                    if (personArray.get(login).canAford(service1.serviceSpots[month].repairCost())) {
                                        System.out.println("Twoj samochod bedzie mozna odebrac za " +
                                                service1.serviceSpots[month].howLongRepair() + " dni a usluga bedzie kosztowac " +
                                                service1.serviceSpots[month].repairCost());
                                    } else
                                        service1.serviceSpots[month].reversSimpleRepair();
                                } else {
                                    System.out.print("Aktualnie serwis jest zajety");
                                    if (service1.freeParkingSpace()) {
                                        System.out.println(" ale twoj samochod zostanie wstawiony na miejsce parkingowe gdzie bedzie czekac na naprawe\n");
                                        for (ParkingSpace parkingSpace : service1.parkingSpaces) {
                                            if (parkingSpace.parkedVehicle == null) {
                                                service1.addToRepairQue(personArray.get(login).ownedVehicles.get(day), day);
                                                break;
                                            }
                                        }
                                    } else
                                        System.out.println(" i nie posiada nawet wolnych miejsc parkingowych.\n");
                                }
                            }
                            case 2 -> {
                                boolean freeSelfRepairSpot = false;
                                day = 0;
                                for (IndependentCarServiceSpot independentCarServiceSpot : service1.independentCarServiceSpots) {
                                    if (independentCarServiceSpot.vehicleInRepairProcess == null) {
                                        freeSelfRepairSpot = true;
                                        break;
                                    }
                                    day++;
                                }
                                if (freeSelfRepairSpot) {
                                    System.out.println("\nWybierz pojazd ktory chcesz naprawiac");
                                    System.out.println(personArray.get(login).ownVehicleList());
                                    month = scanner.nextInt();
                                    System.out.println("Na ile dni chcesz wynajac miejsce naprawcze?");
                                    year = scanner.nextInt();
                                    if (personArray.get(login).canAford(service1.independentCarServiceSpots[day].repairCost() * year)) {
                                        service1.independentCarServiceSpots[day].simpleRepair(personArray.get(login), month - 1, year);
                                        System.out.println("\nWynajecie samodzielnego miejsca naprawczego na "
                                                + service1.serviceSpots[day].howLongRepair() + " dni bedzie cie kosztowac " + service1.serviceSpots[day].repairCost() + "zl\n");
                                    }
                                } else
                                    System.out.println("Aktualnie w serwisie nie ma wolnych miejsc do samodzielnej naprawy");
                            }
                        }
                        break;
                    case 9:
                        try {
                            FileWriter fwWarehouses = new FileWriter("warehouses.txt");

                            ArrayList<ConsumerWarehouse> tmpConsumerWarehouses = service1.consumerWarehouses;
                            tmpConsumerWarehouses.sort(Comparator.comparing(Warehouse::getArea));
                            fwWarehouses.append(service1.serviceWarehous.toString() + '\n');
                            fwWarehouses.append(service1.tiresWarehouse.toString() + '\n');
                            fwWarehouses.append(tmpConsumerWarehouses.toString() + '\n');
                            fwWarehouses.flush();
                            fwWarehouses.close();


                            FileWriter fwService = new FileWriter("services.txt");
                            fwService.write(service1.toString());
                            fwService.flush();
                            fwService.close();

                            FileWriter fwPersons = new FileWriter("clients.txt");
                            fwPersons.append(personArray.toString());
                            fwPersons.flush();
                            fwPersons.close();

                            FileWriter fwRepairLog = new FileWriter("repair log.txt");
                            fwRepairLog.append(service1.serviceSpots[0].getReapirLog());
                            fwRepairLog.flush();
                            fwRepairLog.close();

                            FileWriter fwPersonList = new FileWriter("person list.txt");
                            fwPersonList.write(personArray.toString());
                            fwPersonList.flush();
                            fwPersonList.close();

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println("\nStan aktualny stan zostal zapisany\n");
                        break;
                    default:
                        System.out.println("\nWprowadz poprawna liczbe(od 0 do 9)\n");
                        break;
                }
            } else
                System.out.println("\nZanim cokolwiek zrobisz musisz sie zalogowac. Urzyj opcji pod nr 1 w glownym menu\n");
        }
    }
    public static Service giveService(Service s){
        return s;
    }
}