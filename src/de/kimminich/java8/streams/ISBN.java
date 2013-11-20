package de.kimminich.java8.streams;

public class ISBN {

    private String isbn10;
    private String isbn13;

    public ISBN(Book book) {
        isbn10 = ("00000" + book.getTitle().hashCode()).substring(0, 10);
        isbn13 = "978" + isbn10;
    }

}
