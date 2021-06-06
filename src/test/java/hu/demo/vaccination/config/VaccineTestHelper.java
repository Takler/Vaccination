package hu.demo.vaccination.config;

import hu.demo.vaccination.domain.Vaccine;

import java.util.List;

public class VaccineTestHelper {

    public static final List<Vaccine> VACCINE_DATA = List.of(
            new Vaccine(1, "Pfizer", "mRNA", -70, 16, 999,
                    2, 28, 1, 42, true, true, true),
            new Vaccine(2, "Moderna", "mRNA", -20, 18, 999,
                    2, 28, 2, 42, true, true, true),
            new Vaccine(3, "AstraZeneca", "adenovirus", 4, 18, 999,
                    2, 84, 3, 96, true, true, true),
            new Vaccine(4, "Gamaleja 1st", "adenovirus", 4, 18, 999,
                    2, 21, 5, 35, true, false, false),
            new Vaccine(5, "Gamaleja 2nd", "adenovirus", 4, 18, 999,
                    2, 0, -1, 14, true, false, false),
            new Vaccine(6, "Sinopharm", "unactivated virus", 4, 18, 999,
                    2, 28, 6, 42, true, false, false),
            new Vaccine(7, "Janssen", "adenovirus", 4, 18, 999,
                    1, 0, -1, 14, true, false, true)
    );

    public static final List<Vaccine> VACCINE_FOR_PREGNANT_STRICTER = List.of(
            new Vaccine(1, "Pfizer", "mRNA", -70, 16, 999,
                    2, 28, 1, 42, true, true, true),
            new Vaccine(2, "Moderna", "mRNA", -20, 18, 999,
                    2, 28, 2, 42, true, true, true),
            new Vaccine(3, "AstraZeneca", "adenovirus", 4, 18, 999,
                    2, 84, 3, 96, true, true, true)
    );

    public static final List<Vaccine> VACCINE_FOR_CHRONIC_STRICT = List.of(
            new Vaccine(1, "Pfizer", "mRNA", -70, 16, 999,
                    2, 28, 1, 42, true, true, true),
            new Vaccine(2, "Moderna", "mRNA", -20, 18, 999,
                    2, 28, 2, 42, true, true, true),
            new Vaccine(3, "AstraZeneca", "adenovirus", 4, 18, 999,
                    2, 84, 3, 96, true, true, true),
            new Vaccine(7, "Janssen", "adenovirus", 4, 18, 999,
                    1, 0, -1, 14, true, false, true)
    );

    public static final List<Vaccine> VACCINE_FOR_YOUNG_STRICTEST = List.of(
            new Vaccine(1, "Pfizer", "mRNA", -70, 16, 999,
                    2, 28, 1, 42, true, true, true)
    );
}
