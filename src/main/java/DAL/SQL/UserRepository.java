package DAL.SQL;

import DAL.NamedRepository;
import Exceptions.DatabaseException;
import Model.SQL.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements NamedRepository<User> {

    private static final String ID_USER = "id_user";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    public static final String CREATE = "{ CALL create_user (?, ?) }";
    public static final String UPDATE = "{ CALL update_user (?, ?, ?) }";
    public static final String DELETE = "{ CALL delete_user (?) }";
    public static final String SELECT_BY_ID = "SELECT * FROM get_user_by_id(?)";
    public static final String SELECT_ALL = "SELECT * FROM get_all_users()";
    public static final String FIND_BY_NAME = "SELECT * FROM get_user_by_username(?)";

    @Override
    public void create(List<User> entity) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        for (User user : entity) {
            try (Connection connection = dataSource.getConnection();
                 CallableStatement callableStatement = connection.prepareCall(CREATE)) {
                callableStatement.setString(1, user.getUsername());
                callableStatement.setString(2, user.getPassword());
                callableStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(int id, User user) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(UPDATE)) {
            callableStatement.setInt(1, id);
            callableStatement.setString(2, user.getUsername());
            callableStatement.setString(3, user.getPassword());
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
    public Optional<User> find_by_id(int id) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(
                            new User(
                                    resultSet.getInt(ID_USER),
                                    resultSet.getString(USERNAME),
                                    resultSet.getString(PASSWORD)
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
    public List<User> findAll() throws DatabaseException {
        List<User> users = new ArrayList<>();
        DataSource dataSource = DataSourceSingelton.getInstance();

        // Using try-with-resources to ensure resources are closed
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(SELECT_ALL);
             ResultSet resultSet = callableStatement.executeQuery()) {

            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt(ID_USER),
                        resultSet.getString(USERNAME),
                        resultSet.getString(PASSWORD)
                ));
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error occurred while fetching users from the database.", e);
        }
        return users;
    }

    @Override
    public Optional<User> findByName(User entity) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)) {
            preparedStatement.setString(1, entity.getUsername());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(
                            new User(
                                    resultSet.getInt(1),
                                    resultSet.getString(2).trim(),
                                    resultSet.getString(3).trim()
                            )
                    );
                }
            }
        }
        return Optional.empty();
    }
}
