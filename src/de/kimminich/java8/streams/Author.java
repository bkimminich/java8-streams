package de.kimminich.java8.streams;

public class Author {

    private String name;
    private int rank;

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void addRank(int rankIncrease) {
        rank += rankIncrease;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", rank=" + rank +
                '}';
    }
}
