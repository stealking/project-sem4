package vn.tourism.beta.service;

import org.springframework.data.domain.Sort;
import vn.tourism.beta.entity.TourDetail;

import java.util.List;

public interface TourDetailService {
    public TourDetail updateTourDetailById(TourDetail tourDetail, Long tourDetailId);
    public TourDetail deleteTourDetailById(Long tourDetailId);
    public List<TourDetail> getPage(int pageNumber, Sort.Direction sortDirection, String column, int maxResult);
}
