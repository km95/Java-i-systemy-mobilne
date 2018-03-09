
public class Triangle extends Figure implements Print {

    int a;
    int b;
    int c;

    Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double calculateArea() {
        double p = (1. / 2.) * (a + b + c);

        return Math.sqrt(p * (p - a) * (p - b) * (p - c));

    }

    @Override
    public double calculatePerimeter() {
        return a + b + c;
    }

    @Override
    public void print() {
        System.out.println("a = " + a + "\nb = " + b + "\nc = " + c);
        System.out.println("Pole trojkata = " + calculateArea());
        System.out.println("Obwod trojkata = " + calculatePerimeter());
    }
}
