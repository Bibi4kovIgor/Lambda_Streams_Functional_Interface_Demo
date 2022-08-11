package lambda;

import java.util.function.*;

interface EchoFunction {
    String echo(String str);
    default void printEcho(String str) {
        System.out.println(str);
    }
}

interface PrintString {
    void print(String str);
}

interface Mathematical {
    static boolean isEven(Integer x) {
        return x % 2 == 0;
    }
    boolean isOdd(Integer x);
}
@FunctionalInterface
interface MathematicalOperation {
    double processCalculations(double x, double y);
}

public class LambdaSimple {
    public static void main(String[] args) {

        echoTest();

        printStringTest();

        echoViaFunction();

        Mathematical mathematical = x -> x % 2 != 0;
        predicateOddEven(mathematical::isOdd);
        predicateOddEven(Mathematical::isEven);


        mathematicalOperations();

    }

    private static void predicateOddEven(Predicate<Integer> function) {
        System.out.println(function.test(4));
        System.out.println(function.test(5));
    }

    private static void mathematicalOperations() {
        MathematicalOperation mathematicalOperation = (x, y) -> (x + y) * (x - y);
        BiFunction<Double, Double ,Double> biFunctionCalculation = mathematicalOperation::processCalculations;
        System.out.println(biFunctionCalculation.apply(25d, 26d));
    }

    private static void echoViaFunction() {
        EchoFunction stringFunction = (x) -> x;
        Function<EchoFunction, String> echoFunction = (x) -> stringFunction.echo("Hello world!!");
        System.out.println(echoFunction.apply(stringFunction));
    }

    private static void printStringTest() {
        PrintString printString = (x) -> System.out.printf("Hello %s!", x);
        Consumer<String> stringFunction4Consumer = printString::print;
        stringFunction4Consumer.accept("Ann");
    }

    private static void echoTest() {
        EchoFunction echoFunction = x -> x;
        UnaryOperator<String> stringFunctionConsumer = echoFunction::echo;
        System.out.println(stringFunctionConsumer.apply("Hello Unary!"));
        echoFunction.printEcho("Echo default");
    }




}


