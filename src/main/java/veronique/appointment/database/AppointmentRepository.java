package veronique.appointment.database;

import org.springframework.data.repository.CrudRepository;

import veronique.appointment.models.Appointment;

public interface AppointmentRepository extends CrudRepository<Appointment,Long> {
    
}
