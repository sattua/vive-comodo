package main.model;

import javax.persistence.*;

@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String type;

    public Rol() {
    }

    public Rol(Rol.Type type) {
        this.type = type.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
