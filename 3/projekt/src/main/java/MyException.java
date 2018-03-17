import javax.swing.*;

public class MyException extends RuntimeException {  //wyjątek występujący podczas wykonywania programu
    public MyException() {
        super("Moj wyjatek");
        JOptionPane.showMessageDialog(null, "Błędne dane");
    }
}