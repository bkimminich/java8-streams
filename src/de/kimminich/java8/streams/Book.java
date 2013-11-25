package de.kimminich.java8.streams;

public class Book {

    private String title;
    private Author author;
    private double price;
    private long estimatedReadingTime;
    private int pages;
    private int missingPages;
    private Condition condition;
    private int starRating;
    private ISBN isbn;

    public Book(Author author, String title, int pages, double price, int starRating, int missingPages) {
        this.author = author;
        this.title = title;
        this.price = price;
        this.starRating = starRating;
        this.estimatedReadingTime = 0;
        this.pages = pages;
        this.missingPages = missingPages;
        this.condition = BookStore.computeCondition(missingPages);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMissingPages() {
        return missingPages;
    }

    public void setMissingPages(int missingPages) {
        this.missingPages = missingPages;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public long getEstimatedReadingTime() {
        return estimatedReadingTime;
    }

    public void setEstimatedReadingTime(long estimatedReadingTime) {
        this.estimatedReadingTime = estimatedReadingTime;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public ISBN getISBN() {
        return isbn;
    }

    public void setISBN(ISBN isbn) {
        this.isbn = isbn;
    }

    public void fixSpellingErrors() {
        // TODO send angry mail to author
    }

    @Override
    public String toString() {
        return "Book{" +
                "author=" + author +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", estimatedReadingTime=" + estimatedReadingTime +
                ", missingPages=" + missingPages +
                ", condition=" + condition +
                ", starRating=" + starRating +
                ", isbn=" + isbn +
                '}';
    }
}
