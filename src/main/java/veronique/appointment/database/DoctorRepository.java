package veronique.appointment.database;


import org.springframework.data.repository.CrudRepository;

import veronique.appointment.models.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor,Long>{
    
}
