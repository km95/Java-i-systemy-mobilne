import org.junit.Test;

import static org.junit.Assert.*;

public class FulfillmentCenterContainerTest {
    @Test
    public void addCenter() throws Exception {
        FulfillmentCenterContainer contenerMagazin = new FulfillmentCenterContainer();
        contenerMagazin.addCenter("Krakow",4000);
        contenerMagazin.addCenter("Warszawa",5000);
        assertEquals(2,contenerMagazin.size());
    }

    @Test
    public void getFulfillmentCenter() throws Exception {
        FulfillmentCenterContainer contenerMagazin = new FulfillmentCenterContainer();
        contenerMagazin.addCenter("Krakow",4000);
        contenerMagazin.addCenter("Warszawa",5000);
        assertEquals("Krakow",contenerMagazin.getFulfillmentCenter("Krakow").name);
    }

    @Test
    public void removeCenter() throws Exception {
        FulfillmentCenterContainer contenerMagazin = new FulfillmentCenterContainer();
        contenerMagazin.addCenter("Krakow",4000);
        contenerMagazin.addCenter("Warszawa",5000);
        int pom = contenerMagazin.size();
        contenerMagazin.removeCenter("Krakow");
        assertEquals(pom-1,contenerMagazin.size());
    }

    @Test
    public void findEmpty() throws Exception {
        FulfillmentCenterContainer contenerMagazin = new FulfillmentCenterContainer();
        System.out.println("Tworzenie magazynów :");
        contenerMagazin.addCenter("Krakow",4000);
        contenerMagazin.addCenter("Warszawa",5000);
        contenerMagazin.addCenter("Wroclaw",3000);
        contenerMagazin.addCenter("Gdansk",2000);
        contenerMagazin.addCenter("Zakopane",2000);
        contenerMagazin.getFulfillmentCenter("Krakow").addProduct(new Item("Bluzy", Item.ItemCondition.NEW,2.2,100));
        contenerMagazin.getFulfillmentCenter("Krakow").addProduct(new Item("Spodnie", Item.ItemCondition.NEW,3.2,200));
        contenerMagazin.getFulfillmentCenter("Krakow").addProduct(new Item("Skarpetki", Item.ItemCondition.NEW,0.2,50));
        contenerMagazin.getFulfillmentCenter("Krakow").addProduct(new Item("Bluzy", Item.ItemCondition.NEW,0.2,5000));
        contenerMagazin.getFulfillmentCenter("Warszawa").addProduct(new Item("Bluzy", Item.ItemCondition.USED,2.2,1000));
        contenerMagazin.getFulfillmentCenter("Warszawa").addProduct(new Item("Koszule", Item.ItemCondition.USED,3.2,2000));
        contenerMagazin.getFulfillmentCenter("Wroclaw").addProduct(new Item("Koszule", Item.ItemCondition.REFURBISHER,1.2,2500));
        contenerMagazin.getFulfillmentCenter("Wroclaw").addProduct(new Item("Skarpetki", Item.ItemCondition.REFURBISHER,0.2,400));
        System.out.println("Puste:");
        contenerMagazin.findEmpty();
    }

    @Test
    public void summary() throws Exception {
        FulfillmentCenterContainer contenerMagazin = new FulfillmentCenterContainer();
        System.out.println("Tworzenie magazynów :");
        contenerMagazin.addCenter("Krakow",4000);
        contenerMagazin.addCenter("Warszawa",5000);
        contenerMagazin.addCenter("Wroclaw",3000);
        contenerMagazin.addCenter("Gdansk",2000);
        contenerMagazin.addCenter("Zakopane",2000);
        contenerMagazin.summary();
    }

}