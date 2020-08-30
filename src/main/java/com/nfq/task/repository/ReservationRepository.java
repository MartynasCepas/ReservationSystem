package com.nfq.task.repository;

import com.nfq.task.domain.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
    Reservation findByReservationId(int reservationId);
}
