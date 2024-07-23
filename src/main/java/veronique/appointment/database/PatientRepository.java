package veronique.appointment.database;

import org.springframework.data.repository.CrudRepository;

import veronique.appointment.models.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {
    
}
