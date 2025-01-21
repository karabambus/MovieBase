package DAL;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    void create(List<T> entity) throws Exception;
    void update(int id, T entity) throws Exception;
    void delete(int id) throws Exception;
    Optional<T> find_by_id(int id) throws Exception;
    List<T> findAll() throws Exception;
}
