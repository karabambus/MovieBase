package DAL.SQL;

import DAL.NamedRepository;
import Model.SQL.Movie;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieRepository implements NamedRepository<Movie> {

    private static final String TITLE = "title";
    private static final String YEAR = "release_year";
    private static final String RATING = "rating";
    private static final String PLOT = "plot";
    private static final String LENGTH = "duration";

    public static final String CREATE = "{ CALL create_movie (?, ?, ?, ?, ?) }";
    public static final String UPDATE = "{ CALL update_movie (?, ?, ?, ?, ?, ?) }";
    public static final String DELETE = "{ CALL delete_movie (?) }";
    public static final String SELECT_BY_ID = "SELECT * FROM get_movie_by_id(?)";
    public static final String SELECT_ALL = "SELECT * FROM get_all_movies()";
    public static final String FIND_BY_TITLE = "SELECT * FROM get_movie_by_title(?)";

    @Override
    public void create(List<Movie> entity) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        for (Movie movie : entity) {
            try (Connection connection = dataSource.getConnection();
                 CallableStatement callableStatement = connection.prepareCall(CREATE)) {
                callableStatement.setString(1, movie.getTitle());
                callableStatement.setInt(2, movie.getRelease_year());
                callableStatement.setInt(3, movie.getRating());
                callableStatement.setString(4, movie.getPlot());
                callableStatement.setInt(5, movie.getDuration());
                callableStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(int id, Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(UPDATE)) {
            callableStatement.setInt(1, id);
            callableStatement.setString(2, movie.getTitle());
            callableStatement.setInt(3, movie.getRelease_year());
            callableStatement.setInt(4, movie.getRating());
            callableStatement.setString(5, movie.getPlot());
            callableStatement.setInt(6, movie.getDuration());
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(DELETE)) {
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Movie> find_by_id(int id) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(SELECT_BY_ID)) {
            callableStatement.setInt(1, id);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(
                            new Movie(
                                    resultSet.getString(TITLE),
                                    resultSet.getInt(YEAR),
                                    resultSet.getInt(RATING),
                                    resultSet.getString(PLOT),
                                    resultSet.getInt(LENGTH)
                            )
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Movie> findAll() throws Exception {
        List<Movie> movies = new ArrayList<>();
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(SELECT_ALL)) {

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Parse interval (length) to Duration
                    movies.add(
                            new Movie(
                                    resultSet.getString(TITLE),
                                    resultSet.getInt(YEAR),
                                    resultSet.getInt(RATING),
                                    resultSet.getString(PLOT),
                                    resultSet.getInt(LENGTH)
                            )
                    );
                }
            }
        }
        return movies;
    }

    @Override
    public Optional<Movie> findByName(Movie entity) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(FIND_BY_TITLE)) {
            callableStatement.setString(1, entity.getTitle());

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(
                            new Movie(
                                    resultSet.getString(TITLE),
                                    resultSet.getInt(YEAR),
                                    resultSet.getInt(RATING),
                                    resultSet.getString(PLOT),
                                    resultSet.getInt(LENGTH)
                            )
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
