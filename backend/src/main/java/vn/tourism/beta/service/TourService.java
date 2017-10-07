package vn.tourism.beta.service;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.tourism.beta.entity.Tour;
import vn.tourism.beta.entity.User;

import java.util.List;

public interface TourService {
    public Tour updateTourById(Tour tour, Long tourId);
    public Tour deleteTourById(Long tourId);
    public List<Tour> getPage(int pageNumber, Sort.Direction sortDirection, String column, int maxResult);
}
