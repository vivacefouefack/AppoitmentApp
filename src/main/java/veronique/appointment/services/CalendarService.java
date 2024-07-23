package veronique.appointment.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import veronique.appointment.database.CalendarRepository;
import veronique.appointment.dto.CalendarDTO;
import veronique.appointment.models.Calendar;

@Service
public class CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;

    public List<CalendarDTO> getAllCalendars() {
        List<Calendar> calendars = (List<Calendar>) calendarRepository.findAll();
        return calendars.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private CalendarDTO convertToDTO(Calendar calendar) {
        CalendarDTO calendarDTO = new CalendarDTO();
        calendarDTO.setId(calendar.getId());
        calendarDTO.setDoctorId(calendar.getDoctor().getId());
        calendarDTO.setDoctorName(calendar.getDoctor().getLastName()); 
        calendarDTO.setDate(calendar.getDate());
        calendarDTO.setAvailableSlots(calendar.getAvailableSlots());
        return calendarDTO;
    }

    public Optional<Calendar> getCalendarById(Long id) {
        return calendarRepository.findById(id);
    }

    public Calendar saveCalendar(Calendar calendar) {
        return calendarRepository.save(calendar);
    }

    public void deleteCalendar(Long id) {
        calendarRepository.deleteById(id);
    }
}
