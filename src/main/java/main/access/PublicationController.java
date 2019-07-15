package main.access;

import main.model.Action;
import main.model.User;
import main.model.Publication;
import main.model.PublicationAttribute;
import main.model.PublicationImage;
import main.repo.*;
import main.service.ActionService;
import main.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@CrossOrigin(origins = Constant.CORS_URL)
@RestController
@RequestMapping("/publication")
public class PublicationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private PublicationImageRepository publicationImageRepository;
    @Autowired
    private PublicationAttributeRepository publicationAttributeRepository;
    @Autowired
    private ActionService actionService;


    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> list() {
        Pageable pageable = new PageRequest(0,100, new Sort("id"));
        Page pubs = publicationRepository.paginate(pageable);

        HashMap<String, Object> map = new HashMap<>();
        List<Publication> l = new ArrayList<>(pubs.getContent());
        map.put("data", l);
        map.put("hits", pubs.getTotalElements() + "");

        return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
    }

    @PostMapping("/")
    public Publication createPublication(@RequestBody Publication publication) {
        publication.setStatus(1);

        //TODO needs images, at least one
        List<PublicationImage> images = new ArrayList<>();
        PublicationImage i = new PublicationImage("https://via.placeholder.com/150", 0);
        publicationImageRepository.save(i);

        images.add(i);
        publication.setImages(images);
        publicationRepository.save(publication); // TODO why is saving two times?

        return publication;
    }


    @RequestMapping(value = "/anUploadEndpoint", method = RequestMethod.POST)
    @ResponseBody
    public String doUploadEndpoint(@RequestParam("file") MultipartFile uploadedFile) {
        return "ok";
    }

    @RequestMapping(value = "/")
    public ResponseEntity<Publication> get(
            @RequestParam(value="id") Long id
    ) {
        Optional<Publication> p = publicationRepository.findById(id);
        if (p == null) {
            return new ResponseEntity(p.get(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(p, HttpStatus.OK);
    }

    @RequestMapping(value = "/attribute")
    public ResponseEntity<List<PublicationAttribute>> getAttributes() {
        List<PublicationAttribute> pas = publicationAttributeRepository.findAll();
        if (pas == null) {
            return new ResponseEntity(pas, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(pas, HttpStatus.OK);
    }

    @PostMapping("/attribute")
    public PublicationAttribute createPublicationAttribute(@RequestBody PublicationAttribute a) {
        publicationAttributeRepository.save(a);
        return a;
    }


    @PatchMapping("/appointment")
    public ResponseEntity<Action> createAppointment(
            @RequestParam(value = "publicationid") Long publicationId,
            @RequestParam(value = "userid") Long userId,
            @RequestParam(value = "date") Date date) {

        if (publicationId == null) {
            return new ResponseEntity(publicationId, HttpStatus.BAD_REQUEST);
        }

        Optional<Publication> p = publicationRepository.findById(publicationId);

        if (userId == null) {
            return new ResponseEntity(userId, HttpStatus.BAD_REQUEST);
        }
        Optional<User> u = userRepository.findById(userId);

        if (date == null) {
            return new ResponseEntity(date, HttpStatus.BAD_REQUEST);
        }

        Action a = actionService.createAppointment(p.get(), u.get(), date);

        return new ResponseEntity(a, HttpStatus.OK);
    }

}
