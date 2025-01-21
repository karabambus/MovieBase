package Model.SQL;

public class GenreJunction {
    private int id_genreJunction;
    private int genre_id;
    private int movie_id;

    public GenreJunction(int id_genreJunction, int genre_id, int movie_id) {
        this(genre_id, movie_id);
        this.id_genreJunction = id_genreJunction;
    }

    public GenreJunction( int genre_id, int movie_id) {
        this.genre_id = genre_id;
        this.movie_id = movie_id;
    }

    public int getId_genreJunction() {
        return id_genreJunction;
    }

    public void setId_genreJunction(int id_genreJunction) {
        this.id_genreJunction = id_genreJunction;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    @Override
    public String toString() {
        return "GenreJunction{" +
                "id_genre='" + id_genreJunction + '\'' +
                ", genre_id='" + genre_id + '\'' +
                ", movie_id='" + movie_id + '\'' +
                '}';
    }
}
