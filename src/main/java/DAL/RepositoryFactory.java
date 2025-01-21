package DAL;

import DAL.SQL.DataSourceSingelton;
import Model.SQL.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class RepositoryFactory {

    public static final String PATH = "/config/repository.properties";
    private static final Properties PROPERTIES = new Properties();

    // Map to hold the association between model classes and repository class names
    private static final Map<Class, String> REPOSITORY_MAP = new HashMap<>();

    static {
        try (InputStream in = DataSourceSingelton.class.getResourceAsStream(PATH)) {
            // Load properties from the configuration file
            if (in == null) {
                throw new RuntimeException("Properties file not found: " + PATH);
            }
            PROPERTIES.load(in);

            // Populate the repository map based on the properties file
            REPOSITORY_MAP.put(Actor.class, PROPERTIES.getProperty("repository.Model.SQL.Actor"));
            REPOSITORY_MAP.put(Movie.class, PROPERTIES.getProperty("repository.Model.SQL.Movie"));
            REPOSITORY_MAP.put(Genre.class, PROPERTIES.getProperty("repository.Model.SQL.Genre"));
            REPOSITORY_MAP.put(Director.class, PROPERTIES.getProperty("repository.Model.SQL.Director"));
            REPOSITORY_MAP.put(Directed.class, PROPERTIES.getProperty("repository.Model.SQL.Directed"));
            REPOSITORY_MAP.put(GenreJunction.class, PROPERTIES.getProperty("repository.Model.SQL.GenreJunction"));
            REPOSITORY_MAP.put(Starring.class, PROPERTIES.getProperty("repository.Model.SQL.Starring"));
            REPOSITORY_MAP.put(User.class, PROPERTIES.getProperty("repository.Model.SQL.User"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize repository mapping", e);
        }
    }

    private RepositoryFactory() {
        // Private constructor to prevent instantiation
    }

    public static Repository getInstance(Class modelClass) throws Exception {
        // Retrieve the repository class name from the map
        String className = REPOSITORY_MAP.get(modelClass);

        if (className == null) {
            throw new RuntimeException("No repository found for model class: " + modelClass.getName());
        }

        try {
            // Dynamically load the repository class based on the repository class name
            Class<?> repositoryClass = Class.forName(className);

            // Check if the repository class is of type Repository
            if (!Repository.class.isAssignableFrom(repositoryClass)) {
                throw new RuntimeException("Class " + className + " does not implement Repository interface.");
            }

            // Instantiate the repository using reflection
            Repository repository = (Repository) repositoryClass.getDeclaredConstructor().newInstance();

            // If the repository implements NamedRepository, we can cast it
            if (NamedRepository.class.isAssignableFrom(repositoryClass)) {
                // Cast to NamedRepository if it implements it
                return (NamedRepository) repository;
            }

            return repository;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load repository for model class: " + modelClass.getName(), e);
        }
    }
}
