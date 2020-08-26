package com.nfq.task.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Date;


@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private int reservationId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String reservationDate;
    private String reservationCode;
    private Long selectedId;

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }


    public Long getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(Long selectedId) {
        this.selectedId = selectedId;
    }

    public Integer getReservationHours(){

        String[] time = reservationDate.split(":");
        return Integer.valueOf(time[0]);
    }

    public Integer getReservationMinutes(){

        String[] time = reservationDate.split(":");
        return Integer.valueOf(time[1]);
    }
}
