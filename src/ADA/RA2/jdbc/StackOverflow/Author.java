package ADA.RA2.jdbc.StackOverflow;

public class Author {

    private int id;
    private String name;
    private double points;
    private int rank;

    public Author(String name, double points, int rank) {
        this.name = name;
        this.points = points;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", points=" + points +
                ", rank=" + rank +
                '}';
    }
}
