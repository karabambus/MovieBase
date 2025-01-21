package DAL.SQL;

import DAL.Repository;
import Model.SQL.GenreJunction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreJunctionRepository implements Repository<GenreJunction> {

    private static final String ID_MOVIE_GENRE = "id_movie_genre";
    private static final String GENRE_ID = "genre_id";
    private static final String MOVIE_ID = "movie_id";

    public static final String CREATE = "{ CALL create_movie_genre (?, ?) }";

    public static final String UPDATE = "{ CALL update_movie_genre (?, ?, ?) }";
    public static final String DELETE = "{ CALL delete_movie_genre (?) }";
    public static final String SELECT_BY_ID = "SELECT * FROM get_movie_genre_by_id(?)";
    public static final String SELECT_ALL = "SELECT * FROM get_all_movie_genres()";

    @Override
    public void create(List<GenreJunction> entity) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        for (GenreJunction genreJunction : entity) {
            try (Connection connection = dataSource.getConnection();
                 CallableStatement callableStatement = connection.prepareCall(CREATE)) {
                callableStatement.setInt(1, genreJunction.getGenre_id());
                callableStatement.setInt(2, genreJunction.getMovie_id());
                callableStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(int id, GenreJunction genreJunction) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(UPDATE)) {
            callableStatement.setInt(1, id); // Setting the actor ID
            callableStatement.setInt(1, genreJunction.getId_genreJunction());
            callableStatement.setInt(2, genreJunction.getMovie_id());
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
            callableStatement.setInt(1, id); // Setting the actor ID to delete
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<GenreJunction> find_by_id(int id) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id); // Setting the actor ID to search for

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    GenreJunction genreJunction = new GenreJunction(
                            resultSet.getInt(ID_MOVIE_GENRE),
                            resultSet.getInt(GENRE_ID),
                            resultSet.getInt(MOVIE_ID)
                    );
                    return Optional.of(genreJunction);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<GenreJunction> findAll() throws Exception {
        List<GenreJunction> genreJunctions = new ArrayList<>();
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    GenreJunction genreJunction = new GenreJunction(
                            resultSet.getInt(ID_MOVIE_GENRE),
                            resultSet.getInt(GENRE_ID),
                            resultSet.getInt(MOVIE_ID)
                    );
                    genreJunctions.add(genreJunction);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genreJunctions;
    }
}
