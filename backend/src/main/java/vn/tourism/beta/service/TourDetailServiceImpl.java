package vn.tourism.beta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.tourism.beta.entity.TourDetail;
import vn.tourism.beta.repository.TourDetailRepository;

import java.util.Date;
import java.util.List;

@Service
public class TourDetailServiceImpl implements TourDetailService{
    @Autowired
    private TourDetailRepository tourDetailRepository;

    @Override
    public TourDetail updateTourDetailById(TourDetail tourDetail, Long tourDetailId) {
        TourDetail TourDetailDb = tourDetailRepository.findOne(tourDetailId);
        if(tourDetail.getVoucher() != null)TourDetailDb.setVoucher(tourDetail.getVoucher());
        if(tourDetail.getDetails() != null)TourDetailDb.setDetails(tourDetail.getDetails());

        if(tourDetail.getPriceAdult() != null)TourDetailDb.setPriceAdult(tourDetail.getPriceAdult());
        if(tourDetail.getPriceChildren() != null)TourDetailDb.setPriceChildren(tourDetail.getPriceChildren());
        if(tourDetail.getPriceBaby() != null)TourDetailDb.setPriceBaby(tourDetail.getPriceBaby());
        if(tourDetail.getExCost() != null)TourDetailDb.setExCost(tourDetail.getExCost());
        if(tourDetail.getDepartmentDate() != null)TourDetailDb.setDepartmentDate(tourDetail.getDepartmentDate());


//        if(tourDetail.getTour() != null)TourDetailDb.setTour(tourDetail.getTour());
        if(tourDetail.getUpdatedOn() != null)TourDetailDb.setUpdatedOn(new Date());
//        if(tourDetail.getUpdatedBy() != null)TourDetailDb.setUpdatedBy(tourDetail.getUpdatedBy());

        tourDetailRepository.save(TourDetailDb);
        return TourDetailDb;
    }

    @Override
    public TourDetail deleteTourDetailById( Long tourDetailId) {
        TourDetail TourDetailDb = tourDetailRepository.findOne(tourDetailId);
        TourDetailDb.setEnable(false);
        tourDetailRepository.save(TourDetailDb);
        return TourDetailDb;
    }
    @Override
    public List<TourDetail> getPage(int pageNumber, Sort.Direction sortDirection, String column, int maxResult){
        PageRequest request = new PageRequest(pageNumber - 1, maxResult, sortDirection, column);

        return tourDetailRepository.findAll(request).getContent();
    }
}
