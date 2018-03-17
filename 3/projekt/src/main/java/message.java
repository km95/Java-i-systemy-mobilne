import org.mariuszgromada.math.mxparser.Expression;

import java.text.MessageFormat;

public class message extends MessageFormat {
    public message() {
        super("");
    }
    public String message_1(String pattern) {

        Expression ec = new Expression(pattern);
        Double result = ec.calculate() ;
         try {
        if(Double.toString(result) == "NaN") {
            throw new MyException();
        }
            Object[] testArgs = {pattern, result};
            MessageFormat form1 = new MessageFormat(
                    "{0}\n\t = {1} \n-----\n");
            return form1.format(testArgs);
    } catch(MyException a) {
    }
       return "";
    }

}
