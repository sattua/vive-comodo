package main.service;

import main.model.Action;
import main.model.User;
import main.model.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ActionService {

    @Autowired
    private MultithreadedActionService multithreadedActionService;

    public Action createAppointment(Publication publication, User user, Date date) {

        multithreadedActionService.setName("creating-appointment-thread");
        multithreadedActionService.setAction(publication, user, date);
        try {
            multithreadedActionService.start();
        } catch (Exception e) {

        }
        return multithreadedActionService.getAppointment();
    }
}
