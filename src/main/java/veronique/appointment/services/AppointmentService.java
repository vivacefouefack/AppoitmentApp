package veronique.appointment.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import veronique.appointment.database.AppointmentRepository;
import veronique.appointment.database.DoctorRepository;
import veronique.appointment.database.PatientRepository;
import veronique.appointment.dto.AppointmentDTO;
import veronique.appointment.models.Appointment;
import veronique.appointment.models.Doctor;
import veronique.appointment.models.Patient;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public List<AppointmentDTO> getAllAppointments() {
        List<Appointment> appointments = (List<Appointment>) appointmentRepository.findAll();
        return appointments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private AppointmentDTO convertToDTO(Appointment appointment) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setDate(appointment.getDate());
        appointmentDTO.setTime(appointment.getTime());
        appointmentDTO.setDuration(appointment.getDuration());
        appointmentDTO.setStatus(appointment.getStatus());
        appointmentDTO.setDoctorId(appointment.getDoctor().getId());
        appointmentDTO.setDoctorName(appointment.getDoctor().getLastName()); 
        appointmentDTO.setPatientId(appointment.getPatient().getId());
        appointmentDTO.setPatientName(appointment.getPatient().getLastName()); 
        appointmentDTO.setPatientPhone(appointment.getPatient().getPhone());
        appointmentDTO.setPatientUserName(appointment.getPatient().getUserName());
        return appointmentDTO;
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment saveAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setId(appointmentDTO.getId());
        appointment.setDate(appointmentDTO.getDate());
        appointment.setTime(appointmentDTO.getTime());
        appointment.setDuration(appointmentDTO.getDuration());
        appointment.setStatus(appointmentDTO.getStatus());

        Doctor doctor = doctorRepository.findById(appointmentDTO.getDoctorId())
        .orElseThrow(() -> new RuntimeException("Doctor not found"));
        appointment.setDoctor(doctor);

        Patient patient = patientRepository.findById(appointmentDTO.getPatientId())
        .orElseThrow(() -> new RuntimeException("Patient not found"));

        if(appointmentDTO.getPatientId()==0){
             
        }

        appointment.setPatient(patient);

        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
