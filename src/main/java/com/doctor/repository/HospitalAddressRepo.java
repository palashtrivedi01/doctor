package com.doctor.repository;

import com.doctor.entity.HospitalAddress;
import com.doctor.requestdto.HospitalAddressRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface HospitalAddressRepo extends JpaRepository<HospitalAddress, Long> {
    public HospitalAddressRequestDto findByAddressId(Long addressId);
}
