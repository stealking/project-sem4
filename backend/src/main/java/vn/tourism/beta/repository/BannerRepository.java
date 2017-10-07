package vn.tourism.beta.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import vn.tourism.beta.entity.Banner;

/**
 * Created by stephan on 20.03.16.
 */
public interface BannerRepository extends PagingAndSortingRepository<Banner, Long> {
    Long countAllByEnableEquals(Boolean enable);
    Banner findByIdAndEnableEquals(Long id, Boolean enable);
}
