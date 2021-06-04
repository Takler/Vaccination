package hu.demo.vaccination.config;

import hu.demo.vaccination.domain.Vaccine;

import java.util.List;

public class VaccineTestHelper {

    public static final List<Vaccine> VACCINE_DATA = List.of(
            new Vaccine(1, "Pfizer", "mRNA", -70, 16, 999,
                    2, 1, 28, 42, true, true, true),
            new Vaccine(2, "Moderna", "mRNA", -20, 18, 999,
                    2, 2, 28, 42, true, true, true),
            new Vaccine(3, "AstraZeneca", "adenovirus", 4, 18, 999,
                    2, 3, 84, 96, true, true, true),
            new Vaccine(4, "Gamaleja 1st", "adenovirus", 4, 18, 999,
                    2, 5, 21, 35, true, false, false),
            new Vaccine(5, "Gamaleja 2nd", "adenovirus", 4, 18, 999,
                    2, -1, 0, 14, true, false, false),
            new Vaccine(6, "Sinopharm", "unactivated virus", 4, 18, 999,
                    2, 6, 28, 42, true, false, false),
            new Vaccine(7, "Janssen", "adenovirus", 4, 18, 999,
                    1, -1, 0, 14, true, false, true)
    );
}
