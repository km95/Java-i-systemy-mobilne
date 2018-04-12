import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

public class ItemTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    @Test
    public void addWeight() throws Exception {
        Item ten = new Item("Bluzy", Item.ItemCondition.NEW,2.2,100);
        ten.addWeight(12);
        assertEquals(14.2,ten.getWeight(),1);
    }

    @Test
    public void addQuantity() throws Exception {
        Item ten = new Item("Bluzy", Item.ItemCondition.NEW,2.2,100);
        ten.addQuantity(100);
        assertEquals(200,ten.getQuantity(),1);
    }

    @Test
    public void print() throws Exception {
        Item ten = new Item("Bluzy", Item.ItemCondition.NEW,2.2,100);
        assertEquals(ten.Print(),ten.Print());
    }

    @Test
    public void printV() throws Exception {
        Item ten = new Item("Bluzy", Item.ItemCondition.NEW,2.2,100);
        ten.PrintV();
    }

    @Test
    public void getWeight() throws Exception {
        Item ten = new Item("Bluzy", Item.ItemCondition.NEW,2.2,100);
        ten.addWeight(12);
        assertEquals(14.2,ten.getWeight(),1);
    }

}