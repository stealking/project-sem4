package vn.tourism.beta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.tourism.beta.entity.TourRegister;
import vn.tourism.beta.repository.TourRegisterRepository;

import java.util.List;

@Service
public class TourRegisterServiceImpl implements TourRegisterService {
    @Autowired
    private TourRegisterRepository tourRegisterRepository;

    @Override
    public TourRegister updateTourRegisterById(TourRegister tourRegister, Long TourRegisterId) {
        TourRegister TourRegisterDb = tourRegisterRepository.findOne(TourRegisterId);
        if(tourRegister.getRepresentative() != null)TourRegisterDb.setRepresentative(tourRegister.getRepresentative());
        if(tourRegister.getTourDetail() != null)TourRegisterDb.setTourDetail(tourRegister.getTourDetail());

        if(tourRegister.getNumberAdult() != null)TourRegisterDb.setNumberAdult(tourRegister.getNumberAdult());
        if(tourRegister.getNumberChildren() != null)TourRegisterDb.setNumberChildren(tourRegister.getNumberChildren());
        if(tourRegister.getNumberBaby() != null)TourRegisterDb.setNumberBaby(tourRegister.getNumberBaby());

        if(tourRegister.getRegisteredOn() != null)TourRegisterDb.setRegisteredOn(tourRegister.getRegisteredOn());
        tourRegisterRepository.save(TourRegisterDb);
        return TourRegisterDb;
    }

    @Override
    public TourRegister deleteTourRegisterById( Long tourRegisterId) {
        TourRegister TourRegisterDb = tourRegisterRepository.findOne(tourRegisterId);
        TourRegisterDb.setEnable(false);
        tourRegisterRepository.save(TourRegisterDb);
        return TourRegisterDb;
    }
    @Override
    public List<TourRegister> getPage(int pageNumber, Sort.Direction sortDirection, String column, int maxResult){
        PageRequest request = new PageRequest(pageNumber - 1, maxResult, sortDirection, column);
        return tourRegisterRepository.findAll(request).getContent();
    }
}
