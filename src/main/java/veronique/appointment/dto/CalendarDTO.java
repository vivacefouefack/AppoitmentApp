package veronique.appointment.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CalendarDTO {
    private Long id;
    private Long doctorId;
    private String doctorName; 
    private LocalDate date;
    private String availableSlots;
}
