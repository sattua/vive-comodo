package main.model;

import javax.persistence.*;

@Entity
public class Publication {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private String address;
    private Integer status;

}
