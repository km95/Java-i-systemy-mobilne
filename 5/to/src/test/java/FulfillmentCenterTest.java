import org.junit.Test;

import static org.junit.Assert.*;

public class FulfillmentCenterTest {

    @Test ( expected = IllegalArgumentException.class)
    public void addProduct_error() throws Exception {
        FulfillmentCenter cont = new FulfillmentCenter("Krakow",1000);
                Item it3 = new Item("Spodnie", Item.ItemCondition.NEW,2.2,-100);
    }
    @Test
    public void addProduct() throws Exception {
        FulfillmentCenter cont = new FulfillmentCenter("Krakow",1000);
        Item it = new Item("Bluzy", Item.ItemCondition.NEW,2.2,100);
        Item it2 = new Item("Spodnie", Item.ItemCondition.NEW,2.2,100);

        cont.addProduct(it);
        cont.addProduct(it2);
        assertEquals(it,cont.getItem(1));
        assertEquals(it2,cont.getItem(2));
        cont.addProduct(it);

        assertEquals(200,cont.getItem(1).quantity);
    }
    @Test
    public void getItem() throws Exception {
        FulfillmentCenter cont = new FulfillmentCenter("Krakow",1000);
        Item it = new Item("Bluzy", Item.ItemCondition.NEW,2.2,100);
        cont.addProduct(it);
        assertEquals(it,cont.getItem(1));

    }
    @Test
    public void getProduct() throws Exception {
        FulfillmentCenter cont = new FulfillmentCenter("Krakow",1000);
        Item it = new Item("Bluzy", Item.ItemCondition.NEW,2.2,100);
        Item it2 = new Item("Bluzy", Item.ItemCondition.NEW,2.2,200);
        cont.addProduct(it);
        assertEquals(it,cont.getItem(1));
        cont.getProduct(it);
        assertEquals(null,cont.getItem(1));
        cont.addProduct(it);
        cont.getProduct(it2);

    }

    @Test
    public void removeProduct() throws Exception {
        FulfillmentCenter cont = new FulfillmentCenter("Krakow",1000);
        Item it = new Item("Bluzy", Item.ItemCondition.NEW,2.2,100);
        cont.addProduct(it);
        assertEquals(it,cont.getItem(1));
        cont.removeProduct(it);
        assertEquals(null,cont.getItem(1));
    }

    @Test
    public void search() throws Exception {
        FulfillmentCenter cont = new FulfillmentCenter("Krakow",1000);
        Item it = new Item("Bluzy", Item.ItemCondition.NEW,2.2,100);
        cont.addProduct(it);
        assertEquals(it.Print(),cont.search("Bluzy"));
        assertEquals("brak wyniku",cont.search("Bluza"));
    }

    @Test
    public void searchPartial() throws Exception {
        FulfillmentCenter cont = new FulfillmentCenter("Krakow",1000);
        Item it = new Item("Bluzy", Item.ItemCondition.NEW,2.2,100);
        cont.addProduct(it);
        cont.searchPartial("Bluz");
    }

    @Test
    public void countByCondition() throws Exception {
        FulfillmentCenter cont = new FulfillmentCenter("Krakow",1000);
        Item it = new Item("Bluzy", Item.ItemCondition.NEW,2.2,100);
        Item it2 = new Item("Spodnie", Item.ItemCondition.NEW,2.2,100);
        cont.addProduct(it);
        cont.addProduct(it2);
        cont.countByCondition(Item.ItemCondition.NEW);
    }

    @Test
    public void summary() throws Exception {
        FulfillmentCenter cont = new FulfillmentCenter("Krakow",1000);
        Item it = new Item("Bluzy", Item.ItemCondition.NEW,2.2,100);
        Item it2 = new Item("Spodnie", Item.ItemCondition.NEW,2.2,100);
        cont.addProduct(it);
        cont.addProduct(it2);
        cont.summary();
    }

    @Test
    public void sortByName() throws Exception {
        FulfillmentCenter cont = new FulfillmentCenter("Krakow",1000);
        Item it1 = new Item("Zamek", Item.ItemCondition.NEW,2.2,100);
        Item it2 = new Item("Bluzy", Item.ItemCondition.NEW,2.2,100);
        Item it3 = new Item("Spodnie", Item.ItemCondition.NEW,2.2,100);
        cont.addProduct(it1);
        cont.addProduct(it2);
        cont.addProduct(it3);
        cont.sortByName();
        assertEquals(it2,cont.getItem(1));
    }

    @Test
    public void sortByAmount() throws Exception {
        FulfillmentCenter cont = new FulfillmentCenter("Krakow",1000);
        Item it1 = new Item("Zamek", Item.ItemCondition.NEW,2.2,10);
        Item it2 = new Item("Bluzy", Item.ItemCondition.NEW,2.2,50);
        Item it3 = new Item("Spodnie", Item.ItemCondition.NEW,2.2,100);
        cont.addProduct(it1);
        cont.addProduct(it2);
        cont.addProduct(it3);
        cont.sortByAmount();
        assertEquals(it3,cont.getItem(1));
    }

    @Test
    public void max() throws Exception {
        FulfillmentCenter cont = new FulfillmentCenter("Krakow",1000);
        Item it1 = new Item("Zamek", Item.ItemCondition.NEW,2.2,200);
        Item it2 = new Item("Bluzy", Item.ItemCondition.NEW,2.2,50);
        Item it3 = new Item("Spodnie", Item.ItemCondition.NEW,2.2,100);
        cont.addProduct(it1);
        cont.addProduct(it2);
        cont.addProduct(it3);
        assertEquals(it1.Print(),cont.max());
    }



    @Test
    public void freeSpace() throws Exception {
        FulfillmentCenter cont = new FulfillmentCenter("Krakow",1000);
        Item it3 = new Item("Spodnie", Item.ItemCondition.NEW,2.2,100);
        cont.addProduct(it3);
        assertEquals(10,cont.freeSpace(),2);
    }

}