package vn.tourism.beta.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import vn.tourism.beta.entity.TourType;

/**
 * Created by stephan on 20.03.16.
 */
public interface TourTypeRepository extends PagingAndSortingRepository<TourType, Long> {
    Long countAllByEnableEquals(Boolean enable);
    TourType findByIdAndEnableEquals(Long id, Boolean enable);
}
