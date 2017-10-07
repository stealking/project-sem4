package vn.tourism.beta.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import vn.tourism.beta.entity.Tourist;

/**
 * Created by stephan on 20.03.16.
 */
public interface TouristRepository extends PagingAndSortingRepository<Tourist, Long> {
    Long countAllByEnableEquals(Boolean enable);
    Tourist findByIdAndEnableEquals(Long id, Boolean enable);
}
