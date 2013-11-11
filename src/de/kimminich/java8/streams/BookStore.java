package de.kimminich.java8.streams;

public class BookStore {

    public static Condition computeCondition(int missingPages) {
        return missingPages > 10 ? Condition.BAD : Condition.GOOD;
    }

    public static ISBN generateISBN(Book book) {
        return new ISBN(book);
    }

}
