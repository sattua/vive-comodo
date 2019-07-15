package main.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Rank {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private User user;
    private Integer rank;
    private User reviewer;
    private Publication publication;


    public Rank(User user, Integer rank, User reviewer, Publication publication) {
        this.user = user;
        this.rank = rank;
        this.reviewer = reviewer;
        this.publication = publication;
    }
}
