package main.repo;

import main.model.PublicationImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationImageRepository extends JpaRepository<PublicationImage, Long> {


}