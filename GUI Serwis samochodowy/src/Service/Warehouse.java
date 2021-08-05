package Service;

import Person.Person;

import java.util.ArrayList;

public abstract class Warehouse {
    public ArrayList<Person> withPermission;
    public double area;
    double areaTaken;

    public Warehouse(double area) {
        this.withPermission = new ArrayList<Person>();
        this.area = area;
        this.areaTaken = 0;
    }

    public double getArea() {
        return area;
    }
}
