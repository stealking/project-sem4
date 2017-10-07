package vn.tourism.beta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.tourism.beta.entity.Tourist;
import vn.tourism.beta.repository.TouristRepository;

import java.util.List;

@Service
public class TouristServiceImpl implements TouristService {
    @Autowired
    private TouristRepository touristRepository;

    @Override
    public Tourist updateTouristById(Tourist tourist, Long touristId) {
        Tourist touristDb = touristRepository.findOne(touristId);
        if (tourist.getFullName() != null) touristDb.setFullName(tourist.getFullName());
        if (tourist.getIdentityNumber() != null) touristDb.setIdentityNumber(tourist.getIdentityNumber());
        if (tourist.getGender() != null) touristDb.setGender(tourist.getGender());
        if (tourist.getDob() != null) touristDb.setDob(tourist.getDob());
        if (tourist.getAddress() != null) touristDb.setAddress(tourist.getAddress());
        if (tourist.getPhone() != null) touristDb.setPhone(tourist.getPhone());
        if (tourist.getUpdatedBy() != null) touristDb.setUpdatedBy(tourist.getUpdatedBy());
        if (tourist.getUpdatedOn() != null) touristDb.setUpdatedOn(tourist.getUpdatedOn());
        touristRepository.save(touristDb);
        return touristDb;
    }

    @Override
    public Tourist deleteTouristById(Long touristId) {
        Tourist touristDb = touristRepository.findOne(touristId);
        touristDb.setEnable(false);
        touristRepository.save(touristDb);
        return touristDb;
    }
    @Override
    public List<Tourist>getPage(int pageNumber, Sort.Direction sortDirection,String column,  int maxResult){
        PageRequest request = new PageRequest(pageNumber - 1, maxResult, sortDirection, column);

        return touristRepository.findAll(request).getContent();
    }
}
