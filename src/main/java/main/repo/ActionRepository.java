package main.repo;

import main.model.Action;
import main.model.Publication;
import main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {

    Action findByUser(User user);
    Action findByDateAndAndUserAndPublication(Date date, User user, Publication publication);
}