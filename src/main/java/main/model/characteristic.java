package main.model;

import javax.persistence.*;

public class characteristic {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
}
