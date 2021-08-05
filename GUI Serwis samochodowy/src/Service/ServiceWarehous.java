package Service;

import Item.Item;

import java.util.ArrayList;
import java.util.Comparator;

public class ServiceWarehous extends Warehouse {
    ArrayList<Item> items;
    static int autoCounter = 0;
    public int id;

    public ServiceWarehous(double area) {
        super(area);
        this.items = new ArrayList<Item>();
        id=autoCounter++;
    }

    public ServiceWarehous(double width, double length) {
        super(width * length);
        this.items = new ArrayList<Item>();
        id=autoCounter++;
    }

    @Override
    public String toString() {
        checkFreeArea();
        items.sort(Comparator.comparing(Item::getSurface).reversed());
        return "Service warehouse id "+id+" with area = "+area+", area taken = "+areaTaken+
                (items.isEmpty()?", curantly there is nothing inside":items.toString())+
                ". People with permission to use the warehouse "+withPermission.toString();
    }
    public void checkFreeArea() {
        areaTaken = 0;
        for (Item item : items) {
            areaTaken += item.surface;
        }
    }
}
