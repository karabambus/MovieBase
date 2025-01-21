package DAL.SQL;

import DAL.NamedRepository;
import Model.SQL.Actor;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActorRepository implements NamedRepository<Actor> {

    private static final String ID_ACTOR = "id_actor";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";

    public static final String CREATE = "{ CALL create_actor (?, ?) }";
    public static final String UPDATE = "{ CALL update_actor (?, ?, ?) }";
    public static final String DELETE = "{ CALL delete_actor (?) }";
    public static final String SELECT_BY_ID = "SELECT * FROM get_actor_by_id(?)";
    public static final String SELECT_ALL = "SELECT * FROM get_all_actors()";
    public static final String FIND_BY_NAME = "SELECT * FROM get_actor_by_name(?,?)";
    @Override
    public void create(List<Actor> entity) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        for (Actor actor : entity) {
            try (Connection connection = dataSource.getConnection();
                 CallableStatement callableStatement = connection.prepareCall(CREATE)) {
                callableStatement.setString(1, actor.getName());
                callableStatement.setString(2, actor.getLastName());
                callableStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(int id, Actor actor) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(UPDATE)) {
            callableStatement.setInt(1, id); // Setting the actor ID
            callableStatement.setString(2, actor.getName()); // Setting the new first name
            callableStatement.setString(3, actor.getLastName()); // Setting the new last name
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
    public Optional<Actor> find_by_id(int id) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id); // Setting the actor ID to search for

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Actor actor = new Actor(
                            resultSet.getInt(ID_ACTOR),
                            resultSet.getString(FIRST_NAME),
                            resultSet.getString(LAST_NAME)
                    );
                    return Optional.of(actor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Actor> findAll() throws Exception {
        List<Actor> actors = new ArrayList<>();
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Actor actor = new Actor(
                            resultSet.getInt(ID_ACTOR),
                            resultSet.getString(FIRST_NAME),
                            resultSet.getString(LAST_NAME)
                    );
                    actors.add(actor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }

    @Override
    public Optional<Actor> findByName(Actor entity) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)) {
            preparedStatement.setString(1, entity.getName()); // Setting the actor ID to search for
            preparedStatement.setString(2, entity.getLastName()); // Setting the actor ID to search for

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Actor actor = new Actor(
                            resultSet.getInt(ID_ACTOR),
                            resultSet.getString(FIRST_NAME),
                            resultSet.getString(LAST_NAME)
                    );
                    return Optional.of(actor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
