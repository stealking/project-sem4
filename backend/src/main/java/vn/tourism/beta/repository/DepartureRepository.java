package vn.tourism.beta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.tourism.beta.entity.Departure;

import java.util.List;

/**
 * Created by stephan on 20.03.16.
 */
public interface DepartureRepository extends PagingAndSortingRepository<Departure, Long> {
    List<Departure> findAllByEnableTrue();
    Departure findByIdAndEnableEquals(Long id, Boolean enable);
    Page<Departure> findAllByEnableEquals(Pageable pageable, Boolean enable);
}
