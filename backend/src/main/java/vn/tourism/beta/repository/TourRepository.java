package vn.tourism.beta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.tourism.beta.entity.Tour;

/**
 * Created by stephan on 20.03.16.
 */
public interface TourRepository extends PagingAndSortingRepository<Tour, Long> {
    Long countAllByEnableEquals(Boolean enable);
    Tour findByIdAndEnableEquals(Long id, Boolean enable);
    Page<Tour> findAllByEnableEquals(Pageable pageable, Boolean enable);
    Page<Tour> findByTourType_IdAndDepartmentPointIgnoreCaseContainingAndDestinationIgnoreCaseContainingAndEnableEquals(Pageable pageable, Long tourTypeId, String from, String to, Boolean enable);
    Page<Tour> findByTourType_IdAndEnableEquals(Pageable pageable, Long tourTypeId, Boolean enable);
    Page<Tour> findByDepartmentPointIgnoreCaseContainingAndDestinationIgnoreCaseContainingAndEnableEquals(Pageable pageable, String from, String to, Boolean enable);
}
