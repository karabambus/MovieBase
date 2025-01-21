package Model.SQL;

public final class Movie {
    private int ID_movie;
    private String title;
    private int release_year;
    private int rating;
    private String plot;
    private int duration;
    private int director_ID;


    public Movie(int ID_movie, String title, int release_year, int rating, String plot, int duration, int director_ID) {
        this(title, release_year, rating, plot, duration);
        setID_movie(ID_movie);
        setDirector_ID(director_ID);
    }

    public Movie(String title, int release_year, int rating, String plot, int duration) {
        setTitle(title);
        setRelease_year(release_year);
        setRating(rating);
        setPlot(plot);
        setDuration(duration);
    }

    public void update(Movie m) {
        setTitle(m.getTitle());
        setRelease_year(m.getRelease_year());
        setRating(m.getRating());
        setPlot(m.getPlot());
        setDuration(m.getDuration());
    }

    public int getID_movie() {
        return ID_movie;
    }

    public void setID_movie(int ID_movie) {
        this.ID_movie = ID_movie;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDirector_ID() {
        return director_ID;
    }

    public void setDirector_ID(int director_ID) {
        this.director_ID = director_ID;
    }

    @Override
    public String toString() {
        return "Movie{" + "movieID=" +
                ID_movie + ", title='" +
                title + '\'' + ", year=" +
                release_year + ", rating=" +
                rating + ", plot='" +
                plot + '\'' + ", length="
                + duration + ", iddirector="
                + director_ID + '}';
    }
}
