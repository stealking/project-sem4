package vn.tourism.beta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.tourism.beta.entity.TourType;
import vn.tourism.beta.entity.User;

/**
 * Created by stephan on 20.03.16.
 */
public interface TourTypeRepository extends PagingAndSortingRepository<TourType, Long> {
    Long countAllByEnableEquals(Boolean enable);
    TourType findByIdAndEnableEquals(Long id, Boolean enable);
    Page<TourType> findAllByEnableEquals(Pageable pageable, Boolean enable);
}
