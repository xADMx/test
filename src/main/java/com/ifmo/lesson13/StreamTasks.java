package com.ifmo.lesson13;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;

public class StreamTasks {

    private static class Person {
        private final String name;
        private final int age;
        private final String country;

        public Person(String name, int age, String country) {
            this.name = name;
            this.age = age;
            this.country = country;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getCountry() {
            return country;
        }
    }

    public static void main(String[] args) {
        List<Person> p = generatePeople(100).collect(Collectors.toList());
        Stream<Person> people = p.stream();
        List<String> countries = countriesSortedByTheirPopulationDescending(people);
        people = p.stream();
        String countryThatHasMostKids = countryThatHasMostKids(people);
        people = p.stream();
        Map<String, Long> populationByCountry = populationByCountry(people);


        System.out.println(countries);
        System.out.println(countryThatHasMostKids);
        System.out.println(populationByCountry);

        List<String> words = List.of("the", "hello", "approximation", "greetings", "java", "war");

        Map<Integer, Set<String>> wordsByLength = groupWordsByLength(words);
        int averageWordLength = averageWordLength(words);
        Set<String> longestWords = longestWords(words);

        System.out.println(wordsByLength);
        System.out.println(averageWordLength);
        System.out.println(longestWords);
    }

    // Метод возвращает страны в порядке убывания их населения.
    public static List<String> countriesSortedByTheirPopulationDescending(Stream<Person> people) {
        // TODO implement.

        return people.collect(Collectors.groupingBy(Person::getCountry, counting())).entrySet()
        .stream().sorted((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
        .map(e -> e.getKey()).collect(Collectors.toList());

    }

    // Метод находит страну (или одну из стран), в которых больше всего человек в возрасте
    // до 18 лет.
    public static String countryThatHasMostKids(Stream<Person> people) {
        // TODO implement.
        return people.filter(p -> p.age < 18).collect(Collectors.groupingBy(Person::getCountry, counting())).entrySet()
                .stream().max((o1, o2) -> o1.getValue().compareTo(o2.getValue()))
                .map(Map.Entry::getKey).orElse("Unknown");
    }

    // Метод возвращает карту стран их населения.
    public static Map<String, Long> populationByCountry(Stream<Person> people) {
        // TODO implement.

        return people.collect(Collectors.groupingBy(Person::getCountry, counting()));
    }

    // Метод генерирует случайных людей в ограниченном наборе стран.
    // number - число желаемых людей.
    public static Stream<Person> generatePeople(int number) {
        // TODO implement.
        String[] name = new String[]{"Саша", "Маша", "Юля", "Катя", "Ваня", "Максим","Варлера", "Гена", "Коля"};
        String[] contry = new String[]{"Россия", "Украина", "США", "Англия", "Мексика","Австралия", "Австрия","Алжир", "Сирия"};

        Random rnd = new Random();

        return Stream.generate(() -> new Person(
                name[rnd.nextInt(8)],
                rnd.nextInt(80) + 1,
                contry[rnd.nextInt(8)]
        )).limit(number);
    }

    // Метод возвращает карту сгруппированных слов по их длине. Например, для
    // трёхбуквенных слов будет:
    // 3 -> "the", "map", "got", "war"...
    public static Map<Integer, Set<String>> groupWordsByLength(List<String> words) {
        // TODO implement.

        return words.stream().collect(Collectors.groupingBy(String::length, Collectors.toSet()));
    }

    // Метод находит среднюю длину слов в списке.
    public static int averageWordLength(List<String> words) {
        // TODO implement.

        int sum = words.stream().collect(Collectors.summingInt(String::length));
        System.out.println("sum " + sum + "size" + words.size());
        return sum/words.size();
    }

    // Метод находит самое длинное слово или слова, если таких несколько.
    public static Set<String> longestWords(List<String> words) {
        // TODO implement.
        int max = words.stream().max((s1, s2) ->  Integer.valueOf(s1.length()).compareTo(Integer.valueOf(s2.length()))).orElse("").length();
        return words.stream().filter(e -> e.length() == max).collect(Collectors.toSet());
    }


}
