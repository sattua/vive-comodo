package main.repo;

import main.util.ResponseObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseObjectRepository extends CrudRepository<ResponseObject, Long> {
    List<ResponseObject> findAll();

}
