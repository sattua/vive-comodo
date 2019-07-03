package main.access;

import main.model.Publication;
import main.model.PublicationImage;
import main.repo.PublicationRepository;
import main.repo.PublicationImageRepository;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = Constant.CORS_URL)
@RestController
@RequestMapping("/publication")
public class PublicationController {

    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private PublicationImageRepository publicationImageRepository;

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
            @RequestParam(value="id") int id
    ) {
        Publication p = publicationRepository.findById(id);
        if (p == null) {
            return new ResponseEntity(p, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(p, HttpStatus.OK);
    }

}
