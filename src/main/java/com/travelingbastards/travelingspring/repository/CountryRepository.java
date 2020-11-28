package com.travelingbastards.travelingspring.repository;

import com.travelingbastards.travelingspring.model.Country;
import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface CountryRepository extends CrudRepository <Country, Long> {
    List<Country> findAll();
}
