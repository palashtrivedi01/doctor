package com.doctor.serviceimpl;

import com.doctor.entity.HospitalAddress;
import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;
import com.doctor.repository.HospitalAddressRepo;
import com.doctor.requestdto.HospitalAddressRequestDto;
import com.doctor.services.HospitalAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HospitalAddressServiceImpl implements HospitalAddressService {
    @Autowired
    private HospitalAddressRepo hospitalAddressRepo;

    @Override
    public HospitalAddressRequestDto getHospitalAddressById(Long addressId) throws BusinessException, ControllerException {
        HospitalAddress byId = hospitalAddressRepo.findById(addressId).orElseThrow(() -> new BusinessException("Invalid Id Given by User"));
        if (byId != null) {
            HospitalAddressRequestDto hospitalAddressRequestDto = new HospitalAddressRequestDto();
            hospitalAddressRequestDto.setAddressName(byId.getAddressName());
            hospitalAddressRequestDto.setCity(byId.getCity());
            hospitalAddressRequestDto.setState(byId.getState());
            hospitalAddressRequestDto.setZipCode(byId.getZipCode());
            hospitalAddressRequestDto.setCountry(byId.getCountry());
            return hospitalAddressRequestDto;
        }
        throw new ControllerException("Server error");

    }


}
