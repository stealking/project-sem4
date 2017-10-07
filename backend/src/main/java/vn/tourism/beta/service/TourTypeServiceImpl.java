package vn.tourism.beta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.tourism.beta.entity.Tour;
import vn.tourism.beta.entity.TourType;
import vn.tourism.beta.repository.TourRepository;
import vn.tourism.beta.repository.TourTypeRepository;
import vn.tourism.beta.service.TourTypeService;

import java.util.Date;
import java.util.List;

@Service
public class TourTypeServiceImpl implements TourTypeService {
    @Autowired
    private TourTypeRepository tourTypeRepository;

    @Override
    public TourType updateTourTypeById(TourType tourType, Long TourTypeId) {
        TourType TourTypeDb = tourTypeRepository.findOne(TourTypeId);
        if(tourType.getName() != null)TourTypeDb.setName(tourType.getName());
        if(tourType.getUpdatedOn() != null)TourTypeDb.setUpdatedOn(new Date());
        if(tourType.getUpdatedBy() != null)TourTypeDb.setUpdatedBy(tourType.getUpdatedBy());
        tourTypeRepository.save(TourTypeDb);
        return TourTypeDb;
    }

    @Override
    public TourType deleteTourTypeById( Long tourTypeId) {
        TourType TourTypeDb = tourTypeRepository.findOne(tourTypeId);
        TourTypeDb.setEnable(false);
        tourTypeRepository.save(TourTypeDb);
        return TourTypeDb;
    }
    @Override
    public List<TourType> getPage(int pageNumber, Sort.Direction sortDirection, String column, int maxResult){
        PageRequest request = new PageRequest(pageNumber - 1, maxResult, sortDirection, column);
        return tourTypeRepository.findAll(request).getContent();
    }
}
