package DAL.SQL;

import DAL.NamedRepository;
import Model.SQL.Director;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DirectorRepository implements NamedRepository<Director> {

    private static final String ID_DIRECTOR = "id_director";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";

    public static final String CREATE = "{ CALL create_director (?, ?) }";
    public static final String UPDATE = "{ CALL update_director (?, ?, ?) }";
    public static final String DELETE = "{ CALL delete_director (?) }";
    public static final String SELECT_BY_ID = "SELECT * FROM get_director_by_id(?)";
    public static final String SELECT_ALL = "SELECT * FROM get_all_directors()";
    public static final String FIND_BY_NAME = "SELECT * FROM get_director_by_name(?,?)";

    @Override
    public void create(List<Director> entity) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        for (Director director : entity) {
            try (Connection connection = dataSource.getConnection();
                 CallableStatement callableStatement = connection.prepareCall(CREATE)) {
                callableStatement.setString(1, director.getName());
                callableStatement.setString(2, director.getLastName());
                callableStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(int id, Director entity) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(UPDATE)) {
            callableStatement.setInt(1, id);
            callableStatement.setString(2, entity.getName());
            callableStatement.setString(3, entity.getLastName());
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
    public Optional<Director> find_by_id(int id) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(SELECT_BY_ID)) {
            callableStatement.setInt(1, id);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(
                            new Director(
                                    resultSet.getInt(ID_DIRECTOR),
                                    resultSet.getString(FIRST_NAME),
                                    resultSet.getString(LAST_NAME)
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
    public List<Director> findAll() throws Exception {
        List<Director> directors = new ArrayList<>();

        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(SELECT_ALL)) {

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                while (resultSet.next()) {
                    directors.add(
                            new Director(
                                    resultSet.getInt(ID_DIRECTOR),
                                    resultSet.getString(FIRST_NAME),
                                    resultSet.getString(LAST_NAME)
                            )
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return directors;
    }

    @Override
    public Optional<Director> findByName(Director entity) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(FIND_BY_NAME)) {
            callableStatement.setString(1, entity.getName());
            callableStatement.setString(2, entity.getLastName());

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(
                            new Director(
                                    resultSet.getInt(ID_DIRECTOR),
                                    resultSet.getString(FIRST_NAME),
                                    resultSet.getString(LAST_NAME)
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