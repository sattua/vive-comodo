package main.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String type;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date dateCreated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated")
    private Date lastUpdated;

    public Rol(Rol.Type type) {
        this.type = type.toString();
    }

    public Rol() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return Rol.Type.valueOf(this.type).toString();
    }

    public void setType(Rol.Type type) {
        this.type = type.toString();
    }

    public enum Type {
        GUEST (Constants.GUEST),
        OWNER (Constants.OWNER),
        ADMIN (Constants.ADMIN);

        Type(String typeString) {
        }

        public static class Constants {
            public static final String GUEST = "guest";
            public static final String OWNER = "owner";
            public static final String ADMIN = "administrator";
        }
    }
}
