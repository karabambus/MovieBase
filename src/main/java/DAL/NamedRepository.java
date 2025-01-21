package DAL;

import java.util.Optional;

public interface NamedRepository<T> extends Repository<T> {
    Optional<T> findByName(T entity) throws Exception;
}
