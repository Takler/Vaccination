package hu.demo.vaccination.repository;

import hu.demo.vaccination.utility.CenterInit;
import hu.demo.vaccination.utility.PatientInit;
import hu.demo.vaccination.utility.VaccineInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InitializationRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public InitializationRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void initialization() {
        //TODO write the missing inits the order is drops: the smaller connection tables first, after big tables
        //TODO create: first big tables, after the smaller ones
        jdbc.execute(PatientInit.PATIENT_DROP_TABLE);
        jdbc.execute(VaccineInit.VACCINE_DROP_TABLE);
        jdbc.execute(CenterInit.CENTER_DROP_TABLE);

    }
}
