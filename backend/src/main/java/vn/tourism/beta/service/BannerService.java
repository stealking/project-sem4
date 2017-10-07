package vn.tourism.beta.service;

import org.springframework.data.domain.Sort;
import vn.tourism.beta.entity.Banner;

import java.util.List;

public interface BannerService {
    public Banner updateBannerById(Banner banner, Long bannerId);
    public Banner deleteBannerById(Long bannerId);
    public List<Banner> getPage(int pageNumber, Sort.Direction sortDirection, String column, int maxResult);
}
