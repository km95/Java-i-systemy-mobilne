import org.junit.Test;

import static org.junit.Assert.*;

public class KomparatorTest {
    @Test
    public void compare() throws Exception {
        FulfillmentCenter cont = new FulfillmentCenter("Krakow",1000);
        Item it1 = new Item("Zamek", Item.ItemCondition.NEW,2.2,10);
        Item it3 = new Item("Spodnie", Item.ItemCondition.NEW,2.2,100);
        cont.addProduct(it1);
        cont.addProduct(it3);
        cont.sortByAmount();
        assertEquals(it3,cont.getItem(1));

    }

}