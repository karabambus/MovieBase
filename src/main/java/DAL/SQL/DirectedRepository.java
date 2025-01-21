package DAL.SQL;

import DAL.Repository;
import Model.SQL.Directed;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DirectedRepository implements Repository<Directed> {

    private static final String ID_DIRECTED = "id_directing";
    private static final String DIRECTOR_ID = "director_id";
    private static final String MOVIE_ID = "movie_id";

    public static final String CREATE = "{ CALL create_directing (?, ?) }";

    public static final String UPDATE = "{ CALL update_directing (?, ?, ?) }";
    public static final String DELETE = "{ CALL delete_directing (?) }";
    public static final String SELECT_BY_ID = "SELECT * FROM get_directing_by_id(?)";
    public static final String SELECT_ALL = "SELECT * FROM get_all_directing()";

    @Override
    public void create(List<Directed> entity) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        for (Directed directed : entity) {
            try (Connection connection = dataSource.getConnection();
                 CallableStatement callableStatement = connection.prepareCall(CREATE)) {
                    callableStatement.setInt(1, directed.getDirector_id());
                    callableStatement.setInt(2, directed.getMovie_id());
                    callableStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(int id, Directed directed) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(UPDATE)) {
                callableStatement.setInt(1, id); // Setting the actor ID
                callableStatement.setInt(1, directed.getDirector_id());
                callableStatement.setInt(2, directed.getMovie_id());
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
    public Optional<Directed> find_by_id(int id) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id); // Setting the actor ID to search for

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Directed directed = new Directed(
                            resultSet.getInt(ID_DIRECTED),
                            resultSet.getInt(DIRECTOR_ID),
                            resultSet.getInt(MOVIE_ID)
                    );
                    return Optional.of(directed);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Directed> findAll() throws Exception {
        List<Directed> directings = new ArrayList<>();
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Directed directed = new Directed(
                            resultSet.getInt(ID_DIRECTED),
                            resultSet.getInt(DIRECTOR_ID),
                            resultSet.getInt(MOVIE_ID)
                    );
                    directings.add(directed);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return directings;
    }
}
