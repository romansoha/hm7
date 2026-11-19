import java.util.*;
import java.util.stream.*;

public class Main {

    public static String Task1(List<String> names) {
        return IntStream.range(0, names.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(i -> i + ". " + names.get(i))
                .collect(Collectors.joining(", "));
    }

    public static List<String> Task2(List<String> names) {
        return names.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public static String Task3(String[] numbers) {
        return Arrays.stream(numbers)
                .flatMap(s -> Arrays.stream(s.split(", ")))
                .map(Integer::parseInt)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    public static Stream<Long> Task4(long seed, long a, long c, long m) {
        return Stream.iterate(seed, x -> (a * x + c) & (m - 1));
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> iterator1 = first.iterator();
        Iterator<T> iterator2 = second.iterator();

        List<T> result = new ArrayList<>();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            result.add(iterator1.next());
            result.add(iterator2.next());
        }

        return result.stream();
    }

    public static void main(String[] args) {

        List<String> names = List.of("Ivan", "Peter", "Alex", "John", "Mike");

        System.out.println(Task1(names));
        System.out.println(Task2(names));

        String[] numbers = {"1, 2, 0", "4, 5"};
        System.out.println(Task3(numbers));

        Task4(1L, 25214903917L, 11L, 1L << 48)
                .limit(10)
                .forEach(System.out::println);

        zip(Stream.of(1, 2, 3), Stream.of(10, 20, 30))
                .forEach(System.out::println);
    }
}
