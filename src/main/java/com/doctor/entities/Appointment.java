package com.doctor.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Data
@Table(name = "appointments")
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long appointmentId;

    private String file;

    @Column(name = "appointment_date", nullable = false)
    private Date appointmentDate;

    @Column(name ="doctor_email", unique = true, nullable = false)
    private String doctorEmail;

    @Column(name ="doctor_name", nullable = false)
    private String doctorName;

    @Column(name ="patient_name", nullable = false)
    private String patientName;

    @Column(name ="patient_email", unique = true, nullable = false)
    private String patientEmail;

    @Column(name = "patient_mobile_no", unique = true, nullable = false)
    private String patientMobileNo;

    @CreationTimestamp
    private Timestamp time;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    @JsonIgnore
    private Doctor doctors;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private Patient patients;

//    *************************************************************

    @Column(name = "appointment_image_name")
    @ElementCollection
    @CollectionTable(name = "imageName", joinColumns = @JoinColumn(name = "image_name_fk"))
    @JsonIgnore
    private List<String> imageNames;


}
