import java.util.*;
import java.util.stream.*;
import java.math.BigInteger;

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
                .flatMap(s -> Arrays.stream(s.split("\\s*,\\s*")))
                .map(Integer::parseInt)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    public static Stream<Long> Task4(long seed, long a, long c, long m) {
        BigInteger A = BigInteger.valueOf(a);
        BigInteger C = BigInteger.valueOf(c);
        BigInteger M = BigInteger.valueOf(m);

        return Stream.iterate(seed, x ->
                BigInteger.valueOf(x)
                        .multiply(A)
                        .add(C)
                        .mod(M)
                        .longValue());
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> iterator1 = first.iterator();
        Iterator<T> iterator2 = second.iterator();

        Iterator<T> result = new Iterator<>() {
            private boolean turnFirst = true;

            @Override
            public boolean hasNext() {
                return iterator1.hasNext() && iterator2.hasNext();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                if (turnFirst) {
                    turnFirst = false;
                    return iterator1.next();
                } else {
                    turnFirst = true;
                    return iterator2.next();
                }
            }
        };

        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(result, Spliterator.ORDERED),
                false
        );
    }

    public static void main(String[] args) {

        List<String> names = List.of("Ivan", "Peter", "Alex", "John", "Mike");

        System.out.println(Task1(names));
        System.out.println(Task2(names));

        String[] numbers = {"1,2, 0", "4,  5"};
        System.out.println(Task3(numbers));

        Task4(1L, 5L, 3L, 100L)
                .limit(5)
                .forEach(System.out::println);

        zip(Stream.iterate(1, x -> x + 1),
                Stream.iterate(10, x -> x + 10))
                .limit(10)
                .forEach(System.out::println);
    }
}
