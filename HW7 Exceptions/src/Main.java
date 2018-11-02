import ru.itmo.ctddev.ionov.expression.OverflowException;
import ru.itmo.ctddev.ionov.expression.parser.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(new ExpressionParser().parse("1 2").evaluate(1, 2, 3));
        /*System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);*/
    }
}