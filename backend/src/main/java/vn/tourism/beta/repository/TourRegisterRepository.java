package vn.tourism.beta.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import vn.tourism.beta.entity.TourRegister;

/**
 * Created by stephan on 20.03.16.
 */
public interface TourRegisterRepository extends PagingAndSortingRepository<TourRegister, Long> {
    Long countAllByEnableEquals(Boolean enable);
    TourRegister findByIdAndEnableEquals(Long id, Boolean enable);
}
