package hu.demo.vaccination.config;

import hu.demo.vaccination.domain.Center;
import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.domain.Reservation;
import hu.demo.vaccination.domain.Vaccine;
import hu.demo.vaccination.dto.reservation.PatientReservationData;
import hu.demo.vaccination.dto.reservation.ReservationCreateData;
import hu.demo.vaccination.dto.reservation.ReservationInfoData;
import hu.demo.vaccination.dto.reservation.ReservationNameInfoData;

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

    public static final int RESERVATION_NAME_INFO_1_ID = 1;
    public static final String RESERVATION_NAME_INFO_1_PATIENT_NAME = "Frigyes Csonka";
    public static final String RESERVATION_NAME_INFO_1_CENTER_NAME = "Honvéd Kórház";
    public static final String RESERVATION_NAME_INFO_1_VACCINE_NAME = "Moderna";
    public static final LocalDate RESERVATION_NAME_INFO_1_REGISTRATION = LocalDate.now();
    public static final LocalDate RESERVATION_NAME_INFO_1_NEXT_SHOT = LocalDate.now().plusDays(28);

    public static final int PATIENT_RESERVATION_1_RESERVATION_ID = 1;
    public static final int PATIENT_RESERVATION_1_PATIENT_ID = 123123123;
    public static final String PATIENT_RESERVATION_1_PATIENT_NAME = "Zobor Csongor";
    public static final String PATIENT_RESERVATION_1_CENTER_NAME = "Szent György Kórház";
    public static final String PATIENT_RESERVATION_1_VACCINE_NAME = "Janssen";
    public static final LocalDate PATIENT_RESERVATION_1_REGISTRATION = LocalDate.now();
    public static final LocalDate PATIENT_RESERVATION_1_NEXT_SHOT = LocalDate.now().plusDays(20);

    public static final int RESERVATION_INFO_1_RESERVATION_ID = 1;
    public static final LocalDate RESERVATION_INFO_1_REGISTRATION = LocalDate.now();
    public static final LocalDate RESERVATION_INFO_1_NEXT_SHOT = LocalDate.now().plusDays(22);


    public static Reservation getReservationOne() {
        return new Reservation(RESERVATION_1_ID, RESERVATION_1_PATIENT_ID, RESERVATION_1_CENTER_ID, RESERVATION_1_VACCINE_ID,
                RESERVATION_1_REGISTRATION, RESERVATION_1_NEXT_SHOT, false);
    }

    public static Reservation getReservationTwo() {
        return new Reservation(RESERVATION_2_ID, RESERVATION_2_PATIENT_ID, RESERVATION_2_CENTER_ID, RESERVATION_2_VACCINE_ID,
                RESERVATION_2_REGISTRATION, RESERVATION_2_NEXT_SHOT, false);
    }

    public static ReservationCreateData getReservationOneCreateData() {
        return new ReservationCreateData(RESERVATION_1_PATIENT_ID, RESERVATION_1_CENTER_ID, RESERVATION_1_VACCINE_ID,
                RESERVATION_1_REGISTRATION, RESERVATION_1_NEXT_SHOT);
    }

    public static ReservationCreateData getReservationTwoCreateData() {
        return new ReservationCreateData(RESERVATION_2_PATIENT_ID, RESERVATION_2_CENTER_ID, RESERVATION_2_VACCINE_ID,
                RESERVATION_2_REGISTRATION, RESERVATION_2_NEXT_SHOT);
    }

    public static ReservationNameInfoData getReservationNameInfoDataOne() {
        return new ReservationNameInfoData(RESERVATION_NAME_INFO_1_ID, RESERVATION_NAME_INFO_1_PATIENT_NAME,
                RESERVATION_NAME_INFO_1_CENTER_NAME, RESERVATION_NAME_INFO_1_VACCINE_NAME,
                RESERVATION_NAME_INFO_1_REGISTRATION, RESERVATION_NAME_INFO_1_NEXT_SHOT);

    }

    public static PatientReservationData getPatientReservationDataOne() {
        return new PatientReservationData(PATIENT_RESERVATION_1_RESERVATION_ID, PATIENT_RESERVATION_1_PATIENT_ID,
                PATIENT_RESERVATION_1_PATIENT_NAME, PATIENT_RESERVATION_1_CENTER_NAME,
                PATIENT_RESERVATION_1_VACCINE_NAME, PATIENT_RESERVATION_1_REGISTRATION,
                PATIENT_RESERVATION_1_NEXT_SHOT);
    }

    public static ReservationInfoData getReservationInfoDataOne() {
        Patient patient = PatientTestHelper.getPatientOne();

        Vaccine vaccine = new Vaccine(2, "Moderna", "mRNA", -20, 18,
                999, 2, 2, 28, 42, true,
                true, true);

        Center center = new Center(12, "Honvéd Kórház", "Budapest", "honved@honved.hu",
                "+3619877651", 1000, false);

        return new ReservationInfoData(RESERVATION_INFO_1_RESERVATION_ID, patient, center, vaccine,
                RESERVATION_INFO_1_REGISTRATION, RESERVATION_INFO_1_NEXT_SHOT);
    }
}
