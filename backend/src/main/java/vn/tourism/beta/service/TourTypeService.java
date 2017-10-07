package vn.tourism.beta.service;

import org.springframework.data.domain.Sort;
import vn.tourism.beta.entity.Tour;
import vn.tourism.beta.entity.TourType;

import java.util.List;

public interface TourTypeService {
    public TourType updateTourTypeById(TourType tourType, Long TourTypeId);
    public TourType deleteTourTypeById(Long tourTypeId);
    public List<TourType> getPage(int pageNumber, Sort.Direction sortDirection, String column, int maxResult);
}
