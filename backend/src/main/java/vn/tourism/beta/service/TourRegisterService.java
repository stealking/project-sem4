package vn.tourism.beta.service;

import org.springframework.data.domain.Sort;
import vn.tourism.beta.entity.TourRegister;

import java.util.List;

public interface TourRegisterService {
    public TourRegister updateTourRegisterById(TourRegister tourRegister, Long TourRegisterId);
    public TourRegister deleteTourRegisterById(Long tourRegisterId);
    public List<TourRegister> getPage(int pageNumber, Sort.Direction sortDirection, String column, int maxResult);
}
