package stream;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Write a method called mode that returns the  most frequently occurring element
// of an array of integers.
// Assume that  the array has at least one element and that every element in the array
// has a value between 0 and 100 inclusive.
//        For example, if the array passed contains the values
//        {27, 15, 15, 11, 27}, your method should return 15.

public class NumberWork {

    private static Integer getMostFrequencyNumber(List<Integer> list) {
//        var list = Arrays.asList(arrayList);
        Map<Integer, Long> map = list.stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));
        return map.entrySet().stream()
                .sorted(getReversed())
                .collect(getEntryLinkedHashMapCollector())
                .keySet().stream().findFirst().orElse(0);
    }

    private static Comparator<Map.Entry<Integer, Long>> getReversed() {
        return Map.Entry.<Integer, Long>comparingByValue().reversed();
    }

    private static Collector<Map.Entry<Integer, Long>, ?, LinkedHashMap<Integer, Long>> getEntryLinkedHashMapCollector() {
        return Collectors
                .toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new);
    }

    public static void main(String[] args) {
//        List<Integer> numberList = new ArrayList<>(Arrays.asList(27, 15, 15, 11, 27));
        List<Integer> numberList = List.of(27, 15, 15, 15, 11, 27, 12);

        double result = numberList.stream()
//                .map(Double::valueOf)
                .reduce(0, Integer::sum);

        Double sum = numberList.stream()
                .map(Double::valueOf)
                .mapToDouble(x ->x)
                .sum();

        int sumRangedValues =  IntStream.range(2, 5)
                .map(numberList::get).sum();

        System.out.println(sumRangedValues);


        System.out.println(result);
        List<Character> stringList = List.of('a', 'b', 'c', 'd', 'e');
        String resultStringConcat = stringList.stream()
                .map(String::valueOf)
                .map(NumberWork::editString)
                .reduce("", String::concat);

        System.out.println(resultStringConcat);

//        double sum = getSum(numberList);

//        System.out.println(sum);
//
        String joinedString = IntStream.range(2, 5)
                .map(numberList::get)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", "));
        System.out.println(joinedString);
//        numberList.add(22);
//        System.out.println(
//                numberList.stream()
//                        .sorted()
//                        .collect(Collectors.toList())
//        );

//        System.out.println(getMostFrequencyNumber(numberList));
    }

    private double getSum(List<Integer> numberList) {
       return numberList.stream()
                .map(Double::valueOf)
                .mapToDouble(x -> x)
                .sum();
    }



    private boolean getEvenNumbers(Double x) {
        return x % 2 == 0;
    }

    private static boolean getEvenNumbers(Integer x) {
        return x % 2 == 0;
    }

    private static String editString(String x) {
        x += " ";
        x = x.toUpperCase();
        return x;
    }
}
