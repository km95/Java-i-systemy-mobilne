import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FulfillmentCenter {
    String name;
    List<Item> listOfProducts = new ArrayList<Item>();
    double maximumCapacity;



    public FulfillmentCenter(String name, double maximumCapacity) {
        this.name = name;
        this.maximumCapacity = maximumCapacity;
    }

    public void addProduct(Item I) {
        int capacity = 0;

        for (Item i : listOfProducts) {
            capacity += i.quantity;
        }

        if (capacity + I.quantity <= maximumCapacity) {
            for (Item i : listOfProducts) {
                if (i.compareTo(I) == 0) {
                    i.addQuantity(I.quantity);
                    return;
                }
            }
            listOfProducts.add(I);
        } else {
            System.err.println("Przekroczona pojemność magazynu !!!");
        }
    }
    public Item getItem(int i) {
                if (!listOfProducts.isEmpty()){
                return listOfProducts.get(i-1);
                }
                return null;

    }
    public void getProduct(Item I) {
        for (Item i : listOfProducts) {
            if (i.compareTo(I) == 0) {
                if (i.quantity >= I.quantity) {
                    i.subtractionQuantity(I.quantity);
                    if (i.quantity == 0) {
                        listOfProducts.remove(i);
                    }
                    return;
                } else {
                    System.err.println("Nie ma tyle w magazynie");
                }
            }
        }
    }

    public void removeProduct(Item I) {
        for (Item i : listOfProducts) {
            if(i.compareTo(I) == 0){
                listOfProducts.remove(i);
                return; } } }
    public String search(String name){
        for (Item i:listOfProducts) {
            if (i.compareTo(name) == 0) {
                return i.Print();
            }
        }
        return "brak wyniku";
    }
    public void searchPartial(String name){
        System.out.println( "Wyszukane...");
        for (Item i:listOfProducts) {
            if(i.name.contains(name)) {
                i.PrintV();
            }
        }


    }
    public void countByCondition(Item.ItemCondition I){
        System.out.println( "Wyszukane...");
        for (Item i:listOfProducts) {
            if (i.compareTo(I) == 0) {
                i.PrintV();
            }
        }
    }

    public void sortByName(){
        Collections.sort(listOfProducts);
    }
    public void sortByAmount(){
        Collections.sort(listOfProducts,new Komparator());
    }
    public String max(){
        Item max = Collections.max(listOfProducts);
        return max.Print();
    }
    public void summary() {
        for (Item i : listOfProducts) {
            System.out.println(i.Print());
        }
    }
    public double freeSpace(){
        int capacity = 0;
        for (Item i : listOfProducts) {
            capacity += i.quantity;
        }

        return (capacity/maximumCapacity)*100;
    }

    private class Komparator implements Comparator<Item> {

        public int compare(Item o1, Item o2) {
            int quantity = o2.quantity - o1.quantity;
            if (quantity == 0) {
               return o1.compareTo(o2);
            }
            return quantity;
        }
    }

}
