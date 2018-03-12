import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class FulfillmentCenterContainer {
    Map<String, FulfillmentCenter> centerContainer = new TreeMap<>();

    public FulfillmentCenterContainer() {
    }

    public void addCenter(String nameIn, double weightIn) {
        centerContainer.put(nameIn, new FulfillmentCenter(nameIn, weightIn));
    }
    public FulfillmentCenter getFulfillmentCenter(String key){
        return centerContainer.get(key);
    }
    public void removeCenter(String nameIn){
        centerContainer.remove(nameIn);
    }
    public void findEmpty(){
        Set<Map.Entry<String, FulfillmentCenter>> entrySet = centerContainer.entrySet();
        for (Map.Entry<String, FulfillmentCenter> entry : entrySet) {
            if(entry.getValue().freeSpace() == 0) {
                System.out.println(entry.getKey() + " : " + entry.getValue().freeSpace() + " %");
            }
        }
    }
    public void summary() {
        Set<Map.Entry<String, FulfillmentCenter>> entrySet = centerContainer.entrySet();
        for (Map.Entry<String, FulfillmentCenter> entry : entrySet) {
            System.out.println(entry.getKey() + " : " + entry.getValue().freeSpace()+" %");
        }
    }
}
