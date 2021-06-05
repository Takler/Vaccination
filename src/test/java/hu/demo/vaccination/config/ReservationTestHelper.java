package hu.demo.vaccination.config;

import hu.demo.vaccination.domain.Reservation;
import hu.demo.vaccination.dto.reservation.ReservationCreateData;

import java.time.LocalDate;

public class ReservationTestHelper {

    public static final int RESERVATION_1_ID = 1;
    public static final int RESERVATION_1_PATIENT_ID = 123123123;
    public static final int RESERVATION_1_CENTER_ID = 1;
    public static final int RESERVATION_1_VACCINE_ID = 1;
    public static final LocalDate RESERVATION_1_REGISTRATION = LocalDate.now();
    public static final LocalDate RESERVATION_1_NEXT_SHOT = LocalDate.now().plusDays(14);

    public static final int RESERVATION_2_ID = 2;
    public static final int RESERVATION_2_PATIENT_ID = 157648531;
    public static final int RESERVATION_2_CENTER_ID = 12;
    public static final int RESERVATION_2_VACCINE_ID = 1;
    public static final LocalDate RESERVATION_2_REGISTRATION = LocalDate.now();
    public static final LocalDate RESERVATION_2_NEXT_SHOT = LocalDate.now().plusDays(10);

    public static Reservation getReservationOne() {
        return new Reservation(RESERVATION_1_ID, RESERVATION_1_PATIENT_ID, RESERVATION_1_CENTER_ID, RESERVATION_1_VACCINE_ID,
                RESERVATION_1_REGISTRATION, RESERVATION_1_NEXT_SHOT, false);
    }

    public static Reservation getReservationTwo() {
        return new Reservation(RESERVATION_2_ID, RESERVATION_2_PATIENT_ID, RESERVATION_2_CENTER_ID, RESERVATION_2_VACCINE_ID,
                RESERVATION_2_REGISTRATION, LocalDate.now().plusDays(14), false);
    }

    public static ReservationCreateData getReservationOneCreateData() {
        return new ReservationCreateData(RESERVATION_1_PATIENT_ID, RESERVATION_1_CENTER_ID, RESERVATION_1_VACCINE_ID,
                RESERVATION_1_REGISTRATION, RESERVATION_1_NEXT_SHOT);
    }

    public static ReservationCreateData getReservationTwoCreateData() {
        return new ReservationCreateData(RESERVATION_2_PATIENT_ID, RESERVATION_2_CENTER_ID, RESERVATION_2_VACCINE_ID,
                RESERVATION_2_REGISTRATION, RESERVATION_2_NEXT_SHOT);
    }
}
