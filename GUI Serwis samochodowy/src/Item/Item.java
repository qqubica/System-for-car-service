package Item;

import java.util.Scanner;

public class Item {
    public String name;
    public double surface;

    public Item(String name, double surface) {
        this.name = name;
        while (surface<=0){
            System.out.print("Powieżchnia przedmiotu% musi byc wieksza od 0\nWprowadz powierzchnie przedmiotu ponownie:");
            Scanner scanner=new Scanner(System.in);
            surface=Double.parseDouble(scanner.next());
        }
        this.surface = surface;
    }

    public Item(String name, double width, double length) {
        this.name = name;
        while (width*length<0&& width<=0 && length<=0){
            System.out.println("Powieżchnia przedmiotu musi byc wieksza od 0 a dlugosc i szerokosc dodatnia\nWprowadz dlugosc przedmiotu ponownie:");
            Scanner scanner=new Scanner(System.in);
            width=Double.parseDouble(scanner.next());
            System.out.print("Wprowadz szerokosc przedmiotu ponownie:");
            length=Double.parseDouble(scanner.next());
        }
        this.surface = width * length;
    }

    @Override
    public String toString() {
        return name +" zajmujacy " + surface +
                " powiezchni";
    }

    public double getSurface() {
        return surface;
    }

    public String getName() {
        return name;
    }
}
