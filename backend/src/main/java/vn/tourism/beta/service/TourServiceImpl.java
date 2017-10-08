package vn.tourism.beta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.tourism.beta.entity.Tour;
import vn.tourism.beta.repository.TourRepository;

import java.util.Date;
import java.util.List;

@Service
public class TourServiceImpl implements TourService{
    @Autowired
    private TourRepository tourRepository;

    @Override
    public Tour updateTourById(Tour tour, Long tourId) {
        Tour TourDb = tourRepository.findOne(tourId);

        if(tour.getIntroduction() != null)TourDb.setIntroduction(tour.getIntroduction());
        if(tour.getDetail() != null)TourDb.setDetail(tour.getDetail());
        if(tour.getSummary() != null)TourDb.setSummary(tour.getSummary());
        if(tour.getPolicy() != null)TourDb.setPolicy(tour.getPolicy());
        if(tour.getTourType() != null)TourDb.setTourType(tour.getTourType());
        if(tour.getJourney() != null)TourDb.setJourney(tour.getJourney());
        if(tour.getImage() != null)TourDb.setImage(tour.getImage());
//        if(tour.getDepartmentPoint() != null)TourDb.setDepartmentPoint(tour.getDepartmentPoint());
        if(tour.getTotalTime() != null)TourDb.setTotalTime(tour.getTotalTime());
//        if(tour.getDestination() != null)TourDb.setDestination(tour.getDestination());
        if(tour.getTransport() != null)TourDb.setTransport(tour.getTransport());

        if(tour.getUpdatedOn() != null)TourDb.setUpdatedOn(new Date());
        if(tour.getIntroduction() != null)TourDb.setIntroduction(tour.getIntroduction());

        tourRepository.save(TourDb);
        return TourDb;
    }

    @Override
    public Tour deleteTourById( Long tourId) {
        Tour TourDb = tourRepository.findOne(tourId);
        TourDb.setEnable(false);
        tourRepository.save(TourDb);
        return TourDb;
    }
    @Override
    public List<Tour> getPage(int pageNumber, Sort.Direction sortDirection, String column, int maxResult){
        PageRequest request = new PageRequest(pageNumber - 1, maxResult, sortDirection, column);

        return tourRepository.findAll(request).getContent();
    }
}
