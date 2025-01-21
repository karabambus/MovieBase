package Model.SQL;

public class Directed {
    private int id;
    private int movie_id;
    private int director_id;

    public Directed(int id, int movie_id, int director_id) {
        this(movie_id, director_id);
        this.id = id;
    }

    public Directed(int movie_id, int director_id) {
        this.movie_id = movie_id;
        this.director_id = director_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getDirector_id() {
        return director_id;
    }

    public void setDirector_id(int director_id) {
        this.director_id = director_id;
    }

    @Override
    public String toString() {
        return "Directed{" +
                "id=" + id +
                ", movie_id='" + movie_id + '\'' +
                ", director_id='" + director_id + '\'' +
                '}';
    }
}
