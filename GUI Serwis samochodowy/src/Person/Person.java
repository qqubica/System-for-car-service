package Person;

import Exception.Info;
import Exception.NeverRentException;
import Exception.ProblematicTenantException;
import Service.ConsumerWarehouse;
import Service.Service;
import Vehicle.Vehicle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Person {
    String name;
    String surname;
    public String pesel;
    String adres;
    LocalDate birthday;
    public LocalDate firstRent;
    public ArrayList<Vehicle> ownedVehicles;
    public double monthlyPayment;
    public double debt;
    public ArrayList<Info> infoList;
    public int id;
    static int autoCount = 0;

    public Person(String name, String surname, String pesel, String adres, LocalDate birthday) {
        this.name = name;
        this.surname = surname;
        while (pesel.length() != 11) {
            System.out.print(name + " " + surname + " prowadz swoj nr pesel ponownie(powinien skladac sie z 11 cyft):");
            Scanner scanner = new Scanner(System.in);
            pesel = scanner.next();
        }
        this.pesel = pesel;
        this.adres = adres;
        this.birthday = birthday;
        firstRent = null;
        ownedVehicles = new ArrayList<Vehicle>();
        infoList = new ArrayList<Info>();
        monthlyPayment=0;
        id = autoCount++;
    }

    public Person(String name, String surname, String pesel, String adres, LocalDate birthday, LocalDate firstRent) {

        this.name = name;
        this.surname = surname;
        while (pesel.length() != 11) {
            System.out.print(name + " " + surname + " prowadz swoj nr pesel ponownie(powinien skladac sie z 11 cyft):");
            Scanner scanner = new Scanner(System.in);
            pesel = scanner.next();
        }
        this.pesel = pesel;
        this.adres = adres;
        this.birthday = birthday;
        this.firstRent = firstRent;
        ownedVehicles = new ArrayList<Vehicle>();
        monthlyPayment=0;
        infoList = new ArrayList<Info>();
        id = autoCount++;
    }

    public void addVehicle(Vehicle v) {
        v.vehicleOwner=this;
        ownedVehicles.add(v);
    }

    public void checkIfProblematic()throws ProblematicTenantException{
        if (infoList.size()>3){
            try {
                throw new ProblematicTenantException();
            }catch (Throwable e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void addInfo(Info info){
        infoList.add(info);
    }

    public double debtCounter(double debt){
        infoList.add(new Info());
        return this.debt+=debt;
    }

    public boolean canAford(double price) {
        if (monthlyPayment+price<=1250) {
            monthlyPayment+=price;
            return true;
        } else {
            System.out.println("your monthly rent exceds 1250zl");
            return false;
        }
    }

    public LocalDate getFirstRent() throws NeverRentException{
        if (firstRent==null) {
            try {
                throw new NeverRentException();
            } catch (NeverRentException e) {
                System.out.println(e.getMessage());
            }
        }
        return firstRent;
    }

    public String toString() {
        String toString="person id "+id+ " name "+ name + " surname " + surname + " pesel " + pesel+" birthday "+birthday;
        try {
            toString+= " first rent "+getFirstRent() +" your monthly cost of rent "+monthlyPayment;
        } catch (NeverRentException e) {
            System.out.println(e.getMessage());
        }
        toString+= " owned vehicles "+ownedVehicles.toString();
        return toString;
    }

    public String allPersonalData(){
        return id+ " "+name+" "+surname+" "+pesel+" "+birthday+" \n"+ownedVehicles.toString();
    }

    public String spacesList(Service service){
        String spacesList= "";
        for (ConsumerWarehouse consumerWarehouse : service.consumerWarehouses) {
            for (Person persons : consumerWarehouse.withPermission) {
                if(id==persons.id){
                    spacesList+=consumerWarehouse.toString();
                }
            }
        }
        return spacesList;
    }

    public String ownVehicleList(){
        String tmp= "";
        for (int i = 0;i<ownedVehicles.size();i++){
            tmp+=i+1+" " + ownedVehicles.get(i).name;
            if (i+i<ownedVehicles.size())
                tmp+=", ";
        }
        return tmp;
    }

    public String nameAndSurname(){
        return name+" "+surname;
    }

}
