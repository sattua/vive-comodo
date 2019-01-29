package access;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.User;

@RestController
public class UserController {

    @RequestMapping("/login")
    public User LogIn(@RequestParam(value="name", defaultValue="World") String name){
        return new User(1l, name);
    }

}
