package vn.tourism.beta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.tourism.beta.entity.Tour;
import vn.tourism.beta.entity.TourType;
import vn.tourism.beta.entity.Transport;

/**
 * Created by stephan on 20.03.16.
 */
public interface TourRepository extends PagingAndSortingRepository<Tour, Long>, JpaSpecificationExecutor<Tour> {
    Long countAllByEnableEquals(Boolean enable);
    Tour findByIdAndEnableEquals(Long id, Boolean enable);
    Page<Tour> findAllByEnableEquals(Pageable pageable, Boolean enable);
//    Page<Tour> findByTourType_IdAndDepartmentPointIgnoreCaseContainingAndDestinationIgnoreCaseContainingAndEnableEquals(Pageable pageable, Long tourTypeId, String from, String to, Boolean enable);
//    Page<Tour> findByTourType_IdAndEnableEquals(Pageable pageable, Long tourTypeId, Boolean enable);
//    Page<Tour> findByDepartmentPointIgnoreCaseContainingAndDestinationIgnoreCaseContainingAndEnableEquals(Pageable pageable, String from, String to, Boolean enable);
//    Page<Tour> findByTourType_IdEqualsAndEnableEquals(Pageable pageable, Long id, Boolean enable);
//    Page<Tour> findByTransport_NameEqualsIgnoreCaseContainingAndEnableEquals(Pageable pageable, Long id, Boolean enable);
//    Page<Tour> findByRoute_NameEqualsIgnoreCaseContainingAndEnableEquals(Pageable pageable, Long id, Boolean enable);
//    Page<Tour> findByRoute_NameEqualsIgnoreCaseContainingAndDeparture_NameEqualsIgnoreCaseContainingAndEnableEquals(Pageable pageable, Long id, Boolean enable);
//    Page<Tour> findByTourType_IdEqualsAndRoute_NameEqualsIgnoreCaseContainingAndEnableEquals(Pageable pageable, Long id, Boolean enable);
//    Page<Tour> findByTourType_IdEqualsAndRoute_NameEqualsIgnoreCaseContainingAndRoute_NameEqualsIgnoreCaseContainingAndEnableEquals(Pageable pageable, Long id, Boolean enable);
}
