package com.nfq.task.repository;

import com.nfq.task.domain.Reservation;
import com.nfq.task.domain.User;
import com.nfq.task.domain.Visit;
import com.nfq.task.domain.VisitStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Long> {
    List<Visit> findVisitsByUser(User user);
    Visit findVisitByReservation(Reservation reservation);
    List<Visit> findVisitsByStatus(VisitStatus status);
}
