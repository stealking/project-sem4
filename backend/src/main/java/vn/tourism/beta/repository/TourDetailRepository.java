package vn.tourism.beta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.tourism.beta.entity.TourDetail;

import java.util.List;

/**
 * Created by stephan on 20.03.16.
 */
public interface TourDetailRepository extends PagingAndSortingRepository<TourDetail, Long> {
    Long countAllByEnableEquals(Boolean enable);
    TourDetail findByIdAndEnableEquals(Long id, Boolean enable);
    Page<TourDetail> findAllByTour_idInAndEnableEquals(Pageable pageable, List<Long> id, Boolean enable);
    Page<TourDetail> findAllByTourRegisters_Representative_idEqualsAndEnableEquals(Pageable pageable, Long id, Boolean enable);
}
