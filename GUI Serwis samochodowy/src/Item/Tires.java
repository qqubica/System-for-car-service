package Item;

public class Tires extends Item {
    String tyreCode;
    TiresSezon tiresSezon;

    public Tires(String name, double surface, String tyreCode, TiresSezon tiresSezon) {
        super(name, surface);
        this.tyreCode = tyreCode;
        this.tiresSezon = tiresSezon;
    }

    public Tires(String name, double width, double length, String tyreCode, TiresSezon tiresSezon) {
        super(name, width, length);
        this.tyreCode = tyreCode;
        this.tiresSezon = tiresSezon;
    }
}
