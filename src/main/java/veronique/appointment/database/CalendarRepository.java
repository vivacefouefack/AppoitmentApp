package veronique.appointment.database;

import org.springframework.data.repository.CrudRepository;

import veronique.appointment.models.Calendar;

public interface CalendarRepository extends CrudRepository<Calendar,Long> {
    
}
