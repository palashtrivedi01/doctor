package com.doctor.serviceimpl;

import com.doctor.entity.HospitalDetails;
import com.doctor.exception.BusinessException;
import com.doctor.repository.HospitalDetailsRepo;
import com.doctor.requestdto.HospitalAddressRequestDto;
import com.doctor.requestdto.HospitalDetailsRequestDto;
import com.doctor.services.HospitalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalDetailsServiceImpl implements HospitalDetailsService {
    @Autowired
    private HospitalDetailsRepo hospitalDetailsRepo;

    @Override
    public HospitalDetailsRequestDto addHospitalDetails(HospitalDetailsRequestDto hospitalDetailsRequestDto) throws BusinessException {
        if (hospitalDetailsRequestDto != null) {
            HospitalDetails hospitalDetails = new HospitalDetails();
            hospitalDetails.setHospitalName(hospitalDetailsRequestDto.getHospitalName());
            hospitalDetails.setEmergency(hospitalDetailsRequestDto.getEmergency());
            hospitalDetails.setContactNumber(hospitalDetailsRequestDto.getContactNumber());
            hospitalDetails.setNoOfBeds(hospitalDetailsRequestDto.getNoOfBeds());
            hospitalDetails.setNoOfIcu(hospitalDetailsRequestDto.getNoOfIcu());
            hospitalDetails.setNoOfOt(hospitalDetailsRequestDto.getNoOfOt());
            hospitalDetailsRepo.save(hospitalDetails);
            return hospitalDetailsRequestDto;
        }
        throw new BusinessException("Invalid data Inserted");
    }

    @Override
    public HospitalDetailsRequestDto getHospitalDetailsById(Long hospitalId) throws BusinessException {
        Optional<HospitalDetails> byId = hospitalDetailsRepo.findById(hospitalId);
        if (byId.isPresent()) {
            HospitalDetailsRequestDto hospitalDetailsRequestDto = new HospitalDetailsRequestDto();
            hospitalDetailsRequestDto.setHospitalName(byId.get().getHospitalName());
            hospitalDetailsRequestDto.setContactNumber(byId.get().getContactNumber());
            hospitalDetailsRequestDto.setNoOfBeds(byId.get().getNoOfBeds());
            hospitalDetailsRequestDto.setEmergency(byId.get().getEmergency());
            hospitalDetailsRequestDto.setNoOfIcu(byId.get().getNoOfIcu());
            hospitalDetailsRequestDto.setNoOfOt(byId.get().getNoOfOt());
            return hospitalDetailsRequestDto;
        }
        throw new BusinessException("Hospital not found with given Hospital Id :" + hospitalId);
    }

    @Override
    public List<HospitalDetailsRequestDto> getAllHospitalDetails() throws BusinessException {
        List<HospitalDetails> all = hospitalDetailsRepo.findAll();
        if (all.isEmpty()) {
            throw new BusinessException("Hospital details not found");
        }
        List<HospitalDetailsRequestDto> list = all.stream().map(hospitalDetails -> {
            HospitalDetailsRequestDto hospitalDetailsRequestDto = new HospitalDetailsRequestDto();
            hospitalDetailsRequestDto.setHospitalName(hospitalDetails.getHospitalName());
            hospitalDetailsRequestDto.setContactNumber(hospitalDetails.getContactNumber());
            hospitalDetailsRequestDto.setNoOfIcu(hospitalDetails.getNoOfIcu());
            hospitalDetailsRequestDto.setNoOfOt(hospitalDetails.getNoOfOt());
            hospitalDetailsRequestDto.setNoOfBeds(hospitalDetails.getNoOfBeds());
            hospitalDetailsRequestDto.setEmergency(hospitalDetails.getEmergency());
            return hospitalDetailsRequestDto;
        }).toList();
        return list;


    }

    @Override
    public HospitalDetailsRequestDto getHospitalByName(String hospitalName) throws BusinessException {
        HospitalDetails byName = hospitalDetailsRepo.findByHospitalName(hospitalName);
        if (byName == null) {
            throw new BusinessException("Hospital name not found");
        }
        HospitalDetailsRequestDto dto = new HospitalDetailsRequestDto();
        dto.setHospitalName(byName.getHospitalName());
        dto.setContactNumber(byName.getContactNumber());
        dto.setNoOfBeds(byName.getNoOfBeds());
        dto.setNoOfIcu(byName.getNoOfIcu());
        dto.setNoOfOt(byName.getNoOfOt());
        dto.setEmergency(byName.getEmergency());
        return dto;
    }
}
