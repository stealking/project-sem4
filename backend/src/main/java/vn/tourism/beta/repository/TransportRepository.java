package vn.tourism.beta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.tourism.beta.entity.Transport;

import java.util.List;

/**
 * Created by stephan on 20.03.16.
 */
public interface TransportRepository extends PagingAndSortingRepository<Transport, Long> {
    List<Transport> findAllByEnableTrue();
    Transport findByIdAndEnableEquals(Long id, Boolean enable);
    Page<Transport> findAllByEnableEquals(Pageable pageable, Boolean enable);
}
