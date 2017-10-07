package vn.tourism.beta.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import vn.tourism.beta.entity.Recommend;

/**
 * Created by stephan on 20.03.16.
 */
public interface RecommendRepository extends PagingAndSortingRepository<Recommend, Long> {
    Long countAllByEnableEquals(Boolean enable);
    Recommend findByIdAndEnableEquals(Long id, Boolean enable);
}
