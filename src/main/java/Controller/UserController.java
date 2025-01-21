package Controller;

import DAL.NamedRepository;
import DAL.Repository;
import DAL.RepositoryFactory;
import Exceptions.DatabaseException;
import Exceptions.LoginException;
import Exceptions.RegistrationException;
import Exceptions.UsernameAlreadyRegisteredException;
import Model.SQL.Movie;
import Model.SQL.User;
import Model.TableModel.MovieTableModel;
import Utilities.SQLUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserController {

    public boolean registerUser(String username, String password) throws RegistrationException {
        // Initialize user and repository
        User user = null;
        try {
            user = new User(username, password);  // Password validation is handled here
        } catch (Exception e) {
            e.printStackTrace();
        }

        NamedRepository<User> userRepository;
        try {
            // Attempt to get the repository instance
            userRepository = (NamedRepository<User>) RepositoryFactory.getInstance(User.class);

            // Check if the user exists in the repository
            if (SQLUtils.exists(userRepository.findByName(user))) {
                throw new UsernameAlreadyRegisteredException("Username is already registered.");
            }

            // Add the new user to the repository
            List<User> users = new ArrayList<>();
            users.add(user);
            userRepository.create(users);

        } catch (UsernameAlreadyRegisteredException e) {
            // Handle case where the username is already registered
            throw e;
        } catch (DatabaseException e) {
            // Handle repository-related exceptions
            throw new RegistrationException("An error occurred while interacting with the repository.", e);
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            throw new RegistrationException("An unexpected error occurred during user registration.", e);
        }

        return true;
    }

    public Boolean loginUser(String username, String password) {
        NamedRepository<User> userRepository;
        try {
            // Get the repository instance
            userRepository = (NamedRepository<User>) RepositoryFactory.getInstance(User.class);

            // Create a user object to search for in the repository
            User user = new User(username, password);

            // Try to find the user by username
            Optional<User> userToCompare = userRepository.findByName(user);

            // Check if user is found and compare credentials
            if (userToCompare.isPresent()) {
                return user.equals(userToCompare.get());
            } else {
                throw new LoginException("Username is not registered!");
            }
        } catch (SQLException e) {
            // Log and handle database-related errors
            e.printStackTrace();
        } catch (LoginException e) {
            // Log and handle login-specific errors
            e.printStackTrace();
        } catch (Exception e) {
            // Log and handle any other unexpected errors
            e.printStackTrace();
        }
        // Return false if login fails or an exception occurs
        return false;
    }

    public MovieTableModel createMovieTable() {
        try {
            // Get the movie repository instance using the factory
            NamedRepository<Movie> movieRepository = (NamedRepository<Movie>) RepositoryFactory.getInstance(Movie.class);

            // Fetch all movies from the repository
            List<Movie> movies = movieRepository.findAll();

            // Create and return the MovieTableModel
            return new MovieTableModel(movies);
        } catch (Exception e) {
            // Handle any exceptions and rethrow as a more specific exception
            throw new RuntimeException("Error creating movie table model", e);
        }
    }

}
