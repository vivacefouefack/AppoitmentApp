package veronique.appointment.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import veronique.appointment.dto.CalendarDTO;
import veronique.appointment.models.Calendar;
import veronique.appointment.services.CalendarService;

@RestController
@RequestMapping("/calendars")
@CrossOrigin(origins = "*") 
public class CalendarController {
    @Autowired
    private CalendarService calendarService;

    @GetMapping("/all")
    public ResponseEntity<List<CalendarDTO>> getAllCalendars() {
        try {
            List<CalendarDTO> calendars = calendarService.getAllCalendars();
            return ResponseEntity.ok(calendars);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Calendar> getCalendarById(@PathVariable Long id) {
        try {
            Optional<Calendar> calendar = calendarService.getCalendarById(id);
            return calendar.map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Calendar> saveCalendar(@RequestBody Calendar calendar) {
        try {
            Calendar savedCalendar = calendarService.saveCalendar(calendar);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCalendar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalendar(@PathVariable Long id) {
        try {
            calendarService.deleteCalendar(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
