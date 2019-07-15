package main.access;

import main.model.Rank;
import main.repo.RankRepository;
import main.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = Constant.CORS_URL)
@RestController
@RequestMapping("/rank")
public class RankController {
    @Autowired
    private RankRepository rankRepository;

    @PostMapping("/")
    public ResponseEntity<Rank> create(@RequestBody Rank r) {
        rankRepository.save(r);
        return new ResponseEntity(r, HttpStatus.OK);
    }


}