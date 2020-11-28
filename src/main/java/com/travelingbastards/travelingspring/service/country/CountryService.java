package com.travelingbastards.travelingspring.service.country;

import com.travelingbastards.travelingspring.model.Country;

import java.util.List;

public interface CountryService {

        List<Country> findAll();
        Country findById(long id);

    }
