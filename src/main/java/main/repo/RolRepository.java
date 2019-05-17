package main.repo;

import main.model.Rol;
import org.springframework.data.repository.CrudRepository;

public interface RolRepository extends CrudRepository<Rol, Integer> {

    Rol findByType(String type);
}