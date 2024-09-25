package com.doctor.service.serviceImpl;

import com.doctor.dto.HospitalAddressDto;
import com.doctor.dto.HospitalDto;
import com.doctor.entities.Doctor;
import com.doctor.entities.HospitalAddress;
import com.doctor.entities.HospitalDetails;
import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;
import com.doctor.exception.DoctorNotFoundException;
import com.doctor.repository.HospitalDetailsRepository;
import com.doctor.service.HospitalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalDetailsServiceImpl implements HospitalDetailService {
    @Autowired
    private HospitalDetailsRepository hospitalDetailsRepository;

    @Override
    public HospitalDetails addHospital(HospitalDto hospitalDto) {
        HospitalDetails hospitalDetails = new HospitalDetails();
        hospitalDetails.setHospitalName(hospitalDto.getHospitalName());
        hospitalDetails.setEmergency(hospitalDto.getEmergency());
        hospitalDetails.setContactNumber(hospitalDto.getContactNumber());
        hospitalDetails.setNoOfIcu(hospitalDto.getNoOfIcu());
        hospitalDetails.setNoOfOt(hospitalDto.getNoOfOt());
        hospitalDetails.setNoOfBeds(hospitalDto.getNoOfBeds());
        hospitalDetailsRepository.save(hospitalDetails);
        return hospitalDetails;
    }


    @Override
    public HospitalDto getHopitalById(Long hospitalId) {
        HospitalDetails hospitalDetails = hospitalDetailsRepository.findById(hospitalId).orElseThrow(()-> new BusinessException("Invalid data"));
        HospitalDto hospitalDto = new HospitalDto();
        hospitalDto.setHospitalName(hospitalDto.getHospitalName());
        hospitalDto.setEmergency(hospitalDto.getEmergency());
        hospitalDto.setContactNumber(hospitalDto.getContactNumber());
        hospitalDto.setNoOfIcu(hospitalDto.getNoOfIcu());
        hospitalDto.setNoOfOt(hospitalDto.getNoOfOt());
        hospitalDto.setNoOfBeds(hospitalDto.getNoOfBeds());
        return hospitalDto;
    }

    @Override
    public List<HospitalDetails> getAllHospital() {
//        HospitalDetails hospitalDetails = hospitalDetailsRepository.findAll().orElseThrow(()-> new BusinessException("Invalid data"));
//        HospitalDto hospitalDto = new HospitalDto();
//        hospitalDetails.setHospitalName(hospitalDetails.getHospitalName());
//        hospitalDetails.setEmergency(hospitalDetails.getEmergency());
//        hospitalDetails.setContactNumber(hospitalDetails.getContactNumber());
//        hospitalDetails.setNoOfIcu(hospitalDetails.getNoOfIcu());
//        hospitalDetails.setNoOfOt(hospitalDetails.getNoOfOt());
//        hospitalDetails.setNoOfBeds(hospitalDetails.getNoOfBeds());
        return hospitalDetailsRepository.findAll();

    }
//
//    @Override
//    public List<HospitalDto> getHospitalByName(String hospitalName) {
//        return hospitalDetailsRepository.findByNameContainingIgnoreCase(hospitalName);
//    }
}

