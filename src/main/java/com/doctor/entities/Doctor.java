package com.doctor.entities;

import com.doctor.ENUM.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Data
@Table(name = "doctors")
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    @Column(name = "role")
    private String role;

    @Column(name = "email", unique = true, nullable = false)
//    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
//            message = "Email is mandatory and  must follow the pattern: abc@abc.com")
    private String doctorEmail;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender doctorGender;

    @Column(name = "mobile_number", unique = true, nullable = false)
//    @Pattern(regexp = "[6789][0-9]{9}", message = "Enter valid mobile number")
    private String doctorMobileNumber;

    @Column(name = "name", nullable = false)
//    @Size(min = 3, max = 100, message = "Name must contain min 3 and max 100 characters")
    private String doctorName;

    @Column(name = "password", nullable = false)
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
//            message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character, no whitespace and must be atleast 8 characters long!")
    private String doctorPassword;

    @Column(name = "specialization")
    private String doctorSpecialization;

    @Column(name = "hospital_name", nullable = false)
//    @Size(min = 3, max = 100, message = "Hospital name must contain min 3 and max 100 characters")
    private String hospitalName;

    @Column(name = "register_date", nullable = false)
    private Date registerDate;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @Column(name = "updatedDate")
    private Date updateDate;

    //by default, fetch type lazy hi hota hai, so not necessary to use
//    @OneToOne(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //OneToOne since one doctor can work in only one hospital
//    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL)
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "doctor_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "hospital_id")
    private HospitalDetails hospitalDetails;


//******************************************
    //user
    //appointment list type


//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Users user;

//    @OneToMany(mappedBy = "doctors", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "doctor_id")
//    private List<Appointment> appointments = new ArrayList<>();



}
