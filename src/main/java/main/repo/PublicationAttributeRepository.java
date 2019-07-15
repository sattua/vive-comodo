package main.repo;

import main.model.PublicationAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationAttributeRepository extends JpaRepository<PublicationAttribute, Long> {

}
