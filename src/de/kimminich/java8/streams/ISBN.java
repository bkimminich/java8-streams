package de.kimminich.java8.streams;

public class ISBN {

    private String isbn10;
    private String isbn13;

    public ISBN(Book book) {
        isbn10 = ("0" + Math.abs(book.getTitle().hashCode())).substring(0, 10);
        isbn13 = "978" + isbn10;
    }

    @Override
    public String toString() {
        return "ISBN{" +
                "isbn10='" + isbn10 + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                '}';
    }
}
