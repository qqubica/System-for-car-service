package Person;

import Service.Service;

import java.time.LocalDate;
import java.util.ArrayList;


public class Owner extends Person {
    public ArrayList<Service> services;

    public Owner(String name, String surname, String pesel, String adres, LocalDate birthday) {
        super(name, surname, pesel, adres, birthday);
        services = new ArrayList<Service>();
    }
    public String allPersonalData(){
        return super.allPersonalData() + "\n"+services.toString();
    }
}
