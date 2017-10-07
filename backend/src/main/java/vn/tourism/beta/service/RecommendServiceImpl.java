package vn.tourism.beta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import vn.tourism.beta.entity.Recommend;
import vn.tourism.beta.repository.RecommendRepository;

import java.util.Date;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService{
    @Autowired
    private RecommendRepository serviceRepository;

    @Override
    public Recommend updateRecommendById(Recommend service, Long serviceId) {
        Recommend RecommendDb = serviceRepository.findOne(serviceId);
        if(service.getImage() != null)RecommendDb.setImage(service.getImage());
        if(service.getIntroduction() != null)RecommendDb.setIntroduction(service.getIntroduction());
        if(service.getContent() != null)RecommendDb.setContent(service.getContent());
        if(service.getTag() != null)RecommendDb.setTag(service.getTag());
        if(service.getUpdatedOn() != null)RecommendDb.setUpdatedOn(new Date());
        if(service.getUpdatedBy() != null)RecommendDb.setUpdatedBy(service.getUpdatedBy());
        serviceRepository.save(RecommendDb);
        return RecommendDb;
    }

    @Override
    public Recommend deleteRecommendById( Long serviceId) {
        Recommend RecommendDb = serviceRepository.findOne(serviceId);
        RecommendDb.setEnable(false);
        serviceRepository.save(RecommendDb);
        return RecommendDb;
    }
    @Override
    public List<Recommend> getPage(int pageNumber, Sort.Direction sortDirection, String column, int maxResult){
        PageRequest request = new PageRequest(pageNumber - 1, maxResult, sortDirection, column);

        return serviceRepository.findAll(request).getContent();
    }
}
