package com.doctor.repositories;

import com.doctor.entities.HospitalAddress;
import com.doctor.exception.BusinessException;
import com.doctor.requestDto.HospitalAddressRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Scanner;

public interface IHospitalRepository extends JpaRepository<HospitalAddress, Long> {

    List<HospitalAddress> findHospitalAddressByCity(String city);

    List<HospitalAddress> findHospitalAddressByCountry(String country);

    List<HospitalAddress> findHospitalAddressByState(String state);
}
