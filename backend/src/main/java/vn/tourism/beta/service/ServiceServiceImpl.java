package vn.tourism.beta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import vn.tourism.beta.entity.Service;
import vn.tourism.beta.repository.ServiceRepository;

import java.util.Date;
import java.util.List;

@Controller
public class ServiceServiceImpl implements ServiceService{
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Service updateServiceById(Service service, Long serviceId) {
        Service ServiceDb = serviceRepository.findOne(serviceId);
        if(service.getImage() != null)ServiceDb.setImage(service.getImage());
        if(service.getIntroduction() != null)ServiceDb.setIntroduction(service.getIntroduction());
        if(service.getContent() != null)ServiceDb.setContent(service.getContent());
        if(service.getTag() != null)ServiceDb.setTag(service.getTag());
        if(service.getUpdatedOn() != null)ServiceDb.setUpdatedOn(new Date());
        if(service.getUpdatedBy() != null)ServiceDb.setUpdatedBy(service.getUpdatedBy());
        serviceRepository.save(ServiceDb);
        return ServiceDb;
    }

    @Override
    public Service deleteServiceById( Long serviceId) {
        Service ServiceDb = serviceRepository.findOne(serviceId);
        ServiceDb.setEnable(false);
        serviceRepository.save(ServiceDb);
        return ServiceDb;
    }
    @Override
    public List<Service> getPage(int pageNumber, Sort.Direction sortDirection, String column, int maxResult){
        PageRequest request = new PageRequest(pageNumber - 1, maxResult, sortDirection, column);

        return serviceRepository.findAll(request).getContent();
    }
}
