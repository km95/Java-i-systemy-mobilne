public class Item implements Comparable<Item> {
    String name;
    ItemCondition condition;
    double weight;

    public int getQuantity() {
        return quantity;
    }

    int quantity;

    public enum ItemCondition{
        NEW,
        USED,
        REFURBISHER
    }

    public int compareTo(Item o) {
        int compare = name.compareTo(o.name);
        if(compare == 0) {
            return name.compareTo(o.name);
        }
        else {
            return compare;
        }
    }
    public int compareTo(String nameIn){
        int compare = name.compareTo(nameIn);
        if(compare == 0) {
            return name.compareTo(nameIn);
        }
        else {
            return compare;
        }
    }
    public int compareTo(ItemCondition conditionIn){
        int compare = condition.compareTo(conditionIn);
        if(compare == 0) {
            return condition.compareTo(conditionIn);
        }
        else {
            return compare;
        }
    }

    public void addWeight(double weight) {
        this.weight += weight;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
    public void subtractionQuantity(int quantity) {
        this.quantity -= quantity;
    }

    public Item(String name, ItemCondition condition, double weight, int quantity) {
        this.name = name;
        this.condition = condition;
        if (weight<0 || quantity < 0){
            throw new IllegalArgumentException("wyjatek");
        }else{
            this.weight = weight;
            this.quantity = quantity;
        }


    }
    public String Print (){
        return "nazwa : "+name +" / stan : "+ condition +" / waga : "+ weight +" / ilosc : "+ quantity;
    }
    public void PrintV (){
        System.out.println("nazwa : "+name +" / stan : "+ condition +" / waga : "+ weight +" / ilosc : "+ quantity);
    }

    public double getWeight() {
        return weight;
    }
}
