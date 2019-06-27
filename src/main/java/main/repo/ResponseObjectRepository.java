package main.repo;

import main.util.ResponseObject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResponseObjectRepository extends CrudRepository<ResponseObject, Integer> {
    List<ResponseObject> findAll();

}
