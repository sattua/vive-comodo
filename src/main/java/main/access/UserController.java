package main.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import main.model.User;
import main.model.Rol;
import main.repo.UserRepository;
import main.repo.RolRepository;
import main.util.Constant;

@CrossOrigin(origins = Constant.CORS_URL)
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolRepository rolRepository;

    @PostMapping("/signin")
    public ResponseEntity<User> signIn(
            @RequestParam(value="firstName") String fName,
            @RequestParam(value="lastName") String lName,
            @RequestParam(value="email") String email) {

        if (fName == null || lName == null || email == null ||
            fName.isEmpty() || lName.isEmpty() || email.isEmpty()) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Rol guest = rolRepository.findByType(Rol.Type.GUEST.toString());
        if (guest == null) {
            RolSeeds();
            guest = rolRepository.findByType(Rol.Type.GUEST.toString());
        }

        try {
            User u = createUser(fName,lName, email, guest);
            return new ResponseEntity(u, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/owner")
    public User createOwner(
            @RequestParam(value="firstName") String fName,
            @RequestParam(value="lastName") String lName,
            @RequestParam(value="email") String email) {
        Rol owner = rolRepository.findByType(Rol.Type.OWNER.toString());
        if (owner == null) {
            RolSeeds();
            owner = rolRepository.findByType(Rol.Type.OWNER.toString());
        }
        return createUser(fName,lName, email, owner);
    }

    @PostMapping("/admin")
    public User createAdmin(
            @RequestParam(value="firstName") String fName,
            @RequestParam(value="lastName") String lName,
            @RequestParam(value="email") String email) {
        Rol admin = rolRepository.findByType(Rol.Type.ADMIN.toString());
        if (admin == null) {
            RolSeeds();
            admin = rolRepository.findByType(Rol.Type.ADMIN.toString());
        }
        return createUser(fName,lName, email, admin);
    }

    @RequestMapping("/login")
    public User login(@RequestParam(value="email") String email) {
        User u = userRepository.findByEmail(email);
        return u;
    }

    @RequestMapping("/test")
    public String test() {
        return "Tested, and working";
    }

    private User createUser(String fName, String lName, String email, Rol rol) {
        User n = new User(fName, lName, email, rol);
        userRepository.save(n);
        return n;
    }

    private void RolSeeds () {
        Rol admin = new Rol(Rol.Type.ADMIN);
        Rol owner = new Rol(Rol.Type.OWNER);
        Rol guest = new Rol(Rol.Type.GUEST);
        rolRepository.save(admin);
        rolRepository.save(owner);
        rolRepository.save(guest);
    }

}