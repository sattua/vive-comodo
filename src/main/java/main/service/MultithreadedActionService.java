package main.service;

import main.model.Action;
import main.model.Publication;
import main.model.User;
import main.repo.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MultithreadedActionService extends Thread{

    @Autowired
    private ActionRepository actionRepository;

    private Date date;
    private User user;
    private Publication publication;
    private Action action;

    public void setAction(Publication publication, User user, Date date) {
        this.date = date;
        this.user = user;
        this.publication = publication;
    }

    public Action getAppointment () {
        return this.action;
    }

    @Override
    public void run() {
        Action a = actionRepository.findByDateAndAndUserAndPublication(this.date, this.user, this.publication);

        if (a == null) {
            Action action = new Action(this.user, this.publication, this.date);
            actionRepository.save(action);
            this.action = action;
        }
    }
}
