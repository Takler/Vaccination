package hu.demo.vaccination.repository;

import hu.demo.vaccination.utility.*;
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
        //TODO remove comment when ready :)

        jdbc.execute(ShiftInit.CREATE_TABLE_SHIFT);
        jdbc.execute(InventoryInit.INVENTORY_DROP_TABLE);
        jdbc.execute(VaccinationInit.VACCINATION_DROP_TABLE);
        jdbc.execute(ReservationInit.RESERVATION_DROP_TABLE);

        jdbc.execute(DoctorInit.DROP_TABLE_DOCTOR);
        jdbc.execute(PatientInit.PATIENT_DROP_TABLE);
        jdbc.execute(VaccineInit.VACCINE_DROP_TABLE);
        jdbc.execute(CenterInit.CENTER_DROP_TABLE);

        jdbc.execute(DoctorInit.CREATE_TABLE_DOCTOR);
        jdbc.execute(PatientInit.PATIENT_INIT_TABLE);
        jdbc.execute(VaccineInit.VACCINE_INIT_TABLE);
        jdbc.execute(CenterInit.CENTER_INIT_TABLE);

        jdbc.execute(DoctorInit.INSERT_SAMLE_DATA_DOCTOR);
        jdbc.execute(VaccineInit.VACCINE_DATA_INSERT);
        jdbc.execute(PatientInit.PATIENT_SAMPLE_DATA_INSERT);
        jdbc.execute(CenterInit.CENTER_SAMPLE_DATA_INSERT);

        jdbc.execute(InventoryInit.INVENTORY_INIT_TABLE);
        //jdbc.execute(ShiftInit.SHIFT_INIT_TABLE);
        jdbc.execute(VaccinationInit.VACCINATION_INIT_TABLE);
        jdbc.execute(ReservationInit.RESERVATION_INIT_TABLE);


    }
}
