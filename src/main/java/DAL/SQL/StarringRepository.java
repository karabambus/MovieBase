package DAL.SQL;

import DAL.Repository;
import Model.SQL.Starring;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StarringRepository implements Repository<Starring> {
    private static final String ID_STARRING = "id_starring";
    private static final String ACTOR_ID = "actor_id";
    private static final String MOVIE_ID = "movie_id";

    public static final String CREATE = "{ CALL create_starring (?, ?) }";

    public static final String UPDATE = "{ CALL update_starring (?, ?, ?) }";
    public static final String DELETE = "{ CALL delete_starring (?) }";
    public static final String SELECT_BY_ID = "SELECT * FROM get_starring_by_id(?)";
    public static final String SELECT_ALL = "SELECT * FROM get_all_starring()";

    @Override
    public void create(List<Starring> entity) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        for (Starring starring : entity) {
            try (Connection connection = dataSource.getConnection();
                 CallableStatement callableStatement = connection.prepareCall(CREATE)) {
                callableStatement.setInt(1, starring.getActor_id());
                callableStatement.setInt(2, starring.getMovie_id());
                callableStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(int id, Starring starring) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(UPDATE)) {
            callableStatement.setInt(1, id); // Setting the actor ID
            callableStatement.setInt(1, starring.getActor_id());
            callableStatement.setInt(2, starring.getMovie_id());
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
    public Optional<Starring> find_by_id(int id) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id); // Setting the actor ID to search for

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Starring starring = new Starring(
                            resultSet.getInt(ID_STARRING),
                            resultSet.getInt(ACTOR_ID),
                            resultSet.getInt(MOVIE_ID)
                    );
                    return Optional.of(starring);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Starring> findAll() throws Exception {
        List<Starring> starrings = new ArrayList<>();
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Starring starring = new Starring(
                            resultSet.getInt(ID_STARRING),
                            resultSet.getInt(ACTOR_ID),
                            resultSet.getInt(MOVIE_ID)
                    );
                    starrings.add(starring);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return starrings;
    }
}
