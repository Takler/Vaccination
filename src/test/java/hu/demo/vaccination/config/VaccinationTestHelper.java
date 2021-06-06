package hu.demo.vaccination.config;

import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.domain.Shift;
import hu.demo.vaccination.domain.Vaccination;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class VaccinationTestHelper {

    public static final List<Vaccination> VACCINATION_DATA = List.of(
            new Vaccination(1, 1, 3, 1, LocalDate.now().minusDays(10), false),
            new Vaccination(2, 1, 4, 2, LocalDate.now().minusDays(9), false),
            new Vaccination(3, 1, 5, 3, LocalDate.now().minusDays(8), false),
            new Vaccination(4, 3, 6, 4, LocalDate.now().minusDays(7), false),
            new Vaccination(5, 4, 7, 5, LocalDate.now().minusDays(6), false),
            new Vaccination(6, 2, 8, 6, LocalDate.now().minusDays(5), false),
            new Vaccination(7, 2, 9, 1, LocalDate.now().minusDays(10), false),
            new Vaccination(8, 1, 10, 2, LocalDate.now().minusDays(9), false),
            new Vaccination(9, 2, 11, 2, LocalDate.now().minusDays(9), false),
            new Vaccination(10, 3, 12, 4, LocalDate.now().minusDays(7), false),
            new Vaccination(11, 3, 14, 5, LocalDate.now().minusDays(6), false),
            new Vaccination(12, 6, 15, 1, LocalDate.now().minusDays(10), false),
            new Vaccination(13, 1, 17, 2, LocalDate.now().minusDays(9), false),
            new Vaccination(14, 6, 18, 3, LocalDate.now().minusDays(8), false),
            new Vaccination(15, 7, 20, 5, LocalDate.now().minusDays(6), false),
            new Vaccination(16, 2, 21, 5, LocalDate.now().minusDays(6), false),

            new Vaccination(17, 1, 4, 6, LocalDate.now().minusDays(4), false),
            new Vaccination(18, 3, 6, 6, LocalDate.now().minusDays(4), false),
            new Vaccination(19, 5, 7, 7, LocalDate.now().minusDays(3), false),
            new Vaccination(20, 3, 14, 7, LocalDate.now().minusDays(3), false),
            new Vaccination(21, 1, 17, 8, LocalDate.now().minusDays(2), false),
            new Vaccination(22, 6, 18, 8, LocalDate.now().minusDays(2), false),
            new Vaccination(23, 2, 21, 8, LocalDate.now().minusDays(2), false)
    );

    public static final List<Patient> PATIENT_DATA = List.of(
            new Patient(1, "female", LocalDate.now().minusYears(16).minusDays(5), "1234", false, false),
            new Patient(2, "male", LocalDate.now().minusYears(16).minusDays(150), "2345", false, false),
            new Patient(3, "female", LocalDate.now().minusYears(16).minusDays(300), "3456", false, false),
            new Patient(4, "male", LocalDate.now().minusYears(17).minusDays(125), "4567", false, true),
            new Patient(5, "female", LocalDate.now().minusYears(17).minusDays(356), "5678", true, false),
            new Patient(6, "female", LocalDate.now().minusYears(20).minusDays(50), "1234", true, false),
            new Patient(7, "male", LocalDate.now().minusYears(25).minusDays(85), "6789", false, false),
            new Patient(8, "male", LocalDate.now().minusYears(30).minusDays(258), "7890", false, true),
            new Patient(9, "female", LocalDate.now().minusYears(35).minusDays(182), "8901", true, false),
            new Patient(10, "female", LocalDate.now().minusYears(40).minusDays(71), "1234", true, true),
            new Patient(11, "male", LocalDate.now().minusYears(45).minusDays(5), "9123", false, true),
            new Patient(12, "male", LocalDate.now().minusYears(50).minusDays(86), "2345", false, false),
            new Patient(13, "female", LocalDate.now().minusYears(55).minusDays(164), "3456", true, false),
            new Patient(14, "female", LocalDate.now().minusYears(60).minusDays(58), "4567", false, true),
            new Patient(15, "female", LocalDate.now().minusYears(65).minusDays(134), "1234", false, false),
            new Patient(16, "male", LocalDate.now().minusYears(70).minusDays(254), "5678", false, true),
            new Patient(17, "female", LocalDate.now().minusYears(75).minusDays(244), "6789", false, true),
            new Patient(18, "male", LocalDate.now().minusYears(80).minusDays(95), "2345", false, false),
            new Patient(19, "female", LocalDate.now().minusYears(85).minusDays(220), "8901", false, true),
            new Patient(20, "female", LocalDate.now().minusYears(90).minusDays(128), "1234", false, false),
            new Patient(21, "male", LocalDate.now().minusYears(95).minusDays(1), "2345", false, true)
    );

    public static final List<Integer> VACCINE_IDS = IntStream.rangeClosed(1, 7).boxed().collect(Collectors.toList());

    public static final List<Integer> PATIENT_IDS = IntStream.rangeClosed(1, 21).boxed().collect(Collectors.toList());

    public static final List<Integer> SHIFT_IDS = IntStream.rangeClosed(1, 8).boxed().collect(Collectors.toList());

    public static final List<Shift> SHIFT_DATA = SHIFT_IDS.stream()
            .map(id -> {
                Shift shift = new Shift();
                shift.setId(id);
                return shift;
            })
            .collect(Collectors.toList());
}
