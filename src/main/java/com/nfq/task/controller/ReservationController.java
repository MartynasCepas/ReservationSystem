package com.nfq.task.controller;

import com.nfq.task.domain.Reservation;
import com.nfq.task.domain.User;
import com.nfq.task.service.ReservationService;
import com.nfq.task.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final VisitService visitService;

    @Autowired
    public ReservationController(ReservationService reservationService, VisitService visitService) {
        this.reservationService = reservationService;
        this.visitService = visitService;
    }

    @GetMapping(value={"/booking/create", "/booking", "/"})
    public String createBooking(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("allUsers", populateUsers());
        return "booking";
    }

    @PostMapping("/booking/create")
    public String createBooking(@ModelAttribute Reservation reservation, Model model) {
        reservationService.createReservation(reservation);
        visitService.createVisit(reservation);
        return "result";
    }

    @PostMapping("/booking/cancel/{id}")
    public String cancelBooking(@PathVariable int id){
        System.out.println(id);
        visitService.changeVisitStatus(visitService.getVisitByReservation(reservationService.getReservationById(id)).getId(),"Cancelled");

        return "cancel";
    }

    @ModelAttribute("allUsers")
    public List<User> populateUsers(){
        return reservationService.getAllUsers();
    }
}
