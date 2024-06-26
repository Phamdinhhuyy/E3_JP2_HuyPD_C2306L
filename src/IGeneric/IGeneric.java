package IGeneric;

import java.util.Optional;

public interface IGeneric <T>{
    T add();
    T delete(T t);
    Optional<T> findById(String id);
}
