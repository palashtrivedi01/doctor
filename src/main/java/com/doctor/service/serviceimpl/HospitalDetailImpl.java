package com.doctor.service.serviceimpl;

import com.doctor.dto.HospitalDetailsDto;
import com.doctor.entities.HospitalDetails;
import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;
import com.doctor.repository.HospitalDetailRepo;
import com.doctor.service.HospitalDetailInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalDetailImpl implements HospitalDetailInterface {

    @Autowired
    private HospitalDetailRepo hospitalDetailRepo;


    public HospitalDetailsDto addNewHospital(HospitalDetailsDto hospitalDetailsDto) {

        HospitalDetails hospitalDetails = new HospitalDetails();

        hospitalDetails.setHospitalName(hospitalDetailsDto.getHospitalName());
        hospitalDetails.setContactNumber(hospitalDetailsDto.getContactNumber());
        hospitalDetails.setEmergency(hospitalDetailsDto.getEmergency());
        hospitalDetails.setNoOfBeds(hospitalDetailsDto.getNoOfBeds());
        hospitalDetails.setNoOfIcu(hospitalDetailsDto.getNoOfIcu());
        hospitalDetails.setNoOfOt(hospitalDetailsDto.getNoOfOt());



        hospitalDetailRepo.save(hospitalDetails);
        return hospitalDetailsDto;


    }

    @Override
    public HospitalDetailsDto getHospitalDetailsByHospitalId(Long hospitalId) {

        HospitalDetails hospitalDetails = hospitalDetailRepo.findById(hospitalId).orElseThrow(() -> new BusinessException("Invalid input"));


        if (hospitalDetails != null) {
            HospitalDetailsDto hospitalDetailsDto = new HospitalDetailsDto();
            hospitalDetailsDto.setHospitalName(hospitalDetails.getHospitalName());
            hospitalDetailsDto.setContactNumber(hospitalDetails.getContactNumber());
            hospitalDetailsDto.setEmergency(hospitalDetails.getEmergency());
            hospitalDetailsDto.setNoOfBeds(hospitalDetails.getNoOfBeds());
            hospitalDetailsDto.setNoOfIcu(hospitalDetails.getNoOfIcu());
            hospitalDetailsDto.setNoOfOt(hospitalDetails.getNoOfOt());
            return hospitalDetailsDto;
        }

        throw new ControllerException("Invalid input");

    }

    @Override
    public List<HospitalDetails> getAllHospitals() {

    List<HospitalDetails> hospitalDetails = hospitalDetailRepo.findAll();
    return hospitalDetails;
    }

    @Override
    public List<HospitalDetails> getAllHospitalsByName(String hospitalName) {

        List<HospitalDetails> hospitalDetails=hospitalDetailRepo.findHospitalsByHospitalName(hospitalName);
        return hospitalDetails;
    }


}
