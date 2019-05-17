package main.access;

import main.repo.PublicationRepository;
import main.util.Constant;
import main.util.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = Constant.CORS_URL)
@RestController
@RequestMapping("/publication")

public class PublicationController {

    @Autowired
    private PublicationRepository publicationRepository;

    @RequestMapping("/list")
    public ResponseObject list() {
        Pageable pageable = new PageRequest(0,100, new Sort("id"));

        Page pubs = publicationRepository.paginate(pageable);

        return new ResponseObject(pubs.getContent(), pubs.getTotalElements())  ;
    }

}
