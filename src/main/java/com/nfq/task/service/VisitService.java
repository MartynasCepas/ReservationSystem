package com.nfq.task.service;

import com.nfq.task.domain.Reservation;
import com.nfq.task.domain.User;
import com.nfq.task.domain.Visit;
import com.nfq.task.domain.VisitStatus;
import com.nfq.task.repository.UserRepository;
import com.nfq.task.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class VisitService {

    private final VisitRepository visitRepository;
    private final UserRepository userRepository;

    @Autowired
    public VisitService(VisitRepository visitRepository, UserRepository userRepository) {
        this.visitRepository = visitRepository;
        this.userRepository = userRepository;
    }

    public void createVisit(Reservation reservation){
        User user = userRepository.findById(reservation.getSelectedId()).orElseThrow(() -> new EntityNotFoundException(String.valueOf(reservation.getSelectedId())));
        Visit visit = new Visit();
        visit.setReservation(reservation);
        visit.setUser(user);
        visit.setStatus(VisitStatus.CREATED);
        visitRepository.save(visit);
    }

    public Visit getVisitByReservation(Reservation reservation){
        return visitRepository.findVisitByReservation(reservation);
    }

    public List<Visit> getVisitsByUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User user = userRepository.findByEmail(currentUserName);


        List<Visit> visits = visitRepository.findVisitsByUser(user);

        return visits;
    }

    public void changeVisitStatus(Long id, String newStatus){
        List<Visit> visits = visitRepository.findVisitsByStatus(VisitStatus.STARTED);
        if(visits.size() == 0 || visits.get(0).getId() == id)
        {
        Visit visit = visitRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        switch (newStatus) {
            case "Started":
                visit.setStatus(VisitStatus.STARTED);
                break;
            case "Ended":
                visit.setStatus(VisitStatus.ENDED);
                break;
            case "Cancelled":
                visit.setStatus(VisitStatus.CANCELLED);
                break;
        }
        visitRepository.save(visit);
        }
    }

    public List<Visit> getCurrentVisits(){
        return visitRepository.findVisitsByStatus(VisitStatus.STARTED);
    }
}
