package com.nfq.task.controller;

import com.nfq.task.domain.Reservation;
import com.nfq.task.domain.User;
import com.nfq.task.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
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
        model.addAttribute("reservation", reservation);
        return "result";
    }

    @ModelAttribute("allUsers")
    public List<User> populateUsers(){
        return reservationService.getAllUsers();
    }
}
