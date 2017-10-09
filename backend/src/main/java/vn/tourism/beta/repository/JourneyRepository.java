package vn.tourism.beta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.tourism.beta.entity.Journey;

/**
 * Created by stephan on 20.03.16.
 */
public interface JourneyRepository extends PagingAndSortingRepository<Journey, Long> {
    Long countAllByEnableEquals(Boolean enable);
    Journey findByIdAndEnableEquals(Long id, Boolean enable);
    Page<Journey> findAllByEnableEquals(Pageable pageable, Boolean enable);
}
