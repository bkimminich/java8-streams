package de.kimminich.java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static java.util.function.Predicate.isEqual;

public class Main {

    private static Author uncleBob = new Author("Robert C. Martin");
    private static Author benjaminEvans = new Author("Benjamin J. Evans");
    private static Author michaelCaughey = new Author("Michael Caughey");
    private static List<Book> myBooks = Arrays.asList(
            new Book(uncleBob, "Clean Code", 466, 18.74, 5, 0),
            new Book(uncleBob, "The Clean Coder", 256, 15.13, 5, 0),
            new Book(benjaminEvans, "The Well-Grounded Java Developer", 462, 31.95, 2, 21),
            new Book(michaelCaughey, "Bitcoin Step by Step", 125, 3.16, 1, 8)
    );

    public static void main(String[] args) {
        System.out.println("------ Creating and using a Stream ------");
        creatingAndUsingAStream();
        System.out.println("\n------ Functional Interface ------");
        functionalInterface();
        System.out.println("\n------ Method References ------");
        methodReferences();
        System.out.println("\n------ Intermediate vs. Terminal ------");
        intermediateVsTerminal();
        System.out.println("\n------ Terminal = Consuming  Operations ------");
        consumingOperations();
        System.out.println("\n------ Stateless Intermediate Operations ------");
        statelessIntermediateOperations();
        System.out.println("\n------ Stateful Intermediate Operations ------");
        statefulIntermediateOperations();
        System.out.println("\n------ Short-Circuiting Operations ------");
        shortCircuitingOperations();
        System.out.println("\n------ Collector Examples ------");
        collectorExamples();
        System.out.println("\n------ Joining Collector ------");
        joiningCollector();
    }

    private static void creatingAndUsingAStream() {
        Stream<Book> books = Main.myBooks.stream();
        Stream<Book> goodBooks = books.filter(b -> b.getStarRating() > 3);
        goodBooks.forEach(b -> System.out.println(b.toString()));
    }

    private static void functionalInterface() {
        Consumer<Book> reduceRankForBadAuthors = (Book b) -> {
            if (b.getStarRating() < 2) b.getAuthor().addRank(-1);
        };
        myBooks.stream().forEach(reduceRankForBadAuthors);
        myBooks.stream().forEach(b -> b.setEstimatedReadingTime(90 * b.getPages()));

        System.out.println(myBooks);
    }

    private static void methodReferences() {
        myBooks.stream().forEach(b -> b.fixSpellingErrors());
        myBooks.stream().forEach(Book::fixSpellingErrors); // instance method

        myBooks.stream().forEach(b -> BookStore.generateISBN(b));
        myBooks.stream().forEach(BookStore::generateISBN); // static method

        myBooks.stream().forEach(b -> System.out.println(b.toString()));
        myBooks.stream().forEach(System.out::println); // expression

        Stream<ISBN> isbns1 = myBooks.stream().map(b -> new ISBN(b));
        Stream<ISBN> isbns2 = myBooks.stream().map(ISBN::new); // constructor

        isbns1.forEach(Main::prettyPrint); // static method in own class
        System.out.println();
        isbns2.forEach(Main::prettyPrint);
        System.out.println();
    }

    private static void prettyPrint(Object o) {
        System.out.print(o + " ");
    }

    private static void intermediateVsTerminal() {
        double totalPrice = myBooks.stream().mapToDouble(Book::getPrice).reduce(0.0, (p1, p2) -> p1 + p2);
        System.out.println("Total price: " + totalPrice);
    }

    private static void consumingOperations() {
        try {
            DoubleStream prices = myBooks.stream().mapToDouble(Book::getPrice);
            prices.forEach(p -> System.out.println("Price: " + p));
            double totalPrice = prices.reduce(0.0, (p1, p2) -> p1 + p2);
        } catch (IllegalStateException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private static void statelessIntermediateOperations() {
        double impairments = myBooks.stream()
                .filter(b -> b.getCondition().equals(Condition.BAD))
                .mapToDouble(Book::getPrice)
                .reduce(0.0, (p1, p2) -> p1 + p2);
        System.out.println("Impairment costs: " + impairments);
    }

    private static void statefulIntermediateOperations() {
        myBooks.stream().map(Book::getAuthor).distinct().forEach(System.out::println);
    }

    private static void shortCircuitingOperations() {
        Author rp = new Author("Rosamunde Pilcher");
        boolean phew = myBooks.stream()
                .map(Book::getAuthor)
                .noneMatch(isEqual(rp));
        System.out.println("Am I safe? " + phew);
    }

    private static void collectorExamples() {
        List<Author> authors = myBooks.stream().map(Book::getAuthor).collect(Collectors.toList());
        System.out.println("Authors: " + authors);

        double averagePages = myBooks.stream().collect(Collectors.averagingInt(Book::getPages));
        System.out.println("Average pages per book: " + averagePages);
    }

    private static void joiningCollector() {
        // not efficient due to recursive String concatenation. And ugly.
        String titleList = myBooks.stream().map(Book::getTitle).reduce("", (t1, t2) -> t1 + t2);
        System.out.println("Title List:\n" + titleList);

        // Still inefficient. Still ugly (initial line break)
        titleList = myBooks.stream().map(Book::getTitle).reduce("", (t1, t2) -> t1 + "\n" + t2);
        System.out.println("Title List:\n" + titleList);

        // more efficient thanks to StringBuilder. Pretty printed.
        titleList = myBooks.stream().map(Book::getTitle).collect(Collectors.joining("\n"));
        System.out.println("Title List:\n" + titleList);
    }

}
