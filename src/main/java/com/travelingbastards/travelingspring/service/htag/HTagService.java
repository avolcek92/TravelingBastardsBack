package com.travelingbastards.travelingspring.service.htag;

import com.travelingbastards.travelingspring.model.HTag;

import java.util.List;

public interface HTagService {

        List<HTag> findAll();

        void saveHTag(HTag hTag);

        void deleteHTag(HTag hTag);

        HTag findById(long id);

    }
