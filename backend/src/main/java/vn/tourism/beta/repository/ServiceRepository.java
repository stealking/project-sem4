package vn.tourism.beta.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import vn.tourism.beta.entity.Service;

/**
 * Created by stephan on 20.03.16.
 */
public interface ServiceRepository extends PagingAndSortingRepository<Service, Long> {
    Long countAllByEnableEquals(Boolean enable);
    Service findByIdAndEnableEquals(Long id, Boolean enable);
}
