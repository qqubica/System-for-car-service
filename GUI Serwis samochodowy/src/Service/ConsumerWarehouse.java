package Service;

import Exception.TooManyThingsException;
import Item.Item;
import Person.Person;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;

public class ConsumerWarehouse extends Warehouse {

    LocalDate beaningOfRent;
    LocalDate endOfRent;
    static int rentPerSqrM = 1;
    public ArrayList<Item> items;
    public double rentPerDay;
    static int autoCounter = 0;
    public int id;

    public ConsumerWarehouse(double area) {
        super(area);
        this.beaningOfRent = null;
        this.endOfRent = null;
        this.items = new ArrayList<Item>();
        rentPerDay = this.area * rentPerSqrM;
        this.id = autoCounter++;
    }

    public ConsumerWarehouse(double width, double length) {
        super(width * length);
        this.beaningOfRent = null;
        this.endOfRent = null;
        this.items = new ArrayList<Item>();
        rentPerDay = this.area * rentPerSqrM;
        this.id = autoCounter++;
    }

    public void endOfRent(){
        for (int i = 0; i < items.size(); i++) {
            removeItem(i);
        }
        withPermission.removeAll(withPermission);
        beaningOfRent=null;
        endOfRent=null;
    }

    public void removeItem(int i){
        items.remove(i);
    }

    public void insertItem(Item item) {
        if (areaTaken+item.surface<=area){
            System.out.println(item.name+" wlozono do magazynu");
            items.add(item);
            checkFreeArea();
        }else {
            try {
                tooManyThinksExc();
            }catch (TooManyThingsException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("ten przedmiot juz sie nie zmiesci");
        }
    }

    public void tooManyThinksExc()throws TooManyThingsException{
        try {
            throw new TooManyThingsException();
        }catch (TooManyThingsException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        Item tmpItem;
        checkFreeArea();
        items.sort(Comparator.comparingDouble(Item::getSurface).reversed());
        for (int i = 0; i < items.size()-1; i++) {
            if (items.get(i).getSurface() == items.get(i + 1).getSurface()) {
                if (items.get(i).getName().toLowerCase().toCharArray()[0] < items.get(i + 1).getName().toLowerCase().toCharArray()[0]) {
                    tmpItem = items.get(i + 1);
                    items.set(i + 1,items.get(i));
                    items.set(i,tmpItem);

                }
            }
        }
        return "Consumer Warehouse Id "+id+", area = "+area+", area taken = "+ areaTaken+", "+
                (beaningOfRent==null?"it is not rented":"it's rented from "+beaningOfRent+" until "+endOfRent)+
                (items.isEmpty()?" there is nothing inside": " " + items)+
                ". People with permision to use the warehouse "+ withPermission.toString()+
                "\n";
    }

    public String consumerWarehouseContent(){
        return items.toString();
    }

    public long howLongRent() {
        return beaningOfRent.until(endOfRent, ChronoUnit.DAYS);
    }

    public boolean rentConsumerWarehouse(Person p, int howLongRent) {
        if (p.canAford(rentPerDay*howLongRent)) {
            withPermission.add(p);
            beaningOfRent = Service.programDate;
            p.firstRent = beaningOfRent;
            endOfRent = beaningOfRent.plusDays(howLongRent);
            return true;
        }
        return false;
    }

    public void checkFreeArea() {
        areaTaken = 0;
        for (Item item : items) {
            areaTaken += item.surface;
        }
    }

    public String warehouseDiscription() {
        checkFreeArea();
        return "consumer warehouse nr " + id + " o powierzchni " + area + "m^2 z czego " + areaTaken + "m^2 jest juz zapelnione. " +
                (beaningOfRent==null?"Aktualnie nie jest wynajety ":"Jesy wynajety od "+beaningOfRent+" do " + endOfRent+ "(" + beaningOfRent.until(endOfRent, ChronoUnit.DAYS) + " dni) koszt wynajmu " + rentCost());
    }

    public String warehouseBeforRentInfo() {
        return "consumer warehouse nr " + id + " ma powierzchnie " + area + ", jego wynajem bedzie kosztowac " + rentPerDay + "zl za dzien";
    }

    public double rentCost(){
        return withPermission.get(0).debtCounter(rentPerDay * beaningOfRent.until(endOfRent, ChronoUnit.DAYS));
    }

    public boolean isRented() {
        return !withPermission.isEmpty();
    }
}
