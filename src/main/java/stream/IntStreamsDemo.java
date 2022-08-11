package stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntStreamsDemo {
    public static void main(String[] args) {
        List<Integer> numberList = List.of(1, 2, 2, 3, 3, 4, 5, 6, 7, 7, 8, 9, 9, 9, 10);
        System.out.println(getModifiedList(numberList));

        System.out.println(getSumElement(numberList));

        List<Character> characterList = List.of('a', 'b', 'c', 'd', 'e');
        System.out.println(getConcatenatedStringWithSault("salt", characterList));
        System.out.println(getConcatenatedStringWithSault("pepper", characterList));

        System.out.println(numberList.stream()
                .mapToInt(x -> x)
                .sum());

        System.out.println(IntStream.range(2, 5)
                        .map(numberList::get).sum());


    }

    private static String getConcatenatedStringWithSault(String salt, List<Character> characterList) {
        return characterList.stream()
                .map(String::valueOf)
                .reduce(salt, String::concat);
    }

    private static Integer getSumElement(List<Integer> numberList) {
        return numberList.stream()
                .reduce(0, Integer::sum);
    }

    private static String getModifiedList(List<Integer> numberList) {
        return numberList.stream()
                .filter(IntStreamsDemo::getEvenValues)
                .map(IntStreamsDemo::addTenToValue)
                .limit(3)
                .map(String::valueOf)
                .collect(Collectors.joining("; ", "(", ")"));
    }

    private static void printElements(int x) {
       System.out.print(x + ", ");
    }

    private static boolean getEvenValues(int x) {
        return x % 2 == 0;
    }

    private static int addTenToValue(int x) {
        return x + 10;
    }

//        System.out.println(numberList);
//        List<Integer> numberList = Arrays.asList(1, 2, 2, 3, 3, 4, 5, 6, 7, 7, 8, 9, 9, 9, 10);
//        filterAndPrintNumbersViaStream(numberList);
//        printNumbersViaStream(numberList);
//       numberList.stream()
//                .filter(LambdaSimple::getEven)
//                .forEach(x -> System.out.print(" "));

//        IntStream.range(0, 7).filter(LambdaSimple::getEven)
//                .mapToObj(index -> String.format("%d %d", index, numberList.get(index)))
//                .forEach(System.out::println);

    // get sum of duplicated elements
//        IntStream.range(0, 7)
//                .mapToObj(index -> String.format("%d %d", index, numberList.get(index)))
//                .forEach(System.out::println);

//    private static void filterAndPrintNumbersViaStream(List<Integer> numberList) {
//        numberList.stream()
//                .filter(LambdaSimple::getEven)
//                .map(LambdaSimple::changeNumbers)
//                .limit(3)
//                .forEach(System.out::println);
//    }
}
