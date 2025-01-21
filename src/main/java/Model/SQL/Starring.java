package Model.SQL;

public class Starring {
    private int id_starring;
    private int actor_id;
    private int movie_id;

    public Starring(int id_starring, int actor_id, int movie_id) {
        this(actor_id, movie_id);
        this.id_starring = id_starring;
    }

    public Starring(int actor_id, int movie_id) {
        this.actor_id = actor_id;
        this.movie_id = movie_id;
    }

    public int getId_starring() {
        return id_starring;
    }

    public void setId_starring(int id_starring) {
        this.id_starring = id_starring;
    }

    public int getActor_id() {
        return actor_id;
    }

    public void setActor_id(int actor_id) {
        this.actor_id = actor_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    @Override
    public String toString() {
        return "Starring{" +
                "id_starring=" + id_starring +
                ", actor_id=" + actor_id +
                ", movie_id='" + movie_id + '\'' +
                '}';
    }
}
