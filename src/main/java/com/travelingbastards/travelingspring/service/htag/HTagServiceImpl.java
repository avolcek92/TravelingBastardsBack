package com.travelingbastards.travelingspring.service.htag;

import com.travelingbastards.travelingspring.model.HTag;
import com.travelingbastards.travelingspring.repository.HTagRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HTagServiceImpl implements HTagService {

    @Resource
    private HTagRepository hTagRepository;

    @Override
    public List<HTag> findAll() {
        return hTagRepository.findAll();
    }

    @Override
    public void saveHTag(HTag hTag) {
        hTagRepository.save(hTag);
    }

    @Override
    public void deleteHTag(HTag hTag) {
        hTagRepository.delete(hTag);

    }

    @Override
    public HTag findById(long id) {
        HTag hTag = hTagRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid htag ID: " + id));
        return hTag;
    }
}
