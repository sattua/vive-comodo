package main.repo;

import main.model.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Integer> {
    List<Publication> findAll();
    Publication findById(int id);

    @Query(value = "SELECT * FROM publication",
            countQuery = "SELECT count(*) FROM publication",
            nativeQuery = true)
    Page<Publication> paginate(Pageable pageable);

}
