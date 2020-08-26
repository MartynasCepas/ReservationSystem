package com.nfq.task.service;

import com.nfq.task.domain.Reservation;
import com.nfq.task.domain.User;
import com.nfq.task.repository.ReservationRepository;
import com.nfq.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    public void createReservation(Reservation reservation){
        reservation.setReservationCode(generateReservationCode(reservation));
        reservationRepository.save(reservation);
    }

    public String generateReservationCode(Reservation reservation){
       String code = String.valueOf(reservation.getFirstName().charAt(0)) + String.valueOf(reservation.getLastName().charAt(0)) + String.valueOf(reservation.getReservationId()) ;
       return code;
    }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
        return users;
    }
}
