import java.util.Scanner;

public class CreateFigure {

    public static Triangle CreateTriangle() {
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Podaj a = ");
            int a = scanner.nextInt();
            System.out.println("Podaj b = ");
            int b = scanner.nextInt();
            System.out.println("Podaj c = ");
            int c = scanner.nextInt();
            if (a < 0 || b < 0 || c < 0) {
                System.err.println("Liczba mniejsza od zera");
            } else {
                Triangle ob = new Triangle(a, b, c);
                return ob;
            }
        } while (true);
    }

    public static Square CreateSquare() {

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Podaj a = ");
            int r = scanner.nextInt();
            if (r < 0) {
                System.err.println("Liczba mniejsza od zera");
            } else {
                Square ob = new Square(r);
                return ob;
            }
        } while (true);
    }

    public static Circle CreateCircle() {
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Podaj r = ");
            int r = scanner.nextInt();
            if (r < 0) {
                System.err.println("Liczba mniejsza od zera");
            } else {
                Circle ob = new Circle(r);
                return ob;
            }
        } while (true);
    }

    public static Figure Create(int pom) {
        switch (pom) {
            case 1:
                Triangle obT = CreateFigure.CreateTriangle();
                return obT;
            case 2:
                Square obS = CreateFigure.CreateSquare();
                return obS;

            case 3:
                Circle obC = CreateFigure.CreateCircle();
                return obC;
            case 4:
                System.out.println("Zamykanie");
                break;
        }
        return null;
    }

}
