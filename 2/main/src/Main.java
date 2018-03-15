import javax.sound.midi.Soundbank;

public class Main {
    public static void main(String[] args) {
        FulfillmentCenterContainer contenerMagazin = new FulfillmentCenterContainer();
        System.out.println("Tworzenie magazynów :");
        contenerMagazin.addCenter("Krakow",4000);
        contenerMagazin.addCenter("Warszawa",5000);
        contenerMagazin.addCenter("Wroclaw",3000);
        contenerMagazin.addCenter("Gdansk",2000);
        contenerMagazin.addCenter("Zakopane",2000);
        contenerMagazin.summary();
        System.out.println("Usuwamy Gdansk bo za daleko");
        contenerMagazin.removeCenter("Gdansk");
        System.out.println("Dodajemy produkty do miast");
        contenerMagazin.getFulfillmentCenter("Krakow").addProduct(new Item("Bluzy", Item.ItemCondition.NEW,2.2,100));
        contenerMagazin.getFulfillmentCenter("Krakow").addProduct(new Item("Spodnie", Item.ItemCondition.NEW,3.2,200));
        contenerMagazin.getFulfillmentCenter("Krakow").addProduct(new Item("Koszule", Item.ItemCondition.NEW,1.2,500));
        contenerMagazin.getFulfillmentCenter("Krakow").addProduct(new Item("Koszulki", Item.ItemCondition.NEW,1.2,10));
        contenerMagazin.getFulfillmentCenter("Krakow").addProduct(new Item("Skarpetki", Item.ItemCondition.NEW,0.2,50));
        contenerMagazin.getFulfillmentCenter("Krakow").addProduct(new Item("Bluzy", Item.ItemCondition.NEW,0.2,5000));
        contenerMagazin.getFulfillmentCenter("Warszawa").addProduct(new Item("Bluzy", Item.ItemCondition.USED,2.2,1000));
        contenerMagazin.getFulfillmentCenter("Warszawa").addProduct(new Item("Koszule", Item.ItemCondition.USED,3.2,2000));
        contenerMagazin.getFulfillmentCenter("Wroclaw").addProduct(new Item("Koszule", Item.ItemCondition.REFURBISHER,1.2,2500));
        contenerMagazin.getFulfillmentCenter("Wroclaw").addProduct(new Item("Skarpetki", Item.ItemCondition.REFURBISHER,0.2,400));
        System.out.println("Puste:");
        contenerMagazin.findEmpty();
        System.out.println("Dostepne magazyny:");
        contenerMagazin.summary();
        System.out.println("Magazyn Wroclaw");
        contenerMagazin.getFulfillmentCenter("Wroclaw").summary();
        System.out.println("Zmniejszamy produkty w Wroclawiu");
        contenerMagazin.getFulfillmentCenter("Wroclaw").getProduct(new Item("Koszule", Item.ItemCondition.REFURBISHER,1.2,2000));
        contenerMagazin.getFulfillmentCenter("Wroclaw").summary();
        System.out.println("Usuwamy koszule we Wroclawiu");
        contenerMagazin.getFulfillmentCenter("Wroclaw").removeProduct(new Item("Skarpetki", Item.ItemCondition.REFURBISHER,0.2,400));
        contenerMagazin.getFulfillmentCenter("Wroclaw").summary();
        System.out.println("Szukamy w Krakowie Spodni"+contenerMagazin.getFulfillmentCenter("Krakow").search("Spodnie"));
        System.out.println("Szukamy w Krakowie Koszu");
        contenerMagazin.getFulfillmentCenter("Krakow").searchPartial("Koszu");
        System.out.println("Szukamy produktow uzywanych w Krakowowie:");
        contenerMagazin.getFulfillmentCenter("Krakow").countByCondition(Item.ItemCondition.USED);
        System.out.println("Wyswietlamy wszystkie produkty bo nie ma nic uzywanego :");
        contenerMagazin.getFulfillmentCenter("Krakow").summary();
        System.out.println("Posortujemy rzeczy po nazwie ");
        contenerMagazin.getFulfillmentCenter("Krakow").sortByName();
        contenerMagazin.getFulfillmentCenter("Krakow").summary();
        System.out.println("Posortujemy rzeczy po ilosci  ");
        contenerMagazin.getFulfillmentCenter("Krakow").sortByAmount();
        contenerMagazin.getFulfillmentCenter("Krakow").summary();
        System.out.println("Szukamy najwiekszej liczby produktow w warszawie ");
        contenerMagazin.getFulfillmentCenter("Warszawa").max();
        System.out.println("Wszystkie produkty w Warszawie :");
        contenerMagazin.getFulfillmentCenter("Warszawa").summary();
        contenerMagazin.summary();



    }
}
