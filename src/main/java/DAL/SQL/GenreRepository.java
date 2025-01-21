package DAL.SQL;

import DAL.NamedRepository;
import Model.SQL.Genre;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreRepository implements NamedRepository<Genre> {

    private static final String ID_GENRE = "id_genre";
    private static final String GENRE_NAME = "genre_name";

    public static final String CREATE = "{ CALL create_genre (?) }";
    public static final String UPDATE = "{ CALL update_genre (?, ?) }";
    public static final String DELETE = "{ CALL delete_genre (?) }";
    public static final String SELECT_BY_ID = "SELECT * FROM get_genre_by_id(?)";
    public static final String SELECT_ALL = "SELECT * FROM get_all_genres()";
    public static final String FIND_BY_GENRE_NAME = "SELECT * FROM get_genre_by_name(?)";

    @Override
    public void create(List<Genre> entity) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        for (Genre genre : entity) {
            try (Connection connection = dataSource.getConnection();
                 CallableStatement callableStatement = connection.prepareCall(CREATE)) {
                callableStatement.setString(1, genre.getGenreName());
                callableStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(int id, Genre genre) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(UPDATE)) {
            callableStatement.setInt(1, id);
            callableStatement.setString(2, genre.getGenreName());
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
    public Optional<Genre> find_by_id(int id) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(
                            new Genre(
                                    resultSet.getInt(ID_GENRE),
                                    resultSet.getString(GENRE_NAME)
                            )
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Genre> findAll() throws Exception {
        List<Genre> genres = new ArrayList<>();
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(SELECT_ALL)) {

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                while (resultSet.next()) {
                    genres.add(
                            new Genre(
                                    resultSet.getInt(ID_GENRE),
                                    resultSet.getString(GENRE_NAME)
                            )
                    );
                }
            }
        }
        return genres;
    }

    @Override
    public Optional<Genre> findByName(Genre entity) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_GENRE_NAME)) {
            preparedStatement.setString(1, entity.getGenreName());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(
                            new Genre(
                                    resultSet.getInt(ID_GENRE),
                                    resultSet.getString(GENRE_NAME)
                            )
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }
}
