package veronique.appointment.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class AppointmentDTO {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private int duration;
    private String status;
    private Long doctorId;
    private String doctorName; 
    private Long patientId;
    private String patientName;
    private String PatientUserName;
    private String patientPhone;
}
