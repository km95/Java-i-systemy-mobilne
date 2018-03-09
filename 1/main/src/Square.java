public class Square extends Figure implements Print {

    int a;

    Square(int a) {
        this.a = a;
    }

    @Override
    public double calculateArea() {
        return a * a;
    }

    @Override
    public double calculatePerimeter() {
        return 4 * a;
    }

    @Override
    public void print() {
        System.out.println("a = " + a);
        System.out.println("Pole kwadratu = " + calculateArea());
        System.out.println("Obwod kwadratu = " + calculatePerimeter());
    }
}
