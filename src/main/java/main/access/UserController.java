package main.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<User> signIn(@RequestBody User user, @RequestParam(value = "rol") String rol) {
        // TODO refactor - FE is not sending the rol id? ..
        if (isInvalidNewUser(user)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        try {
            user.setARol(getNewUserRol(rol));
            userRepository.save(user);
            return new ResponseEntity(user, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Rol getNewUserRol(String rolString) {
        switch (rolString.toLowerCase()) {
            case "guest":
                return createNewRol(Rol.Type.GUEST.toString());

            case "owner":
                return createNewRol(Rol.Type.GUEST.toString());

            case "admin":
                return createNewRol(Rol.Type.GUEST.toString());

            default:
                return createNewRol(Rol.Type.GUEST.toString());

        }
    }

    //TODO move to Rol Service
    private Rol createNewRol(String rolName) {
        Rol rol = rolRepository.findByType(rolName);
        if (rol == null) {
            RolSeeds();
            rol = rolRepository.findByType(rolName);
        }
        return rol;
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

    private void RolSeeds () {
        Rol admin = new Rol(Rol.Type.ADMIN);
        Rol owner = new Rol(Rol.Type.OWNER);
        Rol guest = new Rol(Rol.Type.GUEST);
        rolRepository.save(admin);
        rolRepository.save(owner);
        rolRepository.save(guest);
    }

    private Boolean isInvalidNewUser(User u) {
        return u.getFirstName() == null || u.getLastName() == null || u.getEmail() == null ||
                u.getFirstName().isEmpty() || u.getLastName().isEmpty() || u.getEmail().isEmpty();
    }

}