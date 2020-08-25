package com.nfq.task.service;

import com.nfq.task.domain.Reservation;
import com.nfq.task.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void createReservation(Reservation reservation){
        reservation.setReservationCode(generateReservationCode(reservation));
        reservationRepository.save(reservation);
    }

    public String generateReservationCode(Reservation reservation){
       String code = String.valueOf(reservation.getFirstName().charAt(0)) + String.valueOf(reservation.getLastName().charAt(0)) + String.valueOf(reservation.getReservationId()) ;
       return code;
    }
}
