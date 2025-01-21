package Utilities;

import java.util.Optional;

public class SQLUtils {
    private SQLUtils() {}

    public static <T> boolean exists(Optional<T> object) {
        return object.isPresent();
    }
}
