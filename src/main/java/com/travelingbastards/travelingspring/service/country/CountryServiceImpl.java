package com.travelingbastards.travelingspring.service.country;

import com.travelingbastards.travelingspring.model.Country;
import com.travelingbastards.travelingspring.repository.CountryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{

    @Resource
    private CountryRepository countryRepository;

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(long id) {
        Country country = countryRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid country ID: " + id));
        return country;
    }


}
