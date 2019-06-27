package main.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    @ManyToMany(targetEntity=Rol.class, fetch=FetchType.EAGER)
    private List<Rol> roles;

    User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    User(String firstName, String lastName, String email, Rol type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = new ArrayList<>();
        this.roles.add(type);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public void setARol(Rol rol) {
        if (this.roles == null || this.roles.size() < 1) {
            this.roles = new ArrayList<>();
        }
        this.roles.add(rol);
    }
}
