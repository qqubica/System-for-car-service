package Service;

import Item.Tires;
import Person.Person;

import java.util.ArrayList;
import java.util.Comparator;

public class TiresWarehouse extends Warehouse {
    ArrayList<Tires> tires;
    ArrayList<Person> tiresOwner;
    int pricePerDay = 1;
    static int autoCounter = 0;
    public int id;

    public TiresWarehouse(double area) {
        super(area);
        id = autoCounter++;
        tires = new ArrayList<Tires>();
        tiresOwner = new ArrayList<Person>();
        this.id = autoCounter++;
    }

    public TiresWarehouse(double width, double length) {
        super(width * length);
        id = autoCounter++;
        tires = new ArrayList<Tires>();
        tiresOwner = new ArrayList<Person>();
    }

    @Override
    public String toString() {
        checkFreeArea();
        tires.sort(Comparator.comparing(Tires::getSurface).reversed());
        return "TiresWarehouse id " + id + ", area = " + area + ", area taken = " + areaTaken +
                (tiresOwner.isEmpty() ? ", no tires in storage now" :
                        ". People who store tires:\n" + tiresOwner + "\nTires stored:\n" + tires.toString());
    }

    public void checkFreeArea() {
        areaTaken = 0;
        for (Tires item : tires) {
            areaTaken += item.surface;
        }
    }
}
