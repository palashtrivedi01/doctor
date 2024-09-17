package com.doctor.serviceImpl;

import com.doctor.entities.HospitalDetails;
import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;
import com.doctor.payloads.InputValidator;
import com.doctor.repositories.IHospitalDetailsRepository;
import com.doctor.requestDto.HospitalDetailsRequestDto;
import com.doctor.services.IHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HospitalServiceImpl implements IHospitalService {

    @Autowired
    private IHospitalDetailsRepository hospitalDetailsRepository;

    @Override
    public HospitalDetailsRequestDto addHospital(HospitalDetailsRequestDto hospitalDetailsRequestDto) {
        HospitalDetails hospitalDetails = new HospitalDetails();

        hospitalDetails.setHospitalName(hospitalDetailsRequestDto.getHospitalName());
        hospitalDetails.setEmergency(hospitalDetailsRequestDto.getEmergency());
        hospitalDetails.setContactNumber(hospitalDetailsRequestDto.getContactNumber());
        hospitalDetails.setNoOfBeds(hospitalDetailsRequestDto.getNoOfBeds());
        hospitalDetails.setNoOfIcu(hospitalDetailsRequestDto.getNoOfIcu());
        hospitalDetails.setNoOfOt(hospitalDetailsRequestDto.getNoOfOt());

        HospitalDetails savedHospitalDetails = hospitalDetailsRepository.save(hospitalDetails);

        HospitalDetailsRequestDto savedHospitalDetailsToDto = new HospitalDetailsRequestDto();

        savedHospitalDetailsToDto.setHospitalName(savedHospitalDetails.getHospitalName());
        savedHospitalDetailsToDto.setEmergency(savedHospitalDetails.getEmergency());
        savedHospitalDetailsToDto.setContactNumber(savedHospitalDetails.getContactNumber());
        savedHospitalDetailsToDto.setNoOfBeds(savedHospitalDetails.getNoOfBeds());
        savedHospitalDetailsToDto.setNoOfIcu(savedHospitalDetails.getNoOfIcu());
        savedHospitalDetailsToDto.setNoOfOt(savedHospitalDetails.getNoOfOt());

        return savedHospitalDetailsToDto;

    }

    @Override
    public HospitalDetailsRequestDto updateHospital(HospitalDetailsRequestDto hospitalDetailsRequestDto, Long hospitalId) throws BusinessException {

        HospitalDetails existingHospital = this.hospitalDetailsRepository.findById(hospitalId).orElseThrow(
                () -> new BusinessException("No hospital exists with the id " + hospitalId)
        );

        existingHospital.setHospitalName(hospitalDetailsRequestDto.getHospitalName());
        existingHospital.setEmergency(hospitalDetailsRequestDto.getEmergency());
        existingHospital.setContactNumber(hospitalDetailsRequestDto.getContactNumber());
        existingHospital.setNoOfBeds(hospitalDetailsRequestDto.getNoOfBeds());
        existingHospital.setNoOfIcu(hospitalDetailsRequestDto.getNoOfIcu());
        existingHospital.setNoOfOt(hospitalDetailsRequestDto.getNoOfOt());

        HospitalDetails updatedHospitalDetails = hospitalDetailsRepository.save(existingHospital);

        HospitalDetailsRequestDto updatedHospitalDetailsToDto = new HospitalDetailsRequestDto();

        updatedHospitalDetailsToDto.setHospitalName(updatedHospitalDetails.getHospitalName());
        updatedHospitalDetailsToDto.setEmergency(updatedHospitalDetails.getEmergency());
        updatedHospitalDetailsToDto.setContactNumber(updatedHospitalDetails.getContactNumber());
        updatedHospitalDetailsToDto.setNoOfBeds(updatedHospitalDetails.getNoOfBeds());
        updatedHospitalDetailsToDto.setNoOfIcu(updatedHospitalDetails.getNoOfIcu());
        updatedHospitalDetailsToDto.setNoOfOt(updatedHospitalDetails.getNoOfOt());

        return updatedHospitalDetailsToDto;
    }

    @Override
    public String deleteHospitalByHospitalId(Long hospitalId) throws BusinessException {
        HospitalDetails hospitalDetails = this.hospitalDetailsRepository.findById(hospitalId).orElseThrow(
                () -> new BusinessException("No hospital exists with the id " + hospitalId)
        );

        this.hospitalDetailsRepository.delete(hospitalDetails);

        return "Hospital Details with id : " + hospitalId + " deleted successfully";
    }

    @Override
    public HospitalDetailsRequestDto getHospitalByHospitalId(Long hospitalId) throws BusinessException {

        HospitalDetails hospitalDetails = this.hospitalDetailsRepository.findById(hospitalId).orElseThrow(
                () -> new BusinessException("No hospital exists with the id " + hospitalId)
        );

        HospitalDetailsRequestDto hospitalDetailsDto = new HospitalDetailsRequestDto();

        hospitalDetailsDto.setHospitalName(hospitalDetails.getHospitalName());
        hospitalDetailsDto.setContactNumber(hospitalDetails.getContactNumber());
        hospitalDetailsDto.setEmergency(hospitalDetails.getEmergency());
        hospitalDetailsDto.setNoOfBeds(hospitalDetails.getNoOfBeds());
        hospitalDetailsDto.setNoOfIcu(hospitalDetails.getNoOfIcu());
        hospitalDetailsDto.setNoOfOt(hospitalDetails.getNoOfOt());

        return hospitalDetailsDto;
    }

    @Override
    public List<HospitalDetailsRequestDto> getAllHospitals() {

        List<HospitalDetails> hospitalDetailsList = this.hospitalDetailsRepository.findAll();
        if(hospitalDetailsList.isEmpty())
            throw new ControllerException("List of hospitals is empty ");
        else{
            List<HospitalDetailsRequestDto> hospitalDetailsDtoList = hospitalDetailsList.stream()
                    .map(hospitalDetails -> {
                        HospitalDetailsRequestDto hospitalDetailsDto = new HospitalDetailsRequestDto();
                        hospitalDetailsDto.setHospitalName(hospitalDetails.getHospitalName());
                        hospitalDetailsDto.setContactNumber(hospitalDetails.getContactNumber());
                        hospitalDetailsDto.setEmergency(hospitalDetails.getEmergency());
                        hospitalDetailsDto.setNoOfBeds(hospitalDetails.getNoOfBeds());
                        hospitalDetailsDto.setNoOfIcu(hospitalDetails.getNoOfIcu());
                        hospitalDetailsDto.setNoOfOt(hospitalDetails.getNoOfOt());
                        return hospitalDetailsDto;
                    }).toList();

            return hospitalDetailsDtoList;
        }
    }

    @Override
    public List<HospitalDetailsRequestDto> getAllHospitalsByHospitalName(String hospitalName) throws BusinessException {

        if(InputValidator.isNumeric(hospitalName))
            throw new BusinessException("Number cannot be passed as Hospital name");

        List<HospitalDetails> existingHospitalsByName = this.hospitalDetailsRepository.findByHospitalName(hospitalName);
        if(existingHospitalsByName.isEmpty())
            throw new ControllerException("No hospitals found with given name : " + hospitalName);
        else{
            return existingHospitalsByName.stream().map(hospitalDetails -> {
                HospitalDetailsRequestDto hospitalDetailsDto = new HospitalDetailsRequestDto();
                hospitalDetailsDto.setHospitalName(hospitalDetails.getHospitalName());
                hospitalDetailsDto.setContactNumber(hospitalDetails.getContactNumber());
                hospitalDetailsDto.setEmergency(hospitalDetails.getEmergency());
                hospitalDetailsDto.setNoOfBeds(hospitalDetails.getNoOfBeds());
                hospitalDetailsDto.setNoOfIcu(hospitalDetails.getNoOfIcu());
                hospitalDetailsDto.setNoOfOt(hospitalDetails.getNoOfOt());

                return hospitalDetailsDto;
            }).toList();
        }

    }
}
