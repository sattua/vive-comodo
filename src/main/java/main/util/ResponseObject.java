package main.util;

import javax.persistence.*;

@Entity
public class ResponseObject {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String body = null;
    private Long hits = 0l;

    public ResponseObject(String body, Long hits) {
        this.body = body;
        this.hits = hits;
    }
}
