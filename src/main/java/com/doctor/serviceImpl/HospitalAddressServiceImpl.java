package com.doctor.serviceImpl;

import com.doctor.ENUM.Country;
import com.doctor.entities.HospitalAddress;
import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;
import com.doctor.payloads.InputValidator;
import com.doctor.repositories.IHospitalRepository;
import com.doctor.requestDto.HospitalAddressRequestDto;
import com.doctor.services.IHospitalAddressService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HospitalAddressServiceImpl implements IHospitalAddressService {

    @Autowired
    private IHospitalRepository iHospitalRepository;

    private Logger logger = LoggerFactory.getLogger(HospitalAddressServiceImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public HospitalAddressRequestDto saveHospitalAddress(HospitalAddressRequestDto hospitalAddressRequestDto) {
       HospitalAddress hospitalAddress = new HospitalAddress();

        System.out.println(hospitalAddressRequestDto.getCountry());

        Country country = Arrays.stream(Country.values())
                                .filter(countryName -> countryName.name().trim().equalsIgnoreCase(hospitalAddressRequestDto.getCountry()))
                                .findFirst()
                                .orElse(null);

        if(country != null) {
            hospitalAddress.setCountry(country);
            hospitalAddress.setAddressName(hospitalAddressRequestDto.getAddressName().trim());
            hospitalAddress.setCity(hospitalAddressRequestDto.getCity().trim());
            hospitalAddress.setState(hospitalAddressRequestDto.getState().trim());

            if (hospitalAddressRequestDto.getZipCode().length() == 6 && InputValidator.isNumeric(hospitalAddressRequestDto.getZipCode())) {
                hospitalAddress.setZipCode(hospitalAddressRequestDto.getZipCode());
            } else
                throw new ControllerException("Zipcode must contain exactly 6 digits");

            HospitalAddress hospitalAddress1 = this.iHospitalRepository.save(hospitalAddress);

            HospitalAddressRequestDto savedEntityToDto = new HospitalAddressRequestDto();

            savedEntityToDto.setAddressName(hospitalAddress1.getAddressName());
            savedEntityToDto.setCity(hospitalAddress1.getCity());
            savedEntityToDto.setState(hospitalAddress1.getState());
            savedEntityToDto.setCountry(country.name());
            savedEntityToDto.setZipCode(hospitalAddress1.getZipCode());

            return savedEntityToDto;
        }
        else
            throw new ControllerException("Country not available in our database yet, sorry for the inconvenience");
    }

    @Override
    public HospitalAddressRequestDto updateHospitalByHospitalId(Long hospitalId, HospitalAddressRequestDto hospitalAddressRequestDto) throws BusinessException {

        HospitalAddress hospitalAddress = this.iHospitalRepository.findById(hospitalId).orElseThrow(
                () -> new BusinessException("Hospital Address Not Found with given Id : " + hospitalId)
        );

        Country country = Arrays.stream(Country.values())
                .filter(countryName -> countryName.name().trim().equalsIgnoreCase(hospitalAddressRequestDto.getCountry()))
                .findFirst()
                .orElse(null);

        if(country != null) {
            hospitalAddress.setAddressName(hospitalAddressRequestDto.getAddressName().trim());
            hospitalAddress.setCity(hospitalAddressRequestDto.getCity().trim());
            hospitalAddress.setState(hospitalAddressRequestDto.getState().trim());
            hospitalAddress.setZipCode(hospitalAddressRequestDto.getZipCode());
            hospitalAddress.setCountry(country);

            if (hospitalAddressRequestDto.getZipCode().length() == 6 && InputValidator.isNumeric(hospitalAddressRequestDto.getZipCode())) {
                hospitalAddress.setZipCode(hospitalAddressRequestDto.getZipCode());
            } else
                throw new ControllerException("Zipcode must contain exactly 6 digits");

            HospitalAddress updatedHospitalAddress = this.iHospitalRepository.save(hospitalAddress);

            HospitalAddressRequestDto updatedEntityToDto = new HospitalAddressRequestDto();

            updatedEntityToDto.setAddressName(updatedHospitalAddress.getAddressName());
            updatedEntityToDto.setCity(updatedHospitalAddress.getCity());
            updatedEntityToDto.setState(updatedHospitalAddress.getState());
            updatedEntityToDto.setCountry(country.name());
            updatedEntityToDto.setZipCode(updatedHospitalAddress.getZipCode());

            return updatedEntityToDto;
        }
        else
            throw new ControllerException("Update failed since given country not available in our database yet, sorry for the inconvenience");
    }

    @Override
    public String deleteHospitalAddressByHospitalId(Long hospitalId) throws BusinessException {
        HospitalAddress hospitalAddress = this.iHospitalRepository.findById(hospitalId).orElseThrow(
                () -> new BusinessException("Hospital Address Not Found with given Id : " + hospitalId)
        );
        this.iHospitalRepository.delete(hospitalAddress);
        return "Hospital address with id : " + hospitalId + " deleted successfully";
    }


    @Override
    public HospitalAddressRequestDto getHospitalAddressById(Long hospitalAddressId) throws BusinessException {
        HospitalAddress hospitalAddress = iHospitalRepository.findById(hospitalAddressId).orElseThrow(
                () -> new BusinessException("Hospital Address Not Found with given Id : " + hospitalAddressId)
        );
        if (hospitalAddress != null) {
            HospitalAddressRequestDto hospitalAddressRequestDto = new HospitalAddressRequestDto();

            hospitalAddressRequestDto.setAddressName(hospitalAddress.getAddressName());
            hospitalAddressRequestDto.setCity(hospitalAddress.getCity());
            hospitalAddressRequestDto.setState(hospitalAddress.getState());
            hospitalAddressRequestDto.setZipCode(hospitalAddress.getZipCode());
            hospitalAddressRequestDto.setCountry(String.valueOf(hospitalAddress.getCountry()));

            logger.info(hospitalAddressRequestDto.toString());

            return hospitalAddressRequestDto;
        } else
            throw new ControllerException("Hospital Address Not Found due to some error!");
    }

    @Override
    public List<HospitalAddressRequestDto> getAllHospitalAddresses() {
        List<HospitalAddress> hospitalAddressList = this.iHospitalRepository.findAll();
        if (hospitalAddressList.isEmpty())
            throw new ControllerException("NO HOSPITAL ADDRESSES FOUND");
        else
        {
            List<HospitalAddressRequestDto> hospitalAddressRequestDtoList = hospitalAddressList.stream()
                    .map(address -> modelMapper.map(address, HospitalAddressRequestDto.class)).toList();
            return hospitalAddressRequestDtoList;
        }
    }



}
