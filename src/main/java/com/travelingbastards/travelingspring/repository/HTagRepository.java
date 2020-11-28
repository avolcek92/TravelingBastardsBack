package com.travelingbastards.travelingspring.repository;

import com.travelingbastards.travelingspring.model.HTag;
import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface HTagRepository extends CrudRepository <HTag, Long> {
    List<HTag> findAll();
}
