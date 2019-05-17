package main.repo;

import main.model.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PublicationRepository extends CrudRepository<Publication, Integer> {
    List<Publication> findAll();


    @Query(value = "SELECT * FROM publication",
            countQuery = "SELECT count(*) FROM publication",
            nativeQuery = true)
    Page<Publication> paginate(Pageable pageable);

}
