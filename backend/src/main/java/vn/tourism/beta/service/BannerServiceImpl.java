package vn.tourism.beta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.tourism.beta.entity.Banner;
import vn.tourism.beta.repository.BannerRepository;

import java.util.Date;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService{
    @Autowired
    private BannerRepository serviceRepository;

    @Override
    public Banner updateBannerById(Banner service, Long serviceId) {
        Banner BannerDb = serviceRepository.findOne(serviceId);
        if(service.getImage() != null)BannerDb.setImage(service.getImage());
        if(service.getIntroduction() != null)BannerDb.setIntroduction(service.getIntroduction());
        if(service.getContent() != null)BannerDb.setContent(service.getContent());
        if(service.getHref() != null)BannerDb.setHref(service.getHref());
        if(service.getUpdatedOn() != null)BannerDb.setUpdatedOn(new Date());
        if(service.getUpdatedBy() != null)BannerDb.setUpdatedBy(service.getUpdatedBy());
        serviceRepository.save(BannerDb);
        return BannerDb;
    }

    @Override
    public Banner deleteBannerById( Long serviceId) {
        Banner BannerDb = serviceRepository.findOne(serviceId);
        BannerDb.setEnable(false);
        serviceRepository.save(BannerDb);
        return BannerDb;
    }
    @Override
    public List<Banner> getPage(int pageNumber, Sort.Direction sortDirection, String column, int maxResult){
        PageRequest request = new PageRequest(pageNumber - 1, maxResult, sortDirection, column);

        return serviceRepository.findAll(request).getContent();
    }
}
