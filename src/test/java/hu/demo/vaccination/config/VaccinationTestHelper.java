package hu.demo.vaccination.config;

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

    public static final List<Integer> VACCINE_IDS = IntStream.rangeClosed(1, 7).boxed().collect(Collectors.toList());

    public static final List<Integer> PATIENT_IDS = IntStream.rangeClosed(1, 21).boxed().collect(Collectors.toList());

    public static final List<Integer> SHIFT_IDS = IntStream.rangeClosed(1, 8).boxed().collect(Collectors.toList());
}
