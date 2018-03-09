import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        int pom = 0;
        do {
            System.out.println("Menu: \n1 - Trojkat\n2 - Kwadrat\n3 - Kolo\n4 - Wyjscie");
            try {
                Scanner scanner = new Scanner(System.in);
                pom = scanner.nextInt();
                if (pom > 0 && pom < 4) {
                    Figure odp = CreateFigure.Create(pom);
                    odp.print();
                }else {
                    if(pom != 4){
                    System.err.println("Podaj liczbe 1-4");
                    }else{
                        System.out.println("Zamykanie");
                    }
                }
            } catch (InputMismatchException e) {
                System.err.println("Podaj cyfre!!");
            }
        } while (pom != 4);
    }
}
