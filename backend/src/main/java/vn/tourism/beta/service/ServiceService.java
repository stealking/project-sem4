package vn.tourism.beta.service;

import org.springframework.data.domain.Sort;
import vn.tourism.beta.entity.Service;

import java.util.List;

public interface ServiceService {
    public Service updateServiceById(Service service, Long serviceId);
    public Service deleteServiceById(Long serviceId);
    public List<Service> getPage(int pageNumber, Sort.Direction sortDirection, String column, int maxResult);
}
