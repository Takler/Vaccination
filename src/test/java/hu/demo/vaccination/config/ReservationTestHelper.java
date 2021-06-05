package hu.demo.vaccination.config;

import hu.demo.vaccination.domain.Reservation;
import hu.demo.vaccination.dto.reservation.ReservationCreateData;

import java.time.LocalDate;

public class ReservationTestHelper {

    public static final int PATIENT_ID = 123123123;
    public static final int RESERVATION_ID = 1;
    public static final int OTHER_ID = 1;
    public static final int CENTER_ID = 1;
    public static final int VACCINE_ID = 1;

    public static Reservation getSampleReservation() {
        return new Reservation(RESERVATION_ID, PATIENT_ID, CENTER_ID, VACCINE_ID,
                LocalDate.now(), LocalDate.now().plusDays(14), false);
    }

    public static ReservationCreateData getSampleReservationCreateData() {
        return new ReservationCreateData(PATIENT_ID, CENTER_ID, VACCINE_ID,
                LocalDate.now(), LocalDate.now().plusDays(14));
    }

    public static ReservationCreateData getSecondSampleReservationCreateData() {
        return new ReservationCreateData(157648531, 12, 1, LocalDate.now(), LocalDate.now().plusDays(10));
    }
}
